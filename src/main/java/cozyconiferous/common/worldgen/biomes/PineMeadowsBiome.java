package cozyconiferous.common.worldgen.biomes;

import cozyconiferous.common.worldgen.BiomeUtils;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

public class PineMeadowsBiome {

	public static Biome makePineMeadowsBiome() {
		MobSpawnInfo.Builder mobSpawnInfo = new MobSpawnInfo.Builder();
		DefaultBiomeFeatures.withSpawnsWithHorseAndDonkey(mobSpawnInfo);
		mobSpawnInfo.isValidSpawnBiomeForPlayer();

		BiomeGenerationSettings.Builder biomeGenBuilder = (new BiomeGenerationSettings.Builder())
				.withSurfaceBuilder(ConfiguredSurfaceBuilders.field_244178_j);
		biomeGenBuilder.withStructure(StructureFeatures.VILLAGE_TAIGA)
				.withStructure(StructureFeatures.PILLAGER_OUTPOST);

		DefaultBiomeFeatures.withStrongholdAndMineshaft(biomeGenBuilder);
		biomeGenBuilder.withStructure(StructureFeatures.RUINED_PORTAL);
		DefaultBiomeFeatures.withCavesAndCanyons(biomeGenBuilder);
		DefaultBiomeFeatures.withLavaAndWaterLakes(biomeGenBuilder);
		DefaultBiomeFeatures.withMonsterRoom(biomeGenBuilder);
		DefaultBiomeFeatures.withNoiseTallGrass(biomeGenBuilder);
		DefaultBiomeFeatures.withCommonOverworldBlocks(biomeGenBuilder);
		DefaultBiomeFeatures.withOverworldOres(biomeGenBuilder);
		DefaultBiomeFeatures.withDisks(biomeGenBuilder);
		biomeGenBuilder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.FLOWER_PLAIN_DECORATED);
		biomeGenBuilder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_GRASS_PLAIN);

		DefaultBiomeFeatures.withNormalMushroomGeneration(biomeGenBuilder);
		DefaultBiomeFeatures.withSugarCaneAndPumpkins(biomeGenBuilder);
		biomeGenBuilder.withFeature(Decoration.VEGETAL_DECORATION, Features.PATCH_SUNFLOWER);
		biomeGenBuilder.withFeature(Decoration.VEGETAL_DECORATION, Features.OAK_BEES_0002);

		DefaultBiomeFeatures.withLavaAndWaterSprings(biomeGenBuilder);
		DefaultBiomeFeatures.withFrozenTopLayer(biomeGenBuilder);
		return (new Biome.Builder()).precipitation(Biome.RainType.RAIN).category(Biome.Category.PLAINS).depth(0.125F)
				.scale(0.05F).temperature(0.7F).downfall(0.8F)
				.setEffects((new BiomeAmbience.Builder()).setWaterColor(4159204).setWaterFogColor(329011).withGrassColor(0xa7cc5c)
						.withFoliageColor(0xa7cc5c)
						.setFogColor(12638463).withSkyColor(BiomeUtils.getSkyColorWithTemperatureModifier(0.8F))
						.setMoodSound(MoodSoundAmbience.DEFAULT_CAVE).build())
				.withMobSpawnSettings(mobSpawnInfo.copy()).withGenerationSettings(biomeGenBuilder.build()).build();
	}
}
