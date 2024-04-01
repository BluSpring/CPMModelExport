package xyz.bluspring.cpmmodelexport.mixin;

import com.tom.cpm.shared.effects.EffectHideSkull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EffectHideSkull.class)
public interface EffectHideSkullAccessor {
    @Accessor
    boolean isHide();
}
