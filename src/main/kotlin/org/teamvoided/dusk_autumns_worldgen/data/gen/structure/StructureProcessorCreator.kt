package org.teamvoided.dusk_autumns_worldgen.data.gen.structure

import net.minecraft.block.Blocks
import net.minecraft.block.LanternBlock
import net.minecraft.loot.LootTables
import net.minecraft.registry.BootstrapContext
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.BlockTags
import net.minecraft.structure.processor.*
import net.minecraft.structure.rule.*
import net.minecraft.structure.rule.block.entity.AppendLootRuleBlockEntityModifier
import net.minecraft.util.math.int_provider.ConstantIntProvider
import org.teamvoided.dusk_autumns_worldgen.data.structure.DuskStructureProcessorLists

object StructureProcessorCreator {


    // StructureProcessorLists
    fun bootstrap(c: BootstrapContext<StructureProcessorList>) {
        val blockTags = c.getRegistryLookup(RegistryKeys.BLOCK)

        swampVillageProcessorLists(c)
        mangroveSwampVillageProcessorLists(c)
        desertRuinsProcessorLists(c)
    }

    fun swampVillageProcessorLists(c: BootstrapContext<StructureProcessorList>) {
        c.register(
            DuskStructureProcessorLists.VILLAGE_SWAMP_FARM,
            RuleStructureProcessor(
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.WHEAT, 0.3f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.CARROTS.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.WHEAT, 0.3f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.POTATOES.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.WHEAT, 0.1f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.BEETROOTS.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.WHEAT, 0.025f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.BLUE_ORCHID.defaultState
                )
            )
        )
        c.register(
            DuskStructureProcessorLists.VILLAGE_SWAMP_HOUSE,
            RuleStructureProcessor(
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.MOSS_CARPET, 0.5f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.AIR.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.VINE, 0.5f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.AIR.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.DIRT_PATH, 0.2f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.GRASS_BLOCK.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.FLOWER_POT, 0.3f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.POTTED_BLUE_ORCHID.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.FLOWER_POT, 0.1f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.POTTED_RED_MUSHROOM.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.FLOWER_POT, 0.2f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.POTTED_BROWN_MUSHROOM.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.FLOWER_POT, 0.1f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.POTTED_OXEYE_DAISY.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.FLOWER_POT, 0.1f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.POTTED_DARK_OAK_SAPLING.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.FLOWER_POT, 0.2f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.POTTED_OAK_SAPLING.defaultState
                )
            )
        )
        c.register(
            DuskStructureProcessorLists.VILLAGE_SWAMP_STREET,
            RuleStructureProcessor(
                StructureProcessorRule(
                    BlockMatchRuleTest(Blocks.DIRT_PATH),
                    BlockMatchRuleTest(Blocks.WATER),
                    Blocks.DARK_OAK_PLANKS.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.DIRT_PATH, 0.1f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.GRASS_BLOCK.defaultState
                ),
                StructureProcessorRule(
                    TagMatchRuleTest(BlockTags.DIRT),
                    BlockMatchRuleTest(Blocks.WATER),
                    Blocks.WATER.defaultState
                ),
                StructureProcessorRule(
                    BlockMatchRuleTest(Blocks.DIRT_PATH),
                    BlockMatchRuleTest(Blocks.MUD),
                    Blocks.PACKED_MUD.defaultState
                )
            )
        )
        c.register(
            DuskStructureProcessorLists.VILLAGE_SWAMP_ZOMBIE,
            RuleStructureProcessor(
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.8f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.MOSSY_COBBLESTONE.defaultState
                ),
                StructureProcessorRule(
                    TagMatchRuleTest(BlockTags.DOORS),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.AIR.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockStateMatchRuleTest(Blocks.LANTERN.defaultState.with(LanternBlock.HANGING, true), 0.05f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.CHAIN.defaultState
                ),
                StructureProcessorRule(
                    BlockMatchRuleTest(Blocks.LANTERN),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.AIR.defaultState
                ),
                StructureProcessorRule(
                    BlockMatchRuleTest(Blocks.TORCH),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.AIR.defaultState
                ),
                StructureProcessorRule(
                    BlockMatchRuleTest(Blocks.WALL_TORCH),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.AIR.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.07f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.COBWEB.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.MOSSY_COBBLESTONE, 0.07f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.COBWEB.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.TERRACOTTA, 0.07f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.COBWEB.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.DARK_OAK_LOG, 0.05f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.COBWEB.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.DARK_OAK_PLANKS, 0.1f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.COBWEB.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.DARK_OAK_STAIRS, 0.1f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.COBWEB.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.STRIPPED_DARK_OAK_LOG, 0.02f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.COBWEB.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.GLASS_PANE, 0.5f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.COBWEB.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.MOSS_CARPET, 0.25f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.AIR.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.VINE, 0.25f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.AIR.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.DIRT_PATH, 0.2f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.GRASS_BLOCK.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.FLOWER_POT, 0.3f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.POTTED_BLUE_ORCHID.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.FLOWER_POT, 0.1f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.POTTED_RED_MUSHROOM.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.FLOWER_POT, 0.2f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.POTTED_BROWN_MUSHROOM.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.FLOWER_POT, 0.1f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.POTTED_OXEYE_DAISY.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.FLOWER_POT, 0.1f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.POTTED_DARK_OAK_SAPLING.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.FLOWER_POT, 0.2f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.POTTED_OAK_SAPLING.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.WHEAT, 0.3f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.CARROTS.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.WHEAT, 0.3f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.POTATOES.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.WHEAT, 0.1f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.BEETROOTS.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.WHEAT, 0.025f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.BLUE_ORCHID.defaultState
                )
            )
        )
    }

    fun mangroveSwampVillageProcessorLists(c: BootstrapContext<StructureProcessorList>) {
        c.register(
            DuskStructureProcessorLists.VILLAGE_MANGROVE_SWAMP_HOUSE,
            RuleStructureProcessor(
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.MOSS_CARPET, 0.5f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.AIR.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.VINE, 0.5f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.AIR.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.PACKED_MUD, 0.2f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.MUD.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.FLOWER_POT, 0.3f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.POTTED_BLUE_ORCHID.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.FLOWER_POT, 0.1f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.POTTED_RED_MUSHROOM.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.FLOWER_POT, 0.2f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.POTTED_BROWN_MUSHROOM.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.FLOWER_POT, 0.1f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.POTTED_OXEYE_DAISY.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.FLOWER_POT, 0.1f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.POTTED_DARK_OAK_SAPLING.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.FLOWER_POT, 0.2f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.POTTED_MANGROVE_PROPAGULE.defaultState
                )
            )
        )
        c.register(
            DuskStructureProcessorLists.VILLAGE_MANGROVE_SWAMP_STREET,
            RuleStructureProcessor(
                StructureProcessorRule(
                    BlockMatchRuleTest(Blocks.PACKED_MUD),
                    BlockMatchRuleTest(Blocks.WATER),
                    Blocks.MANGROVE_PLANKS.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.PACKED_MUD, 0.1f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.MUD.defaultState
                ),
                StructureProcessorRule(
                    TagMatchRuleTest(BlockTags.DIRT),
                    BlockMatchRuleTest(Blocks.WATER),
                    Blocks.WATER.defaultState
                ),
                StructureProcessorRule(
                    BlockMatchRuleTest(Blocks.PACKED_MUD),
                    BlockMatchRuleTest(Blocks.DIRT),
                    Blocks.DIRT_PATH.defaultState
                )
            )
        )
        c.register(
            DuskStructureProcessorLists.VILLAGE_MANGROVE_SWAMP_ZOMBIE,
            RuleStructureProcessor(
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.8f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.MOSSY_COBBLESTONE.defaultState
                ),
                StructureProcessorRule(
                    TagMatchRuleTest(BlockTags.DOORS),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.AIR.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockStateMatchRuleTest(Blocks.LANTERN.defaultState.with(LanternBlock.HANGING, true), 0.05f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.CHAIN.defaultState
                ),
                StructureProcessorRule(
                    BlockMatchRuleTest(Blocks.LANTERN),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.AIR.defaultState
                ),
                StructureProcessorRule(
                    BlockMatchRuleTest(Blocks.TORCH),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.AIR.defaultState
                ),
                StructureProcessorRule(
                    BlockMatchRuleTest(Blocks.WALL_TORCH),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.AIR.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.COBBLESTONE, 0.07f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.COBWEB.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.MOSSY_COBBLESTONE, 0.07f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.COBWEB.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.TERRACOTTA, 0.07f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.COBWEB.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.MANGROVE_LOG, 0.05f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.COBWEB.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.MANGROVE_PLANKS, 0.1f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.COBWEB.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.MANGROVE_STAIRS, 0.1f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.COBWEB.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.STRIPPED_MANGROVE_LOG, 0.02f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.COBWEB.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.GLASS_PANE, 0.5f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.COBWEB.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.MOSS_CARPET, 0.25f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.AIR.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.VINE, 0.25f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.AIR.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.DIRT_PATH, 0.2f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.GRASS_BLOCK.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.FLOWER_POT, 0.3f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.POTTED_BLUE_ORCHID.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.FLOWER_POT, 0.1f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.POTTED_RED_MUSHROOM.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.FLOWER_POT, 0.2f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.POTTED_BROWN_MUSHROOM.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.FLOWER_POT, 0.1f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.POTTED_OXEYE_DAISY.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.FLOWER_POT, 0.1f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.POTTED_MANGROVE_PROPAGULE.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.FLOWER_POT, 0.2f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.POTTED_OAK_SAPLING.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.WHEAT, 0.3f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.CARROTS.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.WHEAT, 0.3f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.POTATOES.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.WHEAT, 0.1f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.BEETROOTS.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.WHEAT, 0.025f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.BLUE_ORCHID.defaultState
                )
            )
        )
    }

    fun desertRuinsProcessorLists(c: BootstrapContext<StructureProcessorList>) {
        val genericDesertRuinProcessors = RuleStructureProcessor(
            StructureProcessorRule(
                RandomBlockMatchRuleTest(Blocks.SMOOTH_SANDSTONE, 0.3f),
                AlwaysTrueRuleTest.INSTANCE,
                Blocks.SAND.defaultState
            ),
            StructureProcessorRule(
                RandomBlockMatchRuleTest(Blocks.CUT_SANDSTONE, 0.3f),
                AlwaysTrueRuleTest.INSTANCE,
                Blocks.SANDSTONE.defaultState
            ),
            StructureProcessorRule(
                RandomBlockMatchRuleTest(Blocks.POLISHED_GRANITE, 0.05f),
                AlwaysTrueRuleTest.INSTANCE,
                Blocks.EXPOSED_COPPER.defaultState
            )
        )
//        c.register(
//            DuskStructureProcessorLists.DESERT_RUINS_OBELISK_TOP_ARCHAEOLOGY,
//            CappedStructureProcessor(
//                RuleStructureProcessor(
//                    StructureProcessorRule(
//                        BlockMatchRuleTest(Blocks.SAND),
//                        AlwaysTrueRuleTest.INSTANCE,
//                        AlwaysTruePosRuleTest.INSTANCE,
//                        Blocks.SUSPICIOUS_SAND.defaultState,
//                        AppendLootRuleBlockEntityModifier(LootTables.TRAIL_RUINS_COMMON_ARCHAEOLOGY)
//                    )
//                ), ConstantIntProvider.create(2)
//            ), genericDesertRuinProcessors
//        )
//        c.register(
//            DuskStructureProcessorLists.DESERT_RUINS_ROADS_ARCHAEOLOGY,
//            CappedStructureProcessor(
//                RuleStructureProcessor(
//                    StructureProcessorRule(
//                        BlockMatchRuleTest(Blocks.SAND),
//                        AlwaysTrueRuleTest.INSTANCE,
//                        AlwaysTruePosRuleTest.INSTANCE,
//                        Blocks.SUSPICIOUS_SAND.defaultState,
//                        AppendLootRuleBlockEntityModifier(LootTables.TRAIL_RUINS_COMMON_ARCHAEOLOGY)
//                    )
//                ), ConstantIntProvider.create(2)
//            ), genericDesertRuinProcessors
//        )
//        c.register(
//            DuskStructureProcessorLists.DESERT_RUINS_LARGE_RUINS_ARCHAEOLOGY,
//            CappedStructureProcessor(
//                RuleStructureProcessor(
//                    StructureProcessorRule(
//                        BlockMatchRuleTest(Blocks.SAND),
//                        AlwaysTrueRuleTest.INSTANCE,
//                        AlwaysTruePosRuleTest.INSTANCE,
//                        Blocks.SUSPICIOUS_SAND.defaultState,
//                        AppendLootRuleBlockEntityModifier(LootTables.TRAIL_RUINS_COMMON_ARCHAEOLOGY)
//                    )
//                ), ConstantIntProvider.create(12)
//            ),
//            CappedStructureProcessor(
//                RuleStructureProcessor(
//                    StructureProcessorRule(
//                        BlockMatchRuleTest(Blocks.SAND),
//                        AlwaysTrueRuleTest.INSTANCE,
//                        AlwaysTruePosRuleTest.INSTANCE,
//                        Blocks.SUSPICIOUS_SAND.defaultState,
//                        AppendLootRuleBlockEntityModifier(LootTables.TRAIL_RUINS_RARE_ARCHAEOLOGY)
//                    )
//                ), ConstantIntProvider.create(6)
//            ), genericDesertRuinProcessors
//        )
//        c.register(
//            DuskStructureProcessorLists.DESERT_RUINS_SMALL_RUINS_ARCHAEOLOGY,
//            CappedStructureProcessor(
//                RuleStructureProcessor(
//                    StructureProcessorRule(
//                        BlockMatchRuleTest(Blocks.SAND),
//                        AlwaysTrueRuleTest.INSTANCE,
//                        AlwaysTruePosRuleTest.INSTANCE,
//                        Blocks.SUSPICIOUS_SAND.defaultState,
//                        AppendLootRuleBlockEntityModifier(LootTables.TRAIL_RUINS_COMMON_ARCHAEOLOGY)
//                    )
//                ), ConstantIntProvider.create(6)
//            ),
//            CappedStructureProcessor(
//                RuleStructureProcessor(
//                    StructureProcessorRule(
//                        BlockMatchRuleTest(Blocks.SAND),
//                        AlwaysTrueRuleTest.INSTANCE,
//                        AlwaysTruePosRuleTest.INSTANCE,
//                        Blocks.SUSPICIOUS_SAND.defaultState,
//                        AppendLootRuleBlockEntityModifier(LootTables.TRAIL_RUINS_RARE_ARCHAEOLOGY)
//                    )
//                ), ConstantIntProvider.create(3)
//            ), genericDesertRuinProcessors
//        )
    }

    fun redDesertRuinsProcessorLists(c: BootstrapContext<StructureProcessorList>) {
        val genericRedDesertRuinProcessors = RuleStructureProcessor(
            StructureProcessorRule(
                RandomBlockMatchRuleTest(Blocks.SMOOTH_RED_SANDSTONE, 0.3f),
                AlwaysTrueRuleTest.INSTANCE,
                Blocks.RED_SAND.defaultState
            ), StructureProcessorRule(
                RandomBlockMatchRuleTest(Blocks.CUT_RED_SANDSTONE, 0.3f),
                AlwaysTrueRuleTest.INSTANCE,
                Blocks.RED_SANDSTONE.defaultState
            ), StructureProcessorRule(
                RandomBlockMatchRuleTest(Blocks.POLISHED_GRANITE, 0.05f),
                AlwaysTrueRuleTest.INSTANCE,
                Blocks.EXPOSED_COPPER.defaultState
            )
        )
    }

    private fun BootstrapContext<StructureProcessorList>.register(
        key: RegistryKey<StructureProcessorList>, vararg procList: StructureProcessor
    ) = this.register(key, StructureProcessorList(procList.toList()))

    private fun RuleStructureProcessor(vararg procRules: StructureProcessorRule): RuleStructureProcessor =
        RuleStructureProcessor(procRules.toList())

}
