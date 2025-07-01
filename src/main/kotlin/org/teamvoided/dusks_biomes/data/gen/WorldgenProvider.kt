package org.teamvoided.dusks_biomes.data.gen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.RegistryKeys
import java.util.concurrent.CompletableFuture

class WorldgenProvider(o: FabricDataOutput, r: CompletableFuture<RegistryWrapper.WrapperLookup>) :
    FabricDynamicRegistryProvider(o, r) {

    override fun getName(): String = "dusk-worldgen"

    override fun configure(reg: RegistryWrapper.WrapperLookup, e: Entries) {
        e.addAll(reg.getOrThrow(RegistryKeys.BIOME))
        e.addAll(reg.getOrThrow(RegistryKeys.PLACED_FEATURE))
        e.addAll(reg.getOrThrow(RegistryKeys.CONFIGURED_FEATURE))
        e.addAll(reg.getOrThrow(RegistryKeys.DENSITY_FUNCTION))

        e.addAll(reg.getOrThrow(RegistryKeys.PROCESSOR_LIST))
        e.addAll(reg.getOrThrow(RegistryKeys.TEMPLATE_POOL))
        e.addAll(reg.getOrThrow(RegistryKeys.STRUCTURE_SET))
        e.addAll(reg.getOrThrow(RegistryKeys.STRUCTURE))
    }
}
