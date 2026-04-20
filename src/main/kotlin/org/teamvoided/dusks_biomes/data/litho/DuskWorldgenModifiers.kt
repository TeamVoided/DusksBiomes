package org.teamvoided.dusks_biomes.data.litho

import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries
import org.teamvoided.dusks_biomes.DusksBiomes
import org.teamvoided.dusks_biomes.util.key

object DuskWorldgenModifiers {

    val DUSKS_PRE_RULES = key("dusks_pre_rules")
    val DUSKS_POST_RULES = key("dusks_post_rules")
    // Add Structures
    val ADD_RUIN_WARM_RED = key("add_ruin_warm_red")
    val ADD_VILLAGES = key("add_villages")
    // Modify Colors
    val ADJUST_DESERT_COLORS = key("adjust_desert_colors")
    val ADJUST_DEEP_DARK_COLORS = key("adjust_deep_dark_colors")
    val ADJUST_LUSH_CAVE_COLORS = key("adjust_lush_cave_colors")
    val ADJUST_DRIPSTONE_CAVE_COLORS = key("adjust_dripstone_cave_colors")
    // Misc
    val TRIAL_CHAMBERS_FIX = key("trial_chambers_fix")

    fun key(id: String) = LithostitchedRegistries.WORLDGEN_MODIFIER.key(DusksBiomes.id(id))

}