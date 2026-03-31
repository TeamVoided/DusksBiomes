package org.teamvoided.dusks_biomes.data.gen.data.worldgen.placed_feature


import net.minecraft.core.Direction
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.features.TreeFeatures
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.data.worldgen.placement.VegetationPlacements
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.placement.*
import org.teamvoided.dusks_biomes.data.gen.data.worldgen.PlacedFeatureCreator.register
import org.teamvoided.dusks_biomes.data.world.gen.DuskConfiguredFeatures
import org.teamvoided.dusks_biomes.data.world.gen.DuskPlacedFeatures

object TreePlacedFeature {
    fun BootstrapContext<PlacedFeature>.trees() {
        this.register(
            DuskPlacedFeatures.TREES_SNOWY_DARK_GROVE,
            DuskConfiguredFeatures.TREES_OAK_DARK_SPRUCE,
            VegetationPlacements.treePlacement(CountPlacement.of(16))
        )
        this.registerOnSnow(
            DuskPlacedFeatures.TREES_SNOWY_DARK_GROVE_ON_SNOW,
            DuskConfiguredFeatures.TREES_OAK_DARK_SPRUCE_ON_SNOW,
            CountPlacement.of(16)
        )
        this.register(
            DuskPlacedFeatures.TREES_SNOWY_PALE_GROVE,
            DuskConfiguredFeatures.TREES_PALE_SPRUCE,
            VegetationPlacements.treePlacement(CountPlacement.of(16))
        )
        this.registerOnSnow(
            DuskPlacedFeatures.TREES_SNOWY_PALE_GROVE_ON_SNOW,
            DuskConfiguredFeatures.TREES_PALE_SPRUCE_ON_SNOW,
            CountPlacement.of(16)
        )
        this.registerOnSnow(
            DuskPlacedFeatures.TREES_SNOWY_CHERRY_GROVE,
            TreeFeatures.CHERRY_BEES_005,
            PlacementUtils.countExtra(10, 0.1f, 1)
        )
    }

    fun BootstrapContext<PlacedFeature>.registerOnSnow(
        placed: ResourceKey<PlacedFeature>,
        configured: ResourceKey<ConfiguredFeature<*, *>>,
        count: PlacementModifier,
    ) {
        val configuredFeatureProvider = this.lookup(Registries.CONFIGURED_FEATURE)
        this.register(
            placed,
            configuredFeatureProvider.getOrThrow(configured),
            count,
            InSquarePlacement.spread(),
            SurfaceWaterDepthFilter.forMaxDepth(0),
            PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
            BiomeFilter.biome(),
            EnvironmentScanPlacement.scanningFor(
                Direction.UP,
                BlockPredicate.not(BlockPredicate.matchesBlocks(Blocks.POWDER_SNOW)),
                8
            ),
            BlockPredicateFilter.forPredicate(
                BlockPredicate.matchesBlocks(
                    Direction.DOWN.unitVec3i,
                    Blocks.SNOW_BLOCK, Blocks.POWDER_SNOW
                )
            )
        )
    }
}