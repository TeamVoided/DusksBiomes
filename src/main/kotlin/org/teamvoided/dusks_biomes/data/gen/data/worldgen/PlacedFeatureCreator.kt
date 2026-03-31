package org.teamvoided.dusks_biomes.data.gen.data.worldgen

import com.google.common.collect.ImmutableList
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.features.AquaticFeatures
import net.minecraft.data.worldgen.features.MiscOverworldFeatures
import net.minecraft.data.worldgen.features.TreeFeatures
import net.minecraft.data.worldgen.features.VegetationFeatures
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.data.worldgen.placement.VegetationPlacements
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.BlockTags
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.VerticalAnchor
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.placement.*
import org.teamvoided.dusks_biomes.data.gen.data.worldgen.placed_feature.TreePlacedFeature.trees
import org.teamvoided.dusks_biomes.data.tags.DuskBlockTags
import org.teamvoided.dusks_biomes.data.world.gen.DuskConfiguredFeatures
import org.teamvoided.dusks_biomes.data.world.gen.DuskPlacedFeatures

@Suppress("MagicNumber")
object PlacedFeatureCreator {
    fun bootstrap(c: BootstrapContext<PlacedFeature>) {
        val cfLookup = c.lookup(Registries.CONFIGURED_FEATURE)
        c.trees()

        c.register(
            DuskPlacedFeatures.SWAMP_VILLAGE_ROCK,
            cfLookup.getOrThrow(MiscOverworldFeatures.FOREST_ROCK)
        )
        c.register(
            DuskPlacedFeatures.SWAMP_VILLAGE_OAK,
            cfLookup.getOrThrow(TreeFeatures.SWAMP_OAK),
            PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)
        )
        c.register(
            DuskPlacedFeatures.SWAMP_VILLAGE_MANGROVE,
            cfLookup.getOrThrow(VegetationFeatures.MANGROVE_VEGETATION),
            PlacementUtils.filteredByBlockSurvival(Blocks.MANGROVE_PROPAGULE)
        )
        c.register(
            DuskPlacedFeatures.SWAMP_VILLAGE_FLOWERS,
            cfLookup.getOrThrow(VegetationFeatures.FLOWER_SWAMP)
        )
        c.register(
            DuskPlacedFeatures.COBBLESTONE_ROCK,
            cfLookup.getOrThrow(DuskConfiguredFeatures.COBBLESTONE_ROCK),
            CountPlacement.of(2),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            BiomeFilter.biome()

        )
        c.register(
            DuskPlacedFeatures.TREES_WINDSWEPT_BIRCH,
            cfLookup.getOrThrow(VegetationFeatures.BIRCH_TALL),
            VegetationPlacements.treePlacement(
                PlacementUtils.countExtra(2, 0.1f, 1)
            )
        )
        c.register(
            DuskPlacedFeatures.TREES_FROZEN_BADLANDS,
            cfLookup.getOrThrow(TreeFeatures.SPRUCE),
            VegetationPlacements.treePlacement(
                PlacementUtils.countExtra(5, 0.1f, 1),
                Blocks.SPRUCE_SAPLING
            )
        )
        c.register(
            DuskPlacedFeatures.MANGROVE_FROZEN_CHECKED,
            cfLookup.getOrThrow(DuskConfiguredFeatures.MANGROVE_FROZEN_CHECKED),
            BlockPredicateFilter.forPredicate(
                BlockPredicate.wouldSurvive(Blocks.MANGROVE_PROPAGULE.defaultBlockState(), BlockPos.ZERO)
            )
        )
        c.register(
            DuskPlacedFeatures.TALL_MANGROVE_FROZEN_CHECKED,
            cfLookup.getOrThrow(DuskConfiguredFeatures.TALL_MANGROVE_FROZEN_CHECKED),
            BlockPredicateFilter.forPredicate(
                BlockPredicate.wouldSurvive(Blocks.MANGROVE_PROPAGULE.defaultBlockState(), BlockPos.ZERO)
            )
        )
        c.register(
            DuskPlacedFeatures.TREES_MANGROVE_FROZEN,
            cfLookup.getOrThrow(DuskConfiguredFeatures.MANGROVE_FROZEN_VEGETATION),
            CountPlacement.of(15),
            InSquarePlacement.spread(),
            SurfaceWaterDepthFilter.forMaxDepth(5),
            PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
            BiomeFilter.biome(),
            BlockPredicateFilter.forPredicate(
                BlockPredicate.wouldSurvive(Blocks.MANGROVE_PROPAGULE.defaultBlockState(), BlockPos.ZERO)
            )
        )
        c.register(
            DuskPlacedFeatures.TREES_COLD_FOREST,
            cfLookup.getOrThrow(DuskConfiguredFeatures.TREES_OAK_BIRCH_SPRUCE),
            VegetationPlacements.treePlacement(
                PlacementUtils.countExtra(10, 0.1f, 1)
            )
        )
        c.register(
            DuskPlacedFeatures.TREES_COLD_PLAINS,
            cfLookup.getOrThrow(VegetationFeatures.TREES_WINDSWEPT_HILLS),
            VegetationPlacements.treePlacement(
                PlacementUtils.countExtra(0, 0.05f, 1)
            )
        )
        c.register(
            DuskPlacedFeatures.TREES_WARM_FOREST,
            cfLookup.getOrThrow(DuskConfiguredFeatures.TREES_OAK_BIRCH_ACACIA),
            VegetationPlacements.treePlacement(
                PlacementUtils.countExtra(10, 0.1f, 1)
            )
        )
        c.register(
            DuskPlacedFeatures.TREES_WARM_PLAINS,
            cfLookup.getOrThrow(VegetationFeatures.TREES_SAVANNA),
            VegetationPlacements.treePlacement(
                PlacementUtils.countExtra(0, 0.05f, 1)
            )
        )
        c.register(
            DuskPlacedFeatures.FLOWER_SNOWY_CHERRY,
            cfLookup.getOrThrow(DuskConfiguredFeatures.FLOWER_SNOWY_CHERRY),
            NoiseThresholdCountPlacement.of(-0.8, 5, 10),
            RarityFilter.onAverageOnceEvery(5),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            BiomeFilter.biome()
        )
        c.register(
            DuskPlacedFeatures.MUSHROOM_GROVE_VEGETATION,
            cfLookup.getOrThrow(VegetationFeatures.MUSHROOM_ISLAND_VEGETATION),
            CountPlacement.of(12),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            BiomeFilter.biome()
        )

