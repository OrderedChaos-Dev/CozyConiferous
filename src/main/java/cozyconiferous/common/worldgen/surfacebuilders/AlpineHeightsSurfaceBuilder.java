package cozyconiferous.common.worldgen.surfacebuilders;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class AlpineHeightsSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {
	
	public AlpineHeightsSurfaceBuilder(Codec<SurfaceBuilderConfig> CODEC) {
		super(CODEC);
	}
	
	@Override
	public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise,
			BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
		int i = x & 15;
		int j = z & 15;
		BlockState blockstate = Blocks.STONE.getDefaultState();
		ISurfaceBuilderConfig isurfacebuilderconfig = biomeIn.getGenerationSettings().getSurfaceBuilderConfig();
		BlockState blockstate1 = isurfacebuilderconfig.getUnder();
		BlockState blockstate3 = blockstate1;
		int k = (int) (noise / 3.0D + 3.0D + random.nextDouble() * 0.25D);
		int l = -1;
		int i1 = 0;
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

		for (int j1 = startHeight; j1 >= 0; --j1) {
			if (i1 < 15) {
				blockpos$mutable.setPos(i, j1, j);
				BlockState blockstate4 = chunkIn.getBlockState(blockpos$mutable);
				if (blockstate4.isAir()) {
					l = -1;
				} else if (blockstate4.isIn(defaultBlock.getBlock())) {
					if (l == -1) {
						if (k <= 0) {
							blockstate = Blocks.AIR.getDefaultState();
							blockstate3 = defaultBlock;
						} else if (j1 >= seaLevel - 4 && j1 <= seaLevel + 1) {
							blockstate = Blocks.STONE.getDefaultState();
							blockstate3 = blockstate1;
						}

						if (j1 < seaLevel && (blockstate == null || blockstate.isAir())) {
							blockstate = defaultFluid;
						}

						l = k + Math.max(0, j1 - seaLevel);
						if(j1 > 127 + 13 * Math.sin(noise)) {
							chunkIn.setBlockState(blockpos$mutable, Blocks.SNOW_BLOCK.getDefaultState(), false);
						} else if (j1 >= seaLevel - 1) {
							if (j1 > seaLevel + 3 + k) {
								chunkIn.setBlockState(blockpos$mutable, Blocks.STONE.getDefaultState(), false);
							} else {
								chunkIn.setBlockState(blockpos$mutable, Blocks.SNOW_BLOCK.getDefaultState(), false);
							}
						} else {
							chunkIn.setBlockState(blockpos$mutable, blockstate3, false);
						}
					} else if (l > 0) {
						if(j1 > 127 + 13 * Math.sin(noise))
							chunkIn.setBlockState(blockpos$mutable, Blocks.SNOW_BLOCK.getDefaultState(), false);
						else
							chunkIn.setBlockState(blockpos$mutable, Blocks.STONE.getDefaultState(), false);
					}

					++i1;
				}
			}
		}

	}
}
