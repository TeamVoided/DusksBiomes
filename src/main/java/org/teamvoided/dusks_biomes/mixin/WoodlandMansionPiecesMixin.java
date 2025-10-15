package org.teamvoided.dusks_biomes.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.levelgen.structure.structures.WoodlandMansionPieces;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.teamvoided.dusks_biomes.structure.BlockReplacementStructureProcessor;


@Mixin(WoodlandMansionPieces.WoodlandMansionPiece.class)
public class WoodlandMansionPiecesMixin {
    @ModifyReturnValue(method = "makeSettings", at = @At("RETURN"))
    private static StructurePlaceSettings pale(StructurePlaceSettings original) {
        return original.addProcessor(BlockReplacementStructureProcessor.PALE_OAK_REPLACE);
    }
}
