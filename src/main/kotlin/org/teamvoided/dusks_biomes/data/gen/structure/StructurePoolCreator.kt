package org.teamvoided.dusks_biomes.data.gen.structure

import com.mojang.datafixers.util.Pair
import net.minecraft.registry.BootstrapContext
import net.minecraft.registry.Holder
import net.minecraft.registry.HolderProvider
import net.minecraft.registry.RegistryKeys
import net.minecraft.structure.pool.StructurePool
import net.minecraft.structure.pool.StructurePoolElement
import net.minecraft.structure.pool.StructurePools
import net.minecraft.structure.processor.StructureProcessorList
import net.minecraft.structure.processor.StructureProcessorLists
import net.minecraft.world.gen.feature.PlacedFeature
import net.minecraft.world.gen.feature.VillagePlacedFeatures
import org.teamvoided.dusks_biomes.DusksBiomesMod.MODID
import org.teamvoided.dusks_biomes.data.structure.DuskStructurePools
import org.teamvoided.dusks_biomes.data.structure.DuskStructureProcessorLists
import org.teamvoided.dusks_biomes.data.world.gen.DuskPlacedFeatures
import java.util.function.Function

@Suppress("MemberVisibilityCanBePrivate", "MagicNumber")
object StructurePoolCreator {

//    const val zombieChance = (1 / 50) * 100

    fun bootstrap(c: BootstrapContext<StructurePool>) {
        val structurePools = c.getRegistryLookup(RegistryKeys.STRUCTURE_POOL)
        val placedFeatures = c.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val procLists = c.getRegistryLookup(RegistryKeys.STRUCTURE_PROCESSOR_LIST)

        val poolEmpty = structurePools.getHolderOrThrow(StructurePools.EMPTY)
        val procEmpty = procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)

