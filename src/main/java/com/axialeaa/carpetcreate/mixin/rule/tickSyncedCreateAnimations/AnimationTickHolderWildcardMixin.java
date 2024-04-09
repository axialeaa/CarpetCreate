package com.axialeaa.carpetcreate.mixin.rule.tickSyncedCreateAnimations;

import com.axialeaa.carpetcreate.helpers.TickSyncHelper;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

/**
 * This mixin is named as such because it targets two classes both named AnimationTickHolder; one from Flywheel and another from Create.
 */
@Environment(EnvType.CLIENT)
@Mixin({
	com.jozufozu.flywheel.util.AnimationTickHolder.class,
	com.simibubi.create.foundation.utility.AnimationTickHolder.class
})
public class AnimationTickHolderWildcardMixin {

	@WrapOperation(method = { "tick", "getPartialTicks()F" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;isPaused()Z"), remap = false)
	private static boolean shouldPause(Minecraft instance, Operation<Boolean> original) {
		return TickSyncHelper.isGamePaused();
	}

	@ModifyReturnValue(method = "getRenderTime*", at = @At("RETURN"), remap = false)
	private static float scaleRenderTime(float original) {
		return TickSyncHelper.scaleByTickRateFactor(original);
	}

}
