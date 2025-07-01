package org.teamvoided.dusks_biomes.data.gen.world.gen

import com.google.common.collect.ImmutableList
import net.minecraft.block.Blocks
import net.minecraft.registry.Registerable
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.registry.tag.BlockTags
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.intprovider.ConstantIntProvider
import net.minecraft.util.math.intprovider.UniformIntProvider
import net.minecraft.world.gen.YOffset
import net.minecraft.world.gen.blockpredicate.BlockPredicate
import net.minecraft.world.gen.blockpredicate.BlockPredicate.not
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.feature.PlacedFeature
import net.minecraft.world.gen.placementmodifier.*
import org.teamvoided.dusks_biomes.data.tags.DuskBlockTags
import org.teamvoided.dusks_biomes.data.world.gen.DuskConfiguredFeatures
import org.teamvoided.dusks_biomes.data.world.gen.DuskPlacedFeatures

@Suppress("MagicNumber")
object PlacedFeatureCreator {
    fun bootstrap(c: Registerable<PlacedFeature>) {

        val configuredFeatureProvider = c.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE)

        c.register(
            DuskPlacedFeatures.SWAMP_VILLAGE_ROCK,
            configuredFeatureProvider.getOrThrow(MiscConfiguredFeatures.FOREST_ROCK)
        )
        c.register(
            DuskPlacedFeatures.SWAMP_VILLAGE_OAK,
            configuredFeatureProvider.getOrThrow(TreeConfiguredFeatures.SWAMP_OAK),
            PlacedFeatures.wouldSurvive(Blocks.OAK_SAPLING)
        )
        c.register(
            DuskPlacedFeatures.SWAMP_VILLAGE_MANGROVE,
            configuredFeatureProvider.getOrThrow(VegetationConfiguredFeatures.MANGROVE_VEGETATION),
            PlacedFeatures.wouldSurvive(Blocks.MANGROVE_PROPAGULE)
        )
        c.register(
            DuskPlacedFeatures.SWAMP_VILLAGE_FLOWERS,
            configuredFeatureProvider.getOrThrow(VegetationConfiguredFeatures.FLOWER_SWAMP)
        )
        c.register(
            DuskPlacedFeatures.COBBLESTONE_ROCK,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.COBBLESTONE_ROCK),
            CountPlacementModifier.of(2),
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of()

        )
        c.register(
            DuskPlacedFeatures.TREES_WINDSWEPT_BIRCH,
            configuredFeatureProvider.getOrThrow(VegetationConfiguredFeatures.BIRCH_TALL),
            VegetationPlacedFeatures.treeModifiers(
                PlacedFeatures.createCountExtraModifier(2, 0.1f, 1)
            )
        )
        c.register(
            DuskPlacedFeatures.TREES_FROZEN_BADLANDS,
            configuredFeatureProvider.getOrThrow(TreeConfiguredFeatures.SPRUCE),
            VegetationPlacedFeatures.treeModifiersWithWouldSurvive(
                PlacedFeatures.createCountExtraModifier(5, 0.1f, 1),
                Blocks.SPRUCE_SAPLING
            )
        )
        c.register(
            DuskPlacedFeatures.MANGROVE_FROZEN_CHECKED,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.MANGROVE_FROZEN_CHECKED),
            BlockFilterPlacementModifier.of(
                BlockPredicate.wouldSurvive(Blocks.MANGROVE_PROPAGULE.defaultState, BlockPos.ORIGIN)
            )
        )
        c.register(
            DuskPlacedFeatures.TALL_MANGROVE_FROZEN_CHECKED,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.TALL_MANGROVE_FROZEN_CHECKED),
            BlockFilterPlacementModifier.of(
                BlockPredicate.wouldSurvive(Blocks.MANGROVE_PROPAGULE.defaultState, BlockPos.ORIGIN)
            )
        )
        c.register(
            DuskPlacedFeatures.TREES_MANGROVE_FROZEN,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.MANGROVE_FROZEN_VEGETATION),
            CountPlacementModifier.of(15),
            SquarePlacementModifier.of(),
            SurfaceWaterDepthFilterPlacementModifier.of(5),
            PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
            BiomePlacementModifier.of(),
            BlockFilterPlacementModifier.of(
                BlockPredicate.wouldSurvive(Blocks.MANGROVE_PROPAGULE.defaultState, BlockPos.ORIGIN)
            )
        )
        c.register(
            DuskPlacedFeatures.TREES_COLD_FOREST,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.TREES_OAK_BIRCH_SPRUCE),
            VegetationPlacedFeatures.treeModifiers(
                PlacedFeatures.createCountExtraModifier(10, 0.1f, 1)
            )
        )
        c.register(
            DuskPlacedFeatures.TREES_COLD_PLAINS,
            configuredFeatureProvider.getOrThrow(VegetationConfiguredFeatures.TREES_WINDSWEPT_HILLS),
            VegetationPlacedFeatures.treeModifiers(
                PlacedFeatures.createCountExtraModifier(0, 0.05f, 1)
            )
        )
        c.register(
            DuskPlacedFeatures.TREES_WARM_FOREST,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.TREES_OAK_BIRCH_ACACIA),
            VegetationPlacedFeatures.treeModifiers(
                PlacedFeatures.createCountExtraModifier(10, 0.1f, 1)
            )
        )
        c.register(
            DuskPlacedFeatures.TREES_WARM_PLAINS,
            configuredFeatureProvider.getOrThrow(VegetationConfiguredFeatures.TREES_SAVANNA),
            VegetationPlacedFeatures.treeModifiers(
                PlacedFeatures.createCountExtraModifier(0, 0.05f, 1)
            )
        )
        c.register(
            DuskPlacedFeatures.TREES_SNOWY_DARK_GROVE,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.TREES_OAK_DARK_SPRUCE),
            VegetationPlacedFeatures.treeModifiers(
                CountPlacementModifier.of(16)
            )
        )
        c.register(
            DuskPlacedFeatures.TREES_SNOWY_DARK_GROVE_ON_SNOW,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.TREES_OAK_DARK_SPRUCE_ON_SNOW),
            CountPlacementModifier.of(16),
            SquarePlacementModifier.of(),
            SurfaceWaterDepthFilterPlacementModifier.of(0),
            PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
            BiomePlacementModifier.of(),
            EnvironmentScanPlacementModifier.of(
                Direction.UP, not(
                    BlockPredicate.matchingBlocks(*arrayOf(Blocks.POWDER_SNOW))
                ), 8
            ),
            BlockFilterPlacementModifier.of(
                BlockPredicate.matchingBlocks(
                    Direction.DOWN.vector,
                    *arrayOf(Blocks.SNOW_BLOCK, Blocks.POWDER_SNOW)
                )

            )
        )
        c.register(
            DuskPlacedFeatures.TREES_SNOWY_CHERRY_GROVE,
            configuredFeatureProvider.getOrThrow(TreeConfiguredFeatures.CHERRY_BEES_005),
            PlacedFeatures.createCountExtraModifier(10, 0.1f, 1),
            SquarePlacementModifier.of(),
            SurfaceWaterDepthFilterPlacementModifier.of(0),
            PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
            BiomePlacementModifier.of(),
            EnvironmentScanPlacementModifier.of(
                Direction.UP, not(
                    BlockPredicate.matchingBlocks(*arrayOf(Blocks.POWDER_SNOW))
                ), 8
            ),
            BlockFilterPlacementModifier.of(
                BlockPredicate.matchingBlocks(
                    Direction.DOWN.vector,
                    *arrayOf(Blocks.SNOW_BLOCK, Blocks.POWDER_SNOW)
                )

            )
        )
        c.register(
            DuskPlacedFeatures.FLOWER_SNOWY_CHERRY,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.FLOWER_SNOWY_CHERRY),
            NoiseThresholdCountPlacementModifier.of(-0.8, 5, 10),
            RarityFilterPlacementModifier.of(5),
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of()
        )
        c.register(
            DuskPlacedFeatures.MUSHROOM_GROVE_VEGETATION,
            configuredFeatureProvider.getOrThrow(VegetationConfiguredFeatures.MUSHROOM_ISLAND_VEGETATION),
            CountPlacementModifier.of(12),
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of()
        )

        c.register(
            DuskPlacedFeatures.CAVE_DEAD_BUSH,
            configuredFeatureProvider.getOrThrow(VegetationConfiguredFeatures.PATCH_DEAD_BUSH),
            CountPlacementModifier.of(UniformIntProvider.create(124, 177)),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_120_RANGE,
            BiomePlacementModifier.of()
        )
        c.register(
            DuskPlacedFeatures.CAVE_GLOW_LICHEN_EXTRA,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.CAVE_GLOW_LICHEN_EXTRA),
            CountPlacementModifier.of(UniformIntProvider.create(3, 33)),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_120_RANGE,
            BiomePlacementModifier.of()
        )
        c.register(
            DuskPlacedFeatures.ORE_COARSE_DIRT,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.ORE_COARSE_DIRT),
            RarityFilterPlacementModifier.of(3),
            SquarePlacementModifier.of(),
            HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(160)),
            BiomePlacementModifier.of()
        )
        c.register(
            DuskPlacedFeatures.MUSHROOM_CAVE_VEGETATION,
            configuredFeatureProvider.getOrThrow(VegetationConfiguredFeatures.MUSHROOM_ISLAND_VEGETATION),
            CountPlacementModifier.of(125),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_120_RANGE,
            EnvironmentScanPlacementModifier.of(
                Direction.DOWN,
                BlockPredicate.solid(),
                BlockPredicate.IS_AIR,
                12
            ),
            RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)),
            BiomePlacementModifier.of()
        )

        c.register(
            DuskPlacedFeatures.MUSHROOM_CAVE_MUSHROOMS,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.MUSHROOM_CAVE_MUSHROOMS),
            CountPlacementModifier.of(33),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_120_RANGE,
            EnvironmentScanPlacementModifier.of(
                Direction.DOWN,
                BlockPredicate.solid(),
                BlockPredicate.IS_AIR,
                12
            ),
            RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(1)),
            BiomePlacementModifier.of()
        )

        c.register(
            DuskPlacedFeatures.MUSHROOM_CAVE_SURFACE,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.MUSHROOM_CAVE_ROOTS),
            CountPlacementModifier.of(UniformIntProvider.create(1, 2)),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_120_RANGE,
            EnvironmentScanPlacementModifier.of(
                Direction.UP, BlockPredicate.solid(), BlockPredicate.IS_AIR_OR_WATER, 12
            ),
            RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-1)),
            BiomePlacementModifier.of()
        )


        c.register(
            DuskPlacedFeatures.ICE_CAVE_PILLAR,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.ICE_CAVE_PILLAR),
            *arrayOf<PlacementModifier>(
                CountPlacementModifier.of(UniformIntProvider.create(20, 48)),
                SquarePlacementModifier.of(),
                PlacedFeatures.BOTTOM_TO_120_RANGE,
                BiomePlacementModifier.of()
            )
        )
        c.register(
            DuskPlacedFeatures.ICE_SPIKE_FLOOR,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.ICE_SPIKE_FLOOR),
            CountPlacementModifier.of(125),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_120_RANGE,
            EnvironmentScanPlacementModifier.of(
                Direction.DOWN,
                BlockPredicate.matchingBlocks(Blocks.SNOW_BLOCK),
                BlockPredicate.matchingBlockTag(DuskBlockTags.ICE_SPIKE_IGNORE_BLOCKS),
                12
            ),
            BiomePlacementModifier.of()
        )
        c.register(
            DuskPlacedFeatures.ICE_SPIKE_CEILING,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.ICE_SPIKE_CEILING),
            CountPlacementModifier.of(125),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_120_RANGE,
            EnvironmentScanPlacementModifier.of(
                Direction.UP,
                BlockPredicate.matchingBlocks(Blocks.SNOW_BLOCK),
                BlockPredicate.matchingBlockTag(DuskBlockTags.ICE_SPIKE_IGNORE_BLOCKS),
                12
            ),
            BiomePlacementModifier.of()
        )
        c.register(
            DuskPlacedFeatures.ORE_ICE,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.ORE_ICE),
            CountPlacementModifier.of(3),
            SquarePlacementModifier.of(),
            HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(160)),
            BiomePlacementModifier.of()
        )
        c.register(
            DuskPlacedFeatures.ICE_CAVE_FOSSIL,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.ICE_CAVE_FOSSIL),
            CountPlacementModifier.of(3),
            SquarePlacementModifier.of(),
            HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(160)),
            BiomePlacementModifier.of()
        )
        c.register(
            DuskPlacedFeatures.ORE_BLUE_ICE,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.ORE_BLUE_ICE),
            SquarePlacementModifier.of(),
            HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(160)),
            BiomePlacementModifier.of()
        )
        c.register(
            DuskPlacedFeatures.ORE_SAND,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.ORE_SAND),
            CountPlacementModifier.of(14),
            SquarePlacementModifier.of(),
            HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(160)),
            BiomePlacementModifier.of()
        )
        c.register(
            DuskPlacedFeatures.ORE_RED_SAND,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.ORE_RED_SAND),
            CountPlacementModifier.of(14),
            SquarePlacementModifier.of(),
            HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(160)),
            BiomePlacementModifier.of()
        )
        c.register(
            DuskPlacedFeatures.SAND_CACTUS,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.SAND_CAVE_CACTUS),
            CountPlacementModifier.of(88),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_120_RANGE,
            EnvironmentScanPlacementModifier.of(
                Direction.DOWN, BlockPredicate.matchingBlockTag(BlockTags.SAND), BlockPredicate.IS_AIR, 12
            ),
            BiomePlacementModifier.of()
        )
