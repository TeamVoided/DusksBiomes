package org.teamvoided.dusks_biomes.datagen.data.worldgen.lihto


import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector.ClimateParameter
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.world.level.biome.Biomes
import org.teamvoided.dusks_biomes.init.DuskBiomes
import org.teamvoided.dusks_biomes.init.DuskBiomes.DEEP_RED_LUKEWARM_OCEAN
import org.teamvoided.dusks_biomes.init.DuskBiomes.RED_BEACH
import org.teamvoided.dusks_biomes.init.DuskBiomes.RED_LUKEWARM_OCEAN
import org.teamvoided.dusks_biomes.init.DuskBiomes.RED_WARM_OCEAN
import org.teamvoided.dusks_biomes.init.DuskBiomes.SNOWY_RED_BEACH
import org.teamvoided.dusks_biomes.init.DuskBiomes.SNOWY_STONY_SHORE
import org.teamvoided.dusks_biomes.init.DuskBiomes.SNOWY_WINDSWEPT_FOREST
import org.teamvoided.dusks_biomes.init.DuskBiomes.SNOWY_WINDSWEPT_GRAVELLY_HILLS
import org.teamvoided.dusks_biomes.init.DuskBiomes.SNOWY_WINDSWEPT_HILLS
import org.teamvoided.dusks_biomes.init.DuskBiomes.WINDSWEPT_BIRCH_FOREST
import org.teamvoided.dusks_biomes.data.world.gen.DuskBiomeInjectors as DBInject

object BiomeInjectors {

    fun init(c: BootstrapContext<BiomeInjector>) = c.boostrap()

    fun BootstrapContext<BiomeInjector>.boostrap() {

        val snowyVariant = parameter(ClimateParameter.TEMPERATURE, -1.0, -0.45)
        val coldRegion = parameter(ClimateParameter.TEMPERATURE, -1.0, -0.25)
        val warmRegion = parameter(ClimateParameter.TEMPERATURE, 0.25, 1.0)
        val redSandVariant = parameter(ClimateParameter.EROSION, -1.0, -0.223)
        val redSandInlandVariant = parameter(ClimateParameter.EROSION, -1.0, 0.005)

        replacePartially(DBInject.COLD_FOREST, Biomes.FOREST, DuskBiomes.COLD_FOREST, coldRegion)
        replacePartially(DBInject.COLD_PLAINS, Biomes.PLAINS, DuskBiomes.COLD_PLAINS, coldRegion)
        replacePartially(DBInject.WARM_FOREST, Biomes.FOREST, DuskBiomes.WARM_FOREST, warmRegion)
        replacePartially(DBInject.WARM_PLAINS, Biomes.PLAINS, DuskBiomes.WARM_PLAINS, warmRegion)

        // region Windswept Variants
        forcePlacement(
            DBInject.WINDSWEPT_BIRCH_FOREST,
            WINDSWEPT_BIRCH_FOREST,
            parameterMap(
                climateParam(ClimateParameter.TEMPERATURE, -0.45, 0.2),        // Temperature
                climateParam(ClimateParameter.HUMIDITY, -1.0, 0.3),            // Humidity
                climateParam(ClimateParameter.CONTINENTALNESS, -0.19, 0.03),          // Continentalness
                climateParam(ClimateParameter.EROSION, 0.45, 0.55),         // Erosion
                climateParam(ClimateParameter.WEIRDNESS, 0.05, 1.0),         // Weirdness
            )
        )

        replacePartially(
            DBInject.WINDSWEPT_BIRCH_FOREST_REPLACE,
            Biomes.WINDSWEPT_SAVANNA,
            WINDSWEPT_BIRCH_FOREST,
            parameter(ClimateParameter.TEMPERATURE, -1.0, 0.2)
        )

        replacePartially(DBInject.SNOWY_WINDSWEPT_HILLS, Biomes.WINDSWEPT_HILLS, SNOWY_WINDSWEPT_HILLS, snowyVariant)
        replacePartially(
            DBInject.SNOWY_WINDSWEPT_GRAVELLY_HILLS,
            Biomes.WINDSWEPT_GRAVELLY_HILLS,
            SNOWY_WINDSWEPT_GRAVELLY_HILLS,
            snowyVariant
        )
        replacePartially(DBInject.SNOWY_WINDSWEPT_FOREST, Biomes.WINDSWEPT_FOREST, SNOWY_WINDSWEPT_FOREST, snowyVariant)
        // endregion

        replacePartially(DBInject.RED_WARM_OCEAN, Biomes.WARM_OCEAN, RED_WARM_OCEAN, redSandVariant)
        replacePartially(DBInject.RED_LUKEWARM_OCEAN, Biomes.LUKEWARM_OCEAN, RED_LUKEWARM_OCEAN, redSandVariant)
        replacePartially(
            DBInject.DEEP_RED_LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN, DEEP_RED_LUKEWARM_OCEAN, redSandVariant
        )
        replacePartially(DBInject.RED_BEACH, Biomes.BEACH, RED_BEACH, redSandInlandVariant)
        replacePartially(DBInject.SNOWY_RED_BEACH, Biomes.SNOWY_BEACH, SNOWY_RED_BEACH, redSandInlandVariant)
        replacePartially(DBInject.SNOWY_STONY_SHORE, Biomes.STONY_SHORE, SNOWY_STONY_SHORE, snowyVariant)
    }
}
