package org.teamvoided.dusk_autumns_worldgen.init.worldgen

import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.math.MathHelper
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.GenerationSettings
import net.minecraft.world.gen.feature.DefaultBiomeFeatures
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.id

object DuskBiomes {

    val COLD_FOREST = create("cold_forest")
    val COLD_PLAINS = create("cold_plains")
    val WARM_FOREST = create("warm_forest")
    val WARM_PLAINS = create("warm_plains")
    val SNOWY_WINDSWEPT_HILLS = create("snowy_windswept_hills")
    val SNOWY_WINDSWEPT_GRAVELLY_HILLS = create("snowy_windswept_gravelly_hills")
    val SNOWY_WINDSWEPT_FOREST = create("snowy_windswept_forest")
    val SNOWY_OLD_GROWTH_PINE_TAIGA = create("snowy_old_growth_pine_taiga")
    val SNOWY_OLD_GROWTH_SPRUCE_TAIGA = create("snowy_old_growth_spruce_taiga")
    val SNOWY_CHERRY_GROVE = create("snowy_cherry_grove")
    val FROZEN_MANGROVE_SWAMP = create("frozen_mangrove_swamp")
    val WARM_RIVER = create("warm_river")
    val RED_WARM_RIVER = create("red_warm_river")
    val RED_WARM_OCEAN = create("red_warm_ocean")
    val RED_LUKEWARM_OCEAN = create("red_lukewarm_ocean")
    val DEEP_RED_LUKEWARM_OCEAN = create("deep_red_lukewarm_ocean")
    val RED_BEACH = create("red_beach")
    val SNOWY_RED_BEACH = create("snowy_red_beach")
    val SNOWY_STONY_SHORE = create("snowy_stony_shore")
    val RED_DESERT = create("red_desert")
    val FROZEN_CAVERNS = create("frozen_caverns")
    val MUSHROOM_GROVE = create("mushroom_grove")
    val ERODED_MUSHROOM_ISLAND = create("eroded_mushroom_island")
    val MUSHROOM_CAVES = create("mushroom_caves")

    fun init() {}
    fun create(id: String): RegistryKey<Biome?> {
        return RegistryKey.of(RegistryKeys.BIOME, id(id))
    }
}