        c.register(
            DuskPlacedFeatures.CAVE_DEAD_BUSH,
            cfLookup.getOrThrow(VegetationFeatures.PATCH_DEAD_BUSH),
            CountPlacement.of(UniformInt.of(124, 177)),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            BiomeFilter.biome()
        )
        c.register(
            DuskPlacedFeatures.CAVE_GLOW_LICHEN_EXTRA,
            cfLookup.getOrThrow(DuskConfiguredFeatures.CAVE_GLOW_LICHEN_EXTRA),
            CountPlacement.of(UniformInt.of(3, 33)),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            BiomeFilter.biome()
        )
        c.register(
            DuskPlacedFeatures.ORE_COARSE_DIRT,
            cfLookup.getOrThrow(DuskConfiguredFeatures.ORE_COARSE_DIRT),
            RarityFilter.onAverageOnceEvery(3),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(160)),
            BiomeFilter.biome()
        )
        c.register(
            DuskPlacedFeatures.MUSHROOM_CAVE_VEGETATION,
            cfLookup.getOrThrow(VegetationFeatures.MUSHROOM_ISLAND_VEGETATION),
            CountPlacement.of(125),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(
                Direction.DOWN,
                BlockPredicate.solid(),
                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                12
            ),
            RandomOffsetPlacement.vertical(ConstantInt.of(1)),
            BiomeFilter.biome()
        )

        c.register(
            DuskPlacedFeatures.MUSHROOM_CAVE_MUSHROOMS,
            cfLookup.getOrThrow(DuskConfiguredFeatures.MUSHROOM_CAVE_MUSHROOMS),
            CountPlacement.of(33),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(
                Direction.DOWN,
                BlockPredicate.solid(),
                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                12
            ),
            RandomOffsetPlacement.vertical(ConstantInt.of(1)),
            BiomeFilter.biome()
        )

        c.register(
            DuskPlacedFeatures.MUSHROOM_CAVE_SURFACE,
            cfLookup.getOrThrow(DuskConfiguredFeatures.MUSHROOM_CAVE_ROOTS),
            CountPlacement.of(UniformInt.of(1, 2)),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(
                Direction.UP, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE, 12
            ),
            RandomOffsetPlacement.vertical(ConstantInt.of(-1)),
            BiomeFilter.biome()
        )


        c.register(
            DuskPlacedFeatures.ICE_CAVE_PILLAR,
            cfLookup.getOrThrow(DuskConfiguredFeatures.ICE_CAVE_PILLAR),
            CountPlacement.of(UniformInt.of(20, 48)),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            BiomeFilter.biome()
        )
        c.register(
            DuskPlacedFeatures.ICE_SPIKE_FLOOR,
            cfLookup.getOrThrow(DuskConfiguredFeatures.ICE_SPIKE_FLOOR),
            CountPlacement.of(125),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(
                Direction.DOWN,
                BlockPredicate.matchesBlocks(Blocks.SNOW_BLOCK),
                BlockPredicate.matchesTag(DuskBlockTags.ICE_SPIKE_IGNORE_BLOCKS),
                12
            ),
            BiomeFilter.biome()
        )
        c.register(
            DuskPlacedFeatures.ICE_SPIKE_CEILING,
            cfLookup.getOrThrow(DuskConfiguredFeatures.ICE_SPIKE_CEILING),
            CountPlacement.of(125),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(
                Direction.UP,
                BlockPredicate.matchesBlocks(Blocks.SNOW_BLOCK),
                BlockPredicate.matchesTag(DuskBlockTags.ICE_SPIKE_IGNORE_BLOCKS),
                12
            ),
            BiomeFilter.biome()
        )
        c.register(
            DuskPlacedFeatures.ORE_ICE,
            cfLookup.getOrThrow(DuskConfiguredFeatures.ORE_ICE),
            CountPlacement.of(3),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(160)),
            BiomeFilter.biome()
        )
        c.register(
            DuskPlacedFeatures.ICE_CAVE_FOSSIL,
            cfLookup.getOrThrow(DuskConfiguredFeatures.ICE_CAVE_FOSSIL),
            CountPlacement.of(3),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(160)),
            BiomeFilter.biome()
        )
        c.register(
            DuskPlacedFeatures.ORE_BLUE_ICE,
            cfLookup.getOrThrow(DuskConfiguredFeatures.ORE_BLUE_ICE),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(160)),
            BiomeFilter.biome()
        )
        c.register(
            DuskPlacedFeatures.ORE_SAND,
            cfLookup.getOrThrow(DuskConfiguredFeatures.ORE_SAND),
            CountPlacement.of(14),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(160)),
            BiomeFilter.biome()
        )
        c.register(
            DuskPlacedFeatures.ORE_RED_SAND,
            cfLookup.getOrThrow(DuskConfiguredFeatures.ORE_RED_SAND),
            CountPlacement.of(14),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(160)),
            BiomeFilter.biome()
        )
        c.register(
            DuskPlacedFeatures.SAND_CACTUS,
            cfLookup.getOrThrow(DuskConfiguredFeatures.SAND_CAVE_CACTUS),
            CountPlacement.of(88),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(
                Direction.DOWN, BlockPredicate.matchesTag(BlockTags.SAND), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12
            ),
            BiomeFilter.biome()
        )
        c.register(
            DuskPlacedFeatures.SAND_DRY_GRASS,
            cfLookup.getOrThrow(VegetationFeatures.PATCH_DRY_GRASS),
            CountPlacement.of(UniformInt.of(124, 177)),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            BiomeFilter.biome()
        )
