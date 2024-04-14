package com.axialeaa.carpetcreate.mixin.rule.ploughLayerBlockDropFix;

import com.axialeaa.carpetcreate.CarpetCreateSettings;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.simibubi.create.content.contraptions.actors.plough.PloughMovementBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SnowLayerBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PloughMovementBehaviour.class)
public class PloughMovementBehaviourMixin {

    @ModifyExpressionValue(method = "onBlockBroken", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;getBlock()Lnet/minecraft/world/level/block/Block;"))
    private Block modifyBlockCheck(Block original) {
        return CarpetCreateSettings.ploughLayerBlockDropFix && original instanceof SnowLayerBlock ? Blocks.SNOW : original;
    }

}