        generateSwampVillage(c, structurePools, poolEmpty, procLists, procEmpty, placedFeatures)
        generateMangroveSwampVillage(c, structurePools, poolEmpty, procLists, procEmpty, placedFeatures)
//        generateDesertRuins(c, poolEmpty, procLists, procEmpty)
//        generateRedDesertRuin(c, poolEmpty, procLists, procEmpty)
    }

    fun generateSwampVillage(
        c: BootstrapContext<StructurePool>,
        structurePools: HolderProvider<StructurePool>,
        poolEmpty: Holder<StructurePool>,
        procLists: HolderProvider<StructureProcessorList>,
        procEmpty: Holder.Reference<StructureProcessorList>,
        placedFeatures: HolderProvider<PlacedFeature>

    ) {
        var procHouse = procLists.getHolderOrThrow(DuskStructureProcessorLists.VILLAGE_SWAMP_HOUSE)
        val procStreet = procLists.getHolderOrThrow(DuskStructureProcessorLists.VILLAGE_SWAMP_STREET)
        c.register(
            DuskStructurePools.SWAMP_VILLAGE_VILLAGERS,
            StructurePool(
                poolEmpty,
                listOf(
                    pairedLegacySingle("village/swamp/villagers/baby", procEmpty),
                    pairedLegacySingle("village/swamp/villagers/nitwit", procEmpty),
                    pairedLegacySingle("village/swamp/villagers/unemployed", procEmpty, 10)
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.SWAMP_VILLAGE_CENTER,
            StructurePool(
                poolEmpty,
                listOf(
                    pairedLegacySingle("village/swamp/town_centers/swamp_meeting_point_1", procStreet),
                    pairedLegacySingle("village/swamp/town_centers/swamp_meeting_point_2", procStreet),
                    pairedLegacySingle("village/swamp/town_centers/swamp_meeting_point_3", procStreet),
                    pairedLegacySingle("village/swamp/town_centers/swamp_meeting_point_4", procStreet)
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.SWAMP_VILLAGE_CENTER_TREE,
            StructurePool(
                poolEmpty,
                listOf(pairedFeature(placedFeatures.getHolderOrThrow(DuskPlacedFeatures.SWAMP_VILLAGE_OAK))),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.SWAMP_VILLAGE_STREETS,
            StructurePool(
                structurePools.getHolderOrThrow(DuskStructurePools.SWAMP_VILLAGE_TERMINATORS),
                listOf(
                    pairedLegacySingle("village/swamp/streets/corner_01", procStreet, 2),
                    pairedLegacySingle("village/swamp/streets/corner_02", procStreet, 2),
                    pairedLegacySingle("village/swamp/streets/split_01", procStreet, 3),
                    pairedLegacySingle("village/swamp/streets/split_02", procStreet, 2),
                    pairedLegacySingle("village/swamp/streets/square_01", procStreet, 3),
                    pairedLegacySingle("village/swamp/streets/square_02", procStreet, 2),
                    pairedLegacySingle("village/swamp/streets/straight_01", procStreet, 6),
                    pairedLegacySingle("village/swamp/streets/straight_02", procStreet, 5),
                    pairedLegacySingle("village/swamp/streets/straight_03", procStreet, 7),
                    pairedLegacySingle("village/swamp/streets/straight_04", procStreet, 6),
                    pairedLegacySingle("village/swamp/streets/straight_05", procStreet, 3),
                    pairedLegacySingle("village/swamp/streets/straight_06", procStreet, 4),
                    pairedLegacySingle("village/swamp/streets/crossroads_01", procStreet, 3),
                    pairedLegacySingle("village/swamp/streets/crossroads_02", procStreet, 3),
                    pairedLegacySingle("village/swamp/streets/crossroads_03", procStreet, 2),
                    pairedLegacySingle("village/swamp/streets/turn_01", procStreet, 2),
                ),
                StructurePool.Projection.TERRAIN_MATCHING
            )
        )
        c.register(
            DuskStructurePools.SWAMP_VILLAGE_TERMINATORS,
            StructurePool(
                poolEmpty,
                listOf(
                    pairedLegacySingle("village/swamp/streets/terminator_01", procStreet),
                    pairedLegacySingle("village/swamp/streets/terminator_02", procStreet),
                    pairedLegacySingle("village/swamp/streets/terminator_03", procStreet),
                    pairedLegacySingle("village/swamp/streets/terminator_04", procStreet)
                ),
                StructurePool.Projection.TERRAIN_MATCHING
            )
        )
        c.register(
            DuskStructurePools.SWAMP_VILLAGE_HOUSES,
            StructurePool(
                poolEmpty,
                listOf(
                    pairedLegacySingle("village/swamp/houses/swamp_small_house_1", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_small_house_2", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_small_house_3", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_small_house_4", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_small_house_5", procHouse),
                    pairedLegacySingle("village/swamp/houses/swamp_medium_house_1", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_medium_house_2", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_butcher_shop_1", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_tool_smith_1", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_fletcher_house_1", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_shepherds_house_1", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_armorer_1", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_fisher_cottage_1", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_tannery_1", procHouse),
                    pairedLegacySingle("village/swamp/houses/swamp_tannery_2", procHouse),
                    pairedLegacySingle("village/swamp/houses/swamp_cartographer_house_1", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_cartographer_house_2", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_library_1", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_masons_house_1", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_masons_house_2", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_weaponsmith_1", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_temple_1", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_temple_2", procHouse),
                    pairedLegacySingle(
                        "village/swamp/houses/swamp_farm_1",
                        procLists.getHolderOrThrow(DuskStructureProcessorLists.VILLAGE_SWAMP_FARM),
                        9
                    ),
                    pairedLegacySingle(
                        "village/swamp/houses/swamp_farm_2",
                        procLists.getHolderOrThrow(DuskStructureProcessorLists.VILLAGE_SWAMP_FARM),
                        7
                    ),
                    pairedLegacySingle("village/swamp/houses/swamp_animal_pen_1", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_animal_pen_2", procHouse, 2),
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
                    pairedLegacySingle("village/swamp/swamp_decoration_5", procHouse),
                    pairedLegacySingle("village/swamp/swamp_decoration_6", procHouse, 6),
                    pairedLegacySingle("village/swamp/swamp_decoration_7", procHouse),
                    pairedFeature(placedFeatures.getHolderOrThrow(DuskPlacedFeatures.SWAMP_VILLAGE_ROCK), 8),
                    pairedFeature(placedFeatures.getHolderOrThrow(DuskPlacedFeatures.SWAMP_VILLAGE_FLOWERS), 8),
                    pairedFeature(placedFeatures.getHolderOrThrow(DuskPlacedFeatures.SWAMP_VILLAGE_OAK), 5),
                    pairedFeature(placedFeatures.getHolderOrThrow(VillagePlacedFeatures.PILE_HAY), 7),
                    Pair.of(StructurePoolElement.ofEmpty(), 8)
                ),
                StructurePool.Projection.RIGID
            )
        )
        procHouse = procLists.getHolderOrThrow(DuskStructureProcessorLists.VILLAGE_SWAMP_ZOMBIE)
        c.register(
            DuskStructurePools.SWAMP_ZOMBIE_VILLAGE_CENTER,
            StructurePool(
                poolEmpty,
                listOf(
                    pairedLegacySingle("village/swamp/town_centers/swamp_meeting_point_1", procStreet),
                    pairedLegacySingle("village/swamp/town_centers/swamp_meeting_point_2", procStreet),
                    pairedLegacySingle("village/swamp/town_centers/swamp_meeting_point_3", procStreet),
                    pairedLegacySingle("village/swamp/town_centers/swamp_meeting_point_4", procStreet)
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.SWAMP_ZOMBIE_VILLAGE_HOUSES,
            StructurePool(
                poolEmpty,
                listOf(
                    pairedLegacySingle("village/swamp/houses/swamp_small_house_1", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_small_house_2", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_small_house_3", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_small_house_4", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_small_house_5", procHouse),
                    pairedLegacySingle("village/swamp/houses/swamp_medium_house_1", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_medium_house_2", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_butcher_shop_1", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_tool_smith_1", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_fletcher_house_1", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_shepherds_house_1", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_armorer_1", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_fisher_cottage_1", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_tannery_1", procHouse),
                    pairedLegacySingle("village/swamp/houses/swamp_tannery_2", procHouse),
                    pairedLegacySingle("village/swamp/houses/swamp_cartographer_house_1", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_cartographer_house_2", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_library_1", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_masons_house_1", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_masons_house_2", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_weaponsmith_1", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_temple_1", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_temple_2", procHouse),
                    pairedLegacySingle("village/swamp/houses/swamp_farm_1", procHouse, 9),
                    pairedLegacySingle("village/swamp/houses/swamp_farm_2", procHouse, 7),
                    pairedLegacySingle("village/swamp/houses/swamp_animal_pen_1", procHouse, 2),
                    pairedLegacySingle("village/swamp/houses/swamp_animal_pen_2", procHouse, 2),
                    Pair.of(StructurePoolElement.ofEmpty(), 8),
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.SWAMP_ZOMBIE_VILLAGE_DECOR,
            StructurePool(
                poolEmpty,
                listOf(
                    pairedLegacySingle("village/swamp/swamp_decoration_5", procHouse),
                    pairedLegacySingle("village/swamp/swamp_decoration_6", procHouse, 6),
                    pairedLegacySingle("village/swamp/swamp_decoration_7", procHouse),
                    pairedFeature(placedFeatures.getHolderOrThrow(DuskPlacedFeatures.SWAMP_VILLAGE_ROCK), 8),
                    pairedFeature(placedFeatures.getHolderOrThrow(DuskPlacedFeatures.SWAMP_VILLAGE_FLOWERS), 8),
                    pairedFeature(placedFeatures.getHolderOrThrow(DuskPlacedFeatures.SWAMP_VILLAGE_OAK), 5),
                    pairedFeature(placedFeatures.getHolderOrThrow(VillagePlacedFeatures.PILE_HAY), 7),
                    Pair.of(StructurePoolElement.ofEmpty(), 8)
                ),
                StructurePool.Projection.RIGID
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
        var procHouse = procLists.getHolderOrThrow(DuskStructureProcessorLists.VILLAGE_MANGROVE_SWAMP_HOUSE)
        val procStreet = procLists.getHolderOrThrow(DuskStructureProcessorLists.VILLAGE_MANGROVE_SWAMP_STREET)
        c.register(
            DuskStructurePools.MANGROVE_SWAMP_VILLAGE_CENTER,
            StructurePool(
                poolEmpty,
                listOf(
                    pairedLegacySingle(
                        "village/mangrove_swamp/town_centers/mangrove_swamp_meeting_point_1",
                        procStreet
                    ),
                    pairedLegacySingle(
                        "village/mangrove_swamp/town_centers/mangrove_swamp_meeting_point_2",
                        procStreet
                    ),
                    pairedLegacySingle(
                        "village/mangrove_swamp/town_centers/mangrove_swamp_meeting_point_3",
                        procStreet
                    ),
                    pairedLegacySingle("village/mangrove_swamp/town_centers/mangrove_swamp_meeting_point_4", procStreet)
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.MANGROVE_SWAMP_VILLAGE_CENTER_TREE,
            StructurePool(
                poolEmpty,
                listOf(
                    pairedFeature(placedFeatures.getHolderOrThrow(DuskPlacedFeatures.SWAMP_VILLAGE_MANGROVE)),
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.MANGROVE_SWAMP_VILLAGE_STREETS,
            StructurePool(
                structurePools.getHolderOrThrow(DuskStructurePools.MANGROVE_SWAMP_VILLAGE_TERMINATORS),
                listOf(
                    pairedLegacySingle("village/mangrove_swamp/streets/corner_01", procStreet, 2),
                    pairedLegacySingle("village/mangrove_swamp/streets/corner_02", procStreet, 2),
                    pairedLegacySingle("village/mangrove_swamp/streets/split_01", procStreet, 3),
                    pairedLegacySingle("village/mangrove_swamp/streets/split_02", procStreet, 2),
                    pairedLegacySingle("village/mangrove_swamp/streets/square_01", procStreet, 3),
                    pairedLegacySingle("village/mangrove_swamp/streets/square_02", procStreet, 2),
                    pairedLegacySingle("village/mangrove_swamp/streets/straight_01", procStreet, 6),
                    pairedLegacySingle("village/mangrove_swamp/streets/straight_02", procStreet, 5),
                    pairedLegacySingle("village/mangrove_swamp/streets/straight_03", procStreet, 7),
                    pairedLegacySingle("village/mangrove_swamp/streets/straight_04", procStreet, 6),
                    pairedLegacySingle("village/mangrove_swamp/streets/straight_05", procStreet, 3),
                    pairedLegacySingle("village/mangrove_swamp/streets/straight_06", procStreet, 4),
                    pairedLegacySingle("village/mangrove_swamp/streets/crossroads_01", procStreet, 3),
                    pairedLegacySingle("village/mangrove_swamp/streets/crossroads_02", procStreet, 3),
                    pairedLegacySingle("village/mangrove_swamp/streets/crossroads_03", procStreet, 2),
                    pairedLegacySingle("village/mangrove_swamp/streets/turn_01", procStreet, 2),
                ),
                StructurePool.Projection.TERRAIN_MATCHING
            )
        )
        c.register(
            DuskStructurePools.MANGROVE_SWAMP_VILLAGE_TERMINATORS,
            StructurePool(
                poolEmpty,
                listOf(
                    pairedLegacySingle("village/mangrove_swamp/streets/terminator_01", procStreet),
                    pairedLegacySingle("village/mangrove_swamp/streets/terminator_02", procStreet),
                    pairedLegacySingle("village/mangrove_swamp/streets/terminator_03", procStreet),
                    pairedLegacySingle("village/mangrove_swamp/streets/terminator_04", procStreet)
                ),
                StructurePool.Projection.TERRAIN_MATCHING
            )
        )
        c.register(
            DuskStructurePools.MANGROVE_SWAMP_VILLAGE_HOUSES,
            StructurePool(
                poolEmpty,
                listOf(
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_small_house_1", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_small_house_2", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_small_house_3", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_small_house_4", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_small_house_5", procHouse),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_medium_house_1", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_medium_house_2", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_butcher_shop_1", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_tool_smith_1", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_fletcher_house_1", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_shepherds_house_1", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_armorer_1", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_fisher_cottage_1", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_tannery_1", procHouse),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_tannery_2", procHouse),
                    pairedLegacySingle(
                        "village/mangrove_swamp/houses/mangrove_swamp_cartographer_house_1",
                        procHouse,
                        2
                    ),
                    pairedLegacySingle(
                        "village/mangrove_swamp/houses/mangrove_swamp_cartographer_house_2",
                        procHouse,
                        2
                    ),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_library_1", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_masons_house_1", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_masons_house_2", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_weaponsmith_1", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_temple_1", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_temple_2", procHouse),
                    pairedLegacySingle(
                        "village/mangrove_swamp/houses/mangrove_swamp_farm_1",
                        procLists.getHolderOrThrow(DuskStructureProcessorLists.VILLAGE_SWAMP_FARM),
                        9
                    ),
                    pairedLegacySingle(
                        "village/mangrove_swamp/houses/mangrove_swamp_farm_2",
                        procLists.getHolderOrThrow(DuskStructureProcessorLists.VILLAGE_SWAMP_FARM),
                        7
                    ),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_animal_pen_1", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_animal_pen_2", procHouse, 2),
                    Pair.of(StructurePoolElement.ofEmpty(), 8)
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.MANGROVE_SWAMP_VILLAGE_DECOR,
            StructurePool(
                poolEmpty,
                listOf(
                    pairedLegacySingle("village/mangrove_swamp/mangrove_swamp_decoration_5", procHouse),
                    pairedLegacySingle("village/mangrove_swamp/mangrove_swamp_decoration_6", procHouse, 6),
                    pairedLegacySingle("village/mangrove_swamp/mangrove_swamp_decoration_7", procHouse),
                    pairedFeature(placedFeatures.getHolderOrThrow(DuskPlacedFeatures.SWAMP_VILLAGE_ROCK), 8),
                    pairedFeature(placedFeatures.getHolderOrThrow(DuskPlacedFeatures.SWAMP_VILLAGE_FLOWERS), 8),
                    pairedFeature(placedFeatures.getHolderOrThrow(DuskPlacedFeatures.SWAMP_VILLAGE_MANGROVE), 5),
                    pairedFeature(placedFeatures.getHolderOrThrow(VillagePlacedFeatures.PILE_HAY), 7),
                    Pair.of(StructurePoolElement.ofEmpty(), 8)
                ),
                StructurePool.Projection.RIGID
            )
        )
        procHouse = procLists.getHolderOrThrow(DuskStructureProcessorLists.VILLAGE_MANGROVE_SWAMP_ZOMBIE)
        c.register(
            DuskStructurePools.MANGROVE_SWAMP_ZOMBIE_VILLAGE_CENTER,
            StructurePool(
                poolEmpty,
                listOf(
                    pairedLegacySingle(
                        "village/mangrove_swamp/town_centers/mangrove_swamp_meeting_point_1",
                        procStreet
                    ),
                    pairedLegacySingle(
                        "village/mangrove_swamp/town_centers/mangrove_swamp_meeting_point_2",
                        procStreet
                    ),
                    pairedLegacySingle(
                        "village/mangrove_swamp/town_centers/mangrove_swamp_meeting_point_3",
                        procStreet
                    ),
                    pairedLegacySingle("village/mangrove_swamp/town_centers/mangrove_swamp_meeting_point_4", procStreet)
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.MANGROVE_SWAMP_ZOMBIE_VILLAGE_HOUSES,
            StructurePool(
                poolEmpty,
                listOf(
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_small_house_1", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_small_house_2", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_small_house_3", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_small_house_4", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_small_house_5", procHouse),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_medium_house_1", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_medium_house_2", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_butcher_shop_1", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_tool_smith_1", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_fletcher_house_1", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_shepherds_house_1", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_armorer_1", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_fisher_cottage_1", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_tannery_1", procHouse),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_tannery_2", procHouse),
                    pairedLegacySingle(
                        "village/mangrove_swamp/houses/mangrove_swamp_cartographer_house_1",
                        procHouse,
                        2
                    ),
                    pairedLegacySingle(
                        "village/mangrove_swamp/houses/mangrove_swamp_cartographer_house_2",
                        procHouse,
                        2
                    ),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_library_1", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_masons_house_1", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_masons_house_2", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_weaponsmith_1", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_temple_1", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_temple_2", procHouse),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_farm_1", procHouse, 9),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_farm_2", procHouse, 7),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_animal_pen_1", procHouse, 2),
                    pairedLegacySingle("village/mangrove_swamp/houses/mangrove_swamp_animal_pen_2", procHouse, 2),
                    Pair.of(StructurePoolElement.ofEmpty(), 8)
                ),
                StructurePool.Projection.RIGID
            )
        )
        c.register(
            DuskStructurePools.MANGROVE_SWAMP_ZOMBIE_VILLAGE_DECOR,
            StructurePool(
                poolEmpty,
                listOf(
                    pairedLegacySingle("village/mangrove_swamp/mangrove_swamp_decoration_5", procHouse),
                    pairedLegacySingle("village/mangrove_swamp/mangrove_swamp_decoration_6", procHouse, 6),
                    pairedLegacySingle("village/mangrove_swamp/mangrove_swamp_decoration_7", procHouse),
                    pairedFeature(placedFeatures.getHolderOrThrow(DuskPlacedFeatures.SWAMP_VILLAGE_ROCK), 8),
                    pairedFeature(placedFeatures.getHolderOrThrow(DuskPlacedFeatures.SWAMP_VILLAGE_FLOWERS), 8),
                    pairedFeature(placedFeatures.getHolderOrThrow(DuskPlacedFeatures.SWAMP_VILLAGE_MANGROVE), 5),
                    pairedFeature(placedFeatures.getHolderOrThrow(VillagePlacedFeatures.PILE_HAY), 7),
                    Pair.of(StructurePoolElement.ofEmpty(), 8)
                ),
                StructurePool.Projection.RIGID
            )
        )
    }

//    fun generateDesertRuins(
//        c: BootstrapContext<StructurePool>,
//        poolEmpty: Holder<StructurePool>,
//        procLists: HolderProvider<StructureProcessorList>,
//        procEmpty: Holder.Reference<StructureProcessorList>
//    ) {
//        c.register(
//            DuskStructurePools.DESERT_RUINS_OBELISK,
//            StructurePool(
//                poolEmpty,
//                listOf(
//                    pairedLegacySingle("desert_ruins/obelisk", procEmpty, 20),
//                    pairedLegacySingle("desert_ruins/black_obelisk", procEmpty)
//                ),
//                StructurePool.Projection.RIGID
//            )
//        )
//        c.register(
//            DuskStructurePools.DESERT_RUINS_OBELISK_TOP,
//            StructurePool(
//                poolEmpty,
//                listOf(
//                    pairedLegacySingle("desert_ruins/obelisk_top", procEmpty),
//                    pairedLegacySingle("desert_ruins/black_obelisk_top", procEmpty)
//                ),
//                StructurePool.Projection.RIGID
//            )
//        )
//        c.register(
//            DuskStructurePools.DESERT_RUINS_ROADS,
//            StructurePool(
//                poolEmpty,
//                listOf(
//                    pairedLegacySingle("desert_ruins/roads/long_road_end", procEmpty),
//                    pairedLegacySingle("desert_ruins/roads/road_end_1", procEmpty),
//                    pairedLegacySingle("desert_ruins/roads/road_section_1", procEmpty),
//                    pairedLegacySingle("desert_ruins/roads/road_section_2", procEmpty),
//                    pairedLegacySingle("desert_ruins/roads/road_section_3", procEmpty),
//                    pairedLegacySingle("desert_ruins/roads/road_section_4", procEmpty),
//                    pairedLegacySingle("desert_ruins/roads/road_spacer_1", procEmpty)
//                ),
//                StructurePool.Projection.RIGID
//            )
//        )
//        c.register(
//            DuskStructurePools.DESERT_RUINS_LARGE_RUINS,
//            StructurePool(
//                poolEmpty,
//                listOf(
//                    Pair.of(StructurePoolElement.ofEmpty(), 1),
//                    pairedLegacySingle("desert_ruins/desert_ruin_1", procEmpty, 2),
//                    pairedLegacySingle("desert_ruins/desert_ruin_2", procEmpty, 2)
//                ),
//                StructurePool.Projection.RIGID
//            )
//        )
//        c.register(
//            DuskStructurePools.DESERT_RUINS_RUINS,
//            StructurePool(
//                poolEmpty,
//                listOf(
//                    pairedLegacySingle("desert_ruins/ruins/ruin_1", procEmpty),
//                    pairedLegacySingle("desert_ruins/ruins/ruin_2", procEmpty),
//                    pairedLegacySingle("desert_ruins/ruins/ruin_3", procEmpty),
//                    pairedLegacySingle("desert_ruins/ruins/ruin_4", procEmpty),
//                    pairedLegacySingle("desert_ruins/ruins/ruin_5", procEmpty),
//                    pairedLegacySingle("desert_ruins/ruins/ruin_6", procEmpty),
//                    pairedLegacySingle("desert_ruins/ruins/ruin_7", procEmpty),
//                    pairedLegacySingle("desert_ruins/ruins/ruin_8", procEmpty)
//                ),
//                StructurePool.Projection.RIGID
//            )
//        )
//    }
//
//    fun generateRedDesertRuin(
//        c: BootstrapContext<StructurePool>,
//        poolEmpty: Holder<StructurePool>,
//        procLists: HolderProvider<StructureProcessorList>,
//        procEmpty: Holder.Reference<StructureProcessorList>
//    ) {
//        c.register(
//            DuskStructurePools.RED_DESERT_RUINS_OBELISK,
//            StructurePool(
//                poolEmpty,
//                listOf(
//                    pairedLegacySingle("red_desert_ruins/obelisk", procEmpty, 20),
//                    pairedLegacySingle("red_desert_ruins/black_obelisk", procEmpty)
//                ),
//                StructurePool.Projection.RIGID
//            )
//        )
//        c.register(
//            DuskStructurePools.RED_DESERT_RUINS_OBELISK_TOP,
//            StructurePool(
//                poolEmpty,
//                listOf(
//                    pairedLegacySingle("red_desert_ruins/obelisk_top", procEmpty),
//                    pairedLegacySingle("red_desert_ruins/black_obelisk_top", procEmpty)
//                ),
//                StructurePool.Projection.RIGID
//            )
//        )
//        c.register(
//            DuskStructurePools.RED_DESERT_RUINS_ROADS,
//            StructurePool(
//                poolEmpty,
//                listOf(
//                    pairedLegacySingle("red_desert_ruins/roads/long_road_end", procEmpty),
//                    pairedLegacySingle("red_desert_ruins/roads/road_end_1", procEmpty),
//                    pairedLegacySingle("red_desert_ruins/roads/road_section_1", procEmpty),
//                    pairedLegacySingle("red_desert_ruins/roads/road_section_2", procEmpty),
//                    pairedLegacySingle("red_desert_ruins/roads/road_section_3", procEmpty),
//                    pairedLegacySingle("red_desert_ruins/roads/road_section_4", procEmpty),
//                    pairedLegacySingle("red_desert_ruins/roads/road_spacer_1", procEmpty)
//                ),
//                StructurePool.Projection.RIGID
//            )
//        )
//        c.register(
//            DuskStructurePools.RED_DESERT_RUINS_LARGE_RUINS,
//            StructurePool(
//                poolEmpty,
//                listOf(
//                    Pair.of(StructurePoolElement.ofEmpty(), 1),
//                    pairedLegacySingle("red_desert_ruins/desert_ruin_1", procEmpty, 2),
//                    pairedLegacySingle("red_desert_ruins/desert_ruin_2", procEmpty, 2)
//                ),
//                StructurePool.Projection.RIGID
//            )
//        )
//        c.register(
//            DuskStructurePools.RED_DESERT_RUINS_RUINS,
//            StructurePool(
//                poolEmpty,
//                listOf(
//                    pairedLegacySingle("red_desert_ruins/ruins/ruin_1", procEmpty),
//                    pairedLegacySingle("red_desert_ruins/ruins/ruin_2", procEmpty),
//                    pairedLegacySingle("red_desert_ruins/ruins/ruin_3", procEmpty),
//                    pairedLegacySingle("red_desert_ruins/ruins/ruin_4", procEmpty),
//                    pairedLegacySingle("red_desert_ruins/ruins/ruin_5", procEmpty),
//                    pairedLegacySingle("red_desert_ruins/ruins/ruin_6", procEmpty),
//                    pairedLegacySingle("red_desert_ruins/ruins/ruin_7", procEmpty),
//                    pairedLegacySingle("red_desert_ruins/ruins/ruin_8", procEmpty)
//                ),
//                StructurePool.Projection.RIGID
//            )
//        )
//    }


    fun id(str: String) = "$MODID:$str"

    fun pairedSingle(
        str: String, processors: Holder<StructureProcessorList>, weight: Int = 1
    ): Pair<Function<StructurePool.Projection, out StructurePoolElement>, Int> =
        Pair(processedSingle(str, processors), weight)

    fun processedSingle(
        str: String, processors: Holder<StructureProcessorList>
    ): Function<StructurePool.Projection, out StructurePoolElement> =
        StructurePoolElement.ofProcessedSingle(id(str), processors)


    fun pairedLegacySingle(
        str: String, processors: Holder<StructureProcessorList>, weight: Int = 1
    ): Pair<Function<StructurePool.Projection, out StructurePoolElement>, Int> =
        Pair(processedLegacySingle(str, processors), weight)

    fun processedLegacySingle(
        str: String, processors: Holder<StructureProcessorList>
    ): Function<StructurePool.Projection, out StructurePoolElement> =
        StructurePoolElement.ofProcessedLegacySingle(id(str), processors)


    fun pairedFeature(
        placedFeatures: Holder<PlacedFeature>, weight: Int = 1
    ): Pair<Function<StructurePool.Projection, out StructurePoolElement>, Int> =
        Pair(processedFeature(placedFeatures), weight)

    fun processedFeature(holder: Holder<PlacedFeature>): Function<StructurePool.Projection, out StructurePoolElement> =
        StructurePoolElement.ofFeature(holder)

}
