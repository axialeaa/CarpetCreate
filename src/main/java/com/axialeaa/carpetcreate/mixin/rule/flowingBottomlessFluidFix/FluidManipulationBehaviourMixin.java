package com.axialeaa.carpetcreate.mixin.rule.flowingBottomlessFluidFix;

import com.axialeaa.carpetcreate.CarpetCreateSettings;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import com.simibubi.create.content.fluids.transfer.FluidManipulationBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Set;

@Mixin(FluidManipulationBehaviour.class)
public class FluidManipulationBehaviourMixin {

	@WrapWithCondition(method = "search", at = @At(value = "INVOKE", target = "Ljava/util/Set;add(Ljava/lang/Object;)Z"))
	private boolean shouldCountTowardsBody(Set<BlockPos> instance, Object e, @Local Level world, @Local BlockPos currentPos) {
		return !CarpetCreateSettings.flowingBottomlessFluidFix || world.getFluidState(currentPos).isSource();
	}

}
