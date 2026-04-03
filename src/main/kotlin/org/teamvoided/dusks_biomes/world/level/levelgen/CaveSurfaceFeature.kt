package org.teamvoided.dusks_biomes.world.level.levelgen

import com.mojang.serialization.Codec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.util.Mth.lerp
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.MultifaceBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import org.teamvoided.dusks_biomes.world.level.levelgen.config.CaveSurfaceFeatureConfig

class CaveSurfaceFeature(codec: Codec<CaveSurfaceFeatureConfig>) :
    Feature<CaveSurfaceFeatureConfig>(codec) {

    override fun place(context: FeaturePlaceContext<CaveSurfaceFeatureConfig>): Boolean {
        val sourcePos = context.origin()
        val worldGenLevel = context.level()
        if (flatFaceDir(worldGenLevel, sourcePos) == null) return false
        val c = context.config()
        val r = context.random()
        if (worldGenLevel.getBlockState(sourcePos).`is`(c.canReplace))
            worldGenLevel.setBlock(sourcePos, c.blockstate.getState(r, sourcePos), 2)

        val rad = c.radius.sample(r)
        val lowPos = sourcePos.offset(-rad, -rad, -rad)
        val highPos = sourcePos.offset(rad, rad, rad)
        val iterator = BlockPos.betweenClosed(lowPos, highPos)//.sortedBy { it.distSqr(sourcePos) }

        for (pos in iterator) {
            val chanceCore = c.blockChanceCenter.sample(r).toDouble()
            val chanceEdge = c.blockChanceEdge.sample(r).toDouble()
            val chance = lerp(pos.distSqr(sourcePos) / (rad * rad), chanceCore, chanceEdge)
            if (chance > r.nextFloat() && worldGenLevel.getBlockState(pos).`is`(c.canReplace)) {
                val dir = flatFaceDir(worldGenLevel, pos)
                if (dir != null)
                    worldGenLevel.setBlock(pos, c.blockstate.getState(r, pos), 2)
            }

            //if (pos.distSqr(sourcePos) / rad < 0.5)
            //    worldGenLevel.setBlock(pos, Blocks.GLOWSTONE.defaultBlockState(), 2)
            //else
            //    worldGenLevel.setBlock(pos, Blocks.TINTED_GLASS.defaultBlockState(), 2)
        }

        //worldGenLevel.setBlock(lowPos, Blocks.GLOWSTONE.defaultBlockState(), 2)
        //worldGenLevel.setBlock(highPos, Blocks.GLOWSTONE.defaultBlockState(), 2)
        return true
    }

    private fun flatFaceDir(levelAccessor: LevelAccessor, blockPos: BlockPos): Direction? {
        Direction.entries.shuffled().forEach {
            if (MultifaceBlock.canAttachTo(levelAccessor, blockPos, it))
                return it
        }
        return null
    }

    private fun trySetDirectionals(state: BlockState, dir: Direction): BlockState {
        return state
            .trySetValue(BlockStateProperties.FACING, dir)
            .trySetValue(BlockStateProperties.AXIS, dir.axis)
    }
}