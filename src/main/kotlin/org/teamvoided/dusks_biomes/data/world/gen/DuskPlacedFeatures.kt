package org.teamvoided.dusks_biomes.data.world.gen

import net.minecraft.core.registries.Registries
import org.teamvoided.dusks_biomes.DusksBiomes.id
import org.teamvoided.dusks_biomes.util.key

object DuskPlacedFeatures {

    val SWAMP_VILLAGE_ROCK = key("structures/mossy_cobblestone_rock")
    val SWAMP_VILLAGE_OAK = key("structures/swamp_oak")
    val SWAMP_VILLAGE_MANGROVE = key("structures/mangrove")
    val SWAMP_VILLAGE_FLOWERS = key("structures/blue_orchid_patch")
    val COBBLESTONE_ROCK = key("cobblestone_rock")

    //TREE FEATURES
    val TREES_COLD_FOREST = key("tree/trees_cold_forest")
    val TREES_COLD_PLAINS = key("tree/trees_cold_plains")
    val TREES_WARM_FOREST = key("tree/trees_warm_forest")
    val TREES_WARM_PLAINS = key("tree/trees_warm_plains")
    val TREES_SNOWY_DARK_GROVE = key("tree/trees_snowy_dark_grove")
    val TREES_SNOWY_DARK_GROVE_ON_SNOW = key("tree/trees_snowy_dark_grove_on_snow")
    val TREES_SNOWY_PALE_GROVE = key("tree/trees_snowy_pale_grove")
    val TREES_SNOWY_PALE_GROVE_ON_SNOW = key("tree/trees_snowy_pale_grove_on_snow")
    val TREES_WINDSWEPT_BIRCH = key("tree/trees_windswept_birch")
    val TREES_FROZEN_BADLANDS = key("tree/trees_frozen_badlands")
    val MANGROVE_FROZEN_CHECKED = key("tree/mangrove_frozen_checked")
    val TALL_MANGROVE_FROZEN_CHECKED = key("tree/tall_mangrove_frozen_checked")
    val TREES_MANGROVE_FROZEN = key("tree/trees_mangrove_frozen")
    val TREES_SNOWY_CHERRY_GROVE = key("tree/trees_snowy_cherry_grove")
    val FLOWER_SNOWY_CHERRY = key("flower_snowy_cherry")
    val MUSHROOM_GROVE_VEGETATION = key("mushroom_grove_vegetation")

    //CAVE FEATURES
    val CAVE_DEAD_BUSH = key("cave/dead_bush")
    val CAVE_GLOW_LICHEN_EXTRA = key("cave/glow_lichen_extra")
    val ORE_COARSE_DIRT = key("cave/ore_coarse_dirt")
    val MUSHROOM_CAVE_VEGETATION = key("cave/mushroom/vegetation")
    val MUSHROOM_CAVE_MUSHROOMS = key("cave/mushroom/mushrooms")
    val MUSHROOM_CAVE_SURFACE = key("cave/mushroom/surface")
    val ICE_CAVE_PILLAR = key("cave/frozen_cavern/ice_cave_pillar")
    val ICE_SPIKE_FLOOR = key("cave/frozen_cavern/ice_spike_floor")
    val ICE_SPIKE_CEILING = key("cave/frozen_cavern/ice_spike_ceiling")
    val ORE_ICE = key("cave/frozen_cavern/ore_ice")
    val ORE_BLUE_ICE = key("cave/frozen_cavern/ore_blue_ice")
    val ICE_CAVE_FOSSIL = key("cave/frozen/frozen_fossil")
    val SAND_CAVE_PILLAR = key("cave/sand/sand_cave_pillar")
    val SAND_CACTUS = key("cave/sand/cactus")
    val SAND_DRY_GRASS = key("cave/sand/dry_grass")
    val ORE_SAND = key("cave/sand/ore_sand")
    val ORE_RED_SAND = key("cave/sand/ore_red_sand")
    val SAND_SPIKES = key("cave/sand/sandstone_spikes")
    val SAND_SPIKES_ROOF = key("cave/sand/sandstone_roof_spikes")
    val RED_SAND_CAVE_PILLAR = key("cave/sand/red_sand_cave_pillar")
    val RED_SAND_SPIKES = key("cave/sand/red_sandstone_spikes")
    val RED_SAND_SPIKES_ROOF = key("cave/sand/red_sandstone_roof_spikes")

