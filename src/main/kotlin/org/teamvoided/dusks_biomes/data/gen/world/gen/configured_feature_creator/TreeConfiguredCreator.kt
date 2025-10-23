package org.teamvoided.dusks_biomes.data.gen.world.gen.configured_feature_creator

import net.minecraft.core.Holder
import net.minecraft.core.HolderGetter
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.features.TreeFeatures
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.data.worldgen.placement.TreePlacements
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import org.teamvoided.dusks_biomes.data.gen.world.gen.ConfiguredFeatureCreator.registerConfiguredFeature
import org.teamvoided.dusks_biomes.data.world.gen.DuskConfiguredFeatures

object TreeConfiguredCreator {
    fun BootstrapContext<ConfiguredFeature<*, *>>.trees() {
        val cf = this.lookup(Registries.CONFIGURED_FEATURE)
        val pf = this.lookup(Registries.PLACED_FEATURE)
        this.registerDenseGroveTrees(
            DuskConfiguredFeatures.TREES_OAK_DARK_SPRUCE,
            pf.getOrThrow(TreePlacements.DARK_OAK_LEAF_LITTER),
            true
        )
        this.registerDenseGroveTrees(
            DuskConfiguredFeatures.TREES_OAK_DARK_SPRUCE_ON_SNOW,
            cf.inline(TreeFeatures.DARK_OAK_LEAF_LITTER)
        )
        this.registerDenseGroveTrees(DuskConfiguredFeatures.TREES_PALE_SPRUCE, null, true)
        this.registerDenseGroveTrees(DuskConfiguredFeatures.TREES_PALE_SPRUCE_ON_SNOW, null)
    }

    private fun BootstrapContext<ConfiguredFeature<*, *>>.registerDenseGroveTrees(
        feature: ResourceKey<ConfiguredFeature<*, *>>,
        bigTree: Holder<PlacedFeature>?,
        check: Boolean = false,
    ) {
        val cf = this.lookup(Registries.CONFIGURED_FEATURE)
        val pf = this.lookup(Registries.PLACED_FEATURE)

        val list: List<WeightedPlacedFeature> = if (bigTree != null) {
            if (check)
                listOf(
                    WeightedPlacedFeature(cf.inline(TreeFeatures.HUGE_RED_MUSHROOM), 0.025f),
                    WeightedPlacedFeature(cf.inline(TreeFeatures.HUGE_BROWN_MUSHROOM), 0.0125f),
                    WeightedPlacedFeature(bigTree, 2 / 3f),
                    WeightedPlacedFeature(pf.getOrThrow(TreePlacements.FALLEN_SPRUCE_TREE), 0.015f),
                    WeightedPlacedFeature(pf.getOrThrow(TreePlacements.SPRUCE_CHECKED), 0.3f),
                    WeightedPlacedFeature(pf.getOrThrow(TreePlacements.FALLEN_OAK_TREE), 0.02f),
                    WeightedPlacedFeature(pf.getOrThrow(TreePlacements.FANCY_OAK_LEAF_LITTER), 0.125f)
                )
            else
                listOf(
                    WeightedPlacedFeature(bigTree, 2 / 3f),
                    WeightedPlacedFeature(cf.inline(TreeFeatures.FALLEN_SPRUCE_TREE), 0.015f),
                    WeightedPlacedFeature(cf.inline(TreeFeatures.SPRUCE), 0.3f),
                    WeightedPlacedFeature(cf.inline(TreeFeatures.FALLEN_OAK_TREE), 0.02f),
                    WeightedPlacedFeature(cf.inline(TreeFeatures.FANCY_OAK), 0.125f)
                )
        } else {
            if (check)
                listOf(
                    WeightedPlacedFeature(pf.getOrThrow(TreePlacements.PALE_OAK_CREAKING_CHECKED), 0.1f),
                    WeightedPlacedFeature(pf.getOrThrow(TreePlacements.PALE_OAK_CHECKED), 0.9f),
                    WeightedPlacedFeature(pf.getOrThrow(TreePlacements.FALLEN_BIRCH_TREE), 0.015f),
                    WeightedPlacedFeature(pf.getOrThrow(TreePlacements.BIRCH_CHECKED), 0.3f),
                )
            else
                listOf(
                    WeightedPlacedFeature(cf.inline(TreeFeatures.PALE_OAK_CREAKING), 0.1f),
                    WeightedPlacedFeature(cf.inline(TreeFeatures.PALE_OAK), 0.9f),
                    WeightedPlacedFeature(cf.inline(TreeFeatures.FALLEN_BIRCH_TREE), 0.015f),
                    WeightedPlacedFeature(cf.inline(TreeFeatures.BIRCH), 0.2f),
                )
        }
        val fall = if (bigTree != null)
            if (check) pf.getOrThrow(TreePlacements.OAK_CHECKED)
            else PlacementUtils.inlinePlaced(cf.getOrThrow(TreeFeatures.OAK))
        else
            if (check) pf.getOrThrow(TreePlacements.PALE_OAK_CHECKED)
            else PlacementUtils.inlinePlaced(cf.getOrThrow(TreeFeatures.PALE_OAK))


        this.registerConfiguredFeature(
            feature,
            Feature.RANDOM_SELECTOR,
            RandomFeatureConfiguration(list, fall)
        )
    }

    private fun HolderGetter<ConfiguredFeature<*, *>>.inline(feature: ResourceKey<ConfiguredFeature<*, *>>): Holder<PlacedFeature> =
        PlacementUtils.inlinePlaced(this.getOrThrow(feature))
}