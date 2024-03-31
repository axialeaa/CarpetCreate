package com.axialeaa.carpetcreate;

import carpet.api.settings.Rule;
import carpet.api.settings.Validators;

import static carpet.api.settings.RuleCategory.*;

public class CarpetCreateSettings {

	/**<h1>CATEGORIES</h1>*/

	public static final String CREATE = "create";
	public static final String COMPAT = "compat";
	public static final String PORTING_LIB = "portinglib";
	public static final String FLYWHEEL = "flywheel";

	/**<h1>RULES</h1>*/

	@Rule( options = "1", strict = false, categories = { FEATURE, CREATE }, validators = Validators.NonNegativeNumber.class )
	public static int andesiteFunnelExtractAmount = 1;

	@Rule( options = "16", strict = false, categories = { FEATURE, CREATE }, validators = Validators.NonNegativeNumber.class )
	public static int chuteExtractAmount = 16;

	@Rule( categories = { CREATIVE, CREATE, CLIENT } )
	public static boolean creativePersistentGoggleOverlay = false;

	@Rule( categories = { BUGFIX, CREATE } )
	public static boolean deployerPlacementFix = false;

	@Rule( categories = { BUGFIX, CREATE } )
	public static boolean entityContraptionRotationFix = false;

	@Rule( categories = { CREATIVE, COMPAT, CREATE } )
	public static boolean expandedHopperCounters = false;

	@Rule( categories = { BUGFIX, CREATE } )
	public static boolean flowingBottomlessFluidFix = false;

	@Rule( categories = { COMMAND, CREATIVE, FEATURE, CREATE } )
	public static boolean fluidCounters = false;

	@Rule( categories = { FEATURE, CREATE } )
	public static boolean renewableBlazeCakes = false;

	@Rule( categories = { BUGFIX, CREATE } )
	public static boolean respectRespectData = false;

	@Rule( categories = { BUGFIX, CREATE } )
	public static boolean stationDupeFix = false;

	@Rule( categories = { CREATE, PORTING_LIB, CLIENT } )
	public static boolean suppressSpawnDataLogError = false;

	@Rule( categories = { CREATIVE, CREATE, COMPAT, FLYWHEEL, CLIENT } )
	public static boolean tickFreezePausesAnimations = false;

	@Rule( categories = { BUGFIX, CREATE } )
	public static boolean toolboxItemDupeFix = false;

}
