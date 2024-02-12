package org.teamvoided.dusk_autumns_worldgen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistrySetBuilder
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.log
import org.teamvoided.dusk_autumns_worldgen.datagen.*
import org.teamvoided.dusk_autumns_worldgen.datagen.structure.StructureFeatureCreator
import org.teamvoided.dusk_autumns_worldgen.datagen.structure.StructurePoolCreator
import org.teamvoided.dusk_autumns_worldgen.datagen.structure.StructureProcessorCreator
import org.teamvoided.dusk_autumns_worldgen.datagen.structure.StructureSetCreator
import org.teamvoided.dusk_autumns_worldgen.datagen.worldgen.BiomeCreator
import org.teamvoided.dusk_autumns_worldgen.datagen.worldgen.ConfiguredFeatureCreator
import org.teamvoided.dusk_autumns_worldgen.datagen.worldgen.PlacedFeatureCreator

class DuskAutumnsWorldgenData : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        log.info("Hello from DataGen")
        val pack = gen.createPack()

        pack.addProvider(::AdvancementsProvider)
        pack.addProvider(::BiomeTagsProvider)
        pack.addProvider(::WorldgenProvider)
        pack.addProvider(::BlockTagsProvider)
        pack.addProvider(::FluidTagsProvider)
    }

    override fun buildRegistry(gen: RegistrySetBuilder) {
        gen.add(RegistryKeys.BIOME, BiomeCreator::boostrap)
        gen.add(RegistryKeys.CONFIGURED_FEATURE, ConfiguredFeatureCreator::bootstrap)
        gen.add(RegistryKeys.PLACED_FEATURE, PlacedFeatureCreator::bootstrap)

        gen.add(RegistryKeys.STRUCTURE_PROCESSOR_LIST, StructureProcessorCreator::bootstrap)
        gen.add(RegistryKeys.STRUCTURE_POOL, StructurePoolCreator::bootstrap)
        gen.add(RegistryKeys.STRUCTURE_SET, StructureSetCreator::bootstrap)
        gen.add(RegistryKeys.STRUCTURE_FEATURE, StructureFeatureCreator::bootstrap)
    }
}