package org.teamvoided.dusk_autumns_worldgen.datagen.worldgen

import com.google.common.collect.ImmutableList
import net.minecraft.block.Blocks
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3i
import net.minecraft.world.gen.BootstrapContext
import net.minecraft.world.gen.blockpredicate.BlockPredicate
import net.minecraft.world.gen.decorator.*
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil
import org.teamvoided.dusk_autumns_worldgen.init.worldgen.DuskConfiguredFeatures
import org.teamvoided.dusk_autumns_worldgen.init.worldgen.DuskPlacedFeatures

object PlacedFeatureCreator {
    fun bootstrap(context: BootstrapContext<PlacedFeature>) {

        val configuredFeatureProvider = context.lookup(RegistryKeys.CONFIGURED_FEATURE)

        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.COBBLESTONE_ROCK,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.COBBLESTONE_ROCK),
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
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.MANGROVE_FROZEN_CHECKED),
            *arrayOf<PlacementModifier>(
                BlockPredicateFilterPlacementModifier.create(
                    BlockPredicate.wouldSurvive(Blocks.MANGROVE_PROPAGULE.defaultState, BlockPos.ORIGIN)
                )
            )
        )
        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.TALL_MANGROVE_FROZEN_CHECKED,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.TALL_MANGROVE_FROZEN_CHECKED),
            *arrayOf<PlacementModifier>(
                BlockPredicateFilterPlacementModifier.create(
                    BlockPredicate.wouldSurvive(Blocks.MANGROVE_PROPAGULE.defaultState, BlockPos.ORIGIN)
                )
            )
        )
        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.TREES_MANGROVE_FROZEN,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.MANGROVE_FROZEN_VEGETATION),
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
            DuskPlacedFeatures.TREES_WINDSWEPT_MANGROVE_FROZEN,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.MANGROVE_FROZEN_VEGETATION),
            *arrayOf<PlacementModifier>(
                CountPlacementModifier.create(3),
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
            DuskPlacedFeatures.TREES_WINDSWEPT_MANGROVE,
            configuredFeatureProvider.getHolderOrThrow(VegetationConfiguredFeatures.MANGROVE_VEGETATION),
            *arrayOf<PlacementModifier>(
                CountPlacementModifier.create(5),
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
            DuskPlacedFeatures.TREES_OLD_GROWTH_SWAMP,
            configuredFeatureProvider.getHolderOrThrow(TreeConfiguredFeatures.SWAMP_OAK),
            *arrayOf<PlacementModifier>(
                PlacedFeatureUtil.createCountExtraModifier(13, 0.1f, 1),
                InSquarePlacementModifier.getInstance(),
                SurfaceWaterDepthFilterPlacementModifier.create(2),
                PlacedFeatureUtil.OCEAN_FLOOR_HEIGHTMAP,
                BiomePlacementModifier.getInstance(),
                BlockPredicateFilterPlacementModifier.create(
                    BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultState, BlockPos.ORIGIN)
                )
            )
        )
        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.TREES_COLD_FOREST,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.TREES_OAK_BIRCH_SPRUCE),
            VegetationPlacedFeatures.treePlacementModifiers(PlacedFeatureUtil.createCountExtraModifier(10, 0.1f, 1))
        )
        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.TREES_COLD_PLAINS,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.TREES_OAK_BIRCH_SPRUCE),
            VegetationPlacedFeatures.treePlacementModifiers(PlacedFeatureUtil.createCountExtraModifier(10, 0.1f, 1))
        )
        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.TREES_WARM_FOREST,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.TREES_OAK_BIRCH_JUNGLE),
            VegetationPlacedFeatures.treePlacementModifiers(PlacedFeatureUtil.createCountExtraModifier(10, 0.1f, 1))
        )
        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.TREES_WARM_PLAINS,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.TREES_OAK_BIRCH_JUNGLE),
            VegetationPlacedFeatures.treePlacementModifiers(PlacedFeatureUtil.createCountExtraModifier(10, 0.1f, 1))
        )
        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.FLOWER_SNOWY_CHERRY,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.FLOWER_SNOWY_CHERRY),
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
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.CHERRY_SNOW_BEES),
            list
        )
        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.CHERRY_ON_SNOW_BEES,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.CHERRY_SNOW_BEES),
            list
        )

        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.TREES_SNOWY_CHERRY_GROVE,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.TREES_SNOWY_CHERRY),
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
            configuredFeatureProvider.getHolderOrThrow(VegetationConfiguredFeatures.MUSHROOM_ISLAND_VEGETATION),
            *arrayOf<PlacementModifier>(
                CountPlacementModifier.create(12),
                InSquarePlacementModifier.getInstance(),
                PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
                BiomePlacementModifier.getInstance()
            )
        )
        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.INVERTED_ICE_SPIKE_CAVE,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.INVERTED_ICE_SPIKE),
            *arrayOf<PlacementModifier>(
                CountPlacementModifier.create(3),
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