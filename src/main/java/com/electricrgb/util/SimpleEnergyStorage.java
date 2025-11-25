package com.electricrgb.util;

import net.minecraftforge.energy.EnergyStorage;

public class SimpleEnergyStorage extends EnergyStorage {
    public SimpleEnergyStorage(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }

    public void setEnergy(int energy) {
        this.energy = Math.max(0, Math.min(energy, getMaxEnergyStored()));
    }
}
