package com.axialeaa.carpetcreate.mixin.rule.fluidCounters;

import org.spongepowered.asm.mixin.Mixin;

@Mixin(targets = "com.simibubi.create.content.fluids.pipes.FluidPipeBlockEntity.StandardPipeFluidTransportBehaviour", remap = false)
public class FluidPipeBlockEntityMixin {

//	@Shadow @Final FluidPipeBlockEntity this$0;
//
//	@ModifyReturnValue(method = "canHaveFlowToward", at = @At("RETURN"), remap = false)
//	private boolean test(boolean original, BlockState state, Direction direction) {
//		boolean bl = false;
//		Level level = this.this$0.getLevel();
//		BlockPos blockPos = this.this$0.getBlockPos();
//
//		if (CarpetCreateSettings.fluidCounters && level != null) {
//			DyeColor woolColor = WoolTool.getWoolColorAtPosition(level, blockPos.relative(direction));
//			if (woolColor != null)
//				bl = true;
//		}
//
//		return original || bl;
//	}

}
