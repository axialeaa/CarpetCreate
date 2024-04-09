package com.axialeaa.carpetcreate.mixin.rule.extractAmount;

import com.axialeaa.carpetcreate.CarpetCreateSettings;
import com.simibubi.create.content.logistics.funnel.FunnelBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(FunnelBlockEntity.class)
public class FunnelBlockEntityMixin {

	@ModifyConstant(method = "getAmountToExtract", constant = @Constant(intValue = 1), remap = false)
	private int modifyFunnelAmount(int constant) {
		return CarpetCreateSettings.andesiteFunnelExtractAmount;
	}

}
