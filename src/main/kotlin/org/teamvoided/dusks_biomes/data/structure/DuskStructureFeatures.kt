package org.teamvoided.dusks_biomes.data.structure

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.structure.Structure
import org.teamvoided.dusks_biomes.DusksBiomes.id

object DuskStructureFeatures {

    val SWAMP_VILLAGE = create("village_swamp")
    val MANGROVE_SWAMP_VILLAGE = create("village_mangrove_swamp")
    val OCEAN_RUIN_WARM_RED = create("ocean_ruin_warm_red")
//    val DESERT_RUINS = create("desert_ruins/desert_ruins")
//    val RED_DESERT_RUINS = create("desert_ruins/red_desert_ruins")
//    val LARGE_DESERT_RUINS = create("desert_ruins/large_desert_ruins")
//    val LARGE_RED_DESERT_RUINS = create("desert_ruins/large_red_desert_ruins")

    private fun create(id: String): ResourceKey<Structure> = ResourceKey.create(Registries.STRUCTURE, id(id))
}
