package org.teamvoided.dusks_biomes.datagen.data.worldgen

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.Holder
import net.minecraft.core.HolderGetter
import net.minecraft.core.HolderSet
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.ProcessorLists
import net.minecraft.data.worldgen.features.CaveFeatures
import net.minecraft.data.worldgen.features.FeatureUtils
import net.minecraft.data.worldgen.features.VegetationFeatures
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.data.worldgen.placement.TreePlacements
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.BlockTags
import net.minecraft.util.random.WeightedList
import net.minecraft.util.valueproviders.*
import net.minecraft.world.entity.EntityType
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature
import net.minecraft.world.level.levelgen.feature.configurations.*
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize
import net.minecraft.world.level.levelgen.feature.foliageplacers.RandomSpreadFoliagePlacer
import net.minecraft.world.level.levelgen.feature.rootplacers.AboveRootPlacement
import net.minecraft.world.level.levelgen.feature.rootplacers.MangroveRootPlacement
import net.minecraft.world.level.levelgen.feature.rootplacers.MangroveRootPlacer
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider
import net.minecraft.world.level.levelgen.feature.stateproviders.NoiseThresholdProvider
import net.minecraft.world.level.levelgen.feature.stateproviders.RandomizedIntStateProvider
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLeavesDecorator
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator
import net.minecraft.world.level.levelgen.feature.trunkplacers.UpwardsBranchingTrunkPlacer
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest
import net.minecraft.world.level.levelgen.synth.NormalNoise
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.level.storage.loot.BuiltInLootTables
import org.teamvoided.dusks_biomes.DusksBiomes.id
import org.teamvoided.dusks_biomes.datagen.data.worldgen.configured_feature.TreeConfiguredCreator.trees
import org.teamvoided.dusks_biomes.data.tags.DuskBlockTags
import org.teamvoided.dusks_biomes.data.world.gen.DuskConfiguredFeatures
import org.teamvoided.dusks_biomes.data.world.gen.DuskPlacedFeatures
import org.teamvoided.dusks_biomes.datagen.data.worldgen.configured_feature.CaveConfiguredCreator.caves
import org.teamvoided.reef.init.ReefFeatures
import org.teamvoided.reef.world.level.levelgen.feature.config.*
import java.util.*
import kotlin.collections.listOf

object ConfiguredFeatureCreator {
    fun bootstrap(c: BootstrapContext<ConfiguredFeature<*, *>>) {
        val blockTags = c.lookup(Registries.BLOCK)
        val cF = c.lookup(Registries.CONFIGURED_FEATURE)
        val pF = c.lookup(Registries.PLACED_FEATURE)
        val procLists = c.lookup(Registries.PROCESSOR_LIST)

        c.trees()
        c.caves()

        c.registerConfiguredFeature(
            DuskConfiguredFeatures.COBBLESTONE_ROCK, Feature.FOREST_ROCK,
            BlockStateConfiguration(Blocks.COBBLESTONE.defaultBlockState())
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.MANGROVE_FROZEN_CHECKED, Feature.TREE,
            TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.MANGROVE_LOG),
                UpwardsBranchingTrunkPlacer(
                    2,
                    1,
                    4,
                    UniformInt.of(1, 4),
                    0.5f,
                    UniformInt.of(0, 1),
                    blockTags.getOrThrow(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)
                ),
                BlockStateProvider.simple(Blocks.MANGROVE_LEAVES),
                RandomSpreadFoliagePlacer(
                    ConstantInt.of(3),
                    ConstantInt.of(0),
                    ConstantInt.of(2),
                    70
                ),
                Optional.of(
                    MangroveRootPlacer(
                        UniformInt.of(1, 3),
                        BlockStateProvider.simple(Blocks.MANGROVE_ROOTS),
                        Optional.of(AboveRootPlacement(BlockStateProvider.simple(Blocks.SNOW), 0.5f)),
                        MangroveRootPlacement(
                            blockTags.getOrThrow(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH),
                            HolderSet.direct(
                                { it.builtInRegistryHolder() },
                                Blocks.MUD, Blocks.MUDDY_MANGROVE_ROOTS
                            ),
                            BlockStateProvider.simple(Blocks.MUDDY_MANGROVE_ROOTS),
                            8, 15, 0.2f
                        )
                    )
                ),
                TwoLayersFeatureSize(2, 0, 2)
            ).decorators(
                listOf(
                    LeaveVineDecorator(0.125f), AttachedToLeavesDecorator(
                        0.14f, 1, 0, RandomizedIntStateProvider(
                            BlockStateProvider.simple(
                                Blocks.MANGROVE_PROPAGULE.defaultBlockState()
                                    .setValue(MangrovePropaguleBlock.HANGING, true)
                            ), MangrovePropaguleBlock.AGE, UniformInt.of(0, 4)
                        ), 2, listOf(Direction.DOWN)
                    ), BeehiveDecorator(0.01F)
                )
            ).ignoreVines().build()
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.TALL_MANGROVE_FROZEN_CHECKED, Feature.TREE,
            TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.MANGROVE_LOG),
                UpwardsBranchingTrunkPlacer(
                    4, 1, 9,
                    UniformInt.of(1, 6),
                    0.5f,
                    UniformInt.of(0, 1),
                    blockTags.getOrThrow(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)
                ),
                BlockStateProvider.simple(Blocks.MANGROVE_LEAVES),
                RandomSpreadFoliagePlacer(
                    ConstantInt.of(3),
                    ConstantInt.of(0),
                    ConstantInt.of(2),
                    70
                ),
                Optional.of(
                    MangroveRootPlacer(
                        UniformInt.of(3, 7),
                        BlockStateProvider.simple(Blocks.MANGROVE_ROOTS),
                        Optional.of(
                            AboveRootPlacement(BlockStateProvider.simple(Blocks.SNOW), 0.5f)
                        ),
                        MangroveRootPlacement(
                            blockTags.getOrThrow(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH),
                            HolderSet.direct(
                                { it.builtInRegistryHolder() },
                                Blocks.MUD, Blocks.MUDDY_MANGROVE_ROOTS
                            ),
                            BlockStateProvider.simple(Blocks.MUDDY_MANGROVE_ROOTS),
                            8, 15, 0.2f
                        )
                    )
                ),
                TwoLayersFeatureSize(3, 0, 2)
            ).decorators(
                listOf(
                    LeaveVineDecorator(0.125f), AttachedToLeavesDecorator(
                        0.14f, 1, 0, RandomizedIntStateProvider(
                            BlockStateProvider.simple(
                                Blocks.MANGROVE_PROPAGULE.defaultBlockState()
                                    .setValue(MangrovePropaguleBlock.HANGING, true)
                            ), MangrovePropaguleBlock.AGE, UniformInt.of(0, 4)
                        ), 2, listOf(Direction.DOWN)
                    ), BeehiveDecorator(0.01F)
                )
            ).ignoreVines().build()
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.MANGROVE_FROZEN_VEGETATION,
            Feature.RANDOM_SELECTOR,
            RandomFeatureConfiguration(
                listOf(pF.wp(DuskPlacedFeatures.TALL_MANGROVE_FROZEN_CHECKED, 0.85f)),
                pF.getOrThrow(DuskPlacedFeatures.MANGROVE_FROZEN_CHECKED)
            )
        )
