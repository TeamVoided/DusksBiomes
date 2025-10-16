package org.teamvoided.dusks_biomes.data.structure

import net.minecraft.core.registries.Registries.STRUCTURE_SET
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.structure.StructureSet
import org.teamvoided.dusks_biomes.DusksBiomes.id

object DuskStructureSets {
//    val DESERT_RUINS = create("desert_ruins")
//    val SAND_CAVE_FOSSILS = create("sand_cave_fossils")

    fun create(id: String): ResourceKey<StructureSet> = ResourceKey.create(STRUCTURE_SET, id(id))
}
