package org.teamvoided.dusk_autumns_worldgen.datagen.worldgen

import com.google.common.collect.ImmutableList
import net.minecraft.block.Blocks
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3i
import net.minecraft.util.math.int_provider.ConstantIntProvider
import net.minecraft.util.math.int_provider.IntProvider
import net.minecraft.world.gen.BootstrapContext
import net.minecraft.world.gen.YOffset
import net.minecraft.world.gen.blockpredicate.BlockPredicate
import net.minecraft.world.gen.decorator.*
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil
import org.teamvoided.dusk_autumns_worldgen.data.DuskBlockTags
import org.teamvoided.dusk_autumns_worldgen.init.worldgen.DuskConfiguredFeatures
import org.teamvoided.dusk_autumns_worldgen.init.worldgen.DuskPlacedFeatures

object PlacedFeatureCreator {
    fun bootstrap(context: BootstrapContext<PlacedFeature>) {

        val configuredFeatureProvider = context.lookup(RegistryKeys.CONFIGURED_FEATURE)

        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.SWAMP_VILLAGE_ROCK,
            configuredFeatureProvider.getHolderOrThrow(MiscConfiguredFeatures.FOREST_ROCK),
            *arrayOfNulls<PlacementModifier>(0)
        )
        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.SWAMP_VILLAGE_OAK,
            configuredFeatureProvider.getHolderOrThrow(TreeConfiguredFeatures.SWAMP_OAK),
            *arrayOfNulls<PlacementModifier>(0)
        )
        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.SWAMP_VILLAGE_MANGROVE,
            configuredFeatureProvider.getHolderOrThrow(VegetationConfiguredFeatures.MANGROVE_VEGETATION),
            *arrayOfNulls<PlacementModifier>(0)
        )
        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.SWAMP_VILLAGE_FLOWERS,
            configuredFeatureProvider.getHolderOrThrow(VegetationConfiguredFeatures.FLOWER_SWAMP),
            *arrayOfNulls<PlacementModifier>(0)
        )
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
            DuskPlacedFeatures.TREES_WINDSWEPT_BIRCH,
            configuredFeatureProvider.getHolderOrThrow(VegetationConfiguredFeatures.BIRCH_TALL),
            VegetationPlacedFeatures.treePlacementModifiers(PlacedFeatureUtil.createCountExtraModifier(2, 0.1f, 1))
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
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.SWAMP_OAK),
            *arrayOf<PlacementModifier>(
                PlacedFeatureUtil.createCountExtraModifier(12, 0.1f, 1),
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
            VegetationPlacedFeatures.treePlacementModifiers(PlacedFeatureUtil.createCountExtraModifier(0, 0.05f, 1))
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
            VegetationPlacedFeatures.treePlacementModifiers(PlacedFeatureUtil.createCountExtraModifier(0, 0.05f, 1))
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
            DuskPlacedFeatures.ICE_SPIKE_FLOOR,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.ICE_SPIKE_FLOOR),
            *arrayOf<PlacementModifier>(
                CountPlacementModifier.create(125),
                InSquarePlacementModifier.getInstance(),
                PlacedFeatureUtil.BOTTOM_TO_MAX_TERRAIN_HEIGHT_RANGE,
                EnvironmentScanPlacementModifier.create(
                    Direction.DOWN,
                    BlockPredicate.matchingBlocks(Blocks.SNOW_BLOCK),
                    BlockPredicate.matchingBlockTags(DuskBlockTags.ICE_SPIKE_IGNORE_BLOCKS),
                    12
                ),
                BiomePlacementModifier.getInstance()
            )
        )
        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.ICE_SPIKE_CEILING,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.ICE_SPIKE_CEILING),
            *arrayOf<PlacementModifier>(
                CountPlacementModifier.create(125),
                InSquarePlacementModifier.getInstance(),
                PlacedFeatureUtil.BOTTOM_TO_MAX_TERRAIN_HEIGHT_RANGE,
                EnvironmentScanPlacementModifier.create(
                    Direction.UP,
                    BlockPredicate.matchingBlocks(Blocks.SNOW_BLOCK),
                    BlockPredicate.matchingBlockTags(DuskBlockTags.ICE_SPIKE_IGNORE_BLOCKS),
                    12
                ),
                BiomePlacementModifier.getInstance()
            )
        )
        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.ORE_ICE,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.ORE_ICE),
            *arrayOf<PlacementModifier>(
                CountPlacementModifier.create(3),
                InSquarePlacementModifier.getInstance(),
                HeightRangePlacementModifier.createUniform(YOffset.getBottom(), YOffset.fixed(160)),
                BiomePlacementModifier.getInstance()
            )
        )
        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.ORE_BLUE_ICE,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.ORE_BLUE_ICE),
            *arrayOf<PlacementModifier>(
                InSquarePlacementModifier.getInstance(),
                HeightRangePlacementModifier.createUniform(YOffset.getBottom(), YOffset.fixed(160)),
                BiomePlacementModifier.getInstance()
            )
        )
        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.DESERT_WELL,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.DESERT_WELL),
            *arrayOf<PlacementModifier>(
                RarityFilterPlacementModifier.create(1000),
                InSquarePlacementModifier.getInstance(),
                PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
                RandomOffsetPlacementModifier.vertical(ConstantIntProvider.create(-3)),
                BiomePlacementModifier.getInstance()
            )
        )
        PlacedFeatureUtil.register(
            context,
            DuskPlacedFeatures.RED_DESERT_WELL,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.RED_DESERT_WELL),
            *arrayOf<PlacementModifier>(
                RarityFilterPlacementModifier.create(1000),
                InSquarePlacementModifier.getInstance(),
                PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
                RandomOffsetPlacementModifier.vertical(ConstantIntProvider.create(-3)),
                BiomePlacementModifier.getInstance()
            )
        )


//        PlacedFeatureUtil.register(
//            context, UndergroundPlacedFeatures.SPORE_BLOSSOM, holder14, *arrayOf<PlacementModifier>(
//                CountPlacementModifier.create(25),
//                InSquarePlacementModifier.getInstance(),
//                PlacedFeatureUtil.BOTTOM_TO_MAX_TERRAIN_HEIGHT_RANGE,
//                EnvironmentScanPlacementModifier.create(
//                    Direction.UP, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12
//                ),
//                RandomOffsetPlacementModifier.vertical(ConstantIntProvider.create(-1)),
//                BiomePlacementModifier.getInstance()
//            )
//        )

//        PlacedFeatureUtil.register(
//            context,
//            UndergroundPlacedFeatures.MONSTER_ROOM_DEEP,
//            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.CUSTOM_MONSTER_ROOM),
//            *arrayOf<PlacementModifier>(
//                CountPlacementModifier.create(4),
//                InSquarePlacementModifier.getInstance(),
//                HeightRangePlacementModifier.createUniform(YOffset.aboveBottom(6), YOffset.fixed(-1)),
//                BiomePlacementModifier.getInstance()
//            )
//        )


    }

    fun treePlacementModifiersBase(modifier: PlacementModifier): ImmutableList.Builder<PlacementModifier> {
        return ImmutableList.builder<PlacementModifier>().add(modifier).add(InSquarePlacementModifier.getInstance())
            .add(SurfaceWaterDepthFilterPlacementModifier.create(0)).add(PlacedFeatureUtil.OCEAN_FLOOR_HEIGHTMAP)
            .add(BiomePlacementModifier.getInstance())
    }
}