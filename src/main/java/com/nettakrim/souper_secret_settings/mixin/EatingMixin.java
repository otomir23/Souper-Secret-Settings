package com.nettakrim.souper_secret_settings.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.nettakrim.souper_secret_settings.RandomSound;
import com.nettakrim.souper_secret_settings.SouperSecretSettingsClient;

@Mixin(LivingEntity.class)
public abstract class EatingMixin extends Entity {
	@Shadow
	public abstract ItemStack getActiveItem();

	@Inject(at = @At("HEAD"), method = "consumeItem")
	private void finishUsing(CallbackInfo ci) {
		if (getWorld().isClient && getActiveItem().isOf(Items.BEETROOT_SOUP) && isLogicalSideForUpdatingMovement()) {
			SouperSecretSettingsClient.setShader("random_soup");
			RandomSound.Play();
		}
	}

	public EatingMixin(EntityType<?> type, World world) {super(type, world);}
}
