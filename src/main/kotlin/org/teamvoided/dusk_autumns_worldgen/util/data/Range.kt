package org.teamvoided.dusk_autumns_worldgen.util.data

import net.minecraft.world.biome.source.util.MultiNoiseUtil

data class Range(val min: Number, val max: Number) {
    constructor(value: Number) : this(value, value)

    fun min() = min.toFloat()
    fun max() = max.toFloat()
    fun toParameterRange(): MultiNoiseUtil.ParameterRange = MultiNoiseUtil.ParameterRange.of(min(), max())

}