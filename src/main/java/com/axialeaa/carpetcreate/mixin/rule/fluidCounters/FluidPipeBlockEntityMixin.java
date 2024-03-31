package com.axialeaa.carpetcreate.mixin.rule.fluidCounters;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import com.axialeaa.carpetcreate.CarpetCreateSettings;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.simibubi.create.content.fluids.pipes.FluidPipeBlockEntity;

import carpet.utils.WoolTool;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(targets = "com.simibubi.create.content.fluids.pipes.FluidPipeBlockEntity.StandardPipeFluidTransportBehaviour")
public class FluidPipeBlockEntityMixin {

	@Shadow @Final FluidPipeBlockEntity this$0;

	@ModifyReturnValue(method = "canHaveFlowToward", at = @At("RETURN"))
	private boolean test(boolean original, BlockState state, Direction direction) {
		boolean bl = false;
		Level level = this.this$0.getLevel();
		BlockPos blockPos = this.this$0.getBlockPos();

		if (CarpetCreateSettings.fluidCounters && level != null) {
			DyeColor woolColor = WoolTool.getWoolColorAtPosition(level, blockPos.relative(direction));
			if (woolColor != null)
				bl = true;
		}

		return original || bl;
	}

}
