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
import net.minecraft.world.gen.feature.PlacedFeature
import net.minecraft.world.gen.feature.VillagePlacedFeatures
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen
import org.teamvoided.dusk_autumns_worldgen.init.structure.DuskStructurePools
import org.teamvoided.dusk_autumns_worldgen.init.structure.DuskStructureProcessorLists
import org.teamvoided.dusk_autumns_worldgen.init.worldgen.DuskPlacedFeatures

object StructurePoolCreator {

    val zombieChance = (1 / 50) * 100
    fun bootstrap(c: BootstrapContext<StructurePool>) {
        val structurePools = c.lookup(RegistryKeys.STRUCTURE_POOL)
        val empty: Holder<StructurePool> = structurePools.getHolderOrThrow(StructurePools.EMPTY)
        val procLists = c.lookup(RegistryKeys.STRUCTURE_PROCESSOR_LIST)
        val procEmpty = procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)
        val placedFeatures = c.lookup(RegistryKeys.PLACED_FEATURE)

        generateSwampVillage(c, structurePools, empty, procLists, procEmpty, placedFeatures)
        generateMangroveSwampVillage(c, structurePools, empty, procLists, procEmpty, placedFeatures)
        generateDesertRuins(c, empty, procLists, procEmpty)
        generateRedDesertRuin(c, empty, procLists, procEmpty)
    }

    fun generateSwampVillage(
        c: BootstrapContext<StructurePool>,
        structurePools: HolderProvider<StructurePool>,
        empty: Holder<StructurePool>,
        procLists: HolderProvider<StructureProcessorList>,
        procEmpty: Holder.Reference<StructureProcessorList>,
        placedFeatures: HolderProvider<PlacedFeature>

    ) {
        val procHouse = procLists.getHolderOrThrow(DuskStructureProcessorLists.VILLAGE_SWAMP_HOUSE)
        val procStreet = procLists.getHolderOrThrow(DuskStructureProcessorLists.VILLAGE_SWAMP_STREET)
        c.register(
            DuskStructurePools.SWAMP_VILLAGE_VILLAGERS,
            StructurePool(
                empty,
                listOf(
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/villagers/baby",
                            procEmpty
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/villagers/nitwit",
                            procEmpty
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/villagers/unemployed",
                            procEmpty
                        ), 10
                    )
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.SWAMP_VILLAGE_CENTER,
            StructurePool(
                empty,
                listOf(
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/town_centers/swamp_meeting_point_1",
                            procHouse
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/town_centers/swamp_meeting_point_2",
                            procHouse
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/town_centers/swamp_meeting_point_3",
                            procHouse
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/town_centers/swamp_meeting_point_4",
                            procHouse
                        ), 1
                    )
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.SWAMP_VILLAGE_STREETS,
            StructurePool(
                structurePools.getHolderOrThrow(DuskStructurePools.SWAMP_VILLAGE_TERMINATORS),
                listOf(
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/streets/corner_01",
                            procStreet
                        ), 2
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/streets/corner_02",
                            procStreet
                        ), 2
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/streets/split_01",
                            procStreet
                        ), 3
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/streets/split_02",
                            procStreet
                        ), 2
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/streets/square_01",
                            procStreet
                        ), 3
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/streets/square_02",
                            procStreet
                        ), 2
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/streets/straight_01",
                            procStreet
                        ), 6
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/streets/straight_02",
                            procStreet
                        ), 5
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/streets/straight_03",
                            procStreet
                        ), 7
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/streets/straight_04",
                            procStreet
                        ), 6
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/streets/straight_05",
                            procStreet
                        ), 3
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/streets/straight_06",
                            procStreet
                        ), 4
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/streets/crossroads_01",
                            procStreet
                        ), 3
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/streets/crossroads_02",
                            procStreet
                        ), 3
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/streets/crossroads_03",
                            procStreet
                        ), 2
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/streets/turn_01",
                            procStreet
                        ), 2
                    )
                ),
                StructurePool.Projection.TERRAIN_MATCHING
            )
        )
        c.register(
            DuskStructurePools.SWAMP_VILLAGE_HOUSES,
            StructurePool(
                empty,
                listOf(
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/houses/swamp_small_house_1",
                            procHouse
                        ), 2
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/houses/swamp_small_house_2",
                            procHouse
                        ), 2
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/houses/swamp_small_house_3",
                            procHouse
                        ), 2
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/houses/swamp_small_house_4",
                            procHouse
                        ), 2
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/houses/swamp_small_house_5",
                            procHouse
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/houses/swamp_medium_house_1",
                            procHouse
                        ), 2
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/houses/swamp_medium_house_2",
                            procHouse
                        ), 2
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/houses/swamp_butcher_shop_1",
                            procHouse
                        ), 2
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/houses/swamp_tool_smith_1",
                            procHouse
                        ), 2
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/houses/swamp_fletcher_house_1",
                            procHouse
                        ), 2
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/houses/swamp_shepherds_house_1",
                            procHouse
                        ), 2
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/houses/swamp_armorer_1",
                            procHouse
                        ), 2
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/houses/swamp_fisher_cottage_1",
                            procHouse
                        ), 2
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/houses/swamp_tannery_1",
                            procHouse
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/houses/swamp_tannery_2",
                            procHouse
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/houses/swamp_cartographer_house_1",
                            procHouse
                        ), 2
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/houses/swamp_cartographer_house_2",
                            procHouse
                        ), 2
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/houses/swamp_library_1",
                            procHouse
                        ), 2
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/houses/swamp_masons_house_1",
                            procHouse
                        ), 2
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/houses/swamp_masons_house_2",
                            procHouse
                        ), 2
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/houses/swamp_weaponsmith_1",
                            procHouse
                        ), 2
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/houses/swamp_temple_1",
                            procHouse
                        ), 2
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/houses/swamp_temple_2",
                            procHouse
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/houses/swamp_farm_1",
                            procHouse
                        ), 9
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/houses/swamp_farm_2",
                            procHouse
                        ), 7
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/houses/swamp_animal_pen_1",
                            procHouse
                        ), 2
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/houses/swamp_animal_pen_2",
                            procHouse
                        ), 2
                    ),
                    Pair.of(StructurePoolElement.ofEmpty(), 8)
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.SWAMP_VILLAGE_DECOR,
            StructurePool(
                empty,
                listOf(
                    Pair.of(
                        StructurePoolElement.ofFeature(placedFeatures.getHolderOrThrow(DuskPlacedFeatures.SWAMP_VILLAGE_ROCK)),
                        8
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/swamp_decoration_5",
                            procHouse
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/swamp_decoration_6",
                            procHouse
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/swamp_decoration_7",
                            procHouse
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofFeature(placedFeatures.getHolderOrThrow(DuskPlacedFeatures.SWAMP_VILLAGE_FLOWERS)),
                        8
                    ),
                    Pair.of(
                        StructurePoolElement.ofFeature(placedFeatures.getHolderOrThrow(DuskPlacedFeatures.SWAMP_VILLAGE_OAK)),
                        5
                    ),
                    Pair.of(
                        StructurePoolElement.ofFeature(placedFeatures.getHolderOrThrow(VillagePlacedFeatures.PILE_HAY)),
                        7
                    ),
                    Pair.of(StructurePoolElement.ofEmpty(), 8)
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.SWAMP_VILLAGE_TERMINATORS,
            StructurePool(
                empty,
                listOf(
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/terminators/terminator_01",
                            procStreet
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/terminators/terminator_02",
                            procStreet
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/terminators/terminator_03",
                            procStreet
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/terminators/terminator_04",
                            procStreet
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:village/swamp/streets/turn_01",
                            procStreet
                        ), 2
                    )
                ),
                StructurePool.Projection.TERRAIN_MATCHING
            )
        )
    }

    fun generateMangroveSwampVillage(
        c: BootstrapContext<StructurePool>,
        structurePools: HolderProvider<StructurePool>,
        empty: Holder<StructurePool>,
        procLists: HolderProvider<StructureProcessorList>,
        procEmpty: Holder.Reference<StructureProcessorList>,
        placedFeatures: HolderProvider<PlacedFeature>
    ) {

    }

    fun generateDesertRuins(
        c: BootstrapContext<StructurePool>,
        empty: Holder<StructurePool>,
        procLists: HolderProvider<StructureProcessorList>,
        procEmpty: Holder.Reference<StructureProcessorList>
    ) {
        c.register(
            DuskStructurePools.DESERT_RUINS_OBELISK,
            StructurePool(
                empty,
                listOf(
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruins/obelisk",
                            procEmpty
                        ), 24
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruins/black_obelisk",
                            procEmpty
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
                            procEmpty
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruins/black_obelisk_top",
                            procEmpty
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
                            procEmpty
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_end_1",
                            procEmpty
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_section_1",
                            procEmpty
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_section_2",
                            procEmpty
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_section_3",
                            procEmpty
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_section_4",
                            procEmpty
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_spacer_1",
                            procEmpty
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
                            procEmpty
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruin_2",
                            procEmpty
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
                            procEmpty
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_2",
                            procEmpty
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_3",
                            procEmpty
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_4",
                            procEmpty
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_5",
                            procEmpty
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_6",
                            procEmpty
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_7",
                            procEmpty
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_8",
                            procEmpty
                        ), 1
                    )
                ),
                StructurePool.Projection.RIGID
            )
        )
    }

    fun generateRedDesertRuin(
        c: BootstrapContext<StructurePool>,
        empty: Holder<StructurePool>,
        procLists: HolderProvider<StructureProcessorList>,
        procEmpty: Holder.Reference<StructureProcessorList>
    ) {
        c.register(
            DuskStructurePools.RED_DESERT_RUINS_OBELISK,
            StructurePool(
                empty,
                listOf(
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruins/obelisk",
                            procEmpty
                        ), 20
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruins/black_obelisk",
                            procEmpty
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
                            procEmpty
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruins/black_obelisk_top",
                            procEmpty
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
                            procEmpty
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_end_1",
                            procEmpty
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_section_1",
                            procEmpty
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_section_2",
                            procEmpty
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_section_3",
                            procEmpty
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_section_4",
                            procEmpty
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/roads/road_spacer_1",
                            procEmpty
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
                            procEmpty
                        ), 2
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/desert_ruin_2",
                            procEmpty
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
                            procEmpty
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_2",
                            procEmpty
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_3",
                            procEmpty
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_4",
                            procEmpty
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_5",
                            procEmpty
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_6",
                            procEmpty
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_7",
                            procEmpty
                        ), 1
                    ),
                    Pair.of(
                        StructurePoolElement.ofProcessedSingle(
                            "dusk_autumns_worldgen:red_desert_ruins/ruins/ruin_8",
                            procEmpty
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