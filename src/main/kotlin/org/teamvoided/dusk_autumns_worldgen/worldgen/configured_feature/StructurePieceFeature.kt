package org.teamvoided.dusk_autumns_worldgen.worldgen.configured_feature

import com.mojang.serialization.Codec
import net.minecraft.block.Blocks
import net.minecraft.structure.StructurePlacementData
import net.minecraft.util.BlockMirror
import net.minecraft.util.BlockRotation
import net.minecraft.util.math.BlockBox
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.ChunkPos
import net.minecraft.world.StructureWorldAccess
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.util.FeatureContext
import org.apache.commons.lang3.mutable.MutableInt
import org.teamvoided.dusk_autumns_worldgen.worldgen.configured_feature.config.StructurePieceFeatureConfig
import kotlin.math.max
import kotlin.math.min

class StructurePieceFeature(configCodec: Codec<StructurePieceFeatureConfig>?) : Feature<StructurePieceFeatureConfig>(configCodec) {

    override fun place(context: FeatureContext<StructurePieceFeatureConfig>): Boolean {
        val randomGenerator = context.random
        val structureWorldAccess = context.world
        val blockPos = context.origin
        val blockRotation = BlockRotation.random(randomGenerator)
        val config = context.config
        val i = randomGenerator.nextInt(config.structures.size)
        val structureTemplateManager = structureWorldAccess.toServerWorld().server.structureTemplateManager
        val structure = structureTemplateManager.getStructureOrBlank(config.structures[i])
//        val structure2 = structureTemplateManager.getStructureOrBlank(config.overlayStructures[i])
        val chunkPos = ChunkPos(blockPos)
        val blockBox = BlockBox(
            chunkPos.startX - 16,
            structureWorldAccess.bottomY,
            chunkPos.startZ - 16,
            chunkPos.endX + 16,
            structureWorldAccess.topY,
            chunkPos.endZ + 16
        )
        val structurePlacementData =
            StructurePlacementData().setRotation(blockRotation).setBoundingBox(blockBox).setRandom(randomGenerator)
        val vec3i = structure.getRotatedSize(blockRotation)
        val blockPos2 = blockPos.add(-vec3i.x / 2, 0, -vec3i.z / 2)
        var j = blockPos.y
        var k = 0
        while (k < vec3i.x) {
            for (l in 0 until vec3i.z) {
                j = min(
                    j,
                    structureWorldAccess.getTopY(config.heightmap, blockPos2.x + k, blockPos2.z + l)
                )
            }
            ++k
        }
        k = max((j - 15 - randomGenerator.nextInt(10)).toDouble(), (structureWorldAccess.bottomY + 10).toDouble())
            .toInt()
        val blockPos3 = structure.offsetByTransformedSize(blockPos2.withY(k), BlockMirror.NONE, blockRotation)
        if (getEmptyCorners(
                structureWorldAccess,
                structure.calculateBoundingBox(structurePlacementData, blockPos3)
            ) > config.maxEmptyCorners
        ) {
            return false
        }
        structurePlacementData.clearProcessors()
        config.processors.value().list.forEach { structurePlacementData.addProcessor(it) }
        structure.place(structureWorldAccess, blockPos3, blockPos3, structurePlacementData, randomGenerator, 4)
//        structurePlacementData.clearProcessors()
//        config.overlayProcessors.value().list.forEach { structurePlacementData.addProcessor(it) }
//        structure2.place(structureWorldAccess, blockPos3, blockPos3, structurePlacementData, randomGenerator, 4)
        return true
    }

    private fun getEmptyCorners(world: StructureWorldAccess, box: BlockBox): Int {
        val mutableInt = MutableInt(0)
        box.forEachVertex { pos: BlockPos? ->
            val blockState = world.getBlockState(pos)
            if (blockState.isAir || blockState.isOf(Blocks.LAVA) || blockState.isOf(Blocks.WATER)) {
                mutableInt.add(1)
            }
        }
        return mutableInt.value
    }


}