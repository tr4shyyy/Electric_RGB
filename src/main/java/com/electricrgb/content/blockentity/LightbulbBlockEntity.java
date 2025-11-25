package com.electricrgb.content.blockentity;

import com.electricrgb.content.LightbulbBlock;
import com.electricrgb.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraft.world.item.DyeColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.electricrgb.util.SimpleEnergyStorage;

public class LightbulbBlockEntity extends BlockEntity {
    public static final int DEFAULT_COLOR = 0xFFFFFF;
    private static final int CAPACITY = 8000;
    private static final int MAX_RECEIVE = 200;
    private static final int CONSUMPTION_PER_TICK = 20;

    private final SimpleEnergyStorage energy = new SimpleEnergyStorage(CAPACITY, MAX_RECEIVE, 0) {
        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            int received = super.receiveEnergy(maxReceive, simulate);
            if (received > 0 && !simulate) {
                setChanged();
            }
            return received;
        }
    };

    private final LazyOptional<IEnergyStorage> energyCapability = LazyOptional.of(() -> energy);
    private int color = DEFAULT_COLOR;

    public LightbulbBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.LIGHTBULB.get(), pos, state);
    }

    public static void tickServer(Level level, BlockPos pos, BlockState state, LightbulbBlockEntity blockEntity) {
        boolean wasLit = state.getValue(LightbulbBlock.LIT);
        boolean shouldBeLit = false;

        if (blockEntity.energy.getEnergyStored() >= CONSUMPTION_PER_TICK) {
            blockEntity.energy.extractEnergy(CONSUMPTION_PER_TICK, false);
            shouldBeLit = true;
        }

        if (wasLit != shouldBeLit) {
            BlockState updated = state.setValue(LightbulbBlock.LIT, shouldBeLit);
            level.setBlock(pos, updated, 3);
        }

        if (shouldBeLit) {
            blockEntity.setChanged();
        }
    }

    public void setColor(DyeColor dyeColor) {
        this.color = dyeColor.getTextColor();
        setChanged();
        if (level != null) {
            BlockState state = getBlockState();
            level.sendBlockUpdated(worldPosition, state, state, 3);
        }
    }

    public int getColor() {
        return color;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("Energy", energy.getEnergyStored());
        tag.putInt("Color", color);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        color = tag.getInt("Color");
        int stored = tag.getInt("Energy");
        energy.setEnergy(stored);
    }

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable net.minecraft.core.Direction side) {
        if (cap == ForgeCapabilities.ENERGY) {
            return energyCapability.cast();
        }
        return super.getCapability(cap, side);
    }

    public void invalidateCapabilities() {
        energyCapability.invalidate();
    }
}
