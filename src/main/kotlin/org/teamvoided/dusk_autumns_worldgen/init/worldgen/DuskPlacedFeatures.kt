package org.teamvoided.dusk_autumns_worldgen.init.worldgen

import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen

object DuskPlacedFeatures {
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
    val INVERTED_ICE_SPIKE_CAVE = create("inverted_ice_spike")

    fun init() {}
    fun create(id: String) = RegistryKey.of(RegistryKeys.PLACED_FEATURE, DuskAutumnsWorldgen.id(id))
}