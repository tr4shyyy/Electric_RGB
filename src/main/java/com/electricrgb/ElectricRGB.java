package com.electricrgb;

import com.electricrgb.registry.ModBlockEntities;
import com.electricrgb.registry.ModBlocks;
import com.electricrgb.registry.ModCreativeTabs;
import com.electricrgb.registry.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.common.MinecraftForge;

@Mod(ElectricRGB.MOD_ID)
public class ElectricRGB {
    public static final String MOD_ID = "electric_rgb";

    public ElectricRGB() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.register(modBus);
        ModItems.register(modBus);
        ModBlockEntities.register(modBus);
        ModCreativeTabs.register(modBus);

        modBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Nothing to do yet; energy capability is provided by Forge by default.
    }
}
