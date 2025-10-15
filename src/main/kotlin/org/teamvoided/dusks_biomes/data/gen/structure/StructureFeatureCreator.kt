package org.teamvoided.dusks_biomes.data.gen.structure

import net.minecraft.core.HolderSet
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.levelgen.VerticalAnchor
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight
import net.minecraft.world.level.levelgen.structure.Structure
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure
import org.teamvoided.dusks_biomes.data.structure.DuskStructureFeatures
import org.teamvoided.dusks_biomes.data.structure.DuskStructurePools
import org.teamvoided.dusks_biomes.data.tags.DuskBiomeTags

object StructureFeatureCreator {
    fun bootstrap(c: BootstrapContext<Structure>) {
        val biomeTags = c.lookup(Registries.BIOME)
        val structurePools = c.lookup(Registries.TEMPLATE_POOL)

        c.register(
            DuskStructureFeatures.SWAMP_VILLAGE,
            JigsawStructure(
                structureSettings(
                    biomeTags.getOrThrow(DuskBiomeTags.HAS_VILLAGE_SWAMP_STRUCTURE),
                    GenerationStep.Decoration.SURFACE_STRUCTURES,
                    TerrainAdjustment.BEARD_THIN
                ),
                structurePools.getOrThrow(DuskStructurePools.SWAMP_VILLAGE_CENTER),
                6,
                ConstantHeight.of(VerticalAnchor.absolute(0)),
                true,
                Heightmap.Types.WORLD_SURFACE_WG
            )
        )
        c.register(
            DuskStructureFeatures.MANGROVE_SWAMP_VILLAGE,
            JigsawStructure(
                structureSettings(
                    biomeTags.getOrThrow(DuskBiomeTags.HAS_VILLAGE_MANGROVE_SWAMP_STRUCTURE),
                    GenerationStep.Decoration.SURFACE_STRUCTURES,
                    TerrainAdjustment.BEARD_THIN
                ),
                structurePools.getOrThrow(DuskStructurePools.MANGROVE_SWAMP_VILLAGE_CENTER),
                6,
                ConstantHeight.of(VerticalAnchor.absolute(0)),
                true,
                Heightmap.Types.WORLD_SURFACE_WG
            )
        )
//        c.register(
//            DuskStructureFeatures.DESERT_RUINS,
//            JigsawStructure(
//                structureSettings(
//                    biomeTags.getOrThrow(DuskBiomeTags.HAS_DESERT_RUIN),
//                    GenerationStep.Decoration.UNDERGROUND_STRUCTURES,
//                    TerrainAdjustment.BURY
//                ),
//                structurePools.getOrThrow(DuskStructurePools.DESERT_RUINS_LARGE_RUINS),
//                6,
//                ConstantHeight.of(VerticalAnchor.absolute(-8)),
//                false,
//                Heightmap.Types.OCEAN_FLOOR_WG
//            )
//        )
//        c.register(
//            DuskStructureFeatures.RED_DESERT_RUINS,
//            JigsawStructure(
//                structureSettings(
//                    biomeTags.getOrThrow(DuskBiomeTags.HAS_RED_DESERT_RUIN),
//                    GenerationStep.Decoration.UNDERGROUND_STRUCTURES,
//                    TerrainAdjustment.BURY
//                ),
//                structurePools.getOrThrow(DuskStructurePools.RED_DESERT_RUINS_LARGE_RUINS),
//                6,
//                ConstantHeight.of(VerticalAnchor.absolute(-8)),
//                false,
//                Heightmap.Types.OCEAN_FLOOR_WG
//            )
//        )
//        c.register(
//            DuskStructureFeatures.LARGE_DESERT_RUINS,
//            JigsawStructure(
//                structureSettings(
//                    biomeTags.getOrThrow(DuskBiomeTags.HAS_DESERT_RUIN),
//                    GenerationStep.Decoration.UNDERGROUND_STRUCTURES,
//                    TerrainAdjustment.BURY
//                ),
//                structurePools.getOrThrow(DuskStructurePools.DESERT_RUINS_OBELISK),
//                6,
//                ConstantHeight.of(VerticalAnchor.absolute(-12)),
//                false,
//                Heightmap.Types.OCEAN_FLOOR_WG
//            )
//        )
//        c.register(
//            DuskStructureFeatures.LARGE_RED_DESERT_RUINS,
//            JigsawStructure(
//                structureSettings(
//                    biomeTags.getOrThrow(DuskBiomeTags.HAS_RED_DESERT_RUIN),
//                    GenerationStep.Decoration.UNDERGROUND_STRUCTURES,
//                    TerrainAdjustment.BURY
//                ),
//                structurePools.getOrThrow(DuskStructurePools.RED_DESERT_RUINS_OBELISK),
//                6,
//                ConstantHeight.of(VerticalAnchor.absolute(-12)),
//                false,
//                Heightmap.Types.OCEAN_FLOOR_WG
//            )
//        )
    }

    private fun structureSettings(
        biomes: HolderSet<Biome>,
        step: GenerationStep.Decoration,
        terrainAdaptation: TerrainAdjustment,
    ) = Structure.StructureSettings(biomes, mapOf(), step, terrainAdaptation)
}
