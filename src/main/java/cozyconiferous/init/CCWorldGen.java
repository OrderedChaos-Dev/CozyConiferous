package cozyconiferous.init;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import cozyconiferous.core.CozyConiferous;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = CozyConiferous.MOD_ID)
public class CCWorldGen {
	
	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void addBiomeFeatures(BiomeLoadingEvent event) {
		RegistryKey<Biome> biome = RegistryKey.getOrCreateKey(ForgeRegistries.Keys.BIOMES, event.getName());
		Set<BiomeDictionary.Type> biomeTypes = BiomeDictionary.getTypes(biome);
		List<Supplier<ConfiguredFeature<?, ?>>> vegetalFeatures = event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION);
		
		if(biome == CCBiomes.Keys.REDWOODS || biome == CCBiomes.Keys.REDWOOD_PEAKS || biome == CCBiomes.Keys.SNOWY_REDWOODS) {
			vegetalFeatures.add(() -> CCConfiguredFeatures.redwood_forest);
		} else if(biome == CCBiomes.Keys.BOREAL_FOREST || biome == CCBiomes.Keys.SNOWY_BOREAL_FOREST || biome == CCBiomes.Keys.ALPINE_HEIGHTS) {
			vegetalFeatures.add(() -> CCConfiguredFeatures.boreal_forest);
		}
		
		if(biome == CCBiomes.Keys.ALPINE_HEIGHTS) {
			event.getGeneration().getFeatures(Decoration.UNDERGROUND_ORES).add(() -> CCConfiguredFeatures.snow_dirt);
			event.getGeneration().getFeatures(Decoration.UNDERGROUND_ORES).add(() -> CCConfiguredFeatures.stone_dirt);
		}
		
		if(biome == CCBiomes.Keys.PINE_MEADOWS) {
			event.getGeneration().getFeatures(Decoration.UNDERGROUND_ORES).add(() -> CCConfiguredFeatures.pine_meadows);
		}
	}
}
