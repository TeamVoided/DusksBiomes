package org.teamvoided.dusks_biomes.datagen.data.worldgen

import com.google.common.collect.ImmutableList
import net.minecraft.world.level.biome.Biomes
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.Noises
import net.minecraft.world.level.levelgen.SurfaceRules
import net.minecraft.world.level.levelgen.VerticalAnchor

object VanillaSurfaceRules {

    val AIR: SurfaceRules.RuleSource = makeStateRule(Blocks.AIR)
    val BEDROCK: SurfaceRules.RuleSource = makeStateRule(Blocks.BEDROCK)
    val WHITE_TERRACOTTA: SurfaceRules.RuleSource = makeStateRule(Blocks.WHITE_TERRACOTTA)
    val ORANGE_TERRACOTTA: SurfaceRules.RuleSource = makeStateRule(Blocks.ORANGE_TERRACOTTA)
    val TERRACOTTA: SurfaceRules.RuleSource = makeStateRule(Blocks.TERRACOTTA)
    val RED_SAND: SurfaceRules.RuleSource = makeStateRule(Blocks.RED_SAND)
    val RED_SANDSTONE: SurfaceRules.RuleSource = makeStateRule(Blocks.RED_SANDSTONE)
    val STONE: SurfaceRules.RuleSource = makeStateRule(Blocks.STONE)
    val DEEPSLATE: SurfaceRules.RuleSource = makeStateRule(Blocks.DEEPSLATE)
    val DIRT: SurfaceRules.RuleSource = makeStateRule(Blocks.DIRT)
    val PODZOL: SurfaceRules.RuleSource = makeStateRule(Blocks.PODZOL)
    val COARSE_DIRT: SurfaceRules.RuleSource = makeStateRule(Blocks.COARSE_DIRT)
    val MYCELIUM: SurfaceRules.RuleSource = makeStateRule(Blocks.MYCELIUM)
    val GRASS_BLOCK: SurfaceRules.RuleSource = makeStateRule(Blocks.GRASS_BLOCK)
    val CALCITE: SurfaceRules.RuleSource = makeStateRule(Blocks.CALCITE)
    val GRAVEL: SurfaceRules.RuleSource = makeStateRule(Blocks.GRAVEL)
    val SAND: SurfaceRules.RuleSource = makeStateRule(Blocks.SAND)
    val SANDSTONE: SurfaceRules.RuleSource = makeStateRule(Blocks.SANDSTONE)
    val PACKED_ICE: SurfaceRules.RuleSource = makeStateRule(Blocks.PACKED_ICE)
    val SNOW_BLOCK: SurfaceRules.RuleSource = makeStateRule(Blocks.SNOW_BLOCK)
    val MUD: SurfaceRules.RuleSource = makeStateRule(Blocks.MUD)
    val POWDER_SNOW: SurfaceRules.RuleSource = makeStateRule(Blocks.POWDER_SNOW)
    val ICE: SurfaceRules.RuleSource = makeStateRule(Blocks.ICE)
    val WATER: SurfaceRules.RuleSource = makeStateRule(Blocks.WATER)

    fun makeStateRule(block: Block): SurfaceRules.RuleSource = SurfaceRules.state(block.defaultBlockState())

    fun surfaceNoiseAbove(d: Double): SurfaceRules.ConditionSource {
        return SurfaceRules.noiseCondition(Noises.SURFACE, d / 8.25, Double.MAX_VALUE)
    }

