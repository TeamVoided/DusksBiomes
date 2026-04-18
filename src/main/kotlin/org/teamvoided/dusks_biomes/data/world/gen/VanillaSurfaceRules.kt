package org.teamvoided.dusks_biomes.data.world.gen

import com.google.common.collect.ImmutableList
import net.minecraft.world.level.biome.Biomes
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.Noises
import net.minecraft.world.level.levelgen.SurfaceRules.*
import net.minecraft.world.level.levelgen.SurfaceRules.ConditionSource
import net.minecraft.world.level.levelgen.VerticalAnchor

object VanillaSurfaceRules {

    val AIR: RuleSource = makeStateRule(Blocks.AIR)
    val BEDROCK: RuleSource = makeStateRule(Blocks.BEDROCK)
    val WHITE_TERRACOTTA: RuleSource = makeStateRule(Blocks.WHITE_TERRACOTTA)
    val ORANGE_TERRACOTTA: RuleSource = makeStateRule(Blocks.ORANGE_TERRACOTTA)
    val TERRACOTTA: RuleSource = makeStateRule(Blocks.TERRACOTTA)
    val RED_SAND: RuleSource = makeStateRule(Blocks.RED_SAND)
    val RED_SANDSTONE: RuleSource = makeStateRule(Blocks.RED_SANDSTONE)
    val STONE: RuleSource = makeStateRule(Blocks.STONE)
    val DEEPSLATE: RuleSource = makeStateRule(Blocks.DEEPSLATE)
    val DIRT: RuleSource = makeStateRule(Blocks.DIRT)
    val PODZOL: RuleSource = makeStateRule(Blocks.PODZOL)
    val COARSE_DIRT: RuleSource = makeStateRule(Blocks.COARSE_DIRT)
    val MYCELIUM: RuleSource = makeStateRule(Blocks.MYCELIUM)
    val GRASS_BLOCK: RuleSource = makeStateRule(Blocks.GRASS_BLOCK)
    val CALCITE: RuleSource = makeStateRule(Blocks.CALCITE)
    val GRAVEL: RuleSource = makeStateRule(Blocks.GRAVEL)
    val SAND: RuleSource = makeStateRule(Blocks.SAND)
    val SANDSTONE: RuleSource = makeStateRule(Blocks.SANDSTONE)
    val PACKED_ICE: RuleSource = makeStateRule(Blocks.PACKED_ICE)
    val SNOW_BLOCK: RuleSource = makeStateRule(Blocks.SNOW_BLOCK)
    val MUD: RuleSource = makeStateRule(Blocks.MUD)
    val POWDER_SNOW: RuleSource = makeStateRule(Blocks.POWDER_SNOW)
    val ICE: RuleSource = makeStateRule(Blocks.ICE)
    val WATER: RuleSource = makeStateRule(Blocks.WATER)

    fun makeStateRule(block: Block): RuleSource = state(block.defaultBlockState())

     fun surfaceNoiseAbove(d: Double): ConditionSource {
        return noiseCondition(Noises.SURFACE, d / 8.25, Double.MAX_VALUE)
    }

