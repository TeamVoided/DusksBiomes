package org.teamvoided.dusk_autumns_worldgen.data

import net.minecraft.util.Identifier
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.id

object DuskLootTables {
    val LOOT_TABLES: MutableSet<Identifier> = mutableSetOf()


    val COOL_CHEST: Identifier = reg("chests/cool_chest")
    val COOL_ARCHAEOLOGY: Identifier = reg("archaeology/cool_archaeology")









    private fun reg(id: String) = reg(id(id))
    private fun reg(id: Identifier): Identifier {
        if (LOOT_TABLES.add(id)) return id
        throw IllegalArgumentException("$id is already a registered built-in loot table")
    }

}