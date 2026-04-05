package org.teamvoided.dusks_biomes.datagen.data.worldgen.configured_feature

import net.minecraft.core.Direction
import net.minecraft.core.HolderGetter
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.features.FeatureUtils
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.BlockTags
import net.minecraft.util.random.WeightedList
import net.minecraft.util.valueproviders.*
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature
import net.minecraft.world.level.levelgen.feature.configurations.*
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter
import net.minecraft.world.level.levelgen.placement.CaveSurface
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import org.teamvoided.dusks_biomes.data.world.gen.DuskConfiguredFeatures
import org.teamvoided.dusks_biomes.datagen.data.worldgen.ConfiguredFeatureCreator.inline
import org.teamvoided.dusks_biomes.datagen.data.worldgen.ConfiguredFeatureCreator.registerConfiguredFeature
import org.teamvoided.dusks_biomes.init.DuskFeatures
import org.teamvoided.dusks_biomes.world.level.levelgen.config.MultifaceFeatureConfig

object CaveConfiguredCreator {
    fun BootstrapContext<ConfiguredFeature<*, *>>.caves() {
        val cf = this.lookup(Registries.CONFIGURED_FEATURE)
        val pf = this.lookup(Registries.PLACED_FEATURE)
        this.pale(cf, pf)
    }

