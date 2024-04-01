package xyz.bluspring.cpmmodelexport.mixin;

import com.tom.cpm.shared.definition.ModelDefinition;
import com.tom.cpm.shared.parts.ModelPartLink;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ModelPartLink.class)
public interface ModelPartLinkAccessor {
    @Accessor
    ModelDefinition getDef();

    @Accessor
    void setDef(ModelDefinition def);
}
