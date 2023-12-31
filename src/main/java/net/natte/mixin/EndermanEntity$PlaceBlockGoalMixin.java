package net.natte.mixin;

import net.natte.config.Config;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.entity.mob.EndermanEntity$PlaceBlockGoal")
public abstract class EndermanEntity$PlaceBlockGoalMixin {

    // enderman can't place blcoks
    @Inject(method = "canStart()Z", at = @At("HEAD"), cancellable = true)
    private void canStartMixin(CallbackInfoReturnable<Boolean> cir) {

        if (!Config.canPlaceBlocks)
            cir.setReturnValue(false);

    }
}
