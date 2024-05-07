package org.teamvoided.dusks_biomes.data.world.gen

import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.world.gen.DensityFunction
import org.teamvoided.dusks_biomes.DusksBiomesMod.id

object DuskDensityFunction {
    val EXAMPLE = create("example")

    fun create(id: String): RegistryKey<DensityFunction> = RegistryKey.of(RegistryKeys.DENSITY_FUNCTION, id(id))
}
