package com.CGTV32.firstmod.item.custom;

import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

public class BlickyItem extends Item {

    public BlickyItem(Properties properties) {
        super(properties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        // Get the item stack in hand
        ItemStack stack = player.getItemInHand(hand);

        // Check if it's on the server side
        if (!world.isClientSide) {
            // Create the arrow entity
            Arrow arrow = new Arrow(EntityType.ARROW, world);
            arrow.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ()); // Set the position to player's eye level

            // Shoot the arrow in the direction the player is looking
            arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 100.0F, 0.0F); // Velocity and accuracy
            arrow.setNoGravity(true);
            arrow.setBaseDamage(10000);
            arrow.canChangeDimensions(world, world);


            // Add the arrow to the world
            world.addFreshEntity(arrow);

            // Play shooting sound
            world.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (world.random.nextFloat() * 0.4F + 0.8F));

            // Set a cooldown (e.g., 10 ticks = 0.5 seconds)
            player.getCooldowns().addCooldown(this, 10);
        }

        // Return success so the item works
        return InteractionResultHolder.sidedSuccess(stack, world.isClientSide());
    }

    public int getUseDuration(ItemStack stack) {
        return 72000; // High duration for continuous use
    }


}
