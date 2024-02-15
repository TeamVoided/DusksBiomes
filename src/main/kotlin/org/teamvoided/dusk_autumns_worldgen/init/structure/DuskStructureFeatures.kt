package org.teamvoided.dusk_autumns_worldgen.init.structure

import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.world.gen.feature.StructureFeature
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.id

object DuskStructureFeatures {

    val SWAMP_VILLAGE = create("village/swamp_village")
    val MANGROVE_SWAMP_VILLAGE = create("village/mangrove_swamp_village")
    val DESERT_RUINS = create("desert_ruins")
    val RED_DESERT_RUINS = create("red_desert_ruins")
    fun init() {}

    private fun create(id: String): RegistryKey<StructureFeature> =
        RegistryKey.of(RegistryKeys.STRUCTURE_FEATURE, id(id))
}