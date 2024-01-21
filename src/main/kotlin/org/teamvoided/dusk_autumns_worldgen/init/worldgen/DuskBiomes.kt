package org.teamvoided.dusk_autumns_worldgen.init.worldgen

import com.terraformersmc.biolith.api.biome.BiomePlacement
import com.terraformersmc.biolith.api.surface.SurfaceGeneration
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.Biomes
import net.minecraft.world.biome.source.util.MultiNoiseUtil
import net.minecraft.world.biome.source.util.MultiNoiseUtil.NoiseHypercube
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.id


object DuskBiomes {

    val COLD_FOREST = create("cold_forest")
    val COLD_PLAINS = create("cold_plains")
    val WARM_FOREST = create("warm_forest")
    val WARM_PLAINS = create("warm_plains")
    val SNOWY_CHERRY_GROVE = create("snowy_cherry_grove")
    val SNOWY_WINDSWEPT_HILLS = create("snowy_windswept_hills")
    val SNOWY_WINDSWEPT_GRAVELLY_HILLS = create("snowy_windswept_gravelly_hills")
    val SNOWY_WINDSWEPT_FOREST = create("snowy_windswept_forest")
    val SNOWY_OLD_GROWTH_PINE_TAIGA = create("snowy_old_growth_pine_taiga")
    val SNOWY_OLD_GROWTH_SPRUCE_TAIGA = create("snowy_old_growth_spruce_taiga")
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

    val Devils_Roar = create("devils_roar")

    fun init() {
        BiomePlacement.addOverworld(
            ERODED_MUSHROOM_ISLAND,
            makeNoise(
                Range(-1, 1),        // Temperature
                Range(-1, -0.35),      // Humidity
                Range(-1.2, -1.050),          // Continentalness
                Range(-1.0, 1.0),         // Erosion
                Range(0.0),         // Depth
                Range(-1.0, 1.0),         // Weirdness
                0L                  // Offset
            )
        )

        BiomePlacement.replaceOverworld(Biomes.PLAINS, RED_DESERT)
        BiomePlacement.replaceOverworld(Biomes.OCEAN, Biomes.END_HIGHLANDS)

//      For TerraBlender compatibility, it is important the rulesOwner's
//      namespace should be the identical to the namespace of all biomes to which the rules apply.
        SurfaceGeneration.addOverworldSurfaceRules(Identifier("rules/overworld"), DuskSurfaceRules.overworld())
    }



    fun create(id: String): RegistryKey<Biome?> {
        return RegistryKey.of(RegistryKeys.BIOME, id(id))
    }

    fun makeNoise(
        temperature: Range, humidity: Range, continentalness: Range, erosion: Range,
        depth: Range, weirdness: Range, offset: Long
    ): NoiseHypercube = NoiseHypercube(
        temperature.toMNParaRange(),
        humidity.toMNParaRange(),
        continentalness.toMNParaRange(),
        erosion.toMNParaRange(),
        depth.toMNParaRange(),
        weirdness.toMNParaRange(),
        offset
    )

    data class Range(val min: Number, val max: Number) {
        constructor(value: Number) : this(value, value)

        fun min() = min.toFloat()
        fun max() = max.toFloat()
        fun toMNParaRange() = MultiNoiseUtil.ParameterRange.of(min(), max())

    }

}