package org.teamvoided.dusk_autumns_worldgen.datagen.structure

import com.mojang.datafixers.util.Pair
import net.minecraft.registry.Holder
import net.minecraft.registry.HolderProvider
import net.minecraft.registry.RegistryKeys
import net.minecraft.structure.pool.StructurePool
import net.minecraft.structure.pool.StructurePoolElement
import net.minecraft.structure.pool.StructurePools
import net.minecraft.structure.processor.StructureProcessorList
import net.minecraft.structure.processor.StructureProcessorLists
import net.minecraft.world.gen.BootstrapContext
import net.minecraft.world.gen.feature.PlacedFeature
import net.minecraft.world.gen.feature.VillagePlacedFeatures
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.MODID
import org.teamvoided.dusk_autumns_worldgen.init.structure.DuskStructurePools
import org.teamvoided.dusk_autumns_worldgen.init.structure.DuskStructureProcessorLists
import org.teamvoided.dusk_autumns_worldgen.init.worldgen.DuskPlacedFeatures
import java.util.function.Function

@Suppress("MemberVisibilityCanBePrivate")
object StructurePoolCreator {

//    const val zombieChance = (1 / 50) * 100

    fun bootstrap(c: BootstrapContext<StructurePool>) {
        val structurePools = c.lookup(RegistryKeys.STRUCTURE_POOL)
        val placedFeatures = c.lookup(RegistryKeys.PLACED_FEATURE)
        val procLists = c.lookup(RegistryKeys.STRUCTURE_PROCESSOR_LIST)

        val poolEmpty = structurePools.getHolderOrThrow(StructurePools.EMPTY)
        val procEmpty = procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)

