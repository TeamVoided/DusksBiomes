package org.teamvoided.dusks_biomes.data.world.gen

import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries
import org.teamvoided.dusks_biomes.DusksBiomes.id
import org.teamvoided.dusks_biomes.util.key

object DuskBiomeInjectors {

    val COLD_FOREST = key("cold_forest")
    val COLD_PLAINS = key("cold_plains")
    val WARM_FOREST = key("warm_forest")
    val WARM_PLAINS = key("warm_plains")
    val WINDSWEPT_BIRCH_FOREST = key("windswept_birch_forest")
    val WINDSWEPT_BIRCH_FOREST_REPLACE = key("windswept_birch_forest_replace")
    val SNOWY_WINDSWEPT_HILLS = key("snowy_windswept_hills")
    val SNOWY_WINDSWEPT_GRAVELLY_HILLS = key("snowy_windswept_gravelly_hills")
    val SNOWY_WINDSWEPT_FOREST = key("snowy_windswept_forest")
    val SNOWY_OLD_GROWTH_PINE_TAIGA = key("snowy_old_growth_pine_taiga")
    val SNOWY_OLD_GROWTH_SPRUCE_TAIGA = key("snowy_old_growth_spruce_taiga")
    val DARK_GROVE = key("dark_grove")
    val PALE_GROVE = key("pale_grove")
    val PALE_GROVE_CONTINENTALNESS = key("pale_grove_continentalness")
    val SNOWY_CHERRY_GROVE = key("snowy_cherry_grove")
    // Unused
//    val FROZEN_BADLANDS = key("frozen_badlands")
//    val FROZEN_WOODED_BADLANDS = key("frozen_wooded_badlands")
//    val FROZEN_ERODED_BADLANDS = key("frozen_eroded_badlands")
    val FROZEN_MANGROVE_SWAMP = key("frozen_mangrove_swamp")
    val FROZEN_MANGROVE_SWAMP_RIVER = key("frozen_mangrove_swamp_river")
    val ERODED_BADLANDS = key("eroded_badlands")
    val WARM_RIVER = key("warm_river")
    val RED_DESERT = key("red_desert")
    val RED_WARM_RIVER = key("red_warm_river")
    val RED_WARM_OCEAN = key("red_warm_ocean")
    val RED_LUKEWARM_OCEAN = key("red_lukewarm_ocean")
    val DEEP_RED_LUKEWARM_OCEAN = key("deep_red_lukewarm_ocean")
    val RED_BEACH = key("red_beach")
    val SNOWY_RED_BEACH = key("snowy_red_beach")
    val SNOWY_STONY_SHORE = key("snowy_stony_shore")
    val MUSHROOM_GROVE = key("mushroom_grove")
    val MUSHROOM_GROVE_WEIRDNESS = key("mushroom_grove_weirdness")
    val ERODED_MUSHROOM_ISLAND = key("eroded_mushroom_island")
    val ERODED_MUSHROOM_ISLAND_HUMIDITY = key("eroded_mushroom_island_humidity")
    val MUSHROOM_CAVES = key("mushroom_caves")
    val FROZEN_CAVERNS = key("frozen_caverns")
    val FROZEN_CAVERNS_ICE_SPIKE = key("frozen_caverns_ice_spike")
    val FROZEN_CAVERNS_PEAKS = key("frozen_caverns_peaks")
    val FROZEN_CAVERNS_FROZEN_OCEAN = key("frozen_caverns_frozen_ocean")
    val FROZEN_CAVERNS_DEEP_FROZEN_OCEAN = key("frozen_caverns_deep_frozen_ocean")
    val SAND_CAVES = key("sand_caves")
    val SAND_CAVES_DESERT = key("sand_caves_desert")
    val SAND_CAVES_WARM_OCEAN = key("sand_caves_warm_ocean")
    val RED_SAND_CAVES = key("red_sand_caves")
    val RED_SAND_CAVES_BADLANDS = key("red_sand_caves_badlands")
    val GRAVEL_CAVES = key("gravel_caves")
    // TODO dusk should add his spawn conditions
    val PALE_CAVES = key("pale_caves")
    val PALE_CAVES_PALE_GROVE = key("pale_caves_pale_grove")
    val PALE_CAVES_PALE_GROVE_CONTINENTALNESS = key("pale_caves_pale_grove_continentalness")
    val PALE_CAVES_PALE_GARDEN = key("pale_caves_pale_garden")

    fun key(id: String) = LithostitchedRegistries.BIOME_INJECTOR.key(id(id))
}
