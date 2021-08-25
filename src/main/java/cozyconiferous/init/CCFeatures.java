package cozyconiferous.init;

import java.util.ArrayList;

import com.mojang.serialization.Codec;

import cozyconiferous.common.worldgen.features.SnowTreeFeature;
import cozyconiferous.common.worldgen.placers.RedwoodTrunkPlacer;
import cozyconiferous.common.worldgen.placers.SmallRedwoodTrunkPlacer;
import cozyconiferous.core.CozyConiferous;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.trunkplacer.AbstractTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.TrunkPlacerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = CozyConiferous.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class CCFeatures {
	public static ArrayList<Feature<?>> FEATURES = new ArrayList<Feature<?>>();
	
	public static Feature<BaseTreeFeatureConfig> snowTree;
	
	@SubscribeEvent
	public static void initFeatures(RegistryEvent.Register<Feature<?>> event) {
		snowTree = registerFeature(new SnowTreeFeature(BaseTreeFeatureConfig.CODEC), "snow_tree");
		
		event.getRegistry().registerAll(FEATURES.toArray(new Feature[0]));
	}
	
	public static <FC extends IFeatureConfig> Feature<FC> registerFeature(Feature<FC> feature, String name) {
		feature.setRegistryName(new ResourceLocation(CozyConiferous.MOD_ID, name));
		FEATURES.add(feature);
		return feature;
	}
	
	@EventBusSubscriber(modid = CozyConiferous.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
	public static class BlockPlacers {
		public static final TrunkPlacerType<SmallRedwoodTrunkPlacer> SMALL_REDWOOD_TRUNK_PLACER = registerTrunkPlacer("small_redwood_trunk_placer", SmallRedwoodTrunkPlacer.CODEC);
		public static final TrunkPlacerType<RedwoodTrunkPlacer> REDWOOD_TRUNK_PLACER = registerTrunkPlacer("redwood_trunk_placer", RedwoodTrunkPlacer.CODEC);
		
		private static <P extends AbstractTrunkPlacer> TrunkPlacerType<P> registerTrunkPlacer(String key, Codec<P> codec) {
			return Registry.register(Registry.TRUNK_PLACER_TYPES, new ResourceLocation(CozyConiferous.MOD_ID, key), new TrunkPlacerType<>(codec));
		}
	}
}
