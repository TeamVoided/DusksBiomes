package org.teamvoided.dusk_autumns_worldgen.data.tags

import net.minecraft.block.Block
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.id

object DuskBlockTags {
    val ICE_ORE_REPLACEABLE = create("ice_ore_replaceable")
    val MUSHROOM_ROOT_PLACEABLE = create("mushroom_root_placeable")
    val ICE_SPIKE_PLACEABLE_BLOCKS = create("ice_spike_placeable_blocks")
    val ICE_SPIKE_IGNORE_BLOCKS = create("ice_spike_ignore_blocks")
    val CAVE_PILLAR_REPLACEABLE = create("cave_pillar_replaceable")
    val CAVE_PILLAR_PLACEABLE = create("cave_pillar_placeable")


    fun create(id: String): TagKey<Block> = TagKey.of(RegistryKeys.BLOCK, id(id))
}