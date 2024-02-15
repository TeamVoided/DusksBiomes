package org.teamvoided.dusk_autumns_worldgen.data

import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.id

object DuskLootTables {
    private val LOOT_TABLES: MutableSet<Identifier> = mutableSetOf()

    val VILLAGE_SWAMP_HOUSE_CHEST = register("chests/village_swamp_house")
    val VILLAGE_MANGROVE_SWAMP_HOUSE_CHEST = register("chests/village_mangrove_swamp_house")
    val DESERT_RUINS_CHEST = register("chests/desert_ruins")
    val DESERT_RUINS_COMMON_ARCHAEOLOGY = register("archaeology/desert_ruins_common")
    val DESERT_RUINS_RARE_ARCHAEOLOGY = register("archaeology/desert_ruins_rare")
    val RED_DESERT_RUINS_CHEST = register("chests/red_desert_ruins")
    val RED_DESERT_RUINS_COMMON_ARCHAEOLOGY = register("archaeology/red_desert_ruins_common")
    val RED_DESERT_RUINS_RARE_ARCHAEOLOGY = register("archaeology/red_desert_ruins_rare")

    val COOL_CHEST: Identifier = register("chests/cool_chest")
    val COOL_ARCHAEOLOGY: Identifier = register("archaeology/cool_archaeology")

    private fun register(id: String) = reg(id(id))
    private fun reg(id: Identifier): Identifier {
        if (LOOT_TABLES.add(id)) return id
        throw IllegalArgumentException("$id is already a registered built-in loot table")
    }

}