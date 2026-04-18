package org.teamvoided.dusks_biomes.datagen.data.worldgen.lihto.worldgen_modifiers

import dev.worldgen.lithostitched.api.worldgen.modifier.WorldgenModifier
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.world.level.dimension.LevelStem
import org.teamvoided.dusks_biomes.data.world.gen.DuskSurfaceRules
import org.teamvoided.dusks_biomes.data.world.gen.litho.DuskWorldgenModifiers

fun BootstrapContext<WorldgenModifier>.addSurfaceRules() {
    register(
        DuskWorldgenModifiers.DUSKS_PRE_RULES,
        WorldgenModifier.builder().prependSurfaceRule(
            LevelStem.OVERWORLD,
            DuskSurfaceRules.overworld()
        )
    )

    register(
        DuskWorldgenModifiers.DUSKS_POST_RULES,
        WorldgenModifier.builder().appendSurfaceRule(
            LevelStem.OVERWORLD,
            DuskSurfaceRules.overworld()
        )
    )
}