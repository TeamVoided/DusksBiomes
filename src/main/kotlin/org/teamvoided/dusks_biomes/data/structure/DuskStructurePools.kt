package org.teamvoided.dusks_biomes.data.structure

import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.structure.pool.StructurePool
import org.teamvoided.dusks_biomes.DusksBiomesMod

object DuskStructurePools {

    val SWAMP_VILLAGE_VILLAGERS = create("village/swamp/villagers")
    val SWAMP_VILLAGE_CENTER = create("village/swamp/town_centers")
    val SWAMP_VILLAGE_CENTER_TREE = create("village/swamp/village_tree")
    val SWAMP_VILLAGE_STREETS = create("village/swamp/streets")
    val SWAMP_VILLAGE_HOUSES = create("village/swamp/houses")
    val SWAMP_VILLAGE_DECOR = create("village/swamp/decor")
    val SWAMP_VILLAGE_TERMINATORS = create("village/swamp/terminators")
    val MANGROVE_SWAMP_VILLAGE_CENTER = create("village/mangrove_swamp/town_centers")
    val MANGROVE_SWAMP_VILLAGE_CENTER_TREE = create("village/mangrove_swamp/village_tree")
    val MANGROVE_SWAMP_VILLAGE_STREETS = create("village/mangrove_swamp/streets")
    val MANGROVE_SWAMP_VILLAGE_HOUSES = create("village/mangrove_swamp/houses")
    val MANGROVE_SWAMP_VILLAGE_DECOR = create("village/mangrove_swamp/decor")
    val MANGROVE_SWAMP_VILLAGE_TERMINATORS = create("village/mangrove_swamp/terminators")

    val SWAMP_ZOMBIE_VILLAGE_VILLAGERS = create("village/swamp/zombie/villagers")
    val SWAMP_ZOMBIE_VILLAGE_CENTER = create("village/swamp/zombie/town_centers")
    val SWAMP_ZOMBIE_VILLAGE_HOUSES = create("village/swamp/zombie/houses")
    val SWAMP_ZOMBIE_VILLAGE_DECOR = create("village/swamp/zombie/decor")
    val MANGROVE_SWAMP_ZOMBIE_VILLAGE_CENTER = create("village/mangrove_swamp/zombie/town_centers")
    val MANGROVE_SWAMP_ZOMBIE_VILLAGE_HOUSES = create("village/mangrove_swamp/zombie/houses")
    val MANGROVE_SWAMP_ZOMBIE_VILLAGE_DECOR = create("village/mangrove_swamp/zombie/decor")


//    val DESERT_RUINS_OBELISK = create("desert_ruins/obelisk")
//    val DESERT_RUINS_OBELISK_TOP = create("desert_ruins/obelisk_top")
//    val DESERT_RUINS_ROADS = create("desert_ruins/roads")
//    val DESERT_RUINS_LARGE_RUINS = create("desert_ruins/large_ruins")
//    val DESERT_RUINS_RUINS = create("desert_ruins/ruins")
//    val RED_DESERT_RUINS_OBELISK = create("red_desert_ruins/obelisk")
//    val RED_DESERT_RUINS_OBELISK_TOP = create("red_desert_ruins/obelisk_top")
//    val RED_DESERT_RUINS_ROADS = create("red_desert_ruins/roads")
//    val RED_DESERT_RUINS_LARGE_RUINS = create("red_desert_ruins/large_ruins")
//    val RED_DESERT_RUINS_RUINS = create("red_desert_ruins/ruins")

    fun create(id: String): RegistryKey<StructurePool> =
        RegistryKey.of(RegistryKeys.STRUCTURE_POOL, DusksBiomesMod.id(id))
}