    //val SAND_CAVE_VINES = create("cave/sand/cave_vines")
    val SAND_CAVE_CORAL = key("cave/sand/cave_coral")
    val SAND_CAVE_SEAGRASS = key("cave/sand/cave_seagrass")
    val SAND_CAVE_PICKLE = key("cave/sand/cave_pickle")
    val ORE_COBBLESTONE = key("cave/gravel/ore_cobblestone")
    val COBBLESTONE_CAVE_PILLAR = key("cave/gravel/cobblestone_cave_pillar")
    val COBBLESTONE_SPIKES = key("cave/gravel/cobblestone_spikes")
    val COBBLESTONE_SPIKES_ROOF = key("cave/gravel/cobblestone_spikes_roof")
    val COBBLED_DEEPSLATE_CAVE_PILLAR = key("cave/gravel/cobbled_deepslate_cave_pillar")
    val COBBLED_DEEPSLATE_SPIKES = key("cave/gravel/cobbled_deepslate_spikes")
    val COBBLED_DEEPSLATE_SPIKES_ROOF = key("cave/gravel/cobbled_deepslate_spikes_roof")
    val PALE_CAVES_VINES = key("cave/pale/vines")
    val PALE_CAVES_VEGETATION = key("cave/pale/vegetation")
    val PALE_CAVES_CEILING_VEGETATION = key("cave/pale/ceiling_vegetation")
    val PALE_HEART_CEILING = key("cave/pale/heart_ceiling")
    val FLOWER_PALE_CAVE = key("cave/pale/flowers")
    val PALE_CAVE_LEAVES = key("cave/pale/leaves")
    val PALE_CAVE_LEAVES_CEILING = key("cave/pale/leaves_ceiling")
    val PALE_CAVE_RESIN = key("cave/pale/resin")
    val PALE_CAVE_ROOTS = key("cave/pale/roots")


    /* - - - - - MONSTER ROOMS - - - - - */
    val DEEP_MONSTER_ROOM = key("monster_room/deep_monster_room")
    val FROZEN_MONSTER_ROOM = key("monster_room/frozen_monster_room")
    val DEEP_FROZEN_MONSTER_ROOM = key("monster_room/deep_frozen_monster_room")
    val LUSH_MONSTER_ROOM = key("monster_room/lush_monster_room")
    val DEEP_LUSH_MONSTER_ROOM = key("monster_room/deep_lush_monster_room")
    val SAND_MONSTER_ROOM = key("monster_room/sand_monster_room")
    val DEEP_SAND_MONSTER_ROOM = key("monster_room/deep_sand_monster_room")
    val RED_SAND_MONSTER_ROOM = key("monster_room/red_sand_monster_room")
    val DEEP_RED_SAND_MONSTER_ROOM = key("monster_room/deep_red_sand_monster_room")


    /* - - - - - STRUCTURE FEATURES - - - - - */
    val DESERT_WELL = key("structure/desert_well")
    val RED_DESERT_WELL = key("structure/red_desert_well")
    val CAVE_DESERT_WELL = key("structure/cave_desert_well")
    val CAVE_RED_DESERT_WELL = key("structure/cave_red_desert_well")

    // Ores
    val ORE_CARBON_COAL = key("ore_carbon_coal")
    val ORE_CARBON_DIAMONDS = key("ore_carbon_diamonds")

    fun key(id: String) = Registries.PLACED_FEATURE.key(id(id))

}
