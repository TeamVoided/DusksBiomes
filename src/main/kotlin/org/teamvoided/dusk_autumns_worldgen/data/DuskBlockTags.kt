package org.teamvoided.dusk_autumns_worldgen.data

import net.minecraft.block.Block
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.id

object DuskBlockTags {
    @JvmField
    val CANE_SUPPORT = create("cane_support")
    @JvmField
    val CANE_HYDRATION = create("cane_hydration")
    val ICE_ORE_REPLACEABLE = create("ice_ore_replaceable")
    val ICE_SPIKE_PLACEABLE_BLOCKS = create("ice_spike_placeable_blocks")
    val ICE_SPIKE_IGNORE_BLOCKS = create("ice_spike_ignore_blocks")
    val CAVE_PILLAR_REPLACEABLE = create("cave_pillar_replaceable")


    fun create(id: String): TagKey<Block> = TagKey.of(RegistryKeys.BLOCK, id(id))
}