package org.teamvoided.dusk_autumns_worldgen.data

import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.world.biome.Biome
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.id

object DuskBiomeTags {
    @JvmField
    val HAS_ERODED_PILLAR: TagKey<Biome> = create("has_eroded_pillar")

    fun create(id: String) : TagKey<Biome> = TagKey.of(RegistryKeys.BIOME, id(id));
}