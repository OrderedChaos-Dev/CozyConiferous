package cozyconiferous.common.worldgen.surfacebuilders;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class RedwoodsSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {
	public RedwoodsSurfaceBuilder(Codec<SurfaceBuilderConfig> CODEC) {
		super(CODEC);
	}

	public void buildSurface(Random rand, IChunk chunk, Biome biome, int x, int z, int startHeight, double noise,
			BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
		if (noise > 1.75D) {
			SurfaceBuilder.DEFAULT.buildSurface(rand, chunk, biome, x, z, startHeight, noise, defaultBlock,
					defaultFluid, seaLevel, seed, SurfaceBuilder.CORASE_DIRT_DIRT_GRAVEL_CONFIG);
		} else if (noise <= 1.75D && noise > 1.2D) {
			SurfaceBuilder.DEFAULT.buildSurface(rand, chunk, biome, x, z, startHeight, noise, defaultBlock,
					defaultFluid, seaLevel, seed, SurfaceBuilder.STONE_STONE_GRAVEL_CONFIG);
		} else if (noise > -0.90D) {
			SurfaceBuilder.DEFAULT.buildSurface(rand, chunk, biome, x, z, startHeight, noise, defaultBlock,
					defaultFluid, seaLevel, seed, SurfaceBuilder.PODZOL_DIRT_GRAVEL_CONFIG);
		} else {
			SurfaceBuilder.DEFAULT.buildSurface(rand, chunk, biome, x, z, startHeight, noise, defaultBlock,
					defaultFluid, seaLevel, seed, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG);
		}

	}
}
