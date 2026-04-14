package org.teamvoided.dusks_biomes.data.world.gen.litho

import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries
import org.teamvoided.dusks_biomes.DusksBiomes
import org.teamvoided.dusks_biomes.util.key

object DuskWorldgenModifiers {

    val TRIAL_CHAMBERS_FIX = key("trial_chambers_fix")

    fun key(id: String) = LithostitchedRegistries.WORLDGEN_MODIFIER.key(DusksBiomes.id(id))

}