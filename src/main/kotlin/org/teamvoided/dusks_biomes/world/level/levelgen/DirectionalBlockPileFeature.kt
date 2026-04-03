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

    override fun place(context: FeaturePlaceContext<DirectionalBlockPileFeatureConfig>): Boolean {
        val blockPos = context.origin()
        val worldGenLevel = context.level()
        val c = context.config()
        if (blockPos.y < worldGenLevel.minY + 5 ||
            (worldGenLevel.getBlockState(blockPos).`is`(c.canReplace))
        ) {
            return false
        } else {
            val r = context.random()
            val xRad = c.radius.sample(r) //2 + randomSource.nextInt(2)
            val zRad = c.radius.sample(r)
            val height = c.height.sample(r) - 1
            var dir: Direction? = null
            Direction.entries.shuffled().forEach {
                if ((it == Direction.DOWN && c.floor) || (it == Direction.UP && c.ceiling) || (it.axis != Direction.Axis.Y && c.walls)) {
                    if (this.mayPlaceOn(worldGenLevel, blockPos, it))
                        dir = it
                }
            }
            if (dir == null) return false
            val sizeLow = BlockPos(-xRad, 0, -zRad).rotate(dir)
            val sizeHigh = BlockPos(xRad, height, zRad).rotate(dir)

            //between closed does min(a,b) so block stacking issues will arise when facing negative directions?
            for (loopPos in BlockPos.betweenClosed(sizeLow, sizeHigh)) {
                if (
                    circleRange(loopPos, xRad, zRad, dir, c, r) ||
                    r.nextFloat() < c.blockChance.sample(r) //0.03f
                ) {
                    this.tryPlaceBlock(worldGenLevel, warp(loopPos, dir, height).offset(blockPos), dir, r, c)
                }
            }
            worldGenLevel.setBlock(blockPos.offset(sizeLow), Blocks.GLOWSTONE.defaultBlockState(), 260)
            worldGenLevel.setBlock(blockPos.offset(sizeHigh), Blocks.GLOWSTONE.defaultBlockState(), 260)

            return true
        }
    }

    private fun warp(pos: BlockPos, dir: Direction, height: Int): BlockPos {
        return if (dir.axisDirection == Direction.AxisDirection.NEGATIVE) {
            when (dir.axis) {
                Direction.Axis.Y -> BlockPos(pos.x, height - pos.y, pos.z)
                Direction.Axis.X -> BlockPos(height - pos.x, pos.y, pos.z)
                Direction.Axis.Z -> BlockPos(pos.x, pos.y, height - pos.z)
            }
        } else
            pos
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
        r: RandomSource,
        c: DirectionalBlockPileFeatureConfig
    ): Boolean {
        return if (
            levelAccessor.getBlockState(blockPos).`is`(c.canReplace) &&
            this.mayPlaceOn(levelAccessor, blockPos, dir)
        ) {
            levelAccessor.setBlock(blockPos, c.blockstate.getState(r, blockPos), 260)
        } else false
    }

    private fun mayPlaceOn(
        levelAccessor: LevelAccessor,
        blockPos: BlockPos,
        dir: Direction
    ): Boolean {
        val blockPosDir = blockPos.relative(dir)
        val dirState = levelAccessor.getBlockState(blockPosDir)
        return dirState.isFaceSturdy(levelAccessor, blockPosDir, dir.opposite)
    }
}