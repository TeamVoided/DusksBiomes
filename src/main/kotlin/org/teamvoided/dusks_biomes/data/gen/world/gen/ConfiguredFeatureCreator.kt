package org.teamvoided.dusk_autumns_worldgen.data.gen.world.gen

import net.minecraft.block.*
import net.minecraft.entity.EntityType
import net.minecraft.fluid.Fluids
import net.minecraft.loot.LootTables
import net.minecraft.registry.BootstrapContext
import net.minecraft.registry.HolderSet
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.BlockTags
import net.minecraft.structure.processor.StructureProcessorLists
import net.minecraft.structure.rule.TagMatchRuleTest
import net.minecraft.util.collection.DataPool
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.float_provider.UniformFloatProvider
import net.minecraft.util.math.int_provider.*
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler.NoiseParameters
import net.minecraft.world.Heightmap
import net.minecraft.world.gen.blockpredicate.BlockPredicate
import net.minecraft.world.gen.decorator.BlockPredicateFilterPlacementModifier
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize
import net.minecraft.world.gen.feature.util.ConfiguredFeatureUtil
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil
import net.minecraft.world.gen.foliage.BlobFoliagePlacer
import net.minecraft.world.gen.foliage.CherryFoliagePlacer
import net.minecraft.world.gen.foliage.RandomSpreadFoliagePlacer
import net.minecraft.world.gen.root.AboveRootPlacement
import net.minecraft.world.gen.root.MangroveRootPlacement
import net.minecraft.world.gen.root.MangroveRootPlacer
import net.minecraft.world.gen.stateprovider.BlockStateProvider
import net.minecraft.world.gen.stateprovider.NoiseCutoffBlockStateProvider
import net.minecraft.world.gen.stateprovider.RandomizedIntBlockStateProvider
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider
import net.minecraft.world.gen.treedecorator.AttachedToLeavesTreeDecorator
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator
import net.minecraft.world.gen.treedecorator.LeavesVineTreeDecorator
import net.minecraft.world.gen.trunk.CherryTrunkPlacer
import net.minecraft.world.gen.trunk.StraightTrunkPlacer
import net.minecraft.world.gen.trunk.UpwardsBranchingTrunkPlacer
import org.teamvoided.dusks_biomes.DusksBiomesMod.id
import org.teamvoided.dusks_biomes.data.tags.DuskBlockTags
import org.teamvoided.dusks_biomes.data.world.gen.DuskConfiguredFeatures
import org.teamvoided.dusks_biomes.data.world.gen.DuskPlacedFeatures
import org.teamvoided.reef.init.ReefFeatures
import java.util.*
import kotlin.collections.forEach
import kotlin.collections.listOf
import kotlin.collections.plus
import org.teamvoided.reef.world.gen.configured_feature.config.*

@Suppress("DEPRECATION")
object ConfiguredFeatureCreator {
    fun bootstrap(c: BootstrapContext<ConfiguredFeature<*, *>>) {
        val blockTags = c.getRegistryLookup(RegistryKeys.BLOCK)
        val configuredFeatures = c.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE)
        val placedFeatures = c.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val procLists = c.getRegistryLookup(RegistryKeys.STRUCTURE_PROCESSOR_LIST)

