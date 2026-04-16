package org.teamvoided.dusks_biomes.datagen

import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.core.Holder
import net.minecraft.core.HolderLookup
import net.minecraft.core.HolderLookup.RegistryLookup
import net.minecraft.core.RegistrySetBuilder
import net.minecraft.core.registries.Registries
import org.teamvoided.dusks_biomes.DusksBiomes
import org.teamvoided.dusks_biomes.DusksBiomes.log
import org.teamvoided.dusks_biomes.datagen.assets.EnglishTranslationProvider
import org.teamvoided.dusks_biomes.datagen.data.ChestLootTablesProvider
import org.teamvoided.dusks_biomes.datagen.data.tags.BiomeTagsProvider
import org.teamvoided.dusks_biomes.datagen.data.tags.BlockTagsProvider
import org.teamvoided.dusks_biomes.datagen.data.tags.PoolTagsProvider
import org.teamvoided.dusks_biomes.datagen.data.tags.StructureTagsProvider
import org.teamvoided.dusks_biomes.datagen.data.worldgen.BiomeCreator
import org.teamvoided.dusks_biomes.datagen.data.worldgen.ConfiguredFeatureCreator
import org.teamvoided.dusks_biomes.datagen.data.worldgen.PlacedFeatureCreator
import org.teamvoided.dusks_biomes.datagen.data.worldgen.lihto.BiomeInjectors
import org.teamvoided.dusks_biomes.datagen.data.worldgen.lihto.WorldgenModifiers
import org.teamvoided.dusks_biomes.datagen.data.worldgen.structure.StructureFeatureCreator
import org.teamvoided.dusks_biomes.datagen.data.worldgen.structure.StructurePoolCreator
import org.teamvoided.dusks_biomes.datagen.data.worldgen.structure.StructureProcessorCreator
import org.teamvoided.dusks_biomes.datagen.data.worldgen.structure.StructureSetCreator
import java.util.concurrent.CompletableFuture

object DusksBiomesData : DataGeneratorEntrypoint {

    override fun getEffectiveModId(): String = DusksBiomes.MODID

    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        val pack = gen.createPack()
        log.info("Running \"${gen.modContainer.metadata.name}\" Datagen!")

        // Assets
        pack.addProvider(::EnglishTranslationProvider)

        // Data
        pack.addProvider(::BiomeTagsProvider)
        pack.addProvider(::BlockTagsProvider)
        pack.addProvider(::PoolTagsProvider)
        pack.addProvider(::StructureTagsProvider)
        pack.addProvider(::WorldgenProvider)
        pack.addProvider(::AdvancementsProvider)
        pack.addProvider(::ChestLootTablesProvider)
    }

    override fun buildRegistry(gen: RegistrySetBuilder) {
        gen.add(Registries.BIOME, BiomeCreator::boostrap)
        gen.add(Registries.CONFIGURED_FEATURE, ConfiguredFeatureCreator::bootstrap)
        gen.add(Registries.PLACED_FEATURE, PlacedFeatureCreator::bootstrap)

        gen.add(Registries.PROCESSOR_LIST, StructureProcessorCreator::bootstrap)
        gen.add(Registries.TEMPLATE_POOL, StructurePoolCreator::bootstrap)
        gen.add(Registries.STRUCTURE_SET, StructureSetCreator::bootstrap)
        gen.add(Registries.STRUCTURE, StructureFeatureCreator::bootstrap)

        gen.add(LithostitchedRegistries.BIOME_INJECTOR, BiomeInjectors::init)
        gen.add(LithostitchedRegistries.WORLDGEN_MODIFIER, WorldgenModifiers::init)
    }


    class WorldgenProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
        FabricDynamicRegistryProvider(o, r) {

        override fun getName(): String = "Registry Gen"

        override fun configure(reg: HolderLookup.Provider, e: Entries) {
            e.addAll(reg.lookupOrThrow(Registries.BIOME))
            e.addAll(reg.lookupOrThrow(Registries.PLACED_FEATURE))
            e.addAll(reg.lookupOrThrow(Registries.CONFIGURED_FEATURE))
            e.addAll(reg.lookupOrThrow(Registries.DENSITY_FUNCTION))

            e.addAll(reg.lookupOrThrow(Registries.PROCESSOR_LIST))
            e.addAll(reg.lookupOrThrow(Registries.TEMPLATE_POOL))
            e.addAll(reg.lookupOrThrow(Registries.STRUCTURE_SET))
            e.addAll(reg.lookupOrThrow(Registries.STRUCTURE))

            e.addAll(reg.lookupOrThrow(LithostitchedRegistries.BIOME_INJECTOR))
            e.addAll(reg.lookupOrThrow(LithostitchedRegistries.WORLDGEN_MODIFIER))
        }

        fun <T : Any> Entries.addEverything(registry: RegistryLookup<T>): MutableList<Holder<T>> {
            return registry.listElementIds().map { add(registry, it) }.toList()
        }

    }

}