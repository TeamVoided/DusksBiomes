package org.teamvoided.dusks_biomes.datagen.data.worldgen.lihto

import com.mojang.datafixers.util.Either
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector.ClimateParameter
import dev.worldgen.lithostitched.impl.worldgen.biomeinjector.ReplacePartially
import dev.worldgen.lithostitched.impl.worldgen.biomeinjector.internal.ParameterMap
import net.minecraft.core.HolderSet
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.util.InclusiveRange
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.dimension.LevelStem
import net.minecraft.world.level.levelgen.DensityFunction
import java.util.*

fun BootstrapContext<BiomeInjector>.replacePartially(
    key: ResourceKey<BiomeInjector>,
    target: ResourceKey<Biome>,
    replacement: ResourceKey<Biome>,
    coldRegion: ParameterMap,
) {
    val biomes = lookup(Registries.BIOME)
    register(
        key,
        ReplacePartially(
            Optional.empty(),
            LevelStem.OVERWORLD,
            10,
            HolderSet.direct(biomes.getOrThrow(target)),
            biomes.getOrThrow(replacement),
            coldRegion
        )
    )
}

typealias WorldPlacement = Pair<Either<ClimateParameter, DensityFunction>, InclusiveRange<Double>>

fun parameterMap(vararg param: WorldPlacement): ParameterMap = ParameterMap(param.toMap(), Optional.empty())

fun climateParam(param: ClimateParameter, min: Double, max: Double): WorldPlacement {
    return Either.left<ClimateParameter, DensityFunction>(param) to InclusiveRange(min, max)
}