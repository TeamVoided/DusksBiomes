package org.teamvoided.dusks_biomes.data.structure

import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.world.gen.structure.StructureSet
import org.teamvoided.dusks_biomes.DusksBiomesMod

object DuskStructureSets {
//    val DESERT_RUINS = create("desert_ruins")
//    val SAND_CAVE_FOSSILS = create("sand_cave_fossils")

    fun create(id: String): RegistryKey<StructureSet> =
        RegistryKey.of(RegistryKeys.STRUCTURE_SET, DusksBiomesMod.id(id))
}
