package xyz.bluspring.cpmmodelexport.mixin;

import com.tom.cpm.shared.model.Cube;
import com.tom.cpm.shared.model.RenderedCube;
import com.tom.cpm.shared.parts.IModelPart;
import com.tom.cpm.shared.parts.IResolvedModelPart;
import com.tom.cpm.shared.parts.ModelPartDefinition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(ModelPartDefinition.class)
public interface ModelPartDefinitionAccessor {
    @Accessor
    List<Cube> getCubes();

    @Accessor
    List<RenderedCube> getRc();

    @Accessor
    List<IModelPart> getOtherParts();

    @Accessor
    List<IResolvedModelPart> getResolvedOtherParts();
}
