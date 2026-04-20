package org.teamvoided.dusks_biomes.datagen.data.worldgen

import net.minecraft.world.level.biome.Biomes
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.Noises
import net.minecraft.world.level.levelgen.SurfaceRules.*
import net.minecraft.world.level.levelgen.VerticalAnchor
import net.minecraft.world.level.levelgen.placement.CaveSurface
import org.teamvoided.dusks_biomes.data.world.gen.DuskSurfaceRules.iceNoiseThreshold
import org.teamvoided.dusks_biomes.data.world.gen.DuskSurfaceRules.packedIceNoiseThreshold
import org.teamvoided.dusks_biomes.data.world.gen.DuskSurfaceRules.powderSnowNoiseThreshold
import org.teamvoided.dusks_biomes.init.DuskBiomes

object DSurfaceRules {

    val SAND = sequence(
        ifTrue(ON_CEILING, block(Blocks.SANDSTONE)),
        block(Blocks.SAND)
    )
    val RED_SAND = sequence(
        ifTrue(ON_CEILING, block(Blocks.RED_SANDSTONE)),
        block(Blocks.RED_SAND)
    )
    val GRAVEL = sequence(
        ifTrue(ON_CEILING, block(Blocks.STONE)),
        block(Blocks.GRAVEL)
    )
    val RED_SANDSTONE = block(Blocks.RED_SANDSTONE)
    val SANDSTONE = block(Blocks.SANDSTONE)
    val MUD = block(Blocks.MUD)
    val WATER = block(Blocks.WATER)
    val DIRT = block(Blocks.DIRT)
    val COARSE_DIRT = block(Blocks.COARSE_DIRT)
    val PODZOL = block(Blocks.PODZOL)
    val PODZOL_RULE: RuleSource = ifTrue(
        waterBlockCheck(-1, 0),
        sequence(ifTrue(ON_FLOOR, PODZOL), DIRT)
    )
    val MYCELIUM = block(Blocks.MYCELIUM)
    val MYCELIUM_RULE: RuleSource = ifTrue(
        waterBlockCheck(-1, 0),
        sequence(ifTrue(ON_FLOOR, MYCELIUM), DIRT)
    )
    val POWDER_SNOW = block(Blocks.POWDER_SNOW)
    val SNOW_BLOCK = block(Blocks.SNOW_BLOCK)
    val STONE = block(Blocks.STONE)
    val COBBLESTONE = block(Blocks.COBBLESTONE)
    val COBBLED_DEEPSLATE = block(Blocks.COBBLED_DEEPSLATE)

