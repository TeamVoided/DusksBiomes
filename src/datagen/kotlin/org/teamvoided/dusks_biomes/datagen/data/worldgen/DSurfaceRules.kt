package org.teamvoided.dusks_biomes.datagen.data.worldgen

import net.minecraft.world.level.biome.Biomes
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.Noises
import net.minecraft.world.level.levelgen.SurfaceRules.*
import net.minecraft.world.level.levelgen.VerticalAnchor
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
    val POWDER_SNOW = block(Blocks.POWDER_SNOW)
    val SNOW_BLOCK = block(Blocks.SNOW_BLOCK)
    val STONE = block(Blocks.STONE)

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
                isSnowyOldGrowth,
                sequence(
                    ifTrue(surfaceNoiseAbove(1.75), COARSE_DIRT),
                    ifTrue(surfaceNoiseAbove(-0.95), PODZOL)
                )
            ),
            mangroveMud
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
                                    ifTrue(
                                        noiseCondition(Noises.SWAMP, 0.0),
                                        WATER
                                    )
                                )
                            )
                        )
                    )
                ),
                ifTrue(
                    ON_FLOOR,
                    ifTrue(
                        waterDepth1Check,
                        floorWaterDepth1Rule
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

        val caveRules = sequence(
            ifTrue(
                isBiome(DuskBiomes.FROZEN_ERODED_BADLANDS),
                MUD
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
    fun surfaceNoiseAbove(d: Double): ConditionSource = noiseCondition(Noises.SURFACE, d / 8.25, Double.MAX_VALUE)
    // endregion
}