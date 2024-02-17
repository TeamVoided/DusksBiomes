package org.teamvoided.dusk_autumns_worldgen.init.structure

import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.world.gen.structure.StructureSet
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen

object DuskStructureSets {
    val DESERT_RUINS = create("desert_ruins")
    val SAND_CAVE_FOSSILS = create("sand_cave_fossils")
    fun init() {}

    fun create(id: String): RegistryKey<StructureSet> =
        RegistryKey.of(RegistryKeys.STRUCTURE_SET, DuskAutumnsWorldgen.id(id))
}