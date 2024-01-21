package org.teamvoided.dusk_autumns_worldgen.datagen.worldgen

import com.google.common.collect.ImmutableList
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3i
import net.minecraft.world.gen.BootstrapContext
import net.minecraft.world.gen.blockpredicate.BlockPredicate
import net.minecraft.world.gen.decorator.*
import net.minecraft.world.gen.feature.PlacedFeature
import net.minecraft.world.gen.feature.PlacementModifier
import net.minecraft.world.gen.feature.VegetationConfiguredFeatures
import net.minecraft.world.gen.feature.VegetationPlacedFeatures
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil
import org.teamvoided.dusk_autumns_worldgen.init.worldgen.DuskConfiguredFeatures
import org.teamvoided.dusk_autumns_worldgen.init.worldgen.DuskPlacedFeatures

object PlacedFeatureCreator {
    fun bootstrap(context: BootstrapContext<PlacedFeature>) {

        val placedFeatureProvider = context.lookup(RegistryKeys.CONFIGURED_FEATURE)

        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.COBBLESTONE_ROCK,
            placedFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.COBBLESTONE_ROCK),
            *arrayOf<PlacementModifier>(
                CountPlacementModifier.create(2),
                InSquarePlacementModifier.getInstance(),
                PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.getInstance()
            )
        )
        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.MANGROVE_FROZEN_CHECKED,
            placedFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.MANGROVE_FROZEN_CHECKED),
            *arrayOf<PlacementModifier>(
                BlockPredicateFilterPlacementModifier.create(
                    BlockPredicate.wouldSurvive(Blocks.MANGROVE_PROPAGULE.defaultState, BlockPos.ORIGIN)
                )
            )
        )
        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.TALL_MANGROVE_FROZEN_CHECKED,
            placedFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.TALL_MANGROVE_FROZEN_CHECKED),
            *arrayOf<PlacementModifier>(
                BlockPredicateFilterPlacementModifier.create(
                    BlockPredicate.wouldSurvive(Blocks.MANGROVE_PROPAGULE.defaultState, BlockPos.ORIGIN)
                )
            )
        )
        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.TREES_MANGROVE_FROZEN,
            placedFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.MANGROVE_FROZEN_VEGETATION),
            *arrayOf<PlacementModifier>(
                CountPlacementModifier.create(15),
                InSquarePlacementModifier.getInstance(),
                SurfaceWaterDepthFilterPlacementModifier.create(5),
                PlacedFeatureUtil.OCEAN_FLOOR_HEIGHTMAP,
                BiomePlacementModifier.getInstance(),
                BlockPredicateFilterPlacementModifier.create(
                    BlockPredicate.wouldSurvive(Blocks.MANGROVE_PROPAGULE.defaultState, BlockPos.ORIGIN)
                )
            )
        )
        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.TREES_COLD_FOREST,
            placedFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.TREES_OAK_BIRCH_SPRUCE),
            VegetationPlacedFeatures.treePlacementModifiers(PlacedFeatureUtil.createCountExtraModifier(10, 0.1f, 1))
        )
        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.TREES_COLD_PLAINS,
            placedFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.TREES_OAK_BIRCH_SPRUCE),
            VegetationPlacedFeatures.treePlacementModifiers(PlacedFeatureUtil.createCountExtraModifier(10, 0.1f, 1))
        )
        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.TREES_WARM_FOREST,
            placedFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.TREES_OAK_BIRCH_JUNGLE),
            VegetationPlacedFeatures.treePlacementModifiers(PlacedFeatureUtil.createCountExtraModifier(10, 0.1f, 1))
        )
        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.TREES_WARM_PLAINS,
            placedFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.TREES_OAK_BIRCH_JUNGLE),
            VegetationPlacedFeatures.treePlacementModifiers(PlacedFeatureUtil.createCountExtraModifier(10, 0.1f, 1))
        )
        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.FLOWER_SNOWY_CHERRY,
            placedFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.FLOWER_SNOWY_CHERRY),
            *arrayOf<PlacementModifier>(
                NoiseThresholdCountPlacementModifier.create(-0.8, 5, 10),
                RarityFilterPlacementModifier.create(5),
                InSquarePlacementModifier.getInstance(),
                PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.getInstance()
            )
        )

        val blockPredicate =
            BlockPredicate.matchingBlocks(Direction.DOWN.vector, *arrayOf(Blocks.SNOW_BLOCK, Blocks.POWDER_SNOW))
        val list = listOf(
            EnvironmentScanPlacementModifier.create(
                Direction.UP, BlockPredicate.not(
                    BlockPredicate.matchingBlocks(*arrayOf(Blocks.POWDER_SNOW))
                ), 8
            ), BlockPredicateFilterPlacementModifier.create(blockPredicate)
        )
        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.CHERRY_ON_SNOW,
            placedFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.CHERRY_SNOW_BEES),
            list
        )
        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.CHERRY_ON_SNOW_BEES,
            placedFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.CHERRY_SNOW_BEES),
            list
        )

        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.TREES_SNOWY_CHERRY_GROVE,
            placedFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.TREES_SNOWY_CHERRY),
            treePlacementModifiersBase(
                PlacedFeatureUtil.createCountExtraModifier(
                    10,
                    0.1f,
                    1
                )
            ).add(
                BlockPredicateFilterPlacementModifier.create(
                    BlockPredicate.matchingBlocks(
                        Vec3i(0, -1, 0), Blocks.SNOW_BLOCK, Blocks.POWDER_SNOW
                    )
                ),

                EnvironmentScanPlacementModifier.create(
                    Direction.UP,
                    BlockPredicate.matchingBlocks(Blocks.POWDER_SNOW), 8,
                )
            )
                .build()
        )



        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.MUSHROOM_GROVE_VEGETATION,
            placedFeatureProvider.getHolderOrThrow(VegetationConfiguredFeatures.MUSHROOM_ISLAND_VEGETATION),
            *arrayOf<PlacementModifier>(
                CountPlacementModifier.create(12),
                InSquarePlacementModifier.getInstance(),
                PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.getInstance()
            )
        )
    }

    fun treePlacementModifiersBase(modifier: PlacementModifier): ImmutableList.Builder<PlacementModifier> {
        return ImmutableList.builder<PlacementModifier>().add(modifier).add(InSquarePlacementModifier.getInstance())
            .add(SurfaceWaterDepthFilterPlacementModifier.create(0)).add(PlacedFeatureUtil.OCEAN_FLOOR_HEIGHTMAP)
            .add(BiomePlacementModifier.getInstance())
    }
}