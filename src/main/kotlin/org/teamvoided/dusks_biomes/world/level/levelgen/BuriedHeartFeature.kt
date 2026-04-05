package org.teamvoided.dusks_biomes.world.level.levelgen

import com.mojang.serialization.Codec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.Direction.Plane
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.CreakingHeartBlock
import net.minecraft.world.level.block.state.properties.CreakingHeartState
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration
import net.minecraft.world.phys.shapes.Shapes
import org.teamvoided.dusks_biomes.util.Utils
import org.teamvoided.dusks_biomes.util.Utils.directionNoAxis

class BuriedHeartFeature(codec: Codec<NoneFeatureConfiguration>) :
    Feature<NoneFeatureConfiguration>(codec) {

    override fun place(context: FeaturePlaceContext<NoneFeatureConfiguration>): Boolean {
        val origin = context.origin()
        val world = context.level()

        Direction.Axis.entries.shuffled().forEach {
            var thisAxis = true
            for (direction in directionNoAxis(it)) {
                if (this.isVisibleFromOutside(world, origin.relative(direction), direction.opposite)) {
                    thisAxis = false
                    break
                }
            }
            if (thisAxis && world.getBlockState(origin).`is`(BlockTags.LUSH_GROUND_REPLACEABLE) &&
                ((world.getBlockState(origin.relative(it.positive)).`is`(BlockTags.REPLACEABLE) ||
                        world.getBlockState(origin.relative(it.positive)).`is`(BlockTags.LUSH_GROUND_REPLACEABLE)) &&
                        (world.getBlockState(origin.relative(it.negative)).`is`(BlockTags.LUSH_GROUND_REPLACEABLE) ||
                                world.getBlockState(origin.relative(it.negative)).`is`(BlockTags.REPLACEABLE))
                        )
            ) {
                world.setBlock(
                    origin.relative(it.positive),
                    Utils.trySetDirectionals(Blocks.PALE_OAK_LOG, it.positive),
                    2
                )
                world.setBlock(
                    origin.relative(it.negative),
                    Utils.trySetDirectionals(Blocks.PALE_OAK_LOG, it.negative),
                    2
                )
                world.setBlock(
                    origin,
                    Utils.trySetDirectionals(Blocks.CREAKING_HEART, it.positive)
                        .setValue(CreakingHeartBlock.STATE, CreakingHeartState.AWAKE)
                        .setValue(CreakingHeartBlock.NATURAL, true),
                    2
                )
                return true
            }
        }

        return false
    }

    private fun isVisibleFromOutside(levelAccessor: LevelAccessor, blockPos: BlockPos, direction: Direction): Boolean {
        val blockState = levelAccessor.getBlockState(blockPos)
        val voxelShape = blockState.getFaceOcclusionShape(direction)
        return voxelShape == Shapes.empty() || !Block.isShapeFullBlock(voxelShape)
    }
}