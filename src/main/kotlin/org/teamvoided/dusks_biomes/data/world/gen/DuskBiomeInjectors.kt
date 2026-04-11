package org.teamvoided.dusks_biomes.data.world.gen

import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries
import dev.worldgen.lithostitched.api.worldgen.biomeinjector.BiomeInjector
import net.minecraft.resources.ResourceKey
import org.teamvoided.dusks_biomes.DusksBiomes.id

object DuskBiomeInjectors {


    val COLD_FOREST = create("cold_forest")
    val COLD_PLAINS = create("cold_plains")
    val WARM_FOREST = create("warm_forest")
    val WARM_PLAINS = create("warm_plains")

    fun create(id: String): ResourceKey<BiomeInjector> =
        ResourceKey.create(LithostitchedRegistries.BIOME_INJECTOR, id(id))
}
