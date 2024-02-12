package org.teamvoided.dusk_autumns_worldgen.init.structure

import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.structure.processor.StructureProcessorList
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen

object DuskStructureProcessorLists {
    val DESERT_RUINS_OBELISK_TOP_ARCHAEOLOGY = create("desert_ruins/obelisk_top_archaeology")
    val DESERT_RUINS_ROADS_ARCHAEOLOGY = create("desert_ruins/roads_archaeology")
    val DESERT_RUINS_SMALL_RUINS_ARCHAEOLOGY = create("desert_ruins/small_ruins_archaeology")
    val DESERT_RUINS_LARGE_RUINS_ARCHAEOLOGY = create("desert_ruins/large_ruins_archaeology")
    val RED_DESERT_RUINS_OBELISK_TOP_ARCHAEOLOGY = create("red_desert_ruins/obelisk_top_archaeology")
    val RED_DESERT_RUINS_ROADS_ARCHAEOLOGY = create("red_desert_ruins/roads_archaeology")
    val RED_DESERT_RUINS_SMALL_RUINS_ARCHAEOLOGY = create("red_desert_ruins/small_ruins_archaeology")
    val RED_DESERT_RUINS_LARGE_RUINS_ARCHAEOLOGY = create("red_desert_ruins/large_ruins_archaeology")
    fun init()  {}

    fun create(id: String): RegistryKey<StructureProcessorList> =
        RegistryKey.of(RegistryKeys.STRUCTURE_PROCESSOR_LIST, DuskAutumnsWorldgen.id(id))

}