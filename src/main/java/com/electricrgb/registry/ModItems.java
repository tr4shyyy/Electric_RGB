package com.electricrgb.registry;

import com.electricrgb.ElectricRGB;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModItems {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ElectricRGB.MOD_ID);

    public static final RegistryObject<Item> LIGHTBULB_ITEM = registerBlockItem("lightbulb", ModBlocks.LIGHTBULB);
    public static final RegistryObject<Item> CABLE_ITEM = registerBlockItem("cable", ModBlocks.CABLE);

    private static RegistryObject<Item> registerBlockItem(String name, RegistryObject<? extends net.minecraft.world.level.block.Block> block) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(net.minecraftforge.eventbus.api.IEventBus bus) {
        ITEMS.register(bus);
    }
}
