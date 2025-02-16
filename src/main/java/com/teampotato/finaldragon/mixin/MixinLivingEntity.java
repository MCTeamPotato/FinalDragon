package com.teampotato.finaldragon.mixin;

import com.teampotato.finaldragon.FinalDragon;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {
    @Inject(method = "getDamageAfterArmorAbsorb", at = @At(value = "HEAD"), cancellable = true)
    private void onAbsorb(DamageSource source, float amount, CallbackInfoReturnable<Float> cir) {
        if (source.getEntity() instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) source.getEntity();
            if (FinalDragon.entityDontHaveFinalPower(livingEntity)) return;
            cir.setReturnValue(amount);
        }
    }

    @Inject(method = "isDamageSourceBlocked", at = @At("HEAD"), cancellable = true)
    private void onCheckShield(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        if (!FinalDragon.Config.DISABLE_SHIELD.get()) return;
        if (source.getEntity() instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) source.getEntity();
            if (FinalDragon.entityDontHaveFinalPower(livingEntity)) return;
            cir.setReturnValue(false);
        }
    }
}
