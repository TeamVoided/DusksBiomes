package org.teamvoided.dusk_autumns_worldgen.init.structure

import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.structure.processor.StructureProcessorList
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen

object DuskStructureProcessorLists {
    val DESERT_OBELISK_TOP_ARCHAEOLOGY = create("desert_obelisk_top_archaeology")
    val DESERT_ROADS_ARCHAEOLOGY = create("desert_roads_archaeology")
    val DESERT_SMALL_RUINS_ARCHAEOLOGY = create("desert_small_ruins_archaeology")
    val DESERT_LARGE_RUINS_ARCHAEOLOGY = create("desert_large_ruins_archaeology")
    fun init()  {}

    fun create(id: String): RegistryKey<StructureProcessorList> =
        RegistryKey.of(RegistryKeys.STRUCTURE_PROCESSOR_LIST, DuskAutumnsWorldgen.id(id))

}