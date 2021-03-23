package cozyconiferous.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cozyconiferous.client.BlockRendering;
import cozyconiferous.client.EntityRendering;
import cozyconiferous.init.CCConfiguredFeatures;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(CozyConiferous.MOD_ID)
public class CozyConiferous
{
	public static final String MOD_ID = "cozyconiferous";
    private static final Logger LOGGER = LogManager.getLogger();

    public CozyConiferous() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        
        CCConfig.loadConfig(CCConfig.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve("cozyconiferous-common.toml"));
    }

    private void setup(final FMLCommonSetupEvent event) {
    	CCConfiguredFeatures.init();
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
    	BlockRendering.registerRenderers();
    	EntityRendering.registerRenderers();
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {

    }

    private void processIMC(final InterModProcessEvent event) {

    }
}
