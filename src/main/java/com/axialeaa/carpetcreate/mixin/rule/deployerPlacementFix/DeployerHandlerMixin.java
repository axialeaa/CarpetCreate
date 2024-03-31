package com.axialeaa.carpetcreate.mixin.rule.deployerPlacementFix;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.axialeaa.carpetcreate.CarpetCreateSettings;
import com.simibubi.create.content.kinetics.deployer.DeployerHandler;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

@Mixin(DeployerHandler.class)
public class DeployerHandlerMixin {

	@Inject(method = "shouldActivate", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;getBlock()Lnet/minecraft/world/level/block/Block;", shift = At.Shift.BEFORE), cancellable = true, remap = false)
	private static void test(ItemStack held, Level world, BlockPos targetPos, Direction facing, CallbackInfoReturnable<Boolean> cir) {
		if (CarpetCreateSettings.deployerPlacementFix && !world.getBlockState(targetPos).canBeReplaced())
			cir.setReturnValue(false);
	}

}
