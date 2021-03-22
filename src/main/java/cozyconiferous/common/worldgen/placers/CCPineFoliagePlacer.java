package cozyconiferous.common.worldgen.placers;

import java.util.Random;
import java.util.Set;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.foliageplacer.SpruceFoliagePlacer;

public class CCPineFoliagePlacer extends SpruceFoliagePlacer {

	public CCPineFoliagePlacer(FeatureSpread radius, FeatureSpread offset, FeatureSpread trunkHeight) {
		super(radius, offset, trunkHeight);
	}
	
	@Override
	protected void func_230372_a_(IWorldGenerationReader world, Random rand, BaseTreeFeatureConfig config,
			int p_230372_4_, FoliagePlacer.Foliage foliage, int trunkOffset, int p_230372_7_, Set<BlockPos> blocks,
			int leavesStartHeight, MutableBoundingBox box) {
		BlockPos blockpos = foliage.func_236763_a_();
		int i = rand.nextInt(2);
		int j = 1;
		int k = 1;
		int m = 0;

		for (int l = leavesStartHeight; l >= -trunkOffset; --l) {
			if(m < 4)
				placeLeaves(world, rand, config, blockpos, i, blocks, l, foliage.func_236765_c_(), box);
			else
				placeLeavesWithChance(world, rand, config, blockpos, i, blocks, l, foliage.func_236765_c_(), box, 0.8F);
			if (i >= j) {
				i = k;
				k = 2;
				j = Math.min(j + 1, p_230372_7_ + foliage.func_236764_b_());
			} else {
				++i;
			}
			m++;
		}
	}
	
	protected void placeLeaves(IWorldGenerationReader world, Random rand,BaseTreeFeatureConfig config, BlockPos pos, int p_236753_5_, Set<BlockPos> blocks,int p_236753_7_, boolean p_236753_8_, MutableBoundingBox box) {
		int i = p_236753_8_ ? 1 : 0;
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

		for (int j = -p_236753_5_; j <= p_236753_5_ + i; ++j) {
			for (int k = -p_236753_5_; k <= p_236753_5_ + i; ++k) {
				if (!this.func_230375_b_(rand, j, p_236753_7_, k, p_236753_5_, p_236753_8_)) {
					blockpos$mutable.setAndOffset(pos, j, p_236753_7_, k);
					if (TreeFeature.isReplaceableAt(world, blockpos$mutable) && rand.nextFloat() < 0.8F) {
						world.setBlockState(blockpos$mutable,
								config.leavesProvider.getBlockState(rand, blockpos$mutable), 19);
						box.expandTo(new MutableBoundingBox(blockpos$mutable, blockpos$mutable));
						blocks.add(blockpos$mutable.toImmutable());
					}
				}
			}
		}
	}
	
	protected void placeLeavesWithChance(IWorldGenerationReader world, Random rand,BaseTreeFeatureConfig config, BlockPos pos, int p_236753_5_, Set<BlockPos> blocks,int p_236753_7_, boolean p_236753_8_, MutableBoundingBox box, float chance) {
		int i = p_236753_8_ ? 1 : 0;
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

		for (int j = -p_236753_5_; j <= p_236753_5_ + i; ++j) {
			for (int k = -p_236753_5_; k <= p_236753_5_ + i; ++k) {
				if (!this.func_230375_b_(rand, j, p_236753_7_, k, p_236753_5_, p_236753_8_)) {
					blockpos$mutable.setAndOffset(pos, j, p_236753_7_, k);
					if (TreeFeature.isReplaceableAt(world, blockpos$mutable) && rand.nextFloat() < chance) {
						world.setBlockState(blockpos$mutable,
								config.leavesProvider.getBlockState(rand, blockpos$mutable), 19);
						box.expandTo(new MutableBoundingBox(blockpos$mutable, blockpos$mutable));
						blocks.add(blockpos$mutable.toImmutable());
					}
				}
			}
		}
	}
}
