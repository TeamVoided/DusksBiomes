package org.teamvoided.dusk_autumns_worldgen.data.world.gen

import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.world.gen.DensityFunction
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.id

object DuskDensityFunction {
    val EXAMPLE = create("example")

    fun create(id: String): RegistryKey<DensityFunction> = RegistryKey.of(RegistryKeys.DENSITY_FUNCTION, id(id))
}