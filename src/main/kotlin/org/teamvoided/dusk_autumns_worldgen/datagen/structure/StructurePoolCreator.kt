package org.teamvoided.dusk_autumns_worldgen.datagen.structure

import com.mojang.datafixers.util.Pair
import net.minecraft.registry.Holder
import net.minecraft.registry.HolderProvider
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.structure.pool.StructurePool
import net.minecraft.structure.pool.StructurePoolElement
import net.minecraft.structure.pool.StructurePools
import net.minecraft.structure.processor.StructureProcessorList
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
            DuskStructurePools.DESERT_RUINS_OBELISK,
            StructurePool(
                emptyPool,
                listOf(
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruins/obelisk",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 20
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruins/black_obelisk",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    )
                ),
                StructurePool.Projection.RIGID
            )
        )
        context.register(
            DuskStructurePools.DESERT_RUINS_OBELISK_TOP,
            StructurePool(
                emptyPool,
                listOf(
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruins/obelisk_top",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruins/black_obelisk_top",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    )
                ),
                StructurePool.Projection.RIGID
            )
        )
        context.register(
            DuskStructurePools.DESERT_RUINS_ROADS,
            StructurePool(
                emptyPool,
                listOf(
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/long_road_end",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_end_1",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_section_1",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_section_2",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_section_3",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_section_4",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_spacer_1",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    )
                ),
                StructurePool.Projection.RIGID
            )
        )
        context.register(
            DuskStructurePools.DESERT_RUINS_LARGE_RUINS,
            StructurePool(
                emptyPool,
                listOf(
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruin_1",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruin_2",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    )
                ),
                StructurePool.Projection.RIGID
            )
        )
        context.register(
            DuskStructurePools.DESERT_RUINS_RUINS,
            StructurePool(
                emptyPool,
                listOf(
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_1",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_2",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_3",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_4",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_5",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_6",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_7",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_8",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    )
                ),
                StructurePool.Projection.RIGID
            )
        )
        generateRedDesertRuin(context, emptyPool, processorLists)
    }

    fun generateRedDesertRuin(context: BootstrapContext<StructurePool>, emptyPool: Holder<StructurePool>, processorLists: HolderProvider<StructureProcessorList>){
        context.register(
            DuskStructurePools.RED_DESERT_RUINS_OBELISK,
            StructurePool(
                emptyPool,
                listOf(
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruins/obelisk",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 20
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruins/black_obelisk",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    )
                ),
                StructurePool.Projection.RIGID
            )
        )
        context.register(
            DuskStructurePools.RED_DESERT_RUINS_OBELISK_TOP,
            StructurePool(
                emptyPool,
                listOf(
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruins/obelisk_top",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruins/black_obelisk_top",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    )
                ),
                StructurePool.Projection.RIGID
            )
        )
        context.register(
            DuskStructurePools.RED_DESERT_RUINS_ROADS,
            StructurePool(
                emptyPool,
                listOf(
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/long_road_end",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_end_1",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_section_1",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_section_2",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_section_3",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_section_4",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_spacer_1",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    )
                ),
                StructurePool.Projection.RIGID
            )
        )
        context.register(
            DuskStructurePools.RED_DESERT_RUINS_LARGE_RUINS,
            StructurePool(
                emptyPool,
                listOf(
                    Pair.of(
                        StructurePoolElement.ofEmpty(), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruin_1",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 2
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruin_2",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 2
                    )
                ),
                StructurePool.Projection.RIGID
            )
        )
        context.register(
            DuskStructurePools.RED_DESERT_RUINS_RUINS,
            StructurePool(
                emptyPool,
                listOf(
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_1",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_2",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_3",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_4",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_5",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_6",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_7",
                            processorLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_8",
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