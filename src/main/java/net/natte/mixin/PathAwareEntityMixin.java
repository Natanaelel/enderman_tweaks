package net.natte.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.WorldAccess;

// used to extend and override in EndermanEntityMixin.java
@Mixin(PathAwareEntity.class)
public class PathAwareEntityMixin {
	@Inject(method = "canSpawn(Lnet/minecraft/world/WorldAccess;Lnet/minecraft/entity/SpawnReason;)Z", at = @At("HEAD"), cancellable = true)
	public void canSpawnMixin(WorldAccess world, SpawnReason spawnReason, CallbackInfoReturnable<Boolean> cir) {

	}
}
