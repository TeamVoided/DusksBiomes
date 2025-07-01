package org.teamvoided.dusks_biomes.structure

import com.google.common.collect.Maps
import com.mojang.serialization.MapCodec
import net.minecraft.block.*
import net.minecraft.nbt.NbtCompound
import net.minecraft.structure.StructureTemplate.StructureBlockInfo
import net.minecraft.structure.StructurePlacementData
import net.minecraft.structure.processor.StructureProcessor
import net.minecraft.structure.processor.StructureProcessorType
import net.minecraft.util.Util
import net.minecraft.util.math.BlockPos
import net.minecraft.world.WorldView

class PaleOakReplacementStructureProcessor : StructureProcessor() {
    private val replacementMap: Map<Block, Block> = Util.make(Maps.newHashMap()) { hashMap ->
        //hashMap[Blocks.BIRCH_LOG] = Blocks.PALE_OAK_LOG
        //hashMap[Blocks.BIRCH_WOOD] = Blocks.PALE_OAK_WOOD
        //hashMap[Blocks.STRIPPED_BIRCH_LOG] = Blocks.STRIPPED_PALE_OAK_LOG
        //hashMap[Blocks.STRIPPED_BIRCH_WOOD] = Blocks.STRIPPED_PALE_OAK_WOOD
        hashMap[Blocks.BIRCH_PLANKS] = Blocks.PALE_OAK_PLANKS
        hashMap[Blocks.BIRCH_STAIRS] = Blocks.PALE_OAK_STAIRS
        hashMap[Blocks.BIRCH_SLAB] = Blocks.PALE_OAK_SLAB
        hashMap[Blocks.BIRCH_FENCE] = Blocks.PALE_OAK_FENCE
        //hashMap[Blocks.BIRCH_FENCE_GATE] = Blocks.PALE_OAK_FENCE_GATE
        //hashMap[Blocks.BIRCH_DOOR] = Blocks.PALE_OAK_DOOR
        //hashMap[Blocks.BIRCH_TRAPDOOR] = Blocks.PALE_OAK_TRAPDOOR
        //hashMap[Blocks.BIRCH_BUTTON] = Blocks.PALE_OAK_BUTTON
        //hashMap[Blocks.BIRCH_PRESSURE_PLATE] = Blocks.PALE_OAK_PRESSURE_PLATE
        //hashMap[Blocks.BIRCH_SIGN] = Blocks.PALE_OAK_SIGN
        //hashMap[Blocks.BIRCH_WALL_SIGN] = Blocks.PALE_OAK_WALL_SIGN
        //hashMap[Blocks.BIRCH_HANGING_SIGN] = Blocks.PALE_OAK_HANGING_SIGN
        //hashMap[Blocks.BIRCH_WALL_HANGING_SIGN] = Blocks.PALE_OAK_WALL_HANGING_SIGN
        //hashMap[Blocks.BIRCH_SAPLING] = Blocks.PALE_OAK_SAPLING
        hashMap[Blocks.POTTED_BIRCH_SAPLING] = Blocks.POTTED_PALE_OAK_SAPLING
        hashMap[Blocks.WHITE_CARPET] = Blocks.YELLOW_CARPET
    }

    override fun process(
        world: WorldView,
        pos: BlockPos,
        pivot: BlockPos,
        localBlockInfo: StructureBlockInfo,
        currentBlock: StructureBlockInfo,
        placementData: StructurePlacementData
    ): StructureBlockInfo {
        val replaceWith = replacementMap[currentBlock.state().block]
        if (replaceWith == null) {
            return currentBlock
        } else {
            val blockState = currentBlock.state()
            val newState = replaceWith.getStateWithProperties(blockState)
            return StructureBlockInfo(currentBlock.pos(), newState, currentBlock.nbt())
        }
    }

    private fun StructureBlockInfo.pos(): BlockPos = this.comp_1341
    private fun StructureBlockInfo.state(): BlockState = this.comp_1342
    private fun StructureBlockInfo.nbt(): NbtCompound? = this.comp_1343

    override fun getType(): StructureProcessorType<*> {
        return StructureProcessorType.BLACKSTONE_REPLACE
    }

    companion object {
        val CODEC: MapCodec<PaleOakReplacementStructureProcessor> = MapCodec.unit { INSTANCE }
        val INSTANCE: PaleOakReplacementStructureProcessor = PaleOakReplacementStructureProcessor()
    }
}
