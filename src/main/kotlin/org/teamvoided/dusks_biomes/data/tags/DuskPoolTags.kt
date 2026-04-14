package org.teamvoided.dusks_biomes.data.tags

import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool
import org.teamvoided.dusks_biomes.DusksBiomes.id
import org.teamvoided.dusks_biomes.util.tag

object DuskPoolTags {

    val TRIAL_CHAIN_FIX = key("trial_chain_fix")

    fun key(id: String): TagKey<StructureTemplatePool> = Registries.TEMPLATE_POOL.tag(id(id))

}
