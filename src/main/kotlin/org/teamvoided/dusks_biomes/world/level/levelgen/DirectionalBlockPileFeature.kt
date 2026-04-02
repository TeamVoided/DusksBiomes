package org.teamvoided.dusks_biomes.world.level.levelgen

import com.mojang.serialization.Codec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.RandomSource
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import org.teamvoided.dusks_biomes.world.level.levelgen.config.DirectionalBlockPileFeatureConfig

class DirectionalBlockPileFeature(codec: Codec<DirectionalBlockPileFeatureConfig>) :
    Feature<DirectionalBlockPileFeatureConfig>(codec) {

    override fun place(c: FeaturePlaceContext<DirectionalBlockPileFeatureConfig>): Boolean {
        val blockPos = c.origin()
        val worldGenLevel = c.level()
        if (blockPos.y < worldGenLevel.minY + 5) {
            return false
        } else {
            val config = c.config()
            val r = c.random()
            val xRad = config.radius.sample(r) //2 + randomSource.nextInt(2)
            val zRad = config.radius.sample(r)
            val dir = Direction.DOWN
            val sizeLow = BlockPos(-xRad, 0, -zRad).rotate(dir)
            val sizeHigh = BlockPos(xRad, config.height.sample(r), zRad).rotate(dir)

            for (loopPos in BlockPos.betweenClosed(
                sizeLow,
                sizeHigh
            )) {
                if (
                    circleRange(loopPos, xRad, zRad, dir, r) ||
                    r.nextFloat() < config.blockChance.sample(r) //0.03f
                ) {
                    this.tryPlaceBlock(worldGenLevel, loopPos.offset(blockPos), dir, r, config)
                }
            }
            worldGenLevel.setBlock(blockPos.offset(sizeLow), Blocks.GLOWSTONE.defaultBlockState(), 260)
            worldGenLevel.setBlock(blockPos.offset(sizeHigh), Blocks.GLOWSTONE.defaultBlockState(), 260)

            return true
        }
    }

    private fun circleRange(
        pos: BlockPos,
        rad1: Int,
        rad2: Int,
        dir: Direction,
        c: DirectionalBlockPileFeatureConfig,
        r: RandomSource
    ): Boolean {
        val compare = when (dir.axis) {
            Direction.Axis.Y -> (pos.x * pos.x) / (rad1 * rad1) + (pos.z * pos.z) / (rad2 * rad2)
            Direction.Axis.X -> (pos.y * pos.y) / (rad1 * rad1) + (pos.z * pos.z) / (rad2 * rad2)
            Direction.Axis.Z -> (pos.x * pos.x) / (rad1 * rad1) + (pos.y * pos.y) / (rad2 * rad2)
        }
        return compare <= c.blockInRadiusChance.sample(r)
    }

    private fun BlockPos.rotate(dir: Direction): BlockPos {//From down
        return when (dir) {
            Direction.DOWN -> this
            Direction.UP -> BlockPos(this.x, -this.y, this.z)
            Direction.NORTH -> BlockPos(this.x, this.z, -this.y)
            Direction.SOUTH -> BlockPos(this.x, this.z, this.y)
            Direction.EAST -> BlockPos(-this.y, this.x, this.z)
            Direction.WEST -> BlockPos(this.y, this.x, this.z)
        }
    }

    private fun tryPlaceBlock(
        levelAccessor: LevelAccessor,
        blockPos: BlockPos,
        dir: Direction,
        randomSource: RandomSource,
        config: DirectionalBlockPileFeatureConfig
    ): Boolean {
        return if (
            levelAccessor.getBlockState(blockPos).`is`(config.canReplace) &&
            this.mayPlaceOn(levelAccessor, blockPos, dir, randomSource)
        ) {
            levelAccessor.setBlock(blockPos, config.blockstate.getState(randomSource, blockPos), 260)
        } else false
    }

    private fun mayPlaceOn(
        levelAccessor: LevelAccessor,
        blockPos: BlockPos,
        dir: Direction,
        randomSource: RandomSource
    ): Boolean {
        val blockPosDir = blockPos.relative(dir)
        val dirState = levelAccessor.getBlockState(blockPosDir)
        return dirState.isFaceSturdy(levelAccessor, blockPosDir, dir.opposite)
    }
}