package org.teamvoided.dusks_biomes.data.gen.world.gen

import net.minecraft.block.*
import net.minecraft.entity.EntityType
import net.minecraft.fluid.Fluids
import net.minecraft.loot.LootTables
import net.minecraft.registry.Registerable
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.entry.RegistryEntryList
import net.minecraft.registry.tag.BlockTags
import net.minecraft.state.property.EnumProperty
import net.minecraft.state.property.IntProperty
import net.minecraft.structure.processor.StructureProcessorLists
import net.minecraft.structure.rule.TagMatchRuleTest
import net.minecraft.util.collection.Pool
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.floatprovider.UniformFloatProvider
import net.minecraft.util.math.intprovider.BiasedToBottomIntProvider
import net.minecraft.util.math.intprovider.ConstantIntProvider
import net.minecraft.util.math.intprovider.UniformIntProvider
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler.NoiseParameters
import net.minecraft.world.Heightmap
import net.minecraft.world.gen.blockpredicate.BlockPredicate
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize
import net.minecraft.world.gen.foliage.RandomSpreadFoliagePlacer
import net.minecraft.world.gen.placementmodifier.BlockFilterPlacementModifier
import net.minecraft.world.gen.placementmodifier.PlacementModifier
import net.minecraft.world.gen.root.AboveRootPlacement
import net.minecraft.world.gen.root.MangroveRootPlacement
import net.minecraft.world.gen.root.MangroveRootPlacer
import net.minecraft.world.gen.stateprovider.BlockStateProvider
import net.minecraft.world.gen.stateprovider.NoiseThresholdBlockStateProvider
import net.minecraft.world.gen.stateprovider.RandomizedIntBlockStateProvider
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider
import net.minecraft.world.gen.treedecorator.AttachedToLeavesTreeDecorator
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator
import net.minecraft.world.gen.treedecorator.LeavesVineTreeDecorator
import net.minecraft.world.gen.trunk.UpwardsBranchingTrunkPlacer
import org.teamvoided.dusks_biomes.DusksBiomesMod.id
import org.teamvoided.dusks_biomes.data.gen.world.gen.configured_feature_creator.TreeConfiguredCreator.trees
import org.teamvoided.dusks_biomes.data.tags.DuskBlockTags
import org.teamvoided.dusks_biomes.data.world.gen.DuskConfiguredFeatures
import org.teamvoided.dusks_biomes.data.world.gen.DuskPlacedFeatures
import org.teamvoided.reef.init.ReefFeatures
import org.teamvoided.reef.world.gen.configured_feature.config.*
import java.util.*

@Suppress("DEPRECATION")
object ConfiguredFeatureCreator {
    fun bootstrap(c: Registerable<ConfiguredFeature<*, *>>) {
        val blockTags = c.getRegistryLookup(RegistryKeys.BLOCK)
        val configuredFeatures = c.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE)
        val placedFeatures = c.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val procLists = c.getRegistryLookup(RegistryKeys.PROCESSOR_LIST)

        c.trees()

