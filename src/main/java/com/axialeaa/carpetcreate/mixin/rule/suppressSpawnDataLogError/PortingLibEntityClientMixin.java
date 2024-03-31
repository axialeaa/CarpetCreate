package com.axialeaa.carpetcreate.mixin.rule.suppressSpawnDataLogError;

import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.axialeaa.carpetcreate.CarpetCreateSettings;
import com.llamalad7.mixinextras.injector.WrapWithCondition;

import io.github.fabricators_of_create.porting_lib.entity.client.PortingLibEntityClient;

@Mixin(PortingLibEntityClient.class)
public class PortingLibEntityClientMixin {

	@WrapWithCondition(method = "lambda$handlePacketReceived$0", at = @At(value = "INVOKE", target = "Lorg/slf4j/Logger;error(Ljava/lang/String;Ljava/lang/Object;)V"))
	private static boolean shouldSendLoggerMessage(Logger instance, String string, Object o) {
		return !CarpetCreateSettings.suppressSpawnDataLogError;
	}

}
