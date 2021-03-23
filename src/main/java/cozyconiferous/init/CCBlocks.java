package cozyconiferous.init;

import java.util.ArrayList;
import java.util.function.Supplier;

import cozyconiferous.common.blocks.trees.FirTree;
import cozyconiferous.common.blocks.trees.PineTree;
import cozyconiferous.common.blocks.trees.RedwoodTree;
import cozyconiferous.core.CozyConiferous;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TrapDoorBlock;
import net.minecraft.block.WoodButtonBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = CozyConiferous.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class CCBlocks {
	public static ArrayList<Block> BLOCKS = new ArrayList<Block>();

	public static Block fir_sapling, fir_log, fir_leaves, fir_planks, stripped_fir_log, fir_wood, stripped_fir_wood,
			fir_sign, fir_wall_sign, fir_pressure_plate, fir_trapdoor, fir_button, fir_slab, fir_fence_gate, fir_fence,
			fir_door, fir_stairs;

	public static Block pine_sapling, pine_log, pine_leaves, pine_planks, stripped_pine_log, pine_wood,
			stripped_pine_wood, pine_sign, pine_wall_sign, pine_pressure_plate, pine_trapdoor, pine_button, pine_slab,
			pine_fence_gate, pine_fence, pine_door, pine_stairs;

	public static Block redwood_sapling, redwood_log, redwood_leaves, redwood_planks, stripped_redwood_log,
			redwood_wood, stripped_redwood_wood, redwood_sign, redwood_wall_sign, redwood_pressure_plate,
			redwood_trapdoor, redwood_button, redwood_slab, redwood_fence_gate, redwood_fence, redwood_door,
			redwood_stairs;

	@SubscribeEvent
	public static void initBlocks(RegistryEvent.Register<Block> event) {

		fir_sapling = registerBlockWithFuel(new SaplingBlock(new FirTree(), Properties.from(Blocks.OAK_SAPLING)), "fir_sapling",
				100);
		fir_log = registerBlockWithFuel(createLogBlock(MaterialColor.WOOD, MaterialColor.BROWN, () -> stripped_fir_log), "fir_log", 300);
		fir_leaves = registerBlock(new LeavesBlock(Properties.from(Blocks.OAK_LEAVES)), "fir_leaves");
		fir_wood = registerBlockWithFuel(createLogBlock(MaterialColor.BROWN, MaterialColor.BROWN, () -> stripped_fir_wood), "fir_wood", 300);
		fir_planks = registerBlockWithFuel(createPlanksBlock(), "fir_planks", 300);
		stripped_fir_log = registerBlockWithFuel(createLogBlock(MaterialColor.WOOD, MaterialColor.WOOD, null),
				"stripped_fir_log", 300);
		stripped_fir_wood = registerBlockWithFuel(createLogBlock(MaterialColor.WOOD, MaterialColor.WOOD, null),
				"stripped_fir_wood", 300);
		fir_pressure_plate = registerBlockWithFuel(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
				AbstractBlock.Properties.create(Material.WOOD, fir_planks.getMaterialColor()).doesNotBlockMovement()
						.hardnessAndResistance(0.5F).sound(SoundType.WOOD)),
				"fir_pressure_plate", 300);
		fir_trapdoor = registerBlockWithFuel(new TrapDoorBlock(Properties.from(Blocks.OAK_TRAPDOOR)), "fir_trapdoor",
				300);
		fir_button = registerBlockWithFuel(new WoodButtonBlock(Properties.from(Blocks.OAK_BUTTON)), "fir_button", 100);
		fir_slab = registerBlockWithFuel(new SlabBlock(Properties.from(Blocks.OAK_SLAB)), "fir_slab", 150);
		fir_fence_gate = registerBlockWithFuel(new FenceGateBlock(Properties.from(Blocks.SPRUCE_FENCE_GATE)),
				"fir_fence_gate", 300);
		fir_fence = registerBlockWithFuel(new FenceBlock(Properties.from(Blocks.SPRUCE_FENCE)), "fir_fence", 300);
		fir_door = registerBlockWithFuel(new DoorBlock(Properties.from(Blocks.SPRUCE_DOOR)), "fir_door", 200);
		fir_stairs = registerBlockWithFuel(
				new StairsBlock(() -> fir_planks.getDefaultState(), Properties.from(Blocks.SPRUCE_STAIRS)),
				"fir_stairs", 300);
		
		pine_sapling = registerBlockWithFuel(new SaplingBlock(new PineTree(), Properties.from(Blocks.OAK_SAPLING)), "pine_sapling",
				100);
		pine_log = registerBlockWithFuel(createLogBlock(MaterialColor.WOOD, MaterialColor.BROWN, () -> stripped_pine_log), "pine_log", 300);
		pine_leaves = registerBlock(new LeavesBlock(Properties.from(Blocks.OAK_LEAVES)), "pine_leaves");
		pine_wood = registerBlockWithFuel(createLogBlock(MaterialColor.BROWN, MaterialColor.BROWN, () -> stripped_pine_wood), "pine_wood", 300);
		pine_planks = registerBlockWithFuel(createPlanksBlock(), "pine_planks", 300);
		stripped_pine_log = registerBlockWithFuel(createLogBlock(MaterialColor.WOOD, MaterialColor.WOOD, null),
				"stripped_pine_log", 300);
		stripped_pine_wood = registerBlockWithFuel(createLogBlock(MaterialColor.WOOD, MaterialColor.WOOD, null),
				"stripped_pine_wood", 300);
		pine_pressure_plate = registerBlockWithFuel(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
				AbstractBlock.Properties.create(Material.WOOD, pine_planks.getMaterialColor()).doesNotBlockMovement()
						.hardnessAndResistance(0.5F).sound(SoundType.WOOD)),
				"pine_pressure_plate", 300);
		pine_trapdoor = registerBlockWithFuel(new TrapDoorBlock(Properties.from(Blocks.OAK_TRAPDOOR)), "pine_trapdoor",
				300);
		pine_button = registerBlockWithFuel(new WoodButtonBlock(Properties.from(Blocks.OAK_BUTTON)), "pine_button", 100);
		pine_slab = registerBlockWithFuel(new SlabBlock(Properties.from(Blocks.OAK_SLAB)), "pine_slab", 150);
		pine_fence_gate = registerBlockWithFuel(new FenceGateBlock(Properties.from(Blocks.SPRUCE_FENCE_GATE)),
				"pine_fence_gate", 300);
		pine_fence = registerBlockWithFuel(new FenceBlock(Properties.from(Blocks.SPRUCE_FENCE)), "pine_fence", 300);
		pine_door = registerBlockWithFuel(new DoorBlock(Properties.from(Blocks.SPRUCE_DOOR)), "pine_door", 200);
		pine_stairs = registerBlockWithFuel(
				new StairsBlock(() -> pine_planks.getDefaultState(), Properties.from(Blocks.SPRUCE_STAIRS)),
				"pine_stairs", 300);
		
		redwood_sapling = registerBlockWithFuel(new SaplingBlock(new RedwoodTree(), Properties.from(Blocks.OAK_SAPLING)), "redwood_sapling",
				100);
		redwood_log = registerBlockWithFuel(createLogBlock(MaterialColor.WOOD, MaterialColor.BROWN, () -> stripped_redwood_log), "redwood_log", 300);
		redwood_leaves = registerBlock(new LeavesBlock(Properties.from(Blocks.OAK_LEAVES)), "redwood_leaves");
		redwood_wood = registerBlockWithFuel(createLogBlock(MaterialColor.BROWN, MaterialColor.BROWN, () -> stripped_redwood_wood), "redwood_wood", 300);
		redwood_planks = registerBlockWithFuel(createPlanksBlock(), "redwood_planks", 300);
		stripped_redwood_log = registerBlockWithFuel(createLogBlock(MaterialColor.WOOD, MaterialColor.WOOD, null),
				"stripped_redwood_log", 300);
		stripped_redwood_wood = registerBlockWithFuel(createLogBlock(MaterialColor.WOOD, MaterialColor.WOOD, null),
				"stripped_redwood_wood", 300);
		redwood_pressure_plate = registerBlockWithFuel(new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
				AbstractBlock.Properties.create(Material.WOOD, redwood_planks.getMaterialColor()).doesNotBlockMovement()
						.hardnessAndResistance(0.5F).sound(SoundType.WOOD)),
				"redwood_pressure_plate", 300);
		redwood_trapdoor = registerBlockWithFuel(new TrapDoorBlock(Properties.from(Blocks.OAK_TRAPDOOR)), "redwood_trapdoor",
				300);
		redwood_button = registerBlockWithFuel(new WoodButtonBlock(Properties.from(Blocks.OAK_BUTTON)), "redwood_button", 100);
		redwood_slab = registerBlockWithFuel(new SlabBlock(Properties.from(Blocks.OAK_SLAB)), "redwood_slab", 150);
		redwood_fence_gate = registerBlockWithFuel(new FenceGateBlock(Properties.from(Blocks.SPRUCE_FENCE_GATE)),
				"redwood_fence_gate", 300);
		redwood_fence = registerBlockWithFuel(new FenceBlock(Properties.from(Blocks.SPRUCE_FENCE)), "redwood_fence", 300);
		redwood_door = registerBlockWithFuel(new DoorBlock(Properties.from(Blocks.SPRUCE_DOOR)), "redwood_door", 200);
		redwood_stairs = registerBlockWithFuel(
				new StairsBlock(() -> redwood_planks.getDefaultState(), Properties.from(Blocks.SPRUCE_STAIRS)),
				"redwood_stairs", 300);
		
		BLOCKS.forEach((block) -> event.getRegistry().register(block));
	}

	public static Block registerBlock(Block block, String name) {
		block.setRegistryName(new ResourceLocation(CozyConiferous.MOD_ID, name));

		Item.Properties prop = new Item.Properties().group(CCItems.CCItemGroup.INSTANCE);
		BlockItem item = new BlockItem(block, prop);
		item.setRegistryName(new ResourceLocation(CozyConiferous.MOD_ID, name));

		BLOCKS.add(block);
		CCItems.ITEMS.add(item);

		return block;
	}

	public static Block registerBlockWithoutItem(Block block, String name) {
		block.setRegistryName(new ResourceLocation(CozyConiferous.MOD_ID, name));
		BLOCKS.add(block);

		return block;
	}

	public static Block registerBlockWithFuel(Block block, String name, int burnTime) {
		block.setRegistryName(new ResourceLocation(CozyConiferous.MOD_ID, name));

		Item.Properties prop = new Item.Properties().group(CCItems.CCItemGroup.INSTANCE);
		BlockItem item = new BlockItem(block, prop) {
			@Override
			public int getBurnTime(ItemStack stack) {
				return burnTime;
			}
		};
		item.setRegistryName(new ResourceLocation(CozyConiferous.MOD_ID, name));

		BLOCKS.add(block);
		CCItems.ITEMS.add(item);

		return block;
	}

	public static RotatedPillarBlock createLogBlock(MaterialColor topColor, MaterialColor barkColor, Supplier<Block> stripped) {
		return new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD, (state) -> {
			return state.get(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topColor : barkColor;
		}).hardnessAndResistance(2.0F).sound(SoundType.WOOD)) {
			@Override
			public BlockState getToolModifiedState(BlockState state, World world, BlockPos pos, PlayerEntity player, ItemStack stack, ToolType toolType) {
				if(toolType == ToolType.AXE)
					return stripped.get().getDefaultState().with(RotatedPillarBlock.AXIS, state.get(RotatedPillarBlock.AXIS));
				return super.getToolModifiedState(state, world, pos, player, stack, toolType);
			}
		};
	}

	public static Block createPlanksBlock() {
		return new Block(Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F)
				.sound(SoundType.WOOD));
	}
}
