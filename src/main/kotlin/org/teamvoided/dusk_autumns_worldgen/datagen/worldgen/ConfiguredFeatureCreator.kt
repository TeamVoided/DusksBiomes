package org.teamvoided.dusk_autumns_worldgen.datagen.worldgen

import net.minecraft.block.*
import net.minecraft.registry.HolderProvider
import net.minecraft.registry.HolderSet
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.BlockTags
import net.minecraft.util.collection.DataPool
import net.minecraft.util.math.Direction
import net.minecraft.util.math.int_provider.ConstantIntProvider
import net.minecraft.util.math.int_provider.IntProvider
import net.minecraft.util.math.int_provider.UniformIntProvider
import net.minecraft.util.math.int_provider.WeightedListIntProvider
import net.minecraft.world.gen.BootstrapContext
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize
import net.minecraft.world.gen.feature.util.ConfiguredFeatureUtil
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil
import net.minecraft.world.gen.foliage.CherryFoliagePlacer
import net.minecraft.world.gen.foliage.RandomSpreadFoliagePlacer
import net.minecraft.world.gen.root.AboveRootPlacement
import net.minecraft.world.gen.root.MangroveRootPlacement
import net.minecraft.world.gen.root.MangroveRootPlacer
import net.minecraft.world.gen.stateprovider.BlockStateProvider
import net.minecraft.world.gen.stateprovider.RandomizedIntBlockStateProvider
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider
import net.minecraft.world.gen.treedecorator.AttachedToLeavesTreeDecorator
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator
import net.minecraft.world.gen.treedecorator.LeavesVineTreeDecorator
import net.minecraft.world.gen.treedecorator.TreeDecorator
import net.minecraft.world.gen.trunk.CherryTrunkPlacer
import net.minecraft.world.gen.trunk.UpwardsBranchingTrunkPlacer
import org.teamvoided.dusk_autumns_worldgen.init.worldgen.DuskConfiguredFeatures
import org.teamvoided.dusk_autumns_worldgen.init.worldgen.DuskPlacedFeatures
import org.teamvoided.dusk_autumns_worldgen.worldgen.configured_feature.config.SpikeFeatureConfig
import java.util.*

