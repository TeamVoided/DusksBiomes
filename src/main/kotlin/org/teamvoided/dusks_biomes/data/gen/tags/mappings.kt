package org.teamvoided.dusks_biomes.data.gen.tags

import net.minecraft.registry.tag.TagKey
import net.minecraft.unmapped.C_pmamztyz

fun <R, T> C_pmamztyz<R, T>.add(it: R) = this.method_71560(it)
fun <R, T> C_pmamztyz<R, T>.add(vararg  it: R) = this.method_71558(*it)
fun <R, T> C_pmamztyz<R, T>.add(it: Collection<R>) = this.method_71555(it)


fun <R, T> C_pmamztyz<R, T>.addOptionalTag(it: TagKey<T>) = this.method_71559(it)
