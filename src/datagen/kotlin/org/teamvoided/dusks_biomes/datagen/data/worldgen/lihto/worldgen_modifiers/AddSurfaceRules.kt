package org.teamvoided.dusks_biomes.datagen.data.worldgen.lihto.worldgen_modifiers

import dev.worldgen.lithostitched.api.worldgen.modifier.WorldgenModifier
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.world.level.dimension.LevelStem
import org.teamvoided.dusks_biomes.data.world.gen.litho.DuskWorldgenModifiers
import org.teamvoided.dusks_biomes.datagen.data.worldgen.DSurfaceRules

fun BootstrapContext<WorldgenModifier>.addSurfaceRules() {
    register(
        DuskWorldgenModifiers.DUSKS_PRE_RULES,
        WorldgenModifier.builder().prependSurfaceRule(
            LevelStem.OVERWORLD,
            DSurfaceRules.overworld()
        )
    )
/*
    register(
        DuskWorldgenModifiers.DUSKS_POST_RULES,
        WorldgenModifier.builder().appendSurfaceRule(
            LevelStem.OVERWORLD,
            DuskSurfaceRules.overworld()
        )
    )*/
}