package org.teamvoided.dusks_biomes.world.level.levelgen

import com.mojang.serialization.Codec
import net.minecraft.core.BlockPos
import net.minecraft.core.BlockPos.TraversalNodeStatus
import net.minecraft.core.Direction
import net.minecraft.util.Util
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.MultifaceBlock
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import org.teamvoided.dusks_biomes.world.level.levelgen.config.MultifaceFeatureConfig
import java.util.function.Consumer

class MultifaceFeature(codec: Codec<MultifaceFeatureConfig>) :
    Feature<MultifaceFeatureConfig>(codec) {

    override fun place(featurePlaceContext: FeaturePlaceContext<MultifaceFeatureConfig>): Boolean {
        val world = featurePlaceContext.level()
        val origin = featurePlaceContext.origin()
        val r = featurePlaceContext.random()
        val c = featurePlaceContext.config()

        BlockPos.breadthFirstTraversal(
            origin,
            c.radius.sample(r), //range
            c.count.sample(r), //block path
            { blockPos: BlockPos, consumer: Consumer<BlockPos> ->
                for (direction in Util.shuffledCopy(Direction.entries.toTypedArray(), r)) {
                    val blockPos2 = blockPos.relative(direction)
                    if (MultifaceBlock.canAttachTo(world, direction, blockPos, world.getBlockState(blockPos))) {
                        consumer.accept(blockPos2)
                    }
                }
            },
            { blockPos: BlockPos ->
                if (!world.getBlockState(blockPos).isCollisionShapeFullBlock(world, blockPos)) {
                    return@breadthFirstTraversal TraversalNodeStatus.SKIP
                } else {
                    for (direction in Util.shuffledCopy(Direction.entries.toTypedArray(), r)) {
                        val offsetPos = blockPos.relative(direction)
                        var worldState = world.getBlockState(offsetPos)
                        val blockstate = c.blockstate.getState(r, offsetPos)
                        val direction2 = direction.opposite
                        if (worldState.isAir) {
                            worldState = blockstate
                        } else if (worldState.`is`(Blocks.WATER) && worldState.fluidState.isSource) {
                            worldState = blockstate.setValue(MultifaceBlock.WATERLOGGED, true)
                        }

                        if (worldState.`is`(blockstate.block) && !MultifaceBlock.hasFace(worldState, direction2)) {
                            world.setBlock(
                                offsetPos,
                                worldState.setValue(MultifaceBlock.getFaceProperty(direction2), true),
                                3
                            )
                            //return@breadthFirstTraversal TraversalNodeStatus.STOP
                        }
                    }

                    return@breadthFirstTraversal TraversalNodeStatus.ACCEPT
                }
            })
        return true
    }
}