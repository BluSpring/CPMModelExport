package xyz.bluspring.cpmmodelexport.mixin;

import com.tom.cpm.shared.effects.EffectGlow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EffectGlow.class)
public interface EffectGlowAccessor {
    @Accessor
    int getId();
}
