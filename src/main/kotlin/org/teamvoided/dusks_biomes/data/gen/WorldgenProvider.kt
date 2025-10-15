package org.teamvoided.dusks_biomes.data.gen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import java.util.concurrent.CompletableFuture

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
