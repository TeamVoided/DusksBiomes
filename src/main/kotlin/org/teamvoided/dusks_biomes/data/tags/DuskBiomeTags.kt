package org.teamvoided.dusks_biomes.data.tags

import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.world.biome.Biome
import org.teamvoided.dusks_biomes.DusksBiomesMod.id
object DuskBiomeTags {

    val DUSKS_BIOMES = create("dusks_biomes")
    val HAS_FROZEN_VARIANTS = create("has_snow_variants")
    val HAS_HOT_VARIANTS = create("has_hot_variants")
    val HAS_VILLAGE_SWAMP_STRUCTURE = create("has_structure/has_village_swamp")
    val HAS_VILLAGE_MANGROVE_SWAMP_STRUCTURE = create("has_structure/has_village_mangrove_swamp")
//    val HAS_DESERT_RUIN = create("has_structure/has_desert_ruin")
//    val HAS_RED_DESERT_RUIN = create("has_structure/has_red_desert_ruin")
    val HAS_OCEAN_RUIN_RED_WARM = create("has_structure/has_ocean_ruin_red_warm")

    fun create(id: String): TagKey<Biome> = TagKey.of(RegistryKeys.BIOME, id(id))
}
