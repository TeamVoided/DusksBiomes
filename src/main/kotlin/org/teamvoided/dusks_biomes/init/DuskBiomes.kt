package org.teamvoided.dusks_biomes.init

import com.terraformersmc.biolith.api.biome.BiomePlacement
import com.terraformersmc.biolith.api.biome.SubBiomeMatcher
import com.terraformersmc.biolith.api.biome.SubBiomeMatcher.CriterionTargets
import com.terraformersmc.biolith.api.biome.SubBiomeMatcher.of
import com.terraformersmc.biolith.api.surface.SurfaceGeneration
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.Biomes
import net.minecraft.world.biome.source.util.MultiNoiseUtil.NoiseHypercube
import org.teamvoided.dusks_biomes.DusksBiomesMod.id
import org.teamvoided.dusks_biomes.DusksBiomesMod.mc
import org.teamvoided.dusks_biomes.data.world.gen.DuskSurfaceRules
import org.teamvoided.dusks_biomes.util.data.Range


@Suppress("MagicNumber")
object DuskBiomes {
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
    val SNOWY_DARK_GROVE = create("snowy_dark_grove")
    val SNOWY_CHERRY_GROVE = create("snowy_cherry_grove")
    val FROZEN_BADLANDS = create("frozen_badlands")
    val FROZEN_WOODED_BADLANDS = create("frozen_wooded_badlands")
    val FROZEN_ERODED_BADLANDS = create("frozen_eroded_badlands")
    val FROZEN_MANGROVE_SWAMP = create("frozen_mangrove_swamp")
    val FROZEN_WINDSWEPT_MANGROVE_SWAMP = create("frozen_windswept_mangrove_swamp")
    val WINDSWEPT_MANGROVE_SWAMP = create("windswept_mangrove_swamp")
    val OLD_GROWTH_SWAMP = create("old_growth_swamp")
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


