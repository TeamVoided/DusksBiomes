package org.teamvoided.dusks_biomes.data.gen.world.gen

import com.google.common.collect.ImmutableList
import net.minecraft.block.Blocks
import net.minecraft.registry.BootstrapContext
import net.minecraft.registry.Holder
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.BlockTags
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3i
import net.minecraft.util.math.int_provider.ConstantIntProvider
import net.minecraft.util.math.int_provider.UniformIntProvider
import net.minecraft.world.gen.YOffset
import net.minecraft.world.gen.blockpredicate.BlockPredicate
import net.minecraft.world.gen.decorator.*
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil
import org.teamvoided.dusks_biomes.data.tags.DuskBlockTags
import org.teamvoided.dusks_biomes.data.world.gen.DuskConfiguredFeatures
import org.teamvoided.dusks_biomes.data.world.gen.DuskPlacedFeatures

@Suppress("MagicNumber")
object PlacedFeatureCreator {
    fun bootstrap(c: BootstrapContext<PlacedFeature>) {

        val configuredFeatureProvider = c.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE)

        c.register(
            DuskPlacedFeatures.SWAMP_VILLAGE_ROCK,
            configuredFeatureProvider.getHolderOrThrow(MiscConfiguredFeatures.FOREST_ROCK)
        )
        c.register(
            DuskPlacedFeatures.SWAMP_VILLAGE_OAK,
            configuredFeatureProvider.getHolderOrThrow(TreeConfiguredFeatures.SWAMP_OAK),
            PlacedFeatureUtil.createWouldSurvivePlacementModifier(Blocks.OAK_SAPLING)
        )
        c.register(
            DuskPlacedFeatures.SWAMP_VILLAGE_MANGROVE,
            configuredFeatureProvider.getHolderOrThrow(VegetationConfiguredFeatures.MANGROVE_VEGETATION),
            PlacedFeatureUtil.createWouldSurvivePlacementModifier(Blocks.MANGROVE_PROPAGULE)
        )
        c.register(
            DuskPlacedFeatures.SWAMP_VILLAGE_FLOWERS,
            configuredFeatureProvider.getHolderOrThrow(VegetationConfiguredFeatures.FLOWER_SWAMP)
        )
        c.register(
            DuskPlacedFeatures.COBBLESTONE_ROCK,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.COBBLESTONE_ROCK),
            CountPlacementModifier.create(2),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.getInstance()

        )
        c.register(
            DuskPlacedFeatures.TREES_WINDSWEPT_BIRCH,
            configuredFeatureProvider.getHolderOrThrow(VegetationConfiguredFeatures.BIRCH_TALL),
            VegetationPlacedFeatures.treePlacementModifiers(
                PlacedFeatureUtil.createCountExtraModifier(2, 0.1f, 1)
            )
        )

        c.register(
            DuskPlacedFeatures.MANGROVE_FROZEN_CHECKED,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.MANGROVE_FROZEN_CHECKED),
            BlockPredicateFilterPlacementModifier.create(
                BlockPredicate.wouldSurvive(Blocks.MANGROVE_PROPAGULE.defaultState, BlockPos.ORIGIN)
            )
        )
        c.register(
            DuskPlacedFeatures.TALL_MANGROVE_FROZEN_CHECKED,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.TALL_MANGROVE_FROZEN_CHECKED),
            BlockPredicateFilterPlacementModifier.create(
                BlockPredicate.wouldSurvive(Blocks.MANGROVE_PROPAGULE.defaultState, BlockPos.ORIGIN)
            )
        )
        c.register(
            DuskPlacedFeatures.TREES_MANGROVE_FROZEN,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.MANGROVE_FROZEN_VEGETATION),
            CountPlacementModifier.create(15),
            InSquarePlacementModifier.getInstance(),
            SurfaceWaterDepthFilterPlacementModifier.create(5),
            PlacedFeatureUtil.OCEAN_FLOOR_HEIGHTMAP,
            BiomePlacementModifier.getInstance(),
            BlockPredicateFilterPlacementModifier.create(
                BlockPredicate.wouldSurvive(Blocks.MANGROVE_PROPAGULE.defaultState, BlockPos.ORIGIN)
            )
        )
        c.register(
            DuskPlacedFeatures.TREES_WINDSWEPT_MANGROVE_FROZEN,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.MANGROVE_FROZEN_VEGETATION),
            CountPlacementModifier.create(3),
            InSquarePlacementModifier.getInstance(),
            SurfaceWaterDepthFilterPlacementModifier.create(5),
            PlacedFeatureUtil.OCEAN_FLOOR_HEIGHTMAP,
            BiomePlacementModifier.getInstance(),
            BlockPredicateFilterPlacementModifier.create(
                BlockPredicate.wouldSurvive(Blocks.MANGROVE_PROPAGULE.defaultState, BlockPos.ORIGIN)
            )
        )
        c.register(
            DuskPlacedFeatures.TREES_WINDSWEPT_MANGROVE,
            configuredFeatureProvider.getHolderOrThrow(VegetationConfiguredFeatures.MANGROVE_VEGETATION),
            CountPlacementModifier.create(5),
            InSquarePlacementModifier.getInstance(),
            SurfaceWaterDepthFilterPlacementModifier.create(5),
            PlacedFeatureUtil.OCEAN_FLOOR_HEIGHTMAP,
            BiomePlacementModifier.getInstance(),
            BlockPredicateFilterPlacementModifier.create(
                BlockPredicate.wouldSurvive(Blocks.MANGROVE_PROPAGULE.defaultState, BlockPos.ORIGIN)
            )
        )
        c.register(
            DuskPlacedFeatures.TREES_OLD_GROWTH_SWAMP,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.SWAMP_OAK),
            PlacedFeatureUtil.createCountExtraModifier(12, 0.1f, 1),
            InSquarePlacementModifier.getInstance(),
            SurfaceWaterDepthFilterPlacementModifier.create(2),
            PlacedFeatureUtil.OCEAN_FLOOR_HEIGHTMAP,
            BiomePlacementModifier.getInstance(),
            BlockPredicateFilterPlacementModifier.create(
                BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.defaultState, BlockPos.ORIGIN)
            )
        )
        c.register(
            DuskPlacedFeatures.TREES_COLD_FOREST,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.TREES_OAK_BIRCH_SPRUCE),
            VegetationPlacedFeatures.treePlacementModifiers(
                PlacedFeatureUtil.createCountExtraModifier(10, 0.1f, 1)
            )
        )
        c.register(
            DuskPlacedFeatures.TREES_COLD_PLAINS,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.TREES_OAK_BIRCH_SPRUCE),
            VegetationPlacedFeatures.treePlacementModifiers(
                PlacedFeatureUtil.createCountExtraModifier(0, 0.05f, 1)
            )
        )
        c.register(
            DuskPlacedFeatures.TREES_WARM_FOREST,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.TREES_OAK_BIRCH_JUNGLE),
            VegetationPlacedFeatures.treePlacementModifiers(
                PlacedFeatureUtil.createCountExtraModifier(10, 0.1f, 1)
            )
        )
        c.register(
            DuskPlacedFeatures.TREES_WARM_PLAINS,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.TREES_OAK_BIRCH_JUNGLE),
            VegetationPlacedFeatures.treePlacementModifiers(
                PlacedFeatureUtil.createCountExtraModifier(0, 0.05f, 1)
            )
        )
        c.register(
            DuskPlacedFeatures.FLOWER_SNOWY_CHERRY,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.FLOWER_SNOWY_CHERRY),
            NoiseThresholdCountPlacementModifier.create(-0.8, 5, 10),
            RarityFilterPlacementModifier.create(5),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.getInstance()
        )

        val blockPredicate =
            BlockPredicate.matchingBlocks(Direction.DOWN.vector, *arrayOf(Blocks.SNOW_BLOCK, Blocks.POWDER_SNOW))

        val list = listOf(
            EnvironmentScanPlacementModifier.create(
                Direction.UP, BlockPredicate.not(
                    BlockPredicate.matchingBlocks(Blocks.POWDER_SNOW)
                ), 8
            ), BlockPredicateFilterPlacementModifier.create(blockPredicate)
        )

        c.register(
            DuskPlacedFeatures.CHERRY_ON_SNOW,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.CHERRY_SNOW_BEES),
            list
        )
        c.register(
            DuskPlacedFeatures.CHERRY_ON_SNOW_BEES,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.CHERRY_SNOW_BEES),
            list
        )

        c.register(
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
            ).build()
        )
        c.register(
            DuskPlacedFeatures.MUSHROOM_GROVE_VEGETATION,
            configuredFeatureProvider.getHolderOrThrow(VegetationConfiguredFeatures.MUSHROOM_ISLAND_VEGETATION),
            CountPlacementModifier.create(12),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.getInstance()
        )

        c.register(
            DuskPlacedFeatures.CAVE_DEAD_BUSH,
            configuredFeatureProvider.getHolderOrThrow(VegetationConfiguredFeatures.PATCH_DEAD_BUSH),
            CountPlacementModifier.create(UniformIntProvider.create(124, 177)),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.BOTTOM_TO_MAX_TERRAIN_HEIGHT_RANGE,
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DuskPlacedFeatures.CAVE_GLOW_LICHEN_EXTRA,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.CAVE_GLOW_LICHEN_EXTRA),
            CountPlacementModifier.create(UniformIntProvider.create(3, 33)),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.BOTTOM_TO_MAX_TERRAIN_HEIGHT_RANGE,
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DuskPlacedFeatures.ORE_COARSE_DIRT,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.ORE_COARSE_DIRT),
            RarityFilterPlacementModifier.create(3),
            InSquarePlacementModifier.getInstance(),
            HeightRangePlacementModifier.createUniform(YOffset.getBottom(), YOffset.fixed(160)),
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DuskPlacedFeatures.MUSHROOM_CAVE_VEGETATION,
            configuredFeatureProvider.getHolderOrThrow(VegetationConfiguredFeatures.MUSHROOM_ISLAND_VEGETATION),
            CountPlacementModifier.create(125),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.BOTTOM_TO_MAX_TERRAIN_HEIGHT_RANGE,
            EnvironmentScanPlacementModifier.create(
                Direction.DOWN,
                BlockPredicate.solid(),
                BlockPredicate.IS_AIR,
                12
            ),
            RandomOffsetPlacementModifier.vertical(ConstantIntProvider.create(1)),
            BiomePlacementModifier.getInstance()
        )

        c.register(
            DuskPlacedFeatures.MUSHROOM_CAVE_MUSHROOMS,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.MUSHROOM_CAVE_MUSHROOMS),
            CountPlacementModifier.create(33),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.BOTTOM_TO_MAX_TERRAIN_HEIGHT_RANGE,
            EnvironmentScanPlacementModifier.create(
                Direction.DOWN,
                BlockPredicate.solid(),
                BlockPredicate.IS_AIR,
                12
            ),
            RandomOffsetPlacementModifier.vertical(ConstantIntProvider.create(1)),
            BiomePlacementModifier.getInstance()
        )

        c.register(
            DuskPlacedFeatures.MUSHROOM_CAVE_SURFACE,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.MUSHROOM_CAVE_ROOTS),
            CountPlacementModifier.create(UniformIntProvider.create(1, 2)),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.BOTTOM_TO_MAX_TERRAIN_HEIGHT_RANGE,
            EnvironmentScanPlacementModifier.create(
                Direction.UP, BlockPredicate.solid(), BlockPredicate.IS_AIR_OR_WATER, 12
            ),
            RandomOffsetPlacementModifier.vertical(ConstantIntProvider.create(-1)),
            BiomePlacementModifier.getInstance()
        )


        c.register(
            DuskPlacedFeatures.ICE_CAVE_PILLAR,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.ICE_CAVE_PILLAR),
            *arrayOf<PlacementModifier>(
                CountPlacementModifier.create(UniformIntProvider.create(20, 48)),
                InSquarePlacementModifier.getInstance(),
                PlacedFeatureUtil.BOTTOM_TO_MAX_TERRAIN_HEIGHT_RANGE,
                BiomePlacementModifier.getInstance()
            )
        )
        c.register(
            DuskPlacedFeatures.ICE_SPIKE_FLOOR,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.ICE_SPIKE_FLOOR),
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
        c.register(
            DuskPlacedFeatures.ICE_SPIKE_CEILING,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.ICE_SPIKE_CEILING),
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
        c.register(
            DuskPlacedFeatures.ORE_ICE,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.ORE_ICE),
            CountPlacementModifier.create(3),
            InSquarePlacementModifier.getInstance(),
            HeightRangePlacementModifier.createUniform(YOffset.getBottom(), YOffset.fixed(160)),
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DuskPlacedFeatures.ICE_CAVE_FOSSIL,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.ICE_CAVE_FOSSIL),
            CountPlacementModifier.create(3),
            InSquarePlacementModifier.getInstance(),
            HeightRangePlacementModifier.createUniform(YOffset.getBottom(), YOffset.fixed(160)),
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DuskPlacedFeatures.ORE_BLUE_ICE,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.ORE_BLUE_ICE),
            InSquarePlacementModifier.getInstance(),
            HeightRangePlacementModifier.createUniform(YOffset.getBottom(), YOffset.fixed(160)),
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DuskPlacedFeatures.ORE_SAND,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.ORE_SAND),
            CountPlacementModifier.create(14),
            InSquarePlacementModifier.getInstance(),
            HeightRangePlacementModifier.createUniform(YOffset.getBottom(), YOffset.fixed(160)),
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DuskPlacedFeatures.ORE_RED_SAND,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.ORE_RED_SAND),
            CountPlacementModifier.create(14),
            InSquarePlacementModifier.getInstance(),
            HeightRangePlacementModifier.createUniform(YOffset.getBottom(), YOffset.fixed(160)),
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DuskPlacedFeatures.SAND_CACTUS,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.SAND_CAVE_CACTUS),
            CountPlacementModifier.create(88),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.BOTTOM_TO_MAX_TERRAIN_HEIGHT_RANGE,
            EnvironmentScanPlacementModifier.create(
                Direction.DOWN, BlockPredicate.matchingBlockTags(BlockTags.SAND), BlockPredicate.IS_AIR, 12
            ),
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DuskPlacedFeatures.SAND_CAVE_VINES,
            configuredFeatureProvider.getHolderOrThrow(UndergroundConfiguredFeatures.CAVE_VINE),
            CountPlacementModifier.create(47),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.BOTTOM_TO_MAX_TERRAIN_HEIGHT_RANGE,
            EnvironmentScanPlacementModifier.create(
                Direction.UP, BlockPredicate.hasSturdyFace(Direction.DOWN), BlockPredicate.IS_AIR, 12
            ),
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DuskPlacedFeatures.SAND_CAVE_PILLAR,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.SAND_CAVE_PILLAR),
            *arrayOf<PlacementModifier>(
                CountPlacementModifier.create(UniformIntProvider.create(20, 48)),
                InSquarePlacementModifier.getInstance(),
                PlacedFeatureUtil.BOTTOM_TO_MAX_TERRAIN_HEIGHT_RANGE,
                BiomePlacementModifier.getInstance()
            )
        )
        c.register(
            DuskPlacedFeatures.SAND_SPIKES,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.SAND_SPIKES),
            CountPlacementModifier.create(100),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.BOTTOM_TO_MAX_TERRAIN_HEIGHT_RANGE,
            EnvironmentScanPlacementModifier.create(
                Direction.DOWN, BlockPredicate.matchingBlocks(Blocks.SANDSTONE), BlockPredicate.IS_AIR, 12
            ),
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DuskPlacedFeatures.SAND_SPIKES_ROOF,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.SAND_SPIKES_ROOF),
            CountPlacementModifier.create(100),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.BOTTOM_TO_MAX_TERRAIN_HEIGHT_RANGE,
            EnvironmentScanPlacementModifier.create(
                Direction.UP, BlockPredicate.matchingBlocks(Blocks.SANDSTONE), BlockPredicate.IS_AIR, 12
            ),
            RandomOffsetPlacementModifier.vertical(ConstantIntProvider.create(-1)),
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DuskPlacedFeatures.RED_SAND_CAVE_PILLAR,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.RED_SAND_CAVE_PILLAR),
            *arrayOf<PlacementModifier>(
                CountPlacementModifier.create(UniformIntProvider.create(20, 48)),
                InSquarePlacementModifier.getInstance(),
                PlacedFeatureUtil.BOTTOM_TO_MAX_TERRAIN_HEIGHT_RANGE,
                BiomePlacementModifier.getInstance()
            )
        )
        c.register(
            DuskPlacedFeatures.RED_SAND_SPIKES,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.RED_SAND_SPIKES),
            CountPlacementModifier.create(100),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.BOTTOM_TO_MAX_TERRAIN_HEIGHT_RANGE,
            EnvironmentScanPlacementModifier.create(
                Direction.DOWN, BlockPredicate.matchingBlocks(Blocks.RED_SANDSTONE), BlockPredicate.IS_AIR, 12
            ),
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DuskPlacedFeatures.RED_SAND_SPIKES_ROOF,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.RED_SAND_SPIKES_ROOF),
            CountPlacementModifier.create(100),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.BOTTOM_TO_MAX_TERRAIN_HEIGHT_RANGE,
            EnvironmentScanPlacementModifier.create(
                Direction.UP, BlockPredicate.matchingBlocks(Blocks.RED_SANDSTONE), BlockPredicate.IS_AIR, 12
            ),
            RandomOffsetPlacementModifier.vertical(ConstantIntProvider.create(-1)),
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DuskPlacedFeatures.SAND_CAVE_CORAL,
            configuredFeatureProvider.getHolderOrThrow(OceanConfiguredFeatures.WARM_OCEAN_VEGETATION),
            CountPlacementModifier.create(256),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.BOTTOM_TO_MAX_TERRAIN_HEIGHT_RANGE,
            EnvironmentScanPlacementModifier.create(
                Direction.DOWN,
                BlockPredicate.hasSturdyFace(Direction.UP),
                BlockPredicate.matchingBlocks(Blocks.WATER),
                12
            ),
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DuskPlacedFeatures.SAND_CAVE_SEAGRASS,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.SAND_CAVE_SEAGRASS),
            CountPlacementModifier.create(128),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.BOTTOM_TO_MAX_TERRAIN_HEIGHT_RANGE,
            EnvironmentScanPlacementModifier.create(
                Direction.DOWN,
                BlockPredicate.hasSturdyFace(Direction.UP),
                BlockPredicate.matchingBlocks(Blocks.WATER),
                12
            ),
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DuskPlacedFeatures.SAND_CAVE_PICKLE,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.SAND_CAVE_PICKLES),
            CountPlacementModifier.create(256),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.BOTTOM_TO_MAX_TERRAIN_HEIGHT_RANGE,
            EnvironmentScanPlacementModifier.create(
                Direction.DOWN,
                BlockPredicate.hasSturdyFace(Direction.UP),
                BlockPredicate.matchingBlocks(Blocks.WATER),
                12
            ),
            BiomePlacementModifier.getInstance()
        )