        c.registerConfiguredFeature(
            DuskConfiguredFeatures.COBBLESTONE_ROCK, Feature.FOREST_ROCK,
            SingleStateFeatureConfig(Blocks.COBBLESTONE.defaultState)
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.MANGROVE_FROZEN_CHECKED, Feature.TREE,
            TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.MANGROVE_LOG),
                UpwardsBranchingTrunkPlacer(
                    2,
                    1,
                    4,
                    UniformIntProvider.create(1, 4),
                    0.5f,
                    UniformIntProvider.create(0, 1),
                    blockTags.getOrThrow(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)
                ),
                BlockStateProvider.of(Blocks.MANGROVE_LEAVES),
                RandomSpreadFoliagePlacer(
                    ConstantIntProvider.create(3),
                    ConstantIntProvider.create(0),
                    ConstantIntProvider.create(2),
                    70
                ),
                Optional.of(
                    MangroveRootPlacer(
                        UniformIntProvider.create(1, 3),
                        BlockStateProvider.of(Blocks.MANGROVE_ROOTS),
                        Optional.of(AboveRootPlacement(BlockStateProvider.of(Blocks.SNOW), 0.5f)),
                        MangroveRootPlacement(
                            blockTags.getOrThrow(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH),
                            RegistryEntryList.of(
                                { it.registryEntry },
                                *arrayOf(Blocks.MUD, Blocks.MUDDY_MANGROVE_ROOTS)
                            ),
                            BlockStateProvider.of(Blocks.MUDDY_MANGROVE_ROOTS),
                            8, 15, 0.2f
                        )
                    )
                ),
                TwoLayersFeatureSize(2, 0, 2)
            ).decorators(
                listOf(
                    LeavesVineTreeDecorator(0.125f), AttachedToLeavesTreeDecorator(
                        0.14f, 1, 0, RandomizedIntBlockStateProvider(
                            BlockStateProvider.of(
                                Blocks.MANGROVE_PROPAGULE.defaultState.with(PropaguleBlock.HANGING, true)
                            ), PropaguleBlock.AGE, UniformIntProvider.create(0, 4)
                        ), 2, listOf(Direction.DOWN)
                    ), BeehiveTreeDecorator(0.01F)
                )
            ).ignoreVines().build()
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.TALL_MANGROVE_FROZEN_CHECKED, Feature.TREE,
            TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.MANGROVE_LOG),
                UpwardsBranchingTrunkPlacer(
                    4, 1, 9,
                    UniformIntProvider.create(1, 6),
                    0.5f,
                    UniformIntProvider.create(0, 1),
                    blockTags.getOrThrow(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)
                ),
                BlockStateProvider.of(Blocks.MANGROVE_LEAVES),
                RandomSpreadFoliagePlacer(
                    ConstantIntProvider.create(3),
                    ConstantIntProvider.create(0),
                    ConstantIntProvider.create(2),
                    70
                ),
                Optional.of(
                    MangroveRootPlacer(
                        UniformIntProvider.create(3, 7),
                        BlockStateProvider.of(Blocks.MANGROVE_ROOTS),
                        Optional.of(
                            AboveRootPlacement(BlockStateProvider.of(Blocks.SNOW), 0.5f)
                        ),
                        MangroveRootPlacement(
                            blockTags.getOrThrow(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH),
                            RegistryEntryList.of(
                                { it.registryEntry },
                                *arrayOf(Blocks.MUD, Blocks.MUDDY_MANGROVE_ROOTS)
                            ),
                            BlockStateProvider.of(Blocks.MUDDY_MANGROVE_ROOTS),
                            8, 15, 0.2f
                        )
                    )
                ),
                TwoLayersFeatureSize(3, 0, 2)
            ).decorators(
                listOf(
                    LeavesVineTreeDecorator(0.125f), AttachedToLeavesTreeDecorator(
                        0.14f, 1, 0, RandomizedIntBlockStateProvider(
                            BlockStateProvider.of(
                                Blocks.MANGROVE_PROPAGULE.defaultState.with(PropaguleBlock.HANGING, true)
                            ), PropaguleBlock.AGE, UniformIntProvider.create(0, 4)
                        ), 2, listOf(Direction.DOWN)
                    ), BeehiveTreeDecorator(0.01F)
                )
            ).ignoreVines().build()
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.MANGROVE_FROZEN_VEGETATION,
            Feature.RANDOM_SELECTOR,
            RandomFeatureConfig(
                listOf(
                    RandomFeatureEntry(
                        placedFeatures.getOrThrow(DuskPlacedFeatures.TALL_MANGROVE_FROZEN_CHECKED), 0.85f
                    )
                ),
                placedFeatures.getOrThrow(DuskPlacedFeatures.MANGROVE_FROZEN_CHECKED)
            )
        )