//        c.register(
//            DuskPlacedFeatures.SAND_CAVE_VINES,
//            cfLookup.getOrThrow(UndergroundConfiguredFeatures.MOSS_PATCH_CEILING),
//            CountPlacement.of(125),
//            InSquarePlacement.spread(),
//            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
//            EnvironmentScanPlacement.scanningFor(
//                Direction.UP, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12
//            ),
//            BlockPredicateFilter.forPredicate(BlockPredicate.matchingFluids(Vec3i(0, 4, 0), Fluids.WATER)),
//            RandomOffsetPlacement.vertical(ConstantInt.of(-1)),
//            BiomeFilter.biome()
//        )
        c.register(
            DuskPlacedFeatures.SAND_CAVE_PILLAR,
            cfLookup.getOrThrow(DuskConfiguredFeatures.SAND_CAVE_PILLAR),
            CountPlacement.of(UniformInt.of(20, 48)),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            BiomeFilter.biome()
        )
        c.register(
            DuskPlacedFeatures.SAND_SPIKES,
            cfLookup.getOrThrow(DuskConfiguredFeatures.SAND_SPIKES),
            CountPlacement.of(100),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(
                Direction.DOWN, BlockPredicate.matchesBlocks(Blocks.SANDSTONE), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12
            ),
            BiomeFilter.biome()
        )
        c.register(
            DuskPlacedFeatures.SAND_SPIKES_ROOF,
            cfLookup.getOrThrow(DuskConfiguredFeatures.SAND_SPIKES_ROOF),
            CountPlacement.of(100),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(
                Direction.UP, BlockPredicate.matchesBlocks(Blocks.SANDSTONE), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12
            ),
            RandomOffsetPlacement.vertical(ConstantInt.of(-1)),
            BiomeFilter.biome()
        )
        c.register(
            DuskPlacedFeatures.RED_SAND_CAVE_PILLAR,
            cfLookup.getOrThrow(DuskConfiguredFeatures.RED_SAND_CAVE_PILLAR),
            CountPlacement.of(UniformInt.of(20, 48)),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            BiomeFilter.biome()
        )
        c.register(
            DuskPlacedFeatures.RED_SAND_SPIKES,
            cfLookup.getOrThrow(DuskConfiguredFeatures.RED_SAND_SPIKES),
            CountPlacement.of(100),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(
                Direction.DOWN,
                BlockPredicate.matchesBlocks(Blocks.RED_SANDSTONE),
                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                12
            ),
            BiomeFilter.biome()
        )
        c.register(
            DuskPlacedFeatures.RED_SAND_SPIKES_ROOF,
            cfLookup.getOrThrow(DuskConfiguredFeatures.RED_SAND_SPIKES_ROOF),
            CountPlacement.of(100),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(
                Direction.UP,
                BlockPredicate.matchesBlocks(Blocks.RED_SANDSTONE),
                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                12
            ),
            RandomOffsetPlacement.vertical(ConstantInt.of(-1)),
            BiomeFilter.biome()
        )
        c.register(
            DuskPlacedFeatures.SAND_CAVE_CORAL,
            cfLookup.getOrThrow(AquaticFeatures.WARM_OCEAN_VEGETATION),
            CountPlacement.of(256),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(
                Direction.DOWN,
                BlockPredicate.hasSturdyFace(Direction.UP),
                BlockPredicate.matchesBlocks(Blocks.WATER),
                12
            ),
            BiomeFilter.biome()
        )
        c.register(
            DuskPlacedFeatures.SAND_CAVE_SEAGRASS,
            cfLookup.getOrThrow(DuskConfiguredFeatures.SAND_CAVE_SEAGRASS),
            CountPlacement.of(128),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(
                Direction.DOWN,
                BlockPredicate.hasSturdyFace(Direction.UP),
                BlockPredicate.matchesBlocks(Blocks.WATER),
                12
            ),
            BiomeFilter.biome()
        )
        c.register(
            DuskPlacedFeatures.SAND_CAVE_PICKLE,
            cfLookup.getOrThrow(DuskConfiguredFeatures.SAND_CAVE_PICKLES),
            CountPlacement.of(256),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(
                Direction.DOWN,
                BlockPredicate.hasSturdyFace(Direction.UP),
                BlockPredicate.matchesBlocks(Blocks.WATER),
                12
            ),
            BiomeFilter.biome()
        )
        c.register(
            DuskPlacedFeatures.ORE_COBBLESTONE,
            cfLookup.getOrThrow(DuskConfiguredFeatures.ORE_COBBLESTONE),
            CountPlacement.of(14),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(160)),
            BiomeFilter.biome()
        )
        c.register(
            DuskPlacedFeatures.COBBLESTONE_CAVE_PILLAR,
            cfLookup.getOrThrow(DuskConfiguredFeatures.COBBLESTONE_CAVE_PILLAR),
            CountPlacement.of(UniformInt.of(20, 48)),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(256)),
            BiomeFilter.biome()
        )
        c.register(
            DuskPlacedFeatures.COBBLESTONE_SPIKES,
            cfLookup.getOrThrow(DuskConfiguredFeatures.COBBLESTONE_SPIKES),
            CountPlacement.of(100),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(
                Direction.DOWN,
                BlockPredicate.matchesBlocks(Blocks.COBBLESTONE),
                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                12
            ),
            BiomeFilter.biome()
        )
        c.register(
            DuskPlacedFeatures.COBBLESTONE_SPIKES_ROOF,
            cfLookup.getOrThrow(DuskConfiguredFeatures.COBBLESTONE_SPIKES_ROOF),
            CountPlacement.of(100),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(
                Direction.UP, BlockPredicate.matchesBlocks(Blocks.COBBLESTONE), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12
            ),
            RandomOffsetPlacement.vertical(ConstantInt.of(-1)),
            BiomeFilter.biome()
        )
        c.register(
            DuskPlacedFeatures.COBBLED_DEEPSLATE_CAVE_PILLAR,
            cfLookup.getOrThrow(DuskConfiguredFeatures.COBBLED_DEEPSLATE_CAVE_PILLAR),
            CountPlacement.of(UniformInt.of(20, 48)),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(0)),
            BiomeFilter.biome()
        )
        c.register(
            DuskPlacedFeatures.COBBLED_DEEPSLATE_SPIKES,
            cfLookup.getOrThrow(DuskConfiguredFeatures.COBBLED_DEEPSLATE_SPIKES),
            CountPlacement.of(100),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(
                Direction.DOWN,
                BlockPredicate.matchesBlocks(Blocks.COBBLED_DEEPSLATE),
                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                12
            ),
            BiomeFilter.biome()
        )
        c.register(
            DuskPlacedFeatures.COBBLED_DEEPSLATE_SPIKES_ROOF,
            cfLookup.getOrThrow(DuskConfiguredFeatures.COBBLED_DEEPSLATE_SPIKES_ROOF),
            CountPlacement.of(100),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(
                Direction.UP,
                BlockPredicate.matchesBlocks(Blocks.COBBLED_DEEPSLATE),
                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                12
            ),
            RandomOffsetPlacement.vertical(ConstantInt.of(-1)),
            BiomeFilter.biome()
        )

