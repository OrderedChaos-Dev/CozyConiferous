package cozyconiferous.common.worldgen.placers;

import java.util.List;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.FoliagePlacer;
import net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;

public class SmallRedwoodTrunkPlacer extends AbstractTrunkPlacer {
	
	public static final Codec<SmallRedwoodTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) -> {
		return func_236915_a_(instance).apply(instance, SmallRedwoodTrunkPlacer::new);
	});

	public SmallRedwoodTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
		super(baseHeight, heightRandA, heightRandB);
	}

	@Override
	protected TrunkPlacerType<?> func_230381_a_() {
		return TrunkPlacerType.STRAIGHT_TRUNK_PLACER;
	}

	@Override
	public List<FoliagePlacer.Foliage> func_230382_a_(IWorldGenerationReader world, Random rand, int height, BlockPos pos, Set<BlockPos> blocks, MutableBoundingBox box, BaseTreeFeatureConfig config) {
		func_236909_a_(world, pos.down());

		for (int i = 0; i < height; ++i) {
			func_236911_a_(world, rand, pos.up(i), blocks, box, config);
		}
		
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
		placeBase(world, rand, blockpos$mutable, config, pos, 1, 4, 0, 1);
		placeBase(world, rand, blockpos$mutable, config, pos, 1, 4, 1, 0);
		placeBase(world, rand, blockpos$mutable, config, pos, 1, 4, -1, 0);
		placeBase(world, rand, blockpos$mutable, config, pos, 1, 4, 0, -1);

		return ImmutableList.of(new FoliagePlacer.Foliage(pos.up(height), 0, false));
	}
	
	public static void placeBase(IWorldGenerationReader world, Random rand, BlockPos.Mutable pos, BaseTreeFeatureConfig config, BlockPos start, int heightMin, int heightMax, int offX, int offZ) {
		int height = rand.nextInt(heightMax - heightMin) + heightMin;
		int offY = 1;
		while(offY > -3 && world.hasBlockState(pos.setAndOffset(start, offX, offY, offZ), (state) -> state.getMaterial().isReplaceable())) {
			offY--;
		}
		
		for(int i = offY; i < height; i++) {
			BlockPos.Mutable temp = pos.setAndOffset(start, offX, i, offZ);
			
			if(i < height - 1)
				world.setBlockState(temp, config.trunkProvider.getBlockState(rand, temp), 2);
			else
				world.setBlockState(temp, Blocks.SPRUCE_WOOD.getDefaultState(), 2);
		}
	}
}
