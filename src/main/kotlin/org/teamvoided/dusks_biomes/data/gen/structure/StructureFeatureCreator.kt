package org.teamvoided.dusks_biomes.data.gen.structure

import net.minecraft.registry.RegistryEntryLookup
import net.minecraft.registry.entry.RegistryEntryList
import net.minecraft.registry.Registerable
import net.minecraft.registry.RegistryKeys
import net.minecraft.structure.pool.StructurePool
import net.minecraft.world.Heightmap
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.YOffset
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.heightprovider.ConstantHeightProvider
import net.minecraft.world.gen.structure.JigsawStructure
import net.minecraft.world.gen.structure.Structure
import net.minecraft.world.gen.StructureTerrainAdaptation
import org.teamvoided.dusks_biomes.data.tags.DuskBiomeTags
import org.teamvoided.dusks_biomes.data.structure.DuskStructureFeatures
import org.teamvoided.dusks_biomes.data.structure.DuskStructurePools

@Suppress("MagicNumber")
object StructureFeatureCreator {
    fun bootstrap(c: Registerable<Structure>) {
        val biomeTags: RegistryEntryLookup<Biome> = c.getRegistryLookup(RegistryKeys.BIOME)
        val structurePools: RegistryEntryLookup<StructurePool> = c.getRegistryLookup(RegistryKeys.TEMPLATE_POOL)


        c.register(
            DuskStructureFeatures.SWAMP_VILLAGE,
            JigsawStructure(
                structureSettings(
                    biomeTags.getOrThrow(DuskBiomeTags.HAS_VILLAGE_SWAMP_STRUCTURE),
                    GenerationStep.Feature.SURFACE_STRUCTURES,
                    StructureTerrainAdaptation.BEARD_THIN
                ),
                structurePools.getOrThrow(DuskStructurePools.SWAMP_VILLAGE_CENTER),
                6,
                ConstantHeightProvider.create(YOffset.fixed(0)),
                true,
                Heightmap.Type.WORLD_SURFACE_WG
            )
        )
        c.register(
            DuskStructureFeatures.MANGROVE_SWAMP_VILLAGE,
            JigsawStructure(
                structureSettings(
                    biomeTags.getOrThrow(DuskBiomeTags.HAS_VILLAGE_MANGROVE_SWAMP_STRUCTURE),
                    GenerationStep.Feature.SURFACE_STRUCTURES,
                    StructureTerrainAdaptation.BEARD_THIN
                ),
                structurePools.getOrThrow(DuskStructurePools.MANGROVE_SWAMP_VILLAGE_CENTER),
                6,
                ConstantHeightProvider.create(YOffset.fixed(0)),
                true,
                Heightmap.Type.WORLD_SURFACE_WG
            )
        )
//        c.register(
//            DuskStructureFeatures.DESERT_RUINS,
//            JigsawStructure(
//                structureSettings(
//                    biomeTags.getOrThrow(DuskBiomeTags.HAS_DESERT_RUIN),
//                    GenerationStep.Feature.UNDERGROUND_STRUCTURES,
//                    TerrainAdjustment.BURY
//                ),
//                structurePools.getOrThrow(DuskStructurePools.DESERT_RUINS_LARGE_RUINS),
//                6,
//                ConstantHeightProvider.create(YOffset.fixed(-8)),
//                false,
//                Heightmap.Type.OCEAN_FLOOR_WG
//            )
//        )
//        c.register(
//            DuskStructureFeatures.RED_DESERT_RUINS,
//            JigsawStructure(
//                structureSettings(
//                    biomeTags.getOrThrow(DuskBiomeTags.HAS_RED_DESERT_RUIN),
//                    GenerationStep.Feature.UNDERGROUND_STRUCTURES,
//                    TerrainAdjustment.BURY
//                ),
//                structurePools.getOrThrow(DuskStructurePools.RED_DESERT_RUINS_LARGE_RUINS),
//                6,
//                ConstantHeightProvider.create(YOffset.fixed(-8)),
//                false,
//                Heightmap.Type.OCEAN_FLOOR_WG
//            )
//        )
//        c.register(
//            DuskStructureFeatures.LARGE_DESERT_RUINS,
//            JigsawStructure(
//                structureSettings(
//                    biomeTags.getOrThrow(DuskBiomeTags.HAS_DESERT_RUIN),
//                    GenerationStep.Feature.UNDERGROUND_STRUCTURES,
//                    TerrainAdjustment.BURY
//                ),
//                structurePools.getOrThrow(DuskStructurePools.DESERT_RUINS_OBELISK),
//                6,
//                ConstantHeightProvider.create(YOffset.fixed(-12)),
//                false,
//                Heightmap.Type.OCEAN_FLOOR_WG
//            )
//        )
//        c.register(
//            DuskStructureFeatures.LARGE_RED_DESERT_RUINS,
//            JigsawStructure(
//                structureSettings(
//                    biomeTags.getOrThrow(DuskBiomeTags.HAS_RED_DESERT_RUIN),
//                    GenerationStep.Feature.UNDERGROUND_STRUCTURES,
//                    TerrainAdjustment.BURY
//                ),
//                structurePools.getOrThrow(DuskStructurePools.RED_DESERT_RUINS_OBELISK),
//                6,
//                ConstantHeightProvider.create(YOffset.fixed(-12)),
//                false,
//                Heightmap.Type.OCEAN_FLOOR_WG
//            )
//        )
    }

    private fun structureSettings(
        biomes: RegistryEntryList<Biome>,
        step: GenerationStep.Feature,
        terrainAdaptation: StructureTerrainAdaptation
    ) = Structure.Config(biomes, mapOf(), step, terrainAdaptation)
}