//Structure Piece Features
        c.register(
            DuskPlacedFeatures.DESERT_WELL,
            cfLookup.getOrThrow(DuskConfiguredFeatures.DESERT_WELL),
            RarityFilter.onAverageOnceEvery(1000),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            RandomOffsetPlacement.vertical(ConstantInt.of(-2)),
            BiomeFilter.biome()
        )
        c.register(
            DuskPlacedFeatures.RED_DESERT_WELL,
            cfLookup.getOrThrow(DuskConfiguredFeatures.RED_DESERT_WELL),
            RarityFilter.onAverageOnceEvery(1000),
            InSquarePlacement.spread(),
            PlacementUtils.HEIGHTMAP,
            RandomOffsetPlacement.vertical(ConstantInt.of(-2)),
            BiomeFilter.biome()
        )
        c.register(
            DuskPlacedFeatures.CAVE_DESERT_WELL,
            cfLookup.getOrThrow(DuskConfiguredFeatures.DESERT_WELL),
            RarityFilter.onAverageOnceEvery(10),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(
                Direction.DOWN,
                BlockPredicate.hasSturdyFace(Direction.UP),
                BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE,
                12
            ),
            RandomOffsetPlacement.vertical(ConstantInt.of(-2)),
            BiomeFilter.biome()
        )
        c.register(
            DuskPlacedFeatures.CAVE_RED_DESERT_WELL,
            cfLookup.getOrThrow(DuskConfiguredFeatures.RED_DESERT_WELL),
            RarityFilter.onAverageOnceEvery(10),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(
                Direction.DOWN,
                BlockPredicate.hasSturdyFace(Direction.UP),
                BlockPredicate.ONLY_IN_AIR_OR_WATER_PREDICATE,
                12
            ),
            RandomOffsetPlacement.vertical(ConstantInt.of(-2)),
            BiomeFilter.biome()
        )

