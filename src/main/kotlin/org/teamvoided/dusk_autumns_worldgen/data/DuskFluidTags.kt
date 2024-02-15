package org.teamvoided.dusk_autumns_worldgen.data

import net.minecraft.fluid.Fluid
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.id
object DuskFluidTags {
    @JvmField
    val CANE_HYDRATION = create("cane_hydration")


    fun create(id: String) : TagKey<Fluid> = TagKey.of(RegistryKeys.FLUID, id(id))
}