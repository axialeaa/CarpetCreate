package com.axialeaa.carpetcreate.mixin.rule.renewableBlazeCakes;

import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.axialeaa.carpetcreate.CarpetCreateSettings;
import com.simibubi.create.content.fluids.pipes.VanillaFluidTargets;

import io.github.fabricators_of_create.porting_lib.fluids.FluidStack;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

@Mixin(VanillaFluidTargets.class)
public class VanillaFluidTargetsMixin {

	@Inject(method = "shouldPipesConnectTo", at = @At("HEAD"), cancellable = true, remap = false)
	private static void shouldConnectToMagma(BlockState state, CallbackInfoReturnable<Boolean> cir) {
		if (CarpetCreateSettings.renewableBlazeCakes && (state.is(Blocks.MAGMA_BLOCK) || state.is(Blocks.NETHERRACK)))
			cir.setReturnValue(true);
	}

	@SuppressWarnings("UnstableApiUsage")
	@Inject(method = "drainBlock", at = @At("HEAD"), cancellable = true, remap = false)
	private static void drainMagma(Level level, BlockPos pos, BlockState state, TransactionContext ctx, CallbackInfoReturnable<FluidStack> cir) {
		if (CarpetCreateSettings.renewableBlazeCakes && state.getBlock() == Blocks.MAGMA_BLOCK) {
			level.updateSnapshots(ctx);
			level.setBlock(pos, Blocks.NETHERRACK.defaultBlockState(), Block.UPDATE_ALL);
			cir.setReturnValue(new FluidStack(Fluids.LAVA, FluidConstants.BUCKET));
		}
	}

}
