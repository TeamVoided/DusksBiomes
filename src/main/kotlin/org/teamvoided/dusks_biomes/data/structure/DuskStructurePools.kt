package org.teamvoided.dusks_biomes.data.structure

import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.structure.pool.StructurePool
import org.teamvoided.dusks_biomes.DusksBiomesMod

object DuskStructurePools {
    val PALE_MANOR_FOUNDATION = paleManor("foundation")
    val PALE_MANOR_FOYER = paleManor("foyer")
    val PALE_MANOR_STAIRWELL = paleManor("stairwell")
    val PALE_MANOR_HALLWAY = paleManor("hallway")
    val PALE_MANOR_ROOM = paleManor("room")
    val PALE_MANOR_ROOM_WALL = paleManor("room_wall")
    val PALE_MANOR_WINDOW = paleManor("window")
    val PALE_MANOR_WINDOW_AWNING = paleManor("window_awning")
    val PALE_MANOR_ATTIC_ROOM = paleManor("attic_room")
    val PALE_MANOR_ATTIC_END = paleManor("attic_end")
    val PALE_MANOR_ATTIC_WALL = paleManor("attic_wall")

    val PALE_MANOR_ROOM_BEDROOM = paleManorRoom("bedroom")
    val PALE_MANOR_ROOM_OFFICE = paleManorRoom("office")
    val PALE_MANOR_ROOM_GARDEN = paleManorRoom("garden")
    val PALE_MANOR_ROOM_CHEST = paleManorRoom("chest")
    val PALE_MANOR_ROOM_MISC = paleManorRoom("misc")
    val PALE_MANOR_ROOM_SECRET = paleManorRoom("secret")
    val PALE_MANOR_ROOM_TRAP = paleManorRoom("trap")

    val SWAMP_VILLAGE_VILLAGERS = villageSwamp("villagers")
    val SWAMP_VILLAGE_CENTER = villageSwamp("town_centers")
    val SWAMP_VILLAGE_CENTER_TREE = villageSwamp("village_tree")
    val SWAMP_VILLAGE_STREETS = villageSwamp("streets")
    val SWAMP_VILLAGE_HOUSES = villageSwamp("houses")
    val SWAMP_VILLAGE_DECOR = villageSwamp("decor")
    val SWAMP_VILLAGE_TERMINATORS = villageSwamp("terminators")
    val MANGROVE_SWAMP_VILLAGE_CENTER = villageMangrove("town_centers")
    val MANGROVE_SWAMP_VILLAGE_CENTER_TREE = villageMangrove("village_tree")
    val MANGROVE_SWAMP_VILLAGE_STREETS = villageMangrove("streets")
    val MANGROVE_SWAMP_VILLAGE_HOUSES = villageMangrove("houses")
    val MANGROVE_SWAMP_VILLAGE_DECOR = villageMangrove("decor")
    val MANGROVE_SWAMP_VILLAGE_TERMINATORS = villageMangrove("terminators")

    val SWAMP_ZOMBIE_VILLAGE_VILLAGERS = villageSwamp("zombie/villagers")
    val SWAMP_ZOMBIE_VILLAGE_CENTER = villageSwamp("zombie/town_centers")
    val SWAMP_ZOMBIE_VILLAGE_HOUSES = villageSwamp("zombie/houses")
    val SWAMP_ZOMBIE_VILLAGE_DECOR = villageSwamp("zombie/decor")
    val MANGROVE_SWAMP_ZOMBIE_VILLAGE_CENTER = villageMangrove("zombie/town_centers")
    val MANGROVE_SWAMP_ZOMBIE_VILLAGE_HOUSES = villageMangrove("zombie/houses")
    val MANGROVE_SWAMP_ZOMBIE_VILLAGE_DECOR = villageMangrove("zombie/decor")


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


    private fun villageSwamp(id: String): RegistryKey<StructurePool> = create("village/swamp/$id")
    private fun villageMangrove(id: String): RegistryKey<StructurePool> = create("village/mangrove_swamp/$id")
    private fun paleManor(id: String): RegistryKey<StructurePool> = create("pale_manor/$id")
    private fun paleManorRoom(id: String): RegistryKey<StructurePool> = create("pale_manor/room/$id")
    private fun create(id: String): RegistryKey<StructurePool> =
        RegistryKey.of(RegistryKeys.TEMPLATE_POOL, DusksBiomesMod.id(id))
}
