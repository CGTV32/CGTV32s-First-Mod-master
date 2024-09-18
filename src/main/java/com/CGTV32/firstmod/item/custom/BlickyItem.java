package com.CGTV32.firstmod.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;

public class BlickyItem extends Item {

    public BlickyItem(Properties properties) {
        super(properties);
    }

    @Override
    public void onUseTick(Level world, Player player, ItemStack stack, int count) {
        if (!world.isClientSide) {
            // Creating the arrow entity
            Arrow arrow = new Arrow(world, player);
            arrow.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());
            arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3.0F, 1.0F);
            world.addFreshEntity(arrow);

            // Playing the arrow shooting sound
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + 0.5F);
        }
    }

    @Override
    public InteractionResult use(Level world, Player player, InteractionHand hand) {
        player.startUsingItem(hand);
        return InteractionResult.sidedSuccess(world.isClientSide);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000; // High duration for continuous use
    }
}
