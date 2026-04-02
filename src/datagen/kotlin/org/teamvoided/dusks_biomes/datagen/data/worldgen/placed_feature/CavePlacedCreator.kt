package org.teamvoided.dusks_biomes.datagen.data.worldgen.placed_feature

import net.minecraft.core.Direction
import net.minecraft.core.HolderGetter
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.features.VegetationFeatures
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.placement.*
import org.teamvoided.dusks_biomes.data.world.gen.DuskConfiguredFeatures
import org.teamvoided.dusks_biomes.data.world.gen.DuskPlacedFeatures
import org.teamvoided.dusks_biomes.datagen.data.worldgen.PlacedFeatureCreator.register

object CavePlacedCreator {
    fun BootstrapContext<PlacedFeature>.caves() {
        val cf = this.lookup(Registries.CONFIGURED_FEATURE)
        this.pale(cf)
    }

    private fun BootstrapContext<PlacedFeature>.pale(cf: HolderGetter<ConfiguredFeature<*, *>>) {
        this.register(
            DuskPlacedFeatures.PALE_CAVES_VINES,
            DuskConfiguredFeatures.PALE_CAVE_VINES,
            CountPlacement.of(188),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(
                Direction.UP,
                BlockPredicate.hasSturdyFace(Direction.DOWN),
                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                12
            ),
            RandomOffsetPlacement.vertical(ConstantInt.of(-1)),
            BiomeFilter.biome()
        )

        this.register(
            DuskPlacedFeatures.PALE_CAVES_VEGETATION,
            DuskConfiguredFeatures.PALE_CAVE_MOSS_VEGETATION,
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
        this.register(
            DuskPlacedFeatures.PALE_CAVES_CLAY,
            DuskConfiguredFeatures.PALE_CAVE_CLAY,
            CountPlacement.of(62),
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
        this.register(
            DuskPlacedFeatures.PALE_CAVES_CEILING_VEGETATION,
            DuskConfiguredFeatures.PALE_CAVE_MOSS_PATCH_CEILING,
            CountPlacement.of(125),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(
                Direction.UP,
                BlockPredicate.solid(),
                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                12
            ),
            RandomOffsetPlacement.vertical(ConstantInt.of(-1)),
            BiomeFilter.biome()

        )
        this.register(
            DuskPlacedFeatures.PALE_HEART_CEILING,
            DuskConfiguredFeatures.PALE_CAVE_HEART_CEILING,
            CountPlacement.of(25),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(
                Direction.UP,
                BlockPredicate.allOf(
                    BlockPredicate.solid(),
                    BlockPredicate.solid(Direction.UP.unitVec3i),
                    BlockPredicate.hasSturdyFace(Direction.SOUTH.unitVec3i, Direction.NORTH),
                    BlockPredicate.hasSturdyFace(Direction.NORTH.unitVec3i, Direction.SOUTH),
                    BlockPredicate.hasSturdyFace(Direction.EAST.unitVec3i, Direction.WEST),
                    BlockPredicate.hasSturdyFace(Direction.WEST.unitVec3i, Direction.EAST)
                ),
                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                12
            ),
            RandomOffsetPlacement.vertical(ConstantInt.of(1)),
            BiomeFilter.biome()
        )
        this.register(
            DuskPlacedFeatures.FLOWER_PALE_CAVE,
            VegetationFeatures.PALE_FOREST_FLOWERS,
            CountPlacement.of(25),
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
    }
}