//Structure Piece Features
        c.register(
            DuskPlacedFeatures.DESERT_WELL,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.DESERT_WELL),
            RarityFilterPlacementModifier.create(1000),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
            RandomOffsetPlacementModifier.vertical(ConstantIntProvider.create(-2)),
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DuskPlacedFeatures.RED_DESERT_WELL,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.RED_DESERT_WELL),
            RarityFilterPlacementModifier.create(1000),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.MOTION_BLOCKING_HEIGHTMAP,
            RandomOffsetPlacementModifier.vertical(ConstantIntProvider.create(-2)),
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DuskPlacedFeatures.CAVE_DESERT_WELL,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.DESERT_WELL),
            RarityFilterPlacementModifier.create(50),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.BOTTOM_TO_MAX_TERRAIN_HEIGHT_RANGE,
            EnvironmentScanPlacementModifier.create(
                Direction.DOWN,
                BlockPredicate.hasSturdyFace(Direction.UP),
                BlockPredicate.IS_AIR_OR_WATER,
                12
            ),
            RandomOffsetPlacementModifier.vertical(ConstantIntProvider.create(-2)),
            BiomePlacementModifier.getInstance()
        )
        c.register(
            DuskPlacedFeatures.CAVE_RED_DESERT_WELL,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.RED_DESERT_WELL),
            RarityFilterPlacementModifier.create(50),
            InSquarePlacementModifier.getInstance(),
            PlacedFeatureUtil.BOTTOM_TO_MAX_TERRAIN_HEIGHT_RANGE,
            EnvironmentScanPlacementModifier.create(
                Direction.DOWN,
                BlockPredicate.hasSturdyFace(Direction.UP),
                BlockPredicate.IS_AIR_OR_WATER,
                12
            ),
            RandomOffsetPlacementModifier.vertical(ConstantIntProvider.create(-2)),
            BiomePlacementModifier.getInstance()
        )