        val procEmpty = procLists.getHolderOrThrow(StructureProcessorLists.EMPTY)

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
                    blockTags.getTagOrThrow(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)
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
                            blockTags.getTagOrThrow(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH),
                            HolderSet.createDirect(
                                { it.builtInRegistryHolder },
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
                                Blocks.MANGROVE_PROPAGULE.defaultState.with(MangrovePropaguleBlock.HANGING, true)
                            ), MangrovePropaguleBlock.AGE_4, UniformIntProvider.create(0, 4)
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
                    blockTags.getTagOrThrow(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)
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
                            blockTags.getTagOrThrow(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH),
                            HolderSet.createDirect(
                                { it.builtInRegistryHolder },
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
                                Blocks.MANGROVE_PROPAGULE.defaultState.with(MangrovePropaguleBlock.HANGING, true)
                            ), MangrovePropaguleBlock.AGE_4, UniformIntProvider.create(0, 4)
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
                    WeightedPlacedFeature(
                        placedFeatures.getHolderOrThrow(DuskPlacedFeatures.TALL_MANGROVE_FROZEN_CHECKED), 0.85f
                    )
                ),
                placedFeatures.getHolderOrThrow(DuskPlacedFeatures.MANGROVE_FROZEN_CHECKED)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.SWAMP_OAK,
            Feature.TREE,
            TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.OAK_LOG),
                StraightTrunkPlacer(5, 3, 0),
                BlockStateProvider.of(Blocks.OAK_LEAVES),
                BlobFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), 3),
                TwoLayersFeatureSize(1, 0, 1)
            ).decorators(
                listOf(LeavesVineTreeDecorator(0.25f))
            ).build()
        )

        c.registerConfiguredFeature(
            DuskConfiguredFeatures.TREES_OAK_BIRCH_JUNGLE,
            Feature.RANDOM_SELECTOR,
            RandomFeatureConfig(
                listOf(
                    WeightedPlacedFeature(placedFeatures.getHolderOrThrow(TreePlacedFeatures.BIRCH_BEES_0002), 0.4f),
                    WeightedPlacedFeature(
                        placedFeatures.getHolderOrThrow(TreePlacedFeatures.FANCY_OAK_BEES_0002),
                        0.2f
                    ),
                    WeightedPlacedFeature(placedFeatures.getHolderOrThrow(TreePlacedFeatures.JUNGLE_TREE), 0.15f)
                ), placedFeatures.getHolderOrThrow(TreePlacedFeatures.OAK_BEES_0002)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.TREES_OAK_BIRCH_SPRUCE,
            Feature.RANDOM_SELECTOR,
            RandomFeatureConfig(
                listOf(
                    WeightedPlacedFeature(placedFeatures.getHolderOrThrow(TreePlacedFeatures.BIRCH_BEES_0002), 0.4f),
                    WeightedPlacedFeature(
                        placedFeatures.getHolderOrThrow(TreePlacedFeatures.FANCY_OAK_BEES_0002),
                        0.2f
                    ),
                    WeightedPlacedFeature(placedFeatures.getHolderOrThrow(TreePlacedFeatures.SPRUCE_CHECKED), 0.15f)
                ), placedFeatures.getHolderOrThrow(TreePlacedFeatures.OAK_BEES_0002)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.TREES_OAK_DARK_SPRUCE,
            Feature.RANDOM_SELECTOR,
            RandomFeatureConfig(
                listOf(
                    WeightedPlacedFeature(placedFeatures.getHolderOrThrow(TreePlacedFeatures.DARK_OAK_CHECKED), 2 / 3f),
                    WeightedPlacedFeature(
                        placedFeatures.getHolderOrThrow(TreePlacedFeatures.SPRUCE_CHECKED),
                        0.3f
                    ),
                    WeightedPlacedFeature(placedFeatures.getHolderOrThrow(TreePlacedFeatures.FANCY_OAK_CHECKED), 0.125f)
                ), placedFeatures.getHolderOrThrow(TreePlacedFeatures.OAK_CHECKED)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.TREES_OAK_DARK_SPRUCE_ON_SNOW,
            Feature.RANDOM_SELECTOR,
            RandomFeatureConfig(
                listOf(
                    WeightedPlacedFeature(
                        PlacedFeatureUtil.placedInline(
                            configuredFeatures.getHolderOrThrow(
                                TreeConfiguredFeatures.DARK_OAK
                            )
                        ), 2 / 3f
                    ),
                    WeightedPlacedFeature(
                        PlacedFeatureUtil.placedInline(configuredFeatures.getHolderOrThrow(TreeConfiguredFeatures.SPRUCE)),
                        0.3f
                    ),
                    WeightedPlacedFeature(
                        PlacedFeatureUtil.placedInline(
                            configuredFeatures.getHolderOrThrow(
                                TreeConfiguredFeatures.FANCY_OAK
                            )
                        ), 0.125f
                    )
                ),
                PlacedFeatureUtil.placedInline(configuredFeatures.getHolderOrThrow(TreeConfiguredFeatures.OAK))
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.FLOWER_SNOWY_CHERRY, Feature.FLOWER, RandomPatchFeatureConfig(
                96, 6, 2, PlacedFeatureUtil.onlyWhenEmpty(
                    Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(
                        WeightedBlockStateProvider(getPetalStates().addWeighted(Blocks.SNOW.defaultState, 8))
                    )
                )
            )
        )
        c.registerConfiguredFeature<RandomPatchFeatureConfig, Feature<RandomPatchFeatureConfig>>(
            DuskConfiguredFeatures.CAVE_GLOW_LICHEN_EXTRA,
            Feature.RANDOM_PATCH,
            ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                20, PlacedFeatureUtil.placedInline(
                    Feature.MULTIFACE_GROWTH,
                    GlowLichenFeatureConfig(
                        (Blocks.GLOW_LICHEN as AbstractLichenBlock),
                        20,
                        true,
                        true,
                        true,
                        0.75f,
                        HolderSet.createDirect(
                            { obj: Block -> obj.builtInRegistryHolder },
                            *arrayOf<Block>(
                                Blocks.STONE,
                                Blocks.ANDESITE,
                                Blocks.DIORITE,
                                Blocks.GRANITE,
                                Blocks.DRIPSTONE_BLOCK,
                                Blocks.CALCITE,
                                Blocks.TUFF,
                                Blocks.DEEPSLATE,
                                Blocks.GRAVEL,
                                Blocks.MYCELIUM,
                                Blocks.PODZOL,
                                Blocks.COARSE_DIRT,
                                Blocks.RED_MUSHROOM_BLOCK,
                                Blocks.BROWN_MUSHROOM_BLOCK,
                                Blocks.MUSHROOM_STEM,
                                Blocks.PACKED_ICE,
                                Blocks.BLUE_ICE,
                                Blocks.SANDSTONE,
                                Blocks.RED_SANDSTONE
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
                PlacedFeatureUtil.placedInline(
                    configuredFeatures.getHolderOrThrow(VegetationConfiguredFeatures.PATCH_RED_MUSHROOM),
                    *arrayOfNulls<PlacementModifier>(0)
                ),
                PlacedFeatureUtil.placedInline(
                    configuredFeatures.getHolderOrThrow(
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
                PlacedFeatureUtil.placedInline(
                    configuredFeatures.getHolderOrThrow(DuskConfiguredFeatures.CAVE_GLOW_LICHEN_EXTRA),
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
                        ), BlockPredicate.matchingBlockTags(BlockTags.REPLACEABLE_BY_TREES)
                    ), BlockPredicate.matchingBlockTags(
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
                blockTags.getTagOrThrow(DuskBlockTags.CAVE_PILLAR_PLACEABLE)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.ICE_SPIKE,
            ReefFeatures.SPIKE,
            SpikeFeatureConfig(
                60, 10, 30,
                BlockStateProvider.of(Blocks.PACKED_ICE),
                blockTags.getTagOrThrow(DuskBlockTags.ICE_SPIKE_PLACEABLE_BLOCKS)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.INVERTED_ICE_SPIKE,
            ReefFeatures.INVERTED_SPIKE,
            SpikeFeatureConfig(
                60, 10, 30,
                BlockStateProvider.of(Blocks.PACKED_ICE),
                blockTags.getTagOrThrow(DuskBlockTags.ICE_SPIKE_PLACEABLE_BLOCKS)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.BLUE_ICE_SPIKE,
            ReefFeatures.SPIKE,
            SpikeFeatureConfig(
                5, 10, 30,
                BlockStateProvider.of(Blocks.BLUE_ICE),
                blockTags.getTagOrThrow(DuskBlockTags.ICE_SPIKE_PLACEABLE_BLOCKS)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.INVERTED_BLUE_ICE_SPIKE,
            ReefFeatures.INVERTED_SPIKE,
            SpikeFeatureConfig(
                5, 10, 30,
                BlockStateProvider.of(Blocks.BLUE_ICE),
                blockTags.getTagOrThrow(DuskBlockTags.ICE_SPIKE_PLACEABLE_BLOCKS)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.ICE_SPIKE_FLOOR,
            Feature.RANDOM_SELECTOR,
            RandomFeatureConfig(
                listOf(
                    WeightedPlacedFeature(
                        PlacedFeatureUtil.placedInline(configuredFeatures.getHolderOrThrow(DuskConfiguredFeatures.BLUE_ICE_SPIKE)),
                        0.075f
                    )
                ),
                PlacedFeatureUtil.placedInline(configuredFeatures.getHolderOrThrow(DuskConfiguredFeatures.ICE_SPIKE))
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.ICE_SPIKE_CEILING,
            Feature.RANDOM_SELECTOR,
            RandomFeatureConfig(
                listOf(
                    WeightedPlacedFeature(
                        PlacedFeatureUtil.placedInline(configuredFeatures.getHolderOrThrow(DuskConfiguredFeatures.INVERTED_BLUE_ICE_SPIKE)),
                        0.075f
                    )
                ),
                PlacedFeatureUtil.placedInline(configuredFeatures.getHolderOrThrow(DuskConfiguredFeatures.INVERTED_ICE_SPIKE))
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
                    PlacedFeatureUtil.placedInline(configuredFeatures.getHolderOrThrow(DuskConfiguredFeatures.ORE_ICE)),
                    PlacedFeatureUtil.placedInline(configuredFeatures.getHolderOrThrow(UndergroundConfiguredFeatures.FOSSIL_DIAMONDS))
                )
            )
        )
        c.registerConfiguredFeature<RandomPatchFeatureConfig, Feature<RandomPatchFeatureConfig>>(
            DuskConfiguredFeatures.SAND_CAVE_CACTUS,
            Feature.RANDOM_PATCH,
            ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                10, PlacedFeatureUtil.placedInline<BlockColumnFeatureConfig, Feature<BlockColumnFeatureConfig>>(
                    Feature.BLOCK_COLUMN,
                    BlockColumnFeatureConfig.create(
                        BiasedToBottomIntProvider.create(1, 7),
                        BlockStateProvider.of(Blocks.CACTUS)
                    ),
                    *arrayOf<PlacementModifier>(
                        BlockPredicateFilterPlacementModifier.create(
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
                blockTags.getTagOrThrow(DuskBlockTags.CAVE_PILLAR_PLACEABLE)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.SAND_SPIKES,
            Feature.RANDOM_PATCH,
            ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                10, PlacedFeatureUtil.placedInline(
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
                    BlockPredicateFilterPlacementModifier.create(
                        BlockPredicate.hasSturdyFace(Direction.UP)
                    )
                )
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.SAND_SPIKES_ROOF,
            Feature.RANDOM_PATCH,
            ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                10, PlacedFeatureUtil.placedInline(
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
                    BlockPredicateFilterPlacementModifier.create(
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
                blockTags.getTagOrThrow(DuskBlockTags.CAVE_PILLAR_PLACEABLE)
            )
        )

        c.registerConfiguredFeature(
            DuskConfiguredFeatures.RED_SAND_SPIKES,
            Feature.RANDOM_PATCH,
            ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                10, PlacedFeatureUtil.placedInline(
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
                    BlockPredicateFilterPlacementModifier.create(
                        BlockPredicate.hasSturdyFace(Direction.UP)
                    )
                )
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.RED_SAND_SPIKES_ROOF,
            Feature.RANDOM_PATCH,
            ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                10, PlacedFeatureUtil.placedInline(
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
                    BlockPredicateFilterPlacementModifier.create(
                        BlockPredicate.hasSturdyFace(Direction.DOWN)
                    )
                )
            )
        )
        c.registerConfiguredFeature<RandomPatchFeatureConfig, Feature<RandomPatchFeatureConfig>>(
            DuskConfiguredFeatures.SAND_CAVE_SEAGRASS, Feature.RANDOM_PATCH, RandomPatchFeatureConfig(
                64, 7, 3, PlacedFeatureUtil.filtered<SimpleBlockFeatureConfig, Feature<SimpleBlockFeatureConfig>>(
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
                64, 7, 3, PlacedFeatureUtil.filtered<SimpleBlockFeatureConfig, Feature<SimpleBlockFeatureConfig>>(
                    Feature.SIMPLE_BLOCK,
                    SimpleBlockFeatureConfig(
                        NoiseCutoffBlockStateProvider(
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
                PlacedFeatureUtil.placedInline(
                    Feature.ORE,
                    OreFeatureConfig(
                        TagMatchRuleTest(DuskBlockTags.CAVE_PILLAR_PLACEABLE),
                        Blocks.COBBLESTONE.defaultState,
                        64
                    ),
                    *arrayOfNulls<PlacementModifier>(0)
                ),
                PlacedFeatureUtil.placedInline(
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
                blockTags.getTagOrThrow(DuskBlockTags.CAVE_PILLAR_PLACEABLE)
            )
        )

        c.registerConfiguredFeature(
            DuskConfiguredFeatures.COBBLESTONE_SPIKES,
            Feature.RANDOM_PATCH,
            ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                10, PlacedFeatureUtil.placedInline(
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
                    BlockPredicateFilterPlacementModifier.create(
                        BlockPredicate.hasSturdyFace(Direction.UP)
                    )
                )
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.COBBLESTONE_SPIKES_ROOF,
            Feature.RANDOM_PATCH,
            ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                10, PlacedFeatureUtil.placedInline(
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
                    BlockPredicateFilterPlacementModifier.create(
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
                blockTags.getTagOrThrow(DuskBlockTags.CAVE_PILLAR_PLACEABLE)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.COBBLED_DEEPSLATE_SPIKES,
            Feature.RANDOM_PATCH,
            ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                10, PlacedFeatureUtil.placedInline(
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
                    BlockPredicateFilterPlacementModifier.create(
                        BlockPredicate.hasSturdyFace(Direction.UP)
                    )
                )
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.COBBLED_DEEPSLATE_SPIKES_ROOF,
            Feature.RANDOM_PATCH,
            ConfiguredFeatureUtil.createRandomPatchFeatureConfig(
                10, PlacedFeatureUtil.placedInline(
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
                    BlockPredicateFilterPlacementModifier.create(
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
                blockTags.getTagOrThrow(BlockTags.BASE_STONE_OVERWORLD)
            )
        )

//Structure Piece features
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.DESERT_WELL,
            ReefFeatures.STRUCTURE_PIECE,
            StructurePieceFeatureConfig(
                id("feature/desert_well"),
                procEmpty,
                8,
                Heightmap.Type.OCEAN_FLOOR_WG
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.RED_DESERT_WELL,
            ReefFeatures.STRUCTURE_PIECE,
            StructurePieceFeatureConfig(
                id("feature/red_desert_well"),
                procEmpty,
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
            MonsterRoomFeatureConfig(
                BlockStateProvider.of(Blocks.COBBLED_DEEPSLATE),
                BlockStateProvider.of(Blocks.TUFF),
                defaultMonstersRoom,
                LootTables.SIMPLE_DUNGEON_CHEST.value
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.LUSH_MONSTER_ROOM,
            ReefFeatures.MONSTER_ROOM,
            MonsterRoomFeatureConfig(
                BlockStateProvider.of(Blocks.MOSSY_COBBLESTONE),
                BlockStateProvider.of(Blocks.MUD),
                lushMonstersRoom,
                LootTables.SIMPLE_DUNGEON_CHEST.value
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.DEEP_LUSH_MONSTER_ROOM,
            ReefFeatures.MONSTER_ROOM,
            MonsterRoomFeatureConfig(
                BlockStateProvider.of(Blocks.COBBLED_DEEPSLATE),
                BlockStateProvider.of(Blocks.MUD),
                lushMonstersRoom,
                LootTables.SIMPLE_DUNGEON_CHEST.value
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.FROZEN_MONSTER_ROOM,
            ReefFeatures.MONSTER_ROOM,
            MonsterRoomFeatureConfig(
                BlockStateProvider.of(Blocks.COBBLESTONE),
                BlockStateProvider.of(Blocks.PACKED_ICE),
                frozenMonstersRoom,
                LootTables.SIMPLE_DUNGEON_CHEST.value
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.DEEP_FROZEN_MONSTER_ROOM,
            ReefFeatures.MONSTER_ROOM,
            MonsterRoomFeatureConfig(
                BlockStateProvider.of(Blocks.COBBLED_DEEPSLATE),
                BlockStateProvider.of(Blocks.BLUE_ICE),
                frozenMonstersRoom,
                LootTables.SIMPLE_DUNGEON_CHEST.value
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.SAND_MONSTER_ROOM,
            ReefFeatures.MONSTER_ROOM,
            MonsterRoomFeatureConfig(
                BlockStateProvider.of(Blocks.SANDSTONE),
                BlockStateProvider.of(Blocks.SAND),
                sandMonstersRoom,
                LootTables.SIMPLE_DUNGEON_CHEST.value
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.RED_SAND_MONSTER_ROOM,
            ReefFeatures.MONSTER_ROOM,
            MonsterRoomFeatureConfig(
                BlockStateProvider.of(Blocks.RED_SANDSTONE),
                BlockStateProvider.of(Blocks.RED_SAND),
                sandMonstersRoom,
                LootTables.SIMPLE_DUNGEON_CHEST.value
            )
        )
    }

    private fun getPetalStates(): DataPool.Builder<BlockState> {
        val randomPetal = DataPool.builder<BlockState>()
        for (i in 1..4) {
            Direction.Type.HORIZONTAL.forEach {
                randomPetal.addWeighted(
                    Blocks.PINK_PETALS.defaultState.with(PinkPetalsBlock.AMOUNT, i).with(PinkPetalsBlock.FACING, it),
                    1
                )
            }
        }
        return randomPetal
    }

    private fun <FC : FeatureConfig, F : Feature<FC>> BootstrapContext<ConfiguredFeature<*, *>>.registerConfiguredFeature(
        registryKey: RegistryKey<ConfiguredFeature<*, *>>,
        feature: F,
        featureConfig: FC
    ): Any = this.register(registryKey, ConfiguredFeature(feature, featureConfig))

    @Suppress("unused")
    private fun BootstrapContext<ConfiguredFeature<*, *>>.registerConfiguredFeature(
        registryKey: RegistryKey<ConfiguredFeature<*, *>>, feature: Feature<DefaultFeatureConfig>
    ) = this.registerConfiguredFeature(registryKey, feature, FeatureConfig.DEFAULT)

}