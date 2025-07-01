package org.teamvoided.dusks_biomes.data.gen.world.gen.placed_feature_creator

import net.minecraft.block.Blocks
import net.minecraft.registry.Registerable
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.math.Direction
import net.minecraft.world.gen.blockpredicate.BlockPredicate
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.PlacedFeature
import net.minecraft.world.gen.feature.PlacedFeatures
import net.minecraft.world.gen.feature.TreeConfiguredFeatures
import net.minecraft.world.gen.feature.VegetationPlacedFeatures
import net.minecraft.world.gen.placementmodifier.*
import org.teamvoided.dusks_biomes.data.gen.world.gen.PlacedFeatureCreator.register
import org.teamvoided.dusks_biomes.data.gen.world.gen.placed_feature_creator.TreePlacedFeature.trees
import org.teamvoided.dusks_biomes.data.world.gen.DuskConfiguredFeatures
import org.teamvoided.dusks_biomes.data.world.gen.DuskPlacedFeatures

object TreePlacedFeature {
    fun Registerable<PlacedFeature>.trees() {
        val configuredFeatureProvider = this.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE)

        this.register(
            DuskPlacedFeatures.TREES_SNOWY_DARK_GROVE,
            DuskConfiguredFeatures.TREES_OAK_DARK_SPRUCE,
            VegetationPlacedFeatures.treeModifiers(CountPlacementModifier.of(16))
        )
        this.registerOnSnow(
            DuskPlacedFeatures.TREES_SNOWY_DARK_GROVE_ON_SNOW,
            DuskConfiguredFeatures.TREES_OAK_DARK_SPRUCE_ON_SNOW,
            CountPlacementModifier.of(16)
        )
        this.register(
            DuskPlacedFeatures.TREES_SNOWY_PALE_GROVE,
            DuskConfiguredFeatures.TREES_PALE_SPRUCE,
            VegetationPlacedFeatures.treeModifiers(CountPlacementModifier.of(16))
        )
        this.registerOnSnow(
            DuskPlacedFeatures.TREES_SNOWY_PALE_GROVE_ON_SNOW,
            DuskConfiguredFeatures.TREES_PALE_SPRUCE_ON_SNOW,
            CountPlacementModifier.of(16)
        )
        this.registerOnSnow(
            DuskPlacedFeatures.TREES_SNOWY_CHERRY_GROVE,
            TreeConfiguredFeatures.CHERRY_BEES_005,
            PlacedFeatures.createCountExtraModifier(10, 0.1f, 1)
        )
    }

    fun Registerable<PlacedFeature>.registerOnSnow(
        placed: RegistryKey<PlacedFeature>,
        configured: RegistryKey<ConfiguredFeature<*, *>>,
        count: PlacementModifier
    ) {
        val configuredFeatureProvider = this.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE)
        this.register(
            placed,
            configuredFeatureProvider.getOrThrow(configured),
            count,
            SquarePlacementModifier.of(),
            SurfaceWaterDepthFilterPlacementModifier.of(0),
            PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
            BiomePlacementModifier.of(),
            EnvironmentScanPlacementModifier.of(
                Direction.UP, BlockPredicate.not(
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
    }
}