    fun overworld(): RuleSource {
        val above97 = yBlockCheck(VerticalAnchor.absolute(97), 2)
        val above256 = yBlockCheck(VerticalAnchor.absolute(256), 0)
        val above63Multi1 = yStartCheck(VerticalAnchor.absolute(63), -1)
        val above74 = yStartCheck(VerticalAnchor.absolute(74), 1)
        val above60 = yBlockCheck(VerticalAnchor.absolute(60), 0)
        val above62 = yBlockCheck(VerticalAnchor.absolute(62), 0)
        val above63 = yBlockCheck(VerticalAnchor.absolute(63), 0)
        val waterDepth1Check = waterBlockCheck(-1, 0)
        val waterCheck = waterBlockCheck(0, 0)
        val waterDepth6Check = waterStartCheck(-6, -1)
        val holeCheck = hole()
        val stepCheck = steep()

        val powderedSnowCheck1 = ifTrue(
            noiseCondition(Noises.POWDER_SNOW, 0.45, 0.58),
            ifTrue(waterCheck, POWDER_SNOW)
        )
        val powderedSnowCheck2 = ifTrue(
            noiseCondition(Noises.POWDER_SNOW, 0.35, 0.6),
            ifTrue(waterCheck, POWDER_SNOW)
        )

        // region Biome Checks
        val isSandBiome = isBiome(DuskBiomes.WARM_RIVER)
        val isRedSandBiome = isBiome(
            DuskBiomes.RED_WARM_RIVER,
            DuskBiomes.RED_BEACH,
            DuskBiomes.SNOWY_RED_BEACH,

            DuskBiomes.RED_WARM_OCEAN,
            DuskBiomes.RED_LUKEWARM_OCEAN,
        )
        val isRedDesertBiome = isBiome(DuskBiomes.RED_DESERT)

        val isSnowyOldGrowth = isBiome(
            DuskBiomes.SNOWY_OLD_GROWTH_PINE_TAIGA,
            DuskBiomes.SNOWY_OLD_GROWTH_SPRUCE_TAIGA
        )

        val isGrove = isBiome(
            DuskBiomes.DARK_GROVE,
            DuskBiomes.PALE_GROVE,
        )
        val isGroveLike = isBiome(
            DuskBiomes.SNOWY_CHERRY_GROVE,
        )
        val isMushroom = isBiome(
            Biomes.MUSHROOM_FIELDS,
            DuskBiomes.ERODED_MUSHROOM_ISLAND,
            DuskBiomes.MUSHROOM_GROVE
        )

        val hasSandOceanFloor = isBiome(
            DuskBiomes.WARM_RIVER,
            Biomes.DESERT,
            Biomes.BEACH,
        )
        val hasRedSandOceanFloor = isBiome(
            DuskBiomes.RED_DESERT,
            DuskBiomes.RED_BEACH,
            DuskBiomes.RED_WARM_RIVER,
            DuskBiomes.RED_WARM_OCEAN,
            DuskBiomes.RED_LUKEWARM_OCEAN,
            DuskBiomes.DEEP_RED_LUKEWARM_OCEAN,
        )
        // endregion

        val mangroveMud = ifTrue(
            isBiome(DuskBiomes.FROZEN_MANGROVE_SWAMP),
            MUD
        )

        val rule4 = sequence(
            ifTrue(
                isBiome(DuskBiomes.SNOWY_STONY_SHORE),
                sequence(
                    ifTrue(noiseCondition(Noises.GRAVEL, -0.05, 0.05), GRAVEL),
                    STONE
                )
            ),
            ifTrue(
                isBiome(DuskBiomes.SNOWY_WINDSWEPT_HILLS),
                ifTrue(surfaceNoiseAbove(1.0), STONE)
            ),
            ifTrue(isSandBiome, SAND),
            ifTrue(isRedSandBiome, RED_SAND),
            ifTrue(isRedDesertBiome, RED_SAND),
        )


        // rule7
        val floorWaterDepth6Rule = sequence(
            ifTrue(
                isGrove,
                sequence(
                    powderedSnowCheck1,
                    DIRT
                )
            ),
            ifTrue(isGroveLike, powderedSnowCheck1),
            rule4,
            ifTrue(
                isBiome(DuskBiomes.WINDSWEPT_BIRCH_FOREST),
                ifTrue(surfaceNoiseAbove(1.75), STONE)
            ),
            ifTrue(
                isBiome(DuskBiomes.SNOWY_WINDSWEPT_GRAVELLY_HILLS),
                sequence(
                    ifTrue(surfaceNoiseAbove(2.0), GRAVEL),
                    ifTrue(surfaceNoiseAbove(1.0), STONE),
                    ifTrue(surfaceNoiseAbove(-1.0), DIRT),
                    GRAVEL
                )
            ),
            mangroveMud
        )


        // rule8
        val floorWaterDepth1Rule = sequence(
            ifTrue(
                isGrove,
                sequence(
                    powderedSnowCheck2,
                    ifTrue(waterCheck, SNOW_BLOCK)
                )
            ),
            ifTrue(
                isGroveLike,
                sequence(
                    powderedSnowCheck2,
                    ifTrue(
                        waterCheck,
                        ifTrue(surfaceNoiseAbove(-1.0), SNOW_BLOCK)
                    )
                )
            ),
            rule4,
            ifTrue(
                isBiome(DuskBiomes.WINDSWEPT_BIRCH_FOREST),
                sequence(
                    ifTrue(surfaceNoiseAbove(1.75), STONE),
                    ifTrue(surfaceNoiseAbove(-0.5), COARSE_DIRT)
                )
            ),
            ifTrue(
                isBiome(DuskBiomes.SNOWY_WINDSWEPT_GRAVELLY_HILLS),
                sequence(
                    ifTrue(surfaceNoiseAbove(2.0), GRAVEL),
                    ifTrue(surfaceNoiseAbove(1.0), STONE),
                    ifTrue(surfaceNoiseAbove(-1.0), DIRT),
                    GRAVEL
                )
            ),
            ifTrue(
                isSnowyOldGrowth,
                sequence(
                    ifTrue(surfaceNoiseAbove(1.75), COARSE_DIRT),
                    ifTrue(surfaceNoiseAbove(-0.95), PODZOL)
                )
            ),
            ifTrue(isMushroom, MYCELIUM),
            mangroveMud
        )

        val mushroomRules = ifTrue(
            isMushroom,
            sequence(
                ifTrue(
                    UNDER_FLOOR,
                    ifTrue(surfaceSecondaryNoiseAbove(-0.75, 0.75), COARSE_DIRT),
                ),
                ifTrue(
                    stoneDepthCheck(0, false, 2, CaveSurface.FLOOR),
                    ifTrue(surfaceSecondaryNoiseAbove(-2.0, 2.0), PODZOL_RULE)
                ),
                ifTrue(
                    UNDER_CEILING,
                    ifTrue(surfaceNoiseAbove(0.75), COARSE_DIRT)
                ),
                ifTrue(
                    stoneDepthCheck(0, true, 6, CaveSurface.CEILING),
                    ifTrue(surfaceSecondaryNoiseAbove(1.0), COARSE_DIRT)
                ),
            )
        )

        val prelimSurfaceRules = ifTrue(
            abovePreliminarySurface(),
            sequence(
                ifTrue(
                    ON_FLOOR,
                    sequence(
                        ifTrue(
                            isBiome(DuskBiomes.FROZEN_MANGROVE_SWAMP),
                            ifTrue(
                                above60,
                                ifTrue(
                                    not(above63),
                                    ifTrue(noiseCondition(Noises.SWAMP, 0.0), WATER)
                                )
                            )
                        )
                    )
                ),
                mushroomRules,
                ifTrue(
                    ON_FLOOR,
                    ifTrue(
                        waterDepth1Check,
                        floorWaterDepth1Rule
                    )
                ),
                ifTrue(
                    isMushroom,
                    ifTrue(
                        DEEP_UNDER_FLOOR,
                        ifTrue(surfaceNoiseAbove(1.0), DIRT)
                    )
                ),
                ifTrue(
                    waterDepth6Check,
                    sequence(
                        ifTrue(UNDER_FLOOR, floorWaterDepth6Rule),
                        ifTrue(
                            isSandBiome,
                            ifTrue(DEEP_UNDER_FLOOR, SANDSTONE)
                        ),
                        ifTrue(
                            isRedSandBiome,
                            ifTrue(DEEP_UNDER_FLOOR, RED_SANDSTONE)
                        ),
                        ifTrue(
                            isRedDesertBiome,
                            ifTrue(VERY_DEEP_UNDER_FLOOR, RED_SANDSTONE)
                        )
                    )
                ),
                ifTrue(
                    ON_FLOOR,
                    sequence(
                        ifTrue(hasSandOceanFloor, SAND),
                        ifTrue(hasRedSandOceanFloor, RED_SAND)
                    )
                )
            )
        )
        val cobbledDeepslateDepth =
            verticalGradient("minecraft:deepslate", VerticalAnchor.absolute(0), VerticalAnchor.absolute(8))

        val mushroomCaves = sequence(
            ifTrue(
                isBiome(DuskBiomes.MUSHROOM_CAVES),
                sequence(
                    ifTrue(
                        stoneDepthCheck(0, false, 2, CaveSurface.FLOOR),
                        ifTrue(surfaceSecondaryNoiseAbove(-2.0, 2.0), PODZOL_RULE)
                    ),
                    ifTrue(
                        UNDER_FLOOR,
                        ifTrue(surfaceSecondaryNoiseAbove(-0.75, 0.75), COARSE_DIRT)
                    ),
                    ifTrue(
                        UNDER_CEILING,
                        ifTrue(surfaceNoiseAbove(0.75), COARSE_DIRT)
                    ),
                    ifTrue(stoneDepthCheck(0, false, 2, CaveSurface.FLOOR), MYCELIUM_RULE)
                )
            )
        )

        val frozenCaverns = ifTrue(
            isBiome(DuskBiomes.FROZEN_CAVERNS),
            sequence(
                ifTrue(
                    stoneDepthCheck(0, true, 3, CaveSurface.FLOOR),
                    sequence(
                        ifTrue(
                            waterBlockCheck(-6, 0),
                            ifTrue(packedIceNoiseThreshold(0.0, 0.2), block(Blocks.PACKED_ICE))
                        ),
                        ifTrue(
                            waterBlockCheck(0, 0),
                            ifTrue(powderSnowNoiseThreshold(0.45, 0.58), POWDER_SNOW)
                        ),
                        ifTrue(
                            ON_FLOOR,
                            ifTrue(
                                waterBlockCheck(-1, 0),
                                ifTrue(iceNoiseThreshold(0.0, 0.025), block(Blocks.ICE))
                            )
                        ),
                        ifTrue(waterBlockCheck(-1, 0), SNOW_BLOCK)
                    )
                ),
                ifTrue(
                    stoneDepthCheck(0, true, 3, CaveSurface.CEILING),
                    sequence(
                        ifTrue(
                            packedIceNoiseThreshold(0.0, 0.2),
                            block(Blocks.PACKED_ICE)
                        ),
                        ifTrue(
                            waterBlockCheck(0, 0),
                            ifTrue(powderSnowNoiseThreshold(0.45, 0.58), POWDER_SNOW)
                        ),
                        ifTrue(
                            ON_FLOOR,
                            ifTrue(
                                waterBlockCheck(1, 0),
                                ifTrue(iceNoiseThreshold(0.0, 0.025), block(Blocks.ICE))
                            )
                        ),
                        ifTrue(waterBlockCheck(1, 0), SNOW_BLOCK)
                    )
                )
            )
        )

        val caveRules = sequence(
            mushroomCaves,
            frozenCaverns,
            ifTrue(isBiome(DuskBiomes.SAND_CAVES), fallingBlockCaveSurface(SAND, SANDSTONE)),
            ifTrue(isBiome(DuskBiomes.RED_SAND_CAVES), fallingBlockCaveSurface(RED_SAND, RED_SANDSTONE)),
            ifTrue(
                isBiome(DuskBiomes.GRAVEL_CAVES),
                sequence(
                    ifTrue(
                        cobbledDeepslateDepth,
                        gravelCaves(GRAVEL, COBBLED_DEEPSLATE)
                    ),
                    ifTrue(
                        not(cobbledDeepslateDepth),
                        gravelCaves(GRAVEL, COBBLESTONE)
                    )
                )
            )
        )

        return ifTrue(
            yBlockCheck(VerticalAnchor.aboveBottom(5), 0),
            sequence(
                prelimSurfaceRules,
                caveRules
            )
        )
    }