//        c.register(
//            DuskPlacedFeatures.SAND_CAVE_VINES,
//            configuredFeatureProvider.getOrThrow(UndergroundConfiguredFeatures.MOSS_PATCH_CEILING),
//            CountPlacementModifier.of(125),
//            SquarePlacementModifier.of(),
//            PlacedFeatures.BOTTOM_TO_120_RANGE,
//            EnvironmentScanPlacementModifier.of(
//                Direction.UP, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12
//            ),
//            BlockFilterPlacementModifier.of(BlockPredicate.matchingFluids(Vec3i(0, 4, 0), Fluids.WATER)),
//            RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-1)),
//            BiomePlacementModifier.of()
//        )
        c.register(
            DuskPlacedFeatures.SAND_CAVE_PILLAR,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.SAND_CAVE_PILLAR),
            CountPlacementModifier.of(UniformIntProvider.create(20, 48)),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_120_RANGE,
            BiomePlacementModifier.of()
        )
        c.register(
            DuskPlacedFeatures.SAND_SPIKES,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.SAND_SPIKES),
            CountPlacementModifier.of(100),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_120_RANGE,
            EnvironmentScanPlacementModifier.of(
                Direction.DOWN, BlockPredicate.matchingBlocks(Blocks.SANDSTONE), BlockPredicate.IS_AIR, 12
            ),
            BiomePlacementModifier.of()
        )
        c.register(
            DuskPlacedFeatures.SAND_SPIKES_ROOF,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.SAND_SPIKES_ROOF),
            CountPlacementModifier.of(100),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_120_RANGE,
            EnvironmentScanPlacementModifier.of(
                Direction.UP, BlockPredicate.matchingBlocks(Blocks.SANDSTONE), BlockPredicate.IS_AIR, 12
            ),
            RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-1)),
            BiomePlacementModifier.of()
        )
        c.register(
            DuskPlacedFeatures.RED_SAND_CAVE_PILLAR,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.RED_SAND_CAVE_PILLAR),
            CountPlacementModifier.of(UniformIntProvider.create(20, 48)),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_120_RANGE,
            BiomePlacementModifier.of()
        )
        c.register(
            DuskPlacedFeatures.RED_SAND_SPIKES,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.RED_SAND_SPIKES),
            CountPlacementModifier.of(100),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_120_RANGE,
            EnvironmentScanPlacementModifier.of(
                Direction.DOWN, BlockPredicate.matchingBlocks(Blocks.RED_SANDSTONE), BlockPredicate.IS_AIR, 12
            ),
            BiomePlacementModifier.of()
        )
        c.register(
            DuskPlacedFeatures.RED_SAND_SPIKES_ROOF,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.RED_SAND_SPIKES_ROOF),
            CountPlacementModifier.of(100),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_120_RANGE,
            EnvironmentScanPlacementModifier.of(
                Direction.UP, BlockPredicate.matchingBlocks(Blocks.RED_SANDSTONE), BlockPredicate.IS_AIR, 12
            ),
            RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-1)),
            BiomePlacementModifier.of()
        )
        c.register(
            DuskPlacedFeatures.SAND_CAVE_CORAL,
            configuredFeatureProvider.getOrThrow(OceanConfiguredFeatures.WARM_OCEAN_VEGETATION),
            CountPlacementModifier.of(256),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_120_RANGE,
            EnvironmentScanPlacementModifier.of(
                Direction.DOWN,
                BlockPredicate.hasSturdyFace(Direction.UP),
                BlockPredicate.matchingBlocks(Blocks.WATER),
                12
            ),
            BiomePlacementModifier.of()
        )
        c.register(
            DuskPlacedFeatures.SAND_CAVE_SEAGRASS,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.SAND_CAVE_SEAGRASS),
            CountPlacementModifier.of(128),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_120_RANGE,
            EnvironmentScanPlacementModifier.of(
                Direction.DOWN,
                BlockPredicate.hasSturdyFace(Direction.UP),
                BlockPredicate.matchingBlocks(Blocks.WATER),
                12
            ),
            BiomePlacementModifier.of()
        )
        c.register(
            DuskPlacedFeatures.SAND_CAVE_PICKLE,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.SAND_CAVE_PICKLES),
            CountPlacementModifier.of(256),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_120_RANGE,
            EnvironmentScanPlacementModifier.of(
                Direction.DOWN,
                BlockPredicate.hasSturdyFace(Direction.UP),
                BlockPredicate.matchingBlocks(Blocks.WATER),
                12
            ),
            BiomePlacementModifier.of()
        )
        c.register(
            DuskPlacedFeatures.ORE_COBBLESTONE,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.ORE_COBBLESTONE),
            CountPlacementModifier.of(14),
            SquarePlacementModifier.of(),
            HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(160)),
            BiomePlacementModifier.of()
        )
        c.register(
            DuskPlacedFeatures.COBBLESTONE_CAVE_PILLAR,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.COBBLESTONE_CAVE_PILLAR),
            CountPlacementModifier.of(UniformIntProvider.create(20, 48)),
            SquarePlacementModifier.of(),
            HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(256)),
            BiomePlacementModifier.of()
        )
        c.register(
            DuskPlacedFeatures.COBBLESTONE_SPIKES,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.COBBLESTONE_SPIKES),
            CountPlacementModifier.of(100),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_120_RANGE,
            EnvironmentScanPlacementModifier.of(
                Direction.DOWN, BlockPredicate.matchingBlocks(Blocks.COBBLESTONE), BlockPredicate.IS_AIR, 12
            ),
            BiomePlacementModifier.of()
        )
        c.register(
            DuskPlacedFeatures.COBBLESTONE_SPIKES_ROOF,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.COBBLESTONE_SPIKES_ROOF),
            CountPlacementModifier.of(100),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_120_RANGE,
            EnvironmentScanPlacementModifier.of(
                Direction.UP, BlockPredicate.matchingBlocks(Blocks.COBBLESTONE), BlockPredicate.IS_AIR, 12
            ),
            RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-1)),
            BiomePlacementModifier.of()
        )
        c.register(
            DuskPlacedFeatures.COBBLED_DEEPSLATE_CAVE_PILLAR,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.COBBLED_DEEPSLATE_CAVE_PILLAR),
            CountPlacementModifier.of(UniformIntProvider.create(20, 48)),
            SquarePlacementModifier.of(),
            HeightRangePlacementModifier.uniform(YOffset.BOTTOM, YOffset.fixed(0)),
            BiomePlacementModifier.of()
        )
        c.register(
            DuskPlacedFeatures.COBBLED_DEEPSLATE_SPIKES,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.COBBLED_DEEPSLATE_SPIKES),
            CountPlacementModifier.of(100),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_120_RANGE,
            EnvironmentScanPlacementModifier.of(
                Direction.DOWN, BlockPredicate.matchingBlocks(Blocks.COBBLED_DEEPSLATE), BlockPredicate.IS_AIR, 12
            ),
            BiomePlacementModifier.of()
        )
        c.register(
            DuskPlacedFeatures.COBBLED_DEEPSLATE_SPIKES_ROOF,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.COBBLED_DEEPSLATE_SPIKES_ROOF),
            CountPlacementModifier.of(100),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_120_RANGE,
            EnvironmentScanPlacementModifier.of(
                Direction.UP, BlockPredicate.matchingBlocks(Blocks.COBBLED_DEEPSLATE), BlockPredicate.IS_AIR, 12
            ),
            RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-1)),
            BiomePlacementModifier.of()
        )

