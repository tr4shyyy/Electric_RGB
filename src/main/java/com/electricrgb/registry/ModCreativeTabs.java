package com.electricrgb.registry;

import com.electricrgb.ElectricRGB;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    private static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(ForgeRegistries.CREATIVE_MODE_TABS, ElectricRGB.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MAIN = TABS.register("main", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.electric_rgb"))
            .icon(() -> new ItemStack(ModItems.LIGHTBULB_ITEM.get()))
            .displayItems((params, output) -> {
                output.accept(ModItems.LIGHTBULB_ITEM.get());
                output.accept(ModItems.CABLE_ITEM.get());
            })
            .build());

    public static void register(net.minecraftforge.eventbus.api.IEventBus bus) {
        TABS.register(bus);
    }
}