    // region Helpers
    fun block(block: Block): RuleSource = state(block.defaultBlockState())
    fun surfaceNoiseAbove(x: Double): ConditionSource = noiseCondition(Noises.SURFACE, x / 8.25, Double.MAX_VALUE)
    fun surfaceNoiseAbove(x: Double, z: Double): ConditionSource = noiseCondition(Noises.SURFACE, x / 8.25, z / 8.25)
    fun surfaceSecondaryNoiseAbove(min: Double): ConditionSource =
        noiseCondition(Noises.SURFACE_SECONDARY, min / 8.25, Double.MAX_VALUE)

    fun surfaceSecondaryNoiseAbove(x: Double, z: Double): ConditionSource =
        noiseCondition(Noises.SURFACE_SECONDARY, x / 8.25, z / 8.25)

    fun fallingBlockCaveSurface(fallingBlock: RuleSource, solidBlock: RuleSource): RuleSource {
        return sequence(
            ifTrue(
                ON_FLOOR,
                ifTrue(surfaceSecondaryNoiseAbove(0.0), fallingBlock)
            ),
            ifTrue(
                UNDER_FLOOR,
                sequence(
                    ifTrue(surfaceNoiseAbove(-0.25), fallingBlock),
                    ifTrue(surfaceNoiseAbove(-0.5), solidBlock)
                )
            ),
            ifTrue(
                ON_CEILING,
                ifTrue(surfaceSecondaryNoiseAbove(-0.5), solidBlock)
            ),
            ifTrue(
                UNDER_CEILING,
                ifTrue(surfaceSecondaryNoiseAbove(0.5), solidBlock)
            ),
            ifTrue(ON_FLOOR, fallingBlock),
        )
    }

    fun gravelCaves(fallingBlock: RuleSource, solidBlock: RuleSource): RuleSource {
        return sequence(
            ifTrue(
                ON_FLOOR,
                sequence(
                    ifTrue(surfaceNoiseAbove(0.0), fallingBlock),
                    ifTrue(surfaceSecondaryNoiseAbove(-0.5), solidBlock),
                )
            ),
            ifTrue(
                stoneDepthCheck(0, false, 2, CaveSurface.FLOOR),
                sequence(
                    ifTrue(surfaceNoiseAbove(0.6), fallingBlock),
                    ifTrue(surfaceNoiseAbove(0.25), solidBlock)
                )
            ),
            ifTrue(
                ON_CEILING,
                ifTrue(surfaceSecondaryNoiseAbove(-0.25), solidBlock)
            ),
            ifTrue(ON_FLOOR, fallingBlock),
        )
    }
    // endregion
}