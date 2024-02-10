package org.teamvoided.dusk_autumns_worldgen.init.structure

import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.world.gen.structure.StructureSet
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen

object DuskStructureSets {
    val DESERT_OBELISK_SET= create("desert_obelisk_set")
    fun init()  {}

    fun create(id: String): RegistryKey<StructureSet> = RegistryKey.of(RegistryKeys.STRUCTURE_SET, DuskAutumnsWorldgen.id(id))
}