package org.teamvoided.dusk_autumns_worldgen.init.worldgen

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.world.biome.Biomes
import net.minecraft.world.gen.noise.NoiseParametersKeys
import net.minecraft.world.gen.surfacebuilder.SurfaceRules.*
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules

object DuskSurfaceRules {

    private fun block(block: Block): MaterialRule {
        return block(block.defaultState)
    }


    fun overworld(): MaterialRule {
        val podzolAndCoarseDirt = condition(
            biome(DuskBiomes.SNOWY_OLD_GROWTH_PINE_TAIGA, DuskBiomes.SNOWY_OLD_GROWTH_SPRUCE_TAIGA, Biomes.MUSHROOM_FIELDS, DuskBiomes.ERODED_MUSHROOM_ISLAND, DuskBiomes.MUSHROOM_GROVE),
            sequence(
                condition(surfaceNoiseThreshold(1.75), block(Blocks.COARSE_DIRT)),
                condition(surfaceNoiseThreshold(-0.95), block(Blocks.PODZOL))
            )
        )
        val mycelium = condition(
            biome(DuskBiomes.ERODED_MUSHROOM_ISLAND, DuskBiomes.MUSHROOM_GROVE),
            block(Blocks.MYCELIUM)
        )



        val onFloorAndWater = condition(ON_FLOOR, condition(water(-1, 0), sequence(
            podzolAndCoarseDirt,
            mycelium
        )))
        val surface = condition(abovePreliminarySurface(), sequence(onFloorAndWater))
        // Return a surface-only sequence of our surface rules
        return sequence(surface)
    }
    fun surfaceNoiseThreshold(min: Double): MaterialCondition {
        return noiseThreshold(NoiseParametersKeys.SURFACE, min / 8.25, Double.MAX_VALUE)
    }
}