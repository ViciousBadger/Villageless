package net.abadger.villageless.mixin;

import net.abadger.villageless.Villageless;

import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.WanderingTraderManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WanderingTraderManager.class)
public class WanderingTraderManagerMixin {
    @Inject(method = "spawn", at = @At("HEAD"), cancellable = true)
    void blockWanderingTraders(ServerWorld world, boolean spawnMonsters, boolean spawnAnimals, CallbackInfoReturnable<Integer> cir) {
        Villageless.LOGGER.info("Prevented a wandering trader from spawning!");
        cir.setReturnValue(0);
    }
}
