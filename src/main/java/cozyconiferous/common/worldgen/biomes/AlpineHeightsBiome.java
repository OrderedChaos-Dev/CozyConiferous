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
		DefaultBiomeFeatures.commonSpawns(mobSpawnInfo);
		BiomeGenerationSettings.Builder biomeGenBuilder = (new BiomeGenerationSettings.Builder())
				.surfaceBuilder(CCConfiguredSurfaceBuilders.ALPINE_HEIGHTS);

		DefaultBiomeFeatures.addDefaultOverworldLandMesaStructures(biomeGenBuilder);
		biomeGenBuilder.addStructureStart(StructureFeatures.RUINED_PORTAL_MOUNTAIN);
		
		DefaultBiomeFeatures.addDefaultCarvers(biomeGenBuilder);
		DefaultBiomeFeatures.addDefaultLakes(biomeGenBuilder);
		DefaultBiomeFeatures.addDefaultMonsterRoom(biomeGenBuilder);
		DefaultBiomeFeatures.addMossyStoneBlock(biomeGenBuilder);
		DefaultBiomeFeatures.addFerns(biomeGenBuilder);
		DefaultBiomeFeatures.addDefaultUndergroundVariety(biomeGenBuilder);
		DefaultBiomeFeatures.addDefaultOres(biomeGenBuilder);
		DefaultBiomeFeatures.addDefaultSoftDisks(biomeGenBuilder);
		DefaultBiomeFeatures.addDefaultFlowers(biomeGenBuilder);
		DefaultBiomeFeatures.addGiantTaigaVegetation(biomeGenBuilder);
		DefaultBiomeFeatures.addDefaultMushrooms(biomeGenBuilder);
		DefaultBiomeFeatures.addDefaultExtraVegetation(biomeGenBuilder);
		DefaultBiomeFeatures.addExtraEmeralds(biomeGenBuilder);
		DefaultBiomeFeatures.addSparseBerryBushes(biomeGenBuilder);
		DefaultBiomeFeatures.addDefaultSprings(biomeGenBuilder);
		DefaultBiomeFeatures.addSurfaceFreezing(biomeGenBuilder);
		
		return (new Biome.Builder()).precipitation(Biome.RainType.SNOW).biomeCategory(Biome.Category.EXTREME_HILLS).depth(depth)
				.scale(scale).temperature(-0.4F).downfall(0.3F)
				.specialEffects((new BiomeAmbience.Builder()).waterColor(4159204).waterFogColor(329011)
				.fogColor(12638463).skyColor(BiomeUtils.getSkyColorWithTemperatureModifier(2.0F))
				.foliageColorOverride(0x00994d).grassColorOverride(0x00994d).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS)
				.build())
				.mobSpawnSettings(mobSpawnInfo.build())
				.generationSettings(biomeGenBuilder.build()).build();
	}
}
