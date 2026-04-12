package org.teamvoided.dusks_biomes.datagen.data.worldgen.lihto


import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector.ClimateParameter.*
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.world.level.biome.Biomes
import org.teamvoided.dusks_biomes.init.DuskBiomes
import org.teamvoided.dusks_biomes.init.DuskBiomes.DARK_GROVE
import org.teamvoided.dusks_biomes.init.DuskBiomes.DEEP_RED_LUKEWARM_OCEAN
import org.teamvoided.dusks_biomes.init.DuskBiomes.ERODED_MUSHROOM_ISLAND
import org.teamvoided.dusks_biomes.init.DuskBiomes.FROZEN_CAVERNS
import org.teamvoided.dusks_biomes.init.DuskBiomes.FROZEN_MANGROVE_SWAMP
import org.teamvoided.dusks_biomes.init.DuskBiomes.GRAVEL_CAVES
import org.teamvoided.dusks_biomes.init.DuskBiomes.MUSHROOM_CAVES
import org.teamvoided.dusks_biomes.init.DuskBiomes.MUSHROOM_GROVE
import org.teamvoided.dusks_biomes.init.DuskBiomes.PALE_GROVE
import org.teamvoided.dusks_biomes.init.DuskBiomes.RED_BEACH
import org.teamvoided.dusks_biomes.init.DuskBiomes.RED_DESERT
import org.teamvoided.dusks_biomes.init.DuskBiomes.RED_LUKEWARM_OCEAN
import org.teamvoided.dusks_biomes.init.DuskBiomes.RED_SAND_CAVES
import org.teamvoided.dusks_biomes.init.DuskBiomes.RED_WARM_OCEAN
import org.teamvoided.dusks_biomes.init.DuskBiomes.RED_WARM_RIVER
import org.teamvoided.dusks_biomes.init.DuskBiomes.SAND_CAVES
import org.teamvoided.dusks_biomes.init.DuskBiomes.SNOWY_CHERRY_GROVE
import org.teamvoided.dusks_biomes.init.DuskBiomes.SNOWY_RED_BEACH
import org.teamvoided.dusks_biomes.init.DuskBiomes.SNOWY_STONY_SHORE
import org.teamvoided.dusks_biomes.init.DuskBiomes.SNOWY_WINDSWEPT_FOREST
import org.teamvoided.dusks_biomes.init.DuskBiomes.SNOWY_WINDSWEPT_GRAVELLY_HILLS
import org.teamvoided.dusks_biomes.init.DuskBiomes.SNOWY_WINDSWEPT_HILLS
import org.teamvoided.dusks_biomes.init.DuskBiomes.WARM_RIVER
import org.teamvoided.dusks_biomes.init.DuskBiomes.WINDSWEPT_BIRCH_FOREST
import org.teamvoided.dusks_biomes.data.world.gen.DuskBiomeInjectors as DBInject

object BiomeInjectors {

    fun init(c: BootstrapContext<BiomeInjector>) = c.boostrap()

