package com.axialeaa.carpetcreate.mixin.rule.fluidCounters;

import com.simibubi.create.content.fluids.FluidTransportBehaviour;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FluidTransportBehaviour.class)
public abstract class FluidTransportBehaviourMixin extends BlockEntityBehaviour {

//	@Shadow public Map<Direction, PipeConnection> interfaces;

	public FluidTransportBehaviourMixin(SmartBlockEntity be) {
		super(be);
	}

//	@Inject(method = "wipePressure", at = @At(value = "INVOKE", target = "Ljava/util/Map;remove(Ljava/lang/Object;)Ljava/lang/Object;", shift = At.Shift.BEFORE), remap = false)
//	private void test(CallbackInfo ci, @Local Direction direction) {
//		Level level = this.blockEntity.getLevel();
//		BlockPos blockPos = this.blockEntity.getBlockPos();
//
//		if (CarpetCreateSettings.expandedHopperCounters && level != null) {
//			DyeColor woolColor = WoolTool.getWoolColorAtPosition(level, blockPos.relative(direction));
//			if (woolColor != null) {
//				FluidStack fluidStack = this.interfaces.get(direction.getOpposite()).getProvidedFluid();
//				if (!fluidStack.isEmpty())
//					FluidCounter.getCounter(woolColor).add(level.getServer(), fluidStack);
//			}
//		}
//	}

}