//Monster Room features
        val upperMonsterRoom = listOf(
                CountPlacementModifier.create(10),
                InSquarePlacementModifier.getInstance(),
                HeightRangePlacementModifier.createUniform(YOffset.fixed(0), YOffset.getTop()),
                BiomePlacementModifier.getInstance()
            )
        val lowerMonsterRoom = listOf(
                CountPlacementModifier.create(4),
                InSquarePlacementModifier.getInstance(),
                HeightRangePlacementModifier.createUniform(YOffset.aboveBottom(6), YOffset.fixed(-1)),
                BiomePlacementModifier.getInstance()
            )

        c.register(
            DuskPlacedFeatures.DEEP_MONSTER_ROOM,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.DEEP_MONSTER_ROOM),
            lowerMonsterRoom
        )
        c.register(
            DuskPlacedFeatures.LUSH_MONSTER_ROOM,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.LUSH_MONSTER_ROOM),
            upperMonsterRoom
        )
        c.register(
            DuskPlacedFeatures.DEEP_LUSH_MONSTER_ROOM,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.DEEP_LUSH_MONSTER_ROOM),
            lowerMonsterRoom
        )
        c.register(
            DuskPlacedFeatures.FROZEN_MONSTER_ROOM,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.FROZEN_MONSTER_ROOM),
            upperMonsterRoom
        )
        c.register(
            DuskPlacedFeatures.DEEP_FROZEN_MONSTER_ROOM,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.DEEP_FROZEN_MONSTER_ROOM),
            lowerMonsterRoom
        )
        c.register(
            DuskPlacedFeatures.SAND_MONSTER_ROOM,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.SAND_MONSTER_ROOM),
            upperMonsterRoom
        )
        c.register(
            DuskPlacedFeatures.DEEP_SAND_MONSTER_ROOM,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.SAND_MONSTER_ROOM),
            lowerMonsterRoom
        )
        c.register(
            DuskPlacedFeatures.RED_SAND_MONSTER_ROOM,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.RED_SAND_MONSTER_ROOM),
            upperMonsterRoom
        )
        c.register(
            DuskPlacedFeatures.DEEP_RED_SAND_MONSTER_ROOM,
            configuredFeatureProvider.getHolderOrThrow(DuskConfiguredFeatures.RED_SAND_MONSTER_ROOM),
            lowerMonsterRoom
        )

