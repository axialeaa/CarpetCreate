package com.axialeaa.carpetcreate.mixin.rule.expandedHopperCounters;

import carpet.CarpetSettings;
import carpet.helpers.HopperCounter;
import carpet.utils.WoolTool;
import com.axialeaa.carpetcreate.CarpetCreateSettings;
import com.simibubi.create.content.kinetics.crafter.MechanicalCrafterBlockEntity;
import com.simibubi.create.content.kinetics.crafter.RecipeGridHandler;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mixin(MechanicalCrafterBlockEntity.class)
public abstract class MechanicalCrafterBlockEntityMixin extends SmartBlockEntity {

	@Shadow(remap = false) public abstract Direction getTargetDirection();
	@Shadow(remap = false) protected RecipeGridHandler.GroupedItems groupedItems;

	public MechanicalCrafterBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}

	@Inject(method = "tryInsert", at = @At("HEAD"), remap = false)
	private void countOnInsert(CallbackInfo ci) {
		if (CarpetSettings.hopperCounters && CarpetCreateSettings.expandedHopperCounters) {
			Level level = this.getLevel();
			if (level != null) {
				List<ItemStack> list = new ArrayList<>();

				for (Map.Entry<Pair<Integer, Integer>, ItemStack> entry : ((GroupedItemsAccessor) this.groupedItems).getGrid().entrySet()) {
					ItemStack stack = entry.getValue();
					if (stack.isEmpty())
						continue;

					list.add(stack);
				}

				BlockPos blockPos = this.getBlockPos();
				DyeColor dyeColor = WoolTool.getWoolColorAtPosition(level, blockPos.relative(this.getTargetDirection()));

				if (dyeColor != null) {
					for (ItemStack itemStack : list) {
						HopperCounter.getCounter(dyeColor).add(level.getServer(), itemStack);
						itemStack.setCount(0);
					}
				}
			}
		}
	}

}
