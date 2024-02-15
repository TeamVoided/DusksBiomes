package org.teamvoided.dusk_autumns_worldgen.init.worldgen

import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen

object DuskPlacedFeatures {

    val SWAMP_VILLAGE_ROCK = create("structure/mossy_cobblestone_rock")
    val SWAMP_VILLAGE_OAK = create("structure/swamp_oak")
    val SWAMP_VILLAGE_MANGROVE = create("structure/mangrove")
    val SWAMP_VILLAGE_FLOWERS = create("structure/blue_orchid_patch")
    val COBBLESTONE_ROCK = create("cobblestone_rock")
    val TREES_COLD_FOREST = create("trees_cold_forest")
    val TREES_COLD_PLAINS = create("trees_cold_plains")
    val TREES_WARM_FOREST = create("trees_warm_forest")
    val TREES_WARM_PLAINS = create("trees_warm_plains")
    val TREES_WINDSWEPT_BIRCH = create("trees_windswept_birch")
    val MANGROVE_FROZEN_CHECKED = create("mangrove_frozen_checked")
    val TALL_MANGROVE_FROZEN_CHECKED = create("tall_mangrove_frozen_checked")
    val TREES_MANGROVE_FROZEN = create("trees_mangrove_frozen")
    val TREES_WINDSWEPT_MANGROVE_FROZEN = create("trees_windswept_mangrove_frozen")
    val TREES_WINDSWEPT_MANGROVE = create("trees_windswept_mangrove")
    val TREES_OLD_GROWTH_SWAMP = create("trees_old_growth_swamp")
    val CHERRY_ON_SNOW = create("cherry_on_snow")
    val CHERRY_ON_SNOW_BEES = create("cherry_on_snow_bees")
    val TREES_SNOWY_CHERRY_GROVE = create("trees_snowy_cherry_grove")
    val FLOWER_SNOWY_CHERRY = create("flower_snowy_cherry")
    val MUSHROOM_GROVE_VEGETATION = create("mushroom_grove_vegetation")
    val ICE_SPIKE_FLOOR = create("cave/frozen_cavern/ice_spike_floor")
    val ICE_SPIKE_CEILING = create("cave/frozen_cavern/ice_spike_ceiling")
    val ORE_ICE = create("cave/frozen_cavern/ore_ice")
    val ORE_BLUE_ICE = create("cave/frozen_cavern/ore_blue_ice")
    val SAND_CACTUS = create("cave/sand/cactus")
    val SAND_SPIKES = create("cave/sand/sandstone_spikes")
    val SAND_SPIKES_ROOF = create("cave/sand/sandstone_roof_spikes")
    val RED_SAND_SPIKES = create("cave/sand/red_sandstone_spikes")
    val RED_SAND_SPIKES_ROOF = create("cave/sand/red_sandstone_roof_spikes")
    val SAND_CAVE_VINES = create("cave/sand/cave_vines")

    val DEEP_MONSTER_ROOM = create("monster_room/deep_monster_room")
    val FROZEN_MONSTER_ROOM = create("monster_room/frozen_monster_room")
    val DEEP_FROZEN_MONSTER_ROOM = create("monster_room/deep_frozen_monster_room")
    val LUSH_MONSTER_ROOM = create("monster_room/lush_monster_room")
    val DEEP_LUSH_MONSTER_ROOM = create("monster_room/deep_lush_monster_room")
    val SAND_MONSTER_ROOM = create("monster_room/sand_monster_room")
    val DEEP_SAND_MONSTER_ROOM = create("monster_room/deep_sand_monster_room")
    val RED_SAND_MONSTER_ROOM = create("monster_room/red_sand_monster_room")
    val DEEP_RED_SAND_MONSTER_ROOM = create("monster_room/deep_red_sand_monster_room")

    val DESERT_WELL = create("desert_well")
    val RED_DESERT_WELL = create("red_desert_well")
    val CAVE_DESERT_WELL = create("cave/sand/desert_well")
    val CAVE_RED_DESERT_WELL = create("cave/sand/red_desert_well")

    fun init() {}
    fun create(id: String) = RegistryKey.of(RegistryKeys.PLACED_FEATURE, DuskAutumnsWorldgen.id(id))
}