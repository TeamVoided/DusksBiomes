package org.teamvoided.dusk_autumns_worldgen.data

import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.world.biome.Biome
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.id

object DuskBiomeTags {

    val DUSK_BIOMES: TagKey<Biome> = create("dusk_biomes")
    val HAS_FROZEN_VARIANTS: TagKey<Biome> = create("has_snow_variants")
    val HAS_HOT_VARIANTS: TagKey<Biome> = create("has_hot_variants")
    val HAS_VILLAGE_SWAMP_STRUCTURE: TagKey<Biome> = create("has_structure/has_village_swamp")
    val HAS_VILLAGE_MANGROVE_SWAMP_STRUCTURE: TagKey<Biome> = create("has_structure/has_village_mangrove_swamp")
    val HAS_DESERT_RUIN: TagKey<Biome> = create("has_structure/has_desert_ruin")
    val HAS_RED_DESERT_RUIN: TagKey<Biome> = create("has_structure/has_red_desert_ruin")
    val HAS_OCEAN_RUIN_RED_WARM: TagKey<Biome> = create("has_structure/has_ocean_ruin_red_warm")


    @JvmField
    val HAS_ERODED_PILLAR: TagKey<Biome> = create("has_eroded_pillar")

    fun create(id: String) : TagKey<Biome> = TagKey.of(RegistryKeys.BIOME, id(id));
}