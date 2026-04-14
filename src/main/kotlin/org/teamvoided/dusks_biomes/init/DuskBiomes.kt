package org.teamvoided.dusks_biomes.init

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.biome.Biome
import org.teamvoided.dusks_biomes.DusksBiomes.id
import org.teamvoided.reef.util.key


@Suppress("MagicNumber")
object DuskBiomes {
    val DUSK_BIOMES = mutableListOf<ResourceKey<Biome>>()

    val COLD_FOREST = create("cold_forest")
    val COLD_PLAINS = create("cold_plains")
    val WARM_FOREST = create("warm_forest")
    val WARM_PLAINS = create("warm_plains")
    val WINDSWEPT_BIRCH_FOREST = create("windswept_birch_forest")
    val SNOWY_WINDSWEPT_HILLS = create("snowy_windswept_hills")
    val SNOWY_WINDSWEPT_GRAVELLY_HILLS = create("snowy_windswept_gravelly_hills")
    val SNOWY_WINDSWEPT_FOREST = create("snowy_windswept_forest")
    val SNOWY_OLD_GROWTH_PINE_TAIGA = create("snowy_old_growth_pine_taiga")
    val SNOWY_OLD_GROWTH_SPRUCE_TAIGA = create("snowy_old_growth_spruce_taiga")
    val DARK_GROVE = create("dark_grove")
    val PALE_GROVE = create("pale_grove")
    val SNOWY_CHERRY_GROVE = create("snowy_cherry_grove")
    val FROZEN_BADLANDS = create("frozen_badlands")
    val FROZEN_WOODED_BADLANDS = create("frozen_wooded_badlands")
    val FROZEN_ERODED_BADLANDS = create("frozen_eroded_badlands")
    val FROZEN_MANGROVE_SWAMP = create("frozen_mangrove_swamp")
    val WARM_RIVER = create("warm_river")
    val RED_DESERT = create("red_desert")
    val RED_WARM_RIVER = create("red_warm_river")
    val RED_WARM_OCEAN = create("red_warm_ocean")
    val RED_LUKEWARM_OCEAN = create("red_lukewarm_ocean")
    val DEEP_RED_LUKEWARM_OCEAN = create("deep_red_lukewarm_ocean")
    val RED_BEACH = create("red_beach")
    val SNOWY_RED_BEACH = create("snowy_red_beach")
    val SNOWY_STONY_SHORE = create("snowy_stony_shore")
    val MUSHROOM_GROVE = create("mushroom_grove")
    val ERODED_MUSHROOM_ISLAND = create("eroded_mushroom_island")
    val MUSHROOM_CAVES = create("mushroom_caves")
    val FROZEN_CAVERNS = create("frozen_caverns")
    val SAND_CAVES = create("sand_caverns")
    val RED_SAND_CAVES = create("red_sand_caverns")
    val GRAVEL_CAVES = create("gravel_caves")
    val PALE_UNDERGARDEN = create("pale_undergarden")

    fun create(id: String): ResourceKey<Biome> {
        val retorn = Registries.BIOME.key(id(id))
        DUSK_BIOMES.add(retorn)
        return retorn
    }

}