//Monster Room features
        val upperMonsterRoom = listOf(
            CountPlacement.of(10),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.top()),
            BiomeFilter.biome()
        )
        val lowerMonsterRoom = listOf(
            CountPlacement.of(4),
            InSquarePlacement.spread(),
            HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(6), VerticalAnchor.absolute(-1)),
            BiomeFilter.biome()
        )

        c.register(
            DuskPlacedFeatures.DEEP_MONSTER_ROOM,
            cfLookup.getOrThrow(DuskConfiguredFeatures.DEEP_MONSTER_ROOM),
            lowerMonsterRoom
        )
        c.register(
            DuskPlacedFeatures.LUSH_MONSTER_ROOM,
            cfLookup.getOrThrow(DuskConfiguredFeatures.LUSH_MONSTER_ROOM),
            upperMonsterRoom
        )
        c.register(
            DuskPlacedFeatures.DEEP_LUSH_MONSTER_ROOM,
            cfLookup.getOrThrow(DuskConfiguredFeatures.DEEP_LUSH_MONSTER_ROOM),
            lowerMonsterRoom
        )
        c.register(
            DuskPlacedFeatures.FROZEN_MONSTER_ROOM,
            cfLookup.getOrThrow(DuskConfiguredFeatures.FROZEN_MONSTER_ROOM),
            upperMonsterRoom
        )
        c.register(
            DuskPlacedFeatures.DEEP_FROZEN_MONSTER_ROOM,
            cfLookup.getOrThrow(DuskConfiguredFeatures.DEEP_FROZEN_MONSTER_ROOM),
            lowerMonsterRoom
        )
        c.register(
            DuskPlacedFeatures.SAND_MONSTER_ROOM,
            cfLookup.getOrThrow(DuskConfiguredFeatures.SAND_MONSTER_ROOM),
            upperMonsterRoom
        )
        c.register(
            DuskPlacedFeatures.DEEP_SAND_MONSTER_ROOM,
            cfLookup.getOrThrow(DuskConfiguredFeatures.SAND_MONSTER_ROOM),
            lowerMonsterRoom
        )
        c.register(
            DuskPlacedFeatures.RED_SAND_MONSTER_ROOM,
            cfLookup.getOrThrow(DuskConfiguredFeatures.RED_SAND_MONSTER_ROOM),
            upperMonsterRoom
        )
        c.register(
            DuskPlacedFeatures.DEEP_RED_SAND_MONSTER_ROOM,
            cfLookup.getOrThrow(DuskConfiguredFeatures.RED_SAND_MONSTER_ROOM),
            lowerMonsterRoom
        )

