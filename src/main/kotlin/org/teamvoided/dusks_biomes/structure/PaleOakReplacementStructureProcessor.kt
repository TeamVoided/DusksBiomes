package org.teamvoided.dusks_biomes.structure

import com.mojang.serialization.MapCodec
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.nbt.NbtCompound
import net.minecraft.structure.StructurePlacementData
import net.minecraft.structure.StructureTemplate.StructureBlockInfo
import net.minecraft.structure.processor.StructureProcessor
import net.minecraft.structure.processor.StructureProcessorType
import net.minecraft.util.math.BlockPos
import net.minecraft.world.WorldView
import org.teamvoided.dusks_biomes.init.DuskStructureProcessorTypes

object PaleOakReplacementStructureProcessor : StructureProcessor() {
    private val REPLACEMENT_BLOCKS: Map<Block, Block> = mapOf(
        Blocks.BIRCH_LOG to Blocks.PALE_OAK_LOG,
        Blocks.BIRCH_WOOD to Blocks.PALE_OAK_WOOD,
        Blocks.STRIPPED_BIRCH_LOG to Blocks.STRIPPED_PALE_OAK_LOG,
        Blocks.STRIPPED_BIRCH_WOOD to Blocks.STRIPPED_PALE_OAK_WOOD,
        Blocks.BIRCH_PLANKS to Blocks.PALE_OAK_PLANKS,
        Blocks.BIRCH_STAIRS to Blocks.PALE_OAK_STAIRS,
        Blocks.BIRCH_SLAB to Blocks.PALE_OAK_SLAB,
        Blocks.BIRCH_FENCE to Blocks.PALE_OAK_FENCE,
        Blocks.BIRCH_FENCE_GATE to Blocks.PALE_OAK_FENCE_GATE,
        Blocks.BIRCH_DOOR to Blocks.PALE_OAK_DOOR,
        Blocks.BIRCH_TRAPDOOR to Blocks.PALE_OAK_TRAPDOOR,
        Blocks.BIRCH_BUTTON to Blocks.PALE_OAK_BUTTON,
        Blocks.BIRCH_PRESSURE_PLATE to Blocks.PALE_OAK_PRESSURE_PLATE,
        Blocks.BIRCH_SIGN to Blocks.PALE_OAK_SIGN,
        Blocks.BIRCH_WALL_SIGN to Blocks.PALE_OAK_WALL_SIGN,
        Blocks.BIRCH_HANGING_SIGN to Blocks.PALE_OAK_HANGING_SIGN,
        Blocks.BIRCH_WALL_HANGING_SIGN to Blocks.PALE_OAK_WALL_HANGING_SIGN,
        Blocks.BIRCH_SAPLING to Blocks.PALE_OAK_SAPLING,
        Blocks.POTTED_BIRCH_SAPLING to Blocks.POTTED_PALE_OAK_SAPLING,
        Blocks.WHITE_CARPET to Blocks.YELLOW_CARPET,
    )
    override fun getType(): StructureProcessorType<*> = DuskStructureProcessorTypes.PALE_OAK_REPLACE
    override fun process(
        world: WorldView,
        pos: BlockPos,
        pivot: BlockPos,
        localBlockInfo: StructureBlockInfo,
        currentBlock: StructureBlockInfo,
        placementData: StructurePlacementData,
    ): StructureBlockInfo {
        val replaceWith = REPLACEMENT_BLOCKS[currentBlock.state().block] ?: return currentBlock

        val blockState = currentBlock.state()
        val newState = replaceWith.getStateWithProperties(blockState)
        return StructureBlockInfo(currentBlock.pos(), newState, currentBlock.nbt())
    }

    private fun StructureBlockInfo.pos(): BlockPos = this.comp_1341
    private fun StructureBlockInfo.state(): BlockState = this.comp_1342
    private fun StructureBlockInfo.nbt(): NbtCompound? = this.comp_1343

    val CODEC: MapCodec<PaleOakReplacementStructureProcessor> = MapCodec.unit { PaleOakReplacementStructureProcessor }
}
