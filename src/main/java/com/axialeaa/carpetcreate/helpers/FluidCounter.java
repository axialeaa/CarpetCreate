package com.axialeaa.carpetcreate.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import carpet.utils.Messenger;
import io.github.fabricators_of_create.porting_lib.fluids.FluidStack;
import it.unimi.dsi.fastutil.objects.Object2LongLinkedOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2LongMap;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class FluidCounter {

	/**
	 * A map of all the {@link FluidCounter} counters.
	 */
	private static final Map<DyeColor, FluidCounter> COUNTERS;

	static {
		EnumMap<DyeColor, FluidCounter> counterMap = new EnumMap<>(DyeColor.class);
		for (DyeColor color : DyeColor.values())
			counterMap.put(color, new FluidCounter(color));

		COUNTERS = Collections.unmodifiableMap(counterMap);
	}

	public final DyeColor color;
	private final String coloredName;
	private final Object2LongMap<Fluid> counter = new Object2LongLinkedOpenHashMap<>();
	private long startTick;
	private long startMillis;

	private FluidCounter(DyeColor color) {
		startTick = -1;
		this.color = color;
		String hexColor = Integer.toHexString(color.getTextColor());
		if (hexColor.length() < 6)
			hexColor = "0".repeat(6 - hexColor.length()) + hexColor;
		this.coloredName = '#' + hexColor + ' ' + color.getName();
	}

	public void add(MinecraftServer server, FluidStack stack) {
		if (startTick < 0) {
			startTick = server.overworld().getGameTime();
			startMillis = System.currentTimeMillis();
		}
		Fluid fluid = stack.getFluid();
		counter.put(fluid, counter.getLong(fluid) + stack.getAmount());
	}

	public void reset(MinecraftServer server) {
		counter.clear();
		startTick = server.overworld().getGameTime();
		startMillis = System.currentTimeMillis();
	}

	public static void resetAll(MinecraftServer server, boolean fresh) {
		for (FluidCounter counter : COUNTERS.values()) {
			counter.reset(server);
			if (fresh)
				counter.startTick = -1;
		}
	}

	public static List<Component> formatAll(MinecraftServer server, boolean realtime) {
		List<Component> text = new ArrayList<>();

		for (FluidCounter counter : COUNTERS.values()) {
			List<Component> temp = counter.format(server, realtime, false);
			if (temp.size() > 1) {
				if (!text.isEmpty())
					text.add(Messenger.s(""));

				text.addAll(temp);
			}
		}

		if (text.isEmpty())
			text.add(Messenger.s("No fluids have been counted yet."));

		return text;
	}

	public List<Component> format(MinecraftServer server, boolean realTime, boolean brief) {
		long ticks = Math.max(realTime ? (System.currentTimeMillis() - startMillis) / 50 : server.overworld().getGameTime() - startTick, 1);
		double seconds = ticks / 20.0;
		double minutes = seconds / 60.0;

		if (startTick < 0) {
			if (brief)
				// white: -, -/h, - min
				return Collections.singletonList(Messenger.c("b" + coloredName, "w : ", "gi -, -/h, - min "));
			else
				// white hasn't started counting yet
				return Collections.singletonList(Messenger.c(coloredName, "w  hasn't started counting yet"));
		}

		long total = getTotalFluids();

		if (total == 0)
			if (brief)
				// white: 0, 0/h, 30.0 mins
				return Collections.singletonList(Messenger.c("b" + coloredName, "w : ", "wb 0", "w , ", "wb 0", "w /h, ", String.format("wb %.1f ", minutes), "w min"));
			else
				// No fluids for white yet (30.0 min. - real time) [X]
				return Collections.singletonList(Messenger.c("w No fluids for ", coloredName, String.format("w  yet (%.2f min.%s)", minutes, (realTime ? " - real time" : "")), "nb  [X]", "^g reset", "!/fluidcounter " + color.getName() +" reset"));

		if (brief)
			// white: 200, 400/h, 30.0 mins
			return Collections.singletonList(Messenger.c("b" + coloredName,"w : ", "wb " + total, "w , ", "wb " + (total * (20 * 60 * 60) / ticks),"w /h, ", String.format("wb %.1f ", minutes), "w min"));

		List<Component> fluids = new ArrayList<>();
		fluids.add(Messenger.c("w Fluids for ", coloredName,
			"w  (", String.format("wb %.2f", ticks * 1.0 / (20 * 60)), "w  min" + (realTime ? " - real time" : "") + "), ",
			"w total: ", "wb " + total, "w , (",String.format("wb %.1f", total * 1.0 * (20 * 60 * 60) / ticks), "w /h):",
			"nb [X]", "^g reset", "!/fluidcounter " + color + " reset"
		));
		fluids.addAll(counter.object2LongEntrySet().stream().sorted((fluidEntry, fluidEntry1) -> Long.compare(fluidEntry1.getLongValue(), fluidEntry.getLongValue())).map(e -> {
			Fluid fluid = e.getKey();
			Block block = fluid.defaultFluidState().createLegacyBlock().getBlock();

			MutableComponent fluidName = Component.translatable(block.getDescriptionId());
			Style itemStyle = fluidName.getStyle();

			TextColor color = TextColor.fromRgb(block.defaultMapColor().col);
			fluidName.setStyle(itemStyle.withColor(color));
			long count = e.getLongValue();

			return Messenger.c("g - ", fluidName, "g : ", "wb " + count,"g , ", String.format("wb %.1f", count * (20.0 * 60.0 * 60.0) / ticks), "w /h");
		}).toList());
		return fluids;
	}

	public static FluidCounter getCounter(DyeColor color) {
		return COUNTERS.get(color);
	}

	public long getTotalFluids() {
		return counter.isEmpty() ? 0 : counter.values().longStream().sum();
	}

}
