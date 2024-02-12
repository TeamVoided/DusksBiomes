package org.teamvoided.dusk_autumns_worldgen.datagen.structure

import com.google.common.collect.ImmutableList
import net.minecraft.block.Blocks
import net.minecraft.loot.LootTables
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.BlockTags
import net.minecraft.structure.processor.*
import net.minecraft.structure.rule.*
import net.minecraft.structure.rule.block.entity.AppendLootRuleBlockEntityModifier
import net.minecraft.util.Identifier
import net.minecraft.util.math.int_provider.ConstantIntProvider
import net.minecraft.world.gen.BootstrapContext
import org.teamvoided.dusk_autumns_worldgen.init.structure.DuskStructureProcessorLists

object StructureProcessorCreator {


    // StructureProcessorLists
    fun bootstrap(context: BootstrapContext<StructureProcessorList>) {
        val blockTags = context.lookup(RegistryKeys.BLOCK)
        val genericDesertRuinProcessors = RuleStructureProcessor(
            ImmutableList.of(
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
        )
        val genericRedDesertRuinProcessors = RuleStructureProcessor(
            ImmutableList.of(
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.SMOOTH_RED_SANDSTONE, 0.3f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.RED_SAND.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.CUT_RED_SANDSTONE, 0.3f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.RED_SANDSTONE.defaultState
                ),
                StructureProcessorRule(
                    RandomBlockMatchRuleTest(Blocks.POLISHED_GRANITE, 0.05f),
                    AlwaysTrueRuleTest.INSTANCE,
                    Blocks.EXPOSED_COPPER.defaultState
                )
            )
        )



        register(
            context, DuskStructureProcessorLists.DESERT_RUINS_OBELISK_TOP_ARCHAEOLOGY, listOf(
                CappedStructureProcessor(
                    RuleStructureProcessor(
                        java.util.List.of(
                            StructureProcessorRule(
                                BlockMatchRuleTest(
                                    Blocks.SAND
                                ),
                                AlwaysTrueRuleTest.INSTANCE,
                                AlwaysTruePosRuleTest.INSTANCE,
                                Blocks.SUSPICIOUS_SAND.defaultState,
                                AppendLootRuleBlockEntityModifier(LootTables.TRAIL_RUINS_COMMON_ARCHAEOLOGY)
                            )
                        )
                    ), ConstantIntProvider.create(2)
                ),
                genericDesertRuinProcessors
            )
        )
        register(
            context, DuskStructureProcessorLists.DESERT_RUINS_ROADS_ARCHAEOLOGY, listOf(
                CappedStructureProcessor(
                    RuleStructureProcessor(
                        java.util.List.of(
                            StructureProcessorRule(
                                BlockMatchRuleTest(
                                    Blocks.SAND
                                ),
                                AlwaysTrueRuleTest.INSTANCE,
                                AlwaysTruePosRuleTest.INSTANCE,
                                Blocks.SUSPICIOUS_SAND.defaultState,
                                AppendLootRuleBlockEntityModifier(LootTables.TRAIL_RUINS_COMMON_ARCHAEOLOGY)
                            )
                        )
                    ), ConstantIntProvider.create(2)
                ),
                genericDesertRuinProcessors
            )
        )
        register(
            context, DuskStructureProcessorLists.DESERT_RUINS_LARGE_RUINS_ARCHAEOLOGY, listOf(
                CappedStructureProcessor(
                    RuleStructureProcessor(
                        java.util.List.of(
                            StructureProcessorRule(
                                BlockMatchRuleTest(
                                    Blocks.SAND
                                ),
                                AlwaysTrueRuleTest.INSTANCE,
                                AlwaysTruePosRuleTest.INSTANCE,
                                Blocks.SUSPICIOUS_SAND.defaultState,
                                AppendLootRuleBlockEntityModifier(LootTables.TRAIL_RUINS_COMMON_ARCHAEOLOGY)
                            )
                        )
                    ), ConstantIntProvider.create(12)
                ),
                CappedStructureProcessor(
                    RuleStructureProcessor(
                        java.util.List.of(
                            StructureProcessorRule(
                                BlockMatchRuleTest(
                                    Blocks.SAND
                                ),
                                AlwaysTrueRuleTest.INSTANCE,
                                AlwaysTruePosRuleTest.INSTANCE,
                                Blocks.SUSPICIOUS_SAND.defaultState,
                                AppendLootRuleBlockEntityModifier(LootTables.TRAIL_RUINS_RARE_ARCHAEOLOGY)
                            )
                        )
                    ), ConstantIntProvider.create(6)
                ),
                genericDesertRuinProcessors
            )
        )
        register(
            context, DuskStructureProcessorLists.DESERT_RUINS_SMALL_RUINS_ARCHAEOLOGY, listOf(
                CappedStructureProcessor(
                    RuleStructureProcessor(
                        java.util.List.of(
                            StructureProcessorRule(
                                BlockMatchRuleTest(
                                    Blocks.SAND
                                ),
                                AlwaysTrueRuleTest.INSTANCE,
                                AlwaysTruePosRuleTest.INSTANCE,
                                Blocks.SUSPICIOUS_SAND.defaultState,
                                AppendLootRuleBlockEntityModifier(LootTables.TRAIL_RUINS_COMMON_ARCHAEOLOGY)
                            )
                        )
                    ), ConstantIntProvider.create(6)
                ),
                CappedStructureProcessor(
                    RuleStructureProcessor(
                        java.util.List.of(
                            StructureProcessorRule(
                                BlockMatchRuleTest(
                                    Blocks.SAND
                                ),
                                AlwaysTrueRuleTest.INSTANCE,
                                AlwaysTruePosRuleTest.INSTANCE,
                                Blocks.SUSPICIOUS_SAND.defaultState,
                                AppendLootRuleBlockEntityModifier(LootTables.TRAIL_RUINS_RARE_ARCHAEOLOGY)
                            )
                        )
                    ), ConstantIntProvider.create(3)
                ),
                genericDesertRuinProcessors
            )
        )
//        register(context, StructureProcessorLists.EMPTY, listOf())
//        register(
//            context, StructureProcessorLists.ANCIENT_CITY_START_DEGRADATION, listOf(
//                RuleStructureProcessor(
//                    ImmutableList.of(
//                        StructureProcessorRule(
//                            RandomBlockMatchRuleTest(Blocks.DEEPSLATE_BRICKS, 0.3f),
//                            AlwaysTrueRuleTest.INSTANCE,
//                            Blocks.CRACKED_DEEPSLATE_BRICKS.defaultState
//                        ),
//                        StructureProcessorRule(
//                            RandomBlockMatchRuleTest(Blocks.DEEPSLATE_TILES, 0.3f),
//                            AlwaysTrueRuleTest.INSTANCE,
//                            Blocks.CRACKED_DEEPSLATE_TILES.defaultState
//                        ),
//                        StructureProcessorRule(
//                            RandomBlockMatchRuleTest(Blocks.SOUL_LANTERN, 0.05f),
//                            AlwaysTrueRuleTest.INSTANCE,
//                            Blocks.AIR.defaultState
//                        )
//                    )
//                ), ProtectedBlocksStructureProcessor(BlockTags.FEATURES_CANNOT_REPLACE)
//            )
//        )
//        register(
//            context, StructureProcessorLists.ANCIENT_CITY_GENERIC_DEGRADATION, listOf(
//                BlockRotStructureProcessor(blockTags.getTagOrThrow(BlockTags.ANCIENT_CITY_REPLACEABLE), 0.95f),
//                RuleStructureProcessor(
//                    ImmutableList.of(
//                        StructureProcessorRule(
//                            RandomBlockMatchRuleTest(Blocks.DEEPSLATE_BRICKS, 0.3f),
//                            AlwaysTrueRuleTest.INSTANCE, Blocks.CRACKED_DEEPSLATE_BRICKS.defaultState
//                        ),
//                        StructureProcessorRule(
//                            RandomBlockMatchRuleTest(Blocks.DEEPSLATE_TILES, 0.3f),
//                            AlwaysTrueRuleTest.INSTANCE,
//                            Blocks.CRACKED_DEEPSLATE_TILES.defaultState
//                        ),
//                        StructureProcessorRule(
//                            RandomBlockMatchRuleTest(Blocks.SOUL_LANTERN, 0.05f),
//                            AlwaysTrueRuleTest.INSTANCE,
//                            Blocks.AIR.defaultState
//                        )
//                    )
//                ),
//                ProtectedBlocksStructureProcessor(BlockTags.FEATURES_CANNOT_REPLACE)
//            )
//        )
    }

    private fun register(
        context: BootstrapContext<StructureProcessorList>,
        key: RegistryKey<StructureProcessorList>, structureProcessorList: List<StructureProcessor>
    ) {
        context.register(key, StructureProcessorList(structureProcessorList))
    }
}
