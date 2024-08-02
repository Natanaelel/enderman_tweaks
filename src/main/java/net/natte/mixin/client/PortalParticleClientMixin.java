package net.natte.mixin.client;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.PortalParticle;
import net.minecraft.client.world.ClientWorld;
import net.natte.EndermanTweaksClient;
import net.natte.config.Config;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PortalParticle.class)
public abstract class PortalParticleClientMixin extends Particle {

    protected PortalParticleClientMixin(ClientWorld world, double x, double y, double z) {
        super(world, x, y, z);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onCreate(ClientWorld clientWorld, double x, double y, double z, double vx, double vy, double vz, CallbackInfo ci) {
        if (EndermanTweaksClient.isAddingEndermanParticle || Config.doesColorApplyToAllPortalParticles) {
            float i = this.random.nextFloat() * 0.6F + 0.4F;
            this.red = i * Config.endermanParticleRed;
            this.green = i * Config.endermanParticleGreen;
            this.blue = i * Config.endermanParticleBlue;
        }
    }
}
