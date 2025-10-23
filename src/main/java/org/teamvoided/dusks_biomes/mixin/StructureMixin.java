package org.teamvoided.dusks_biomes.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.pieces.PiecesContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static org.teamvoided.dusks_biomes.util.Utils.modifyPiecesKt;


@Mixin(Structure.class)
public class StructureMixin {

    @ModifyExpressionValue(method = "generate", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/structure/pieces/StructurePiecesBuilder;build()Lnet/minecraft/world/level/levelgen/structure/pieces/PiecesContainer;"))
    PiecesContainer modifyPieces(PiecesContainer original) {
        return modifyPiecesKt(original);
    }
}
