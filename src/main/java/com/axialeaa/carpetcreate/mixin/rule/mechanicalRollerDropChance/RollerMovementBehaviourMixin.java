package com.axialeaa.carpetcreate.mixin.rule.mechanicalRollerDropChance;

import com.axialeaa.carpetcreate.CarpetCreateSettings;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.simibubi.create.content.contraptions.actors.roller.RollerMovementBehaviour;
import net.minecraft.util.RandomSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(RollerMovementBehaviour.class)
public class RollerMovementBehaviourMixin {

    @WrapOperation(method = "lambda$destroyBlock$0", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/RandomSource;nextBoolean()Z"))
    private boolean shouldFailBlockDrops(RandomSource instance, Operation<Boolean> original) {
        double dropChance = CarpetCreateSettings.mechanicalRollerDropChance;
        return dropChance <= 0 || instance.nextFloat() > dropChance;
    }

}
