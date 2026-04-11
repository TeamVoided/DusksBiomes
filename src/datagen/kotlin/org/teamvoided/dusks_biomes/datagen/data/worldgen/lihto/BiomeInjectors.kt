package org.teamvoided.dusks_biomes.datagen.data.worldgen.lihto


import com.mojang.datafixers.util.Either
import com.terraformersmc.biolith.api.biome.sub.BiomeParameterTargets
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector.ClimateParameter
import dev.worldgen.lithostitched.impl.worldgen.biomeinjector.ReplacePartially
import dev.worldgen.lithostitched.impl.worldgen.biomeinjector.internal.ParameterMap
import net.minecraft.core.HolderSet
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.util.InclusiveRange
import net.minecraft.world.level.biome.Biomes
import net.minecraft.world.level.dimension.LevelStem
import net.minecraft.world.level.levelgen.DensityFunction
import org.teamvoided.dusks_biomes.data.world.gen.DuskBiomeInjectors
import org.teamvoided.dusks_biomes.init.DuskBiomes
import java.util.*

object BiomeInjectors {

    fun boostrap(c: BootstrapContext<BiomeInjector>) {
        val biomes = c.lookup(Registries.BIOME)


        val snowyVariant = CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, -1F, -0.45f)
        val coldRegion2 = CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, -1F, -0.3f)
        val warmRegion = CriterionBuilder.value(BiomeParameterTargets.TEMPERATURE, 0.375F, 1f)
        val redSandVariant = CriterionBuilder.value(BiomeParameterTargets.EROSION, -1F, -0.223f)
        val redSandInlandVariant = CriterionBuilder.value(BiomeParameterTargets.EROSION, -1F, 0.05f)


//        BiomePlacement.addSubOverworld(Biomes.FOREST, COLD_FOREST, coldRegion2)
//        BiomePlacement.addSubOverworld(Biomes.PLAINS, COLD_PLAINS, coldRegion2)
//        BiomePlacement.addSubOverworld(Biomes.FOREST, WARM_FOREST, warmRegion)
//        BiomePlacement.addSubOverworld(Biomes.PLAINS, WARM_PLAINS, warmRegion)


        val coldRegion = ParameterMap(mapOf(climateParam(ClimateParameter.TEMPERATURE, -1.0, -0.3)), Optional.empty())

        c.register(
            DuskBiomeInjectors.COLD_FOREST,
            ReplacePartially(
                Optional.empty(),
                LevelStem.OVERWORLD,
                1,
                HolderSet.direct(biomes.getOrThrow(Biomes.FOREST)),
                biomes.getOrThrow(DuskBiomes.PALE_CAVES),
                coldRegion
            )
        )
    }

    fun climateParam(
        param: ClimateParameter, min: Double, max: Double,
    ): Pair<Either<ClimateParameter, DensityFunction>, InclusiveRange<Double>> {
        return Either.left<ClimateParameter, DensityFunction>(param) to InclusiveRange(min, max)
    }
}
