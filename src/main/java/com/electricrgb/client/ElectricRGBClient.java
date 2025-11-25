package com.electricrgb.client;

import com.electricrgb.ElectricRGB;
import com.electricrgb.content.blockentity.LightbulbBlockEntity;
import com.electricrgb.registry.ModBlocks;
import com.electricrgb.registry.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ElectricRGB.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ElectricRGBClient {

    @SubscribeEvent
    public static void onBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((state, level, pos, tintIndex) -> {
            if (level != null && pos != null && level.getBlockEntity(pos) instanceof LightbulbBlockEntity be) {
                return be.getColor();
            }
            return LightbulbBlockEntity.DEFAULT_COLOR;
        }, ModBlocks.LIGHTBULB.get());
    }

    @SubscribeEvent
    public static void onItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> LightbulbBlockEntity.DEFAULT_COLOR, ModItems.LIGHTBULB_ITEM.get());
    }
}
