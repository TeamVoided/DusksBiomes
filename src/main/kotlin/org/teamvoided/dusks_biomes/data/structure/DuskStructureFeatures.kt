package org.teamvoided.dusks_biomes.data.structure

import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.world.gen.feature.StructureFeature
import org.teamvoided.dusks_biomes.DusksBiomesMod.id

object DuskStructureFeatures {

    val SWAMP_VILLAGE = create("village_swamp")
    val MANGROVE_SWAMP_VILLAGE = create("village_mangrove_swamp")
//    val DESERT_RUINS = create("desert_ruins/desert_ruins")
//    val RED_DESERT_RUINS = create("desert_ruins/red_desert_ruins")
//    val LARGE_DESERT_RUINS = create("desert_ruins/large_desert_ruins")
//    val LARGE_RED_DESERT_RUINS = create("desert_ruins/large_red_desert_ruins")

    private fun create(id: String): RegistryKey<StructureFeature> =
        RegistryKey.of(RegistryKeys.STRUCTURE_FEATURE, id(id))
}
