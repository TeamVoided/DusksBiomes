package org.teamvoided.dusks_biomes.datagen.data.worldgen.lihto

import dev.worldgen.lithostitched.api.worldgen.modifier.WorldgenModifier
import dev.worldgen.lithostitched.api.worldgen.util.BiomeEffects
import net.minecraft.core.HolderSet
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.world.level.biome.Biomes
import net.minecraft.world.level.levelgen.structure.BuiltinStructureSets
import net.minecraft.world.level.levelgen.structure.StructureSet
import org.teamvoided.dusks_biomes.data.structure.DuskStructureFeatures
import org.teamvoided.dusks_biomes.data.structure.DuskStructureProcessorLists
import org.teamvoided.dusks_biomes.data.tags.DuskPoolTags
import org.teamvoided.dusks_biomes.data.world.gen.litho.DuskWorldgenModifiers
import java.util.*

object WordgenModifiers {

    fun init(c: BootstrapContext<WorldgenModifier>) = c.boostrap()

    fun BootstrapContext<WorldgenModifier>.boostrap() {
        val templatePools = lookup(Registries.TEMPLATE_POOL)
        val processorLists = lookup(Registries.PROCESSOR_LIST)
        val structureSets = lookup(Registries.STRUCTURE_SET)
        val structures = lookup(Registries.STRUCTURE)
        val biomes = lookup(Registries.BIOME)

        register(
            DuskWorldgenModifiers.TRIAL_CHAMBERS_FIX,
            WorldgenModifier.builder().setPoolElementProcessors(
                templatePools.getOrThrow(DuskPoolTags.TRIAL_CHAIN_FIX),
                processorLists.getOrThrow(DuskStructureProcessorLists.TRIAL_CHAMBERS_FIX),
                true
            )
        )

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
    }
}