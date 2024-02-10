package org.teamvoided.dusk_autumns_worldgen.datagen.structure

import com.google.common.collect.ImmutableList
import net.minecraft.block.Blocks
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.BlockTags
import net.minecraft.structure.processor.*
import net.minecraft.structure.rule.AlwaysTrueRuleTest
import net.minecraft.structure.rule.RandomBlockMatchRuleTest
import net.minecraft.world.gen.BootstrapContext

object StructureProcessorCreator {


    // StructureProcessorLists
    fun bootstrap(context: BootstrapContext<StructureProcessorList>) {
        val blockTags = context.lookup(RegistryKeys.BLOCK)
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
        key: RegistryKey<StructureProcessorList>,
        structureProcessorList: List<StructureProcessor>
    ) {
        context.register(key, StructureProcessorList(structureProcessorList))
    }
}
