package org.teamvoided.dusk_autumns_worldgen.worldgen.treedecorator

import com.mojang.serialization.Codec
import net.minecraft.block.VineBlock
import net.minecraft.state.property.BooleanProperty
import net.minecraft.util.math.BlockPos
import net.minecraft.world.gen.treedecorator.TreeDecorator
import net.minecraft.world.gen.treedecorator.TreeDecoratorType
import org.teamvoided.dusk_autumns_worldgen.init.DuskWorldgen
import java.util.function.Consumer

class FixedLeavesVineTreeDecorator(private val randomPlacementThreshold: Float) : TreeDecorator() {
    override fun getType(): TreeDecoratorType<*> = DuskWorldgen.FIXED_LEAVE_VINE

    override fun generate(placer: Placer) {
        val randomGenerator = placer.random
        placer.leafPositions.forEach(Consumer { pos: BlockPos ->
            var blockPos: BlockPos
            if (randomGenerator.nextFloat() < this.randomPlacementThreshold) {
                blockPos = pos.west()
                if (placer.isAir(blockPos)) {
                    placeVines(blockPos, VineBlock.EAST, placer)
                }
            }

            if (randomGenerator.nextFloat() < this.randomPlacementThreshold) {
                blockPos = pos.east()
                if (placer.isAir(blockPos)) {
                    placeVines(blockPos, VineBlock.WEST, placer)
                }
            }

            if (randomGenerator.nextFloat() < this.randomPlacementThreshold) {
                blockPos = pos.north()
                if (placer.isAir(blockPos)) {
                    placeVines(blockPos, VineBlock.SOUTH, placer)
                }
            }
            if (randomGenerator.nextFloat() < this.randomPlacementThreshold) {
                blockPos = pos.south()
                if (placer.isAir(blockPos)) {
                    placeVines(blockPos, VineBlock.NORTH, placer)
                }
            }
        })
    }

    companion object {

        val CODEC: Codec<FixedLeavesVineTreeDecorator> = Codec.floatRange(0.0f, 1.0f).fieldOf("probability")
            .xmap(::FixedLeavesVineTreeDecorator) { it.randomPlacementThreshold }.codec()
        private fun placeVines(pos: BlockPos, facing: BooleanProperty, placer: Placer) {
            var pos1 = pos
            placer.replaceWithVine(pos1, facing)
            var i = 4

            pos1 = pos1.down()
            while (placer.isAir(pos1) && i > 0) {
                if (!placer.world.testBlockState(pos1.up()) { it.block is VineBlock }) break
                placer.replaceWithVine(pos1, facing)
                pos1 = pos1.down()
                --i
            }
        }
    }

}