    fun init() {
        val windsweptVariant = of(
            SubBiomeMatcher.Criterion.ofRange(
                CriterionTargets.HUMIDITY, SubBiomeMatcher.CriterionTypes.VALUE,
                -1F, 0.1f, false
            ),
            SubBiomeMatcher.Criterion.ofRange(
                CriterionTargets.WEIRDNESS, SubBiomeMatcher.CriterionTypes.VALUE,
                -1F, 0f, false
            )
        )
        val oldGrowthVariant = of(
            SubBiomeMatcher.Criterion.ofRange(
                CriterionTargets.HUMIDITY, SubBiomeMatcher.CriterionTypes.VALUE,
                0.1F, 1f, false
            ),
            SubBiomeMatcher.Criterion.ofRange(
                CriterionTargets.WEIRDNESS, SubBiomeMatcher.CriterionTypes.VALUE,
                0F, 1f, false
            )
        )
        val snowyVariant = SubBiomeMatcher.Criterion.ofRange(
            CriterionTargets.TEMPERATURE, SubBiomeMatcher.CriterionTypes.VALUE,
            -1F, -0.45f, false
        )
        val coldRegion = SubBiomeMatcher.Criterion.ofRange(
            CriterionTargets.TEMPERATURE, SubBiomeMatcher.CriterionTypes.VALUE,
            -1F, -0.3f, false
        )
        val warmRegion = SubBiomeMatcher.Criterion.ofRange(
            CriterionTargets.TEMPERATURE, SubBiomeMatcher.CriterionTypes.VALUE,
            0.375F, 1f, false
        )
        val redSandVariant = of(
            SubBiomeMatcher.Criterion.ofRange(
                CriterionTargets.EROSION, SubBiomeMatcher.CriterionTypes.VALUE,
                -1F, -0.223f, false
            )
        )
        val redSandInlandVariant = of(
            SubBiomeMatcher.Criterion.ofRange(
                CriterionTargets.EROSION, SubBiomeMatcher.CriterionTypes.VALUE,
                -1F, 0.05f, false
            )
        )

        BiomePlacement.addSubOverworld(
            Biomes.FOREST, COLD_FOREST, of(
                coldRegion
            )
        )
        BiomePlacement.addSubOverworld(
            Biomes.PLAINS, COLD_PLAINS, of(
                coldRegion
            )
        )
        BiomePlacement.addSubOverworld(
            Biomes.FOREST, WARM_FOREST, of(
                warmRegion
            )
        )
        BiomePlacement.addSubOverworld(
            Biomes.PLAINS, WARM_PLAINS, of(
                warmRegion
            )
        )
        addOverworld(
            WINDSWEPT_BIRCH_FOREST,
            Range(-0.45, 0.2),        // Temperature
            Range(-1, 0.3),            // Humidity
            Range(-0.19, 0.03),          // Continentalness
            Range(0.45, 0.55),         // Erosion
            Range(0.05, 1),         // Weirdness
        )
        BiomePlacement.addSubOverworld(
            Biomes.WINDSWEPT_SAVANNA, WINDSWEPT_BIRCH_FOREST, of(
                SubBiomeMatcher.Criterion.ofRange(
                    CriterionTargets.TEMPERATURE, SubBiomeMatcher.CriterionTypes.VALUE,
                    -1f, 0.2f, false
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
        BiomePlacement.addSubOverworld(
            Biomes.GROVE, SNOWY_DARK_GROVE, of(
                SubBiomeMatcher.Criterion.ofRange(
                    CriterionTargets.HUMIDITY, SubBiomeMatcher.CriterionTypes.VALUE,
                    0.3F, 1f, false
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
        addOverworld(
            FROZEN_MANGROVE_SWAMP,
            Range(-1, -0.45),        // Temperature
            Range(-1, 1),            // Humidity
            Range(-0.11, 1),          // Continentalness
            Range(0.55, 1.0),         // Erosion
            Range(0, 0.4),         // Weirdness
        )
        BiomePlacement.addSubOverworld(
            Biomes.BADLANDS, Biomes.ERODED_BADLANDS, of(
                SubBiomeMatcher.Criterion.ofRange(
                    CriterionTargets.HUMIDITY, SubBiomeMatcher.CriterionTypes.VALUE,
                    -1F, -0.35f, false
                )
            )
        )
//        addFrozenBadlands(
//            Range(-0.11, 1),
//            Range(-1, 0.05)
//        )
        addOverworld(
            FROZEN_MANGROVE_SWAMP,
            Range(-1, -0.45),        // Temperature
            Range(-1, 1),            // Humidity
            Range(-0.11, 1),          // Continentalness
            Range(0.55, 1.0),         // Erosion
            Range(0, 0.4),         // Weirdness
        )
        addOverworld(
            FROZEN_MANGROVE_SWAMP,
            Range(-1, -0.45),        // Temperature
            Range(-1, 1),            // Humidity
            Range(-0.11, 1),          // Continentalness
            Range(0.55, 1.0),         // Erosion
            Range(0.933, 1),         // Weirdness
        )
        addOverworld(
            FROZEN_WINDSWEPT_MANGROVE_SWAMP,
            Range(-1, -0.45),        // Temperature
            Range(-1, 0.1),            // Humidity
            Range(-0.11, 1),          // Continentalness
            Range(0.55, 1.0),         // Erosion
            Range(-0.4, 0),         // Weirdness
        )
        addOverworld(
            FROZEN_WINDSWEPT_MANGROVE_SWAMP,
            Range(-1, -0.45),        // Temperature
            Range(-1, 0.1),            // Humidity
            Range(-0.11, 1),          // Continentalness
            Range(0.55, 1.0),         // Erosion
            Range(-1, -0.933),         // Weirdness
        )
        BiomePlacement.addSubOverworld(
            Biomes.FROZEN_RIVER, FROZEN_MANGROVE_SWAMP, of(
                SubBiomeMatcher.Criterion.ofRange(
                    CriterionTargets.TEMPERATURE, SubBiomeMatcher.CriterionTypes.VALUE,
                    -1f, -0.45f, false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    CriterionTargets.EROSION, SubBiomeMatcher.CriterionTypes.VALUE,
                    0.55F, 1f, false
                )
            )
        )
        BiomePlacement.addSubOverworld(
            Biomes.MANGROVE_SWAMP, WINDSWEPT_MANGROVE_SWAMP,
            windsweptVariant
        )
        BiomePlacement.addSubOverworld(
            Biomes.SWAMP, OLD_GROWTH_SWAMP,
            oldGrowthVariant
        )
        BiomePlacement.addSubOverworld(
            Biomes.DESERT, RED_DESERT,
            redSandInlandVariant
        )
        BiomePlacement.addSubOverworld(
            Biomes.RIVER, RED_WARM_RIVER, of(
                SubBiomeMatcher.Criterion.ofRange(
                    CriterionTargets.TEMPERATURE, SubBiomeMatcher.CriterionTypes.VALUE,
                    0.55f, 1f, false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    CriterionTargets.EROSION, SubBiomeMatcher.CriterionTypes.VALUE,
                    -1F, 0.0f, false
                )
            )
        )
        BiomePlacement.addSubOverworld(
            Biomes.RIVER, WARM_RIVER, of(
                SubBiomeMatcher.Criterion.ofRange(
                    CriterionTargets.TEMPERATURE, SubBiomeMatcher.CriterionTypes.VALUE,
                    0.55f, 1f, false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    CriterionTargets.EROSION, SubBiomeMatcher.CriterionTypes.VALUE,
                    0F, 1f, false
                )
            )
        )
        BiomePlacement.addSubOverworld(
            Biomes.WARM_OCEAN, RED_WARM_OCEAN,
            redSandVariant
        )
        BiomePlacement.addSubOverworld(
            Biomes.LUKEWARM_OCEAN, RED_LUKEWARM_OCEAN,
            redSandVariant
        )
        BiomePlacement.addSubOverworld(
            Biomes.DEEP_LUKEWARM_OCEAN, DEEP_RED_LUKEWARM_OCEAN,
            redSandVariant
        )
        BiomePlacement.addSubOverworld(
            Biomes.BEACH, RED_BEACH,
            redSandInlandVariant

        )
        BiomePlacement.addSubOverworld(
            Biomes.SNOWY_BEACH, SNOWY_RED_BEACH,
            redSandInlandVariant

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
                    0.3f, 1f, false
                )
            )
        )
        BiomePlacement.addSubOverworld(
            Biomes.MUSHROOM_FIELDS, MUSHROOM_GROVE, of(
                SubBiomeMatcher.Criterion.ofRange(
                    CriterionTargets.HUMIDITY, SubBiomeMatcher.CriterionTypes.VALUE,
                    0.1f, 1f, false
                ),
                SubBiomeMatcher.Criterion.ofRange(
                    CriterionTargets.WEIRDNESS, SubBiomeMatcher.CriterionTypes.VALUE,
                    0f, 1f, false
                )
            )
        )
        addOverworld(
            ERODED_MUSHROOM_ISLAND,
            Range(-1, 1),        // Temperature
            Range(-1, -0.3),      // Humidity
            Range(-1.2, -0.95),          // Continentalness
            Range(-1, 1),         // Erosion
            Range(-1, 1),         // Weirdness
        )
        addOverworld(
            ERODED_MUSHROOM_ISLAND,
            Range(-1, 1),        // Temperature
            Range(-1, 0.1),      // Humidity
            Range(-1.2, -0.95),          // Continentalness
            Range(-1, 1),         // Erosion
            Range(0, 1),         // Weirdness
        )
        BiomePlacement.addOverworld(
            MUSHROOM_CAVES,
            createNoise(
                Range(-1, 1),        // Temperature
                Range(-1, 0.7),      // Humidity
                Range(-1.2, -1.05),          // Continentalness
                Range(-1, 1),         // Erosion
                Range(0.2, 0.9),         // Depth
                Range(-1, 1),         // Weirdness
                0L                  // Offset
            )
        )
        BiomePlacement.addOverworld(
            MUSHROOM_CAVES,
            createNoise(
                Range(-1, 1),        // Temperature
                Range(-1, 1),      // Humidity
                Range(-1.2, -0.95),          // Continentalness
                Range(-1, 1),         // Erosion
                Range(0.2, 0.9),         // Depth
                Range(-1, 1),         // Weirdness
                0L                  // Offset
            )
        )
        BiomePlacement.addOverworld(
            FROZEN_CAVERNS,
            createNoise(
                Range(-1, -0.9),        // Temperature
                Range(-1, -0.7),      // Humidity
                Range(-1, 0.8),          // Continentalness
                Range(-1, 1),         // Erosion
                Range(0.2, 0.9),         // Depth
                Range(-1, 1),         // Weirdness
                0L                  // Offset
            )
        )
        BiomePlacement.addOverworld(
            SAND_CAVES,
            createNoise(
                Range(2 / 3, 1),        // Temperature
                Range(-1, -0.65),      // Humidity
                Range(-1, 0.8),          // Continentalness
                Range(0.05, 1),         // Erosion
                Range(0.2, 0.9),         // Depth
                Range(-1, 1),         // Weirdness
                0L                  // Offset
            )
        )
        BiomePlacement.addOverworld(
            RED_SAND_CAVES,
            createNoise(
                Range(2 / 3, 1),        // Temperature
                Range(-1, -0.65),      // Humidity
                Range(-1, 0.8),          // Continentalness
                Range(-1, 0.05),         // Erosion
                Range(0.2, 0.9),         // Depth
                Range(-1, 1),         // Weirdness
                0L                  // Offset
            )
        )
        BiomePlacement.addOverworld(
            GRAVEL_CAVES,
            createNoise(
                Range(-0.9, 1),        // Temperature
                Range(-1, -0.8),      // Humidity
                Range(-0.95, 0.8),          // Continentalness
                Range(-1, 1),         // Erosion
                Range(0.2, 0.9),         // Depth
                Range(-1, 1),         // Weirdness
                0L                  // Offset
            )
        )


//      For TerraBlender compatibility, it is important the rulesOwner's
//      namespace should be the identical to the namespace of all biomes to which the rules apply.
        SurfaceGeneration.addOverworldSurfaceRules(mc("rules/overworld"), DuskSurfaceRules.overworld())
    }

    fun create(id: String): RegistryKey<Biome> = RegistryKey.of(RegistryKeys.BIOME, id(id))

    fun createNoise(
        temperature: Range, humidity: Range, continentalness: Range, erosion: Range,
        depth: Range, weirdness: Range, offset: Long
    ): NoiseHypercube = NoiseHypercube(
        temperature.toParameterRange(),
        humidity.toParameterRange(),
        continentalness.toParameterRange(),
        erosion.toParameterRange(),
        depth.toParameterRange(),
        weirdness.toParameterRange(),
        offset
    )

    fun addFrozenBadlands(
        continentalness: Range, erosion: Range
    ) {
        addOverworld(
            FROZEN_BADLANDS,
            Range(-1, -0.45),        // Temperature
            Range(-0.1, 0.1),      // Humidity
            continentalness,
            erosion,
            Range(-1, -0.05),         // Weirdness
        )
        addOverworld(
            FROZEN_BADLANDS,
            Range(-1, -0.45),        // Temperature
            Range(-0.35, 0.1),      // Humidity
            continentalness,
            erosion,
            Range(0.05, 1),         // Weirdness
        )
        addOverworld(
            FROZEN_WOODED_BADLANDS,
            Range(-1, -0.45),        // Temperature
            Range(0.1, 1),      // Humidity
            continentalness,
            erosion,
            Range(0.05, 1),         // Weirdness
        )
        addOverworld(
            FROZEN_WOODED_BADLANDS,
            Range(-1, -0.45),        // Temperature
            Range(0.1, 1),      // Humidity
            continentalness,
            erosion,
            Range(-1, -0.05),         // Weirdness
        )
        addOverworld(
            FROZEN_ERODED_BADLANDS,
            Range(-1, -0.45),        // Temperature
            Range(-1, -0.35),      // Humidity
            continentalness,
            erosion,
            Range(0.05, 1),         // Weirdness
        )
        addOverworld(
            FROZEN_ERODED_BADLANDS,
            Range(-1, -0.45),        // Temperature
            Range(-1, -0.1),      // Humidity
            continentalness,
            erosion,
            Range(-1, -0.05),         // Weirdness
        )
    }

    fun addOverworld(
        biome: RegistryKey<Biome>, temperature: Range, humidity: Range,
        continentalness: Range, erosion: Range, weirdness: Range
    ) {
        BiomePlacement.addOverworld(
            biome,
            createNoise(
                temperature,        // Temperature
                humidity,            // Humidity
                continentalness,          // Continentalness
                erosion,         // Erosion
                Range(0.0),         // Depth
                weirdness,         // Weirdness
                0L                  // Offset
            )
        )
        BiomePlacement.addOverworld(
            biome,
            createNoise(
                temperature,        // Temperature
                humidity,            // Humidity
                continentalness,          // Continentalness
                erosion,         // Erosion
                Range(1),         // Depth
                weirdness,         // Weirdness
                0L                  // Offset
            )
        )
    }

    fun addOverworld(
        biome: RegistryKey<Biome>, temperature: Range, humidity: Range,
        continentalness: Range, erosion: Range
    ) {
        BiomePlacement.addOverworld(
            biome,
            createNoise(
                temperature,        // Temperature
                humidity,            // Humidity
                continentalness,          // Continentalness
                erosion,         // Erosion
                Range(0.0),         // Depth
                Range(0.05, 1),         // Weirdness
                0L                  // Offset
            )
        )
        BiomePlacement.addOverworld(
            biome,
            createNoise(
                temperature,        // Temperature
                humidity,            // Humidity
                continentalness,          // Continentalness
                erosion,         // Erosion
                Range(0.0),         // Depth
                Range(-1, -0.05),         // Weirdness
                0L                  // Offset
            )
        )
        BiomePlacement.addOverworld(
            biome,
            createNoise(
                temperature,        // Temperature
                humidity,            // Humidity
                continentalness,          // Continentalness
                erosion,         // Erosion
                Range(1),         // Depth
                Range(0.05, 1),         // Weirdness
                0L                  // Offset
            )
        )
        BiomePlacement.addOverworld(
            biome,
            createNoise(
                temperature,        // Temperature
                humidity,            // Humidity
                continentalness,          // Continentalness
                erosion,         // Erosion
                Range(1),         // Depth
                Range(-1, -0.05),         // Weirdness
                0L                  // Offset
            )
        )
    }
}

/*
        BiomePlacement.addOverworld(
            DEVILS_ROAR,
            makeNoise(
                Range(-1, 1),        // Temperature
                Range(-1, 1),      // Humidity
                Range(-1.05, -0.415),          // Continentalness
                Range(-0.78, -0.375),         // Erosion
                Range(0),         // Depth
                Range(-1, 0),         // Weirdness
                0L                  // Offset
            )
        )
 */
