package net.abadger.villageless.mixin;

import net.abadger.villageless.Villageless;

import net.minecraft.world.spawner.PatrolSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PatrolSpawner.class)
public abstract class PatrolSpawnerMixin {

    @Inject(method = "spawn", at = @At("HEAD"), cancellable = true)
    private void spawnInject(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(0);
    }
}
