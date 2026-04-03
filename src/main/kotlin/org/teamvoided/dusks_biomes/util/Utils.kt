package org.teamvoided.dusks_biomes.util

import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator

object Utils {
    fun setCount(x: Number, y: Number) = SetItemCountFunction.setCount(uniformNum(x, y))
    fun uniformNum(x: Number, y: Number): UniformGenerator = UniformGenerator.between(x.toFloat(), y.toFloat())
}