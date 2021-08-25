package cozyconiferous.init;

import java.util.ArrayList;
import java.util.List;

import cozyconiferous.common.entities.CCBoatEntity;
import cozyconiferous.core.CozyConiferous;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = CozyConiferous.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class CCEntities {

	public static final List<EntityType<?>> ENTITIES = new ArrayList<EntityType<?>>();
	public static final EntityType<CCBoatEntity> CC_BOAT = registerEntity(EntityType.Builder.<CCBoatEntity>of(CCBoatEntity::new, EntityClassification.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10), "cc_boat");
	
	public static <T extends Entity> EntityType<T> registerEntity(EntityType.Builder<?> builder, String name) {
		EntityType<T> entity = (EntityType<T>) builder.build(name).setRegistryName(new ResourceLocation(CozyConiferous.MOD_ID, name));
		ENTITIES.add(entity);

		return entity;
	}
	
	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
		ENTITIES.forEach((e) -> event.getRegistry().register(e));
	}
}
