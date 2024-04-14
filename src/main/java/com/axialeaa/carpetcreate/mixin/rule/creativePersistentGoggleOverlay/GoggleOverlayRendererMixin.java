package com.axialeaa.carpetcreate.mixin.rule.creativePersistentGoggleOverlay;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.axialeaa.carpetcreate.CarpetCreateSettings;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.simibubi.create.content.equipment.goggles.GoggleOverlayRenderer;

import net.minecraft.world.entity.player.Player;

@Environment(EnvType.CLIENT)
@Mixin(GoggleOverlayRenderer.class)
public class GoggleOverlayRendererMixin {

	@WrapOperation(method = "renderOverlay", at = @At(value = "INVOKE", target = "Lcom/simibubi/create/content/equipment/goggles/GogglesItem;isWearingGoggles(Lnet/minecraft/world/entity/player/Player;)Z"))
	private static boolean shouldRenderOverlay(Player predicate, Operation<Boolean> original) {
		return original.call(predicate) || (CarpetCreateSettings.creativePersistentGoggleOverlay && (predicate.isCreative() || predicate.isSpectator()));
	}

}
