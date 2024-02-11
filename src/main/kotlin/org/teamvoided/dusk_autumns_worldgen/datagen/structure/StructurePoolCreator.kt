package org.teamvoided.dusk_autumns_worldgen.datagen.structure

import com.mojang.datafixers.util.Pair
import net.minecraft.registry.Holder
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.structure.pool.StructurePool
import net.minecraft.structure.pool.StructurePoolElement
import net.minecraft.structure.pool.StructurePools
import net.minecraft.structure.processor.StructureProcessorLists
import net.minecraft.world.gen.BootstrapContext
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen
import org.teamvoided.dusk_autumns_worldgen.init.structure.DuskStructurePools

object StructurePoolCreator {
    fun bootstrap(c: BootstrapContext<StructurePool>) {
        generateDesertRuins(c)
    }

    fun generateDesertRuins(context: BootstrapContext<StructurePool>) {
        val structurePools = context.lookup(RegistryKeys.STRUCTURE_POOL)
        val emptyPool: Holder<StructurePool> = structurePools.getHolderOrThrow(StructurePools.EMPTY)
        val processorLists = context.lookup(RegistryKeys.STRUCTURE_PROCESSOR_LIST)

        context.register(
            DuskStructurePools.DESERT_RUINS_OBELISK_TOP,
            StructurePool(
                emptyPool,
                listOf(
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:desert_ruins/obelisk_top",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    )
                ),
                StructurePool.Projection.RIGID
            )
        )
        context.register(
            DuskStructurePools.DESERT_RUINS_OBELISK,
            StructurePool(
                emptyPool,
                listOf(
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:desert_ruins/obelisk_top",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    )
                ),
                StructurePool.Projection.RIGID
            )
        )
    }
    //look to BiomeCreator

    fun init() {}
    fun create(id: String): RegistryKey<StructurePool?> {
        return RegistryKey.of(RegistryKeys.STRUCTURE_POOL, DuskAutumnsWorldgen.id(id))
    }
}