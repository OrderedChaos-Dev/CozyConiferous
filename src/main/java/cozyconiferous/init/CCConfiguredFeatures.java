package cozyconiferous.init;

import com.google.common.collect.ImmutableList;

import cozyconiferous.common.worldgen.placers.CCPineFoliagePlacer;
import cozyconiferous.common.worldgen.placers.RedwoodTrunkPlacer;
import cozyconiferous.common.worldgen.placers.SmallRedwoodTrunkPlacer;
import cozyconiferous.core.CozyConiferous;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.foliageplacer.MegaPineFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.PineFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.SpruceFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

public class CCConfiguredFeatures {

	public static final RuleTest SNOW_BLOCK = new BlockMatchRuleTest(Blocks.SNOW_BLOCK);

	public static ConfiguredFeature<BaseTreeFeatureConfig, ?> huge_redwood;
	public static ConfiguredFeature<BaseTreeFeatureConfig, ?> redwood;
	public static ConfiguredFeature<BaseTreeFeatureConfig, ?> fir;
	public static ConfiguredFeature<BaseTreeFeatureConfig, ?> pine;

	public static ConfiguredFeature<?, ?> redwood_forest;
	public static ConfiguredFeature<?, ?> boreal_forest;
	public static ConfiguredFeature<?, ?> pine_meadows;

	public static void init() {
		huge_redwood = register("huge_redwood",
				Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(
						new SimpleBlockStateProvider(CCBlocks.redwood_log.defaultBlockState()),
						new SimpleBlockStateProvider(CCBlocks.redwood_leaves.defaultBlockState()),
						new MegaPineFoliagePlacer(FeatureSpread.fixed(0), FeatureSpread.fixed(0), FeatureSpread.of(16, 7)),
						new RedwoodTrunkPlacer(32, 24, 24), new TwoLayerFeature(1, 1, 2))).build()));

		redwood = register("redwood",
				Feature.TREE.configured((new BaseTreeFeatureConfig.Builder(
						new SimpleBlockStateProvider(CCBlocks.redwood_log.defaultBlockState()),
						new SimpleBlockStateProvider(CCBlocks.redwood_leaves.defaultBlockState()),
						new PineFoliagePlacer(FeatureSpread.fixed(1), FeatureSpread.fixed(1),
								FeatureSpread.of(3, 1)),
						new SmallRedwoodTrunkPlacer(7, 5, 0), new TwoLayerFeature(2, 0, 2))).ignoreVines()
								.build()));

		fir = CCFeatures.snowTree.configured(
				(new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(CCBlocks.fir_log.defaultBlockState()),
						new SimpleBlockStateProvider(CCBlocks.fir_leaves.defaultBlockState()),
						new SpruceFoliagePlacer(FeatureSpread.of(3, 1), FeatureSpread.of(1, 1),
								FeatureSpread.of(4, 2)),
						new StraightTrunkPlacer(15, 3, 4), new TwoLayerFeature(2, 0, 2))).ignoreVines().build());

		pine = Feature.TREE.configured(
				(new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(CCBlocks.pine_log.defaultBlockState()),
						new SimpleBlockStateProvider(CCBlocks.pine_leaves.defaultBlockState()),
						new CCPineFoliagePlacer(FeatureSpread.of(3, 1), FeatureSpread.of(1, 1),
								FeatureSpread.of(3, 2)),
						new StraightTrunkPlacer(9, 2, 2), new TwoLayerFeature(2, 0, 2))).ignoreVines().build());

		redwood_forest = register("redwood_forest", Feature.RANDOM_SELECTOR
				.configured(new MultipleRandomFeatureConfig(
						ImmutableList.of(huge_redwood.weighted(0.75F), redwood.weighted(0.25F)), huge_redwood))
				.decorated(Features.Placements.HEIGHTMAP_SQUARE)
				.decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(7, 0.4F, 2))));
		
		boreal_forest = register("boreal_forest",
				Feature.RANDOM_SELECTOR
						.configured(new MultipleRandomFeatureConfig(
								ImmutableList.of(fir.weighted(0.75F), pine.weighted(0.25F)), fir))
						.decorated(Features.Placements.HEIGHTMAP_SQUARE)
						.decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.3F, 1))));
		
		pine_meadows = register("pine_meadows",
				Feature.RANDOM_SELECTOR
					.configured(new MultipleRandomFeatureConfig(ImmutableList.of(pine.weighted(0.4F),
							Features.OAK.weighted(0.2F), Features.OAK_BEES_0002.weighted(0.15F)), Features.OAK))
					.decorated(Features.Placements.HEIGHTMAP_SQUARE)
					.decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.2F, 2))));
	}

	private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key,
			ConfiguredFeature<FC, ?> configuredFeature) {
		return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
				new ResourceLocation(CozyConiferous.MOD_ID, key), configuredFeature);
	}

	public static void load() {

	}
}
