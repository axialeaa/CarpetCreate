package com.axialeaa.carpetcreate.mixin.rule.expandedHopperCounters;

import carpet.CarpetSettings;
import carpet.helpers.HopperCounter;
import carpet.utils.WoolTool;
import com.axialeaa.carpetcreate.CarpetCreateSettings;
import com.simibubi.create.content.logistics.chute.AbstractChuteBlock;
import com.simibubi.create.content.logistics.chute.ChuteBlockEntity;
import com.simibubi.create.content.logistics.chute.ChuteItemHandler;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.TransactionContext;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@SuppressWarnings("UnstableApiUsage")
@Mixin(ChuteItemHandler.class)
public abstract class ChuteItemHandlerMixin extends SingleVariantStorage<ItemVariant> {

	@Shadow private ChuteBlockEntity blockEntity;

	@Inject(method = "insert(Lnet/fabricmc/fabric/api/transfer/v1/item/ItemVariant;JLnet/fabricmc/fabric/api/transfer/v1/transaction/TransactionContext;)J", at = @At("HEAD"), cancellable = true, remap = false)
	private void countOnInsert(ItemVariant insertedVariant, long maxAmount, TransactionContext transaction, CallbackInfoReturnable<Long> cir) {
		if (CarpetSettings.hopperCounters && CarpetCreateSettings.expandedHopperCounters) {
			Level level = this.blockEntity.getLevel();
			BlockPos blockPos = this.blockEntity.getBlockPos();

			if (level != null) {
				BlockState blockState = level.getBlockState(blockPos);
				DyeColor dyeColor = WoolTool.getWoolColorAtPosition(level, blockPos.relative(Objects.requireNonNull(AbstractChuteBlock.getChuteFacing(blockState))));

				if (dyeColor != null) {
					ItemStack itemStack = insertedVariant.toStack();
					if (!itemStack.isEmpty()) {
						// updates the hopper counter, inserts the item into the inventory, and then removes it
						// i hate this and i will update it if i find a better way
						HopperCounter.getCounter(dyeColor).add(level.getServer(), itemStack);
						cir.setReturnValue(super.insert(insertedVariant, maxAmount, transaction));
						this.blockEntity.setItem(ItemStack.EMPTY);
					}
				}
			}
		}
	}

}
