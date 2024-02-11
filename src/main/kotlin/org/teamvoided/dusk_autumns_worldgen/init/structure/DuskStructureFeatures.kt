package org.teamvoided.dusk_autumns_worldgen.init.structure

import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.world.gen.feature.StructureFeature
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.id

object DuskStructureFeatures {

    val DESERT_RUINS = createKey("desert_ruins")
    val RED_DESERT_RUINS = createKey("red_desert_ruins")
    fun init()  {}

    private fun createKey(id: String): RegistryKey<StructureFeature> {
        return RegistryKey.of(RegistryKeys.STRUCTURE_FEATURE, id(id))
    }
}