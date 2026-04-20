package org.teamvoided.dusks_biomes.datagen.data.lihto.worldgen_modifiers

import dev.worldgen.lithostitched.api.worldgen.modifier.WorldgenModifier
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.world.level.levelgen.structure.BuiltinStructureSets
import net.minecraft.world.level.levelgen.structure.StructureSet
import org.teamvoided.dusks_biomes.data.structure.DuskStructureFeatures
import org.teamvoided.dusks_biomes.data.litho.DuskWorldgenModifiers

fun BootstrapContext<WorldgenModifier>.addStructures() {
    val structureSets = lookup(Registries.STRUCTURE_SET)
    val structures = lookup(Registries.STRUCTURE)

    register(
        DuskWorldgenModifiers.ADD_RUIN_WARM_RED,
        WorldgenModifier.builder().addStructureSetEntries(
            structureSets.getOrThrow(BuiltinStructureSets.OCEAN_RUINS),
            StructureSet.StructureSelectionEntry(
                structures.getOrThrow(DuskStructureFeatures.OCEAN_RUIN_WARM_RED),
                1
            )
        )
    )

    register(
        DuskWorldgenModifiers.ADD_VILLAGES,
        WorldgenModifier.builder().addStructureSetEntries(
            structureSets.getOrThrow(BuiltinStructureSets.OCEAN_RUINS),
            StructureSet.StructureSelectionEntry(
                structures.getOrThrow(DuskStructureFeatures.SWAMP_VILLAGE),
                1
            ),
            StructureSet.StructureSelectionEntry(
                structures.getOrThrow(DuskStructureFeatures.MANGROVE_SWAMP_VILLAGE),
                1
            )
        )
    )
}