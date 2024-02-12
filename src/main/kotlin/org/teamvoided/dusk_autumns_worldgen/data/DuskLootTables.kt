package org.teamvoided.dusk_autumns_worldgen.data

import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.id

object DuskLootTables {
    val LOOT_TABLES: MutableSet<Identifier> = mutableSetOf()

    val VILLAGE_SWAMP_HOUSE_CHEST = reg("chests/village_swamp_house")
    val VILLAGE_MANGROVE_SWAMP_HOUSE_CHEST = reg("chests/village_mangrove_swamp_house")
    val DESERT_RUINS_CHEST = reg("chests/desert_ruins")
    val DESERT_RUINS_COMMON_ARCHAEOLOGY = reg("archaeology/desert_ruins_common")
    val DESERT_RUINS_RARE_ARCHAEOLOGY = reg("archaeology/desert_ruins_rare")
    val RED_DESERT_RUINS_CHEST = reg("chests/red_desert_ruins")
    val RED_DESERT_RUINS_COMMON_ARCHAEOLOGY = reg("archaeology/red_desert_ruins_common")
    val RED_DESERT_RUINS_RARE_ARCHAEOLOGY = reg("archaeology/red_desert_ruins_rare")

    val COOL_CHEST: Identifier = reg("chests/cool_chest")
    val COOL_ARCHAEOLOGY: Identifier = reg("archaeology/cool_archaeology")

    private fun reg(id: String) = reg(id(id))
    private fun reg(id: Identifier): Identifier {
        if (LOOT_TABLES.add(id)) return id
        throw IllegalArgumentException("$id is already a registered built-in loot table")
    }

}