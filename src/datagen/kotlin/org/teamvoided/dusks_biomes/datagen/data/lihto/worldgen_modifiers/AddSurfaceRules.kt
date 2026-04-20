package org.teamvoided.dusks_biomes.datagen.data.lihto.worldgen_modifiers

import dev.worldgen.lithostitched.api.worldgen.modifier.WorldgenModifier
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.world.level.dimension.LevelStem
import org.teamvoided.dusks_biomes.data.litho.DuskWorldgenModifiers
import org.teamvoided.dusks_biomes.datagen.data.worldgen.DSurfaceRules

fun BootstrapContext<WorldgenModifier>.addSurfaceRules() {
    register(
        DuskWorldgenModifiers.DUSKS_BIOMES_RULES,
        WorldgenModifier.builder()
            .prependSurfaceRule(LevelStem.OVERWORLD, DSurfaceRules.overworld())
    )
}