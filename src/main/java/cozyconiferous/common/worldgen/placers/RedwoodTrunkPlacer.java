package cozyconiferous.common.worldgen.placers;

import java.util.List;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import cozyconiferous.init.CCBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;

public class RedwoodTrunkPlacer extends AbstractTrunkPlacer {
	
	public static final Codec<RedwoodTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) -> {
		return func_236915_a_(instance).apply(instance, RedwoodTrunkPlacer::new);
	});

	public RedwoodTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
		super(baseHeight, heightRandA, heightRandB);
	}

	@Override
	protected TrunkPlacerType<?> func_230381_a_() {
		return TrunkPlacerType.GIANT_TRUNK_PLACER;
	}

	@Override
	public List<FoliagePlacer.Foliage> func_230382_a_(IWorldGenerationReader world, Random rand, int height, BlockPos pos, Set<BlockPos> blocks, MutableBoundingBox boundingBox, BaseTreeFeatureConfig config) {
		BlockPos blockpos = pos.down();
		//place dirt
		func_236909_a_(world, blockpos);
		func_236909_a_(world, blockpos.east());
		func_236909_a_(world, blockpos.south());
		func_236909_a_(world, blockpos.south().east());
		
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

		for (int i = 0; i < height; ++i) {
			placeLog(world, rand, blockpos$mutable, blocks, boundingBox, config, pos, 0, i, 0);
			placeLog(world, rand, blockpos$mutable, blocks, boundingBox, config, pos, 1, i, 0);
			placeLog(world, rand, blockpos$mutable, blocks, boundingBox, config, pos, 1, i, 1);
			placeLog(world, rand, blockpos$mutable, blocks, boundingBox, config, pos, 0, i, 1);
		}
		
		placeBase(world, rand, blockpos$mutable, config, pos, 5, 8, -1, 0);
		placeBase(world, rand, blockpos$mutable, config, pos, 5, 8, -1, 1);
		placeBase(world, rand, blockpos$mutable, config, pos, 5, 8, 0, 2);
		placeBase(world, rand, blockpos$mutable, config, pos, 5, 8, 1, 2);
		placeBase(world, rand, blockpos$mutable, config, pos, 5, 8, 2, 0);
		placeBase(world, rand, blockpos$mutable, config, pos, 5, 8, 2, 1);
		placeBase(world, rand, blockpos$mutable, config, pos, 5, 8, 0, -1);
		placeBase(world, rand, blockpos$mutable, config, pos, 5, 8, 1, -1);
		
		placeBase(world, rand, blockpos$mutable, config, pos, 1, 5, -1, 2);
		placeBase(world, rand, blockpos$mutable, config, pos, 1, 5, -1, -1);
		placeBase(world, rand, blockpos$mutable, config, pos, 1, 5, 2, 2);
		placeBase(world, rand, blockpos$mutable, config, pos, 1, 5, 2, -1);
		placeBase(world, rand, blockpos$mutable, config, pos, 1, 5, -2, 0);
		placeBase(world, rand, blockpos$mutable, config, pos, 1, 5, -2, 1);
		placeBase(world, rand, blockpos$mutable, config, pos, 1, 5, 3, 1);
		placeBase(world, rand, blockpos$mutable, config, pos, 1, 5, 3, 0);
		placeBase(world, rand, blockpos$mutable, config, pos, 1, 5, 0, -2);
		placeBase(world, rand, blockpos$mutable, config, pos, 1, 5, 1, -2);
		placeBase(world, rand, blockpos$mutable, config, pos, 1, 5, 0, 3);
		placeBase(world, rand, blockpos$mutable, config, pos, 1, 5, 1, 3);

		return ImmutableList.of(new FoliagePlacer.Foliage(pos.up(height), 0, true));
	}

	private static void placeLog(IWorldGenerationReader world, Random rand, BlockPos.Mutable pos, Set<BlockPos> blocks, MutableBoundingBox boundingBox, BaseTreeFeatureConfig config, BlockPos start, int offX, int offY, int offZ) {
		pos.setAndOffset(start, offX, offY, offZ);
		//place log
		func_236910_a_(world, rand, pos, blocks, boundingBox, config);
	}
	
	public static void placeBase(IWorldGenerationReader world, Random rand, BlockPos.Mutable pos, BaseTreeFeatureConfig config, BlockPos start, int heightMin, int heightMax, int offX, int offZ) {
		int height = rand.nextInt(heightMax - heightMin) + heightMin;
		int offY = 1;
		while(offY > -4 && world.hasBlockState(pos.setAndOffset(start, offX, offY, offZ), (state) -> state.getMaterial().isReplaceable())) {
			offY--;
		}
		
		for(int i = offY; i < height; i++) {
			BlockPos.Mutable temp = pos.setAndOffset(start, offX, i, offZ);
			
			if(i < height - 1)
				world.setBlockState(temp, config.trunkProvider.getBlockState(rand, temp), 2);
			else
				world.setBlockState(temp, CCBlocks.redwood_wood.getDefaultState(), 2);
		}
	}
}
