package org.teamvoided.dusk_autumns_worldgen.datagen.structure

import net.minecraft.registry.HolderProvider
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.BiomeTags
import net.minecraft.structure.pool.StructurePool
import net.minecraft.world.Heightmap
import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.BootstrapContext
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.YOffset
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.heightprovider.ConstantHeightProvider
import net.minecraft.world.gen.structure.TerrainAdjustment
import org.teamvoided.dusk_autumns_worldgen.data.DuskBiomeTags
import org.teamvoided.dusk_autumns_worldgen.init.structure.DuskStructureFeatures
import org.teamvoided.dusk_autumns_worldgen.init.structure.DuskStructurePools

object StructureFeatureCreator {
    fun bootstrap(c : BootstrapContext<StructureFeature>) {
        val biomeTags: HolderProvider<Biome> = c.lookup(RegistryKeys.BIOME)
        val structurePools: HolderProvider<StructurePool> = c.lookup(RegistryKeys.STRUCTURE_POOL)

        c.register(
            DuskStructureFeatures.DESERT_RUINS,
            JigsawFeature(
                StructureFeature.StructureSettings(
                    biomeTags.getTagOrThrow(DuskBiomeTags.HAS_DESERT_RUIN),
                    mapOf(),
                    GenerationStep.Feature.SURFACE_STRUCTURES,
                    TerrainAdjustment.BURY
                ),
                structurePools.getHolderOrThrow(DuskStructurePools.DESERT_RUINS_OBELISK),
                6,
                ConstantHeightProvider.create(YOffset.fixed(-10)),
                false,
                Heightmap.Type.OCEAN_FLOOR_WG
            )
        )
//        c.register(
//            DuskStructureFeatures.RED_DESERT_RUINS,
//            JigsawFeature(
//                StructureFeature.StructureSettings(
//                    biomeTags.getTagOrThrow(DuskBiomeTags.HAS_RED_DESERT_RUIN),
//                    mapOf(),
//                    GenerationStep.Feature.SURFACE_STRUCTURES,
//                    TerrainAdjustment.BURY
//                ),
//                structurePools.getHolderOrThrow(DuskStructurePools.RED_DESERT_RUINS_OBELISK),
//                6,
//                ConstantHeightProvider.create(YOffset.fixed(-10)),
//                false,
//                Heightmap.Type.OCEAN_FLOOR_WG
//            )
//        )
    }
}