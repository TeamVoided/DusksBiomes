package org.teamvoided.dusk_autumns_worldgen.worldgen.configured_feature

import com.mojang.serialization.Codec
import net.minecraft.block.Blocks
import net.minecraft.util.math.MathHelper
import net.minecraft.world.gen.feature.DefaultFeatureConfig
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.util.FeatureContext
import kotlin.math.abs

class InvertedIceSpikeFeature(configCodec: Codec<DefaultFeatureConfig>?) : Feature<DefaultFeatureConfig>(configCodec) {

    override fun place(context: FeatureContext<DefaultFeatureConfig?>): Boolean {
        var blockPos = context.origin
        val randomGenerator = context.random
        val structureWorldAccess = context.world
//        while (structureWorldAccess.isAir(blockPos) && blockPos.y > structureWorldAccess.bottomY + 2) {
//            blockPos = blockPos.down()
//        }

//        if (!structureWorldAccess.getBlockState(blockPos).isOf(Blocks.AIR)) {
//            return false
//        } else {
        blockPos = blockPos.down(randomGenerator.nextInt(4))
        val i = randomGenerator.nextInt(4) + 7
        val j = i / 4 + randomGenerator.nextInt(2)
        val lower = j > 1 && randomGenerator.nextInt(1) == 0
        if (lower) {
            blockPos = blockPos.down(10 + randomGenerator.nextInt(30))
        }

        var k = 0
        var l: Int
        while (k < i) {
            val f = (1f - k / i) * j
            l = MathHelper.ceil(f)
            for (m in -l..l) {
                val g = MathHelper.abs(m) - 0.25f
                for (n in -l..l) {
                    val h = MathHelper.abs(n) - 0.25f
                    if ((m == 0 && n == 0 || !(g * g + h * h > f * f)) && (m != -l && m != l && n != -l && n != l || !(randomGenerator.nextFloat() > 0.75f))) {
                        var blockState = structureWorldAccess.getBlockState(blockPos.add(m, k, n))
                        if (blockState.isAir || isSoil(blockState) || blockState.isOf(Blocks.SNOW_BLOCK) || blockState.isOf(
                                Blocks.ICE
                            )
                        ) {
                            this.setBlockState(
                                structureWorldAccess,
                                blockPos.add(m, -k, n),
                                Blocks.PACKED_ICE.defaultState
                            )
                        }

                        if (k != 0 && l > 1) {
                            blockState = structureWorldAccess.getBlockState(blockPos.add(m, -k, n))
                            if (blockState.isAir || isSoil(blockState) || blockState.isOf(Blocks.SNOW_BLOCK) || blockState.isOf(
                                    Blocks.ICE
                                )
                            ) {
                                this.setBlockState(
                                    structureWorldAccess,
                                    blockPos.add(m, k, n),
                                    Blocks.PACKED_ICE.defaultState
                                )
                            }
                        }
                    }
                }
            }
            ++k
        }

        k = j - 1
        if (k < 0) {
            k = 0
        } else if (k > 1) {
            k = 1
        }

        if (!lower) return true

        for (o in -k..k) {
            for (q in -k..k) {
                var blockPos2 = context.origin.add(o, -1, q)
                var p = if (abs(o) == 1 && abs(q) == 1) randomGenerator.nextInt(5) else 50

                while (blockPos2.y > blockPos.y) {
                    this.setBlockState(structureWorldAccess, blockPos2, Blocks.PACKED_ICE.defaultState)
                    blockPos2 = blockPos2.down()
                    if (--p > 0) continue
                    blockPos2 = blockPos2.down(randomGenerator.nextInt(5) + 1)
                    p = randomGenerator.nextInt(5)
                }
            }
        }

        return true
    }
}