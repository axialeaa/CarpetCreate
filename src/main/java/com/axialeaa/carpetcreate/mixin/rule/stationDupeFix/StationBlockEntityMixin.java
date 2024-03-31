package com.axialeaa.carpetcreate.mixin.rule.stationDupeFix;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.axialeaa.carpetcreate.CarpetCreateSettings;
import com.simibubi.create.content.trains.station.StationBlock;
import com.simibubi.create.content.trains.station.StationBlockEntity;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(StationBlockEntity.class)
public abstract class StationBlockEntityMixin extends SmartBlockEntity {

	public StationBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}

	@Inject(method = "enterAssemblyMode", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;setValue(Lnet/minecraft/world/level/block/state/properties/Property;Ljava/lang/Comparable;)Ljava/lang/Object;", shift = At.Shift.BEFORE), cancellable = true)
	private void checkStationBeforeAssembly(ServerPlayer sender, CallbackInfoReturnable<Boolean> cir) {
		if (level != null && CarpetCreateSettings.stationDupeFix && !(level.getBlockState(worldPosition).getBlock() instanceof StationBlock))
			cir.setReturnValue(true);
	}

}
