package com.axialeaa.carpetcreate.mixin.rule.extractAmount;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.axialeaa.carpetcreate.CarpetCreateSettings;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.simibubi.create.content.logistics.chute.ChuteBlockEntity;

@Mixin(ChuteBlockEntity.class)
public class ChuteBlockEntityMixin {

	@ModifyReturnValue(method = "getExtractionAmount", at = @At("RETURN"), remap = false)
	private int modifyExtractionAmount(int original) {
		return CarpetCreateSettings.chuteExtractAmount;
	}

}
