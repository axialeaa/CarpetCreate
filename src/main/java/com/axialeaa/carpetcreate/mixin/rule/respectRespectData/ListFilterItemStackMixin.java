package com.axialeaa.carpetcreate.mixin.rule.respectRespectData;

import java.util.Objects;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.axialeaa.carpetcreate.CarpetCreateSettings;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.simibubi.create.content.logistics.filter.FilterItemStack;

import net.minecraft.world.item.ItemStack;

@Mixin(FilterItemStack.ListFilterItemStack.class)
public class ListFilterItemStackMixin {

	@WrapOperation(method = "<init>", at = @At(value = "FIELD", target = "Lcom/simibubi/create/content/logistics/filter/FilterItemStack$ListFilterItemStack;shouldRespectNBT:Z"))
	private void invertDefaultsCheck(FilterItemStack.ListFilterItemStack instance, boolean value, Operation<Void> original, @Local(argsOnly = true) ItemStack filter, @Local boolean defaults) {
		if (CarpetCreateSettings.respectRespectData)
			instance.shouldRespectNBT = !defaults && Objects.requireNonNull(filter.getTag()).getBoolean("RespectNBT");
		else original.call(instance, value);
	}

}
