package org.teamvoided.dusk_autumns_worldgen.datagen.worldgen

import net.minecraft.registry.RegistryKeys
import net.minecraft.util.math.noise.InterpolatedNoiseSampler
import net.minecraft.world.gen.BootstrapContext
import net.minecraft.world.gen.DensityFunction
import org.teamvoided.dusk_autumns_worldgen.init.worldgen.DuskDensityFunction.EXAMPLE

object DensityFunctionCreator {

    fun bootstrap(c: BootstrapContext<DensityFunction>) {
        val noiseParameters = c.lookup(RegistryKeys.NOISE_PARAMETERS)
        val densityFunctions = c.lookup(RegistryKeys.DENSITY_FUNCTION)

        c.register(
            EXAMPLE,
            InterpolatedNoiseSampler.createUnseeded(0.25, 0.125, 80.0, 160.0, 8.0)
        )

        /*

        NoiseRouterData.class

         */

    }
}