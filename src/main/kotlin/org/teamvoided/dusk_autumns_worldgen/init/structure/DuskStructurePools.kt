package org.teamvoided.dusk_autumns_worldgen.init.structure

import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.structure.pool.StructurePool
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen

object DuskStructurePools {
    val DESERT_RUINS_OBELISK = create("desert_ruins/obelisk")
    val DESERT_RUINS_OBELISK_TOP = create("desert_ruins/obelisk_top")
    val DESERT_RUINS_ROADS = create("desert_ruins/roads")
    val DESERT_RUINS_LARGE_RUINS = create("desert_ruins/large_ruins")
    val DESERT_RUINS_RUINS = create("desert_ruins/ruins")
    val RED_DESERT_RUINS_OBELISK = create("red_desert_ruins/obelisk")
    val RED_DESERT_RUINS_OBELISK_TOP = create("red_desert_ruins/obelisk_top")
    val RED_DESERT_RUINS_ROADS = create("red_desert_ruins/roads")
    val RED_DESERT_RUINS_LARGE_RUINS = create("red_desert_ruins/large_ruins")
    val RED_DESERT_RUINS_RUINS = create("red_desert_ruins/ruins")
    fun init()  {}

    fun create(id: String): RegistryKey<StructurePool?> {
        return RegistryKey.of(RegistryKeys.STRUCTURE_POOL, DuskAutumnsWorldgen.id(id))
    }

}