//Structure Piece Features
        c.register(
            DuskPlacedFeatures.DESERT_WELL,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.DESERT_WELL),
            RarityFilterPlacementModifier.of(1000),
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-2)),
            BiomePlacementModifier.of()
        )
        c.register(
            DuskPlacedFeatures.RED_DESERT_WELL,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.RED_DESERT_WELL),
            RarityFilterPlacementModifier.of(1000),
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-2)),
            BiomePlacementModifier.of()
        )
        c.register(
            DuskPlacedFeatures.CAVE_DESERT_WELL,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.DESERT_WELL),
            RarityFilterPlacementModifier.of(10),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_120_RANGE,
            EnvironmentScanPlacementModifier.of(
                Direction.DOWN,
                BlockPredicate.hasSturdyFace(Direction.UP),
                BlockPredicate.IS_AIR_OR_WATER,
                12
            ),
            RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-2)),
            BiomePlacementModifier.of()
        )
        c.register(
            DuskPlacedFeatures.CAVE_RED_DESERT_WELL,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.RED_DESERT_WELL),
            RarityFilterPlacementModifier.of(10),
            SquarePlacementModifier.of(),
            PlacedFeatures.BOTTOM_TO_120_RANGE,
            EnvironmentScanPlacementModifier.of(
                Direction.DOWN,
                BlockPredicate.hasSturdyFace(Direction.UP),
                BlockPredicate.IS_AIR_OR_WATER,
                12
            ),
            RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-2)),
            BiomePlacementModifier.of()
        )

