package org.teamvoided.dusks_biomes.data.gen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryBuilder
import org.teamvoided.dusks_biomes.DusksBiomesMod.log
import org.teamvoided.dusks_biomes.data.gen.structure.StructureFeatureCreator
import org.teamvoided.dusks_biomes.data.gen.structure.StructurePoolCreator
import org.teamvoided.dusks_biomes.data.gen.structure.StructureProcessorCreator
import org.teamvoided.dusks_biomes.data.gen.structure.StructureSetCreator
import org.teamvoided.dusks_biomes.data.gen.tags.BiomeTagsProvider
import org.teamvoided.dusks_biomes.data.gen.tags.BlockTagsProvider
import org.teamvoided.dusks_biomes.data.gen.tags.StructureTagsProvider
import org.teamvoided.dusks_biomes.data.gen.world.gen.BiomeCreator
import org.teamvoided.dusks_biomes.data.gen.world.gen.ConfiguredFeatureCreator
import org.teamvoided.dusks_biomes.data.gen.world.gen.PlacedFeatureCreator

class DusksBiomesModData : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        log.info("Hello from DataGen")
        val pack = gen.createPack()

        pack.addProvider(::AdvancementsProvider)

        pack.addProvider(::WorldgenProvider)

        pack.addProvider(::BiomeTagsProvider)
        pack.addProvider(::BlockTagsProvider)
        pack.addProvider(::StructureTagsProvider)

        pack.addProvider(::ChestLootTablesProvider)
//        pack.addProvider(::ArchaeologyLootTablesProvider)

        pack.addProvider(::EnglishTranslationProvider)
    }

    override fun buildRegistry(gen: RegistryBuilder) {
        gen.addRegistry(RegistryKeys.BIOME, BiomeCreator::boostrap)
        gen.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ConfiguredFeatureCreator::bootstrap)
        gen.addRegistry(RegistryKeys.PLACED_FEATURE, PlacedFeatureCreator::bootstrap)

        gen.addRegistry(RegistryKeys.PROCESSOR_LIST, StructureProcessorCreator::bootstrap)
        gen.addRegistry(RegistryKeys.TEMPLATE_POOL, StructurePoolCreator::bootstrap)
        gen.addRegistry(RegistryKeys.STRUCTURE_SET, StructureSetCreator::bootstrap)
        gen.addRegistry(RegistryKeys.STRUCTURE, StructureFeatureCreator::bootstrap)
    }
}