    fun BootstrapContext<BiomeInjector>.boostrap() {

        val snowyVariant = parameter(TEMPERATURE, -1.0, -0.45)
        val coldRegion = parameter(TEMPERATURE, -1.0, -0.25)
        val warmRegion = parameter(TEMPERATURE, 0.25, 1.0)
        val redSandVariant = parameter(EROSION, -1.0, -0.223)
        val redSandInlandVariant = parameter(EROSION, -1.0, 0.005)

        replacePartially(DBInject.COLD_FOREST, Biomes.FOREST, DuskBiomes.COLD_FOREST, coldRegion)
        replacePartially(DBInject.COLD_PLAINS, Biomes.PLAINS, DuskBiomes.COLD_PLAINS, coldRegion)
        replacePartially(DBInject.WARM_FOREST, Biomes.FOREST, DuskBiomes.WARM_FOREST, warmRegion)
        replacePartially(DBInject.WARM_PLAINS, Biomes.PLAINS, DuskBiomes.WARM_PLAINS, warmRegion)

        // region Windswept Variants
        forcePlacement(
            DBInject.WINDSWEPT_BIRCH_FOREST, WINDSWEPT_BIRCH_FOREST,
            parameterMap(
                climateParam(TEMPERATURE, -0.45, 0.2),
                climateParam(HUMIDITY, -1.0, 0.3),
                climateParam(CONTINENTALNESS, -0.19, 0.03),
                climateParam(EROSION, 0.45, 0.55),
                climateParam(WEIRDNESS, 0.05, 1.0),
            )
        )

        replacePartially(
            DBInject.WINDSWEPT_BIRCH_FOREST_REPLACE, Biomes.WINDSWEPT_SAVANNA, WINDSWEPT_BIRCH_FOREST,
            parameter(TEMPERATURE, -1.0, 0.2)
        )

        replacePartially(DBInject.SNOWY_WINDSWEPT_HILLS, Biomes.WINDSWEPT_HILLS, SNOWY_WINDSWEPT_HILLS, snowyVariant)
        replacePartially(
            DBInject.SNOWY_WINDSWEPT_GRAVELLY_HILLS, Biomes.WINDSWEPT_GRAVELLY_HILLS, SNOWY_WINDSWEPT_GRAVELLY_HILLS,
            snowyVariant
        )
        replacePartially(DBInject.SNOWY_WINDSWEPT_FOREST, Biomes.WINDSWEPT_FOREST, SNOWY_WINDSWEPT_FOREST, snowyVariant)
        // endregion

        
        // region Grove
        replacePartially(
            DBInject.DARK_GROVE,
            Biomes.GROVE, DARK_GROVE,
            parameterMap(
                climateParam(HUMIDITY, 0.3, 1.0),
                climateParam(TEMPERATURE, -1.0, -0.15),
                climateParam(CONTINENTALNESS, -1.0, 0.3)
            ),
        )
        replacePartially(
            DBInject.PALE_GROVE,
            Biomes.GROVE, PALE_GROVE,
            parameterMap(
                climateParam(HUMIDITY, 0.3, 1.0),
                climateParam(TEMPERATURE, -0.15, 1.0)
            ),
        )
        replacePartially(
            DBInject.PALE_GROVE_CONTINENTALNESS,
            Biomes.GROVE, PALE_GROVE,
            parameterMap(
                climateParam(HUMIDITY, 0.3, 1.0),
                climateParam(TEMPERATURE, -1.0, -0.15),
                climateParam(CONTINENTALNESS, 0.3, 1.0)
            ),
        )

        replacePartially(
            DBInject.SNOWY_CHERRY_GROVE,
            Biomes.SNOWY_SLOPES, SNOWY_CHERRY_GROVE, 
            parameterMap(
                climateParam(TEMPERATURE, -1.0, -0.45),
                climateParam(HUMIDITY, -1.0, -0.35),
                climateParam(WEIRDNESS, -1.0, 0.0),
            )
        )
        // endregion

        // region Frozen Mangrove Swamp
        replacePartially(
            DBInject.FROZEN_MANGROVE_SWAMP, Biomes.FROZEN_RIVER, FROZEN_MANGROVE_SWAMP,
            parameterMap(
                climateParam(TEMPERATURE, -1.0, -0.45),
                climateParam(EROSION, 0.55, 1.0),
            )
        )
        // endregion

        replacePartially(
            DBInject.ERODED_BADLANDS, Biomes.BADLANDS, Biomes.ERODED_BADLANDS,
            parameter(HUMIDITY, -1.0, -0.35),
        )
        replacePartially(DBInject.RED_DESERT, Biomes.DESERT, RED_DESERT, redSandInlandVariant)

        // region Water Biomes
        replacePartially(
            DBInject.RED_WARM_RIVER, Biomes.RIVER, RED_WARM_RIVER,
            parameterMap(
                climateParam(TEMPERATURE, 0.55, 1.0),
                climateParam(EROSION, -1.0, 0.05),
            )
        )
        replacePartially(
            DBInject.WARM_RIVER, Biomes.RIVER, WARM_RIVER,
            parameterMap(
                climateParam(TEMPERATURE, 0.55, 1.0),
                climateParam(EROSION, 0.05, 1.0),
            )
        )
        replacePartially(DBInject.RED_WARM_OCEAN, Biomes.WARM_OCEAN, RED_WARM_OCEAN, redSandVariant)
        replacePartially(DBInject.RED_LUKEWARM_OCEAN, Biomes.LUKEWARM_OCEAN, RED_LUKEWARM_OCEAN, redSandVariant)
        replacePartially(
            DBInject.DEEP_RED_LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, DEEP_RED_LUKEWARM_OCEAN, redSandVariant
        )
        replacePartially(DBInject.RED_BEACH, Biomes.BEACH, RED_BEACH, redSandInlandVariant)
        replacePartially(DBInject.SNOWY_RED_BEACH, Biomes.SNOWY_BEACH, SNOWY_RED_BEACH, redSandInlandVariant)
        replacePartially(DBInject.SNOWY_STONY_SHORE, Biomes.STONY_SHORE, SNOWY_STONY_SHORE, snowyVariant)
        // endregion

        // region Mushroom
        replacePartially(
            DBInject.MUSHROOM_GROVE, Biomes.MUSHROOM_FIELDS, MUSHROOM_GROVE,
            parameter(HUMIDITY, 0.3, 1.0)
        )
        replacePartially(
            DBInject.MUSHROOM_GROVE_WEIRDNESS, Biomes.MUSHROOM_FIELDS, MUSHROOM_GROVE,
            parameterMap(
                climateParam(HUMIDITY, 0.1, 1.0),
                climateParam(WEIRDNESS, -1.0, 0.0),
            )
        )
        replacePartially(
            DBInject.ERODED_MUSHROOM_ISLAND, Biomes.MUSHROOM_FIELDS, ERODED_MUSHROOM_ISLAND,
            parameter(HUMIDITY, -1.0, -0.35),
        )
        replacePartially(
            DBInject.ERODED_MUSHROOM_ISLAND_HUMIDITY, Biomes.MUSHROOM_FIELDS, ERODED_MUSHROOM_ISLAND,
            parameterMap(
                climateParam(HUMIDITY, -1.0, -0.1),
                climateParam(WEIRDNESS, 0.0, 1.0),
            )
        )
        // endregion

        // region Caves
        val caveParms = parameter(DEPTH, 0.2, 1.0)

        forcePlacement(
            DBInject.MUSHROOM_CAVES, MUSHROOM_CAVES,
            parameterMap(
                climateParam(TEMPERATURE, -1.0, 1.0),
                climateParam(HUMIDITY, -1.0, 0.7),
                climateParam(CONTINENTALNESS, -1.2, -1.05),
                climateParam(EROSION, -1.0, 1.0),
                climateParam(DEPTH, 0.2, 0.9),
                climateParam(WEIRDNESS, -1.0, 1.0),
            )
        )

        forcePlacement(
            DBInject.FROZEN_CAVERNS, FROZEN_CAVERNS,
            parameterMap(
                climateParam(TEMPERATURE, -1.0, -0.9),
                climateParam(HUMIDITY, -1.0, -0.7),
                climateParam(CONTINENTALNESS, -1.0, 0.8),
                climateParam(EROSION, -1.0, 1.0),
                climateParam(DEPTH, 0.2, 0.9),
                climateParam(WEIRDNESS, -1.0, 1.0),
            )
        )
        replacePartially(DBInject.FROZEN_CAVERNS_ICE_SPIKE, Biomes.ICE_SPIKES, FROZEN_CAVERNS, caveParms)
        replacePartially(DBInject.FROZEN_CAVERNS_PEAKS, Biomes.FROZEN_PEAKS, FROZEN_CAVERNS, caveParms)
        replacePartially(DBInject.FROZEN_CAVERNS_FROZEN_OCEAN, Biomes.FROZEN_OCEAN, FROZEN_CAVERNS, caveParms)

        forcePlacement(
            DBInject.SAND_CAVES, SAND_CAVES,
            parameterMap(
                climateParam(TEMPERATURE, 0.8, 1.0),
                climateParam(HUMIDITY, -1.0, -0.65),
                climateParam(CONTINENTALNESS, -1.0, 0.8),
                climateParam(EROSION, 0.05, 1.0),
                climateParam(DEPTH, 0.2, 0.9),
                climateParam(WEIRDNESS, -1.0, 1.0),
            )
        )
        replacePartially(DBInject.SAND_CAVES_DESERT, Biomes.DESERT, SAND_CAVES, caveParms)
        replacePartially(DBInject.SAND_CAVES_WARM_OCEAN, Biomes.WARM_OCEAN, SAND_CAVES, caveParms)

        forcePlacement(
            DBInject.RED_SAND_CAVES, RED_SAND_CAVES,
            parameterMap(
                climateParam(TEMPERATURE, 0.8, 1.0),
                climateParam(HUMIDITY, -1.0, -0.65),
                climateParam(CONTINENTALNESS, -1.0, 0.8),
                climateParam(EROSION, -1.0, 0.05),
                climateParam(DEPTH, 0.2, 0.9),
                climateParam(WEIRDNESS, -1.0, 1.0),
            )
        )

        replacePartially(DBInject.RED_SAND_CAVES_BADLANDS, ConventionalBiomeTags.IS_BADLANDS, RED_SAND_CAVES, caveParms)

        forcePlacement(
            DBInject.GRAVEL_CAVES, GRAVEL_CAVES,
            parameterMap(
                climateParam(TEMPERATURE, -0.9, 1.0),
                climateParam(HUMIDITY, -1.0, -0.8),
                climateParam(CONTINENTALNESS, -0.95, 0.8),
                climateParam(EROSION, -1.0, 1.0),
                climateParam(DEPTH, 0.2, 0.9),
                climateParam(WEIRDNESS, -1.0, 1.0),
            )
        )

        // endregion
    }
}
