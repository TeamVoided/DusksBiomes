package org.teamvoided.dusk_autumns_worldgen.init.worldgen

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.util.math.VerticalSurfaceType
import net.minecraft.world.biome.Biomes
import net.minecraft.world.gen.YOffset
import net.minecraft.world.gen.noise.NoiseParametersKeys
import net.minecraft.world.gen.surfacebuilder.SurfaceRules.*

object DuskSurfaceRules {

    private fun block(block: Block): MaterialRule {
        return block(block.defaultState)
    }


    fun overworld(): MaterialRule {
        //Sorted like the vanilla surface rule locations https://minecraft.wiki/w/World_generation#Surface
        //Surface rule sequence 1: Floor
        val swampWater = condition(
            biome(
                DuskBiomes.WINDSWEPT_MANGROVE_SWAMP,
                DuskBiomes.FROZEN_MANGROVE_SWAMP,
                DuskBiomes.FROZEN_WINDSWEPT_MANGROVE_SWAMP,
                DuskBiomes.OLD_GROWTH_SWAMP
            ), condition(
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
        val windsweptHillSurface = condition(
            biome(DuskBiomes.SNOWY_WINDSWEPT_HILLS),
            sequence(
                condition(surfaceNoiseThreshold(1.0), block(Blocks.STONE))
            )
        )
        val sandSurface = sequence(
            condition(
                biome(
                    DuskBiomes.WARM_RIVER
                ),
                sequence(
                    condition(
                        ON_CEILING, block(Blocks.SANDSTONE)
                    ),
                    block(Blocks.SAND)
                )
            ),
            condition(
                biome(
                    DuskBiomes.RED_WARM_RIVER,
                    DuskBiomes.RED_DESERT,
                    DuskBiomes.RED_WARM_OCEAN,
                    DuskBiomes.RED_BEACH,
                    DuskBiomes.SNOWY_RED_BEACH
                ),
                sequence(
                    condition(
                        ON_CEILING, block(Blocks.RED_SANDSTONE)
                    ),
                    block(Blocks.RED_SAND)
                )
            )
        )
        val windsweptBirchSurface = condition(
            biome(DuskBiomes.WINDSWEPT_BIRCH_FOREST),
            sequence(
                condition(surfaceNoiseThreshold(1.75), block(Blocks.STONE)),
                condition(surfaceNoiseThreshold(-0.5), block(Blocks.COARSE_DIRT))
            )
        )
        val windsweptGravelSurface = condition(
            biome(DuskBiomes.SNOWY_WINDSWEPT_GRAVELLY_HILLS),
            sequence(
                condition(
                    surfaceNoiseThreshold(2.0),
                    sequence(
                        condition(
                            ON_CEILING, block(Blocks.STONE)
                        ),
                        block(Blocks.GRAVEL)
                    )
                ),
                condition(surfaceNoiseThreshold(1.0), block(Blocks.STONE)),
                condition(
                    surfaceNoiseThreshold(-1.0),
                    sequence(
                        condition(
                            ON_FLOOR, block(Blocks.GRASS_BLOCK)
                        ),
                        block(Blocks.DIRT)
                    )
                ),
                condition(
                    ON_CEILING, block(Blocks.STONE)
                ),
                block(Blocks.GRAVEL)


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
                condition(surfaceNoiseThreshold(-1.0), block(Blocks.PODZOL))
            )
        )
        val mud = condition(
            biome(
                DuskBiomes.WINDSWEPT_MANGROVE_SWAMP,
                DuskBiomes.FROZEN_MANGROVE_SWAMP,
                DuskBiomes.FROZEN_WINDSWEPT_MANGROVE_SWAMP
            ),
            block(Blocks.MUD)
        )
        val mycelium = condition(
            biome(DuskBiomes.ERODED_MUSHROOM_ISLAND, DuskBiomes.MUSHROOM_GROVE),
            block(Blocks.MYCELIUM)
        )

        //Deep under floor with water above (or not)
        val deepWindsweptHillSurface = condition(
            biome(DuskBiomes.SNOWY_WINDSWEPT_HILLS),
            sequence(
                condition(surfaceNoiseThreshold(1.0), block(Blocks.STONE))
            )
        )
        val deepSand = sequence(
            condition(
                biome(
                    DuskBiomes.WARM_RIVER
                ),
                sequence(
                    condition(
                        ON_CEILING, block(Blocks.SANDSTONE)
                    ),
                    block(Blocks.SAND)
                )
            ),
            condition(
                biome(
                    DuskBiomes.RED_WARM_RIVER,
                    DuskBiomes.RED_WARM_OCEAN,
                    DuskBiomes.RED_BEACH,
                    DuskBiomes.SNOWY_RED_BEACH
                ),
                sequence(
                    condition(
                        ON_CEILING, block(Blocks.RED_SANDSTONE)
                    ),
                    block(Blocks.RED_SAND)
                )
            )
        )
        val deepWindsweptBirchSurface = condition(
            biome(DuskBiomes.WINDSWEPT_BIRCH_FOREST),
            condition(surfaceNoiseThreshold(1.75), block(Blocks.STONE))
        )
        val deepWindsweptGravelSurface = condition(
            biome(DuskBiomes.SNOWY_WINDSWEPT_GRAVELLY_HILLS),
            sequence(
                condition(
                    surfaceNoiseThreshold(2.0),
                    sequence(
                        condition(
                            ON_CEILING, block(Blocks.STONE)
                        ),
                        block(Blocks.GRAVEL)
                    )
                ),
                condition(surfaceNoiseThreshold(1.0), block(Blocks.STONE)),
                condition(
                    surfaceNoiseThreshold(-1.0),
                    block(Blocks.DIRT)
                ),
                condition(
                    ON_CEILING, block(Blocks.STONE)
                ),
                block(Blocks.GRAVEL)


            )
        )
        val deepMud = condition(
            biome(
                DuskBiomes.WINDSWEPT_MANGROVE_SWAMP,
                DuskBiomes.FROZEN_MANGROVE_SWAMP,
                DuskBiomes.FROZEN_WINDSWEPT_MANGROVE_SWAMP
            ),
            block(Blocks.MUD)
        )
        val sandstoneDesert = condition(
            biome(DuskBiomes.RED_DESERT),
            sequence(
                condition(
                    DEEPEST_LEVEL_UNDER_FLOOR, block(Blocks.RED_SANDSTONE)
                )
            )
        )
        //Surface rule sequence 5
        val sandOcean = condition(
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
        //Cave Surface
        val frozenCaverns = sequence(
            condition(
                DEEP_UNDER_FLOOR, sequence(
                    condition(
                        UNDER_FLOOR, sequence(
                            condition(
                                water(-6, 0), sequence(
                                    condition(
                                        noiseThreshold(NoiseParametersKeys.PACKED_ICE, 0.0, 0.2),
                                        block(Blocks.PACKED_ICE)
                                    )
                                )
                            ),
                            condition(
                                water(0, 0), sequence(
                                    condition(
                                        noiseThreshold(NoiseParametersKeys.POWDER_SNOW, 0.45, 0.58),
                                        block(Blocks.POWDER_SNOW)
                                    )
                                )
                            )
                        )
                    ),
                    condition(
                        ON_FLOOR, sequence(
                            condition(
                                water(-1, 0), sequence(
                                    condition(
                                        noiseThreshold
                                            (NoiseParametersKeys.ICE, 0.0, 0.025),
                                        block(Blocks.ICE)
                                    )
                                )
                            )
                        )
                    ),
                    condition(
                        water(-1, 0), sequence(
                            block(Blocks.SNOW_BLOCK)
                        )
                    )
                )
            ),
            condition(
                stoneDepth(0, true, 6, VerticalSurfaceType.CEILING), sequence(
                    condition(
                        UNDER_CEILING, sequence(
                            condition(
                                water(6, 0), sequence(
                                    condition(
                                        noiseThreshold(NoiseParametersKeys.PACKED_ICE, 0.0, 0.2),
                                        block(Blocks.PACKED_ICE)
                                    )
                                )
                            ),
                            condition(
                                water(0, 0), sequence(
                                    condition(
                                        noiseThreshold(NoiseParametersKeys.POWDER_SNOW, 0.45, 0.58),
                                        block(Blocks.POWDER_SNOW)
                                    )
                                )
                            )
                        )
                    ),
                    condition(
                        ON_FLOOR, sequence(
                            condition(
                                water(1, 0), sequence(
                                    condition(
                                        noiseThreshold
                                            (NoiseParametersKeys.ICE, 0.0, 0.025),
                                        block(Blocks.ICE)
                                    )
                                )
                            )
                        )
                    ),
                    condition(
                        water(1, 0), sequence(
                            block(Blocks.SNOW_BLOCK)
                        )
                    )
                )
            )
        )


        //Begin the Layout
        //
        //
        val onFloorAndWater = condition(
            ON_FLOOR, condition(
                water(-1, 0), sequence(
                    windsweptHillSurface,
                    sandSurface,
                    windsweptBirchSurface,
                    windsweptGravelSurface,
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
                    deepWindsweptGravelSurface,
                    sandSurface,
                    deepWindsweptBirchSurface,
                    deepWindsweptGravelSurface,
                    deepMud
                )
            )
        )
        val surface = condition(
            abovePreliminarySurface(),
            sequence(
                swampWater,
                onFloorAndWater,
                onFloorInDeepWater,
                deepSand,
                sandstoneDesert,
                sandOcean
            )
        )
        val cave = sequence(
            frozenCaverns
        )
        // Return a surface-only sequence of surface rules
        return sequence(
            condition(
                aboveY(YOffset.fixed(-55), 0), sequence(
                    surface, cave
                )
            )
        )
    }

    fun surfaceNoiseThreshold(min: Double): MaterialCondition {
        return noiseThreshold(NoiseParametersKeys.SURFACE, min / 8.25, Double.MAX_VALUE)
    }

    fun surfaceSecondaryNoiseThreshold(min: Double): MaterialCondition {
        return noiseThreshold(NoiseParametersKeys.SURFACE_SECONDARY, min / 8.25, Double.MAX_VALUE)
    }
}


//        val devilsRoar = condition(
//            biome(
//                DuskBiomes.DEVILS_ROAR
//            ),
//            sequence(
//                condition(surfaceSecondaryNoiseThreshold(-0.95), block(Blocks.BLACKSTONE)),
//                condition(
//                    surfaceNoiseThreshold(0.35),
//                    sequence(
//                        condition(
//                            DEEP_UNDER_FLOOR, sequence(
//                                condition(
//                                    ON_CEILING, block(Blocks.RED_SANDSTONE)
//                                ),
//                                block(Blocks.RED_SAND)
//                            )
//                        ),
//                        condition(
//                            DEEPEST_LEVEL_UNDER_FLOOR, block(Blocks.RED_SANDSTONE)
//                        )
//
//                    )
//                )
//            )
//        )