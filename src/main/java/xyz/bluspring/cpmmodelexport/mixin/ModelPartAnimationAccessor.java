package xyz.bluspring.cpmmodelexport.mixin;

import com.tom.cpm.shared.parts.ModelPartAnimation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(ModelPartAnimation.class)
public interface ModelPartAnimationAccessor {
    @Accessor
    Map<Integer, Object> getParsedData();

    @Accessor
    int getBlankId();

    @Accessor
    int getResetId();

    @Accessor
    String getModelProfilesId();
}
