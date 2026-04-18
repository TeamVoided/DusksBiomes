package org.teamvoided.dusks_biomes.datagen.data.worldgen

import net.minecraft.world.level.biome.Biomes
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.Noises
import net.minecraft.world.level.levelgen.SurfaceRules.*
import net.minecraft.world.level.levelgen.VerticalAnchor
import org.teamvoided.dusks_biomes.data.world.gen.DuskSurfaceRules
import org.teamvoided.dusks_biomes.data.world.gen.VanillaSurfaceRules
import org.teamvoided.dusks_biomes.init.DuskBiomes

object DSurfaceRules {

    val RED_SAND_STONE = block(Blocks.RED_SANDSTONE)
    val MOD = block(Blocks.MUD)

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
        val isSandBiome = isBiome(DuskBiomes.WARM_RIVER)
        val isRedSandBiome = isBiome(
            DuskBiomes.RED_WARM_RIVER,
            DuskBiomes.RED_BEACH,
            DuskBiomes.SNOWY_RED_BEACH,

            DuskBiomes.RED_WARM_OCEAN,
            DuskBiomes.RED_LUKEWARM_OCEAN,
        )

        val hasRedSandOceanFloor = isBiome(
            DuskBiomes.RED_DESERT,
            DuskBiomes.RED_BEACH,
            DuskBiomes.RED_WARM_RIVER,
            DuskBiomes.RED_WARM_OCEAN,
            DuskBiomes.RED_LUKEWARM_OCEAN,
            DuskBiomes.DEEP_RED_LUKEWARM_OCEAN,
        )

        val hasSandOceanFloor = isBiome(
            DuskBiomes.WARM_RIVER,
            Biomes.DESERT,
            Biomes.BEACH,
        )

        val isRedDesertBiome = isBiome(DuskBiomes.RED_DESERT)

        val mangroveMud = ifTrue(
            isBiome(DuskBiomes.FROZEN_MANGROVE_SWAMP),
            DuskSurfaceRules.block(Blocks.MUD)
        )

        val rule4 = sequence(
            ifTrue(isSandBiome, DuskSurfaceRules.sand),
            ifTrue(isRedSandBiome, DuskSurfaceRules.redSand),
            ifTrue(isRedDesertBiome, DuskSurfaceRules.redSand),
        )


        // rule7
        val floorWaterDepth6 = sequence(
            rule4,
            mangroveMud
        )


        // rule8
        val floorWaterDepth1 = sequence(
            rule4,
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
                                        VanillaSurfaceRules.WATER
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
                        floorWaterDepth1
                    )
                ),
                ifTrue(
                    waterDepth6Check,
                    sequence(
                        ifTrue(UNDER_FLOOR, floorWaterDepth6),
                        ifTrue(
                            isSandBiome,
                            ifTrue(DEEP_UNDER_FLOOR, VanillaSurfaceRules.SANDSTONE)
                        ),
                        ifTrue(
                            isRedSandBiome,
                            ifTrue(DEEP_UNDER_FLOOR, RED_SAND_STONE)
                        ),
                        ifTrue(
                            isRedDesertBiome,
                            ifTrue(VERY_DEEP_UNDER_FLOOR, RED_SAND_STONE)
                        )
                    )
                ),
                ifTrue(
                    ON_FLOOR,
                    sequence(
                        ifTrue(
                            hasRedSandOceanFloor,
                            DuskSurfaceRules.redSand
                        ),
                        ifTrue(
                            hasSandOceanFloor,
                            DuskSurfaceRules.sand
                        )
                    )
                )
            )
        )

        val caveRules = sequence(
            ifTrue(
                isBiome(DuskBiomes.FROZEN_ERODED_BADLANDS),
                block(Blocks.MUD)
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
    // endregion
}