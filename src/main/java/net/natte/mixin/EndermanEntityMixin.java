package net.natte.mixin;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.TagKey;
import net.natte.config.Config;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EndermanEntity.class)
public abstract class EndermanEntityMixin {

	// makes endermen waterproof
	@Inject(method = "hurtByWater()Z", at = @At("HEAD"), cancellable = true)
	private void hurtByWaterMixin(CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(Config.doesWaterHurt);
	}

	// makes endermen take damage from arrows
	@Redirect(method = "damage(Lnet/minecraft/entity/damage/DamageSource;F)Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/damage/DamageSource;isIn(Lnet/minecraft/registry/tag/TagKey;)Z"))
	private boolean isInMixin(DamageSource source, TagKey<DamageType> tag) {
		if (source.isIn(DamageTypeTags.IS_PROJECTILE)) {
			return !Config.doesProjectileHit;
		}
		return source.isIn(tag);
	}

}