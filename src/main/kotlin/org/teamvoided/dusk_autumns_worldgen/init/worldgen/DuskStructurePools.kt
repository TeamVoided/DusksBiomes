package org.teamvoided.dusk_autumns_worldgen.init.worldgen

import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen

object DuskStructurePools {
    val DESERT_OBELISK_TOP = create("desert_obelisk_top")

    fun init() {}
    fun create(id: String) = RegistryKey.of(RegistryKeys.STRUCTURE_POOL, DuskAutumnsWorldgen.id(id))
}