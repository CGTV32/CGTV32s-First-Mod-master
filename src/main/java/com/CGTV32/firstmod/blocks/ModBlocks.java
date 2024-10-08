package com.CGTV32.firstmod.blocks;

import com.CGTV32.firstmod.FirstMod;
import com.CGTV32.firstmod.item.ModItems;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, FirstMod.MOD_ID);

    public static final RegistryObject<Block> OPPITE_BLOCK = registerBlock("oppite_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(5f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.ANVIL)));

    public static final RegistryObject<Block> RAW_OPPITE_BLOCK = registerBlock("raw_oppite_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(5f)
                    .requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> OPPITE_ORE = registerBlock("oppite_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.of()
                    .strength(5f)
                    .requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> OBAMIUM_BLOCK = registerBlock("obamium_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(5f)
                    .requiresCorrectToolForDrops()));



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }


    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