    fun overworldLike(onlyAbovePrelimSurf: Boolean, bedrockFloor: Boolean): RuleSource {
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
        val frozenOceanCondition = isBiome(Biomes.FROZEN_OCEAN, Biomes.DEEP_FROZEN_OCEAN)
        val stepCheck = steep()
        val dirtRule = sequence(
            ifTrue(waterCheck, GRASS_BLOCK),
            DIRT
        )
        val sandRule = sequence(
            ifTrue(ON_CEILING, SANDSTONE),
            SAND
        )
        val gravelRule = sequence(
            ifTrue(ON_CEILING, STONE),
            GRAVEL
        )
        val sandBiomeCheck = isBiome(Biomes.WARM_OCEAN, Biomes.BEACH, Biomes.SNOWY_BEACH)
        val desertCheck = isBiome(Biomes.DESERT)
        val rule4 = sequence(
            ifTrue(
                isBiome(Biomes.STONY_PEAKS),
                sequence(
                    ifTrue(
                        noiseCondition(Noises.CALCITE, -0.0125, 0.0125),
                        CALCITE
                    ), STONE
                )
            ),
            ifTrue(
                isBiome(Biomes.STONY_SHORE),
                sequence(
                    ifTrue(
                        noiseCondition(Noises.GRAVEL, -0.05, 0.05),
                        gravelRule
                    ), STONE
                )
            ),
            ifTrue(
                isBiome(Biomes.WINDSWEPT_HILLS),
                ifTrue(surfaceNoiseAbove(1.0), STONE)
            ),
            ifTrue(sandBiomeCheck, sandRule),
            ifTrue(desertCheck, sandRule),
            ifTrue(isBiome(Biomes.DRIPSTONE_CAVES), STONE)
        )
        val powderedSnowCheck = ifTrue(
            noiseCondition(Noises.POWDER_SNOW, 0.45, 0.58),
            ifTrue(waterCheck, POWDER_SNOW)
        )
        val rule6 = ifTrue(
            noiseCondition(Noises.POWDER_SNOW, 0.35, 0.6),
            ifTrue(waterCheck, POWDER_SNOW)
        )
        val rule7 = sequence(
            ifTrue(
                isBiome(Biomes.FROZEN_PEAKS),
                sequence(
                    ifTrue(stepCheck, PACKED_ICE),
                    ifTrue(
                        noiseCondition(Noises.PACKED_ICE, -0.5, 0.2),
                        PACKED_ICE
                    ),
                    ifTrue(noiseCondition(Noises.ICE, -0.0625, 0.025), ICE),
                    ifTrue(waterCheck, SNOW_BLOCK)
                )
            ),
            ifTrue(
                isBiome(Biomes.SNOWY_SLOPES),
                sequence(
                    ifTrue(stepCheck, STONE),
                    powderedSnowCheck,
                    ifTrue(waterCheck, SNOW_BLOCK)
                )
            ),
            ifTrue(isBiome(Biomes.JAGGED_PEAKS), STONE),
            ifTrue(
                isBiome(Biomes.GROVE),
                sequence(powderedSnowCheck, DIRT)
            ),
            rule4,
            ifTrue(
                isBiome(Biomes.WINDSWEPT_SAVANNA),
                ifTrue(surfaceNoiseAbove(1.75), STONE)
            ),
            ifTrue(
                isBiome(Biomes.WINDSWEPT_GRAVELLY_HILLS),
                sequence(
                    ifTrue(surfaceNoiseAbove(2.0), gravelRule),
                    ifTrue(surfaceNoiseAbove(1.0), STONE),
                    ifTrue(surfaceNoiseAbove(-1.0), DIRT),
                    gravelRule
                )
            ),
            ifTrue(isBiome(Biomes.MANGROVE_SWAMP), MUD),
            DIRT
        )
        val rule8 = sequence(
            ifTrue(
                isBiome(Biomes.FROZEN_PEAKS),
                sequence(
                    ifTrue(stepCheck, PACKED_ICE),
                    ifTrue(
                        noiseCondition(Noises.PACKED_ICE, 0.0, 0.2),
                        PACKED_ICE
                    ),
                    ifTrue(noiseCondition(Noises.ICE, 0.0, 0.025), ICE),
                    ifTrue(waterCheck, SNOW_BLOCK)
                )
            ),
            ifTrue(
                isBiome(Biomes.SNOWY_SLOPES),
                sequence(
                    ifTrue(stepCheck, STONE),
                    rule6,
                    ifTrue(waterCheck, SNOW_BLOCK)
                )
            ),
            ifTrue(
                isBiome(Biomes.JAGGED_PEAKS),
                sequence(
                    ifTrue(stepCheck, STONE),
                    ifTrue(waterCheck, SNOW_BLOCK)
                )
            ),
            ifTrue(
                isBiome(Biomes.GROVE),
                sequence(rule6, ifTrue(waterCheck, SNOW_BLOCK))
            ),
            rule4,
            ifTrue(
                isBiome(Biomes.WINDSWEPT_SAVANNA),
                sequence(
                    ifTrue(
                        surfaceNoiseAbove(1.75),
                        STONE
                    ), ifTrue(surfaceNoiseAbove(-0.5), COARSE_DIRT)
                )
            ),
            ifTrue(
                isBiome(Biomes.WINDSWEPT_GRAVELLY_HILLS),
                sequence(
                    ifTrue(surfaceNoiseAbove(2.0), gravelRule),
                    ifTrue(surfaceNoiseAbove(1.0), STONE),
                    ifTrue(surfaceNoiseAbove(-1.0), dirtRule),
                    gravelRule
                )
            ),
            ifTrue(
                isBiome(Biomes.OLD_GROWTH_PINE_TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA),
                sequence(
                    ifTrue(
                        surfaceNoiseAbove(1.75),
                        COARSE_DIRT
                    ), ifTrue(surfaceNoiseAbove(-0.95), PODZOL)
                )
            ),
            ifTrue(
                isBiome(Biomes.ICE_SPIKES),
                ifTrue(waterCheck, SNOW_BLOCK)
            ),
            ifTrue(isBiome(Biomes.MANGROVE_SWAMP), MUD),
            ifTrue(isBiome(Biomes.MUSHROOM_FIELDS), MYCELIUM),
            dirtRule
        )
        val surfaceNoiseCheck1 = noiseCondition(Noises.SURFACE, -0.909, -0.5454)
        val surfaceNoiseCheck2 = noiseCondition(Noises.SURFACE, -0.1818, 0.1818)
        val surfaceNoiseCheck3 = noiseCondition(Noises.SURFACE, 0.5454, 0.909)
        val preFinalRules = sequence(
            ifTrue(
                ON_FLOOR,
                sequence(
                    ifTrue(
                        isBiome(Biomes.WOODED_BADLANDS),
                        ifTrue(
                            above97,
                            sequence(
                                ifTrue(surfaceNoiseCheck1, COARSE_DIRT),
                                ifTrue(surfaceNoiseCheck2, COARSE_DIRT),
                                ifTrue(surfaceNoiseCheck3, COARSE_DIRT),
                                dirtRule
                            )
                        )
                    ),
                    ifTrue(
                        isBiome(Biomes.SWAMP),
                        ifTrue(
                            above62,
                            ifTrue(
                                not(above63),
                                ifTrue(
                                    noiseCondition(Noises.SWAMP, 0.0),
                                    WATER
                                )
                            )
                        )
                    ),
                    ifTrue(
                        isBiome(Biomes.MANGROVE_SWAMP),
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
                isBiome(Biomes.BADLANDS, Biomes.ERODED_BADLANDS, Biomes.WOODED_BADLANDS),
                sequence(
                    ifTrue(
                        ON_FLOOR,
                        sequence(
                            ifTrue(above256, ORANGE_TERRACOTTA),
                            ifTrue(
                                above74,
                                sequence(
                                    ifTrue(surfaceNoiseCheck1, TERRACOTTA),
                                    ifTrue(surfaceNoiseCheck2, TERRACOTTA),
                                    ifTrue(surfaceNoiseCheck3, TERRACOTTA),
                                    bandlands()
                                )
                            ),
                            ifTrue(
                                waterDepth1Check,
                                sequence(
                                    ifTrue(
                                        ON_CEILING,
                                        RED_SANDSTONE
                                    ), RED_SAND
                                )
                            ),
                            ifTrue(not(holeCheck), ORANGE_TERRACOTTA),
                            ifTrue(waterDepth6Check, WHITE_TERRACOTTA),
                            gravelRule
                        )
                    ),
                    ifTrue(
                        above63Multi1,
                        sequence(
                            ifTrue(
                                above63,
                                ifTrue(
                                    not(above74),
                                    ORANGE_TERRACOTTA
                                )
                            ), bandlands()
                        )
                    ),
                    ifTrue(
                        UNDER_FLOOR,
                        ifTrue(waterDepth6Check, WHITE_TERRACOTTA)
                    )
                )
            ),
            ifTrue(
                ON_FLOOR,
                ifTrue(
                    waterDepth1Check,
                    sequence(
                        ifTrue(
                            frozenOceanCondition,
                            ifTrue(
                                holeCheck,
                                sequence(
                                    ifTrue(waterCheck, AIR),
                                    ifTrue(temperature(), ICE),
                                    WATER
                                )
                            )
                        ),
                        rule8
                    )
                )
            ),
            ifTrue(
                waterDepth6Check,
                sequence(
                    ifTrue(
                        ON_FLOOR,
                        ifTrue(
                            frozenOceanCondition,
                            ifTrue(holeCheck, WATER)
                        )
                    ),
                    ifTrue(UNDER_FLOOR, rule7),
                    ifTrue(
                        sandBiomeCheck,
                        ifTrue(DEEP_UNDER_FLOOR, SANDSTONE)
                    ),
                    ifTrue(
                        desertCheck,
                        ifTrue(VERY_DEEP_UNDER_FLOOR, SANDSTONE)
                    )
                )
            ),
            ifTrue(
                ON_FLOOR,
                sequence(
                    ifTrue(
                        isBiome(Biomes.FROZEN_PEAKS, Biomes.JAGGED_PEAKS),
                        STONE
                    ),
                    ifTrue(
                        isBiome(
                            Biomes.WARM_OCEAN,
                            Biomes.LUKEWARM_OCEAN,
                            Biomes.DEEP_LUKEWARM_OCEAN
                        ), sandRule
                    ),
                    gravelRule
                )
            )
        )
        val builder = ImmutableList.builder<RuleSource>()

        if (bedrockFloor) {
            builder.add(
                ifTrue(
                    verticalGradient(
                        "bedrock_floor",
                        VerticalAnchor.bottom(),
                        VerticalAnchor.aboveBottom(5)
                    ), BEDROCK
                )
            )
        }

        val finalRules = ifTrue(abovePreliminarySurface(), preFinalRules)
        builder.add(if (onlyAbovePrelimSurf) finalRules else preFinalRules)
        builder.add(
            ifTrue(
                verticalGradient(
                    "deepslate",
                    VerticalAnchor.absolute(0),
                    VerticalAnchor.absolute(8)
                ), DEEPSLATE
            )
        )
        return sequence(*builder.build().toTypedArray())
    }
}