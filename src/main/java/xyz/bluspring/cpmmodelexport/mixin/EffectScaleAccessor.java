package xyz.bluspring.cpmmodelexport.mixin;

import com.tom.cpl.math.Vec3f;
import com.tom.cpm.shared.effects.EffectScale;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EffectScale.class)
public interface EffectScaleAccessor {
    @Accessor
    int getId();

    @Accessor
    Vec3f getScale();

    @Accessor
    float getMcScale();
}