//DARK OAK TRUNK DOESN'T REPLACE WATER AAAAAAAAAAAAAAA
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.TREES_OAK_BIRCH_ACACIA,
            Feature.RANDOM_SELECTOR,
            RandomFeatureConfig(
                listOf(
                    RandomFeatureEntry(placedFeatures.getOrThrow(TreePlacedFeatures.BIRCH_BEES_0002), 0.4f),
                    RandomFeatureEntry(
                        placedFeatures.getOrThrow(TreePlacedFeatures.FANCY_OAK_BEES_0002),
                        0.2f
                    ),
                    RandomFeatureEntry(placedFeatures.getOrThrow(TreePlacedFeatures.ACACIA_CHECKED), 0.15f)
                ), placedFeatures.getOrThrow(TreePlacedFeatures.OAK_BEES_0002)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.TREES_OAK_BIRCH_SPRUCE,
            Feature.RANDOM_SELECTOR,
            RandomFeatureConfig(
                listOf(
                    RandomFeatureEntry(placedFeatures.getOrThrow(TreePlacedFeatures.BIRCH_BEES_0002), 0.4f),
                    RandomFeatureEntry(
                        placedFeatures.getOrThrow(TreePlacedFeatures.FANCY_OAK_BEES_0002),
                        0.2f
                    ),
                    RandomFeatureEntry(placedFeatures.getOrThrow(TreePlacedFeatures.SPRUCE_CHECKED), 0.15f)
                ), placedFeatures.getOrThrow(TreePlacedFeatures.OAK_BEES_0002)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.FLOWER_SNOWY_CHERRY, Feature.FLOWER, RandomPatchFeatureConfig(
                96, 6, 2, PlacedFeatures.createEntry(
                    Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(
                        WeightedBlockStateProvider(flowerbed(Blocks.PINK_PETALS).add(Blocks.SNOW.defaultState, 8))
                    )
                )
            )
        )

        c.registerConfiguredFeature<RandomPatchFeatureConfig, Feature<RandomPatchFeatureConfig>>(
            DuskConfiguredFeatures.CAVE_GLOW_LICHEN_EXTRA,
            Feature.RANDOM_PATCH,
            ConfiguredFeatures.createRandomPatchFeatureConfig(
                20, PlacedFeatures.createEntry(
                    Feature.MULTIFACE_GROWTH,
                    MultifaceGrowthFeatureConfig(
                        Blocks.GLOW_LICHEN as MultifaceGrowthBlock,
                        20,
                        false,
                        true,
                        true,
                        0.75f,
                        RegistryEntryList.of<Block, Block>(
                            { it.registryEntry },
                            *arrayOf<Block>(
                                Blocks.STONE,
                                Blocks.ANDESITE,
                                Blocks.DIORITE,
                                Blocks.GRANITE,
                                Blocks.DRIPSTONE_BLOCK,
                                Blocks.CALCITE,
                                Blocks.TUFF,
                                Blocks.DEEPSLATE
                            )
                        )
                    ),
                    *arrayOfNulls<PlacementModifier>(0)
                )
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.ORE_COARSE_DIRT,
            Feature.ORE,
            OreFeatureConfig(TagMatchRuleTest(DuskBlockTags.CAVE_PILLAR_PLACEABLE), Blocks.COARSE_DIRT.defaultState, 64)
        )
        c.registerConfiguredFeature<RandomBooleanFeatureConfig, Feature<RandomBooleanFeatureConfig>>(
            DuskConfiguredFeatures.MUSHROOM_CAVE_MUSHROOMS,
            Feature.RANDOM_BOOLEAN_SELECTOR,
            RandomBooleanFeatureConfig(
                PlacedFeatures.createEntry(
                    configuredFeatures.getOrThrow(VegetationConfiguredFeatures.PATCH_RED_MUSHROOM),
                    *arrayOfNulls<PlacementModifier>(0)
                ),
                PlacedFeatures.createEntry(
                    configuredFeatures.getOrThrow(
                        VegetationConfiguredFeatures.PATCH_BROWN_MUSHROOM
                    ),
                    *arrayOfNulls<PlacementModifier>(0)
                )
            )
        )
        c.registerConfiguredFeature<RootSystemFeatureConfig, Feature<RootSystemFeatureConfig>>(
            DuskConfiguredFeatures.MUSHROOM_CAVE_ROOTS,
            Feature.ROOT_SYSTEM,
            RootSystemFeatureConfig(
                PlacedFeatures.createEntry(
                    configuredFeatures.getOrThrow(DuskConfiguredFeatures.CAVE_GLOW_LICHEN_EXTRA),
                    *arrayOfNulls<PlacementModifier>(0)
                ),
                2,
                2,
                BlockTags.AZALEA_ROOT_REPLACEABLE,
                BlockStateProvider.of(Blocks.ROOTED_DIRT),
                20,
                100,
                3,
                4,
                BlockStateProvider.of(Blocks.HANGING_ROOTS),
                20,
                60,
                BlockPredicate.bothOf(
                    BlockPredicate.eitherOf(
                        BlockPredicate.matchingBlocks(
                            listOf(
                                Blocks.AIR,
                                Blocks.CAVE_AIR,
                                Blocks.VOID_AIR
                            )
                        ), BlockPredicate.matchingBlockTag(BlockTags.REPLACEABLE_BY_TREES)
                    ), BlockPredicate.matchingBlockTag(
                        Direction.DOWN.vector, DuskBlockTags.MUSHROOM_ROOT_PLACEABLE
                    )
                )
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.ICE_CAVE_PILLAR,
            ReefFeatures.LARGE_CAVE_PILLAR,
            LargeCavePillarFeatureConfig(
                40,
                UniformIntProvider.create(3, 19),
                UniformFloatProvider.create(0.4f, 2.0f),
                0.33f,
                UniformFloatProvider.create(0.3f, 0.9f),
                UniformFloatProvider.create(0.4f, 1.0f),
                UniformFloatProvider.create(0.0f, 2.0f),
                4,
                0.6f,
                BlockStateProvider.of(Blocks.BLUE_ICE),
                blockTags.getOrThrow(DuskBlockTags.CAVE_PILLAR_PLACEABLE)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.ICE_SPIKE,
            ReefFeatures.SPIKE,
            SpikeFeatureConfig(
                60, 10, 30,
                BlockStateProvider.of(Blocks.PACKED_ICE),
                blockTags.getOrThrow(DuskBlockTags.ICE_SPIKE_PLACEABLE_BLOCKS)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.INVERTED_ICE_SPIKE,
            ReefFeatures.INVERTED_SPIKE,
            SpikeFeatureConfig(
                60, 10, 30,
                BlockStateProvider.of(Blocks.PACKED_ICE),
                blockTags.getOrThrow(DuskBlockTags.ICE_SPIKE_PLACEABLE_BLOCKS)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.BLUE_ICE_SPIKE,
            ReefFeatures.SPIKE,
            SpikeFeatureConfig(
                5, 10, 30,
                BlockStateProvider.of(Blocks.BLUE_ICE),
                blockTags.getOrThrow(DuskBlockTags.ICE_SPIKE_PLACEABLE_BLOCKS)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.INVERTED_BLUE_ICE_SPIKE,
            ReefFeatures.INVERTED_SPIKE,
            SpikeFeatureConfig(
                5, 10, 30,
                BlockStateProvider.of(Blocks.BLUE_ICE),
                blockTags.getOrThrow(DuskBlockTags.ICE_SPIKE_PLACEABLE_BLOCKS)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.ICE_SPIKE_FLOOR,
            Feature.RANDOM_SELECTOR,
            RandomFeatureConfig(
                listOf(
                    RandomFeatureEntry(
                        PlacedFeatures.createEntry(configuredFeatures.getOrThrow(DuskConfiguredFeatures.BLUE_ICE_SPIKE)),
                        0.075f
                    )
                ),
                PlacedFeatures.createEntry(configuredFeatures.getOrThrow(DuskConfiguredFeatures.ICE_SPIKE))
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.ICE_SPIKE_CEILING,
            Feature.RANDOM_SELECTOR,
            RandomFeatureConfig(
                listOf(
                    RandomFeatureEntry(
                        PlacedFeatures.createEntry(configuredFeatures.getOrThrow(DuskConfiguredFeatures.INVERTED_BLUE_ICE_SPIKE)),
                        0.075f
                    )
                ),
                PlacedFeatures.createEntry(configuredFeatures.getOrThrow(DuskConfiguredFeatures.INVERTED_ICE_SPIKE))
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.ORE_ICE,
            Feature.ORE,
            OreFeatureConfig(TagMatchRuleTest(DuskBlockTags.ICE_ORE_REPLACEABLE), Blocks.ICE.defaultState, 64)
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.ORE_BLUE_ICE,
            Feature.ORE,
            OreFeatureConfig(TagMatchRuleTest(DuskBlockTags.ICE_ORE_REPLACEABLE), Blocks.BLUE_ICE.defaultState, 64)
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.ICE_CAVE_FOSSIL,
            ReefFeatures.FEATURE_LIST,
            ListFeatureConfig(
                10, listOf(
                    PlacedFeatures.createEntry(configuredFeatures.getOrThrow(DuskConfiguredFeatures.ORE_ICE)),
                    PlacedFeatures.createEntry(configuredFeatures.getOrThrow(UndergroundConfiguredFeatures.FOSSIL_DIAMONDS))
                )
            )
        )
        c.registerConfiguredFeature<RandomPatchFeatureConfig, Feature<RandomPatchFeatureConfig>>(
            DuskConfiguredFeatures.SAND_CAVE_CACTUS,
            Feature.RANDOM_PATCH,
            ConfiguredFeatures.createRandomPatchFeatureConfig(
                10, PlacedFeatures.createEntry<BlockColumnFeatureConfig, Feature<BlockColumnFeatureConfig>>(
                    Feature.BLOCK_COLUMN,
                    BlockColumnFeatureConfig.create(
                        BiasedToBottomIntProvider.create(1, 7),
                        BlockStateProvider.of(Blocks.CACTUS)
                    ),
                    *arrayOf<PlacementModifier>(
                        BlockFilterPlacementModifier.of(
                            BlockPredicate.bothOf(
                                BlockPredicate.IS_AIR,
                                BlockPredicate.wouldSurvive(Blocks.CACTUS.defaultState, BlockPos.ORIGIN)
                            )
                        )
                    )
                )
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.ORE_SAND,
            Feature.ORE,
            OreFeatureConfig(TagMatchRuleTest(DuskBlockTags.CAVE_PILLAR_PLACEABLE), Blocks.SAND.defaultState, 64)
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.SAND_CAVE_PILLAR,
            ReefFeatures.LARGE_CAVE_PILLAR,
            LargeCavePillarFeatureConfig(
                30,
                UniformIntProvider.create(3, 19),
                UniformFloatProvider.create(0.4f, 2.0f),
                0.33f,
                UniformFloatProvider.create(0.3f, 0.9f),
                UniformFloatProvider.create(0.4f, 1.0f),
                UniformFloatProvider.create(0.0f, 0.3f),
                4,
                0.6f,
                BlockStateProvider.of(Blocks.SANDSTONE),
                blockTags.getOrThrow(DuskBlockTags.CAVE_PILLAR_PLACEABLE)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.SAND_SPIKES,
            Feature.RANDOM_PATCH,
            ConfiguredFeatures.createRandomPatchFeatureConfig(
                10, PlacedFeatures.createEntry(
                    Feature.BLOCK_COLUMN,
                    BlockColumnFeatureConfig(
                        listOf(
                            BlockColumnFeatureConfig.createLayer(
                                UniformIntProvider.create(1, 7),
                                BlockStateProvider.of(Blocks.SANDSTONE.defaultState)
                            ),
                            BlockColumnFeatureConfig.createLayer(
                                UniformIntProvider.create(2, 5),
                                BlockStateProvider.of(Blocks.SANDSTONE_WALL.defaultState)
                            )
                        ),
                        Direction.UP, BlockPredicate.IS_AIR, false
                    ),
                    BlockFilterPlacementModifier.of(
                        BlockPredicate.hasSturdyFace(Direction.UP)
                    )
                )
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.SAND_SPIKES_ROOF,
            Feature.RANDOM_PATCH,
            ConfiguredFeatures.createRandomPatchFeatureConfig(
                10, PlacedFeatures.createEntry(
                    Feature.BLOCK_COLUMN,
                    BlockColumnFeatureConfig(
                        listOf(
                            BlockColumnFeatureConfig.createLayer(
                                UniformIntProvider.create(1, 7),
                                BlockStateProvider.of(Blocks.SANDSTONE.defaultState)
                            ),
                            BlockColumnFeatureConfig.createLayer(
                                UniformIntProvider.create(2, 5),
                                BlockStateProvider.of(Blocks.SANDSTONE_WALL.defaultState)
                            )
                        ),
                        Direction.DOWN, BlockPredicate.IS_AIR, false
                    ),
                    BlockFilterPlacementModifier.of(
                        BlockPredicate.hasSturdyFace(Direction.DOWN)
                    )
                )
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.ORE_RED_SAND,
            Feature.ORE,
            OreFeatureConfig(
                TagMatchRuleTest(DuskBlockTags.CAVE_PILLAR_PLACEABLE),
                Blocks.RED_SAND.defaultState,
                64
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.RED_SAND_CAVE_PILLAR,
            ReefFeatures.LARGE_CAVE_PILLAR,
            LargeCavePillarFeatureConfig(
                30,
                UniformIntProvider.create(3, 19),
                UniformFloatProvider.create(0.4f, 2.0f),
                0.33f,
                UniformFloatProvider.create(0.3f, 0.9f),
                UniformFloatProvider.create(0.4f, 1.0f),
                UniformFloatProvider.create(0.0f, 0.3f),
                4,
                0.6f,
                BlockStateProvider.of(Blocks.RED_SANDSTONE),
                blockTags.getOrThrow(DuskBlockTags.CAVE_PILLAR_PLACEABLE)
            )
        )

        c.registerConfiguredFeature(
            DuskConfiguredFeatures.RED_SAND_SPIKES,
            Feature.RANDOM_PATCH,
            ConfiguredFeatures.createRandomPatchFeatureConfig(
                10, PlacedFeatures.createEntry(
                    Feature.BLOCK_COLUMN,
                    BlockColumnFeatureConfig(
                        listOf(
                            BlockColumnFeatureConfig.createLayer(
                                UniformIntProvider.create(1, 7),
                                BlockStateProvider.of(Blocks.RED_SANDSTONE.defaultState)
                            ),
                            BlockColumnFeatureConfig.createLayer(
                                UniformIntProvider.create(2, 5),
                                BlockStateProvider.of(Blocks.RED_SANDSTONE_WALL.defaultState)
                            )
                        ),
                        Direction.UP, BlockPredicate.IS_AIR, false
                    ),
                    BlockFilterPlacementModifier.of(
                        BlockPredicate.hasSturdyFace(Direction.UP)
                    )
                )
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.RED_SAND_SPIKES_ROOF,
            Feature.RANDOM_PATCH,
            ConfiguredFeatures.createRandomPatchFeatureConfig(
                10, PlacedFeatures.createEntry(
                    Feature.BLOCK_COLUMN,
                    BlockColumnFeatureConfig(
                        listOf(
                            BlockColumnFeatureConfig.createLayer(
                                UniformIntProvider.create(1, 7),
                                BlockStateProvider.of(Blocks.RED_SANDSTONE.defaultState)
                            ),
                            BlockColumnFeatureConfig.createLayer(
                                UniformIntProvider.create(2, 5),
                                BlockStateProvider.of(Blocks.RED_SANDSTONE_WALL.defaultState)
                            )
                        ),
                        Direction.DOWN, BlockPredicate.IS_AIR, false
                    ),
                    BlockFilterPlacementModifier.of(
                        BlockPredicate.hasSturdyFace(Direction.DOWN)
                    )
                )
            )
        )
        c.registerConfiguredFeature<RandomPatchFeatureConfig, Feature<RandomPatchFeatureConfig>>(
            DuskConfiguredFeatures.SAND_CAVE_SEAGRASS, Feature.RANDOM_PATCH, RandomPatchFeatureConfig(
                64, 7, 3, PlacedFeatures.createEntry<SimpleBlockFeatureConfig, Feature<SimpleBlockFeatureConfig>>(
                    Feature.SIMPLE_BLOCK,
                    SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.SEAGRASS)),
                    BlockPredicate.allOf(
                        *arrayOf<BlockPredicate>(
                            BlockPredicate.matchingFluids(Fluids.WATER),
                            BlockPredicate.wouldSurvive(Blocks.SEAGRASS.defaultState, BlockPos.ORIGIN)
                        )
                    )
                )
            )
        )
        c.registerConfiguredFeature<RandomPatchFeatureConfig, Feature<RandomPatchFeatureConfig>>(
            DuskConfiguredFeatures.SAND_CAVE_PICKLES, Feature.RANDOM_PATCH, RandomPatchFeatureConfig(
                64, 7, 3, PlacedFeatures.createEntry<SimpleBlockFeatureConfig, Feature<SimpleBlockFeatureConfig>>(
                    Feature.SIMPLE_BLOCK,
                    SimpleBlockFeatureConfig(
                        NoiseThresholdBlockStateProvider(
                            6789L,
                            NoiseParameters(0, 1.0, *DoubleArray(0)),
                            0.005f,
                            -0.8f,
                            0.33333334f,
                            Blocks.SEAGRASS.defaultState,
                            listOf(
                                Blocks.SEA_PICKLE.defaultState,
                                Blocks.SEA_PICKLE.defaultState.with(SeaPickleBlock.PICKLES, 2)
                            ),
                            listOf(
                                Blocks.SEA_PICKLE.defaultState,
                                Blocks.SEA_PICKLE.defaultState.with(SeaPickleBlock.PICKLES, 2),
                                Blocks.SEA_PICKLE.defaultState.with(SeaPickleBlock.PICKLES, 3),
                                Blocks.SEA_PICKLE.defaultState.with(SeaPickleBlock.PICKLES, 4)
                            )
                        )
                    ),
                    BlockPredicate.allOf(
                        *arrayOf<BlockPredicate>(
                            BlockPredicate.matchingFluids(Fluids.WATER),
                            BlockPredicate.wouldSurvive(Blocks.SEAGRASS.defaultState, BlockPos.ORIGIN)
                        )
                    )
                )
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.ORE_COBBLESTONE,
            Feature.RANDOM_BOOLEAN_SELECTOR,
            RandomBooleanFeatureConfig(
                PlacedFeatures.createEntry(
                    Feature.ORE,
                    OreFeatureConfig(
                        TagMatchRuleTest(DuskBlockTags.CAVE_PILLAR_PLACEABLE),
                        Blocks.COBBLESTONE.defaultState,
                        64
                    ),
                    *arrayOfNulls<PlacementModifier>(0)
                ),
                PlacedFeatures.createEntry(
                    Feature.ORE,
                    OreFeatureConfig(
                        TagMatchRuleTest(DuskBlockTags.CAVE_PILLAR_PLACEABLE),
                        Blocks.COBBLED_DEEPSLATE.defaultState,
                        64
                    ),
                    *arrayOfNulls<PlacementModifier>(0)
                )
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.COBBLESTONE_CAVE_PILLAR,
            ReefFeatures.LARGE_CAVE_PILLAR,
            LargeCavePillarFeatureConfig(
                30,
                UniformIntProvider.create(3, 19),
                UniformFloatProvider.create(0.4f, 2.0f),
                0.33f,
                UniformFloatProvider.create(0.3f, 0.9f),
                UniformFloatProvider.create(0.4f, 1.0f),
                UniformFloatProvider.create(0.0f, 0.3f),
                4,
                0.6f,
                BlockStateProvider.of(Blocks.COBBLESTONE),
                blockTags.getOrThrow(DuskBlockTags.CAVE_PILLAR_PLACEABLE)
            )
        )

        c.registerConfiguredFeature(
            DuskConfiguredFeatures.COBBLESTONE_SPIKES,
            Feature.RANDOM_PATCH,
            ConfiguredFeatures.createRandomPatchFeatureConfig(
                10, PlacedFeatures.createEntry(
                    Feature.BLOCK_COLUMN,
                    BlockColumnFeatureConfig(
                        listOf(
                            BlockColumnFeatureConfig.createLayer(
                                UniformIntProvider.create(1, 7),
                                BlockStateProvider.of(Blocks.COBBLESTONE.defaultState)
                            ),
                            BlockColumnFeatureConfig.createLayer(
                                UniformIntProvider.create(2, 5),
                                BlockStateProvider.of(Blocks.COBBLESTONE_WALL.defaultState)
                            )
                        ),
                        Direction.UP, BlockPredicate.IS_AIR, false
                    ),
                    BlockFilterPlacementModifier.of(
                        BlockPredicate.hasSturdyFace(Direction.UP)
                    )
                )
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.COBBLESTONE_SPIKES_ROOF,
            Feature.RANDOM_PATCH,
            ConfiguredFeatures.createRandomPatchFeatureConfig(
                10, PlacedFeatures.createEntry(
                    Feature.BLOCK_COLUMN,
                    BlockColumnFeatureConfig(
                        listOf(
                            BlockColumnFeatureConfig.createLayer(
                                UniformIntProvider.create(1, 7),
                                BlockStateProvider.of(Blocks.COBBLESTONE.defaultState)
                            ),
                            BlockColumnFeatureConfig.createLayer(
                                UniformIntProvider.create(2, 5),
                                BlockStateProvider.of(Blocks.COBBLESTONE_WALL.defaultState)
                            )
                        ),
                        Direction.DOWN, BlockPredicate.IS_AIR, false
                    ),
                    BlockFilterPlacementModifier.of(
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
                UniformIntProvider.create(3, 19),
                UniformFloatProvider.create(0.4f, 2.0f),
                0.33f,
                UniformFloatProvider.create(0.3f, 0.9f),
                UniformFloatProvider.create(0.4f, 1.0f),
                UniformFloatProvider.create(0.0f, 0.3f),
                4,
                0.6f,
                BlockStateProvider.of(Blocks.COBBLED_DEEPSLATE),
                blockTags.getOrThrow(DuskBlockTags.CAVE_PILLAR_PLACEABLE)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.COBBLED_DEEPSLATE_SPIKES,
            Feature.RANDOM_PATCH,
            ConfiguredFeatures.createRandomPatchFeatureConfig(
                10, PlacedFeatures.createEntry(
                    Feature.BLOCK_COLUMN,
                    BlockColumnFeatureConfig(
                        listOf(
                            BlockColumnFeatureConfig.createLayer(
                                UniformIntProvider.create(1, 7),
                                BlockStateProvider.of(Blocks.COBBLED_DEEPSLATE.defaultState)
                            ),
                            BlockColumnFeatureConfig.createLayer(
                                UniformIntProvider.create(2, 5),
                                BlockStateProvider.of(Blocks.COBBLED_DEEPSLATE_WALL.defaultState)
                            )
                        ),
                        Direction.UP, BlockPredicate.IS_AIR, false
                    ),
                    BlockFilterPlacementModifier.of(
                        BlockPredicate.hasSturdyFace(Direction.UP)
                    )
                )
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.COBBLED_DEEPSLATE_SPIKES_ROOF,
            Feature.RANDOM_PATCH,
            ConfiguredFeatures.createRandomPatchFeatureConfig(
                10, PlacedFeatures.createEntry(
                    Feature.BLOCK_COLUMN,
                    BlockColumnFeatureConfig(
                        listOf(
                            BlockColumnFeatureConfig.createLayer(
                                UniformIntProvider.create(1, 7),
                                BlockStateProvider.of(Blocks.COBBLED_DEEPSLATE.defaultState)
                            ),
                            BlockColumnFeatureConfig.createLayer(
                                UniformIntProvider.create(2, 5),
                                BlockStateProvider.of(Blocks.COBBLED_DEEPSLATE_WALL.defaultState)
                            )
                        ),
                        Direction.DOWN, BlockPredicate.IS_AIR, false
                    ),
                    BlockFilterPlacementModifier.of(
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
                UniformIntProvider.create(3, 19),
                UniformFloatProvider.create(0.4f, 2.0f),
                0.33f,
                UniformFloatProvider.create(0.3f, 0.9f),
                UniformFloatProvider.create(0.4f, 1.0f),
                UniformFloatProvider.create(0.0f, 0.3f),
                4,
                0.6f,
                BlockStateProvider.of(Blocks.DIAMOND_BLOCK),
                blockTags.getOrThrow(BlockTags.BASE_STONE_OVERWORLD)
            )
        )

//Structure Piece features
        val procDesertWell = procLists.getOrThrow(StructureProcessorLists.EMPTY)
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.DESERT_WELL,
            ReefFeatures.STRUCTURE_PIECE,
            StructurePieceFeatureConfig(
                id("feature/desert_well"),
                procDesertWell,
                8,
                Heightmap.Type.OCEAN_FLOOR_WG
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.RED_DESERT_WELL,
            ReefFeatures.STRUCTURE_PIECE,
            StructurePieceFeatureConfig(
                id("feature/red_desert_well"),
                procDesertWell,
                8,
                Heightmap.Type.OCEAN_FLOOR_WG,
            )
        )

//Monster Room features
        val defaultMonstersRoom = listOf(EntityType.SKELETON, EntityType.ZOMBIE, EntityType.ZOMBIE, EntityType.SPIDER)
        val lushMonstersRoom = defaultMonstersRoom + listOf(/*put stuff here*/)
        val frozenMonstersRoom = listOf(EntityType.STRAY, EntityType.ZOMBIE, EntityType.ZOMBIE, EntityType.SPIDER)
        val sandMonstersRoom = listOf(EntityType.SKELETON, EntityType.HUSK, EntityType.HUSK, EntityType.SPIDER)

        c.registerConfiguredFeature(
            DuskConfiguredFeatures.DEEP_MONSTER_ROOM,
            ReefFeatures.MONSTER_ROOM,
            ReefMonsterRoomFeatureConfig(
                BlockStateProvider.of(Blocks.COBBLED_DEEPSLATE),
                BlockStateProvider.of(Blocks.TUFF),
                defaultMonstersRoom,
                LootTables.SIMPLE_DUNGEON_CHEST.value
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.LUSH_MONSTER_ROOM,
            ReefFeatures.MONSTER_ROOM,
            ReefMonsterRoomFeatureConfig(
                BlockStateProvider.of(Blocks.MOSSY_COBBLESTONE),
                BlockStateProvider.of(Blocks.MUD),
                lushMonstersRoom,
                LootTables.SIMPLE_DUNGEON_CHEST.value
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.DEEP_LUSH_MONSTER_ROOM,
            ReefFeatures.MONSTER_ROOM,
            ReefMonsterRoomFeatureConfig(
                BlockStateProvider.of(Blocks.COBBLED_DEEPSLATE),
                BlockStateProvider.of(Blocks.MUD),
                lushMonstersRoom,
                LootTables.SIMPLE_DUNGEON_CHEST.value
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.FROZEN_MONSTER_ROOM,
            ReefFeatures.MONSTER_ROOM,
            ReefMonsterRoomFeatureConfig(
                BlockStateProvider.of(Blocks.COBBLESTONE),
                BlockStateProvider.of(Blocks.PACKED_ICE),
                frozenMonstersRoom,
                LootTables.SIMPLE_DUNGEON_CHEST.value
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.DEEP_FROZEN_MONSTER_ROOM,
            ReefFeatures.MONSTER_ROOM,
            ReefMonsterRoomFeatureConfig(
                BlockStateProvider.of(Blocks.COBBLED_DEEPSLATE),
                BlockStateProvider.of(Blocks.BLUE_ICE),
                frozenMonstersRoom,
                LootTables.SIMPLE_DUNGEON_CHEST.value
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.SAND_MONSTER_ROOM,
            ReefFeatures.MONSTER_ROOM,
            ReefMonsterRoomFeatureConfig(
                BlockStateProvider.of(Blocks.SANDSTONE),
                BlockStateProvider.of(Blocks.SAND),
                sandMonstersRoom,
                LootTables.SIMPLE_DUNGEON_CHEST.value
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.RED_SAND_MONSTER_ROOM,
            ReefFeatures.MONSTER_ROOM,
            ReefMonsterRoomFeatureConfig(
                BlockStateProvider.of(Blocks.RED_SANDSTONE),
                BlockStateProvider.of(Blocks.RED_SAND),
                sandMonstersRoom,
                LootTables.SIMPLE_DUNGEON_CHEST.value
            )
        )
    }


    private fun flowerbed(block: Block): Pool.Builder<BlockState> = segmentedBlock(
        block,
        1,
        4,
        FlowerbedBlock.FLOWER_AMOUNT,
        FlowerbedBlock.HORIZONTAL_FACING
    )

    private fun segmentedBlock(
        block: Block,
        min: Int,
        max: Int,
        intProperty: IntProperty,
        enumProperty: EnumProperty<Direction>
    ): Pool.Builder<BlockState> {
        val builder = Pool.builder<BlockState>()

        for (k in min..max) {
            val var7: Iterator<Direction> = Direction.Type.HORIZONTAL.iterator()

            while (var7.hasNext()) {
                val direction = var7.next()
                builder.add(
                    (block.defaultState.with(intProperty, k)).with(
                        enumProperty,
                        direction
                    ), 1
                )
            }
        }

        return builder
    }

    fun <FC : FeatureConfig, F : Feature<FC>> Registerable<ConfiguredFeature<*, *>>.registerConfiguredFeature(
        registryKey: RegistryKey<ConfiguredFeature<*, *>>,
        feature: F,
        featureConfig: FC
    ): Any = this.register(registryKey, ConfiguredFeature(feature, featureConfig))

    @Suppress("unused")
    private fun Registerable<ConfiguredFeature<*, *>>.registerConfiguredFeature(
        registryKey: RegistryKey<ConfiguredFeature<*, *>>, feature: Feature<DefaultFeatureConfig>
    ) = this.registerConfiguredFeature(registryKey, feature, FeatureConfig.DEFAULT)

}