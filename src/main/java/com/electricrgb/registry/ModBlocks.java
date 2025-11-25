package com.electricrgb.registry;

import com.electricrgb.ElectricRGB;
import com.electricrgb.content.CableBlock;
import com.electricrgb.content.LightbulbBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ElectricRGB.MOD_ID);

    public static final RegistryObject<Block> LIGHTBULB = BLOCKS.register("lightbulb", LightbulbBlock::new);
    public static final RegistryObject<Block> CABLE = BLOCKS.register("cable", CableBlock::new);

    public static void register(net.minecraftforge.eventbus.api.IEventBus bus) {
        BLOCKS.register(bus);
    }
}