//DARK OAK TRUNK DOESN'T REPLACE WATER AAAAAAAAAAAAAAA
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.TREES_OAK_BIRCH_ACACIA,
            Feature.RANDOM_SELECTOR,
            RandomFeatureConfiguration(
                listOf(
                    pF.wp(TreePlacements.FALLEN_BIRCH_TREE, 0.0025f),
                    pF.wp(TreePlacements.BIRCH_BEES_0002_LEAF_LITTER, 0.2f),
                    pF.wp(TreePlacements.FANCY_OAK_BEES_0002_LEAF_LITTER, 0.1f),
                    pF.wp(TreePlacements.FALLEN_OAK_TREE, 0.0125f),
                    pF.wp(TreePlacements.ACACIA_CHECKED, 0.015f)
                ), pF.getOrThrow(TreePlacements.OAK_BEES_0002_LEAF_LITTER)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.TREES_OAK_BIRCH_SPRUCE,
            Feature.RANDOM_SELECTOR,
            RandomFeatureConfiguration(
                listOf(
                    pF.wp(TreePlacements.FALLEN_BIRCH_TREE, 0.0025f),
                    pF.wp(TreePlacements.BIRCH_BEES_0002_LEAF_LITTER, 0.2f),
                    pF.wp(TreePlacements.FANCY_OAK_BEES_0002_LEAF_LITTER, 0.1f),
                    pF.wp(TreePlacements.FALLEN_OAK_TREE, 0.0125f),
                    pF.wp(TreePlacements.SPRUCE_CHECKED, 0.02f),
                    pF.wp(TreePlacements.FALLEN_SPRUCE_TREE, 0.0025f)
                ), pF.getOrThrow(TreePlacements.OAK_BEES_0002_LEAF_LITTER)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.FLOWER_SNOWY_CHERRY, Feature.FLOWER, RandomPatchConfiguration(
                96, 6, 2, PlacementUtils.onlyWhenEmpty(
                    Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(
                        WeightedStateProvider(flowerbed(Blocks.PINK_PETALS).add(Blocks.SNOW.defaultBlockState(), 8))
                    )
                )
            )
        )

        c.registerConfiguredFeature<RandomPatchConfiguration, Feature<RandomPatchConfiguration>>(
            DuskConfiguredFeatures.CAVE_GLOW_LICHEN_EXTRA,
            Feature.RANDOM_PATCH,
            FeatureUtils.simpleRandomPatchConfiguration(
                20, PlacementUtils.inlinePlaced(
                    Feature.MULTIFACE_GROWTH,
                    MultifaceGrowthConfiguration(
                        Blocks.GLOW_LICHEN as MultifaceSpreadeableBlock,
                        20,
                        false,
                        true,
                        true,
                        0.75f,
                        HolderSet.direct(
                            { it.builtInRegistryHolder() },
                            Blocks.STONE,
                            Blocks.ANDESITE,
                            Blocks.DIORITE,
                            Blocks.GRANITE,
                            Blocks.DRIPSTONE_BLOCK,
                            Blocks.CALCITE,
                            Blocks.TUFF,
                            Blocks.DEEPSLATE
                        )
                    ),
                )
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.ORE_COARSE_DIRT,
            Feature.ORE,
            OreConfiguration(
                TagMatchTest(DuskBlockTags.CAVE_PILLAR_PLACEABLE),
                Blocks.COARSE_DIRT.defaultBlockState(),
                64
            )
        )
        c.registerConfiguredFeature<RandomBooleanFeatureConfiguration, Feature<RandomBooleanFeatureConfiguration>>(
            DuskConfiguredFeatures.MUSHROOM_CAVE_MUSHROOMS,
            Feature.RANDOM_BOOLEAN_SELECTOR,
            RandomBooleanFeatureConfiguration(
                PlacementUtils.inlinePlaced(
                    cF.getOrThrow(VegetationFeatures.PATCH_RED_MUSHROOM),
                ),
                PlacementUtils.inlinePlaced(
                    cF.getOrThrow(
                        VegetationFeatures.PATCH_BROWN_MUSHROOM
                    )
                )
            )
        )
        c.registerConfiguredFeature<RootSystemConfiguration, Feature<RootSystemConfiguration>>(
            DuskConfiguredFeatures.MUSHROOM_CAVE_ROOTS,
            Feature.ROOT_SYSTEM,
            RootSystemConfiguration(
                PlacementUtils.inlinePlaced(
                    cF.getOrThrow(DuskConfiguredFeatures.CAVE_GLOW_LICHEN_EXTRA),
                ),
                2,
                2,
                BlockTags.AZALEA_ROOT_REPLACEABLE,
                BlockStateProvider.simple(Blocks.ROOTED_DIRT),
                20,
                100,
                3,
                4,
                BlockStateProvider.simple(Blocks.HANGING_ROOTS),
                20,
                60,
                BlockPredicate.allOf(
                    BlockPredicate.anyOf(
                        BlockPredicate.matchesBlocks(
                            listOf(
                                Blocks.AIR,
                                Blocks.CAVE_AIR,
                                Blocks.VOID_AIR
                            )
                        ), BlockPredicate.matchesTag(BlockTags.REPLACEABLE_BY_TREES)
                    ), BlockPredicate.matchesTag(
                        Direction.DOWN.unitVec3i, DuskBlockTags.MUSHROOM_ROOT_PLACEABLE
                    )
                )
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.ICE_CAVE_PILLAR,
            ReefFeatures.LARGE_CAVE_PILLAR,
            LargeCavePillarFeatureConfig(
                40,
                UniformInt.of(3, 19),
                UniformFloat.of(0.4f, 2.0f),
                0.33f,
                UniformFloat.of(0.3f, 0.9f),
                UniformFloat.of(0.4f, 1.0f),
                UniformFloat.of(0.0f, 2.0f),
                4,
                0.6f,
                BlockStateProvider.simple(Blocks.BLUE_ICE),
                blockTags.getOrThrow(DuskBlockTags.CAVE_PILLAR_PLACEABLE)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.ICE_SPIKE,
            ReefFeatures.SPIKE,
            SpikeFeatureConfig(
                60, 10, 30,
                BlockStateProvider.simple(Blocks.PACKED_ICE),
                blockTags.getOrThrow(DuskBlockTags.ICE_SPIKE_PLACEABLE_BLOCKS)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.INVERTED_ICE_SPIKE,
            ReefFeatures.INVERTED_SPIKE,
            SpikeFeatureConfig(
                60, 10, 30,
                BlockStateProvider.simple(Blocks.PACKED_ICE),
                blockTags.getOrThrow(DuskBlockTags.ICE_SPIKE_PLACEABLE_BLOCKS)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.BLUE_ICE_SPIKE,
            ReefFeatures.SPIKE,
            SpikeFeatureConfig(
                5, 10, 30,
                BlockStateProvider.simple(Blocks.BLUE_ICE),
                blockTags.getOrThrow(DuskBlockTags.ICE_SPIKE_PLACEABLE_BLOCKS)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.INVERTED_BLUE_ICE_SPIKE,
            ReefFeatures.INVERTED_SPIKE,
            SpikeFeatureConfig(
                5, 10, 30,
                BlockStateProvider.simple(Blocks.BLUE_ICE),
                blockTags.getOrThrow(DuskBlockTags.ICE_SPIKE_PLACEABLE_BLOCKS)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.ICE_SPIKE_FLOOR,
            Feature.RANDOM_SELECTOR,
            RandomFeatureConfiguration(
                listOf(
                    WeightedPlacedFeature(
                        PlacementUtils.inlinePlaced(cF.getOrThrow(DuskConfiguredFeatures.BLUE_ICE_SPIKE)),
                        0.075f
                    )
                ),
                PlacementUtils.inlinePlaced(cF.getOrThrow(DuskConfiguredFeatures.ICE_SPIKE))
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.ICE_SPIKE_CEILING,
            Feature.RANDOM_SELECTOR,
            RandomFeatureConfiguration(
                listOf(
                    WeightedPlacedFeature(
                        PlacementUtils.inlinePlaced(cF.getOrThrow(DuskConfiguredFeatures.INVERTED_BLUE_ICE_SPIKE)),
                        0.075f
                    )
                ),
                PlacementUtils.inlinePlaced(cF.getOrThrow(DuskConfiguredFeatures.INVERTED_ICE_SPIKE))
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.ORE_ICE,
            Feature.ORE,
            OreConfiguration(TagMatchTest(DuskBlockTags.ICE_ORE_REPLACEABLE), Blocks.ICE.defaultBlockState(), 64)
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.ORE_BLUE_ICE,
            Feature.ORE,
            OreConfiguration(
                TagMatchTest(DuskBlockTags.ICE_ORE_REPLACEABLE),
                Blocks.BLUE_ICE.defaultBlockState(),
                64
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.ICE_CAVE_FOSSIL,
            ReefFeatures.FEATURE_LIST,
            ListFeatureConfig(
                10, listOf(
                    PlacementUtils.inlinePlaced(cF.getOrThrow(DuskConfiguredFeatures.ORE_ICE)),
                    PlacementUtils.inlinePlaced(cF.getOrThrow(CaveFeatures.FOSSIL_DIAMONDS))
                )
            )
        )
        c.registerConfiguredFeature<RandomPatchConfiguration, Feature<RandomPatchConfiguration>>(
            DuskConfiguredFeatures.SAND_CAVE_CACTUS,
            Feature.RANDOM_PATCH,
            FeatureUtils.simpleRandomPatchConfiguration(
                10, PlacementUtils.inlinePlaced<BlockColumnConfiguration, Feature<BlockColumnConfiguration>>(
                    Feature.BLOCK_COLUMN, BlockColumnConfiguration(
                        listOf<BlockColumnConfiguration.Layer>(
                            BlockColumnConfiguration.layer(
                                BiasedToBottomInt.of(1, 7),
                                BlockStateProvider.simple(Blocks.CACTUS)
                            ), BlockColumnConfiguration.layer(
                                WeightedListInt(
                                    WeightedList.builder<IntProvider>()
                                        .add(ConstantInt.of(0), 3)
                                        .add(ConstantInt.of(1), 1)
                                        .build()
                                ),
                                BlockStateProvider.simple(Blocks.CACTUS_FLOWER)
                            )
                        ), Direction.UP, BlockPredicate.ONLY_IN_AIR_PREDICATE, false
                    ),
                    BlockPredicateFilter.forPredicate(
                        BlockPredicate.allOf(
                            BlockPredicate.ONLY_IN_AIR_PREDICATE,
                            BlockPredicate.wouldSurvive(Blocks.CACTUS.defaultBlockState(), BlockPos.ZERO)
                        )
                    )
                )
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.ORE_SAND,
            Feature.ORE,
            OreConfiguration(TagMatchTest(DuskBlockTags.CAVE_PILLAR_PLACEABLE), Blocks.SAND.defaultBlockState(), 64)
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.SAND_CAVE_PILLAR,
            ReefFeatures.LARGE_CAVE_PILLAR,
            LargeCavePillarFeatureConfig(
                30,
                UniformInt.of(3, 19),
                UniformFloat.of(0.4f, 2.0f),
                0.33f,
                UniformFloat.of(0.3f, 0.9f),
                UniformFloat.of(0.4f, 1.0f),
                UniformFloat.of(0.0f, 0.3f),
                4,
                0.6f,
                BlockStateProvider.simple(Blocks.SANDSTONE),
                blockTags.getOrThrow(DuskBlockTags.CAVE_PILLAR_PLACEABLE)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.SAND_SPIKES,
            Feature.RANDOM_PATCH,
            FeatureUtils.simpleRandomPatchConfiguration(
                10, PlacementUtils.inlinePlaced(
                    Feature.BLOCK_COLUMN,
                    BlockColumnConfiguration(
                        listOf(
                            BlockColumnConfiguration.layer(
                                UniformInt.of(1, 7),
                                BlockStateProvider.simple(Blocks.SANDSTONE.defaultBlockState())
                            ),
                            BlockColumnConfiguration.layer(
                                UniformInt.of(2, 5),
                                BlockStateProvider.simple(Blocks.SANDSTONE_WALL.defaultBlockState())
                            )
                        ),
                        Direction.UP, BlockPredicate.ONLY_IN_AIR_PREDICATE, false
                    ),
                    BlockPredicateFilter.forPredicate(
                        BlockPredicate.hasSturdyFace(Direction.UP)
                    )
                )
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.SAND_SPIKES_ROOF,
            Feature.RANDOM_PATCH,
            FeatureUtils.simpleRandomPatchConfiguration(
                10, PlacementUtils.inlinePlaced(
                    Feature.BLOCK_COLUMN,
                    BlockColumnConfiguration(
                        listOf(
                            BlockColumnConfiguration.layer(
                                UniformInt.of(1, 7),
                                BlockStateProvider.simple(Blocks.SANDSTONE.defaultBlockState())
                            ),
                            BlockColumnConfiguration.layer(
                                UniformInt.of(2, 5),
                                BlockStateProvider.simple(Blocks.SANDSTONE_WALL.defaultBlockState())
                            )
                        ),
                        Direction.DOWN, BlockPredicate.ONLY_IN_AIR_PREDICATE, false
                    ),
                    BlockPredicateFilter.forPredicate(
                        BlockPredicate.hasSturdyFace(Direction.DOWN)
                    )
                )
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.ORE_RED_SAND,
            Feature.ORE,
            OreConfiguration(
                TagMatchTest(DuskBlockTags.CAVE_PILLAR_PLACEABLE),
                Blocks.RED_SAND.defaultBlockState(),
                64
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.RED_SAND_CAVE_PILLAR,
            ReefFeatures.LARGE_CAVE_PILLAR,
            LargeCavePillarFeatureConfig(
                30,
                UniformInt.of(3, 19),
                UniformFloat.of(0.4f, 2.0f),
                0.33f,
                UniformFloat.of(0.3f, 0.9f),
                UniformFloat.of(0.4f, 1.0f),
                UniformFloat.of(0.0f, 0.3f),
                4,
                0.6f,
                BlockStateProvider.simple(Blocks.RED_SANDSTONE),
                blockTags.getOrThrow(DuskBlockTags.CAVE_PILLAR_PLACEABLE)
            )
        )

        c.registerConfiguredFeature(
            DuskConfiguredFeatures.RED_SAND_SPIKES,
            Feature.RANDOM_PATCH,
            FeatureUtils.simpleRandomPatchConfiguration(
                10, PlacementUtils.inlinePlaced(
                    Feature.BLOCK_COLUMN,
                    BlockColumnConfiguration(
                        listOf(
                            BlockColumnConfiguration.layer(
                                UniformInt.of(1, 7),
                                BlockStateProvider.simple(Blocks.RED_SANDSTONE.defaultBlockState())
                            ),
                            BlockColumnConfiguration.layer(
                                UniformInt.of(2, 5),
                                BlockStateProvider.simple(Blocks.RED_SANDSTONE_WALL.defaultBlockState())
                            )
                        ),
                        Direction.UP, BlockPredicate.ONLY_IN_AIR_PREDICATE, false
                    ),
                    BlockPredicateFilter.forPredicate(
                        BlockPredicate.hasSturdyFace(Direction.UP)
                    )
                )
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.RED_SAND_SPIKES_ROOF,
            Feature.RANDOM_PATCH,
            FeatureUtils.simpleRandomPatchConfiguration(
                10, PlacementUtils.inlinePlaced(
                    Feature.BLOCK_COLUMN,
                    BlockColumnConfiguration(
                        listOf(
                            BlockColumnConfiguration.layer(
                                UniformInt.of(1, 7),
                                BlockStateProvider.simple(Blocks.RED_SANDSTONE.defaultBlockState())
                            ),
                            BlockColumnConfiguration.layer(
                                UniformInt.of(2, 5),
                                BlockStateProvider.simple(Blocks.RED_SANDSTONE_WALL.defaultBlockState())
                            )
                        ),
                        Direction.DOWN, BlockPredicate.ONLY_IN_AIR_PREDICATE, false
                    ),
                    BlockPredicateFilter.forPredicate(
                        BlockPredicate.hasSturdyFace(Direction.DOWN)
                    )
                )
            )
        )
        c.registerConfiguredFeature<RandomPatchConfiguration, Feature<RandomPatchConfiguration>>(
            DuskConfiguredFeatures.SAND_CAVE_SEAGRASS, Feature.RANDOM_PATCH, RandomPatchConfiguration(
                64, 7, 3, PlacementUtils.filtered(
                    Feature.SIMPLE_BLOCK,
                    SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.SEAGRASS)),
                    BlockPredicate.allOf(
                        BlockPredicate.matchesFluids(Fluids.WATER),
                        BlockPredicate.wouldSurvive(Blocks.SEAGRASS.defaultBlockState(), BlockPos.ZERO)
                    )
                )
            )
        )
        c.registerConfiguredFeature<RandomPatchConfiguration, Feature<RandomPatchConfiguration>>(
            DuskConfiguredFeatures.SAND_CAVE_PICKLES, Feature.RANDOM_PATCH, RandomPatchConfiguration(
                64, 7, 3, PlacementUtils.filtered(
                    Feature.SIMPLE_BLOCK,
                    SimpleBlockConfiguration(
                        NoiseThresholdProvider(
                            6789L,
                            NormalNoise.NoiseParameters(0, 1.0),
                            0.005f,
                            -0.8f,
                            0.33333334f,
                            Blocks.SEAGRASS.defaultBlockState(),
                            listOf(
                                Blocks.SEA_PICKLE.defaultBlockState(),
                                Blocks.SEA_PICKLE.defaultBlockState().setValue(SeaPickleBlock.PICKLES, 2)
                            ),
                            listOf(
                                Blocks.SEA_PICKLE.defaultBlockState(),
                                Blocks.SEA_PICKLE.defaultBlockState().setValue(SeaPickleBlock.PICKLES, 2),
                                Blocks.SEA_PICKLE.defaultBlockState().setValue(SeaPickleBlock.PICKLES, 3),
                                Blocks.SEA_PICKLE.defaultBlockState().setValue(SeaPickleBlock.PICKLES, 4)
                            )
                        )
                    ),
                    BlockPredicate.allOf(
                        BlockPredicate.matchesFluids(Fluids.WATER),
                        BlockPredicate.wouldSurvive(Blocks.SEAGRASS.defaultBlockState(), BlockPos.ZERO)
                    )
                )
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.ORE_COBBLESTONE,
            Feature.RANDOM_BOOLEAN_SELECTOR,
            RandomBooleanFeatureConfiguration(
                PlacementUtils.inlinePlaced(
                    Feature.ORE,
                    OreConfiguration(
                        TagMatchTest(DuskBlockTags.CAVE_PILLAR_PLACEABLE),
                        Blocks.COBBLESTONE.defaultBlockState(),
                        64
                    ),
                ),
                PlacementUtils.inlinePlaced(
                    Feature.ORE,
                    OreConfiguration(
                        TagMatchTest(DuskBlockTags.CAVE_PILLAR_PLACEABLE),
                        Blocks.COBBLED_DEEPSLATE.defaultBlockState(),
                        64
                    ),
                )
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.COBBLESTONE_CAVE_PILLAR,
            ReefFeatures.LARGE_CAVE_PILLAR,
            LargeCavePillarFeatureConfig(
                30,
                UniformInt.of(3, 19),
                UniformFloat.of(0.4f, 2.0f),
                0.33f,
                UniformFloat.of(0.3f, 0.9f),
                UniformFloat.of(0.4f, 1.0f),
                UniformFloat.of(0.0f, 0.3f),
                4,
                0.6f,
                BlockStateProvider.simple(Blocks.COBBLESTONE),
                blockTags.getOrThrow(DuskBlockTags.CAVE_PILLAR_PLACEABLE)
            )
        )

        c.registerConfiguredFeature(
            DuskConfiguredFeatures.COBBLESTONE_SPIKES,
            Feature.RANDOM_PATCH,
            FeatureUtils.simpleRandomPatchConfiguration(
                10, PlacementUtils.inlinePlaced(
                    Feature.BLOCK_COLUMN,
                    BlockColumnConfiguration(
                        listOf(
                            BlockColumnConfiguration.layer(
                                UniformInt.of(1, 7),
                                BlockStateProvider.simple(Blocks.COBBLESTONE.defaultBlockState())
                            ),
                            BlockColumnConfiguration.layer(
                                UniformInt.of(2, 5),
                                BlockStateProvider.simple(Blocks.COBBLESTONE_WALL.defaultBlockState())
                            )
                        ),
                        Direction.UP, BlockPredicate.ONLY_IN_AIR_PREDICATE, false
                    ),
                    BlockPredicateFilter.forPredicate(
                        BlockPredicate.hasSturdyFace(Direction.UP)
                    )
                )
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.COBBLESTONE_SPIKES_ROOF,
            Feature.RANDOM_PATCH,
            FeatureUtils.simpleRandomPatchConfiguration(
                10, PlacementUtils.inlinePlaced(
                    Feature.BLOCK_COLUMN,
                    BlockColumnConfiguration(
                        listOf(
                            BlockColumnConfiguration.layer(
                                UniformInt.of(1, 7),
                                BlockStateProvider.simple(Blocks.COBBLESTONE.defaultBlockState())
                            ),
                            BlockColumnConfiguration.layer(
                                UniformInt.of(2, 5),
                                BlockStateProvider.simple(Blocks.COBBLESTONE_WALL.defaultBlockState())
                            )
                        ),
                        Direction.DOWN, BlockPredicate.ONLY_IN_AIR_PREDICATE, false
                    ),
                    BlockPredicateFilter.forPredicate(
                        BlockPredicate.hasSturdyFace(Direction.DOWN)
                    )
                )
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.COBBLED_DEEPSLATE_CAVE_PILLAR,
            ReefFeatures.LARGE_CAVE_PILLAR,
            LargeCavePillarFeatureConfig(
                30,
                UniformInt.of(3, 19),
                UniformFloat.of(0.4f, 2.0f),
                0.33f,
                UniformFloat.of(0.3f, 0.9f),
                UniformFloat.of(0.4f, 1.0f),
                UniformFloat.of(0.0f, 0.3f),
                4,
                0.6f,
                BlockStateProvider.simple(Blocks.COBBLED_DEEPSLATE),
                blockTags.getOrThrow(DuskBlockTags.CAVE_PILLAR_PLACEABLE)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.COBBLED_DEEPSLATE_SPIKES,
            Feature.RANDOM_PATCH,
            FeatureUtils.simpleRandomPatchConfiguration(
                10, PlacementUtils.inlinePlaced(
                    Feature.BLOCK_COLUMN,
                    BlockColumnConfiguration(
                        listOf(
                            BlockColumnConfiguration.layer(
                                UniformInt.of(1, 7),
                                BlockStateProvider.simple(Blocks.COBBLED_DEEPSLATE.defaultBlockState())
                            ),
                            BlockColumnConfiguration.layer(
                                UniformInt.of(2, 5),
                                BlockStateProvider.simple(Blocks.COBBLED_DEEPSLATE_WALL.defaultBlockState())
                            )
                        ),
                        Direction.UP, BlockPredicate.ONLY_IN_AIR_PREDICATE, false
                    ),
                    BlockPredicateFilter.forPredicate(
                        BlockPredicate.hasSturdyFace(Direction.UP)
                    )
                )
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.COBBLED_DEEPSLATE_SPIKES_ROOF,
            Feature.RANDOM_PATCH,
            FeatureUtils.simpleRandomPatchConfiguration(
                10, PlacementUtils.inlinePlaced(
                    Feature.BLOCK_COLUMN,
                    BlockColumnConfiguration(
                        listOf(
                            BlockColumnConfiguration.layer(
                                UniformInt.of(1, 7),
                                BlockStateProvider.simple(Blocks.COBBLED_DEEPSLATE.defaultBlockState())
                            ),
                            BlockColumnConfiguration.layer(
                                UniformInt.of(2, 5),
                                BlockStateProvider.simple(Blocks.COBBLED_DEEPSLATE_WALL.defaultBlockState())
                            )
                        ),
                        Direction.DOWN, BlockPredicate.ONLY_IN_AIR_PREDICATE, false
                    ),
                    BlockPredicateFilter.forPredicate(
                        BlockPredicate.hasSturdyFace(Direction.DOWN)
                    )
                )
            )
        )




        c.registerConfiguredFeature(
            DuskConfiguredFeatures.TEST_CAVE_PILLAR,
            ReefFeatures.LARGE_CAVE_PILLAR,
            LargeCavePillarFeatureConfig(
                30,
                UniformInt.of(3, 19),
                UniformFloat.of(0.4f, 2.0f),
                0.33f,
                UniformFloat.of(0.3f, 0.9f),
                UniformFloat.of(0.4f, 1.0f),
                UniformFloat.of(0.0f, 0.3f),
                4,
                0.6f,
                BlockStateProvider.simple(Blocks.DIAMOND_BLOCK),
                blockTags.getOrThrow(BlockTags.BASE_STONE_OVERWORLD)
            )
        )

//Structure Piece features
        val procDesertWell = procLists.getOrThrow(ProcessorLists.EMPTY)
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.DESERT_WELL,
            ReefFeatures.STRUCTURE_PIECE,
            StructurePieceFeatureConfig(
                id("feature/desert_well"),
                procDesertWell,
                8,
                Heightmap.Types.OCEAN_FLOOR_WG
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.RED_DESERT_WELL,
            ReefFeatures.STRUCTURE_PIECE,
            StructurePieceFeatureConfig(
                id("feature/red_desert_well"),
                procDesertWell,
                8,
                Heightmap.Types.OCEAN_FLOOR_WG,
            )
        )

        //Monster Room features
        val defaultMonstersRoom = listOf(EntityType.SKELETON, EntityType.ZOMBIE, EntityType.ZOMBIE, EntityType.SPIDER)
        val lushMonstersRoom = listOf(EntityType.BOGGED, EntityType.ZOMBIE, EntityType.ZOMBIE, EntityType.SPIDER)
        val frozenMonstersRoom = listOf(EntityType.STRAY, EntityType.ZOMBIE, EntityType.ZOMBIE, EntityType.SPIDER)
        val sandMonstersRoom = listOf(EntityType.SKELETON, EntityType.HUSK, EntityType.HUSK, EntityType.SPIDER)

        c.registerConfiguredFeature(
            DuskConfiguredFeatures.DEEP_MONSTER_ROOM,
            ReefFeatures.MONSTER_ROOM,
            ReefMonsterRoomFeatureConfig(
                BlockStateProvider.simple(Blocks.COBBLED_DEEPSLATE),
                BlockStateProvider.simple(Blocks.TUFF),
                defaultMonstersRoom,
                BuiltInLootTables.SIMPLE_DUNGEON.identifier()
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.LUSH_MONSTER_ROOM,
            ReefFeatures.MONSTER_ROOM,
            ReefMonsterRoomFeatureConfig(
                BlockStateProvider.simple(Blocks.MOSSY_COBBLESTONE),
                BlockStateProvider.simple(Blocks.MUD),
                lushMonstersRoom,
                BuiltInLootTables.SIMPLE_DUNGEON.identifier()
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.DEEP_LUSH_MONSTER_ROOM,
            ReefFeatures.MONSTER_ROOM,
            ReefMonsterRoomFeatureConfig(
                BlockStateProvider.simple(Blocks.COBBLED_DEEPSLATE),
                BlockStateProvider.simple(Blocks.MUD),
                lushMonstersRoom,
                BuiltInLootTables.SIMPLE_DUNGEON.identifier()
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.FROZEN_MONSTER_ROOM,
            ReefFeatures.MONSTER_ROOM,
            ReefMonsterRoomFeatureConfig(
                BlockStateProvider.simple(Blocks.COBBLESTONE),
                BlockStateProvider.simple(Blocks.PACKED_ICE),
                frozenMonstersRoom,
                BuiltInLootTables.SIMPLE_DUNGEON.identifier()
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.DEEP_FROZEN_MONSTER_ROOM,
            ReefFeatures.MONSTER_ROOM,
            ReefMonsterRoomFeatureConfig(
                BlockStateProvider.simple(Blocks.COBBLED_DEEPSLATE),
                BlockStateProvider.simple(Blocks.BLUE_ICE),
                frozenMonstersRoom,
                BuiltInLootTables.SIMPLE_DUNGEON.identifier()
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.SAND_MONSTER_ROOM,
            ReefFeatures.MONSTER_ROOM,
            ReefMonsterRoomFeatureConfig(
                BlockStateProvider.simple(Blocks.SANDSTONE),
                BlockStateProvider.simple(Blocks.SAND),
                sandMonstersRoom,
                BuiltInLootTables.SIMPLE_DUNGEON.identifier()
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.RED_SAND_MONSTER_ROOM,
            ReefFeatures.MONSTER_ROOM,
            ReefMonsterRoomFeatureConfig(
                BlockStateProvider.simple(Blocks.RED_SANDSTONE),
                BlockStateProvider.simple(Blocks.RED_SAND),
                sandMonstersRoom,
                BuiltInLootTables.SIMPLE_DUNGEON.identifier()
            )
        )
    }

    private fun HolderGetter<PlacedFeature>.wp(
        feature: ResourceKey<PlacedFeature>,
        chance: Float
    ): WeightedPlacedFeature = WeightedPlacedFeature(this.getOrThrow(feature), chance)


    fun HolderGetter<ConfiguredFeature<*, *>>.inline(feature: ResourceKey<ConfiguredFeature<*, *>>): Holder<PlacedFeature> =
        PlacementUtils.inlinePlaced(this.getOrThrow(feature))

    @Suppress("SameParameterValue")
    private fun flowerbed(block: Block): WeightedList.Builder<BlockState> = segmentedBlock(
        block,
        1, 4,
        FlowerBedBlock.AMOUNT,
        FlowerBedBlock.FACING
    )

    @Suppress("SameParameterValue")
    private fun segmentedBlock(
        block: Block, min: Int, max: Int, int: IntegerProperty, enum: EnumProperty<Direction>,
    ): WeightedList.Builder<BlockState> {
        val builder = WeightedList.builder<BlockState>()

        for (k in min..max) {
            for (direction in Direction.Plane.HORIZONTAL) {
                builder.add(
                    block.defaultBlockState()
                        .setValue(int, k)
                        .setValue(enum, direction),
                    1
                )
            }
        }

        return builder
    }

    fun <FC : FeatureConfiguration, F : Feature<FC>> BootstrapContext<ConfiguredFeature<*, *>>.registerConfiguredFeature(
        registryKey: ResourceKey<ConfiguredFeature<*, *>>,
        feature: F,
        featureConfig: FC,
    ): Any = this.register(registryKey, ConfiguredFeature(feature, featureConfig))

    @Suppress("unused")
    private fun BootstrapContext<ConfiguredFeature<*, *>>.registerConfiguredFeature(
        registryKey: ResourceKey<ConfiguredFeature<*, *>>, feature: Feature<NoneFeatureConfiguration>,
    ) = this.registerConfiguredFeature(registryKey, feature, FeatureConfiguration.NONE)

}