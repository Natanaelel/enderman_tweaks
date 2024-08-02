package net.natte.mixin.client;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.world.World;
import net.natte.EndermanTweaksClient;
import net.natte.config.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndermanEntity.class)
public abstract class EndermanEntityClientMixin {

    @WrapWithCondition(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"))
    private boolean shouldAddParticle(World instance, ParticleEffect parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ){
        return Config.doesEndermanEmitParticles;
    }

    @Inject(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V", shift = At.Shift.BEFORE))
    private void beforeAddParticle(CallbackInfo ci){
        EndermanTweaksClient.isAddingEndermanParticle = true;
    }

    @Inject(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V", shift = At.Shift.AFTER))
    private void afterAddParticle(CallbackInfo ci){
        EndermanTweaksClient.isAddingEndermanParticle = false;
    }
}
