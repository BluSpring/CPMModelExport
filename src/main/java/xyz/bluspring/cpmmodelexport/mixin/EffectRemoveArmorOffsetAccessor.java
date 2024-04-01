package xyz.bluspring.cpmmodelexport.mixin;

import com.tom.cpm.shared.effects.EffectRemoveArmorOffset;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EffectRemoveArmorOffset.class)
public interface EffectRemoveArmorOffsetAccessor {
    @Accessor
    boolean isRemove();
}
