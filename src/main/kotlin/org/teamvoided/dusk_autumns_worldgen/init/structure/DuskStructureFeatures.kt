package org.teamvoided.dusk_autumns_worldgen.init.structure

import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.world.gen.feature.StructureFeature
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.id

object DuskStructureFeatures {

    val SWAMP_VILLAGE = create("village_swamp")
    val MANGROVE_SWAMP_VILLAGE = create("village_mangrove_swamp")
    val DESERT_RUINS = create("desert_ruins/desert_ruins")
    val RED_DESERT_RUINS = create("desert_ruins/red_desert_ruins")
    val LARGE_DESERT_RUINS = create("desert_ruins/large_desert_ruins")
    val LARGE_RED_DESERT_RUINS = create("desert_ruins/large_red_desert_ruins")
    val SAND_CAVE_FOSSILS = create("sand_cave_fossils")
    fun init() {}

    private fun create(id: String): RegistryKey<StructureFeature> =
        RegistryKey.of(RegistryKeys.STRUCTURE_FEATURE, id(id))
}