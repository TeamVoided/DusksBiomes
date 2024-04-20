package org.teamvoided.dusk_autumns_worldgen.data.gen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistrySetBuilder
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.log
import org.teamvoided.dusk_autumns_worldgen.data.gen.structure.StructureFeatureCreator
import org.teamvoided.dusk_autumns_worldgen.data.gen.structure.StructurePoolCreator
import org.teamvoided.dusk_autumns_worldgen.data.gen.structure.StructureProcessorCreator
import org.teamvoided.dusk_autumns_worldgen.data.gen.structure.StructureSetCreator
import org.teamvoided.dusk_autumns_worldgen.data.gen.tags.BiomeTagsProvider
import org.teamvoided.dusk_autumns_worldgen.data.gen.tags.BlockTagsProvider
import org.teamvoided.dusk_autumns_worldgen.data.gen.tags.StructureTagsProvider
import org.teamvoided.dusk_autumns_worldgen.data.gen.world.gen.BiomeCreator
import org.teamvoided.dusk_autumns_worldgen.data.gen.world.gen.ConfiguredFeatureCreator
import org.teamvoided.dusk_autumns_worldgen.data.gen.world.gen.DensityFunctionCreator
import org.teamvoided.dusk_autumns_worldgen.data.gen.world.gen.PlacedFeatureCreator

class DuskAutumnsWorldgenData : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        log.info("Hello from DataGen")
        val pack = gen.createPack()

        pack.addProvider(::AdvancementsProvider)

        pack.addProvider(::WorldgenProvider)

        pack.addProvider(::BiomeTagsProvider)
        pack.addProvider(::BlockTagsProvider)
        pack.addProvider(::StructureTagsProvider)

        pack.addProvider(::ChestLootTablesProvider)
        pack.addProvider(::ArchaeologyLootTablesProvider)
    }

    override fun buildRegistry(gen: RegistrySetBuilder) {
        gen.add(RegistryKeys.BIOME, BiomeCreator::boostrap)
        gen.add(RegistryKeys.CONFIGURED_FEATURE, ConfiguredFeatureCreator::bootstrap)
        gen.add(RegistryKeys.PLACED_FEATURE, PlacedFeatureCreator::bootstrap)
        gen.add(RegistryKeys.DENSITY_FUNCTION, DensityFunctionCreator::bootstrap)

        gen.add(RegistryKeys.STRUCTURE_PROCESSOR_LIST, StructureProcessorCreator::bootstrap)
        gen.add(RegistryKeys.STRUCTURE_POOL, StructurePoolCreator::bootstrap)
        gen.add(RegistryKeys.STRUCTURE_SET, StructureSetCreator::bootstrap)
        gen.add(RegistryKeys.STRUCTURE_FEATURE, StructureFeatureCreator::bootstrap)
    }
}