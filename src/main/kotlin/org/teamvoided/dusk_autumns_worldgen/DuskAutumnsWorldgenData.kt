package org.teamvoided.dusk_autumns_worldgen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistrySetBuilder
import net.minecraft.structure.pool.StructurePool
import net.minecraft.structure.pool.StructurePools
import net.minecraft.structure.processor.StructureProcessorLists
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.log
import org.teamvoided.dusk_autumns_worldgen.datagen.BiomeTagsProvider
import org.teamvoided.dusk_autumns_worldgen.datagen.WorldgenProvider
import org.teamvoided.dusk_autumns_worldgen.datagen.AdvancementsProvider
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
    }

    override fun buildRegistry(gen: RegistrySetBuilder) {
        gen.add(RegistryKeys.BIOME, BiomeCreator::boostrap)
        gen.add(RegistryKeys.CONFIGURED_FEATURE, ConfiguredFeatureCreator::bootstrap)
        gen.add(RegistryKeys.PLACED_FEATURE, PlacedFeatureCreator::bootstrap)
    }
}