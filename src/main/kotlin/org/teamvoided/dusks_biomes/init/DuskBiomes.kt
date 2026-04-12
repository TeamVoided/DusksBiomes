package org.teamvoided.dusks_biomes.init

import com.terraformersmc.biolith.api.biome.BiomePlacement
import com.terraformersmc.biolith.api.biome.sub.BiomeParameterTargets
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder
import com.terraformersmc.biolith.api.surface.SurfaceGeneration
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.Biomes
import net.minecraft.world.level.biome.Climate.ParameterPoint
import org.teamvoided.dusks_biomes.DusksBiomes.id
import org.teamvoided.dusks_biomes.DusksBiomes.mc
import org.teamvoided.dusks_biomes.data.world.gen.DuskSurfaceRules
import org.teamvoided.dusks_biomes.util.Range
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
    val PALE_CAVES = create("pale_caves")

    fun init() {
        val redSandInlandVariant = CriterionBuilder.value(BiomeParameterTargets.EROSION, -1F, 0.05f)

        BiomePlacement.addSubOverworld(
            Biomes.TAIGA, SNOWY_OLD_GROWTH_SPRUCE_TAIGA, CriterionBuilder.allOf(
                CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, -1f, -0.45f),
                CriterionBuilder.value(BiomeParameterTargets.WEIRDNESS, -1F, 0f),
            )
        )
        BiomePlacement.addSubOverworld(
            Biomes.TAIGA, SNOWY_OLD_GROWTH_PINE_TAIGA, CriterionBuilder.allOf(
                CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, -1f, -0.45f),
                CriterionBuilder.value(BiomeParameterTargets.WEIRDNESS, 0F, 1f),
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
        addOverworld(
            FROZEN_MANGROVE_SWAMP,
            Range(-1, -0.45),        // Temperature
            Range(-1, 1),            // Humidity
            Range(-0.11, 1),          // Continentalness
            Range(0.55, 1.0),         // Erosion
            Range(-0.4, 0.4),         // Weirdness
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
            FROZEN_MANGROVE_SWAMP,
            Range(-1, -0.45),        // Temperature
            Range(-1, 0.1),            // Humidity
            Range(-0.11, 1),          // Continentalness
            Range(0.55, 1.0),         // Erosion
            Range(-1, -0.933),         // Weirdness
        )



//      For TerraBlender compatibility, it is important the rulesOwner's
//      namespace should be the identical to the namespace of all biomes to which the rules apply.
        SurfaceGeneration.addOverworldSurfaceRules(mc("rules/overworld"), DuskSurfaceRules.overworld())
    }

    fun create(id: String): ResourceKey<Biome> {
        val retorn = Registries.BIOME.key(id(id))
        DUSK_BIOMES.add(retorn)
        return retorn
    }

    fun createNoise(
        temperature: Range, humidity: Range, continentalness: Range, erosion: Range,
        depth: Range, weirdness: Range, offset: Long,
    ): ParameterPoint = ParameterPoint(
        temperature.toParameterRange(),
        humidity.toParameterRange(),
        continentalness.toParameterRange(),
        erosion.toParameterRange(),
        depth.toParameterRange(),
        weirdness.toParameterRange(),
        offset
    )

    fun addOverworld(
        biome: ResourceKey<Biome>, temperature: Range, humidity: Range,
        continentalness: Range, erosion: Range, weirdness: Range,
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

}