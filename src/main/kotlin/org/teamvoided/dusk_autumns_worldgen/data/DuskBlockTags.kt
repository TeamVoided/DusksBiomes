package org.teamvoided.dusk_autumns_worldgen.data

import net.minecraft.block.Block
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.id

object DuskBlockTags {
    @JvmField
    val CANE_SUPPORT: TagKey<Block> = create("cane_support")
    @JvmField
    val CANE_HYDRATION: TagKey<Block> = create("cane_hydration")


    fun create(id: String) : TagKey<Block> = TagKey.of(RegistryKeys.BLOCK, id(id))
}