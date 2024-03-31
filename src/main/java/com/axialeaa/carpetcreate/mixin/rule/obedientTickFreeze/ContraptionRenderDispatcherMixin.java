package com.axialeaa.carpetcreate.mixin.rule.obedientTickFreeze;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.axialeaa.carpetcreate.CarpetCreateSettings;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.simibubi.create.content.contraptions.render.ContraptionRenderDispatcher;

import carpet.fakes.LevelInterface;
import carpet.helpers.TickRateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;

@Mixin(ContraptionRenderDispatcher.class)
public class ContraptionRenderDispatcherMixin {

	@WrapOperation(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;isPaused()Z"))
	private static boolean shouldPause(Minecraft instance, Operation<Boolean> original) {
		ClientLevel level = instance.level;
		if (level != null && CarpetCreateSettings.obedientTickFreeze) {
			TickRateManager tickRateManager = ((LevelInterface) level).tickRateManager();
			return !tickRateManager.runsNormally();
		}
		return original.call(instance);
	}

}
