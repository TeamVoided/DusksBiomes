package org.teamvoided.dusk_autumns_worldgen.datagen.structure

import com.mojang.datafixers.util.Pair
import net.minecraft.registry.Holder
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.structure.pool.StructurePool
import net.minecraft.structure.pool.StructurePoolElement
import net.minecraft.structure.pool.StructurePools
import net.minecraft.structure.processor.StructureProcessorList
import net.minecraft.structure.processor.StructureProcessorLists
import net.minecraft.world.gen.BootstrapContext
import org.teamvoided.dusk_autumns_worldgen.init.structure.DuskStructureProcessorLists
import java.util.List

class DesertRuinsGenerator {
    object TrailRuinsGenerator {
        val REGISTRY_KEY: RegistryKey<StructurePool?> = StructurePools.registryKey("trail_ruins/tower")

        fun bootstrap(context: BootstrapContext<StructurePool?>) {
            val holderProvider = context.lookup(RegistryKeys.STRUCTURE_POOL)
            val emptyPool: Holder<StructurePool> = holderProvider.getHolderOrThrow(StructurePools.EMPTY)
            val holderProvider2 = context.lookup(RegistryKeys.STRUCTURE_PROCESSOR_LIST)
            val desertObeliskTopProcessor: Holder<StructureProcessorList> =
                holderProvider2.getHolderOrThrow(DuskStructureProcessorLists.DESERT_OBELISK_TOP_ARCHAEOLOGY)
            val desertRoadProcessor: Holder<StructureProcessorList> =
                holderProvider2.getHolderOrThrow(DuskStructureProcessorLists.DESERT_ROADS_ARCHAEOLOGY)
            val desertSmallRuinsProcessor: Holder<StructureProcessorList> =
                holderProvider2.getHolderOrThrow(DuskStructureProcessorLists.DESERT_SMALL_RUINS_ARCHAEOLOGY)
            val desertLargeRuinsProcessor: Holder<StructureProcessorList> =
                holderProvider2.getHolderOrThrow(DuskStructureProcessorLists.DESERT_LARGE_RUINS_ARCHAEOLOGY)

            context.register(
                REGISTRY_KEY,
                StructurePool(
                    emptyPool,
                    listOf(
                        Pair.of(StructurePoolElement.ofProcessedSingle("trail_ruins/tower/tower_1", desertSmallRuinsProcessor), 1)
                    ),
                    StructurePool.Projection.RIGID
                )
            )
            StructurePools.register(
                context,
                "trail_ruins/tower/tower_top",
                StructurePool(
                    emptyPool,
                    listOf(
                        Pair.of(StructurePoolElement.ofProcessedSingle("trail_ruins/tower/tower_top_1", desertObeliskTopProcessor), 1)
                    ),
                    StructurePool.Projection.RIGID
                )
            )
            StructurePools.register(
                context, "trail_ruins/tower/additions", StructurePool(
                    emptyPool, listOf(
                        Pair.of(StructurePoolElement.ofProcessedSingle("trail_ruins/tower/hall_1", desertSmallRuinsProcessor), 1)
                    ), StructurePool.Projection.RIGID
                )
            )
            StructurePools.register(
                context,
                "trail_ruins/roads",
                StructurePool(
                    emptyPool,
                    listOf(
                        Pair.of(StructurePoolElement.ofProcessedSingle("trail_ruins/roads/road_section_1", desertRoadProcessor), 1)
                    ),
                    StructurePool.Projection.RIGID
                )
            )
            StructurePools.register(
                context, "trail_ruins/buildings", StructurePool(
                    emptyPool, listOf(
                        Pair.of(StructurePoolElement.ofProcessedSingle("trail_ruins/buildings/group_hall_1", desertSmallRuinsProcessor), 1)
                    ), StructurePool.Projection.RIGID
                )
            )
            StructurePools.register(
                context, "trail_ruins/buildings/grouped", StructurePool(
                    emptyPool, listOf(
                        Pair.of(StructurePoolElement.ofProcessedSingle("trail_ruins/buildings/group_full_1", desertSmallRuinsProcessor), 1)
                    ), StructurePool.Projection.RIGID
                )
            )
            StructurePools.register(
                context,
                "trail_ruins/decor",
                StructurePool(
                    emptyPool,
                    listOf(
                        Pair.of(StructurePoolElement.ofProcessedSingle("trail_ruins/decor/decor_1", desertSmallRuinsProcessor), 1)
                    ),
                    StructurePool.Projection.RIGID
                )
            )
        }
    }
}