//Monster Room features
        val upperMonsterRoom = listOf(
            CountPlacementModifier.of(10),
            SquarePlacementModifier.of(),
            HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.getTop()),
            BiomePlacementModifier.of()
        )
        val lowerMonsterRoom = listOf(
            CountPlacementModifier.of(4),
            SquarePlacementModifier.of(),
            HeightRangePlacementModifier.uniform(YOffset.aboveBottom(6), YOffset.fixed(-1)),
            BiomePlacementModifier.of()
        )

        c.register(
            DuskPlacedFeatures.DEEP_MONSTER_ROOM,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.DEEP_MONSTER_ROOM),
            lowerMonsterRoom
        )
        c.register(
            DuskPlacedFeatures.LUSH_MONSTER_ROOM,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.LUSH_MONSTER_ROOM),
            upperMonsterRoom
        )
        c.register(
            DuskPlacedFeatures.DEEP_LUSH_MONSTER_ROOM,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.DEEP_LUSH_MONSTER_ROOM),
            lowerMonsterRoom
        )
        c.register(
            DuskPlacedFeatures.FROZEN_MONSTER_ROOM,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.FROZEN_MONSTER_ROOM),
            upperMonsterRoom
        )
        c.register(
            DuskPlacedFeatures.DEEP_FROZEN_MONSTER_ROOM,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.DEEP_FROZEN_MONSTER_ROOM),
            lowerMonsterRoom
        )
        c.register(
            DuskPlacedFeatures.SAND_MONSTER_ROOM,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.SAND_MONSTER_ROOM),
            upperMonsterRoom
        )
        c.register(
            DuskPlacedFeatures.DEEP_SAND_MONSTER_ROOM,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.SAND_MONSTER_ROOM),
            lowerMonsterRoom
        )
        c.register(
            DuskPlacedFeatures.RED_SAND_MONSTER_ROOM,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.RED_SAND_MONSTER_ROOM),
            upperMonsterRoom
        )
        c.register(
            DuskPlacedFeatures.DEEP_RED_SAND_MONSTER_ROOM,
            configuredFeatureProvider.getOrThrow(DuskConfiguredFeatures.RED_SAND_MONSTER_ROOM),
            lowerMonsterRoom
        )

