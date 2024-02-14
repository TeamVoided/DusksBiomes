package org.teamvoided.dusk_autumns_worldgen.util

import net.minecraft.loot.provider.number.UniformLootNumberProvider

object Utils {
    fun uniformNum(x: Number, y: Number): UniformLootNumberProvider =
        UniformLootNumberProvider.create(x.toFloat(), y.toFloat())

    fun <T> List<T>.add(vararg item: T): List<T> {
        val x = this.toMutableList()
        x.addAll(item)
        return x.toList()
    }
}