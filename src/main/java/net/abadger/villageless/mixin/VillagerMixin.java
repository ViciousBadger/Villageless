package net.abadger.villageless.mixin;

import net.abadger.villageless.Villageless;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VillagerEntity.class)
public abstract class VillagerMixin extends MerchantEntity {
    @Shadow
    abstract boolean isNatural();

    public VillagerMixin(EntityType<? extends VillagerEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void tickInject(CallbackInfo ci) {
        if (this.isNatural() && !this.isDead()) {
            this.kill();
            Villageless.LOGGER.info("Killed a villager!");
            ci.cancel();
        }
    }
}
