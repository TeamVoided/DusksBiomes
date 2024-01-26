package org.teamvoided.dusk_autumns_worldgen.init.worldgen

import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.world.gen.feature.DefaultFeatureConfig
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.FeatureConfig
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.id
import org.teamvoided.dusk_autumns_worldgen.worldgen.configured_feature.InvertedIceSpikeFeature


object DuskConfiguredFeatures {
    val COBBLESTONE_ROCK = create("cobblestone_rock")
    val TREES_OAK_BIRCH_SPRUCE = create("trees_oak_birch_spruce")
    val TREES_OAK_BIRCH_JUNGLE = create("trees_oak_birch_jungle")
    val MANGROVE_FROZEN_CHECKED = create("mangrove_frozen_checked")
    val TALL_MANGROVE_FROZEN_CHECKED = create("tall_mangrove_frozen_checked")
    val MANGROVE_FROZEN_VEGETATION = create("mangrove_frozen_vegetation")
    val CHERRY_SNOW = create("cherry_snow")
    val CHERRY_SNOW_BEES = create("cherry_snow_bees")
    val TREES_SNOWY_CHERRY = create("trees_snowy_chery")
    val FLOWER_SNOWY_CHERRY = create("flower_snowy_cherry")
    val INVERTED_ICE_SPIKE_CAVE = create("inverted_ice_spike")

//    Register Configure Feature Types

    val INVERTED_ICE_SPIKE = register("inverted_ice_spike", InvertedIceSpikeFeature(DefaultFeatureConfig.CODEC));


    fun init() {}
    fun create(id: String) = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, id(id))
    private fun <C : FeatureConfig?, F : Feature<C>?> register(name: String, feature: F): F {
        return Registry.register(Registries.FEATURE, id(name), feature)
    }
}