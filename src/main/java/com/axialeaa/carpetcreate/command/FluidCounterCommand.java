package com.axialeaa.carpetcreate.command;

import static net.minecraft.commands.Commands.literal;

import com.axialeaa.carpetcreate.CarpetCreateSettings;
import com.axialeaa.carpetcreate.helpers.FluidCounter;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import carpet.utils.Messenger;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.DyeColor;

/**
 * Class for the /counter command which allows to use hoppers pointing into wool
 */
public class FluidCounterCommand {

	public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		LiteralArgumentBuilder<CommandSourceStack> commandBuilder = literal("fluidcounter")
			.requires(ctx -> CarpetCreateSettings.expandedHopperCounters)
			.executes(ctx -> listAllCounters(ctx.getSource(), false))
			.then(literal("reset")
				.executes(c -> resetCounters(c.getSource())));

		for (DyeColor dyeColor : DyeColor.values())
			commandBuilder.then(literal(dyeColor.toString())
				.executes(ctx -> displayCounter(ctx.getSource(), dyeColor, false))
				.then(literal("reset")
					.executes(ctx -> resetCounter(ctx.getSource(), dyeColor)))
				.then(literal("realtime")
					.executes(ctx -> displayCounter(ctx.getSource(), dyeColor, true)))
			);

		dispatcher.register(commandBuilder);
	}

	private static int displayCounter(CommandSourceStack source, DyeColor color, boolean realtime) {
		FluidCounter fluidCounter = FluidCounter.getCounter(color);

		for (Component message : fluidCounter.format(source.getServer(), realtime, false))
			source.sendSuccess(() -> message, false);

		return Command.SINGLE_SUCCESS;
	}

	private static int resetCounters(CommandSourceStack source) {
		FluidCounter.resetAll(source.getServer(), false);
		Messenger.m(source, "w Restarted all counters");

		return Command.SINGLE_SUCCESS;
	}

	private static int resetCounter(CommandSourceStack source, DyeColor color) {
		FluidCounter.getCounter(color).reset(source.getServer());
		Messenger.m(source, "w Restarted " + color + " counter");

		return Command.SINGLE_SUCCESS;
	}

	private static int listAllCounters(CommandSourceStack source, boolean realtime) {
		for (Component message: FluidCounter.formatAll(source.getServer(), realtime))
			source.sendSuccess(() -> message, false);

		return Command.SINGLE_SUCCESS;
	}
}
