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
import net.minecraft.world.gen.feature.Features.Placements;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
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

	public static ConfiguredFeature<?, ?> stone_dirt;
	public static ConfiguredFeature<?, ?> snow_dirt;

	public static void init() {
		huge_redwood = register("huge_redwood",
				Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(
						new SimpleBlockStateProvider(CCBlocks.redwood_log.getDefaultState()),
						new SimpleBlockStateProvider(CCBlocks.redwood_leaves.getDefaultState()),
						new MegaPineFoliagePlacer(FeatureSpread.func_242252_a(0), FeatureSpread.func_242252_a(0),
								FeatureSpread.func_242253_a(25, 7)),
						new RedwoodTrunkPlacer(40, 30, 14), new TwoLayerFeature(1, 1, 2))).build()));

		redwood = register("redwood",
				Feature.TREE.withConfiguration((new BaseTreeFeatureConfig.Builder(
						new SimpleBlockStateProvider(CCBlocks.redwood_log.getDefaultState()),
						new SimpleBlockStateProvider(CCBlocks.redwood_leaves.getDefaultState()),
						new PineFoliagePlacer(FeatureSpread.func_242252_a(1), FeatureSpread.func_242252_a(1),
								FeatureSpread.func_242253_a(3, 1)),
						new SmallRedwoodTrunkPlacer(7, 5, 0), new TwoLayerFeature(2, 0, 2))).setIgnoreVines()
								.build()));

		redwood_forest = register("redwood_forest", Feature.RANDOM_SELECTOR
				.withConfiguration(new MultipleRandomFeatureConfig(
						ImmutableList.of(huge_redwood.withChance(0.75F), redwood.withChance(0.25F)), huge_redwood))
				.withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
				.withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(4, 0.3F, 1))));

		fir = Feature.TREE.withConfiguration(
				(new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(CCBlocks.fir_log.getDefaultState()),
						new SimpleBlockStateProvider(CCBlocks.fir_leaves.getDefaultState()),
						new SpruceFoliagePlacer(FeatureSpread.func_242253_a(3, 1), FeatureSpread.func_242253_a(1, 1),
								FeatureSpread.func_242253_a(4, 2)),
						new StraightTrunkPlacer(15, 3, 4), new TwoLayerFeature(2, 0, 2))).setIgnoreVines().build());

		pine = Feature.TREE.withConfiguration(
				(new BaseTreeFeatureConfig.Builder(new SimpleBlockStateProvider(CCBlocks.pine_log.getDefaultState()),
						new SimpleBlockStateProvider(CCBlocks.pine_leaves.getDefaultState()),
						new CCPineFoliagePlacer(FeatureSpread.func_242253_a(3, 1), FeatureSpread.func_242253_a(1, 1),
								FeatureSpread.func_242253_a(3, 2)),
						new StraightTrunkPlacer(9, 2, 2), new TwoLayerFeature(2, 0, 2))).setIgnoreVines().build());

		
		boreal_forest = register("boreal_forest",
				Feature.RANDOM_SELECTOR
						.withConfiguration(new MultipleRandomFeatureConfig(
								ImmutableList.of(fir.withChance(0.75F), pine.withChance(0.25F)), fir))
						.withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
						.withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(10, 0.3F, 1))));
		
		pine_meadows = register("pine_meadows",
				Feature.RANDOM_SELECTOR
						.withConfiguration(new MultipleRandomFeatureConfig(
								ImmutableList.of(pine.withChance(0.75F)), pine))
						.withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
						.withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.5F, 2))));

		stone_dirt = register("stone_dirt",
				Feature.ORE
						.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
								Blocks.DIRT.getDefaultState(), 33))
						.withPlacement(Placements.HEIGHTMAP_PLACEMENT).square().func_242731_b(10));
		snow_dirt = register("snow_dirt",
				Feature.ORE
						.withConfiguration(new OreFeatureConfig(SNOW_BLOCK,
								Blocks.DIRT.getDefaultState(), 33))
						.withPlacement(Placements.HEIGHTMAP_PLACEMENT).square().func_242731_b(10));
	}

	private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key,
			ConfiguredFeature<FC, ?> configuredFeature) {
		return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE,
				new ResourceLocation(CozyConiferous.MOD_ID, key), configuredFeature);
	}

	public static void load() {

	}
}
