package com.electricrgb.content.blockentity;

import com.electricrgb.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.electricrgb.util.SimpleEnergyStorage;

public class CableBlockEntity extends BlockEntity {
    private static final int CAPACITY = 16000;
    private static final int MAX_TRANSFER = 400;

    private final SimpleEnergyStorage energy = new SimpleEnergyStorage(CAPACITY, MAX_TRANSFER, MAX_TRANSFER) {
        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            int received = super.receiveEnergy(maxReceive, simulate);
            if (received > 0 && !simulate) {
                setChanged();
            }
            return received;
        }

        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            int extracted = super.extractEnergy(maxExtract, simulate);
            if (extracted > 0 && !simulate) {
                setChanged();
            }
            return extracted;
        }
    };

    private final LazyOptional<IEnergyStorage> energyCapability = LazyOptional.of(() -> energy);

    public CableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CABLE.get(), pos, state);
    }

    public static void tickServer(Level level, BlockPos pos, BlockState state, CableBlockEntity blockEntity) {
        for (Direction direction : Direction.values()) {
            blockEntity.pushEnergy(level, pos.relative(direction), direction.getOpposite());
        }
    }

    private void pushEnergy(Level level, BlockPos targetPos, Direction incomingDirection) {
        BlockEntity targetEntity = level.getBlockEntity(targetPos);
        if (targetEntity == null) {
            return;
        }

        LazyOptional<IEnergyStorage> targetEnergy = targetEntity.getCapability(ForgeCapabilities.ENERGY, incomingDirection);
        targetEnergy.ifPresent(storage -> {
            int available = energy.extractEnergy(MAX_TRANSFER, true);
            if (available > 0) {
                int accepted = storage.receiveEnergy(available, false);
                if (accepted > 0) {
                    energy.extractEnergy(accepted, false);
                }
            }
        });
    }

    @Override
    protected void saveAdditional(net.minecraft.nbt.CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("Energy", energy.getEnergyStored());
    }

    @Override
    public void load(net.minecraft.nbt.CompoundTag tag) {
        super.load(tag);
        energy.setEnergy(tag.getInt("Energy"));
    }

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ENERGY) {
            return energyCapability.cast();
        }
        return super.getCapability(cap, side);
    }

    public void invalidateCapabilities() {
        energyCapability.invalidate();
    }
}
