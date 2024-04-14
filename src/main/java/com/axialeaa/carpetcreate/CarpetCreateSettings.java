package com.axialeaa.carpetcreate;

import carpet.api.settings.CarpetRule;
import carpet.api.settings.Rule;
import carpet.api.settings.Validator;
import carpet.api.settings.Validators;
import net.minecraft.commands.CommandSourceStack;

import static carpet.api.settings.RuleCategory.*;

public class CarpetCreateSettings {

	/**<h1>CATEGORIES</h1>*/

	public static final String CREATE = "create";
	public static final String COMPAT = "compat";
	public static final String PORTING_LIB = "porting_lib";
	public static final String FLYWHEEL = "flywheel";

	/**<h1>VALIDATORS</h1>*/

	private static class ExtractAmountValidator extends Validator<Integer> {

		@Override
		public Integer validate(CommandSourceStack source, CarpetRule<Integer> currentRule, Integer newValue, String typedString) {
			return (newValue >= 1 && newValue <= 64) ? newValue : null;
		}

		@Override
		public String description() {
			return "You must choose a value from 1 to 64";
		}

	}

	/**<h1>RULES</h1>*/

	@Rule( options = { "1", "16", "64" }, strict = false, validators = ExtractAmountValidator.class, categories = { FEATURE, CREATE } )
	public static int andesiteFunnelExtractAmount = 1;

	@Rule( options = { "16", "64" }, strict = false, validators = ExtractAmountValidator.class, categories = { FEATURE, CREATE } )
	public static int chuteExtractAmount = 16;

	@Rule( categories = { CREATIVE, CREATE, CLIENT } )
	public static boolean creativePersistentGoggleOverlay = false;

	@Rule( categories = { BUGFIX, CREATE } )
	public static boolean deployerPlacementFix = false;

//	@Rule( categories = { BUGFIX, CREATE } )
//	public static boolean entityContraptionRotationFix = false;

	@Rule( categories = { CREATIVE, COMPAT, CREATE } )
	public static boolean expandedHopperCounters = false;

	@Rule( categories = { BUGFIX, CREATE } )
	public static boolean flowingBottomlessFluidFix = false;

//	@Rule( categories = { COMMAND, CREATIVE, FEATURE, CREATE } )
//	public static boolean fluidCounters = false;

	@Rule( options = { "0.0", "0.5", "1.0" }, strict = false, validators = Validators.Probablity.class, categories = { CREATIVE, SURVIVAL, CREATE } )
	public static double mechanicalRollerDropChance = 0.5;

	@Rule( categories = { BUGFIX, COMPAT, CREATE } )
	public static boolean ploughLayerBlockDropFix = false;

	@Rule( categories = { FEATURE, CREATE } )
	public static boolean renewableBlazeCakes = false;

	@Rule( categories = { BUGFIX, CREATE } )
	public static boolean respectRespectData = false;

	@Rule( categories = { BUGFIX, CREATE } )
	public static boolean stationDupeFix = false;

	@Rule( categories = { CREATE, PORTING_LIB, CLIENT } )
	public static boolean suppressSpawnDataLogError = false;

	@Rule( categories = { CREATE, EXPERIMENTAL, COMPAT, FLYWHEEL, CLIENT } )
	public static boolean tickSyncedCreateAnimations = false;

	@Rule( categories = { BUGFIX, CREATE } )
	public static boolean toolboxItemDupeFix = false;

}
