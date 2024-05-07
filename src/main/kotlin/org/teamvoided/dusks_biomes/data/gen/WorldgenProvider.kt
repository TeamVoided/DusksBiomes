package org.teamvoided.dusks_biomes.data.gen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.RegistryKeys
import java.util.concurrent.CompletableFuture

class WorldgenProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricDynamicRegistryProvider(o, r) {

    override fun getName(): String = "dusk-worldgen"

    override fun configure(reg: HolderLookup.Provider, e: Entries) {
        e.addAll(reg.getLookupOrThrow(RegistryKeys.BIOME))
        e.addAll(reg.getLookupOrThrow(RegistryKeys.PLACED_FEATURE))
        e.addAll(reg.getLookupOrThrow(RegistryKeys.CONFIGURED_FEATURE))
        e.addAll(reg.getLookupOrThrow(RegistryKeys.DENSITY_FUNCTION))

        e.addAll(reg.getLookupOrThrow(RegistryKeys.STRUCTURE_PROCESSOR_LIST))
        e.addAll(reg.getLookupOrThrow(RegistryKeys.STRUCTURE_POOL))
        e.addAll(reg.getLookupOrThrow(RegistryKeys.STRUCTURE_SET))
        e.addAll(reg.getLookupOrThrow(RegistryKeys.STRUCTURE_FEATURE))
    }
}
