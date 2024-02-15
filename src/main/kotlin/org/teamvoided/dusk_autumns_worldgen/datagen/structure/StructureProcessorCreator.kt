package org.teamvoided.dusk_autumns_worldgen.datagen.structure

import net.minecraft.block.Blocks
import net.minecraft.loot.LootTables
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.structure.processor.*
import net.minecraft.structure.rule.AlwaysTruePosRuleTest
import net.minecraft.structure.rule.AlwaysTrueRuleTest
import net.minecraft.structure.rule.BlockMatchRuleTest
import net.minecraft.structure.rule.RandomBlockMatchRuleTest
import net.minecraft.structure.rule.block.entity.AppendLootRuleBlockEntityModifier
import net.minecraft.util.math.int_provider.ConstantIntProvider
import net.minecraft.world.gen.BootstrapContext
import org.teamvoided.dusk_autumns_worldgen.init.structure.DuskStructureProcessorLists

object StructureProcessorCreator {


    // StructureProcessorLists
    fun bootstrap(c: BootstrapContext<StructureProcessorList>) {
        val blockTags = c.lookup(RegistryKeys.BLOCK)

        swampVillageProcessorLists(c)
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
        c.register(DuskStructureProcessorLists.VILLAGE_SWAMP_HOUSE)
        c.register(DuskStructureProcessorLists.VILLAGE_SWAMP_STREET)
    }

    fun mangroveSwampVillageProcessorLists(c: BootstrapContext<StructureProcessorList>) {
        c.register(DuskStructureProcessorLists.VILLAGE_MANGROVE_SWAMP_HOUSE)
        c.register(DuskStructureProcessorLists.VILLAGE_MANGROVE_SWAMP_STREET)
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
        c.register(
            DuskStructureProcessorLists.DESERT_RUINS_OBELISK_TOP_ARCHAEOLOGY,
            CappedStructureProcessor(
                RuleStructureProcessor(
                    StructureProcessorRule(
                        BlockMatchRuleTest(Blocks.SAND),
                        AlwaysTrueRuleTest.INSTANCE,
                        AlwaysTruePosRuleTest.INSTANCE,
                        Blocks.SUSPICIOUS_SAND.defaultState,
                        AppendLootRuleBlockEntityModifier(LootTables.TRAIL_RUINS_COMMON_ARCHAEOLOGY)
                    )
                ), ConstantIntProvider.create(2)
            ), genericDesertRuinProcessors
        )
        c.register(
            DuskStructureProcessorLists.DESERT_RUINS_ROADS_ARCHAEOLOGY,
            CappedStructureProcessor(
                RuleStructureProcessor(
                    StructureProcessorRule(
                        BlockMatchRuleTest(Blocks.SAND),
                        AlwaysTrueRuleTest.INSTANCE,
                        AlwaysTruePosRuleTest.INSTANCE,
                        Blocks.SUSPICIOUS_SAND.defaultState,
                        AppendLootRuleBlockEntityModifier(LootTables.TRAIL_RUINS_COMMON_ARCHAEOLOGY)
                    )
                ), ConstantIntProvider.create(2)
            ), genericDesertRuinProcessors
        )
        c.register(
            DuskStructureProcessorLists.DESERT_RUINS_LARGE_RUINS_ARCHAEOLOGY,
            CappedStructureProcessor(
                RuleStructureProcessor(
                    StructureProcessorRule(
                        BlockMatchRuleTest(Blocks.SAND),
                        AlwaysTrueRuleTest.INSTANCE,
                        AlwaysTruePosRuleTest.INSTANCE,
                        Blocks.SUSPICIOUS_SAND.defaultState,
                        AppendLootRuleBlockEntityModifier(LootTables.TRAIL_RUINS_COMMON_ARCHAEOLOGY)
                    )
                ), ConstantIntProvider.create(12)
            ),
            CappedStructureProcessor(
                RuleStructureProcessor(
                    StructureProcessorRule(
                        BlockMatchRuleTest(Blocks.SAND),
                        AlwaysTrueRuleTest.INSTANCE,
                        AlwaysTruePosRuleTest.INSTANCE,
                        Blocks.SUSPICIOUS_SAND.defaultState,
                        AppendLootRuleBlockEntityModifier(LootTables.TRAIL_RUINS_RARE_ARCHAEOLOGY)
                    )
                ), ConstantIntProvider.create(6)
            ), genericDesertRuinProcessors
        )
        c.register(
            DuskStructureProcessorLists.DESERT_RUINS_SMALL_RUINS_ARCHAEOLOGY,
            CappedStructureProcessor(
                RuleStructureProcessor(
                    StructureProcessorRule(
                        BlockMatchRuleTest(Blocks.SAND),
                        AlwaysTrueRuleTest.INSTANCE,
                        AlwaysTruePosRuleTest.INSTANCE,
                        Blocks.SUSPICIOUS_SAND.defaultState,
                        AppendLootRuleBlockEntityModifier(LootTables.TRAIL_RUINS_COMMON_ARCHAEOLOGY)
                    )
                ), ConstantIntProvider.create(6)
            ),
            CappedStructureProcessor(
                RuleStructureProcessor(
                    StructureProcessorRule(
                        BlockMatchRuleTest(Blocks.SAND),
                        AlwaysTrueRuleTest.INSTANCE,
                        AlwaysTruePosRuleTest.INSTANCE,
                        Blocks.SUSPICIOUS_SAND.defaultState,
                        AppendLootRuleBlockEntityModifier(LootTables.TRAIL_RUINS_RARE_ARCHAEOLOGY)
                    )
                ), ConstantIntProvider.create(3)
            ), genericDesertRuinProcessors
        )
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
