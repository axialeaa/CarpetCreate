package com.axialeaa.carpetcreate.mixin.rule.fluidCounters;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.axialeaa.carpetcreate.CarpetCreateSettings;
import com.axialeaa.carpetcreate.helpers.FluidCounter;
import com.llamalad7.mixinextras.sugar.Local;
import com.simibubi.create.content.fluids.FluidTransportBehaviour;
import com.simibubi.create.content.fluids.PipeConnection;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;

import carpet.utils.WoolTool;
import io.github.fabricators_of_create.porting_lib.fluids.FluidStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;

@Mixin(FluidTransportBehaviour.class)
public abstract class FluidTransportBehaviourMixin extends BlockEntityBehaviour {

	@Shadow public Map<Direction, PipeConnection> interfaces;

	public FluidTransportBehaviourMixin(SmartBlockEntity be) {
		super(be);
	}

	@Inject(method = "wipePressure", at = @At(value = "INVOKE", target = "Ljava/util/Map;remove(Ljava/lang/Object;)Ljava/lang/Object;", shift = At.Shift.BEFORE), remap = false)
	private void test(CallbackInfo ci, @Local Direction direction) {
		Level level = this.blockEntity.getLevel();
		BlockPos blockPos = this.blockEntity.getBlockPos();

		if (CarpetCreateSettings.expandedHopperCounters && level != null) {
			DyeColor woolColor = WoolTool.getWoolColorAtPosition(level, blockPos.relative(direction));
			if (woolColor != null) {
				FluidStack fluidStack = this.interfaces.get(direction.getOpposite()).getProvidedFluid();
				if (!fluidStack.isEmpty())
					FluidCounter.getCounter(woolColor).add(level.getServer(), fluidStack);
			}
		}
	}

}
