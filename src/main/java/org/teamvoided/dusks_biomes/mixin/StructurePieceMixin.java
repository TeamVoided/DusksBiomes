package org.teamvoided.dusks_biomes.mixin;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(StructurePiece.class)
public class StructurePieceMixin {

    @Unique
    private static ResourceKey<Structure> reef$structureRefrence = null;
}
