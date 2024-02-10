package org.teamvoided.dusk_autumns_worldgen.init.structure

import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.structure.pool.StructurePool
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen

object DuskStructurePools {
    val DESERT_RUINS_OBELISK = create("desert_ruins_obelisk")
    val DESERT_RUINS_OBELISK_TOP = create("desert_ruins_obelisk_top")
    val DESERT_RUINS_ROADS = create("desert_ruins_roads")
    val DESERT_RUINS_LARGE_RUINS = create("desert_ruins_large_ruins")
    val DESERT_RUINS_SMALL_RUINS = create("desert_ruins_small_ruins")
    fun init()  {}

    fun create(id: String): RegistryKey<StructurePool?> {
        return RegistryKey.of(RegistryKeys.STRUCTURE_POOL, DuskAutumnsWorldgen.id(id))
    }

}