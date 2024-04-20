package org.teamvoided.dusk_autumns_worldgen.datagen.worldgen

import net.minecraft.registry.BootstrapContext
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.math.noise.InterpolatedNoiseSampler
import net.minecraft.world.gen.DensityFunction
import org.teamvoided.dusk_autumns_worldgen.init.worldgen.DuskDensityFunction.EXAMPLE

object DensityFunctionCreator {

    fun bootstrap(c: BootstrapContext<DensityFunction>) {
        val noiseParameters = c.getRegistryLookup(RegistryKeys.NOISE_PARAMETERS)
        val densityFunctions = c.getRegistryLookup(RegistryKeys.DENSITY_FUNCTION)

        c.register(
            EXAMPLE,
            InterpolatedNoiseSampler.createUnseeded(0.25, 0.125, 80.0, 160.0, 8.0)
        )

        /*

        NoiseRouterData.class

         */

    }
}