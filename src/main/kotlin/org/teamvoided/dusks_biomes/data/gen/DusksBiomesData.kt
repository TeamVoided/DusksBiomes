package org.teamvoided.dusks_biomes.data.gen

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.RegistrySetBuilder
import net.minecraft.core.registries.Registries
import org.teamvoided.dusks_biomes.DusksBiomes.log
import org.teamvoided.dusks_biomes.data.gen.assets.EnglishTranslationProvider
import org.teamvoided.dusks_biomes.data.gen.data.worldgen.structure.StructureFeatureCreator
import org.teamvoided.dusks_biomes.data.gen.data.worldgen.structure.StructurePoolCreator
import org.teamvoided.dusks_biomes.data.gen.data.worldgen.structure.StructureProcessorCreator
import org.teamvoided.dusks_biomes.data.gen.data.worldgen.structure.StructureSetCreator
import org.teamvoided.dusks_biomes.data.gen.data.tags.BiomeTagsProvider
import org.teamvoided.dusks_biomes.data.gen.data.tags.BlockTagsProvider
import org.teamvoided.dusks_biomes.data.gen.data.tags.StructureTagsProvider
import org.teamvoided.dusks_biomes.data.gen.data.worldgen.BiomeCreator
import org.teamvoided.dusks_biomes.data.gen.data.worldgen.ConfiguredFeatureCreator
import org.teamvoided.dusks_biomes.data.gen.data.worldgen.PlacedFeatureCreator
import java.util.concurrent.CompletableFuture

class DusksBiomesData : DataGeneratorEntrypoint {
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

    override fun buildRegistry(gen: RegistrySetBuilder) {
        gen.add(Registries.BIOME, BiomeCreator::boostrap)
        gen.add(Registries.CONFIGURED_FEATURE, ConfiguredFeatureCreator::bootstrap)
        gen.add(Registries.PLACED_FEATURE, PlacedFeatureCreator::bootstrap)

        gen.add(Registries.PROCESSOR_LIST, StructureProcessorCreator::bootstrap)
        gen.add(Registries.TEMPLATE_POOL, StructurePoolCreator::bootstrap)
        gen.add(Registries.STRUCTURE_SET, StructureSetCreator::bootstrap)
        gen.add(Registries.STRUCTURE, StructureFeatureCreator::bootstrap)
    }

    class WorldgenProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
        FabricDynamicRegistryProvider(o, r) {

        override fun getName(): String = "dusk-worldgen"

        override fun configure(reg: HolderLookup.Provider, e: Entries) {
            e.addAll(reg.lookupOrThrow(Registries.BIOME))
            e.addAll(reg.lookupOrThrow(Registries.PLACED_FEATURE))
            e.addAll(reg.lookupOrThrow(Registries.CONFIGURED_FEATURE))
            e.addAll(reg.lookupOrThrow(Registries.DENSITY_FUNCTION))

            e.addAll(reg.lookupOrThrow(Registries.PROCESSOR_LIST))
            e.addAll(reg.lookupOrThrow(Registries.TEMPLATE_POOL))
            e.addAll(reg.lookupOrThrow(Registries.STRUCTURE_SET))
            e.addAll(reg.lookupOrThrow(Registries.STRUCTURE))
        }
    }

}
