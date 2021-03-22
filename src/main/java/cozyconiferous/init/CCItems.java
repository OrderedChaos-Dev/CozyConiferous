package cozyconiferous.init;

import java.util.ArrayList;

import cozyconiferous.core.CozyConiferous;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = CozyConiferous.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class CCItems {

	public static ArrayList<Item> ITEMS = new ArrayList<Item>();
	
	@SubscribeEvent
	public static void initItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(ITEMS.toArray(new Item[0]));
	}
	
	public static Item registerItem(Item item, String name) {
		item.setRegistryName(new ResourceLocation(CozyConiferous.MOD_ID, name));
		ITEMS.add(item);
		
		return item;
	}
	
	public static class CCItemGroup extends ItemGroup {
		
		public static final CCItemGroup INSTANCE = new CCItemGroup("cozyconiferous");
		
		public CCItemGroup(String label) {
			super(label);
		}

		@Override
		public ItemStack createIcon() {
			return new ItemStack(CCBlocks.fir_sapling);
		}
	}
}
