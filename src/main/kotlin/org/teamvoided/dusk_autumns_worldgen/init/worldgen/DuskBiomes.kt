package org.teamvoided.dusk_autumns_worldgen.init.worldgen

import com.terraformersmc.biolith.api.biome.BiomePlacement
import com.terraformersmc.biolith.api.biome.SubBiomeMatcher
import com.terraformersmc.biolith.api.biome.SubBiomeMatcher.CriterionTargets
import com.terraformersmc.biolith.api.biome.SubBiomeMatcher.of
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

    val DEVILS_ROAR = create("devils_roar")


    fun init() {
        val snowyVariant = SubBiomeMatcher.Criterion.ofRange(
            CriterionTargets.TEMPERATURE, SubBiomeMatcher.CriterionTypes.VALUE,
            -1F, -0.45f, false
        )
        val redSandVariant = SubBiomeMatcher.Criterion.ofRange(
            CriterionTargets.EROSION, SubBiomeMatcher.CriterionTypes.VALUE,
            -1F, -0.375f, false
        )
        val redSandLandVariant = of(
            SubBiomeMatcher.Criterion.ofRange(
                CriterionTargets.EROSION, SubBiomeMatcher.CriterionTypes.VALUE,
                -1F, -0.223f, false
            ),
            SubBiomeMatcher.Criterion.ofRange(
                CriterionTargets.WEIRDNESS, SubBiomeMatcher.CriterionTypes.VALUE,
                -1F, 0f, false
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.FOREST, COLD_FOREST, of(
                SubBiomeMatcher.Criterion.ofRange(
                    CriterionTargets.TEMPERATURE, SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.3F, 0f, false
                )
            )
        )
        BiomePlacement.addSubOverworld(
            Biomes.PLAINS, COLD_PLAINS, of(
                SubBiomeMatcher.Criterion.ofRange(
                    CriterionTargets.TEMPERATURE, SubBiomeMatcher.CriterionTypes.VALUE,
                    -0.3F, 0f, false
                )
            )
        )
        BiomePlacement.addSubOverworld(
            Biomes.FOREST, WARM_FOREST, of(
                SubBiomeMatcher.Criterion.ofRange(
                    CriterionTargets.TEMPERATURE, SubBiomeMatcher.CriterionTypes.VALUE,
                    0.375F, 1f, false
                )
            )
        )
        BiomePlacement.addSubOverworld(
            Biomes.PLAINS, WARM_PLAINS, of(
                SubBiomeMatcher.Criterion.ofRange(
                    CriterionTargets.TEMPERATURE, SubBiomeMatcher.CriterionTypes.VALUE,
                    0.375F, 1f, false
                )
            )
        )
        BiomePlacement.addSubOverworld(
            Biomes.SNOWY_SLOPES, SNOWY_CHERRY_GROVE, of(
                SubBiomeMatcher.Criterion.ofRange(
                    CriterionTargets.TEMPERATURE, SubBiomeMatcher.CriterionTypes.VALUE,
                    -1f, -0.45f, false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    CriterionTargets.HUMIDITY, SubBiomeMatcher.CriterionTypes.VALUE,
                    -1F, -0.35f, false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    CriterionTargets.WEIRDNESS, SubBiomeMatcher.CriterionTypes.VALUE,
                    -1F, 0f, false
                )
            )
        )
        BiomePlacement.addSubOverworld(
            Biomes.WINDSWEPT_HILLS, SNOWY_WINDSWEPT_HILLS, of(
                snowyVariant
            )
        )
        BiomePlacement.addSubOverworld(
            Biomes.WINDSWEPT_GRAVELLY_HILLS, SNOWY_WINDSWEPT_GRAVELLY_HILLS, of(
                snowyVariant
            )
        )
        BiomePlacement.addSubOverworld(
            Biomes.WINDSWEPT_FOREST, SNOWY_WINDSWEPT_FOREST, of(
                snowyVariant
            )
        )
        BiomePlacement.addSubOverworld(
            Biomes.TAIGA, SNOWY_OLD_GROWTH_SPRUCE_TAIGA, of(
                SubBiomeMatcher.Criterion.ofRange(
                    CriterionTargets.TEMPERATURE, SubBiomeMatcher.CriterionTypes.VALUE,
                    -1f, -0.45f, false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    CriterionTargets.WEIRDNESS, SubBiomeMatcher.CriterionTypes.VALUE,
                    -1F, 0f, false
                )
            )
        )
        BiomePlacement.addSubOverworld(
            Biomes.TAIGA, SNOWY_OLD_GROWTH_PINE_TAIGA, of(
                SubBiomeMatcher.Criterion.ofRange(
                    CriterionTargets.TEMPERATURE, SubBiomeMatcher.CriterionTypes.VALUE,
                    -1f, -0.45f, false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    CriterionTargets.WEIRDNESS, SubBiomeMatcher.CriterionTypes.VALUE,
                    0F, 1f, false
                )
            )
        )
        BiomePlacement.addOverworld(
            FROZEN_MANGROVE_SWAMP,
            makeNoise(
                Range(-1, -0.45),        // Temperature
                Range(-1, 1),            // Humidity
                Range(-0.11, 1),          // Continentalness
                Range(0.55, 1.0),         // Erosion
                Range(0.0),         // Depth
                Range(-0.4, 0.4),         // Weirdness
                0L                  // Offset
            )
        )
        BiomePlacement.addSubOverworld(
            Biomes.DESERT, RED_DESERT, of(
                redSandVariant
            )
        )
        BiomePlacement.addSubOverworld(
            Biomes.DESERT, RED_DESERT,
            redSandLandVariant
        )

        BiomePlacement.addSubOverworld(
            Biomes.RIVER, WARM_RIVER, of(
                SubBiomeMatcher.Criterion.ofRange(
                    CriterionTargets.TEMPERATURE, SubBiomeMatcher.CriterionTypes.VALUE,
                    0.55f, 1f, false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    CriterionTargets.EROSION, SubBiomeMatcher.CriterionTypes.VALUE,
                    -1F, -0.223f, true
                )
            )
        )
        BiomePlacement.addSubOverworld(
            Biomes.RIVER, RED_WARM_RIVER, of(
                SubBiomeMatcher.Criterion.ofRange(
                    CriterionTargets.TEMPERATURE, SubBiomeMatcher.CriterionTypes.VALUE,
                    0.55f, 1f, false
                ),
                redSandVariant
            )
        )
        BiomePlacement.addSubOverworld(
            Biomes.RIVER, RED_WARM_RIVER, of(
                SubBiomeMatcher.Criterion.ofRange(
                    CriterionTargets.TEMPERATURE, SubBiomeMatcher.CriterionTypes.VALUE,
                    0.55f, 1f, false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    CriterionTargets.EROSION, SubBiomeMatcher.CriterionTypes.VALUE,
                    -1F, -0.223f, false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    CriterionTargets.WEIRDNESS, SubBiomeMatcher.CriterionTypes.VALUE,
                    -1F, 0f, false
                )
            )
        )
        BiomePlacement.addSubOverworld(
            Biomes.WARM_OCEAN, RED_WARM_OCEAN, of(
                redSandVariant
            )
        )
        BiomePlacement.addSubOverworld(
            Biomes.LUKEWARM_OCEAN, RED_LUKEWARM_OCEAN, of(
                redSandVariant
            )
        )
        BiomePlacement.addSubOverworld(
            Biomes.DEEP_LUKEWARM_OCEAN, DEEP_RED_LUKEWARM_OCEAN, of(
                redSandVariant
            )
        )
        BiomePlacement.addSubOverworld(
            Biomes.BEACH, RED_BEACH, of(
                redSandVariant
            )
        )
        BiomePlacement.addSubOverworld(
            Biomes.BEACH, RED_BEACH,
            redSandLandVariant

        )
        BiomePlacement.addSubOverworld(
            Biomes.SNOWY_BEACH, SNOWY_RED_BEACH, of(
                redSandVariant
            )
        )
        BiomePlacement.addSubOverworld(
            Biomes.SNOWY_BEACH, SNOWY_RED_BEACH,
            redSandLandVariant

        )
        BiomePlacement.addSubOverworld(
            Biomes.STONY_SHORE, SNOWY_STONY_SHORE, of(
                snowyVariant
            )
        )
        BiomePlacement.addSubOverworld(
            Biomes.MUSHROOM_FIELDS, MUSHROOM_GROVE, of(
                SubBiomeMatcher.Criterion.ofRange(
                    CriterionTargets.HUMIDITY, SubBiomeMatcher.CriterionTypes.VALUE,
                    0.33f, 1f, false
                )
            )
        )

        BiomePlacement.addOverworld(
            ERODED_MUSHROOM_ISLAND,
            makeNoise(
                Range(-1, 1),        // Temperature
                Range(-1, -0.1),      // Humidity
                Range(-1.2, -0.85),          // Continentalness
                Range(-1, 1),         // Erosion
                Range(0),         // Depth
                Range(-1, 1),         // Weirdness
                0L                  // Offset
            )
        )


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