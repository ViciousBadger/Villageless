package net.abadger.villageless.mixin;

import net.abadger.villageless.Villageless;

import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.WoodlandMansionStructure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(WoodlandMansionStructure.class)
public abstract class WoodlandMansionStructureMixin {
    @Inject(method = "getStructurePosition", at = @At("HEAD"), cancellable = true)
    private void getStructurePositionInject(CallbackInfoReturnable<Optional<Structure.StructurePosition>> cir) {
        Villageless.LOGGER.info("Blocking woodland mansion structure");
        cir.setReturnValue(Optional.empty());
    }
}
