package org.teamvoided.dusks_biomes.world.level.levelgen

import com.mojang.serialization.Codec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.tags.BlockTags
import net.minecraft.util.RandomSource
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.MultifaceBlock
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration
import org.teamvoided.dusks_biomes.util.Utils
import org.teamvoided.dusks_biomes.util.Utils.directionNoAxis
import kotlin.math.absoluteValue

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
        var attDir = dir
        var pos = origin
        for (i in 0..15) {
            //random rotation
            //if (i > 0) {
            //    val d = Direction.getRandom(r)
            //    val ad = flatFaceDir(world, pos.relative(dir, 2), d.axis)
            //    if (d != dir.opposite && ad != null && (d != originalDir || r.nextBoolean())) {
            //        if (world.getBlockState(pos.relative(dir)).`is`(BlockTags.REPLACEABLE)) {
            //            dir = d
            //            attDir = ad
            //        }
            //    }
            //}

            pos = pos.relative(dir)
            //stops from crossing chunk borders
            if (((pos.x - origin.x).absoluteValue < 16 && (pos.z - origin.z).absoluteValue < 16) &&
                world.getBlockState(pos).`is`(BlockTags.REPLACEABLE)
            ) {
                val rotate = r.nextInt(3)>0

                // checks to see if the next position is on the same plane as prev, else tries to find a new plane
                if (rotate && MultifaceBlock.canAttachTo(world, pos, attDir)) {
                    world.setBlock(pos, dir)
                } else {
                    val ad = flatFaceDir(world, pos, dir.axis)
                    if (rotate && ad != null) {
                        attDir = ad
                        world.setBlock(pos, dir)
                    } else {
                        var work = false
                        for (dir2 in directionNoAxis(dir.axis).shuffled()) {
                            val pos2 = pos.relative(dir2)
                            val ad = flatFaceDir(world, pos2, dir2.axis)
                            if (ad != null && world.getBlockState(pos2).`is`(BlockTags.REPLACEABLE)) {
                                dir = dir2
                                attDir = ad
                                world.setBlock(pos, dir)
                                //pos = pos2
                                //world.setBlock(pos2, dir2)
                                work = true
                            }
                        }
                        if (!work) return
                    }
                }
            } else return
        }
    }

    private fun LevelAccessor.setBlock(pos: BlockPos, direction: Direction): Boolean {
        return this.setBlock(pos, Utils.trySetDirectionals(Blocks.PALE_OAK_LOG, direction), 2)
    }

    private fun BlockPos.distance(pos: BlockPos): BlockPos {
        val x = (this.x - pos.x).absoluteValue
        val y = (this.y - pos.y).absoluteValue
        val z = (this.z - pos.z).absoluteValue
        return BlockPos(x, y, z)
    }

//if (r.nextInt(2) != 0) {}
//Direction.entries.shuffled().forEach {
//    if (it != dir) {
//        MultifaceBlock.canAttachTo(world, pos, it)
//    }
//}

    private fun flatFaceDir(levelAccessor: LevelAccessor, blockPos: BlockPos, axis: Direction.Axis): Direction? {
        directionNoAxis(axis).shuffled().forEach {
            if (MultifaceBlock.canAttachTo(levelAccessor, blockPos, it))
                return it
        }
        return null
    }

    private fun flatFaceDir(levelAccessor: LevelAccessor, blockPos: BlockPos): Direction? {
        Direction.entries.shuffled().forEach {
            if (MultifaceBlock.canAttachTo(levelAccessor, blockPos, it))
                return it
        }
        return null
    }
}