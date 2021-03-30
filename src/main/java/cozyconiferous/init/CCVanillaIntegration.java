package cozyconiferous.init;

import net.minecraft.block.ComposterBlock;
import net.minecraft.util.IItemProvider;

public class CCVanillaIntegration {
	
	public static void init() {
		setCompostInfo(CCBlocks.fir_leaves, 0.3F);
		setCompostInfo(CCBlocks.pine_leaves, 0.3F);
		setCompostInfo(CCBlocks.redwood_leaves, 0.3F);
		setCompostInfo(CCBlocks.fir_sapling, 0.3F);
		setCompostInfo(CCBlocks.pine_sapling, 0.3F);
		setCompostInfo(CCBlocks.redwood_sapling, 0.3F);
	}
	
	public static void setCompostInfo(IItemProvider item, float chance) {
		ComposterBlock.CHANCES.put(item.asItem(), chance);
	}
}
