package org.teamvoided.dusk_autumns_worldgen.datagen.worldgen

import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.MangrovePropaguleBlock
import net.minecraft.block.PinkPetalsBlock
import net.minecraft.entity.EntityType
import net.minecraft.loot.LootTables
import net.minecraft.registry.HolderSet
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.BlockTags
import net.minecraft.structure.processor.StructureProcessorLists
import net.minecraft.structure.rule.TagMatchRuleTest
import net.minecraft.util.collection.DataPool
import net.minecraft.util.math.Direction
import net.minecraft.util.math.float_provider.UniformFloatProvider
import net.minecraft.util.math.int_provider.ConstantIntProvider
import net.minecraft.util.math.int_provider.IntProvider
import net.minecraft.util.math.int_provider.UniformIntProvider
import net.minecraft.util.math.int_provider.WeightedListIntProvider
import net.minecraft.world.Heightmap
import net.minecraft.world.gen.BootstrapContext
import net.minecraft.world.gen.blockpredicate.BlockPredicate
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize
import net.minecraft.world.gen.feature.util.PlacedFeatureUtil
import net.minecraft.world.gen.foliage.BlobFoliagePlacer
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
import net.minecraft.world.gen.trunk.CherryTrunkPlacer
import net.minecraft.world.gen.trunk.StraightTrunkPlacer
import net.minecraft.world.gen.trunk.UpwardsBranchingTrunkPlacer
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.id
import org.teamvoided.dusk_autumns_worldgen.data.DuskBlockTags
import org.teamvoided.dusk_autumns_worldgen.init.worldgen.DuskConfiguredFeatures
import org.teamvoided.dusk_autumns_worldgen.init.worldgen.DuskPlacedFeatures
import org.teamvoided.dusk_autumns_worldgen.init.worldgen.VoidFeatures
import org.teamvoided.dusk_autumns_worldgen.worldgen.configured_feature.config.LargeCavePillarFeatureConfig
import org.teamvoided.dusk_autumns_worldgen.worldgen.configured_feature.config.MonsterRoomFeatureConfig
import org.teamvoided.dusk_autumns_worldgen.worldgen.configured_feature.config.SpikeFeatureConfig
import org.teamvoided.dusk_autumns_worldgen.worldgen.configured_feature.config.StructurePieceFeatureConfig
import java.util.*

@Suppress("DEPRECATION")
object ConfiguredFeatureCreator {
    fun bootstrap(c: BootstrapContext<ConfiguredFeature<*, *>>) {
        val blockTags = c.lookup(RegistryKeys.BLOCK)
        val configuredFeatures = c.lookup(RegistryKeys.CONFIGURED_FEATURE)
        val placedFeatures = c.lookup(RegistryKeys.PLACED_FEATURE)
        val procLists = c.lookup(RegistryKeys.STRUCTURE_PROCESSOR_LIST)

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
            DuskConfiguredFeatures.CHERRY_SNOW,
            Feature.TREE,
            cherry().build()
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.CHERRY_SNOW_BEES,
            Feature.TREE,
            cherry()
                .decorators(listOf(BeehiveTreeDecorator(0.02f)))
                .build()
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.TREES_SNOWY_CHERRY,
            Feature.RANDOM_SELECTOR,
            RandomFeatureConfig(
                listOf(
                    WeightedPlacedFeature(placedFeatures.getHolderOrThrow(DuskPlacedFeatures.CHERRY_ON_SNOW_BEES), 0.4f)
                ), placedFeatures.getHolderOrThrow(DuskPlacedFeatures.CHERRY_ON_SNOW)
            )
        )

