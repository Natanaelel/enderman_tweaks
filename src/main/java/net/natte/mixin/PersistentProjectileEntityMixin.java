package net.natte.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.natte.config.Config;

@Mixin(PersistentProjectileEntity.class)
public abstract class PersistentProjectileEntityMixin {

    // make arrows hit endermen
    @ModifyVariable(method = "onEntityHit(Lnet/minecraft/util/hit/EntityHitResult;)V", at = @At("STORE"), ordinal = 0)
    private boolean isEndermanMixin(boolean isEnderman) {
        return isEnderman && !Config.doesProjectileHit;
    }
}
