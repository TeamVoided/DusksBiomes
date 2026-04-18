package org.teamvoided.dusks_biomes.data.world.gen

import net.minecraft.world.level.biome.Biomes
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.Noises
import net.minecraft.world.level.levelgen.SurfaceRules.*
import net.minecraft.world.level.levelgen.VerticalAnchor
import net.minecraft.world.level.levelgen.placement.CaveSurface
import org.teamvoided.dusks_biomes.init.DuskBiomes

object DuskSurfaceRules {

    fun block(block: Block): RuleSource = state(block.defaultBlockState())

    val grass: RuleSource = sequence(
        ifTrue(
            waterBlockCheck(-1, 0),
            sequence(
                ifTrue(
                    ON_FLOOR, block(Blocks.GRASS_BLOCK)
                ),
                block(Blocks.DIRT)
            )
        )
    )
    val podzol: RuleSource = sequence(
        ifTrue(
            waterBlockCheck(-1, 0),
            sequence(
                ifTrue(
                    ON_FLOOR, block(Blocks.PODZOL)
                ),
                block(Blocks.DIRT)
            )
        )
    )
    val mycelium: RuleSource = sequence(
        ifTrue(
            waterBlockCheck(-1, 0),
            sequence(
                ifTrue(
                    ON_FLOOR, block(Blocks.MYCELIUM)
                ),
                block(Blocks.DIRT)
            )
        )
    )
    val gravel: RuleSource = sequence(
        ifTrue(
            ON_CEILING, block(Blocks.STONE)
        ),
        block(Blocks.GRAVEL)
    )
    val sand: RuleSource = sequence(
        ifTrue(ON_CEILING, block(Blocks.SANDSTONE)),
        block(Blocks.SAND)
    )
    val redSand: RuleSource = sequence(
        ifTrue(ON_CEILING, block(Blocks.RED_SANDSTONE)),
        block(Blocks.RED_SAND)
    )


