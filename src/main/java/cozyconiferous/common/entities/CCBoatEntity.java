package cozyconiferous.common.entities;

import cozyconiferous.init.CCBlocks;
import cozyconiferous.init.CCEntities;
import cozyconiferous.init.CCItems;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class CCBoatEntity extends BoatEntity {

	private static final DataParameter<Integer> CC_BOAT_TYPE = EntityDataManager.defineId(CCBoatEntity.class, DataSerializers.INT);

	public CCBoatEntity(EntityType<? extends CCBoatEntity> type, World world) {
		super(type, world);
		this.blocksBuilding = true;
	}

	public CCBoatEntity(World worldIn, double x, double y, double z) {
		this(CCEntities.CC_BOAT, worldIn);
		this.setPos(x, y, z);
		this.setDeltaMovement(Vector3d.ZERO);
		this.xo = x;
		this.yo = y;
		this.zo = z;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(CC_BOAT_TYPE, CCBoatEntity.CCType.FIR.ordinal());
	}

	@Override
	public Item getDropItem() {
		switch (this.getCCBoatType()) {
		case FIR:
		default:
			return CCItems.fir_boat;
		case PINE:
			return CCItems.pine_boat;
		case REDWOOD:
			return CCItems.redwood_boat;
		}
	}

	@Override
	protected void addAdditionalSaveData(CompoundNBT compound) {
		compound.putString("Type", this.getCCBoatType().getName());
	}

	@Override
	protected void readAdditionalSaveData(CompoundNBT compound) {
		if (compound.contains("Type", 8)) {
			this.setBoatType(CCBoatEntity.CCType.getTypeFromString(compound.getString("Type")));
		}
	}

	public void setBoatType(CCBoatEntity.CCType boatType) {
		this.entityData.set(CC_BOAT_TYPE, boatType.ordinal());
	}

	public CCBoatEntity.CCType getCCBoatType() {
		return CCBoatEntity.CCType.byId(this.entityData.get(CC_BOAT_TYPE));
	}

	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	public static enum CCType {
		FIR(CCBlocks.fir_planks, "fir"),
		PINE(CCBlocks.pine_planks, "pine"),
		REDWOOD(CCBlocks.redwood_planks, "redwood");

		private final String name;
		private final Block block;

		private CCType(Block block, String name) {
			this.name = name;
			this.block = block;
		}

		public String getName() {
			return this.name;
		}

		public Block asPlank() {
			return this.block;
		}

		public String toString() {
			return this.name;
		}

		public static CCBoatEntity.CCType byId(int id) {
			CCBoatEntity.CCType[] aboatentity$type = values();
			if (id < 0 || id >= aboatentity$type.length) {
				id = 0;
			}

			return aboatentity$type[id];
		}

		public static CCBoatEntity.CCType getTypeFromString(String nameIn) {
			CCBoatEntity.CCType[] aboatentity$type = values();

			for (int i = 0; i < aboatentity$type.length; ++i) {
				if (aboatentity$type[i].getName().equals(nameIn)) {
					return aboatentity$type[i];
				}
			}

			return aboatentity$type[0];
		}
	}
}
