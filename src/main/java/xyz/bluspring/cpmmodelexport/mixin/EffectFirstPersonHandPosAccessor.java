package xyz.bluspring.cpmmodelexport.mixin;

import com.tom.cpm.shared.effects.EffectFirstPersonHandPos;
import com.tom.cpm.shared.model.PartPosition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EffectFirstPersonHandPos.class)
public interface EffectFirstPersonHandPosAccessor {
    @Accessor
    PartPosition getLeftHand();

    @Accessor
    PartPosition getRightHand();
}
