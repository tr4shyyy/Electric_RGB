package com.electricrgb.registry;

import com.electricrgb.ElectricRGB;
import com.electricrgb.content.blockentity.CableBlockEntity;
import com.electricrgb.content.blockentity.LightbulbBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ElectricRGB.MOD_ID);

    public static final RegistryObject<BlockEntityType<LightbulbBlockEntity>> LIGHTBULB =
            BLOCK_ENTITIES.register("lightbulb", () -> BlockEntityType.Builder.of(LightbulbBlockEntity::new, ModBlocks.LIGHTBULB.get()).build(null));

    public static final RegistryObject<BlockEntityType<CableBlockEntity>> CABLE =
            BLOCK_ENTITIES.register("cable", () -> BlockEntityType.Builder.of(CableBlockEntity::new, ModBlocks.CABLE.get()).build(null));

    public static void register(net.minecraftforge.eventbus.api.IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }
}