//        c.register(
//            UndergroundPlacedFeatures.SPORE_BLOSSOM, holder14,
//            CountPlacementModifier.create(25),
//            InSquarePlacementModifier.getInstance(),
//            PlacedFeatureUtil.BOTTOM_TO_MAX_TERRAIN_HEIGHT_RANGE,
//            EnvironmentScanPlacementModifier.create(
//                Direction.UP, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12
//            ),
//            RandomOffsetPlacementModifier.vertical(ConstantIntProvider.create(-1)),
//            BiomePlacementModifier.getInstance()
//
//        )

    }

    private fun treePlacementModifiersBase(modifier: PlacementModifier): ImmutableList.Builder<PlacementModifier> {
        return ImmutableList.builder<PlacementModifier>().add(modifier).add(InSquarePlacementModifier.getInstance())
            .add(SurfaceWaterDepthFilterPlacementModifier.create(0)).add(PlacedFeatureUtil.OCEAN_FLOOR_HEIGHTMAP)
            .add(BiomePlacementModifier.getInstance())
    }

//    PlacedFeatureUtil.register(
//    c,

    fun BootstrapContext<PlacedFeature>.register(
        registryKey: RegistryKey<PlacedFeature>, configuredFeature: Holder<ConfiguredFeature<*, *>>,
        vararg placementModifiers: PlacementModifier
    ): Any = this.register(registryKey, PlacedFeature(configuredFeature, placementModifiers.toList()))

    fun BootstrapContext<PlacedFeature>.register(
        registryKey: RegistryKey<PlacedFeature>, configuredFeature: Holder<ConfiguredFeature<*, *>>,
        placementModifiers: List<PlacementModifier>
    ): Any = this.register(registryKey, PlacedFeature(configuredFeature, placementModifiers))

}