object ConfiguredFeatureCreator {
    fun bootstrap(context: BootstrapContext<ConfiguredFeature<*, *>>) {
        val blockTags = context.lookup(RegistryKeys.BLOCK)
        val configuredFeatures: HolderProvider<ConfiguredFeature<*, *>> =
            context.lookup(RegistryKeys.CONFIGURED_FEATURE)
        val placedFeatures: HolderProvider<PlacedFeature> =
            context.lookup(RegistryKeys.PLACED_FEATURE)

        ConfiguredFeatureUtil.registerConfiguredFeature(
            context, DuskConfiguredFeatures.COBBLESTONE_ROCK, Feature.FOREST_ROCK,
            SingleStateFeatureConfig(Blocks.COBBLESTONE.defaultState)
        )
        ConfiguredFeatureUtil.registerConfiguredFeature<TreeFeatureConfig, Feature<TreeFeatureConfig>>(
            context, DuskConfiguredFeatures.MANGROVE_FROZEN_CHECKED, Feature.TREE,
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
                        Optional.of(
                            AboveRootPlacement(
                                BlockStateProvider.of(Blocks.SNOW),
                                0.5f
                            )
                        ),
                        MangroveRootPlacement(
                            blockTags.getTagOrThrow(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH),
                            HolderSet.createDirect(
                                { obj: Block -> obj.builtInRegistryHolder },
                                *arrayOf<Block>(Blocks.MUD, Blocks.MUDDY_MANGROVE_ROOTS)
                            ),
                            BlockStateProvider.of(Blocks.MUDDY_MANGROVE_ROOTS),
                            8,
                            15,
                            0.2f
                        )
                    )
                ),
                TwoLayersFeatureSize(2, 0, 2)
            )
                .decorators(
                    listOf(
                        LeavesVineTreeDecorator(0.125f), AttachedToLeavesTreeDecorator(
                            0.14f, 1, 0, RandomizedIntBlockStateProvider(
                                BlockStateProvider.of(
                                    Blocks.MANGROVE_PROPAGULE.defaultState.with(
                                        MangrovePropaguleBlock.HANGING,
                                        true
                                    )
                                ), MangrovePropaguleBlock.AGE_4, UniformIntProvider.create(0, 4)
                            ), 2, listOf(Direction.DOWN)
                        ), BeehiveTreeDecorator(0.01F)
                    )
                ).ignoreVines().build()
        )
        ConfiguredFeatureUtil.registerConfiguredFeature<TreeFeatureConfig, Feature<TreeFeatureConfig>>(
            context, DuskConfiguredFeatures.TALL_MANGROVE_FROZEN_CHECKED, Feature.TREE,
            (TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.MANGROVE_LOG),
                UpwardsBranchingTrunkPlacer(
                    4,
                    1,
                    9,
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
                            AboveRootPlacement(
                                BlockStateProvider.of(Blocks.SNOW),
                                0.5f
                            )
                        ),
                        MangroveRootPlacement(
                            blockTags.getTagOrThrow(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH),
                            HolderSet.createDirect(
                                { obj: Block -> obj.builtInRegistryHolder },
                                *arrayOf<Block>(Blocks.MUD, Blocks.MUDDY_MANGROVE_ROOTS)
                            ),
                            BlockStateProvider.of(Blocks.MUDDY_MANGROVE_ROOTS),
                            8,
                            15,
                            0.2f
                        )
                    )
                ),
                TwoLayersFeatureSize(3, 0, 2)
            )).decorators(
                listOf(
                    LeavesVineTreeDecorator(0.125f), AttachedToLeavesTreeDecorator(
                        0.14f, 1, 0, RandomizedIntBlockStateProvider(
                            BlockStateProvider.of(
                                Blocks.MANGROVE_PROPAGULE.defaultState.with(
                                    MangrovePropaguleBlock.HANGING,
                                    true
                                )
                            ), MangrovePropaguleBlock.AGE_4, UniformIntProvider.create(0, 4)
                        ), 2, listOf(Direction.DOWN)
                    ), BeehiveTreeDecorator(0.01F)
                )
            ).ignoreVines().build()
        )
        ConfiguredFeatureUtil.registerConfiguredFeature<RandomFeatureConfig, Feature<RandomFeatureConfig>>(
            context,
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
        ConfiguredFeatureUtil.registerConfiguredFeature<RandomFeatureConfig, Feature<RandomFeatureConfig>>(
            context, DuskConfiguredFeatures.TREES_OAK_BIRCH_JUNGLE,
            Feature.RANDOM_SELECTOR,
            RandomFeatureConfig(
                listOf(
                    WeightedPlacedFeature(
                        placedFeatures.getHolderOrThrow(TreePlacedFeatures.BIRCH_BEES_0002),
                        0.4f
                    ),
                    WeightedPlacedFeature(
                        placedFeatures.getHolderOrThrow(TreePlacedFeatures.FANCY_OAK_BEES_0002),
                        0.2f
                    ),
                    WeightedPlacedFeature(
                        placedFeatures.getHolderOrThrow(TreePlacedFeatures.JUNGLE_TREE),
                        0.15f
                    )
                ), placedFeatures.getHolderOrThrow(TreePlacedFeatures.OAK_BEES_0002)
            )
        )
        ConfiguredFeatureUtil.registerConfiguredFeature<RandomFeatureConfig, Feature<RandomFeatureConfig>>(
            context, DuskConfiguredFeatures.TREES_OAK_BIRCH_SPRUCE,
            Feature.RANDOM_SELECTOR,
            RandomFeatureConfig(
                listOf(
                    WeightedPlacedFeature(
                        placedFeatures.getHolderOrThrow(TreePlacedFeatures.BIRCH_BEES_0002),
                        0.4f
                    ),
                    WeightedPlacedFeature(
                        placedFeatures.getHolderOrThrow(TreePlacedFeatures.FANCY_OAK_BEES_0002),
                        0.2f
                    ),
                    WeightedPlacedFeature(
                        placedFeatures.getHolderOrThrow(TreePlacedFeatures.SPRUCE_CHECKED),
                        0.15f
                    )
                ), placedFeatures.getHolderOrThrow(TreePlacedFeatures.OAK_BEES_0002)
            )
        )

        fun cherry(): TreeFeatureConfig.Builder {
            return TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.CHERRY_LOG),
                CherryTrunkPlacer(
                    7,
                    1,
                    0,
                    WeightedListIntProvider(
                        DataPool.builder<IntProvider>().method_34975(ConstantIntProvider.create(1), 1)
                            .method_34975(ConstantIntProvider.create(2), 1)
                            .method_34975(ConstantIntProvider.create(3), 1).build()
                    ),
                    UniformIntProvider.create(2, 4),
                    UniformIntProvider.create(-4, -3),
                    UniformIntProvider.create(-1, 0)
                ),
                BlockStateProvider.of(Blocks.CHERRY_LEAVES),
                CherryFoliagePlacer(
                    ConstantIntProvider.create(4),
                    ConstantIntProvider.create(0),
                    ConstantIntProvider.create(5),
                    0.25f,
                    0.5f,
                    0.16666667f,
                    0.33333334f
                ),
                TwoLayersFeatureSize(1, 0, 2)
            )
                .dirtProvider(BlockStateProvider.of(Blocks.SNOW_BLOCK)).ignoreVines()
        }
        ConfiguredFeatureUtil.registerConfiguredFeature(
            context,
            DuskConfiguredFeatures.CHERRY_SNOW,
            Feature.TREE,
            cherry().build()
        )
        ConfiguredFeatureUtil.registerConfiguredFeature<TreeFeatureConfig, Feature<TreeFeatureConfig>>(
            context, DuskConfiguredFeatures.CHERRY_SNOW_BEES, Feature.TREE, cherry().decorators(
                listOf<TreeDecorator>(
                    BeehiveTreeDecorator(0.02f)
                )
            ).build()
        )
        ConfiguredFeatureUtil.registerConfiguredFeature<RandomFeatureConfig, Feature<RandomFeatureConfig>>(
            context, DuskConfiguredFeatures.TREES_SNOWY_CHERRY,
            Feature.RANDOM_SELECTOR,
            RandomFeatureConfig(
                listOf(
                    WeightedPlacedFeature(
                        placedFeatures.getHolderOrThrow(DuskPlacedFeatures.CHERRY_ON_SNOW_BEES),
                        0.4f
                    )
                ), placedFeatures.getHolderOrThrow(DuskPlacedFeatures.CHERRY_ON_SNOW)
            )
        )

        val randomPetal = DataPool.builder<BlockState>()
        for (i in 1..4) {
            val var35: Iterator<*> = Direction.Type.HORIZONTAL.iterator()

            while (var35.hasNext()) {
                val direction = var35.next() as Direction
                randomPetal.method_34975(
                    Blocks.PINK_PETALS.defaultState.with(PinkPetalsBlock.AMOUNT, i)
                        .with(PinkPetalsBlock.FACING, direction), 1
                )
            }
        }
        ConfiguredFeatureUtil.registerConfiguredFeature<RandomPatchFeatureConfig, Feature<RandomPatchFeatureConfig>>(
            context, DuskConfiguredFeatures.FLOWER_SNOWY_CHERRY, Feature.FLOWER, RandomPatchFeatureConfig(
                96, 6, 2, PlacedFeatureUtil.onlyWhenEmpty<SimpleBlockFeatureConfig, Feature<SimpleBlockFeatureConfig>>(
                    Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(
                        WeightedBlockStateProvider(randomPetal.method_34975(Blocks.SNOW.defaultState, 8))
                    )
                )
            )
        )
        ConfiguredFeatureUtil.registerConfiguredFeature(
            context,
            DuskConfiguredFeatures.INVERTED_ICE_SPIKE,
            DuskConfiguredFeatures.INVERTED_SPIKE,
            SpikeFeatureConfig(
                60, 10, 30, BlockStateProvider.of(Blocks.PACKED_ICE), blockTags.getTagOrThrow(BlockTags.REPLACEABLE)
            )
        )
    }
}