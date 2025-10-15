package org.teamvoided.dusks_biomes.util

import net.minecraft.world.level.biome.Climate

data class Range(val min: Number, val max: Number) {
    constructor(value: Number) : this(value, value)

    fun min() = min.toFloat()
    fun max() = max.toFloat()
    fun toParameterRange(): Climate.Parameter = Climate.Parameter.span(min(), max())

}
