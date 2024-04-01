package xyz.bluspring.cpmmodelexport.mixin;

import com.tom.cpm.shared.parts.ModelPartPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ModelPartPlayer.class)
public interface ModelPartPlayerAccessor {
    @Accessor
    boolean[] getKeep();
}
