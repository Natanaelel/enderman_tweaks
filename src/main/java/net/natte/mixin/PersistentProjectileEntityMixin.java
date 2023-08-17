package net.natte.mixin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.At;


import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.natte.config.Config;

@Mixin(PersistentProjectileEntity.class)
public abstract class PersistentProjectileEntityMixin {
    
    // make arrows hit endermen
    @ModifyVariable(method = "onEntityHit(Lnet/minecraft/util/hit/EntityHitResult;)V", at = @At("STORE"), ordinal = 0 )
    private boolean isEndermanMixin(boolean isEnderman){
        Logger LOGGER = LoggerFactory.getLogger("enderman_tweaks"); // TODO: remove
        LOGGER.info("prevented enderman hit?"); // TODO: remove
        return isEnderman && !Config.doesProjectileHit;
    }
    // TODO: remove
    // @Inject(method = "onEntityHit(Lnet/minecraft/util/hit/EntityHitResult;)V", at = @At("HEAD"))
    // void log(CallbackInfo ci){
    //     Logger LOGGER = LoggerFactory.getLogger("enderman_tweaks");
    //     LOGGER.info("onEntityHit");
    // }
}
