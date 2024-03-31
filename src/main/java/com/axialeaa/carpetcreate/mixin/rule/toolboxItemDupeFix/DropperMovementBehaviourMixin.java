package com.axialeaa.carpetcreate.mixin.rule.toolboxItemDupeFix;

import com.axialeaa.carpetcreate.CarpetCreateSettings;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.simibubi.create.content.contraptions.behaviour.dispenser.DropperMovementBehaviour;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.function.Predicate;

@Mixin(DropperMovementBehaviour.class)
public class DropperMovementBehaviourMixin {

	@WrapOperation(method = "lambda$collectItems$1", at = @At(value = "INVOKE", target = "Lcom/simibubi/create/foundation/item/ItemHelper;sameItemPredicate(Lnet/minecraft/world/item/ItemStack;)Ljava/util/function/Predicate;"), remap = false)
	private static Predicate<ItemStack> test(ItemStack stack, Operation<Predicate<ItemStack>> original) {
		return CarpetCreateSettings.toolboxItemDupeFix ? (otherItemStack) -> ItemStack.isSameItemSameTags(stack, otherItemStack) : original.call(stack);
	}

}
