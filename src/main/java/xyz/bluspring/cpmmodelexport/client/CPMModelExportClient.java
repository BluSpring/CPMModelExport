package xyz.bluspring.cpmmodelexport.client;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.tom.cpl.util.Image;
import com.tom.cpm.CustomPlayerModels;
import com.tom.cpm.client.CustomPlayerModelsClient;
import com.tom.cpm.shared.MinecraftClientAccess;
import com.tom.cpm.shared.config.ResourceLoader;
import com.tom.cpm.shared.definition.Link;
import com.tom.cpm.shared.definition.ModelDefinition;
import com.tom.cpm.shared.definition.ModelDefinitionLoader;
import com.tom.cpm.shared.editor.Exporter;
import com.tom.cpm.shared.io.ChecksumInputStream;
import com.tom.cpm.shared.io.ChecksumOutputStream;
import com.tom.cpm.shared.io.IOHelper;
import com.tom.cpm.shared.io.SkinDataInputStream;
import com.tom.cpm.shared.parts.*;
import com.tom.cpm.shared.skin.TextureType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.Minecraft;
import xyz.bluspring.cpmmodelexport.mixin.ModelPartLinkAccessor;

import java.io.*;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CPMModelExportClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommandManager.literal("loadmodel")
                .then(
                    ClientCommandManager.argument("url", StringArgumentType.greedyString())
                        .executes(ctx -> {
                            var loader = CustomPlayerModelsClient.mc.getDefinitionLoader();
                            var url = StringArgumentType.getString(ctx, "url");
                            var definition = new ModelDefinition(loader, CustomPlayerModelsClient.mc.getCurrentClientPlayer());
                            try {
                                var link = new Link("git", url);
                                var modelData = loader.load(link, ResourceLoader.ResourceEncoding.BASE64, definition);

                                var list = new LinkedList<IModelPart>();

                                var skinType = new ModelPartDefinitionLink(link);
                                ((ModelPartLinkAccessor) skinType).setDef(definition);

                                var part = (ModelPartDefinition) skinType.resolve();

                                list.add(part);
                                definition.setParts(list);

                                CustomPlayerModelsClient.mc.getCurrentClientPlayer().setModelDefinition(CompletableFuture.completedFuture(definition));

                                var name = "autumn_model";
                                File models = new File(MinecraftClientAccess.get().getGameDir(), "player_models");
                                models.mkdirs();
                                File out = new File(models, name.replaceAll("[^a-zA-Z0-9\\.\\-]", "") + ".cpmmodel");

                                String var10003;
                                for(Random r = new Random(); out.exists(); out = new File(models, var10003 + "_" + Integer.toHexString(r.nextInt()) + ".cpmmodel")) {
                                    var10003 = name.replaceAll("[^a-zA-Z0-9\\.\\-]", "");
                                }

                                FileOutputStream fout = new FileOutputStream(out);

                                var byteOutput = new ByteArrayOutputStream();
                                var ioHelper = new IOHelper(byteOutput);
                                part.write(ioHelper);

                                try {
                                    fout.write(ModelDefinitionLoader.HEADER);
                                    ChecksumOutputStream cos = new ChecksumOutputStream(fout);
                                    IOHelper h = new IOHelper(cos);
                                    h.writeUTF(name);
                                    h.writeUTF("");

                                    var bytes = byteOutput.toByteArray();
                                    h.writeByteArray(bytes);

                                    h.writeVarInt(0);
                                    h.writeVarInt(0);

                                    cos.close();
                                } catch (Throwable var12) {
                                    try {
                                        fout.close();
                                    } catch (Throwable var11) {
                                        var12.addSuppressed(var11);
                                    }

                                    var12.printStackTrace();
                                }

                                fout.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            return 1;
                        })
                ));
        }));
    }

    private void storeModel(String name, String desc, Image icon, byte[] data) throws IOException {
        File models = new File(MinecraftClientAccess.get().getGameDir(), "player_models");
        models.mkdirs();
        File out = new File(models, name.replaceAll("[^a-zA-Z0-9\\.\\-]", "") + ".cpmmodel");

        String var10003;
        for(Random r = new Random(); out.exists(); out = new File(models, var10003 + "_" + Integer.toHexString(r.nextInt()) + ".cpmmodel")) {
            var10003 = name.replaceAll("[^a-zA-Z0-9\\.\\-]", "");
        }

        FileOutputStream fout = new FileOutputStream(out);

        try {
            fout.write(ModelDefinitionLoader.HEADER);
            ChecksumOutputStream cos = new ChecksumOutputStream(fout);
            IOHelper h = new IOHelper(cos);
            h.writeUTF(name);
            h.writeUTF(desc != null ? desc : "");
            h.writeVarInt(data.length);
            h.write(data);
            h.writeVarInt(0);
            if (icon != null) {
                h.writeImage(icon);
            } else {
                h.writeVarInt(0);
            }

            cos.close();
        } catch (Throwable var12) {
            try {
                fout.close();
            } catch (Throwable var11) {
                var12.addSuppressed(var11);
            }

            throw var12;
        }

        fout.close();
    }
}
