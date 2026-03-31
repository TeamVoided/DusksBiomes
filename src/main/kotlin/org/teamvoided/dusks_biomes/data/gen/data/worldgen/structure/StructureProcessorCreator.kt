package org.teamvoided.dusks_biomes.data.gen.data.worldgen.structure

import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.LanternBlock
import net.minecraft.world.level.levelgen.structure.templatesystem.*
import org.teamvoided.dusks_biomes.data.gen.data.worldgen.structure.processor_list.OceanRuins.oceanRuinWarmRed
import org.teamvoided.dusks_biomes.data.structure.DuskStructureProcessorLists


object StructureProcessorCreator {


    // StructureProcessorLists
    fun bootstrap(c: BootstrapContext<StructureProcessorList>) {
        val blockTags = c.lookup(Registries.BLOCK)

        swampVillageProcessorLists(c)
        mangroveSwampVillageProcessorLists(c)
        c.oceanRuinWarmRed()
//        desertRuinsProcessorLists(c)
    }

    fun swampVillageProcessorLists(c: BootstrapContext<StructureProcessorList>) {
        c.register(
            DuskStructureProcessorLists.VILLAGE_SWAMP_FARM,
            RuleProcessor(
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.WHEAT, 0.3f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.CARROTS.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.WHEAT, 0.3f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.POTATOES.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.WHEAT, 0.1f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.BEETROOTS.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.WHEAT, 0.025f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.BLUE_ORCHID.defaultBlockState()
                )
            )
        )
        c.register(
            DuskStructureProcessorLists.VILLAGE_SWAMP_HOUSE,
            RuleProcessor(
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.MOSS_CARPET, 0.5f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.AIR.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.VINE, 0.5f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.AIR.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.DIRT_PATH, 0.2f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.GRASS_BLOCK.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.FLOWER_POT, 0.3f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.POTTED_BLUE_ORCHID.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.FLOWER_POT, 0.1f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.POTTED_RED_MUSHROOM.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.FLOWER_POT, 0.2f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.POTTED_BROWN_MUSHROOM.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.FLOWER_POT, 0.1f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.POTTED_OXEYE_DAISY.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.FLOWER_POT, 0.1f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.POTTED_DARK_OAK_SAPLING.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.FLOWER_POT, 0.2f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.POTTED_OAK_SAPLING.defaultBlockState()
                )
            )
        )
        c.register(
            DuskStructureProcessorLists.VILLAGE_SWAMP_STREET,
            RuleProcessor(
                ProcessorRule(
                    BlockMatchTest(Blocks.DIRT_PATH),
                    BlockMatchTest(Blocks.WATER),
                    Blocks.DARK_OAK_PLANKS.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.DIRT_PATH, 0.1f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.GRASS_BLOCK.defaultBlockState()
                ),
                ProcessorRule(
                    TagMatchTest(BlockTags.DIRT),
                    BlockMatchTest(Blocks.WATER),
                    Blocks.WATER.defaultBlockState()
                ),
                ProcessorRule(
                    BlockMatchTest(Blocks.DIRT_PATH),
                    BlockMatchTest(Blocks.MUD),
                    Blocks.PACKED_MUD.defaultBlockState()
                )
            )
        )
        c.register(
            DuskStructureProcessorLists.VILLAGE_SWAMP_ZOMBIE,
            RuleProcessor(
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.COBBLESTONE, 0.8f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.MOSSY_COBBLESTONE.defaultBlockState()
                ),
                ProcessorRule(
                    TagMatchTest(BlockTags.DOORS),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.AIR.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockStateMatchTest(
                        Blocks.LANTERN.defaultBlockState().setValue(LanternBlock.HANGING, true),
                        0.05f
                    ),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.IRON_CHAIN.defaultBlockState()
                ),
                ProcessorRule(
                    BlockMatchTest(Blocks.LANTERN),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.AIR.defaultBlockState()
                ),
                ProcessorRule(
                    BlockMatchTest(Blocks.TORCH),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.AIR.defaultBlockState()
                ),
                ProcessorRule(
                    BlockMatchTest(Blocks.WALL_TORCH),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.AIR.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.COBBLESTONE, 0.07f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.COBWEB.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.MOSSY_COBBLESTONE, 0.07f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.COBWEB.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.TERRACOTTA, 0.07f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.COBWEB.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.DARK_OAK_LOG, 0.05f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.COBWEB.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.DARK_OAK_PLANKS, 0.1f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.COBWEB.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.DARK_OAK_STAIRS, 0.1f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.COBWEB.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.STRIPPED_DARK_OAK_LOG, 0.02f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.COBWEB.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.GLASS_PANE, 0.5f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.COBWEB.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.MOSS_CARPET, 0.25f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.AIR.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.VINE, 0.25f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.AIR.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.DIRT_PATH, 0.2f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.GRASS_BLOCK.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.FLOWER_POT, 0.3f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.POTTED_BLUE_ORCHID.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.FLOWER_POT, 0.1f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.POTTED_RED_MUSHROOM.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.FLOWER_POT, 0.2f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.POTTED_BROWN_MUSHROOM.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.FLOWER_POT, 0.1f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.POTTED_OXEYE_DAISY.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.FLOWER_POT, 0.1f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.POTTED_DARK_OAK_SAPLING.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.FLOWER_POT, 0.2f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.POTTED_OAK_SAPLING.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.WHEAT, 0.3f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.CARROTS.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.WHEAT, 0.3f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.POTATOES.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.WHEAT, 0.1f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.BEETROOTS.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.WHEAT, 0.025f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.BLUE_ORCHID.defaultBlockState()
                )
            )
        )
    }

    fun mangroveSwampVillageProcessorLists(c: BootstrapContext<StructureProcessorList>) {
        c.register(
            DuskStructureProcessorLists.VILLAGE_MANGROVE_SWAMP_HOUSE,
            RuleProcessor(
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.MOSS_CARPET, 0.5f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.AIR.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.VINE, 0.5f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.AIR.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.PACKED_MUD, 0.2f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.MUD.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.FLOWER_POT, 0.3f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.POTTED_BLUE_ORCHID.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.FLOWER_POT, 0.1f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.POTTED_RED_MUSHROOM.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.FLOWER_POT, 0.2f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.POTTED_BROWN_MUSHROOM.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.FLOWER_POT, 0.1f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.POTTED_OXEYE_DAISY.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.FLOWER_POT, 0.1f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.POTTED_DARK_OAK_SAPLING.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.FLOWER_POT, 0.2f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.POTTED_MANGROVE_PROPAGULE.defaultBlockState()
                )
            )
        )
        c.register(
            DuskStructureProcessorLists.VILLAGE_MANGROVE_SWAMP_STREET,
            RuleProcessor(
                ProcessorRule(
                    BlockMatchTest(Blocks.PACKED_MUD),
                    BlockMatchTest(Blocks.WATER),
                    Blocks.MANGROVE_PLANKS.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.PACKED_MUD, 0.1f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.MUD.defaultBlockState()
                ),
                ProcessorRule(
                    TagMatchTest(BlockTags.DIRT),
                    BlockMatchTest(Blocks.WATER),
                    Blocks.WATER.defaultBlockState()
                ),
                ProcessorRule(
                    BlockMatchTest(Blocks.PACKED_MUD),
                    BlockMatchTest(Blocks.DIRT),
                    Blocks.DIRT_PATH.defaultBlockState()
                )
            )
        )
        c.register(
            DuskStructureProcessorLists.VILLAGE_MANGROVE_SWAMP_ZOMBIE,
            RuleProcessor(
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.COBBLESTONE, 0.8f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.MOSSY_COBBLESTONE.defaultBlockState()
                ),
                ProcessorRule(
                    TagMatchTest(BlockTags.DOORS),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.AIR.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockStateMatchTest(Blocks.LANTERN.defaultBlockState().setValue(LanternBlock.HANGING, true), 0.05f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.IRON_CHAIN.defaultBlockState()
                ),
                ProcessorRule(
                    BlockMatchTest(Blocks.LANTERN),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.AIR.defaultBlockState()
                ),
                ProcessorRule(
                    BlockMatchTest(Blocks.TORCH),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.AIR.defaultBlockState()
                ),
                ProcessorRule(
                    BlockMatchTest(Blocks.WALL_TORCH),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.AIR.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.COBBLESTONE, 0.07f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.COBWEB.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.MOSSY_COBBLESTONE, 0.07f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.COBWEB.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.TERRACOTTA, 0.07f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.COBWEB.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.MANGROVE_LOG, 0.05f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.COBWEB.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.MANGROVE_PLANKS, 0.1f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.COBWEB.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.MANGROVE_STAIRS, 0.1f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.COBWEB.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.STRIPPED_MANGROVE_LOG, 0.02f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.COBWEB.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.GLASS_PANE, 0.5f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.COBWEB.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.MOSS_CARPET, 0.25f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.AIR.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.VINE, 0.25f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.AIR.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.DIRT_PATH, 0.2f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.GRASS_BLOCK.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.FLOWER_POT, 0.3f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.POTTED_BLUE_ORCHID.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.FLOWER_POT, 0.1f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.POTTED_RED_MUSHROOM.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.FLOWER_POT, 0.2f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.POTTED_BROWN_MUSHROOM.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.FLOWER_POT, 0.1f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.POTTED_OXEYE_DAISY.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.FLOWER_POT, 0.1f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.POTTED_MANGROVE_PROPAGULE.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.FLOWER_POT, 0.2f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.POTTED_OAK_SAPLING.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.WHEAT, 0.3f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.CARROTS.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.WHEAT, 0.3f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.POTATOES.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.WHEAT, 0.1f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.BEETROOTS.defaultBlockState()
                ),
                ProcessorRule(
                    RandomBlockMatchTest(Blocks.WHEAT, 0.025f),
                    AlwaysTrueTest.INSTANCE,
                    Blocks.BLUE_ORCHID.defaultBlockState()
                )
            )
        )
    }

    fun desertRuinsProcessorLists(c: BootstrapContext<StructureProcessorList>) {
        val genericDesertRuinProcessors = RuleProcessor(
            ProcessorRule(
                RandomBlockMatchTest(Blocks.SMOOTH_SANDSTONE, 0.3f),
                AlwaysTrueTest.INSTANCE,
                Blocks.SAND.defaultBlockState()
            ),
            ProcessorRule(
                RandomBlockMatchTest(Blocks.CUT_SANDSTONE, 0.3f),
                AlwaysTrueTest.INSTANCE,
                Blocks.SANDSTONE.defaultBlockState()
            ),
            ProcessorRule(
                RandomBlockMatchTest(Blocks.POLISHED_GRANITE, 0.05f),
                AlwaysTrueTest.INSTANCE,
                Blocks.EXPOSED_COPPER.defaultBlockState()
            )
        )
//        c.register(
//            DuskStructureProcessorLists.DESERT_RUINS_OBELISK_TOP_ARCHAEOLOGY,
//            CappedStructureProcessor(
//                RuleProcessor(
//                    ProcessorRule(
//                        BlockMatchTest(Blocks.SAND),
//                        AlwaysTrueTest.INSTANCE,
//                        AlwaysTruePosRuleTest.INSTANCE,
//                        Blocks.SUSPICIOUS_SAND.defaultBlockState(),
//                        AppendLootRuleBlockEntityModifier(LootTables.TRAIL_RUINS_COMMON_ARCHAEOLOGY)
//                    )
//                ), ConstantIntProvider.create(2)
//            ), genericDesertRuinProcessors
//        )
//        c.register(
//            DuskStructureProcessorLists.DESERT_RUINS_ROADS_ARCHAEOLOGY,
//            CappedStructureProcessor(
//                RuleProcessor(
//                    ProcessorRule(
//                        BlockMatchTest(Blocks.SAND),
//                        AlwaysTrueTest.INSTANCE,
//                        AlwaysTruePosRuleTest.INSTANCE,
//                        Blocks.SUSPICIOUS_SAND.defaultBlockState(),
//                        AppendLootRuleBlockEntityModifier(LootTables.TRAIL_RUINS_COMMON_ARCHAEOLOGY)
//                    )
//                ), ConstantIntProvider.create(2)
//            ), genericDesertRuinProcessors
//        )
//        c.register(
//            DuskStructureProcessorLists.DESERT_RUINS_LARGE_RUINS_ARCHAEOLOGY,
//            CappedStructureProcessor(
//                RuleProcessor(
//                    ProcessorRule(
//                        BlockMatchTest(Blocks.SAND),
//                        AlwaysTrueTest.INSTANCE,
//                        AlwaysTruePosRuleTest.INSTANCE,
//                        Blocks.SUSPICIOUS_SAND.defaultBlockState(),
//                        AppendLootRuleBlockEntityModifier(LootTables.TRAIL_RUINS_COMMON_ARCHAEOLOGY)
//                    )
//                ), ConstantIntProvider.create(12)
//            ),
//            CappedStructureProcessor(
//                RuleProcessor(
//                    ProcessorRule(
//                        BlockMatchTest(Blocks.SAND),
//                        AlwaysTrueTest.INSTANCE,
//                        AlwaysTruePosRuleTest.INSTANCE,
//                        Blocks.SUSPICIOUS_SAND.defaultBlockState(),
//                        AppendLootRuleBlockEntityModifier(LootTables.TRAIL_RUINS_RARE_ARCHAEOLOGY)
//                    )
//                ), ConstantIntProvider.create(6)
//            ), genericDesertRuinProcessors
//        )
//        c.register(
//            DuskStructureProcessorLists.DESERT_RUINS_SMALL_RUINS_ARCHAEOLOGY,
//            CappedStructureProcessor(
//                RuleProcessor(
//                    ProcessorRule(
//                        BlockMatchTest(Blocks.SAND),
//                        AlwaysTrueTest.INSTANCE,
//                        AlwaysTruePosRuleTest.INSTANCE,
//                        Blocks.SUSPICIOUS_SAND.defaultBlockState(),
//                        AppendLootRuleBlockEntityModifier(LootTables.TRAIL_RUINS_COMMON_ARCHAEOLOGY)
//                    )
//                ), ConstantIntProvider.create(6)
//            ),
//            CappedStructureProcessor(
//                RuleProcessor(
//                    ProcessorRule(
//                        BlockMatchTest(Blocks.SAND),
//                        AlwaysTrueTest.INSTANCE,
//                        AlwaysTruePosRuleTest.INSTANCE,
//                        Blocks.SUSPICIOUS_SAND.defaultBlockState(),
//                        AppendLootRuleBlockEntityModifier(LootTables.TRAIL_RUINS_RARE_ARCHAEOLOGY)
//                    )
//                ), ConstantIntProvider.create(3)
//            ), genericDesertRuinProcessors
//        )
    }

    fun redDesertRuinsProcessorLists(c: BootstrapContext<StructureProcessorList>) {
        val genericRedDesertRuinProcessors = RuleProcessor(
            ProcessorRule(
                RandomBlockMatchTest(Blocks.SMOOTH_RED_SANDSTONE, 0.3f),
                AlwaysTrueTest.INSTANCE,
                Blocks.RED_SAND.defaultBlockState()
            ), ProcessorRule(
                RandomBlockMatchTest(Blocks.CUT_RED_SANDSTONE, 0.3f),
                AlwaysTrueTest.INSTANCE,
                Blocks.RED_SANDSTONE.defaultBlockState()
            ), ProcessorRule(
                RandomBlockMatchTest(Blocks.POLISHED_GRANITE, 0.05f),
                AlwaysTrueTest.INSTANCE,
                Blocks.EXPOSED_COPPER.defaultBlockState()
            )
        )
    }

     fun BootstrapContext<StructureProcessorList>.register(
        key: ResourceKey<StructureProcessorList>, vararg procList: StructureProcessor
    ) = this.register(key, StructureProcessorList(procList.toList()))

     fun RuleProcessor(vararg procRules: ProcessorRule): RuleProcessor =
        RuleProcessor(procRules.toList())

}
