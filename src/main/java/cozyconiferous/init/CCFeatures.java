package cozyconiferous.init;

import java.util.ArrayList;

import cozyconiferous.common.worldgen.features.SnowTreeFeature;
import cozyconiferous.core.CozyConiferous;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
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
}