    fun overworld(): RuleSource {
        //Sorted like the vanilla surface rule locations https://minecraft.wiki/w/World_generation#Surface
        //Surface rule sequence 1: Floor
        val woodedBadlands = ifTrue(
            isBiome(
                DuskBiomes.FROZEN_WOODED_BADLANDS
            ), ifTrue(
                ON_FLOOR,
                ifTrue(
                    yBlockCheck(VerticalAnchor.absolute(97), 0),
                    sequence(
                        ifTrue(
                            surfaceNoiseThresholdNoDivision(-0.909, -0.5454),
                            block(Blocks.COARSE_DIRT)
                        ),
                        ifTrue(
                            surfaceNoiseThresholdNoDivision(-0.1818, 0.1818),
                            block(Blocks.COARSE_DIRT)
                        ),
                        ifTrue(
                            surfaceNoiseThresholdNoDivision(0.5454, 0.909),
                            block(Blocks.COARSE_DIRT)
                        ),
                        grass
                    )
                )
            )
        )
        val swampWater = ifTrue(
            isBiome(
                DuskBiomes.FROZEN_MANGROVE_SWAMP
            ), ifTrue(
                ON_FLOOR, sequence(
                    ifTrue(
                        yBlockCheck(VerticalAnchor.absolute(60), 0),
                        ifTrue(
                            not(yBlockCheck(VerticalAnchor.absolute(63), 0)),
                            ifTrue(
                                swampThreshold(0.0),
                                block(Blocks.WATER)
                            )
                        )
                    )
                )
            )
        )

        //Surface rule sequence 4: floor without water
        val windsweptHillSurface = ifTrue(
            isBiome(DuskBiomes.SNOWY_WINDSWEPT_HILLS),
            sequence(
                ifTrue(surfaceNoiseThreshold(1.0), block(Blocks.STONE))
            )
        )
        val sandSurface = sequence(
            ifTrue(
                isBiome(
                    DuskBiomes.WARM_RIVER
                ),
                sand
            ),
            ifTrue(
                isBiome(
                    DuskBiomes.RED_WARM_RIVER,
                    DuskBiomes.RED_DESERT,
                    DuskBiomes.RED_WARM_OCEAN,
                    DuskBiomes.RED_BEACH,
                    DuskBiomes.SNOWY_RED_BEACH
                ),
                redSand
            )
        )
        val windsweptBirchSurface = ifTrue(
            isBiome(DuskBiomes.WINDSWEPT_BIRCH_FOREST),
            sequence(
                ifTrue(surfaceNoiseThreshold(1.75), block(Blocks.STONE)),
                ifTrue(surfaceNoiseThreshold(-0.5), block(Blocks.COARSE_DIRT))
            )
        )
        val windsweptGravelSurface = ifTrue(
            isBiome(DuskBiomes.SNOWY_WINDSWEPT_GRAVELLY_HILLS),
            sequence(
                ifTrue(
                    surfaceNoiseThreshold(2.0),
                    gravel
                ),
                ifTrue(
                    surfaceNoiseThreshold(1.0),
                    block(Blocks.STONE)
                ),
                ifTrue(
                    surfaceNoiseThreshold(-1.0),
                    grass
                ),
                gravel
            )
        )
        val podzolAndCoarseDirt = ifTrue(
            isBiome(
                DuskBiomes.SNOWY_OLD_GROWTH_PINE_TAIGA,
                DuskBiomes.SNOWY_OLD_GROWTH_SPRUCE_TAIGA
            ),
            sequence(
                ifTrue(surfaceNoiseThreshold(1.75), block(Blocks.COARSE_DIRT)),
                ifTrue(surfaceNoiseThreshold(-0.95), block(Blocks.PODZOL))
            )
        )
        val mushroomIslandSurface = ifTrue(
            isBiome(
                Biomes.MUSHROOM_FIELDS,
                DuskBiomes.ERODED_MUSHROOM_ISLAND,
                DuskBiomes.MUSHROOM_GROVE
            ), sequence(
                ifTrue(
                    DEEP_UNDER_FLOOR, sequence(
                        ifTrue(
                            surfaceNoiseThreshold(1.0),
                            mycelium
                        )
                    )
                ),
                ifTrue(
                    UNDER_FLOOR, sequence(
                        ifTrue(
                            surfaceSecondaryNoiseThreshold(-0.75, 0.75),
                            block(Blocks.COARSE_DIRT)
                        )
                    )
                ),
                ifTrue(
                    stoneDepthCheck(0, false, 2, CaveSurface.FLOOR), sequence(
                        ifTrue(
                            surfaceSecondaryNoiseThreshold(-2.0, 2.0),
                            podzol
                        )
                    )
                ),
                ifTrue(
                    UNDER_CEILING, sequence(
                        ifTrue(
                            surfaceNoiseThreshold(0.75),
                            block(Blocks.COARSE_DIRT)
                        )
                    )
                ),
                ifTrue(
                    stoneDepthCheck(0, true, 6, CaveSurface.CEILING), sequence(
                        ifTrue(
                            surfaceSecondaryNoiseThreshold(1.0),
                            block(Blocks.COARSE_DIRT)
                        )
                    )
                ),
                ifTrue(
                    UNDER_FLOOR, mycelium
                )
            )
        )
        val mud = ifTrue(
            isBiome(
                DuskBiomes.FROZEN_MANGROVE_SWAMP
            ),
            block(Blocks.MUD)
        )

        //Deep under floor with water above (or not)
        val deepWindsweptHillSurface = ifTrue(
            isBiome(DuskBiomes.SNOWY_WINDSWEPT_HILLS),
            sequence(
                ifTrue(surfaceNoiseThreshold(1.0), block(Blocks.STONE))
            )
        )
        val deepSand = sequence(
            ifTrue(
                isBiome(
                    DuskBiomes.WARM_RIVER
                ),
                sand
            ),
            ifTrue(
                isBiome(
                    DuskBiomes.RED_WARM_RIVER,
                    DuskBiomes.RED_WARM_OCEAN,
                    DuskBiomes.RED_BEACH,
                    DuskBiomes.SNOWY_RED_BEACH
                ),
                redSand
            )
        )
        val deepWindsweptBirchSurface = ifTrue(
            isBiome(DuskBiomes.WINDSWEPT_BIRCH_FOREST),
            ifTrue(
                surfaceNoiseThreshold(1.75),
                block(Blocks.STONE)
            )
        )
        val deepWindsweptGravelSurface = ifTrue(
            isBiome(DuskBiomes.SNOWY_WINDSWEPT_GRAVELLY_HILLS),
            sequence(
                ifTrue(
                    surfaceNoiseThreshold(2.0),
                    gravel
                ),
                ifTrue(
                    surfaceNoiseThreshold(1.0),
                    block(Blocks.STONE)
                ),
                ifTrue(
                    surfaceNoiseThreshold(-1.0),
                    block(Blocks.DIRT)
                ),
                gravel
            )
        )
        val deepMud = ifTrue(
            isBiome(
                DuskBiomes.FROZEN_MANGROVE_SWAMP
            ),
            block(Blocks.MUD)
        )
        val sandstoneDesert = ifTrue(
            isBiome(DuskBiomes.RED_DESERT),
            sequence(
                ifTrue(
                    VERY_DEEP_UNDER_FLOOR, block(Blocks.RED_SANDSTONE)
                )
            )
        )
        //Surface rule sequence 5
        val sandOcean = ifTrue(
            ON_FLOOR, ifTrue(
                isBiome(
                    DuskBiomes.RED_WARM_RIVER,
                    DuskBiomes.RED_WARM_OCEAN,
                    DuskBiomes.RED_LUKEWARM_OCEAN,
                    DuskBiomes.DEEP_RED_LUKEWARM_OCEAN
                ),
                redSand
            )
        )
//Non-Vanilla adjacent biomes
        val snowyCherryGrove = ifTrue(
            isBiome(
                DuskBiomes.SNOWY_CHERRY_GROVE,
                DuskBiomes.DARK_GROVE,
                DuskBiomes.PALE_GROVE
            ),
            ifTrue(
                stoneDepthCheck(0, true, 0, CaveSurface.FLOOR), ifTrue(
                    waterBlockCheck(-6, 0),
                    sequence(
                        ifTrue(
                            stoneDepthCheck(0, false, 0, CaveSurface.FLOOR),
                            ifTrue(
                                powderSnowNoiseThreshold(0.35, 0.6),
                                block(Blocks.POWDER_SNOW)
                            )
                        ),
                        ifTrue(
                            powderSnowNoiseThreshold(0.45, 0.58),
                            block(Blocks.POWDER_SNOW)
                        ),
                        ifTrue(
                            surfaceNoiseThreshold(-1.0),
                            block(Blocks.SNOW_BLOCK)
                        )
                    )
                )
            )
        )
        val stonyShore = ifTrue(
            isBiome(
                DuskBiomes.SNOWY_STONY_SHORE
            ),
            ifTrue(
                stoneDepthCheck(0, true, 0, CaveSurface.FLOOR), ifTrue(
                    waterBlockCheck(-6, 0),
                    sequence(
                        ifTrue(
                            gravelNoiseThreshold(-0.05, 0.05),
                            gravel
                        ),
                        block(Blocks.STONE)
                    )
                )
            )
        )
        val badlands = ifTrue(
            isBiome(
                DuskBiomes.FROZEN_BADLANDS,
                DuskBiomes.FROZEN_WOODED_BADLANDS,
                DuskBiomes.FROZEN_ERODED_BADLANDS
            ),
            sequence(
                ifTrue(
                    UNDER_FLOOR,
                    ifTrue(
                        powderSnowNoiseThreshold(0.45, 0.58),
                        block(Blocks.POWDER_SNOW)

                    )
                ),
                ifTrue(
                    ON_FLOOR,
                    sequence(
                        ifTrue(
                            waterBlockCheck(-6, 0),
                            sequence(
                                ifTrue(
                                    powderSnowNoiseThreshold(0.35, 0.6),
                                    block(Blocks.POWDER_SNOW)
                                ),
                                ifTrue(
                                    surfaceNoiseThreshold(0.0),
                                    block(Blocks.SNOW_BLOCK)
                                )
                            )
                        ),
                        ifTrue(
                            yBlockCheck(VerticalAnchor.absolute(256), 0),
                            block(Blocks.ORANGE_TERRACOTTA)
                        ),
                        ifTrue(
                            yStartCheck(VerticalAnchor.absolute(74), 0),
                            sequence(
                                ifTrue(
                                    surfaceNoiseThresholdNoDivision(-0.909, -0.5454),
                                    block(Blocks.TERRACOTTA)
                                ),
                                ifTrue(
                                    surfaceNoiseThresholdNoDivision(-0.1818, 0.1818),
                                    block(Blocks.TERRACOTTA)
                                ),
                                ifTrue(
                                    surfaceNoiseThresholdNoDivision(0.5454, 0.909),
                                    block(Blocks.TERRACOTTA)
                                ),
                                bandlands()
                            )
                        ),
                        ifTrue(
                            waterBlockCheck(-1, 0),
                            redSand
                        ),
                        ifTrue(
                            not(hole()),
                            block(Blocks.ORANGE_TERRACOTTA)
                        ),
                        ifTrue(
                            waterStartCheck(-6, -1),
                            block(Blocks.WHITE_TERRACOTTA)
                        ),
                        gravel
                    )
                ),
                ifTrue(
                    yStartCheck(VerticalAnchor.absolute(63), -1),
                    sequence(
                        ifTrue(
                            yBlockCheck(VerticalAnchor.absolute(63), 0),
                            ifTrue(
                                not(
                                    yStartCheck(VerticalAnchor.absolute(74), 1)
                                ),
                                block(Blocks.ORANGE_TERRACOTTA)
                            )
                        ),
                        bandlands()
                    )
                ),
                ifTrue(
                    UNDER_FLOOR,
                    ifTrue(
                        waterStartCheck(-6, -1),
                        block(Blocks.WHITE_TERRACOTTA)
                    )
                )
            )
        )
//Cave Surface
        val mushroomCaves = sequence(
            ifTrue(
                isBiome(DuskBiomes.MUSHROOM_CAVES),
                sequence(
                    ifTrue(
                        DEEP_UNDER_FLOOR, sequence(
                            ifTrue(
                                surfaceNoiseThreshold(1.0),
                                mycelium
                            )
                        )
                    ),
                    ifTrue(
                        stoneDepthCheck(0, false, 2, CaveSurface.FLOOR), sequence(
                            ifTrue(
                                surfaceSecondaryNoiseThreshold(-2.0, 2.0),
                                podzol
                            )
                        )
                    ),
                    ifTrue(
                        UNDER_FLOOR, sequence(
                            ifTrue(
                                surfaceSecondaryNoiseThreshold(-0.75, 0.75),
                                block(Blocks.COARSE_DIRT)
                            )
                        )
                    ),
                    ifTrue(
                        UNDER_CEILING, sequence(
                            ifTrue(
                                surfaceNoiseThreshold(0.75),
                                block(Blocks.COARSE_DIRT)
                            )
                        )
                    ),
                    ifTrue(
                        stoneDepthCheck(0, true, 6, CaveSurface.CEILING), sequence(
                            ifTrue(
                                surfaceSecondaryNoiseThreshold(1.0),
                                block(Blocks.COARSE_DIRT)
                            )
                        )
                    ),
                    ifTrue(
                        stoneDepthCheck(0, false, 2, CaveSurface.FLOOR), sequence(
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
            VerticalAnchor.absolute(0),
            VerticalAnchor.absolute(8)
        )
        val fallingBlockCaves = sequence(
            ifTrue(
                isBiome(DuskBiomes.SAND_CAVES),
                fallingBlockCaveSurface(sand, block(Blocks.SANDSTONE))
            ),
            ifTrue(
                isBiome(DuskBiomes.RED_SAND_CAVES),
                fallingBlockCaveSurface(redSand, block(Blocks.RED_SANDSTONE))
            ),
            ifTrue(
                isBiome(DuskBiomes.GRAVEL_CAVES),
                sequence(
                    ifTrue(
                        cobbledDeepslateDepth,
                        fallingBlockCaveSurface(gravel, block(Blocks.COBBLED_DEEPSLATE))
                    ),
                    ifTrue(
                        not(cobbledDeepslateDepth),
                        fallingBlockCaveSurface(gravel, block(Blocks.COBBLESTONE))
                    )
                )
            )
        )
        val frozenCaverns = ifTrue(
            isBiome(DuskBiomes.FROZEN_CAVERNS),
            sequence(
                ifTrue(
                    DEEP_UNDER_FLOOR, sequence(
                        sequence(
                            ifTrue(
                                waterBlockCheck(-6, 0), sequence(
                                    ifTrue(
                                        packedIceNoiseThreshold(0.0, 0.2),
                                        block(Blocks.PACKED_ICE)
                                    )
                                )
                            ),
                            ifTrue(
                                waterBlockCheck(0, 0), sequence(
                                    ifTrue(
                                        powderSnowNoiseThreshold(0.45, 0.58),
                                        block(Blocks.POWDER_SNOW)
                                    )
                                )
                            )
                        ),
                        ifTrue(
                            ON_FLOOR,
                            ifTrue(
                                waterBlockCheck(-1, 0), sequence(
                                    ifTrue(
                                        iceNoiseThreshold(0.0, 0.025),
                                        block(Blocks.ICE)
                                    )
                                )
                            )
                        ),
                        ifTrue(
                            waterBlockCheck(-1, 0), sequence(
                                block(Blocks.SNOW_BLOCK)
                            )
                        )
                    )
                ),
                ifTrue(
                    stoneDepthCheck(0, true, 6, CaveSurface.CEILING), sequence(
                        ifTrue(
                            packedIceNoiseThreshold(0.0, 0.2),
                            block(Blocks.PACKED_ICE)
                        ),
                        ifTrue(
                            waterBlockCheck(0, 0), sequence(
                                ifTrue(
                                    powderSnowNoiseThreshold(0.45, 0.58),
                                    block(Blocks.POWDER_SNOW)
                                )
                            )
                        ),
                        ifTrue(
                            ON_FLOOR,
                            ifTrue(
                                waterBlockCheck(1, 0), sequence(
                                    ifTrue(
                                        iceNoiseThreshold(0.0, 0.025),
                                        block(Blocks.ICE)
                                    )
                                )
                            )
                        ),
                        ifTrue(
                            waterBlockCheck(1, 0), sequence(
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
        val onFloorAndWater = ifTrue(
            ON_FLOOR, ifTrue(
                waterBlockCheck(-1, 0), sequence(
                    windsweptHillSurface,
                    sandSurface,
                    windsweptBirchSurface,
                    windsweptGravelSurface,
                    podzolAndCoarseDirt,
                    mud
                )
            )
        )
        val onFloorInDeepWater = ifTrue(
            DEEP_UNDER_FLOOR, ifTrue(
                waterBlockCheck(-6, 0), sequence(
                    deepWindsweptHillSurface,
                    deepSand,
                    deepWindsweptBirchSurface,
                    deepWindsweptGravelSurface,
                    mushroomIslandSurface,
                    deepMud
                )
            )
        )
        val surface = ifTrue(
            abovePreliminarySurface(),
            sequence(
                swampWater,
                woodedBadlands,
                badlands,
                onFloorAndWater,
                onFloorInDeepWater,
                snowyCherryGrove,
                stonyShore,
                sandstoneDesert,
                sandOcean
            )
        )
        // Return a surface-only sequence of surface rules
        return sequence(
            ifTrue(
                yBlockCheck(VerticalAnchor.absolute(-55), 0),
                sequence(
                    surface,
                    sequence(
                        mushroomCaves,
                        fallingBlockCaves,
                        frozenCaverns
                    )
                )
            )
        )
    }

    fun fallingBlockCaveSurface(fallingBlock: RuleSource, solidBlock: RuleSource): RuleSource {
        return sequence(
            ifTrue(
                DEEP_UNDER_FLOOR, sequence(
                    ifTrue(
                        surfaceNoiseThreshold(1.0),
                        solidBlock
                    )
                )
            ),
            ifTrue(
                ON_FLOOR, sequence(
                    ifTrue(
                        surfaceSecondaryNoiseThreshold(0.0),
                        solidBlock
                    )
                )
            ),
            ifTrue(
                UNDER_FLOOR, sequence(
                    ifTrue(
                        surfaceNoiseThreshold(-0.5),
                        fallingBlock
                    )
                )
            ),
            ifTrue(
                UNDER_CEILING, sequence(
                    ifTrue(
                        surfaceNoiseThreshold(0.0),
                        solidBlock
                    )
                )
            ),
            ifTrue(
                stoneDepthCheck(0, true, 6, CaveSurface.CEILING), sequence(
                    ifTrue(
                        surfaceSecondaryNoiseThreshold(0.5),
                        solidBlock
                    )
                )
            ),
            ifTrue(
                stoneDepthCheck(0, false, 2, CaveSurface.FLOOR), sequence(
                    sequence(
                        fallingBlock
                    )
                )
            )
        )
    }

    fun surfaceNoiseThreshold(min: Double): ConditionSource {
        return noiseCondition(Noises.SURFACE, min / 8.25, Double.MAX_VALUE)
    }

    fun surfaceNoiseThreshold(min: Double, max: Double): ConditionSource {
        return noiseCondition(Noises.SURFACE, min / 8.25, max / 8.25)
    }

    fun surfaceNoiseThresholdNoDivision(min: Double, max: Double): ConditionSource {
        return noiseCondition(Noises.SURFACE, min, max)
    }

    fun surfaceSecondaryNoiseThreshold(min: Double): ConditionSource {
        return noiseCondition(Noises.SURFACE_SECONDARY, min / 8.25, Double.MAX_VALUE)
    }

    fun surfaceSecondaryNoiseThreshold(min: Double, max: Double): ConditionSource {
        return noiseCondition(Noises.SURFACE_SECONDARY, min / 8.25, max / 8.25)
    }

    fun swampThreshold(min: Double): ConditionSource {
        return noiseCondition(Noises.SWAMP, min, Double.MAX_VALUE)
    }

    fun packedIceNoiseThreshold(min: Double, max: Double): ConditionSource {
        return noiseCondition(Noises.PACKED_ICE, min, max)
    }

    fun iceNoiseThreshold(min: Double, max: Double): ConditionSource {
        return noiseCondition(Noises.ICE, min, max)
    }

    fun powderSnowNoiseThreshold(min: Double, max: Double): ConditionSource {
        return noiseCondition(Noises.POWDER_SNOW, min, max)
    }

    fun gravelNoiseThreshold(min: Double, max: Double): ConditionSource {
        return noiseCondition(Noises.GRAVEL, min, max)
    }
}


//        val devilsRoar = ifTrue(
//            isBiome(
//                DuskBiomes.DEVILS_ROAR
//            ),
//            sequence(
//                ifTrue(surfaceSecondaryNoiseThreshold(-0.95), block(Blocks.BLACKSTONE)),
//                ifTrue(
//                    surfaceNoiseThreshold(0.35),
//                    sequence(
//                        ifTrue(
//                            DEEP_UNDER_FLOOR, sequence(
//                                ifTrue(
//                                    ON_CEILING, block(Blocks.RED_SANDSTONE)
//                                ),
//                                block(Blocks.RED_SAND)
//                            )
//                        ),
//                        ifTrue(
//                            DEEPEST_LEVEL_UNDER_FLOOR, block(Blocks.RED_SANDSTONE)
//                        )
//
//                    )
//                )
//            )
//        )
