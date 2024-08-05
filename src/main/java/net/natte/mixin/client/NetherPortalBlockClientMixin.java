package net.natte.mixin.client;

import net.minecraft.block.BlockState;
import net.minecraft.block.NetherPortalBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.natte.EndermanTweaksClient;
import net.natte.ParticleType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetherPortalBlock.class)
public abstract class NetherPortalBlockClientMixin {
    @Inject(method = "randomDisplayTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V", shift = At.Shift.BEFORE))
    private void beforeAddParticle(BlockState state, World world, BlockPos pos, Random random, CallbackInfo ci){
        EndermanTweaksClient.currentlyAddingType = ParticleType.NETHER_PORTAL;
    }

    @Inject(method = "randomDisplayTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticle(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V", shift = At.Shift.AFTER))
    private void afterAddParticle(BlockState state, World world, BlockPos pos, Random random, CallbackInfo ci){
        EndermanTweaksClient.currentlyAddingType = ParticleType.NONE;
    }
}
