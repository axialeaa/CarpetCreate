package com.axialeaa.carpetcreate.mixin.rule.expandedHopperCounters;

import java.util.Objects;

import javax.annotation.Nullable;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.axialeaa.carpetcreate.CarpetCreateSettings;
import com.simibubi.create.content.logistics.funnel.AbstractFunnelBlock;
import com.simibubi.create.content.logistics.funnel.FunnelBlockEntity;

import carpet.CarpetSettings;
import carpet.helpers.HopperCounter;
import carpet.utils.WoolTool;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(AbstractFunnelBlock.class)
public abstract class AbstractFunnelBlockMixin {

	@Shadow @Nullable public static Direction getFunnelFacing(BlockState state) {
		return null;
	}

	@Inject(method = "tryInsert", at = @At("HEAD"), cancellable = true, remap = false)
	private static void countOnInsert(Level worldIn, BlockPos pos, ItemStack toInsert, boolean simulate, CallbackInfoReturnable<ItemStack> cir) {
		if (CarpetSettings.hopperCounters && CarpetCreateSettings.expandedHopperCounters) {
			BlockState blockState = worldIn.getBlockState(pos);
			DyeColor dyeColor = WoolTool.getWoolColorAtPosition(worldIn, pos.relative(Objects.requireNonNull(getFunnelFacing(blockState)).getOpposite()));

			if (dyeColor != null && !toInsert.isEmpty()) {
                HopperCounter.getCounter(dyeColor).add(worldIn.getServer(), toInsert);
				cir.setReturnValue(toInsert);
				toInsert.setCount(0);

				if (worldIn.getBlockEntity(pos) instanceof FunnelBlockEntity funnelBlockEntity && funnelBlockEntity.hasFlap())
					funnelBlockEntity.flap(true);
            }
		}
	}

}
