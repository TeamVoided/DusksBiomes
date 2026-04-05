package org.teamvoided.dusks_biomes.util

import net.minecraft.core.Direction
import net.minecraft.util.Util
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.PipeBlock
import net.minecraft.world.level.block.VineBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator

object Utils {
    fun setCount(x: Number, y: Number) = SetItemCountFunction.setCount(uniformNum(x, y))
    fun uniformNum(x: Number, y: Number): UniformGenerator = UniformGenerator.between(x.toFloat(), y.toFloat())

    fun directionNoAxis(axis: Direction.Axis): List<Direction> {
        return when (axis) {
            Direction.Axis.Y -> listOf(Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST)
            Direction.Axis.X -> listOf(Direction.DOWN, Direction.UP, Direction.NORTH, Direction.SOUTH)
            Direction.Axis.Z -> listOf(Direction.DOWN, Direction.UP, Direction.WEST, Direction.EAST)
        }
    }


    fun trySetDirectionals(state: Block, dir: Direction): BlockState {
        return state.defaultBlockState()
            .trySetValue(BlockStateProperties.FACING, dir)
            .trySetValue(BlockStateProperties.AXIS, dir.axis)
    }

    val PROPERTY_BY_DIRECTION: Map<Direction, BooleanProperty> =
        PipeBlock.PROPERTY_BY_DIRECTION.entries.stream()
            .collect(Util.toMap())
    val PROPERTY_BY_DIRECTION_WITHOUT_DOWN: Map<Direction, BooleanProperty> =
        PipeBlock.PROPERTY_BY_DIRECTION.entries.stream()
            .filter { it.key != Direction.DOWN }
            .collect(Util.toMap())

}