    fun overworldLike(onlyAbovePrelimSurf: Boolean, bedrockFloor: Boolean): SurfaceRules.RuleSource {
        val above97 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(97), 2)
        val above256 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(256), 0)
        val above63Multi1 = SurfaceRules.yStartCheck(VerticalAnchor.absolute(63), -1)
        val above74 = SurfaceRules.yStartCheck(VerticalAnchor.absolute(74), 1)
        val above60 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(60), 0)
        val above62 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 0)
        val above63 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(63), 0)
        val waterDepth1Check = SurfaceRules.waterBlockCheck(-1, 0)
        val waterCheck = SurfaceRules.waterBlockCheck(0, 0)
        val waterDepth6Check = SurfaceRules.waterStartCheck(-6, -1)
        val holeCheck = SurfaceRules.hole()
        val frozenOceanCondition = SurfaceRules.isBiome(Biomes.FROZEN_OCEAN, Biomes.DEEP_FROZEN_OCEAN)
        val stepCheck = SurfaceRules.steep()
        val dirtRule = SurfaceRules.sequence(
            SurfaceRules.ifTrue(waterCheck, GRASS_BLOCK),
            DIRT
        )
        val sandRule = SurfaceRules.sequence(
            SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, SANDSTONE),
            SAND
        )
        val gravelRule = SurfaceRules.sequence(
            SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, STONE),
            GRAVEL
        )
        val sandBiomeCheck = SurfaceRules.isBiome(Biomes.WARM_OCEAN, Biomes.BEACH, Biomes.SNOWY_BEACH)
        val desertCheck = SurfaceRules.isBiome(Biomes.DESERT)
        val rule4 = SurfaceRules.sequence(
            SurfaceRules.ifTrue(
                SurfaceRules.isBiome(Biomes.STONY_PEAKS),
                SurfaceRules.sequence(
                    SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.CALCITE, -0.0125, 0.0125), CALCITE),
                    STONE
                )
            ),
            SurfaceRules.ifTrue(
                SurfaceRules.isBiome(Biomes.STONY_SHORE),
                SurfaceRules.sequence(
                    SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.GRAVEL, -0.05, 0.05), gravelRule),
                    STONE
                )
            ),
            SurfaceRules.ifTrue(
                SurfaceRules.isBiome(Biomes.WINDSWEPT_HILLS),
                SurfaceRules.ifTrue(surfaceNoiseAbove(1.0), STONE)
            ),
            SurfaceRules.ifTrue(sandBiomeCheck, sandRule),
            SurfaceRules.ifTrue(desertCheck, sandRule),
            SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.DRIPSTONE_CAVES), STONE)
        )
        val powderedSnowCheck1 = SurfaceRules.ifTrue(
            SurfaceRules.noiseCondition(Noises.POWDER_SNOW, 0.45, 0.58),
            SurfaceRules.ifTrue(waterCheck, POWDER_SNOW)
        )
        val powderedSnowCheck2 = SurfaceRules.ifTrue(
            SurfaceRules.noiseCondition(Noises.POWDER_SNOW, 0.35, 0.6),
            SurfaceRules.ifTrue(waterCheck, POWDER_SNOW)
        )
        val rule7 = SurfaceRules.sequence(
            SurfaceRules.ifTrue(
                SurfaceRules.isBiome(Biomes.FROZEN_PEAKS),
                SurfaceRules.sequence(
                    SurfaceRules.ifTrue(stepCheck, PACKED_ICE),
                    SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.PACKED_ICE, -0.5, 0.2), PACKED_ICE),
                    SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.ICE, -0.0625, 0.025), ICE),
                    SurfaceRules.ifTrue(waterCheck, SNOW_BLOCK)
                )
            ),
            SurfaceRules.ifTrue(
                SurfaceRules.isBiome(Biomes.SNOWY_SLOPES),
                SurfaceRules.sequence(
                    SurfaceRules.ifTrue(stepCheck, STONE),
                    powderedSnowCheck1,
                    SurfaceRules.ifTrue(waterCheck, SNOW_BLOCK)
                )
            ),
            SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.JAGGED_PEAKS), STONE),
            SurfaceRules.ifTrue(
                SurfaceRules.isBiome(Biomes.GROVE),
                SurfaceRules.sequence(powderedSnowCheck1, DIRT)
            ),
            rule4,
            SurfaceRules.ifTrue(
                SurfaceRules.isBiome(Biomes.WINDSWEPT_SAVANNA),
                SurfaceRules.ifTrue(surfaceNoiseAbove(1.75), STONE)
            ),
            SurfaceRules.ifTrue(
                SurfaceRules.isBiome(Biomes.WINDSWEPT_GRAVELLY_HILLS),
                SurfaceRules.sequence(
                    SurfaceRules.ifTrue(surfaceNoiseAbove(2.0), gravelRule),
                    SurfaceRules.ifTrue(surfaceNoiseAbove(1.0), STONE),
                    SurfaceRules.ifTrue(surfaceNoiseAbove(-1.0), DIRT),
                    gravelRule
                )
            ),
            SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.MANGROVE_SWAMP), MUD),
            DIRT
        )
        val rule8 = SurfaceRules.sequence(
            SurfaceRules.ifTrue(
                SurfaceRules.isBiome(Biomes.FROZEN_PEAKS),
                SurfaceRules.sequence(
                    SurfaceRules.ifTrue(stepCheck, PACKED_ICE),
                    SurfaceRules.ifTrue(
                        SurfaceRules.noiseCondition(Noises.PACKED_ICE, 0.0, 0.2),
                        PACKED_ICE
                    ),
                    SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.ICE, 0.0, 0.025), ICE),
                    SurfaceRules.ifTrue(waterCheck, SNOW_BLOCK)
                )
            ),
            SurfaceRules.ifTrue(
                SurfaceRules.isBiome(Biomes.SNOWY_SLOPES),
                SurfaceRules.sequence(
                    SurfaceRules.ifTrue(stepCheck, STONE),
                    powderedSnowCheck2,
                    SurfaceRules.ifTrue(waterCheck, SNOW_BLOCK)
                )
            ),
            SurfaceRules.ifTrue(
                SurfaceRules.isBiome(Biomes.JAGGED_PEAKS),
                SurfaceRules.sequence(
                    SurfaceRules.ifTrue(stepCheck, STONE),
                    SurfaceRules.ifTrue(waterCheck, SNOW_BLOCK)
                )
            ),
            SurfaceRules.ifTrue(
                SurfaceRules.isBiome(Biomes.GROVE),
                SurfaceRules.sequence(powderedSnowCheck2, SurfaceRules.ifTrue(waterCheck, SNOW_BLOCK))
            ),
            rule4,
            SurfaceRules.ifTrue(
                SurfaceRules.isBiome(Biomes.WINDSWEPT_SAVANNA),
                SurfaceRules.sequence(
                    SurfaceRules.ifTrue(surfaceNoiseAbove(1.75), STONE),
                    SurfaceRules.ifTrue(surfaceNoiseAbove(-0.5), COARSE_DIRT)
                )
            ),
            SurfaceRules.ifTrue(
                SurfaceRules.isBiome(Biomes.WINDSWEPT_GRAVELLY_HILLS),
                SurfaceRules.sequence(
                    SurfaceRules.ifTrue(surfaceNoiseAbove(2.0), gravelRule),
                    SurfaceRules.ifTrue(surfaceNoiseAbove(1.0), STONE),
                    SurfaceRules.ifTrue(surfaceNoiseAbove(-1.0), dirtRule),
                    gravelRule
                )
            ),
            SurfaceRules.ifTrue(
                SurfaceRules.isBiome(Biomes.OLD_GROWTH_PINE_TAIGA, Biomes.OLD_GROWTH_SPRUCE_TAIGA),
                SurfaceRules.sequence(
                    SurfaceRules.ifTrue(surfaceNoiseAbove(1.75), COARSE_DIRT),
                    SurfaceRules.ifTrue(surfaceNoiseAbove(-0.95), PODZOL)
                )
            ),
            SurfaceRules.ifTrue(
                SurfaceRules.isBiome(Biomes.ICE_SPIKES),
                SurfaceRules.ifTrue(waterCheck, SNOW_BLOCK)
            ),
            SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.MANGROVE_SWAMP), MUD),
            SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.MUSHROOM_FIELDS), MYCELIUM),
            dirtRule
        )
        val surfaceNoiseCheck1 = SurfaceRules.noiseCondition(Noises.SURFACE, -0.909, -0.5454)
        val surfaceNoiseCheck2 = SurfaceRules.noiseCondition(Noises.SURFACE, -0.1818, 0.1818)
        val surfaceNoiseCheck3 = SurfaceRules.noiseCondition(Noises.SURFACE, 0.5454, 0.909)
        val preFinalRules = SurfaceRules.sequence(
            SurfaceRules.ifTrue(
                SurfaceRules.ON_FLOOR,
                SurfaceRules.sequence(
                    SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.WOODED_BADLANDS),
                        SurfaceRules.ifTrue(
                            above97,
                            SurfaceRules.sequence(
                                SurfaceRules.ifTrue(surfaceNoiseCheck1, COARSE_DIRT),
                                SurfaceRules.ifTrue(surfaceNoiseCheck2, COARSE_DIRT),
                                SurfaceRules.ifTrue(surfaceNoiseCheck3, COARSE_DIRT),
                                dirtRule
                            )
                        )
                    ),
                    SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.SWAMP),
                        SurfaceRules.ifTrue(
                            above62,
                            SurfaceRules.ifTrue(
                                SurfaceRules.not(above63),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SWAMP, 0.0), WATER)
                            )
                        )
                    ),
                    SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(Biomes.MANGROVE_SWAMP),
                        SurfaceRules.ifTrue(
                            above60,
                            SurfaceRules.ifTrue(
                                SurfaceRules.not(above63),
                                SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SWAMP, 0.0), WATER)
                            )
                        )
                    )
                )
            ),
            SurfaceRules.ifTrue(
                SurfaceRules.isBiome(Biomes.BADLANDS, Biomes.ERODED_BADLANDS, Biomes.WOODED_BADLANDS),
                SurfaceRules.sequence(
                    SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.sequence(
                            SurfaceRules.ifTrue(above256, ORANGE_TERRACOTTA),
                            SurfaceRules.ifTrue(
                                above74,
                                SurfaceRules.sequence(
                                    SurfaceRules.ifTrue(surfaceNoiseCheck1, TERRACOTTA),
                                    SurfaceRules.ifTrue(surfaceNoiseCheck2, TERRACOTTA),
                                    SurfaceRules.ifTrue(surfaceNoiseCheck3, TERRACOTTA),
                                    SurfaceRules.bandlands()
                                )
                            ),
                            SurfaceRules.ifTrue(
                                waterDepth1Check,
                                SurfaceRules.sequence(
                                    SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, RED_SANDSTONE),
                                    RED_SAND
                                )
                            ),
                            SurfaceRules.ifTrue(SurfaceRules.not(holeCheck), ORANGE_TERRACOTTA),
                            SurfaceRules.ifTrue(waterDepth6Check, WHITE_TERRACOTTA),
                            gravelRule
                        )
                    ),
                    SurfaceRules.ifTrue(
                        above63Multi1,
                        SurfaceRules.sequence(
                            SurfaceRules.ifTrue(
                                above63,
                                SurfaceRules.ifTrue(SurfaceRules.not(above74), ORANGE_TERRACOTTA)
                            ),
                            SurfaceRules.bandlands()
                        )
                    ),
                    SurfaceRules.ifTrue(
                        SurfaceRules.UNDER_FLOOR,
                        SurfaceRules.ifTrue(waterDepth6Check, WHITE_TERRACOTTA)
                    )
                )
            ),
            SurfaceRules.ifTrue(
                SurfaceRules.ON_FLOOR,
                SurfaceRules.ifTrue(
                    waterDepth1Check,
                    SurfaceRules.sequence(
                        SurfaceRules.ifTrue(
                            frozenOceanCondition,
                            SurfaceRules.ifTrue(
                                holeCheck,
                                SurfaceRules.sequence(
                                    SurfaceRules.ifTrue(waterCheck, AIR),
                                    SurfaceRules.ifTrue(SurfaceRules.temperature(), ICE),
                                    WATER
                                )
                            )
                        ),
                        rule8
                    )
                )
            ),
            SurfaceRules.ifTrue(
                waterDepth6Check,
                SurfaceRules.sequence(
                    SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.ifTrue(
                            frozenOceanCondition,
                            SurfaceRules.ifTrue(holeCheck, WATER)
                        )
                    ),
                    SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, rule7),
                    SurfaceRules.ifTrue(
                        sandBiomeCheck,
                        SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, SANDSTONE)
                    ),
                    SurfaceRules.ifTrue(
                        desertCheck,
                        SurfaceRules.ifTrue(SurfaceRules.VERY_DEEP_UNDER_FLOOR, SANDSTONE)
                    )
                )
            ),
            SurfaceRules.ifTrue(
                SurfaceRules.ON_FLOOR,
                SurfaceRules.sequence(
                    SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.FROZEN_PEAKS, Biomes.JAGGED_PEAKS), STONE),
                    SurfaceRules.ifTrue(
                        SurfaceRules.isBiome(
                            Biomes.WARM_OCEAN,
                            Biomes.LUKEWARM_OCEAN,
                            Biomes.DEEP_LUKEWARM_OCEAN
                        ),
                        sandRule
                    ),
                    gravelRule
                )
            )
        )
        val builder = ImmutableList.builder<SurfaceRules.RuleSource>()

        if (bedrockFloor) {
            builder.add(
                SurfaceRules.ifTrue(
                    SurfaceRules.verticalGradient(
                        "bedrock_floor",
                        VerticalAnchor.bottom(),
                        VerticalAnchor.aboveBottom(5)
                    ), BEDROCK
                )
            )
        }

        val finalRules = SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), preFinalRules)
        builder.add(if (onlyAbovePrelimSurf) finalRules else preFinalRules)
        builder.add(
            SurfaceRules.ifTrue(
                SurfaceRules.verticalGradient(
                    "deepslate",
                    VerticalAnchor.absolute(0),
                    VerticalAnchor.absolute(8)
                ), DEEPSLATE
            )
        )
        return SurfaceRules.sequence(*builder.build().toTypedArray())
    }
}