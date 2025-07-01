package org.teamvoided.dusks_biomes.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.WoodlandMansionGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.dusks_biomes.structure.PaleOakReplacementStructureProcessor;



@Mixin(WoodlandMansionGenerator.Piece.class)
public class WoodlandMansionGeneratorMixin {
    @ModifyReturnValue(method = "createPlacementData", at = @At("RETURN"))
    private static StructurePlacementData pale(StructurePlacementData original) {
        original.addProcessor(PaleOakReplacementStructureProcessor.INSTANCE);
        return original;
    }
}