//        c.register(
//            UndergroundPlacedFeatures.SPORE_BLOSSOM, holder14,
//            CountPlacementModifier.of(25),
//            SquarePlacementModifier.of(),
//            PlacedFeatures.BOTTOM_TO_120_RANGE,
//            EnvironmentScanPlacementModifier.of(
//                Direction.UP, BlockPredicate.solid(), BlockPredicate.IS_AIR, 12
//            ),
//            RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-1)),
//            BiomePlacementModifier.of()
//
//        )

    }

    private fun treePlacementModifiersBase(modifier: PlacementModifier): ImmutableList.Builder<PlacementModifier> {
        return ImmutableList.builder<PlacementModifier>().add(modifier).add(SquarePlacementModifier.of())
            .add(SurfaceWaterDepthFilterPlacementModifier.of(0)).add(PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP)
            .add(BiomePlacementModifier.of())
    }

//    PlacedFeatureUtil.register(
//    c,

    fun Registerable<PlacedFeature>.register(
        registryKey: RegistryKey<PlacedFeature>, configuredFeature: RegistryEntry<ConfiguredFeature<*, *>>,
        vararg placementModifiers: PlacementModifier,
    ): Any = this.register(registryKey, PlacedFeature(configuredFeature, placementModifiers.toList()))

    fun Registerable<PlacedFeature>.register(
        registryKey: RegistryKey<PlacedFeature>, configuredFeature: RegistryEntry<ConfiguredFeature<*, *>>,
        placementModifiers: List<PlacementModifier>,
    ): Any = this.register(registryKey, PlacedFeature(configuredFeature, placementModifiers))

}
