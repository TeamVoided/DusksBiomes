package org.teamvoided.dusks_biomes.data

import net.minecraft.loot.LootTable
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import org.teamvoided.dusks_biomes.DusksBiomesMod.id

object DuskLootTables {
    private val READ_ONLY_LOOT_TABLES: MutableSet<RegistryKey<LootTable>> = mutableSetOf()

    val VILLAGE_SWAMP_HOUSE_CHEST = register("chests/village_swamp_house")
    val VILLAGE_MANGROVE_SWAMP_HOUSE_CHEST = register("chests/village_mangrove_swamp_house")
    val DESERT_RUINS_CHEST = register("chests/desert_ruins")
    val DESERT_RUINS_COMMON_ARCHAEOLOGY = register("archaeology/desert_ruins_common")
    val DESERT_RUINS_RARE_ARCHAEOLOGY = register("archaeology/desert_ruins_rare")
    val RED_DESERT_RUINS_CHEST = register("chests/red_desert_ruins")
    val RED_DESERT_RUINS_COMMON_ARCHAEOLOGY = register("archaeology/red_desert_ruins_common")
    val RED_DESERT_RUINS_RARE_ARCHAEOLOGY = register("archaeology/red_desert_ruins_rare")

    val COOL_CHEST = register("chests/cool_chest")
    val COOL_ARCHAEOLOGY = register("archaeology/cool_archaeology")

    private fun register(id: String): RegistryKey<LootTable> = register(RegistryKey.of(RegistryKeys.LOOT_TABLE, id(id)))

    private fun register(registryKey: RegistryKey<LootTable>): RegistryKey<LootTable> {
        if (READ_ONLY_LOOT_TABLES.add(registryKey)) return registryKey
        throw IllegalArgumentException(registryKey.value.toString() + " is already a registered built-in loot table")
    }
    fun getAll(): Set<RegistryKey<LootTable>> = READ_ONLY_LOOT_TABLES.toSet()
}
