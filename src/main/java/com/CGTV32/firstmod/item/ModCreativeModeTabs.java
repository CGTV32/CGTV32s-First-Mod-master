package com.CGTV32.firstmod.item;

import com.CGTV32.firstmod.FirstMod;
import com.CGTV32.firstmod.blocks.ModBlocks;
import net.minecraft.client.tutorial.Tutorial;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
        DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FirstMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> OPP_TAB = CREATIVE_MODE_TABS.register("opp_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.OPPITE.get()))
                    .title(Component.translatable("creativetab.firstmod.opptab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.OPPITE.get());
                        output.accept(ModItems.RAW_OPPITE.get());
                    }).build());


    public static final RegistryObject<CreativeModeTab> OPP_TAB_BLOCKS = CREATIVE_MODE_TABS.register("opp_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.OPPITE_BLOCK.get()))
                    .withTabsBefore(OPP_TAB.getId())
                    .title(Component.translatable("creativetab.firstmod.oppblockstab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.OPPITE_BLOCK.get());
                        output.accept(ModBlocks.RAW_OPPITE_BLOCK.get());
                        output.accept(ModBlocks.OPPITE_ORE.get());
                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
