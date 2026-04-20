package org.teamvoided.dusks_biomes.datagen.data.lihto.worldgen_modifiers

import dev.worldgen.lithostitched.api.worldgen.modifier.WorldgenModifier
import dev.worldgen.lithostitched.api.worldgen.util.BiomeEffects
import net.minecraft.core.HolderSet
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.world.level.biome.Biomes
import org.teamvoided.dusks_biomes.data.litho.DuskWorldgenModifiers
import java.util.*

fun BootstrapContext<WorldgenModifier>.modifyBiomeEffects() {
    val biomes = lookup(Registries.BIOME)
    register(
        DuskWorldgenModifiers.ADJUST_DESERT_COLORS,
        WorldgenModifier.builder().replaceEffects(
            HolderSet.direct(
                biomes.getOrThrow(Biomes.DESERT),
                biomes.getOrThrow(Biomes.BADLANDS),
                biomes.getOrThrow(Biomes.ERODED_BADLANDS),
                biomes.getOrThrow(Biomes.WOODED_BADLANDS),
            ),
            BiomeEffects(
                Optional.empty(),
                Optional.of(4445678),
                Optional.of(270131),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
            )
        )
    )

    register(
        DuskWorldgenModifiers.ADJUST_DEEP_DARK_COLORS,
        WorldgenModifier.builder().replaceEffects(
            biomes.getOrThrow(Biomes.DEEP_DARK),
            BiomeEffects(
                Optional.empty(),
                Optional.of(415592),
                Optional.of(415592),
                Optional.empty(),
                Optional.of(2012068),
                Optional.of(2012068),
                Optional.of(2012068),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
            )
        )
    )

    register(
        DuskWorldgenModifiers.ADJUST_LUSH_CAVE_COLORS,
        WorldgenModifier.builder().replaceEffects(
            biomes.getOrThrow(Biomes.LUSH_CAVES),
            BiomeEffects(
                Optional.empty(),
                Optional.of(4445678),
                Optional.of(270131),
                Optional.empty(),
                Optional.of(10866506),
                Optional.of(10140753),
                Optional.of(10140753),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
            )
        )
    )

    register(
        DuskWorldgenModifiers.ADJUST_DRIPSTONE_CAVE_COLORS,
        WorldgenModifier.builder().replaceEffects(
            biomes.getOrThrow(Biomes.DRIPSTONE_CAVES),
            BiomeEffects(
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.of(10665583),
                Optional.of(10665583),
                Optional.of(10665583),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty(),
            )
        )
    )
}