//        c.register(
//            UndergroundPlacedFeatures.SPORE_BLOSSOM, holder14,
//            CountPlacement.of(25),
//            InSquarePlacement.spread(),
//            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
//            EnvironmentScanPlacement.scanningFor(
//                Direction.UP, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12
//            ),
//            RandomOffsetPlacement.vertical(ConstantInt.of(-1)),
//            BiomeFilter.biome()
//
//        )

    }

    private fun treePlacementModifiersBase(modifier: PlacementModifier): ImmutableList.Builder<PlacementModifier> {
        return ImmutableList.builder<PlacementModifier>().add(modifier).add(InSquarePlacement.spread())
            .add(SurfaceWaterDepthFilter.forMaxDepth(0)).add(PlacementUtils.HEIGHTMAP_OCEAN_FLOOR)
            .add(BiomeFilter.biome())
    }

//    PlacedFeatureUtil.register(
//    c,

    fun BootstrapContext<PlacedFeature>.register(
        registryKey: ResourceKey<PlacedFeature>, configuredFeature: Holder<ConfiguredFeature<*, *>>,
        vararg placementModifiers: PlacementModifier,
    ): Any = this.register(registryKey, PlacedFeature(configuredFeature, placementModifiers.toList()))

    fun BootstrapContext<PlacedFeature>.register(
        registryKey: ResourceKey<PlacedFeature>, configuredFeature: Holder<ConfiguredFeature<*, *>>,
        placementModifiers: List<PlacementModifier>,
    ): Any = this.register(registryKey, PlacedFeature(configuredFeature, placementModifiers))

    fun BootstrapContext<PlacedFeature>.register(
        registryKey: ResourceKey<PlacedFeature>, configuredFeature: ResourceKey<ConfiguredFeature<*, *>>,
        vararg placementModifiers: PlacementModifier,
    ): Any {
        val cf = this.lookup(Registries.CONFIGURED_FEATURE)
        return this.register(registryKey, PlacedFeature(cf.getOrThrow(configuredFeature), placementModifiers.toList()))
    }

    fun BootstrapContext<PlacedFeature>.register(
        registryKey: ResourceKey<PlacedFeature>, configuredFeature: ResourceKey<ConfiguredFeature<*, *>>,
        placementModifiers: List<PlacementModifier>,
    ): Any {
        val cf = this.lookup(Registries.CONFIGURED_FEATURE)
        return this.register(registryKey, PlacedFeature(cf.getOrThrow(configuredFeature), placementModifiers))
    }

}
