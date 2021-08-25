package cozyconiferous.common.blocks.trees;

import java.util.Random;

import cozyconiferous.init.CCConfiguredFeatures;
import net.minecraft.block.trees.BigTree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class RedwoodTree extends BigTree {

	@Override
	protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredFeature(Random randomIn, boolean largeHive) {
		return CCConfiguredFeatures.redwood;
	}

	@Override
	protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getConfiguredMegaFeature(Random rand) {
		return CCConfiguredFeatures.huge_redwood;
	}
}
