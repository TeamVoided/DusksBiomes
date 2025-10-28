package org.teamvoided.dusks_biomes.data.gen.structure.processor_list

import dev.worldgen.lithostitched.worldgen.processor.BlockSwapStructureProcessor
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.structure.templatesystem.*
import net.minecraft.world.level.levelgen.structure.templatesystem.rule.blockentity.AppendLoot
import net.minecraft.world.level.storage.loot.BuiltInLootTables
import org.teamvoided.dusks_biomes.data.gen.structure.StructureProcessorCreator.RuleProcessor
import org.teamvoided.dusks_biomes.data.gen.structure.StructureProcessorCreator.register
import org.teamvoided.dusks_biomes.data.structure.DuskStructureProcessorLists.OCEAN_RUIN_WARM_RED


object OceanRuins {
    fun BootstrapContext<StructureProcessorList>.oceanRuinWarmRed() {
        register(
            OCEAN_RUIN_WARM_RED,
            CappedProcessor(
                RuleProcessor(
                    ProcessorRule(
                        BlockMatchTest(Blocks.RED_SAND),
                        AlwaysTrueTest.INSTANCE,
                        PosAlwaysTrueTest.INSTANCE,
                        Blocks.SUSPICIOUS_SAND.defaultBlockState(),
                        AppendLoot(BuiltInLootTables.OCEAN_RUIN_WARM_ARCHAEOLOGY)
                    )
                ), ConstantInt.of(2)
            ),
            blockSwap(
                Blocks.SUSPICIOUS_SAND to Blocks.SUSPICIOUS_SAND,
                Blocks.SAND to Blocks.RED_SAND,
                Blocks.SANDSTONE to Blocks.RED_SANDSTONE,
                Blocks.CUT_SANDSTONE to Blocks.CUT_RED_SANDSTONE,
                Blocks.CHISELED_SANDSTONE to Blocks.CHISELED_RED_SANDSTONE,
                Blocks.SANDSTONE_STAIRS to Blocks.RED_SANDSTONE_STAIRS,
            ),
        )
    }

    fun blockSwap(vararg pair: Pair<Block, Block>): BlockSwapStructureProcessor =
        BlockSwapStructureProcessor(pair.associate { it.first.id() to it.second.id() })

    fun Block.id() = BuiltInRegistries.BLOCK.getKey(this)
}

