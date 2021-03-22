package cozyconiferous.common.worldgen.biomes;

import cozyconiferous.common.worldgen.BiomeUtils;
import cozyconiferous.init.CCConfiguredSurfaceBuilders;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.feature.structure.StructureFeatures;

public class AlpineHeightsBiome {

	public static Biome makeAlpineHeightsBiome(float depth, float scale) {
		MobSpawnInfo.Builder mobSpawnInfo = new MobSpawnInfo.Builder();
		DefaultBiomeFeatures.withBatsAndHostiles(mobSpawnInfo);
		BiomeGenerationSettings.Builder biomeGenBuilder = (new BiomeGenerationSettings.Builder())
				.withSurfaceBuilder(CCConfiguredSurfaceBuilders.ALPINE_HEIGHTS);

		DefaultBiomeFeatures.withStrongholdAndMineshaft(biomeGenBuilder);
		biomeGenBuilder.withStructure(StructureFeatures.RUINED_PORTAL_MOUNTAIN);
		
		DefaultBiomeFeatures.withCavesAndCanyons(biomeGenBuilder);
		DefaultBiomeFeatures.withLavaAndWaterLakes(biomeGenBuilder);
		DefaultBiomeFeatures.withMonsterRoom(biomeGenBuilder);
		DefaultBiomeFeatures.withForestRocks(biomeGenBuilder);
		DefaultBiomeFeatures.withLargeFern(biomeGenBuilder);
		DefaultBiomeFeatures.withCommonOverworldBlocks(biomeGenBuilder);
		DefaultBiomeFeatures.withOverworldOres(biomeGenBuilder);
		DefaultBiomeFeatures.withDisks(biomeGenBuilder);
		DefaultBiomeFeatures.withDefaultFlowers(biomeGenBuilder);
		DefaultBiomeFeatures.withGiantTaigaGrassVegetation(biomeGenBuilder);
		DefaultBiomeFeatures.withNormalMushroomGeneration(biomeGenBuilder);
		DefaultBiomeFeatures.withSugarCaneAndPumpkins(biomeGenBuilder);
		DefaultBiomeFeatures.withSparseBerries(biomeGenBuilder);
		DefaultBiomeFeatures.withFrozenTopLayer(biomeGenBuilder);
		
		return (new Biome.Builder()).precipitation(Biome.RainType.SNOW).category(Biome.Category.EXTREME_HILLS).depth(depth)
				.scale(scale).temperature(-0.4F).downfall(0.3F)
				.setEffects((new BiomeAmbience.Builder()).setWaterColor(4159204).setWaterFogColor(329011)
				.setFogColor(12638463).withSkyColor(BiomeUtils.getSkyColorWithTemperatureModifier(2.0F))
				.withFoliageColor(0x00994d).withGrassColor(0x00994d).setMoodSound(MoodSoundAmbience.DEFAULT_CAVE)
				.build())
				.withMobSpawnSettings(mobSpawnInfo.copy())
				.withGenerationSettings(biomeGenBuilder.build()).build();
	}
}