    private fun BootstrapContext<ConfiguredFeature<*, *>>.pale(
        cf: HolderGetter<ConfiguredFeature<*, *>>,
        pf: HolderGetter<PlacedFeature>
    ) {
        val hangingMoss = BlockStateProvider.simple(
            Blocks.PALE_HANGING_MOSS.defaultBlockState()
                .setValue(BlockStateProperties.TIP, false)
        )
        val hangingMossTip =
            BlockColumnConfiguration.layer(ConstantInt.of(1), BlockStateProvider.simple(Blocks.PALE_HANGING_MOSS))
        val hangingMossColumnList = listOf(
            BlockColumnConfiguration.layer(
                WeightedListInt(
                    WeightedList.builder<IntProvider>()
                        .add(UniformInt.of(0, 19), 2)
                        .add(UniformInt.of(0, 2), 3)
                        .add(UniformInt.of(0, 6), 10).build()
                ),
                hangingMoss
            ),
            hangingMossTip
        )
        this.registerConfiguredFeature(
            DuskConfiguredFeatures.PALE_CAVE_VINES,
            Feature.BLOCK_COLUMN,
            BlockColumnConfiguration(hangingMossColumnList, Direction.DOWN, BlockPredicate.ONLY_IN_AIR_PREDICATE, true)
        )
        this.registerConfiguredFeature(
            DuskConfiguredFeatures.PALE_CAVE_VINE_IN_MOSS,
            Feature.BLOCK_COLUMN,
            BlockColumnConfiguration(
                listOf(
                    BlockColumnConfiguration.layer(
                        WeightedListInt(
                            WeightedList.builder<IntProvider>()
                                .add(UniformInt.of(0, 3), 5)
                                .add(UniformInt.of(1, 7), 1)
                                .build()
                        ),
                        hangingMoss
                    ),
                    hangingMossTip
                ), Direction.DOWN, BlockPredicate.ONLY_IN_AIR_PREDICATE, true
            )
        )
        this.registerConfiguredFeature(
            DuskConfiguredFeatures.PALE_CAVE_MOSS_VEGETATION,
            Feature.SIMPLE_BLOCK,
            SimpleBlockConfiguration(
                WeightedStateProvider(
                    WeightedList.builder<BlockState>()
                        .add(
                            Blocks.PALE_OAK_LEAVES.defaultBlockState()
                                .setValue(BlockStateProperties.PERSISTENT, true), 7
                        )
                        .add(
                            Blocks.DARK_OAK_LEAVES.defaultBlockState()
                                .setValue(BlockStateProperties.PERSISTENT, true), 7
                        )
                        .add(Blocks.OPEN_EYEBLOSSOM.defaultBlockState(), 4)
                        .add(Blocks.PALE_OAK_SAPLING.defaultBlockState())
                        .add(Blocks.PALE_MOSS_CARPET.defaultBlockState(), 25)
                        .add(Blocks.SHORT_GRASS.defaultBlockState(), 25)
                        .add(Blocks.TALL_GRASS.defaultBlockState(), 10)
                )
            )
        )
        this.registerConfiguredFeature(
            DuskConfiguredFeatures.PALE_CAVE_MOSS_PATCH,
            Feature.VEGETATION_PATCH,
            VegetationPatchConfiguration(
                BlockTags.MOSS_REPLACEABLE,
                BlockStateProvider.simple(Blocks.PALE_MOSS_BLOCK),
                cf.inline(DuskConfiguredFeatures.PALE_CAVE_MOSS_VEGETATION),
                CaveSurface.FLOOR,
                ConstantInt.of(1),
                0f,
                5,
                0.5f,
                UniformInt.of(4, 7),
                0.3f
            )
        )
        this.registerConfiguredFeature(
            DuskConfiguredFeatures.PALE_CAVE_POOL,
            Feature.WATERLOGGED_VEGETATION_PATCH,
            VegetationPatchConfiguration(
                BlockTags.LUSH_GROUND_REPLACEABLE,
                BlockStateProvider.simple(Blocks.PALE_MOSS_BLOCK),
                cf.inline(DuskConfiguredFeatures.PALE_CAVE_LEAVES),
                CaveSurface.FLOOR,
                ConstantInt.of(3),
                0.8f,
                5,
                0.1f,
                UniformInt.of(4, 7),
                0.7f
            )
        )
        this.registerConfiguredFeature(
            DuskConfiguredFeatures.PALE_CAVE_GRASS_PATCH,
            Feature.VEGETATION_PATCH,
            VegetationPatchConfiguration(
                BlockTags.LUSH_GROUND_REPLACEABLE,
                BlockStateProvider.simple(Blocks.GRASS_BLOCK),
                cf.inline(DuskConfiguredFeatures.PALE_CAVE_MOSS_VEGETATION),
                CaveSurface.FLOOR,
                ConstantInt.of(1),
                0f,
                5,
                0.4f,
                UniformInt.of(4, 7),
                0.7f
            )
        )

        this.registerConfiguredFeature(
            DuskConfiguredFeatures.PALE_CAVE_VEGETATION,
            Feature.RANDOM_SELECTOR,
            RandomFeatureConfiguration(
                listOf(
                    WeightedPlacedFeature(cf.inline(DuskConfiguredFeatures.PALE_CAVE_POOL), 0.1f),
                    WeightedPlacedFeature(cf.inline(DuskConfiguredFeatures.PALE_CAVE_GRASS_PATCH), 0.5f)
                ),
                PlacementUtils.inlinePlaced(cf.getOrThrow(DuskConfiguredFeatures.PALE_CAVE_MOSS_PATCH))
            )
        )
        this.registerConfiguredFeature(
            DuskConfiguredFeatures.PALE_CAVE_MOSS_PATCH_CEILING,
            Feature.VEGETATION_PATCH,
            VegetationPatchConfiguration(
                BlockTags.MOSS_REPLACEABLE,
                BlockStateProvider.simple(Blocks.PALE_MOSS_BLOCK),
                cf.inline(DuskConfiguredFeatures.PALE_CAVE_VINE_IN_MOSS),
                CaveSurface.CEILING,
                UniformInt.of(1, 2),
                0f,
                5,
                0.08f,
                UniformInt.of(4, 7),
                0.3f
            )
        )
        this.registerConfiguredFeature(
            DuskConfiguredFeatures.PALE_CAVE_HEART_CEILING,
            DuskFeatures.BURIED_HEART,
            NoneFeatureConfiguration()
        )
        this.registerConfiguredFeature(
            DuskConfiguredFeatures.PALE_CAVE_RESIN,
            DuskFeatures.MULTIFACE_GROWTH,
            MultifaceFeatureConfig(BlockStateProvider.simple(Blocks.RESIN_CLUMP))
        )
        this.registerConfiguredFeature(
            DuskConfiguredFeatures.PALE_CAVE_FLOWERS,
            Feature.RANDOM_PATCH,
            FeatureUtils.simplePatchConfiguration(
                Feature.SIMPLE_BLOCK,
                SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.OPEN_EYEBLOSSOM))
            )
        )
        this.registerConfiguredFeature(
            DuskConfiguredFeatures.PALE_CAVE_ROOTS,
            DuskFeatures.CREEPING_VINE,
            NoneFeatureConfiguration()
        )



        this.leafCollumPatch(DuskConfiguredFeatures.PALE_CAVE_PALE_LEAVES, Blocks.PALE_OAK_LEAVES, Direction.UP)
        this.leafCollumPatch(DuskConfiguredFeatures.PALE_CAVE_DARK_LEAVES, Blocks.DARK_OAK_LEAVES, Direction.UP)
        this.registerConfiguredFeature(
            DuskConfiguredFeatures.PALE_CAVE_LEAVES,
            Feature.RANDOM_BOOLEAN_SELECTOR,
            RandomBooleanFeatureConfiguration(
                cf.inline(DuskConfiguredFeatures.PALE_CAVE_PALE_LEAVES),
                cf.inline(DuskConfiguredFeatures.PALE_CAVE_DARK_LEAVES)
            )
        )

        this.leafCollumPatch(
            DuskConfiguredFeatures.PALE_CAVE_PALE_LEAVES_CEILING,
            Blocks.PALE_OAK_LEAVES,
            Direction.DOWN
        )
        this.leafCollumPatch(
            DuskConfiguredFeatures.PALE_CAVE_DARK_LEAVES_CEILING,
            Blocks.DARK_OAK_LEAVES,
            Direction.DOWN
        )
        this.registerConfiguredFeature(
            DuskConfiguredFeatures.PALE_CAVE_LEAVES_CEILING,
            Feature.RANDOM_BOOLEAN_SELECTOR,
            RandomBooleanFeatureConfiguration(
                cf.inline(DuskConfiguredFeatures.PALE_CAVE_PALE_LEAVES_CEILING),
                cf.inline(DuskConfiguredFeatures.PALE_CAVE_DARK_LEAVES_CEILING)
            )
        )
    }

    private fun BootstrapContext<ConfiguredFeature<*, *>>.leafCollumPatch(
        feature: ResourceKey<ConfiguredFeature<*, *>>,
        leaves: Block,
        dir: Direction
    ) {
        this.registerConfiguredFeature(
            feature,
            Feature.RANDOM_PATCH,
            FeatureUtils.simpleRandomPatchConfiguration(
                15,
                PlacementUtils.inlinePlaced(
                    Feature.BLOCK_COLUMN,
                    BlockColumnConfiguration(
                        listOf(
                            BlockColumnConfiguration.layer(
                                BiasedToBottomInt.of(1, 4),
                                BlockStateProvider.simple(
                                    leaves.defaultBlockState().setValue(BlockStateProperties.PERSISTENT, true)
                                )
                            )
                        ), dir, BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, false
                    ),
                    BlockPredicateFilter.forPredicate(
                        BlockPredicate.allOf(
                            BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE,
                            BlockPredicate.hasSturdyFace(dir.opposite.unitVec3i, dir)
                        )
                    )
                )
            )
        )
    }


}