        generateSwampVillage(c, structurePools, poolEmpty, procLists, procEmpty, placedFeatures)
        generateMangroveSwampVillage(c, structurePools, poolEmpty, procLists, procEmpty, placedFeatures)
        generateDesertRuins(c, poolEmpty, procLists, procEmpty)
        generateRedDesertRuin(c, poolEmpty, procLists, procEmpty)
    }

    fun generateSwampVillage(
        c: BootstrapContext<StructurePool>,
        structurePools: HolderProvider<StructurePool>,
        poolEmpty: Holder<StructurePool>,
        procLists: HolderProvider<StructureProcessorList>,
        procEmpty: Holder.Reference<StructureProcessorList>,
        placedFeatures: HolderProvider<PlacedFeature>

    ) {
        val procHouse = procLists.getHolderOrThrow(DuskStructureProcessorLists.VILLAGE_SWAMP_HOUSE)
        val procStreet = procLists.getHolderOrThrow(DuskStructureProcessorLists.VILLAGE_SWAMP_STREET)
        c.register(
            DuskStructurePools.SWAMP_VILLAGE_VILLAGERS,
            StructurePool(
                poolEmpty,
                listOf(
                    pairedSingle("village/swamp/villagers/baby", procEmpty),
                    pairedSingle("village/swamp/villagers/nitwit", procEmpty),
                    pairedSingle("village/swamp/villagers/unemployed", procEmpty, 10)
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.SWAMP_VILLAGE_CENTER,
            StructurePool(
                poolEmpty,
                listOf(
                    pairedSingle("village/swamp/town_centers/swamp_meeting_point_1", procStreet),
                    pairedSingle("village/swamp/town_centers/swamp_meeting_point_2", procStreet),
                    pairedSingle("village/swamp/town_centers/swamp_meeting_point_3", procStreet),
                    pairedSingle("village/swamp/town_centers/swamp_meeting_point_4", procStreet)
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.SWAMP_VILLAGE_STREETS,
            StructurePool(
                structurePools.getHolderOrThrow(DuskStructurePools.SWAMP_VILLAGE_TERMINATORS),
                listOf(
                    pairedSingle("village/swamp/streets/corner_01", procStreet, 2),
                    pairedSingle("village/swamp/streets/corner_02", procStreet, 2),
                    pairedSingle("village/swamp/streets/split_01", procStreet, 3),
                    pairedSingle("village/swamp/streets/split_02", procStreet, 2),
                    pairedSingle("village/swamp/streets/square_01", procStreet, 3),
                    pairedSingle("village/swamp/streets/square_02", procStreet, 2),
                    pairedSingle("village/swamp/streets/straight_01", procStreet, 6),
                    pairedSingle("village/swamp/streets/straight_02", procStreet, 5),
                    pairedSingle("village/swamp/streets/straight_03", procStreet, 7),
                    pairedSingle("village/swamp/streets/straight_04", procStreet, 6),
                    pairedSingle("village/swamp/streets/straight_05", procStreet, 3),
                    pairedSingle("village/swamp/streets/straight_06", procStreet, 4),
                    pairedSingle("village/swamp/streets/crossroads_01", procStreet, 3),
                    pairedSingle("village/swamp/streets/crossroads_02", procStreet, 3),
                    pairedSingle("village/swamp/streets/crossroads_03", procStreet, 2),
                    pairedSingle("village/swamp/streets/turn_01", procStreet, 2),
                ),
                StructurePool.Projection.TERRAIN_MATCHING
            )
        )
        c.register(
            DuskStructurePools.SWAMP_VILLAGE_HOUSES,
            StructurePool(
                poolEmpty,
                listOf(
                    pairedSingle("village/swamp/houses/swamp_small_house_1", procHouse, 2),
                    pairedSingle("village/swamp/houses/swamp_small_house_2", procHouse, 2),
                    pairedSingle("village/swamp/houses/swamp_small_house_3", procHouse, 2),
                    pairedSingle("village/swamp/houses/swamp_small_house_4", procHouse, 2),
                    pairedSingle("village/swamp/houses/swamp_small_house_5", procHouse),
                    pairedSingle("village/swamp/houses/swamp_medium_house_1", procHouse, 2),
                    pairedSingle("village/swamp/houses/swamp_medium_house_2", procHouse, 2),
                    pairedSingle("village/swamp/houses/swamp_butcher_shop_1", procHouse, 2),
                    pairedSingle("village/swamp/houses/swamp_tool_smith_1", procHouse, 2),
                    pairedSingle("village/swamp/houses/swamp_fletcher_house_1", procHouse, 2),
                    pairedSingle("village/swamp/houses/swamp_shepherds_house_1", procHouse, 2),
                    pairedSingle("village/swamp/houses/swamp_armorer_1", procHouse, 2),
                    pairedSingle("village/swamp/houses/swamp_fisher_cottage_1", procHouse, 2),
                    pairedSingle("village/swamp/houses/swamp_tannery_1", procHouse),
                    pairedSingle("village/swamp/houses/swamp_tannery_2", procHouse),
                    pairedSingle("village/swamp/houses/swamp_cartographer_house_1", procHouse, 2),
                    pairedSingle("village/swamp/houses/swamp_cartographer_house_2", procHouse, 2),
                    pairedSingle("village/swamp/houses/swamp_library_1", procHouse, 2),
                    pairedSingle("village/swamp/houses/swamp_masons_house_1", procHouse, 2),
                    pairedSingle("village/swamp/houses/swamp_masons_house_2", procHouse, 2),
                    pairedSingle("village/swamp/houses/swamp_weaponsmith_1", procHouse, 2),
                    pairedSingle("village/swamp/houses/swamp_temple_1", procHouse, 2),
                    pairedSingle("village/swamp/houses/swamp_temple_2", procHouse),
                    pairedSingle(
                        "village/swamp/houses/swamp_farm_1",
                        procLists.getHolderOrThrow(DuskStructureProcessorLists.VILLAGE_SWAMP_FARM),
                        9
                    ),
                    pairedSingle(
                        "village/swamp/houses/swamp_farm_2",
                        procLists.getHolderOrThrow(DuskStructureProcessorLists.VILLAGE_SWAMP_FARM),
                        7
                    ),
                    pairedSingle("village/swamp/houses/swamp_animal_pen_1", procHouse, 2),
                    pairedSingle("village/swamp/houses/swamp_animal_pen_2", procHouse, 2),
                    Pair.of(StructurePoolElement.ofEmpty(), 8)
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.SWAMP_VILLAGE_DECOR,
            StructurePool(
                poolEmpty,
                listOf(
                    pairedSingle("village/swamp/swamp_decoration_5", procHouse),
                    pairedSingle("village/swamp/swamp_decoration_6", procHouse),
                    pairedSingle("village/swamp/swamp_decoration_7", procHouse),
                    Pair.of(
                        StructurePoolElement.ofFeature(placedFeatures.getHolderOrThrow(DuskPlacedFeatures.SWAMP_VILLAGE_ROCK)),
                        8
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
                poolEmpty,
                listOf(
                    pairedSingle("village/swamp/streets/terminator_01", procEmpty),
                    pairedSingle("village/swamp/streets/terminator_02", procEmpty),
                    pairedSingle("village/swamp/streets/terminator_03", procEmpty),
                    pairedSingle("village/swamp/streets/terminator_04", procEmpty),
                    pairedSingle("village/swamp/streets/turn_01", procEmpty, 2)
                ),
                StructurePool.Projection.TERRAIN_MATCHING
            )
        )
    }

    fun generateMangroveSwampVillage(
        c: BootstrapContext<StructurePool>,
        structurePools: HolderProvider<StructurePool>,
        poolEmpty: Holder<StructurePool>,
        procLists: HolderProvider<StructureProcessorList>,
        procEmpty: Holder.Reference<StructureProcessorList>,
        placedFeatures: HolderProvider<PlacedFeature>
    ) {

    }

    fun generateDesertRuins(
        c: BootstrapContext<StructurePool>,
        poolEmpty: Holder<StructurePool>,
        procLists: HolderProvider<StructureProcessorList>,
        procEmpty: Holder.Reference<StructureProcessorList>
    ) {
        c.register(
            DuskStructurePools.DESERT_RUINS_OBELISK,
            StructurePool(
                poolEmpty,
                listOf(
                    pairedSingle("desert_ruins/desert_ruins/obelisk", procEmpty, 20),
                    pairedSingle("desert_ruins/desert_ruins/black_obelisk", procEmpty)
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.DESERT_RUINS_OBELISK_TOP,
            StructurePool(
                poolEmpty,
                listOf(
                    pairedSingle("desert_ruins/desert_ruins/obelisk_top", procEmpty),
                    pairedSingle("desert_ruins/desert_ruins/black_obelisk_top", procEmpty)
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.DESERT_RUINS_ROADS,
            StructurePool(
                poolEmpty,
                listOf(
                    pairedSingle("desert_ruins/roads/long_road_end", procEmpty),
                    pairedSingle("desert_ruins/roads/road_end_1", procEmpty),
                    pairedSingle("desert_ruins/roads/road_section_1", procEmpty),
                    pairedSingle("desert_ruins/roads/road_section_2", procEmpty),
                    pairedSingle("desert_ruins/roads/road_section_3", procEmpty),
                    pairedSingle("desert_ruins/roads/road_section_4", procEmpty),
                    pairedSingle("desert_ruins/roads/road_spacer_1", procEmpty)
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.DESERT_RUINS_LARGE_RUINS,
            StructurePool(
                poolEmpty,
                listOf(
                    Pair.of(StructurePoolElement.ofEmpty(), 1),
                    pairedSingle("desert_ruins/desert_ruin_1", procEmpty, 2),
                    pairedSingle("desert_ruins/desert_ruin_2", procEmpty, 2)
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.DESERT_RUINS_RUINS,
            StructurePool(
                poolEmpty,
                listOf(
                    pairedSingle("desert_ruins/ruins/ruin_1", procEmpty),
                    pairedSingle("desert_ruins/ruins/ruin_2", procEmpty),
                    pairedSingle("desert_ruins/ruins/ruin_3", procEmpty),
                    pairedSingle("desert_ruins/ruins/ruin_4", procEmpty),
                    pairedSingle("desert_ruins/ruins/ruin_5", procEmpty),
                    pairedSingle("desert_ruins/ruins/ruin_6", procEmpty),
                    pairedSingle("desert_ruins/ruins/ruin_7", procEmpty),
                    pairedSingle("desert_ruins/ruins/ruin_8", procEmpty)
                ),
                StructurePool.Projection.RIGID
            )
        )
    }

    fun generateRedDesertRuin(
        c: BootstrapContext<StructurePool>,
        poolEmpty: Holder<StructurePool>,
        procLists: HolderProvider<StructureProcessorList>,
        procEmpty: Holder.Reference<StructureProcessorList>
    ) {
        c.register(
            DuskStructurePools.RED_DESERT_RUINS_OBELISK,
            StructurePool(
                poolEmpty,
                listOf(
                    pairedSingle("red_desert_ruins/desert_ruins/obelisk", procEmpty, 20),
                    pairedSingle("red_desert_ruins/desert_ruins/black_obelisk", procEmpty)
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.RED_DESERT_RUINS_OBELISK_TOP,
            StructurePool(
                poolEmpty,
                listOf(
                    pairedSingle("red_desert_ruins/desert_ruins/obelisk_top", procEmpty),
                    pairedSingle("red_desert_ruins/desert_ruins/black_obelisk_top", procEmpty)
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.RED_DESERT_RUINS_ROADS,
            StructurePool(
                poolEmpty,
                listOf(
                    pairedSingle("red_desert_ruins/roads/long_road_end", procEmpty),
                    pairedSingle("red_desert_ruins/roads/road_end_1", procEmpty),
                    pairedSingle("red_desert_ruins/roads/road_section_1", procEmpty),
                    pairedSingle("red_desert_ruins/roads/road_section_2", procEmpty),
                    pairedSingle("red_desert_ruins/roads/road_section_3", procEmpty),
                    pairedSingle("red_desert_ruins/roads/road_section_4", procEmpty),
                    pairedSingle("red_desert_ruins/roads/road_spacer_1", procEmpty)
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.RED_DESERT_RUINS_LARGE_RUINS,
            StructurePool(
                poolEmpty,
                listOf(
                    Pair.of(StructurePoolElement.ofEmpty(), 1),
                    pairedSingle("red_desert_ruins/desert_ruin_1", procEmpty, 2),
                    pairedSingle("red_desert_ruins/desert_ruin_2", procEmpty, 2)
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.RED_DESERT_RUINS_RUINS,
            StructurePool(
                poolEmpty,
                listOf(
                    pairedSingle("red_desert_ruins/ruins/ruin_1", procEmpty),
                    pairedSingle("red_desert_ruins/ruins/ruin_2", procEmpty),
                    pairedSingle("red_desert_ruins/ruins/ruin_3", procEmpty),
                    pairedSingle("red_desert_ruins/ruins/ruin_4", procEmpty),
                    pairedSingle("red_desert_ruins/ruins/ruin_5", procEmpty),
                    pairedSingle("red_desert_ruins/ruins/ruin_6", procEmpty),
                    pairedSingle("red_desert_ruins/ruins/ruin_7", procEmpty),
                    pairedSingle("red_desert_ruins/ruins/ruin_8", procEmpty)
                ),
                StructurePool.Projection.RIGID
            )
        )
    }


    fun id(str: String) = "$MODID:$str"

    fun pairedSingle(
        str: String, processors: Holder<StructureProcessorList>
    ): Pair<Function<StructurePool.Projection, out StructurePoolElement>, Int> =
        Pair(processedSingle(str, processors), 1)

    fun pairedSingle(
        str: String, processors: Holder<StructureProcessorList>, weight: Int
    ): Pair<Function<StructurePool.Projection, out StructurePoolElement>, Int> =
        Pair(processedSingle(str, processors), weight)

    fun processedSingle(
        str: String, processors: Holder<StructureProcessorList>
    ): Function<StructurePool.Projection, out StructurePoolElement> =
        StructurePoolElement.ofProcessedSingle(id(str), processors)
}