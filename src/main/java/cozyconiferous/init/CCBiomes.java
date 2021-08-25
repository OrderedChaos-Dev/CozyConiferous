package cozyconiferous.init;

import java.util.ArrayList;
import java.util.List;

import cozyconiferous.common.worldgen.biomes.AlpineHeightsBiome;
import cozyconiferous.common.worldgen.biomes.BorealForestBiome;
import cozyconiferous.common.worldgen.biomes.PineMeadowsBiome;
import cozyconiferous.common.worldgen.biomes.RedwoodsBiome;
import cozyconiferous.core.CCConfig;
import cozyconiferous.core.CozyConiferous;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = CozyConiferous.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class CCBiomes {

	public static final List<Biome> BIOMES = new ArrayList<Biome>();
	
	public static Biome redwoods = register(RedwoodsBiome.makeRedwoodsBiome(0.19F, 0.31F, 0.45F, 0.6F, false), "redwoods");
	public static Biome redwood_peaks = register(RedwoodsBiome.makeRedwoodsBiome(3F, 1F, 0.4F, 0.55F, false), "redwood_peaks");
	public static Biome snowy_redwoods = register(RedwoodsBiome.makeRedwoodsBiome(0.19F, 0.31F, 0.3F, 0.4F, true), "snowy_redwoods");
	public static Biome boreal_forest = register(BorealForestBiome.makeBorealForestBiome(false), "boreal_forest");
	public static Biome snowy_boreal_forest = register(BorealForestBiome.makeBorealForestBiome(true), "snowy_boreal_forest");
	public static Biome alpine_heights = register(AlpineHeightsBiome.makeAlpineHeightsBiome(5F, 1.1F), "alpine_heights");
	public static Biome pine_meadows = register(PineMeadowsBiome.makePineMeadowsBiome(), "pine_meadows");
	
	public static class Keys {
		public static final RegistryKey<Biome> REDWOODS = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(CozyConiferous.MOD_ID, "redwoods"));
		public static final RegistryKey<Biome> REDWOOD_PEAKS = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(CozyConiferous.MOD_ID, "redwood_peaks"));
		public static final RegistryKey<Biome> SNOWY_REDWOODS = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(CozyConiferous.MOD_ID, "snowy_redwoods"));
		public static final RegistryKey<Biome> BOREAL_FOREST = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(CozyConiferous.MOD_ID, "boreal_forest"));
		public static final RegistryKey<Biome> SNOWY_BOREAL_FOREST = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(CozyConiferous.MOD_ID, "snowy_boreal_forest"));
		public static final RegistryKey<Biome> ALPINE_HEIGHTS = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(CozyConiferous.MOD_ID, "alpine_heights"));
		public static final RegistryKey<Biome> PINE_MEADOWS = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(CozyConiferous.MOD_ID, "pine_meadows"));
	}
	
	@SubscribeEvent
	public static void registerBiomes(RegistryEvent.Register<Biome> event) {
		BIOMES.forEach((biome) -> event.getRegistry().register(biome));
		
		addBiomes();
		addTypes();
	}
	
	public static Biome register(Biome biome, String name) {
		biome.setRegistryName(new ResourceLocation(CozyConiferous.MOD_ID, name));
		BIOMES.add(biome);
		return biome;
	}
	
	public static void addBiomes() {
		if(CCConfig.redwoods.get())
			BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(Keys.REDWOODS, 1));
		if(CCConfig.redwood_peaks.get())
			BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(Keys.REDWOOD_PEAKS, 1));
		if(CCConfig.snowy_redwoods.get())
			BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(Keys.SNOWY_REDWOODS, 1));
		if(CCConfig.boreal_forest.get())
			BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(Keys.BOREAL_FOREST, 1));
		if(CCConfig.snowy_boreal_forest.get())
			BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(Keys.SNOWY_BOREAL_FOREST, 1));
		if(CCConfig.alpine_heights.get())
			BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(Keys.ALPINE_HEIGHTS, 1));
		if(CCConfig.pine_meadows.get())
			BiomeManager.addBiome(BiomeType.COOL, new BiomeEntry(Keys.PINE_MEADOWS, 1));
	}
	
	public static void addTypes() {
		BiomeDictionary.addTypes(Keys.REDWOODS, Type.FOREST, Type.CONIFEROUS, Type.COLD, Type.OVERWORLD);
		BiomeDictionary.addTypes(Keys.REDWOOD_PEAKS, Type.FOREST, Type.CONIFEROUS, Type.COLD, Type.MOUNTAIN, Type.OVERWORLD);
		BiomeDictionary.addTypes(Keys.SNOWY_REDWOODS, Type.FOREST, Type.CONIFEROUS, Type.COLD, Type.SNOWY, Type.OVERWORLD);
		BiomeDictionary.addTypes(Keys.BOREAL_FOREST, Type.FOREST, Type.CONIFEROUS, Type.COLD, Type.OVERWORLD);
		BiomeDictionary.addTypes(Keys.SNOWY_BOREAL_FOREST, Type.FOREST, Type.CONIFEROUS, Type.COLD, Type.SNOWY, Type.OVERWORLD);
		BiomeDictionary.addTypes(Keys.ALPINE_HEIGHTS, Type.FOREST, Type.CONIFEROUS, Type.COLD, Type.SNOWY, Type.MOUNTAIN, Type.OVERWORLD);
		BiomeDictionary.addTypes(Keys.PINE_MEADOWS, Type.CONIFEROUS, Type.PLAINS, Type.OVERWORLD);
	}
}
