package org.teamvoided.dusks_biomes.data.structure

import net.minecraft.core.registries.Registries.PROCESSOR_LIST
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList
import org.teamvoided.dusks_biomes.DusksBiomes.id

object DuskStructureProcessorLists {

    val VILLAGE_SWAMP_FARM = create("village/farm_swamp")
    val VILLAGE_SWAMP_HOUSE = create("village/house_swamp")
    val VILLAGE_SWAMP_STREET = create("village/street_swamp")
    val VILLAGE_SWAMP_ZOMBIE = create("village/zombie_swamp")
    val VILLAGE_MANGROVE_SWAMP_HOUSE = create("village/house_mangrove_swamp")
    val VILLAGE_MANGROVE_SWAMP_STREET = create("village/street_mangrove_swamp")
    val VILLAGE_MANGROVE_SWAMP_ZOMBIE = create("village/zombie_mangrove_swamp")

//    val DESERT_RUINS_OBELISK_TOP_ARCHAEOLOGY = create("desert_ruins/obelisk_top_archaeology")
//    val DESERT_RUINS_ROADS_ARCHAEOLOGY = create("desert_ruins/roads_archaeology")
//    val DESERT_RUINS_SMALL_RUINS_ARCHAEOLOGY = create("desert_ruins/small_ruins_archaeology")
//    val DESERT_RUINS_LARGE_RUINS_ARCHAEOLOGY = create("desert_ruins/large_ruins_archaeology")
//    val RED_DESERT_RUINS_OBELISK_TOP_ARCHAEOLOGY = create("red_desert_ruins/obelisk_top_archaeology")
//    val RED_DESERT_RUINS_ROADS_ARCHAEOLOGY = create("red_desert_ruins/roads_archaeology")
//    val RED_DESERT_RUINS_SMALL_RUINS_ARCHAEOLOGY = create("red_desert_ruins/small_ruins_archaeology")
//    val RED_DESERT_RUINS_LARGE_RUINS_ARCHAEOLOGY = create("red_desert_ruins/large_ruins_archaeology")

    fun create(id: String): ResourceKey<StructureProcessorList> = ResourceKey.create(PROCESSOR_LIST, id(id))
}
