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

    val zombieChance = (1/50)*100
    fun bootstrap(c: BootstrapContext<StructurePool>) {
        val structurePools = c.lookup(RegistryKeys.STRUCTURE_POOL)
        val empty: Holder<StructurePool> = structurePools.getHolderOrThrow(StructurePools.EMPTY)
        val procLists = c.lookup(RegistryKeys.STRUCTURE_PROCESSOR_LIST)

//        generateSwampVillage(c, empty,procLists)
//        generateMangroveSwampVillage(c, empty,procLists)
        generateDesertRuins(c, empty, procLists)
        generateRedDesertRuin(c, empty, procLists)
    }

    fun generateSwampVillage(c: BootstrapContext<StructurePool>, empty: Holder<StructurePool>, procLists: HolderProvider<StructureProcessorList>){
        c.register(
            DuskStructurePools.RED_DESERT_RUINS_OBELISK,
            StructurePool(
                empty,
                listOf(
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/town_centers/swamp_meeting_point_1",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), zombieChance
                    )
                ),
                StructurePool.Projection.RIGID
            )
        )
    }

    fun generateMangroveSwampVillage(c: BootstrapContext<StructurePool>, empty: Holder<StructurePool>, procLists: HolderProvider<StructureProcessorList>){

    }

    fun generateDesertRuins(c: BootstrapContext<StructurePool>, empty: Holder<StructurePool>, procLists: HolderProvider<StructureProcessorList>) {
        c.register(
            DuskStructurePools.DESERT_RUINS_OBELISK,
            StructurePool(
                empty,
                listOf(
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruins/obelisk",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 24
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruins/black_obelisk",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    )
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.DESERT_RUINS_OBELISK_TOP,
            StructurePool(
                empty,
                listOf(
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruins/obelisk_top",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruins/black_obelisk_top",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    )
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.DESERT_RUINS_ROADS,
            StructurePool(
                empty,
                listOf(
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/long_road_end",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_end_1",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_section_1",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_section_2",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_section_3",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_section_4",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_spacer_1",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    )
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.DESERT_RUINS_LARGE_RUINS,
            StructurePool(
                empty,
                listOf(
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruin_1",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruin_2",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    )
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.DESERT_RUINS_RUINS,
            StructurePool(
                empty,
                listOf(
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_1",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_2",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_3",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_4",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_5",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_6",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_7",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_8",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    )
                ),
                StructurePool.Projection.RIGID
            )
        )
    }

    fun generateRedDesertRuin(c: BootstrapContext<StructurePool>, empty: Holder<StructurePool>, procLists: HolderProvider<StructureProcessorList>){
        c.register(
            DuskStructurePools.RED_DESERT_RUINS_OBELISK,
            StructurePool(
                empty,
                listOf(
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruins/obelisk",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 20
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruins/black_obelisk",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    )
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.RED_DESERT_RUINS_OBELISK_TOP,
            StructurePool(
                empty,
                listOf(
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruins/obelisk_top",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruins/black_obelisk_top",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    )
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.RED_DESERT_RUINS_ROADS,
            StructurePool(
                empty,
                listOf(
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/long_road_end",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_end_1",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_section_1",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_section_2",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_section_3",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_section_4",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_spacer_1",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    )
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.RED_DESERT_RUINS_LARGE_RUINS,
            StructurePool(
                empty,
                listOf(
                    Pair.of(
                        StructurePoolElement.ofEmpty(), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruin_1",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 2
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruin_2",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 2
                    )
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.RED_DESERT_RUINS_RUINS,
            StructurePool(
                empty,
                listOf(
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_1",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_2",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_3",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_4",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_5",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_6",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_7",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_8",
                            procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
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