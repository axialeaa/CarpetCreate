package com.axialeaa.carpetcreate.mixin.rule.expandedHopperCounters;

import com.simibubi.create.content.kinetics.crafter.RecipeGridHandler;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(RecipeGridHandler.GroupedItems.class)
public interface GroupedItemsAccessor {

	@Accessor(value = "grid", remap = false)
	Map<Pair<Integer, Integer>, ItemStack> getGrid();

}
