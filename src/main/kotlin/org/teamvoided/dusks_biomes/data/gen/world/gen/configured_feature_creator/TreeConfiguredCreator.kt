package org.teamvoided.dusks_biomes.data.gen.world.gen.configured_feature_creator

import net.minecraft.registry.Registerable
import net.minecraft.registry.RegistryEntryLookup
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.world.gen.feature.*
import org.teamvoided.dusks_biomes.data.gen.world.gen.ConfiguredFeatureCreator.registerConfiguredFeature
import org.teamvoided.dusks_biomes.data.world.gen.DuskConfiguredFeatures

object TreeConfiguredCreator {
    fun Registerable<ConfiguredFeature<*, *>>.trees() {
        val cf = this.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE)
        val pf = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        this.registerDenseGroveTrees(
            DuskConfiguredFeatures.TREES_OAK_DARK_SPRUCE,
            pf.getOrThrow(TreePlacedFeatures.DARK_OAK_CHECKED),
            true
        )
        this.registerDenseGroveTrees(
            DuskConfiguredFeatures.TREES_OAK_DARK_SPRUCE_ON_SNOW,
            cf.inline(TreeConfiguredFeatures.DARK_OAK)
        )
        this.registerDenseGroveTrees(
            DuskConfiguredFeatures.TREES_PALE_SPRUCE,
            null,
            true
        )
        this.registerDenseGroveTrees(
            DuskConfiguredFeatures.TREES_PALE_SPRUCE_ON_SNOW,
            null,
            true
        )
    }

    private fun Registerable<ConfiguredFeature<*, *>>.registerDenseGroveTrees(
        feature: RegistryKey<ConfiguredFeature<*, *>>,
        bigTree: RegistryEntry<PlacedFeature>?,
        check: Boolean = false
    ) {
        val cf = this.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE)
        val pf = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)

        val list: List<RandomFeatureEntry> = if (bigTree != null)
            if (check)
                listOf(
                    RandomFeatureEntry(cf.inline(TreeConfiguredFeatures.HUGE_RED_MUSHROOM), 0.025f),
                    RandomFeatureEntry(cf.inline(TreeConfiguredFeatures.HUGE_BROWN_MUSHROOM), 0.0125f),
                    RandomFeatureEntry(bigTree, 2 / 3f),
                    RandomFeatureEntry(pf.getOrThrow(TreePlacedFeatures.SPRUCE_CHECKED), 0.3f),
                    RandomFeatureEntry(pf.getOrThrow(TreePlacedFeatures.FANCY_OAK_CHECKED), 0.125f)
                )
            else
                listOf(
                    RandomFeatureEntry(bigTree, 2 / 3f),
                    RandomFeatureEntry(cf.inline(TreeConfiguredFeatures.SPRUCE), 0.3f),
                    RandomFeatureEntry(cf.inline(TreeConfiguredFeatures.FANCY_OAK), 0.125f)
                )
        else
            if (check)
                listOf(
                    RandomFeatureEntry(pf.getOrThrow(TreePlacedFeatures.PALE_OAK_CREAKING_CHECKED), 0.1f),
                    RandomFeatureEntry(pf.getOrThrow(TreePlacedFeatures.PALE_OAK_CHECKED), 0.9f)
                )
            else
                listOf(
                    RandomFeatureEntry(cf.inline(TreeConfiguredFeatures.PALE_OAK_CREAKING), 0.1f),
                    RandomFeatureEntry(cf.inline(TreeConfiguredFeatures.PALE_OAK), 0.9f)
                )
        val fall = if (bigTree != null)
            if (check) pf.getOrThrow(TreePlacedFeatures.OAK_CHECKED)
            else PlacedFeatures.createEntry(cf.getOrThrow(TreeConfiguredFeatures.OAK))
        else
            if (check) pf.getOrThrow(TreePlacedFeatures.PALE_OAK_CHECKED)
            else PlacedFeatures.createEntry(cf.getOrThrow(TreeConfiguredFeatures.PALE_OAK))


        this.registerConfiguredFeature(
            feature,
            Feature.RANDOM_SELECTOR,
            RandomFeatureConfig(list, fall)
        )
    }

    private fun RegistryEntryLookup<ConfiguredFeature<*, *>>.inline(feature: RegistryKey<ConfiguredFeature<*, *>>): RegistryEntry<PlacedFeature> =
        PlacedFeatures.createEntry(this.getOrThrow(feature))
}