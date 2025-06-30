//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package org.teamvoided.dusks_biomes.structure

import com.google.common.collect.Maps
import com.mojang.serialization.MapCodec
import net.minecraft.block.*
import net.minecraft.structure.Structure.StructureBlockInfo
import net.minecraft.structure.StructurePlacementData
import net.minecraft.structure.processor.StructureProcessor
import net.minecraft.structure.processor.StructureProcessorType
import net.minecraft.util.Util
import net.minecraft.util.math.BlockPos
import net.minecraft.world.WorldView

class PaleOakReplacementStructureProcessor : StructureProcessor() {
    private val replacementMap: Map<Block, Block> = Util.make(Maps.newHashMap()) { hashMap ->
        hashMap[Blocks.BIRCH_PLANKS] = Blocks.PALE_OAK_PLANKS
        hashMap[Blocks.BIRCH_STAIRS] = Blocks.PALE_OAK_STAIRS
        hashMap[Blocks.BIRCH_SLAB] = Blocks.PALE_OAK_SLAB
        hashMap[Blocks.BIRCH_FENCE] = Blocks.PALE_OAK_FENCE
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

    override fun getType(): StructureProcessorType<*> {
        return StructureProcessorType.BLACKSTONE_REPLACE
    }

    companion object {
        val CODEC: MapCodec<PaleOakReplacementStructureProcessor> = MapCodec.unit { INSTANCE }
        val INSTANCE: PaleOakReplacementStructureProcessor = PaleOakReplacementStructureProcessor()
    }
}
