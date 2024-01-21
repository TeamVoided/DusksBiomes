package org.teamvoided.dusk_autumns_worldgen.init.worldgen

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.world.biome.Biomes
import net.minecraft.world.gen.noise.NoiseParametersKeys
import net.minecraft.world.gen.surfacebuilder.SurfaceRules.*

object DuskSurfaceRules {

    private fun block(block: Block): MaterialRule {
        return block(block.defaultState)
    }


    fun overworld(): MaterialRule {
        val crimsonForest = condition(
            biome(Biomes.CRIMSON_FOREST), sequence(
                condition(
                    UNDER_FLOOR,
                    sequence(
                        condition(
                            noiseThreshold(NoiseParametersKeys.NETHER_WART, 1.17),
                            block(Blocks.NETHER_WART_BLOCK)
                        ),
                        block(Blocks.CRIMSON_NYLIUM)
                    )
                ),
                block(Blocks.NETHERRACK)
            )
        )

        val warpedForest = condition(
            biome(DuskBiomes.RED_DESERT),
            sequence(
                condition(
                    ON_CEILING,
                    sequence(
                        block(Blocks.RED_SANDSTONE)
                    )
                ),
                block(Blocks.RED_SAND)
            )
        )

        val end = condition(biome(Biomes.END_HIGHLANDS), sequence(block(Blocks.END_STONE)))

        // Return a surface-only sequence of our surface rules
        return condition(abovePreliminarySurface(), sequence(crimsonForest, warpedForest, end))
    }
}