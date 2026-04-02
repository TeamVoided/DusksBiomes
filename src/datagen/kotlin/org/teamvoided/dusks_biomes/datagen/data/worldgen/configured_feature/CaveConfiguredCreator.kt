package org.teamvoided.dusks_biomes.datagen.data.worldgen.configured_feature

import net.minecraft.core.Direction
import net.minecraft.core.HolderGetter
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.features.CaveFeatures
import net.minecraft.data.worldgen.features.FeatureUtils
import net.minecraft.data.worldgen.features.VegetationFeatures
import net.minecraft.tags.BlockTags
import net.minecraft.util.random.WeightedList
import net.minecraft.util.valueproviders.*
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.*
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider
import net.minecraft.world.level.levelgen.placement.CaveSurface
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import org.teamvoided.dusks_biomes.data.world.gen.DuskConfiguredFeatures
import org.teamvoided.dusks_biomes.datagen.data.worldgen.ConfiguredFeatureCreator.inline
import org.teamvoided.dusks_biomes.datagen.data.worldgen.ConfiguredFeatureCreator.registerConfiguredFeature

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
                        .add(Blocks.PALE_OAK_SAPLING.defaultBlockState(), 4)
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
                0.8f,
                UniformInt.of(4, 7),
                0.3f
            )
        )
        this.registerConfiguredFeature(
            DuskConfiguredFeatures.PALE_CAVE_CLAY_PATCH,
            Feature.VEGETATION_PATCH,
            VegetationPatchConfiguration(
                BlockTags.LUSH_GROUND_REPLACEABLE,
                BlockStateProvider.simple(Blocks.CLAY),
                cf.inline(DuskConfiguredFeatures.PALE_CAVE_MOSS_VEGETATION),
                CaveSurface.FLOOR,
                ConstantInt.of(3),
                0.8f,
                2,
                0.05f,
                UniformInt.of(4, 7),
                0.7f
            )
        )
        this.registerConfiguredFeature(
            DuskConfiguredFeatures.PALE_CAVE_CLAY_POOL,
            Feature.WATERLOGGED_VEGETATION_PATCH,
            VegetationPatchConfiguration(
                BlockTags.LUSH_GROUND_REPLACEABLE,
                BlockStateProvider.simple(Blocks.CLAY),
                cf.inline(DuskConfiguredFeatures.PALE_CAVE_MOSS_VEGETATION),
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
            DuskConfiguredFeatures.PALE_CAVE_CLAY,
            Feature.RANDOM_BOOLEAN_SELECTOR,
            RandomBooleanFeatureConfiguration(
                cf.inline(DuskConfiguredFeatures.PALE_CAVE_CLAY_PATCH),
                cf.inline(DuskConfiguredFeatures.PALE_CAVE_CLAY_POOL)
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
            Feature.BLOCK_COLUMN,
            BlockColumnConfiguration(
                listOf(
                    BlockColumnConfiguration.layer(
                        ConstantInt.of(1),
                        BlockStateProvider.simple(Blocks.PALE_OAK_LOG)
                    ),
                    BlockColumnConfiguration.layer(
                        ConstantInt.of(1),
                        BlockStateProvider.simple(Blocks.CREAKING_HEART)
                    ),
                    BlockColumnConfiguration.layer(
                        ConstantInt.of(1),
                        BlockStateProvider.simple(Blocks.PALE_OAK_LOG)
                    )
                ),
                Direction.DOWN,
                BlockPredicate.alwaysTrue(),
                false
            )
        )
        this.registerConfiguredFeature(
            DuskConfiguredFeatures.PALE_CAVE_FLOWERS,
            Feature.RANDOM_PATCH,
            FeatureUtils.simplePatchConfiguration(
                Feature.SIMPLE_BLOCK,
                SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.OPEN_EYEBLOSSOM))
            )
        )


        //this.registerConfiguredFeature(
        //    CaveFeatures.ROOTED_AZALEA_TREE,
        //    Feature.ROOT_SYSTEM,
        //    RootSystemConfiguration(
        //        PlacementUtils.inlinePlaced(
        //            holderGetter.getOrThrow(TreeFeatures.AZALEA_TREE),
        //            *arrayOfNulls<PlacementModifier>(0)
        //        ),
        //        3,
        //        3,
        //        BlockTags.AZALEA_ROOT_REPLACEABLE,
        //        BlockStateProvider.simple(Blocks.ROOTED_DIRT),
        //        20,
        //        100,
        //        3,
        //        2,
        //        BlockStateProvider.simple(Blocks.HANGING_ROOTS),
        //        20,
        //        2,
        //        BlockPredicate.allOf(
        //            BlockPredicate.anyOf(
        //                BlockPredicate.matchesBlocks(
        //                    List.of<Block?>(
        //                        Blocks.AIR,
        //                        Blocks.CAVE_AIR,
        //                        Blocks.VOID_AIR
        //                    )
        //                ), BlockPredicate.matchesTag(BlockTags.REPLACEABLE_BY_TREES)
        //            ), BlockPredicate.matchesTag(Direction.DOWN.getUnitVec3i(), BlockTags.AZALEA_GROWS_ON)
        //        )
        //    )
        //)
    }
}