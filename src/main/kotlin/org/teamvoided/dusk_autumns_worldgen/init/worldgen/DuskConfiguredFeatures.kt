package org.teamvoided.dusk_autumns_worldgen.init.worldgen

import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.id


@Suppress("HasPlatformType")
object DuskConfiguredFeatures {
    val COBBLESTONE_ROCK = create("cobblestone_rock")
    val TREES_OAK_BIRCH_SPRUCE = create("tree/trees_oak_birch_spruce")
    val TREES_OAK_BIRCH_JUNGLE = create("tree/trees_oak_birch_jungle")
    val MANGROVE_FROZEN_CHECKED = create("tree/mangrove_frozen_checked")
    val TALL_MANGROVE_FROZEN_CHECKED = create("tree/tall_mangrove_frozen_checked")
    val MANGROVE_FROZEN_VEGETATION = create("tree/mangrove_frozen_vegetation")
    val SWAMP_OAK = create("tree/swamp_oak")
    val CHERRY_SNOW = create("tree/cherry_snow")
    val CHERRY_SNOW_BEES = create("tree/cherry_snow_bees")
    val TREES_SNOWY_CHERRY = create("tree/trees_snowy_chery")
    val FLOWER_SNOWY_CHERRY = create("flower_snowy_cherry")
    val CAVE_GLOW_LICHEN_EXTRA = create("cave/glow_lichen_extra")
    val MUSHROOM_CAVE_SURFACE = create("cave/mushroom/surface")
    val MUSHROOM_CAVE_MUSHROOMS = create("cave/mushroom/mushrooms")
    val MUSHROOM_CAVE_ROOTS = create("cave/mushroom/roots")
    val ICE_CAVE_PILLAR = create("cave/frozen/ice_cave_pillar")
    val ICE_SPIKE_FLOOR = create("cave/frozen/ice_spike_floor")
    val ICE_SPIKE_CEILING = create("cave/frozen/ice_spike_ceiling")
    val ICE_SPIKE = create("cave/frozen/ice_spike")
    val INVERTED_ICE_SPIKE = create("cave/frozen/inverted_ice_spike")
    val BLUE_ICE_SPIKE = create("cave/frozen/blue_ice_spike")
    val INVERTED_BLUE_ICE_SPIKE = create("cave/frozen/inverted_blue_ice_spike")
    val ORE_ICE = create("cave/frozen/ore_ice")
    val ORE_BLUE_ICE = create("cave/frozen/ore_blue_ice")
    val SAND_CAVE_CACTUS = create("cave/sand/sand_cave_cactus")
    val ORE_SAND = create("cave/sand/ore_sand")
    val ORE_RED_SAND = create("cave/sand/ore_red_sand")
    val SAND_CAVE_PILLAR = create("cave/sand/sand_cave_pillar")
    val SAND_SPIKES = create("cave/sand/spikes")
    val SAND_SPIKES_ROOF = create("cave/sand/spikes_roof")
    val RED_SAND_CAVE_PILLAR = create("cave/sand/red_sand_cave_pillar")
    val RED_SAND_SPIKES = create("cave/sand/red_spikes")
    val RED_SAND_SPIKES_ROOF = create("cave/sand/red_spikes_roof")
    val SAND_CAVE_SEAGRASS = create("cave/sand/cave_seagrass")
    val SAND_CAVE_PICKLES = create("cave/sand/cave_pickles")

    val DEEP_MONSTER_ROOM = create("monster_room/deep_monster_room")
    val FROZEN_MONSTER_ROOM = create("monster_room/frozen_monster_room")
    val DEEP_FROZEN_MONSTER_ROOM = create("monster_room/deep_frozen_monster_room")
    val LUSH_MONSTER_ROOM = create("monster_room/lush_monster_room")
    val DEEP_LUSH_MONSTER_ROOM = create("monster_room/deep_lush_monster_room")
    val SAND_MONSTER_ROOM = create("monster_room/sand_monster_room")
    val RED_SAND_MONSTER_ROOM = create("monster_room/red_sand_monster_room")

    val DESERT_WELL = create("structure/desert_well")
    val RED_DESERT_WELL = create("structure/red_desert_well")


    val TEST_CAVE_PILLAR = create("test_cave_pillar")

    fun init() {}
    fun create(id: String) = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, id(id))
}