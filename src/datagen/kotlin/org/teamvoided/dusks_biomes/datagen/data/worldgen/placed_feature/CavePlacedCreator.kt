package org.teamvoided.dusks_biomes.datagen.data.worldgen.placed_feature

import net.minecraft.core.Direction
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.resources.ResourceKey
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.placement.*
import org.teamvoided.dusks_biomes.data.world.gen.DuskConfiguredFeatures
import org.teamvoided.dusks_biomes.data.world.gen.DuskPlacedFeatures
import org.teamvoided.dusks_biomes.datagen.data.worldgen.PlacedFeatureCreator.register

object CavePlacedCreator {
    fun BootstrapContext<PlacedFeature>.caves() {
        this.pale()
    }

    private fun BootstrapContext<PlacedFeature>.pale() {
        this.basicCavePlacement(
            DuskPlacedFeatures.PALE_HEART_CEILING,
            DuskConfiguredFeatures.PALE_CAVE_HEART_CEILING,
            25,
            Direction.UP,
            false
        )
        this.basicCavePlacement(
            DuskPlacedFeatures.PALE_CAVES_VINES,
            DuskConfiguredFeatures.PALE_CAVE_VINES,
            188,
            Direction.UP
        )
        this.basicCavePlacement(
            DuskPlacedFeatures.PALE_CAVES_VEGETATION,
            DuskConfiguredFeatures.PALE_CAVE_VEGETATION,
            125,
            Direction.DOWN
        )
        this.basicCavePlacement(
            DuskPlacedFeatures.PALE_CAVES_CEILING_VEGETATION,
            DuskConfiguredFeatures.PALE_CAVE_MOSS_PATCH_CEILING,
            125,
            Direction.UP
        )
        this.basicCavePlacement(
            DuskPlacedFeatures.FLOWER_PALE_CAVE,
            DuskConfiguredFeatures.PALE_CAVE_FLOWERS,
            100,
            Direction.DOWN
        )
        this.basicCavePlacement(
            DuskPlacedFeatures.PALE_CAVE_LEAVES,
            DuskConfiguredFeatures.PALE_CAVE_LEAVES,
            100,
            Direction.DOWN
        )
        this.basicCavePlacement(
            DuskPlacedFeatures.PALE_CAVE_LEAVES_CEILING,
            DuskConfiguredFeatures.PALE_CAVE_LEAVES_CEILING,
            100,
            Direction.UP
        )
    }

    fun BootstrapContext<PlacedFeature>.basicCavePlacement(
        placed: ResourceKey<PlacedFeature>,
        configured: ResourceKey<ConfiguredFeature<*, *>>,
        count: Int,
        direction: Direction = Direction.DOWN,
        offset: Boolean = true
    ) {
        val placement = mutableListOf(
            CountPlacement.of(count),
            InSquarePlacement.spread(),
            PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
            EnvironmentScanPlacement.scanningFor(
                direction,
                BlockPredicate.solid(),
                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                12
            )
        )
        if (offset) placement += (RandomOffsetPlacement.vertical(ConstantInt.of(direction.opposite.stepY)))
        placement += BiomeFilter.biome()
        this.register(
            placed,
            configured,
            placement
        )
    }
}