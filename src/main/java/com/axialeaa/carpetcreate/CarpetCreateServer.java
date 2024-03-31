package com.axialeaa.carpetcreate;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.utils.Translations;
import com.axialeaa.carpetcreate.command.FluidCounterCommand;
import com.mojang.brigadier.CommandDispatcher;
import com.simibubi.create.Create;
import io.github.fabricators_of_create.porting_lib.util.EnvExecutor;
import net.fabricmc.api.ModInitializer;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CarpetCreateServer implements ModInitializer, CarpetExtension {
	public static final String MOD_ID = "carpet-create";
	public static final String MOD_NAME = "CarpetCreate";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	@Override
	public void onInitialize() {
		CarpetServer.manageExtension(new CarpetCreateServer());

		LOGGER.info("Create addon mod [{}] is loading alongside Create [{}]!", MOD_NAME, Create.VERSION);
		LOGGER.info(EnvExecutor.unsafeRunForDist(
				() -> () -> "{} is accessing Porting Lib from the client!",
				() -> () -> "{} is accessing Porting Lib from the server!"
		), MOD_NAME);
	}

	@Override
	public void onGameStarted() {
		CarpetServer.settingsManager.parseSettingsClass(CarpetCreateSettings.class);
	}

	@Override
	public void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext) {
		FluidCounterCommand.register(dispatcher);
	}

	@Override
	public Map<String, String> canHasTranslations(String lang) {
		return Translations.getTranslationFromResourcePath("assets/" + MOD_ID + "/lang/%s.json".formatted(lang));
	}

}
