package org.teamvoided.dusk_autumns_worldgen.datagen.structure

import net.minecraft.entity.SpawnGroup
import net.minecraft.registry.HolderProvider
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.BiomeTags
import net.minecraft.structure.AncientCityGenerator
import net.minecraft.structure.pool.StructurePool
import net.minecraft.unmapped.C_xsycfxyj
import net.minecraft.util.Identifier
import net.minecraft.util.collection.Pool
import net.minecraft.world.Heightmap
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.SpawnSettings.SpawnEntry
import net.minecraft.world.gen.BootstrapContext
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.YOffset
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.feature.StructureSpawnOverride.BoundingBoxType
import net.minecraft.world.gen.heightprovider.ConstantHeightProvider
import net.minecraft.world.gen.structure.BuiltInStructures
import net.minecraft.world.gen.structure.TerrainAdjustment
import org.teamvoided.dusk_autumns_worldgen.data.DuskBiomeTags
import org.teamvoided.dusk_autumns_worldgen.init.structure.DuskStructureFeatures
import org.teamvoided.dusk_autumns_worldgen.init.structure.DuskStructurePools
import java.util.*
import java.util.function.Function
import java.util.stream.Collectors

object StructureFeatureCreator {
    fun bootstrap(c: BootstrapContext<StructureFeature>) {
        val biomeTags: HolderProvider<Biome> = c.lookup(RegistryKeys.BIOME)
        val structurePools: HolderProvider<StructurePool> = c.lookup(RegistryKeys.STRUCTURE_POOL)


        c.register(
            DuskStructureFeatures.SWAMP_VILLAGE,
            JigsawFeature(
                StructureFeature.StructureSettings(
                    biomeTags.getTagOrThrow(DuskBiomeTags.HAS_VILLAGE_SWAMP_STRUCTURE),
                    mapOf(),
                    GenerationStep.Feature.SURFACE_STRUCTURES,
                    TerrainAdjustment.STRUCTURE_WEIGHT_THIN
                ),
                structurePools.getHolderOrThrow(DuskStructurePools.SWAMP_VILLAGE_CENTER),
                6,
                ConstantHeightProvider.create(YOffset.fixed(0)),
                true,
                Heightmap.Type.WORLD_SURFACE_WG
            )
        )
        c.register(
            DuskStructureFeatures.MANGROVE_SWAMP_VILLAGE,
            JigsawFeature(
                StructureFeature.StructureSettings(
                    biomeTags.getTagOrThrow(DuskBiomeTags.HAS_VILLAGE_MANGROVE_SWAMP_STRUCTURE),
                    mapOf(),
                    GenerationStep.Feature.SURFACE_STRUCTURES,
                    TerrainAdjustment.STRUCTURE_WEIGHT_THIN
                ),
        structurePools.getHolderOrThrow(DuskStructurePools.SWAMP_VILLAGE_CENTER),
                6,
                ConstantHeightProvider.create(YOffset.fixed(0)),
                true,
                Heightmap.Type.WORLD_SURFACE_WG
            )
        )
        c.register(
            DuskStructureFeatures.DESERT_RUINS,
            JigsawFeature(
                StructureFeature.StructureSettings(
                    biomeTags.getTagOrThrow(DuskBiomeTags.HAS_DESERT_RUIN),
                    mapOf(),
                    GenerationStep.Feature.UNDERGROUND_STRUCTURES,
                    TerrainAdjustment.BURY
                ),
                structurePools.getHolderOrThrow(DuskStructurePools.DESERT_RUINS_OBELISK),
                Optional.of(Identifier("center")),
                6,
                ConstantHeightProvider.create(YOffset.fixed(-12)),
                false,
                Optional.of(Heightmap.Type.OCEAN_FLOOR_WG),
                80,
                listOf()
            )
        )
        c.register(
            DuskStructureFeatures.RED_DESERT_RUINS,
            JigsawFeature(
                StructureFeature.StructureSettings(
                    biomeTags.getTagOrThrow(DuskBiomeTags.HAS_RED_DESERT_RUIN),
                    mapOf(),
                    GenerationStep.Feature.UNDERGROUND_STRUCTURES,
                    TerrainAdjustment.BURY
                ),
                structurePools.getHolderOrThrow(DuskStructurePools.RED_DESERT_RUINS_OBELISK),
                Optional.of(Identifier("center")),
                6,
                ConstantHeightProvider.create(YOffset.fixed(-12)),
                false,
                Optional.of(Heightmap.Type.OCEAN_FLOOR_WG),
                80,
                listOf()
            )
        )
    }
}