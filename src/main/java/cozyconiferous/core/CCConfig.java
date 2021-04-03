package cozyconiferous.core;

import java.nio.file.Path;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CCConfig {

	private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
	
	public static ForgeConfigSpec COMMON_CONFIG;
	
	public static ForgeConfigSpec.ConfigValue<Boolean> boreal_forest;
	public static ForgeConfigSpec.ConfigValue<Boolean> snowy_boreal_forest;
	public static ForgeConfigSpec.ConfigValue<Boolean> redwoods;
	public static ForgeConfigSpec.ConfigValue<Boolean> snowy_redwoods;
	public static ForgeConfigSpec.ConfigValue<Boolean> redwood_peaks;
	public static ForgeConfigSpec.ConfigValue<Boolean> pine_meadows;
	public static ForgeConfigSpec.ConfigValue<Boolean> alpine_heights;
	
	static {
		COMMON_BUILDER.comment("Cozy Coniferous Settings").push("Biome Settings");
		
		boreal_forest = COMMON_BUILDER.define("boreal_forest", true);
		snowy_boreal_forest = COMMON_BUILDER.define("snowy_boreal_forest", true);
		redwoods = COMMON_BUILDER.define("redwoods", true);
		snowy_redwoods = COMMON_BUILDER.define("snowy_redwoods", true);
		redwood_peaks = COMMON_BUILDER.define("redwood_peaks", true);
		pine_meadows = COMMON_BUILDER.define("pine_meadows", true);
		alpine_heights = COMMON_BUILDER.define("alpine_heights", true);
		
		COMMON_BUILDER.pop();
		COMMON_CONFIG = COMMON_BUILDER.build();
	}
	
	public static void loadConfig(ForgeConfigSpec spec, Path path) {
		final CommentedFileConfig configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
		
		configData.load();
		spec.setConfig(configData);
	}
}
