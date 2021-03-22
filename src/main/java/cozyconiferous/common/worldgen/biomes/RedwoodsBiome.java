package cozyconiferous.common.worldgen.biomes;

import cozyconiferous.common.worldgen.BiomeUtils;
import cozyconiferous.init.CCConfiguredSurfaceBuilders;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.feature.structure.StructureFeatures;

public class RedwoodsBiome {

	public static Biome makeRedwoodsBiome(float depth, float scale, float temperature, float downfall, boolean isSnowy) {
		MobSpawnInfo.Builder mobSpawnInfo = new MobSpawnInfo.Builder();
		
		mobSpawnInfo.withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.WOLF, 8, 4, 4));
		mobSpawnInfo.withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.RABBIT, 4, 2, 3));
		mobSpawnInfo.withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.FOX, 8, 2, 4));
		mobSpawnInfo.isValidSpawnBiomeForPlayer();
		DefaultBiomeFeatures.withBatsAndHostiles(mobSpawnInfo);

		float f = isSnowy ? -0.4F : temperature;
		
		BiomeGenerationSettings.Builder biomeGenBuilder = (new BiomeGenerationSettings.Builder()).withSurfaceBuilder(CCConfiguredSurfaceBuilders.REDWOODS);
		DefaultBiomeFeatures.withStrongholdAndMineshaft(biomeGenBuilder);
		biomeGenBuilder.withStructure(StructureFeatures.RUINED_PORTAL);
		biomeGenBuilder.withStructure(StructureFeatures.VILLAGE_TAIGA);
		biomeGenBuilder.withStructure(StructureFeatures.PILLAGER_OUTPOST);
		
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
		DefaultBiomeFeatures.withLavaAndWaterSprings(biomeGenBuilder);
		DefaultBiomeFeatures.withSparseBerries(biomeGenBuilder);
		DefaultBiomeFeatures.withFrozenTopLayer(biomeGenBuilder);
		
		if(!isSnowy)
			DefaultBiomeFeatures.withPlainGrassVegetation(biomeGenBuilder);

	      
		return (new Biome.Builder()).precipitation(isSnowy ? RainType.SNOW : RainType.RAIN)
				.category(Biome.Category.TAIGA).depth(depth).scale(scale).temperature(f)
				.downfall(downfall)
				.setEffects((new BiomeAmbience.Builder()).setWaterColor(4159204)
						.setWaterFogColor(329011).setFogColor(12638463)
						.withGrassColor(0x379332).withFoliageColor(0x379C32)
						.withSkyColor(BiomeUtils.getSkyColorWithTemperatureModifier(0.25F))
						.setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build())
				.withMobSpawnSettings(mobSpawnInfo.copy())
				.withGenerationSettings(biomeGenBuilder.build()).build();
	}
}
