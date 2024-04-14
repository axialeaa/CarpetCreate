package com.axialeaa.carpetcreate.mixin.rule.tickSyncedCreateAnimations;

import com.axialeaa.carpetcreate.helpers.TickSyncHelper;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.simibubi.create.content.contraptions.render.ContraptionRenderDispatcher;
import com.simibubi.create.foundation.blockEntity.behaviour.scrollValue.ScrollValueHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Environment(EnvType.CLIENT)
@Mixin({
	ContraptionRenderDispatcher.class,
	ScrollValueHandler.class
})
public class _TickFreezeMultiMixin {

	@WrapOperation(method = "tick*", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;isPaused()Z"))
	private static boolean shouldPause(Minecraft instance, Operation<Boolean> original) {
		return TickSyncHelper.isGamePaused();
	}

}
