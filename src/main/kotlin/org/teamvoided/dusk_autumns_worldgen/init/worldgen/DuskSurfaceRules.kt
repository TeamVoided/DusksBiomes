package org.teamvoided.dusk_autumns_worldgen.init.worldgen

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.world.biome.Biomes
import net.minecraft.world.gen.YOffset
import net.minecraft.world.gen.noise.NoiseParametersKeys
import net.minecraft.world.gen.surfacebuilder.SurfaceRules.*
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules

object DuskSurfaceRules {

    private fun block(block: Block): MaterialRule {
        return block(block.defaultState)
    }


    fun overworld(): MaterialRule {
        //Sorted like the vanilla surface rule locations https://minecraft.wiki/w/World_generation#Surface
        //Surface rule sequence 1: Floor
        val swampWater = condition(
            biome(DuskBiomes.FROZEN_MANGROVE_SWAMP), condition(
                ON_FLOOR, sequence(
                    condition(
                        aboveY(YOffset.fixed(60), 0),
                        condition(
                            not(aboveY(YOffset.fixed(63), 0)),
                            condition(noiseThreshold(NoiseParametersKeys.SURFACE_SWAMP, 0.0), block(Blocks.WATER))
                        )
                    )
                )
            )
        )

        //Surface rule sequence 4: floor without water
        val redSandSurface = condition(
            biome(DuskBiomes.RED_DESERT, DuskBiomes.RED_WARM_OCEAN, DuskBiomes.RED_BEACH, DuskBiomes.SNOWY_RED_BEACH),
            sequence(
                condition(
                    ON_CEILING, block(Blocks.RED_SANDSTONE)
                ),
                block(Blocks.RED_SAND)
            )
        )
        val podzolAndCoarseDirt = condition(
            biome(
                DuskBiomes.SNOWY_OLD_GROWTH_PINE_TAIGA,
                DuskBiomes.SNOWY_OLD_GROWTH_SPRUCE_TAIGA
            ),
            sequence(
                condition(surfaceNoiseThreshold(1.75), block(Blocks.COARSE_DIRT)),
                condition(surfaceNoiseThreshold(-0.95), block(Blocks.PODZOL))
            )
        )
        val lessPodzolAndCoarseDirt = condition(
            biome(
                Biomes.MUSHROOM_FIELDS,
                DuskBiomes.ERODED_MUSHROOM_ISLAND,
                DuskBiomes.MUSHROOM_GROVE
            ),
            sequence(
                condition(surfaceNoiseThreshold(1.0), block(Blocks.COARSE_DIRT)),
                condition(surfaceNoiseThreshold(-0.35), block(Blocks.PODZOL))
            )
        )
        val devilsRoar = condition(
            biome(
                DuskBiomes.DEVILS_ROAR
            ),
            sequence(
                condition(surfaceSecondaryNoiseThreshold(-0.95), block(Blocks.BLACKSTONE)),
                condition(
                    DEEP_UNDER_FLOOR, condition(
                        surfaceNoiseThreshold(0.35),
                        sequence(
                            condition(
                                ON_CEILING, block(Blocks.RED_SANDSTONE)
                            ),
                            block(Blocks.RED_SAND)
                        )
                    )
                )
            )
        )
        val mud = condition(
            biome(DuskBiomes.FROZEN_MANGROVE_SWAMP),
            block(Blocks.MUD)
        )
        val mycelium = condition(
            biome(DuskBiomes.ERODED_MUSHROOM_ISLAND, DuskBiomes.MUSHROOM_GROVE),
            block(Blocks.MYCELIUM)
        )

        //Deep under floor with water above (or not)
        val redSandDeep = condition(
            biome(DuskBiomes.RED_WARM_OCEAN, DuskBiomes.RED_BEACH, DuskBiomes.SNOWY_RED_BEACH),
            sequence(
                condition(
                    ON_CEILING, block(Blocks.RED_SANDSTONE)
                ),
                block(Blocks.RED_SAND)
            )
        )
        val mudDeep = condition(
            biome(DuskBiomes.FROZEN_MANGROVE_SWAMP),
            block(Blocks.MUD)
        )
        val redSandstoneDesert = condition(
            biome(DuskBiomes.RED_DESERT),
            sequence(
                condition(
                    DEEPEST_LEVEL_UNDER_FLOOR, block(Blocks.RED_SANDSTONE)
                )
            )
        )
        //Surface rule sequence 5
        val redSandOcean = condition(
            ON_FLOOR, condition(
                biome(DuskBiomes.RED_WARM_OCEAN, DuskBiomes.RED_LUKEWARM_OCEAN, DuskBiomes.DEEP_RED_LUKEWARM_OCEAN),
                sequence(
                    condition(
                        ON_CEILING, block(Blocks.RED_SANDSTONE)
                    ),
                    block(Blocks.RED_SAND)
                )
            )
        )


        //Begin the Layout
        //
        //
        val onFloorAndWater = condition(
            ON_FLOOR, condition(
                water(-1, 0), sequence(
                    redSandSurface,
                    podzolAndCoarseDirt,
                    lessPodzolAndCoarseDirt,
                    mud,
                    mycelium
                )
            )
        )
        val onFloorInDeepWater = condition(
            DEEP_UNDER_FLOOR, condition(
                water(-6, 0), sequence(
                    redSandSurface,
                    mudDeep
                )
            )
        )
        val surface = condition(
            abovePreliminarySurface(),
            sequence(
                swampWater,
                onFloorAndWater,
                onFloorInDeepWater,
                redSandDeep,
                redSandstoneDesert,
                redSandOcean,
                devilsRoar
            )
        )
        // Return a surface-only sequence of surface rules
        return sequence(surface)
    }

    fun surfaceNoiseThreshold(min: Double): MaterialCondition {
        return noiseThreshold(NoiseParametersKeys.SURFACE, min / 8.25, Double.MAX_VALUE)
    }

    fun surfaceSecondaryNoiseThreshold(min: Double): MaterialCondition {
        return noiseThreshold(NoiseParametersKeys.SURFACE_SECONDARY, min / 8.25, Double.MAX_VALUE)
    }
}