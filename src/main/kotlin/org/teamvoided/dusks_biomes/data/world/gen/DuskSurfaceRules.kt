package org.teamvoided.dusks_biomes.data.world.gen

import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.util.math.VerticalSurfaceType
import net.minecraft.world.biome.Biomes
import net.minecraft.world.gen.YOffset
import net.minecraft.world.gen.noise.NoiseParametersKeys
import net.minecraft.world.gen.surfacebuilder.SurfaceRules.*
import org.teamvoided.dusks_biomes.init.DuskBiomes

@Suppress("MagicNumber")
object DuskSurfaceRules {

    private fun block(block: Block): MaterialRule {
        return block(block.defaultState)
    }

    val grass = sequence(
        condition(
            water(-1, 0),
            sequence(
                condition(
                    ON_FLOOR, block(Blocks.GRASS_BLOCK)
                ),
                block(Blocks.DIRT)
            )
        )
    )
    val podzol = sequence(
        condition(
            water(-1, 0),
            sequence(
                condition(
                    ON_FLOOR, block(Blocks.PODZOL)
                ),
                block(Blocks.DIRT)
            )
        )
    )
    val mycelium = sequence(
        condition(
            water(-1, 0),
            sequence(
                condition(
                    ON_FLOOR, block(Blocks.MYCELIUM)
                ),
                block(Blocks.DIRT)
            )
        )
    )
    val gravel = sequence(
        condition(
            ON_CEILING, block(Blocks.STONE)
        ),
        block(Blocks.GRAVEL)
    )
    val sand = sequence(
        condition(
            ON_CEILING, block(Blocks.SANDSTONE)
        ),
        block(Blocks.SAND)
    )
    val sandRed = sequence(
        condition(
            ON_CEILING, block(Blocks.RED_SANDSTONE)
        ),
        block(Blocks.RED_SAND)
    )


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
                            condition(
                                swampThreshold(0.0),
                                block(Blocks.WATER)
                            )
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
                sand
            ),
            condition(
                biome(
                    DuskBiomes.RED_WARM_RIVER,
                    DuskBiomes.RED_DESERT,
                    DuskBiomes.RED_WARM_OCEAN,
                    DuskBiomes.RED_BEACH,
                    DuskBiomes.SNOWY_RED_BEACH
                ),
                sandRed
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
                    gravel
                ),
                condition(
                    surfaceNoiseThreshold(1.0),
                    block(Blocks.STONE)
                ),
                condition(
                    surfaceNoiseThreshold(-1.0),
                    grass
                ),
                gravel
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
        val mushroomIslandSurface = condition(
            biome(
                Biomes.MUSHROOM_FIELDS,
                DuskBiomes.ERODED_MUSHROOM_ISLAND,
                DuskBiomes.MUSHROOM_GROVE
            ), sequence(
                condition(
                    DEEP_UNDER_FLOOR, sequence(
                        condition(
                            surfaceNoiseThreshold(1.0),
                            mycelium
                        )
                    )
                ),
                condition(
                    UNDER_FLOOR, sequence(
                        condition(
                            surfaceSecondaryNoiseThreshold(-0.75, 0.75),
                            block(Blocks.COARSE_DIRT)
                        )
                    )
                ),
                condition(
                    stoneDepth(0, false, 2, VerticalSurfaceType.FLOOR), sequence(
                        condition(
                            surfaceSecondaryNoiseThreshold(-2.0, 2.0),
                            podzol
                        )
                    )
                ),
                condition(
                    UNDER_CEILING, sequence(
                        condition(
                            surfaceNoiseThreshold(0.75),
                            block(Blocks.COARSE_DIRT)
                        )
                    )
                ),
                condition(
                    stoneDepth(0, true, 6, VerticalSurfaceType.CEILING), sequence(
                        condition(
                            surfaceSecondaryNoiseThreshold(1.0),
                            block(Blocks.COARSE_DIRT)
                        )
                    )
                ),
                condition(
                    UNDER_FLOOR, mycelium
                )
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
        val myceliumSurface = condition(
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
                sand
            ),
            condition(
                biome(
                    DuskBiomes.RED_WARM_RIVER,
                    DuskBiomes.RED_WARM_OCEAN,
                    DuskBiomes.RED_BEACH,
                    DuskBiomes.SNOWY_RED_BEACH
                ),
                sandRed
            )
        )
        val deepWindsweptBirchSurface = condition(
            biome(DuskBiomes.WINDSWEPT_BIRCH_FOREST),
            condition(
                surfaceNoiseThreshold(1.75),
                block(Blocks.STONE)
            )
        )
        val deepWindsweptGravelSurface = condition(
            biome(DuskBiomes.SNOWY_WINDSWEPT_GRAVELLY_HILLS),
            sequence(
                condition(
                    surfaceNoiseThreshold(2.0),
                    gravel
                ),
                condition(
                    surfaceNoiseThreshold(1.0),
                    block(Blocks.STONE)
                ),
                condition(
                    surfaceNoiseThreshold(-1.0),
                    block(Blocks.DIRT)
                ),
                gravel
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
                sandRed
            )
        )
//Non-Vanilla adjacent biomes
        val snowyCherryGrove = condition(
            biome(DuskBiomes.SNOWY_CHERRY_GROVE),
            condition(
                stoneDepth(0, true, 0, VerticalSurfaceType.FLOOR), condition(
                    water(0, 0),
                    sequence(
                        condition(
                            stoneDepth(0, true, 0, VerticalSurfaceType.FLOOR),
                            condition(
                                powderSnowNoiseThreshold(0.35, 0.6),
                                block(Blocks.POWDER_SNOW)
                            )
                        ),
                        condition(
                            powderSnowNoiseThreshold(0.45, 0.58),
                            block(Blocks.POWDER_SNOW)
                        ),
                        condition(
                            surfaceNoiseThreshold(-1.0),
                            block(Blocks.SNOW_BLOCK)
                        )
                    )
                )
            )
        )
//Cave Surface
        val mushroomCaves = sequence(
            condition(
                biome(DuskBiomes.MUSHROOM_CAVES),
                sequence(
                    condition(
                        DEEP_UNDER_FLOOR, sequence(
                            condition(
                                surfaceNoiseThreshold(1.0),
                                mycelium
                            )
                        )
                    ),
                    condition(
                        stoneDepth(0, false, 2, VerticalSurfaceType.FLOOR), sequence(
                            condition(
                                surfaceSecondaryNoiseThreshold(-2.0, 2.0),
                                podzol
                            )
                        )
                    ),
                    condition(
                        UNDER_FLOOR, sequence(
                            condition(
                                surfaceSecondaryNoiseThreshold(-0.75, 0.75),
                                block(Blocks.COARSE_DIRT)
                            )
                        )
                    ),
                    condition(
                        UNDER_CEILING, sequence(
                            condition(
                                surfaceNoiseThreshold(0.75),
                                block(Blocks.COARSE_DIRT)
                            )
                        )
                    ),
                    condition(
                        stoneDepth(0, true, 6, VerticalSurfaceType.CEILING), sequence(
                            condition(
                                surfaceSecondaryNoiseThreshold(1.0),
                                block(Blocks.COARSE_DIRT)
                            )
                        )
                    ),
                    condition(
                        stoneDepth(0, false, 2, VerticalSurfaceType.FLOOR), sequence(
                            sequence(
                                mycelium
                            )
                        )
                    )
                )
            )
        )
        val cobbledDeepslateDepth = verticalGradient(
            "minecraft:deepslate",
            YOffset.fixed(0),
            YOffset.fixed(8)
        )
        val fallingBlockCaves = sequence(
            condition(
                biome(DuskBiomes.SAND_CAVES),
                fallingBlockCaveSurface(sand, block(Blocks.SANDSTONE))
            ),
            condition(
                biome(DuskBiomes.RED_SAND_CAVES),
                fallingBlockCaveSurface(sandRed, block(Blocks.RED_SANDSTONE))
            ),
            condition(
                biome(DuskBiomes.GRAVEL_CAVES),
                sequence(
                    condition(
                        cobbledDeepslateDepth,
                        fallingBlockCaveSurface(gravel, block(Blocks.COBBLED_DEEPSLATE))
                    ),
                    condition(
                        not(cobbledDeepslateDepth),
                        fallingBlockCaveSurface(gravel, block(Blocks.COBBLESTONE))
                    )
                )
            )
        )
        val frozenCaverns = condition(
            biome(DuskBiomes.FROZEN_CAVERNS),
            sequence(
                condition(
                    DEEP_UNDER_FLOOR, sequence(
                        sequence(
                            condition(
                                water(-6, 0), sequence(
                                    condition(
                                        packedIceNoiseThreshold(0.0, 0.2),
                                        block(Blocks.PACKED_ICE)
                                    )
                                )
                            ),
                            condition(
                                water(0, 0), sequence(
                                    condition(
                                        powderSnowNoiseThreshold(0.45, 0.58),
                                        block(Blocks.POWDER_SNOW)
                                    )
                                )
                            )
                        ),
                        condition(
                            ON_FLOOR,
                            condition(
                                water(-1, 0), sequence(
                                    condition(
                                        iceNoiseThreshold(0.0, 0.025),
                                        block(Blocks.ICE)
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
                            packedIceNoiseThreshold(0.0, 0.2),
                            block(Blocks.PACKED_ICE)
                        ),
                        condition(
                            water(0, 0), sequence(
                                condition(
                                    powderSnowNoiseThreshold(0.45, 0.58),
                                    block(Blocks.POWDER_SNOW)
                                )
                            )
                        ),
                        condition(
                            ON_FLOOR,
                            condition(
                                water(1, 0), sequence(
                                    condition(
                                        iceNoiseThreshold(0.0, 0.025),
                                        block(Blocks.ICE)
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
                    mud,
                    myceliumSurface
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
                    mushroomIslandSurface,
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
                snowyCherryGrove,
                deepSand,
                sandstoneDesert,
                sandOcean
            )
        )
        val cave = sequence(
            mushroomCaves,
            fallingBlockCaves,
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

    fun fallingBlockCaveSurface(fallingBlock: MaterialRule, solidBlock: MaterialRule): MaterialRule {
        return sequence(
            condition(
                DEEP_UNDER_FLOOR, sequence(
                    condition(
                        surfaceNoiseThreshold(1.0),
                        solidBlock
                    )
                )
            ),
            condition(
                ON_FLOOR, sequence(
                    condition(
                        surfaceSecondaryNoiseThreshold(0.0),
                        solidBlock
                    )
                )
            ),
            condition(
                UNDER_FLOOR, sequence(
                    condition(
                        surfaceNoiseThreshold(-0.5),
                        fallingBlock
                    )
                )
            ),
            condition(
                UNDER_CEILING, sequence(
                    condition(
                        surfaceNoiseThreshold(0.0),
                        solidBlock
                    )
                )
            ),
            condition(
                stoneDepth(0, true, 6, VerticalSurfaceType.CEILING), sequence(
                    condition(
                        surfaceSecondaryNoiseThreshold(0.5),
                        solidBlock
                    )
                )
            ),
            condition(
                stoneDepth(0, false, 2, VerticalSurfaceType.FLOOR), sequence(
                    sequence(
                        fallingBlock
                    )
                )
            )
        )
    }

    fun surfaceNoiseThreshold(min: Double): MaterialCondition {
        return noiseThreshold(NoiseParametersKeys.SURFACE, min / 8.25, Double.MAX_VALUE)
    }

    fun surfaceNoiseThreshold(min: Double, max: Double): MaterialCondition {
        return noiseThreshold(NoiseParametersKeys.SURFACE, min / 8.25, max / 8.25)
    }

    fun surfaceSecondaryNoiseThreshold(min: Double): MaterialCondition {
        return noiseThreshold(NoiseParametersKeys.SURFACE_SECONDARY, min / 8.25, Double.MAX_VALUE)
    }

    fun surfaceSecondaryNoiseThreshold(min: Double, max: Double): MaterialCondition {
        return noiseThreshold(NoiseParametersKeys.SURFACE_SECONDARY, min / 8.25, max / 8.25)
    }

    fun swampThreshold(min: Double): MaterialCondition {
        return noiseThreshold(NoiseParametersKeys.SURFACE_SWAMP, min, Double.MAX_VALUE)
    }

    fun packedIceNoiseThreshold(min: Double, max: Double): MaterialCondition {
        return noiseThreshold(NoiseParametersKeys.PACKED_ICE, min, max)
    }

    fun iceNoiseThreshold(min: Double, max: Double): MaterialCondition {
        return noiseThreshold(NoiseParametersKeys.ICE, min, max)
    }

    fun powderSnowNoiseThreshold(min: Double, max: Double): MaterialCondition {
        return noiseThreshold(NoiseParametersKeys.POWDER_SNOW, min, max)
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
