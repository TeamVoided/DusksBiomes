package org.teamvoided.dusks_biomes.world.level.levelgen

import com.mojang.serialization.Codec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.tags.BlockTags
import net.minecraft.util.RandomSource
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.MultifaceBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration
import org.teamvoided.dusks_biomes.util.Utils
import org.teamvoided.dusks_biomes.util.Utils.directionNoAxis
import kotlin.math.absoluteValue
import kotlin.math.max
import kotlin.math.min

class CreepingVineFeature(codec: Codec<NoneFeatureConfiguration>) :
    Feature<NoneFeatureConfiguration>(codec) {

    override fun place(context: FeaturePlaceContext<NoneFeatureConfiguration>): Boolean {
        val origin = context.origin()
        val world = context.level()
        val startDir = flatFaceDir(world, origin) ?: return false
        val c = context.config()
        val r = context.random()
        if (world.getBlockState(origin).`is`(BlockTags.REPLACEABLE))
            world.setBlock(origin, Utils.trySetDirectionals(Blocks.PALE_OAK_LOG, startDir), 2)
        directionNoAxis(startDir.axis).forEach {
            arm0(it, origin, world, c, r)
        }

        return true
    }

    private fun arm0(
        originalDir: Direction,
        origin: BlockPos,
        world: LevelAccessor,
        c: NoneFeatureConfiguration,
        r: RandomSource
    ) {
        var dir = originalDir
        var pos = origin
        for (i in 0..32) {
            if (world.getBlockState(origin).`is`(BlockTags.REPLACEABLE)) {
                pos = pos.relative(dir)
                val dist = pos.distance(origin)
                if (dist.x < 16 && dist.z < 16) {
                    if (MultifaceBlock.canAttachTo(world, pos, dir)) {
                        world.setBlock(pos, Utils.trySetDirectionals(Blocks.PALE_OAK_LOG, dir), 2)
                    }
                } else return
            }
        }

    }

    private fun BlockPos.distance(pos: BlockPos): BlockPos {
        val x = (this.x - pos.x).absoluteValue
        val y = (this.y - pos.y).absoluteValue
        val z = (this.z - pos.z).absoluteValue
        return BlockPos(x, y, z)
    }

    private fun arm1(
        originalDir: Direction,
        origin: BlockPos,
        world: LevelAccessor,
        c: NoneFeatureConfiguration,
        r: RandomSource
    ): BlockState? {
        val a = arm2(originalDir, origin, world, c, r)
        if (a != null) return a

        Direction.entries.forEach {
            if (it != originalDir.opposite) {
                val pos2 = origin.relative(originalDir).relative(it)
                val b = arm2(originalDir, pos2, world, c, r)
                if (b != null) return b
            }
        }
        return null
    }

    private fun arm2(
        originalDir: Direction,
        origin: BlockPos,
        world: LevelAccessor,
        c: NoneFeatureConfiguration,
        r: RandomSource
    ): BlockState? {
        val pos = origin.relative(originalDir)
        Direction.entries.forEach {
            if (it != originalDir.opposite && MultifaceBlock.canAttachTo(world, pos, it)) {
                return Utils.trySetDirectionals(Blocks.PALE_OAK_LOG, it)
            }
        }
        return null
    }

    //if (r.nextInt(2) != 0) {}
    //Direction.entries.shuffled().forEach {
    //    if (it != dir) {
    //        MultifaceBlock.canAttachTo(world, pos, it)
    //    }
    //}

    private fun flatFaceDir(levelAccessor: LevelAccessor, blockPos: BlockPos): Direction? {
        Direction.entries.shuffled().forEach {
            if (MultifaceBlock.canAttachTo(levelAccessor, blockPos, it))
                return it
        }
        return null
    }
}