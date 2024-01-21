package org.teamvoided.dusk_autumns_worldgen.init.worldgen

import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.util.ConfiguredFeatureUtil
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.id

object DuskConfiguredFeatures {
    val COBBLESTONE_ROCK = create("cobblestone_rock")
    val TREES_OAK_BIRCH_SPRUCE = create("trees_oak_birch_spruce")
    val TREES_OAK_BIRCH_JUNGLE = create("trees_oak_birch_jungle")
    val MANGROVE_FROZEN_CHECKED = create("mangrove_frozen_checked")
    val TALL_MANGROVE_FROZEN_CHECKED = create("tall_mangrove_frozen_checked")
    val MANGROVE_FROZEN_VEGETATION = create("mangrove_frozen_vegetation")
    val CHERRY_SNOW = create("cherry_snow")
    val CHERRY_SNOW_BEES = create("cherry_snow_bees")
    val FLOWER_SNOWY_CHERRY = create("flower_snowy_cherry")

    fun init() {}
    fun create(id: String) = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, id(id))
}