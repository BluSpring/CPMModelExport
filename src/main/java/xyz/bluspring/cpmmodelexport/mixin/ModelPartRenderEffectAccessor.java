package xyz.bluspring.cpmmodelexport.mixin;

import com.tom.cpm.shared.effects.IRenderEffect;
import com.tom.cpm.shared.parts.ModelPartRenderEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ModelPartRenderEffect.class)
public interface ModelPartRenderEffectAccessor {
    @Accessor
    IRenderEffect getEffect();
}