        val randomPetal = DataPool.builder<BlockState>()
        for (i in 1..4) {
            Direction.Type.HORIZONTAL.forEach {
                randomPetal.method_34975(
                    Blocks.PINK_PETALS.defaultState.with(PinkPetalsBlock.AMOUNT, i).with(PinkPetalsBlock.FACING, it),
                    1
                )
            }
        }
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.FLOWER_SNOWY_CHERRY, Feature.FLOWER, RandomPatchFeatureConfig(
                96, 6, 2, PlacedFeatureUtil.onlyWhenEmpty(
                    Feature.SIMPLE_BLOCK, SimpleBlockFeatureConfig(
                        WeightedBlockStateProvider(randomPetal.method_34975(Blocks.SNOW.defaultState, 8))
                    )
                )
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.ICE_SPIKE,
            VoidFeatures.SPIKE,
            SpikeFeatureConfig(
                60, 10, 30,
                BlockStateProvider.of(Blocks.PACKED_ICE),
                blockTags.getTagOrThrow(DuskBlockTags.ICE_SPIKE_PLACEABLE_BLOCKS)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.INVERTED_ICE_SPIKE,
            VoidFeatures.INVERTED_SPIKE,
            SpikeFeatureConfig(
                60, 10, 30,
                BlockStateProvider.of(Blocks.PACKED_ICE),
                blockTags.getTagOrThrow(DuskBlockTags.ICE_SPIKE_PLACEABLE_BLOCKS)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.BLUE_ICE_SPIKE,
            VoidFeatures.SPIKE,
            SpikeFeatureConfig(
                5, 10, 30,
                BlockStateProvider.of(Blocks.BLUE_ICE),
                blockTags.getTagOrThrow(DuskBlockTags.ICE_SPIKE_PLACEABLE_BLOCKS)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.INVERTED_BLUE_ICE_SPIKE,
            VoidFeatures.INVERTED_SPIKE,
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
            DuskConfiguredFeatures.SAND_CAVE_PILLAR,
            VoidFeatures.LARGE_CAVE_PILLAR,
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
                BlockStateProvider.of(Blocks.SANDSTONE)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.SAND_SPIKES,
            Feature.BLOCK_COLUMN,
            BlockColumnFeatureConfig(
                listOf(
                    BlockColumnFeatureConfig.createLayer(
                        UniformIntProvider.create(1, 7),
                        BlockStateProvider.of(Blocks.SANDSTONE.defaultState)
                    ),
                    BlockColumnFeatureConfig.createLayer(
                        UniformIntProvider.create(1, 5),
                        BlockStateProvider.of(Blocks.SANDSTONE_WALL.defaultState)
                    )
                ),
                Direction.UP, BlockPredicate.IS_AIR, false
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.SAND_SPIKES_ROOF,
            Feature.BLOCK_COLUMN,
            BlockColumnFeatureConfig(
                listOf(
                    BlockColumnFeatureConfig.createLayer(
                        UniformIntProvider.create(1, 7),
                        BlockStateProvider.of(Blocks.SANDSTONE.defaultState)
                    ),
                    BlockColumnFeatureConfig.createLayer(
                        UniformIntProvider.create(1, 5),
                        BlockStateProvider.of(Blocks.SANDSTONE_WALL.defaultState)
                    )
                ),
                Direction.DOWN, BlockPredicate.IS_AIR, false
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.RED_SAND_CAVE_PILLAR,
            VoidFeatures.LARGE_CAVE_PILLAR,
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
                BlockStateProvider.of(Blocks.RED_SANDSTONE)
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.RED_SAND_SPIKES,
            Feature.BLOCK_COLUMN,
            BlockColumnFeatureConfig(
                listOf(
                    BlockColumnFeatureConfig.createLayer(
                        UniformIntProvider.create(3, 7),
                        BlockStateProvider.of(Blocks.RED_SANDSTONE.defaultState)
                    ),
                    BlockColumnFeatureConfig.createLayer(
                        UniformIntProvider.create(1, 5),
                        BlockStateProvider.of(Blocks.RED_SANDSTONE_WALL.defaultState)
                    )
                ),
                Direction.UP, BlockPredicate.IS_AIR, false
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.RED_SAND_SPIKES_ROOF,
            Feature.BLOCK_COLUMN,
            BlockColumnFeatureConfig(
                listOf(
                    BlockColumnFeatureConfig.createLayer(
                        UniformIntProvider.create(3, 7),
                        BlockStateProvider.of(Blocks.RED_SANDSTONE.defaultState)
                    ),
                    BlockColumnFeatureConfig.createLayer(
                        UniformIntProvider.create(1, 5),
                        BlockStateProvider.of(Blocks.RED_SANDSTONE_WALL.defaultState)
                    )
                ),
                Direction.DOWN, BlockPredicate.IS_AIR, false
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.TEST_CAVE_PILLAR,
            VoidFeatures.LARGE_CAVE_PILLAR,
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
                BlockStateProvider.of(Blocks.DIAMOND_BLOCK)
            )
        )

//Monster Room features
        val defaultMonstersRoom = listOf(EntityType.SKELETON, EntityType.ZOMBIE, EntityType.ZOMBIE, EntityType.SPIDER)
        val lushMonstersRoom = defaultMonstersRoom + listOf(/*put stuff here*/)
        val frozenMonstersRoom = listOf(EntityType.STRAY, EntityType.ZOMBIE, EntityType.ZOMBIE, EntityType.SPIDER)
        val sandMonstersRoom = listOf(EntityType.SKELETON, EntityType.HUSK, EntityType.HUSK, EntityType.SPIDER)

        c.registerConfiguredFeature(
            DuskConfiguredFeatures.DEEP_MONSTER_ROOM,
            VoidFeatures.MONSTER_ROOM,
            MonsterRoomFeatureConfig(
                BlockStateProvider.of(Blocks.COBBLED_DEEPSLATE),
                BlockStateProvider.of(Blocks.TUFF),
                defaultMonstersRoom,
                LootTables.SIMPLE_DUNGEON_CHEST
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.LUSH_MONSTER_ROOM,
            VoidFeatures.MONSTER_ROOM,
            MonsterRoomFeatureConfig(
                BlockStateProvider.of(Blocks.MOSSY_COBBLESTONE),
                BlockStateProvider.of(Blocks.MUD),
                lushMonstersRoom,
                LootTables.SIMPLE_DUNGEON_CHEST
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.DEEP_LUSH_MONSTER_ROOM,
            VoidFeatures.MONSTER_ROOM,
            MonsterRoomFeatureConfig(
                BlockStateProvider.of(Blocks.COBBLED_DEEPSLATE),
                BlockStateProvider.of(Blocks.MUD),
                lushMonstersRoom,
                LootTables.SIMPLE_DUNGEON_CHEST
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.FROZEN_MONSTER_ROOM,
            VoidFeatures.MONSTER_ROOM,
            MonsterRoomFeatureConfig(
                BlockStateProvider.of(Blocks.COBBLESTONE),
                BlockStateProvider.of(Blocks.SNOW_BLOCK),
                frozenMonstersRoom,
                LootTables.SIMPLE_DUNGEON_CHEST
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.DEEP_FROZEN_MONSTER_ROOM,
            VoidFeatures.MONSTER_ROOM,
            MonsterRoomFeatureConfig(
                BlockStateProvider.of(Blocks.COBBLED_DEEPSLATE),
                BlockStateProvider.of(Blocks.PACKED_ICE),
                frozenMonstersRoom,
                LootTables.SIMPLE_DUNGEON_CHEST
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.SAND_MONSTER_ROOM,
            VoidFeatures.MONSTER_ROOM,
            MonsterRoomFeatureConfig(
                BlockStateProvider.of(Blocks.SANDSTONE),
                BlockStateProvider.of(Blocks.SAND),
                sandMonstersRoom,
                LootTables.SIMPLE_DUNGEON_CHEST
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.RED_SAND_MONSTER_ROOM,
            VoidFeatures.MONSTER_ROOM,
            MonsterRoomFeatureConfig(
                BlockStateProvider.of(Blocks.RED_SANDSTONE),
                BlockStateProvider.of(Blocks.RED_SAND),
                sandMonstersRoom,
                LootTables.SIMPLE_DUNGEON_CHEST
            )
        )
//Structure Piece features
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.DESERT_WELL,
            VoidFeatures.STRUCTURE_PIECE,
            StructurePieceFeatureConfig(
                id("feature/desert_well"),
                procEmpty,
                8,
                Heightmap.Type.OCEAN_FLOOR_WG
            )
        )
        c.registerConfiguredFeature(
            DuskConfiguredFeatures.RED_DESERT_WELL,
            VoidFeatures.STRUCTURE_PIECE,
            StructurePieceFeatureConfig(
                id("feature/red_desert_well"),
                procEmpty,
                8,
                Heightmap.Type.OCEAN_FLOOR_WG,
            )
        )
    }

    private fun cherry(): TreeFeatureConfig.Builder {
        return TreeFeatureConfig.Builder(
            BlockStateProvider.of(Blocks.CHERRY_LOG),
            CherryTrunkPlacer(
                7, 1, 0,
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
                0.25f, 0.5f,
                0.16666667f, 0.33333334f
            ),
            TwoLayersFeatureSize(1, 0, 2)
        ).dirtProvider(BlockStateProvider.of(Blocks.SNOW_BLOCK)).ignoreVines()
    }

    private fun <FC : FeatureConfig, F : Feature<FC>> BootstrapContext<ConfiguredFeature<*, *>>.registerConfiguredFeature(
        registryKey: RegistryKey<ConfiguredFeature<*, *>>,
        feature: F,
        featureConfig: FC
    ): Any = this.register(registryKey, ConfiguredFeature(feature, featureConfig))

    private fun BootstrapContext<ConfiguredFeature<*, *>>.registerConfiguredFeature(
        registryKey: RegistryKey<ConfiguredFeature<*, *>>, feature: Feature<DefaultFeatureConfig>
    ) = this.registerConfiguredFeature(registryKey, feature, FeatureConfig.DEFAULT)

}