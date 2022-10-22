package net.abadger.villageless.mixin;

import net.abadger.villageless.Villageless;

import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.structure.JigsawStructure;
import net.minecraft.world.gen.structure.Structure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(JigsawStructure.class)
public abstract class JigsawStructureMixin {
    @Shadow
    RegistryEntry<StructurePool> startPool;

    @Inject(method = "getStructurePosition", at = @At("HEAD"), cancellable = true)
    private void getStructurePositionInject(CallbackInfoReturnable<Optional<Structure.StructurePosition>> cir) {
        if (!startPool.getKey().isEmpty()) {
            String structureName = startPool.getKey().get().getValue().toUnderscoreSeparatedString();
            if (structureName.contains("village")) {
                Villageless.LOGGER.info("Blocking structure containing string 'village': " + structureName);
                cir.setReturnValue(Optional.empty());
                return;
            }
            if (structureName.contains("pillager_outpost")) {
                Villageless.LOGGER.info("Blocking structure containing string 'pillager_outpost': " + structureName);
                cir.setReturnValue(Optional.empty());
            }
        }
    }
}
