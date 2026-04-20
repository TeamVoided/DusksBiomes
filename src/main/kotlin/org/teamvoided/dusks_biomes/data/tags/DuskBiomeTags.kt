package org.teamvoided.dusks_biomes.data.tags

import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.level.biome.Biome
import org.teamvoided.dusks_biomes.DusksBiomes.id

object DuskBiomeTags {

    val DUSKS_BIOMES = create("dusks_biomes")

    val IS_FROZEN_BADLANDS = create("is_frozen_badlands")
    val IS_SNOWY_HILL = create("is_snowy_hill")

    val HAS_FROZEN_VARIANTS = create("has_snow_variants")
    val HAS_HOT_VARIANTS = create("has_hot_variants")
    val HAS_VILLAGE_SWAMP_STRUCTURE = create("has_structure/village_swamp")
    val HAS_VILLAGE_MANGROVE_SWAMP_STRUCTURE = create("has_structure/village_mangrove_swamp")
//    val HAS_DESERT_RUIN = create("has_structure/has_desert_ruin")
//    val HAS_RED_DESERT_RUIN = create("has_structure/has_red_desert_ruin")

    val HAS_SAND_CAVES = create("has_biome/sand_caves")
    val HAS_RED_SAND_CAVES = create("has_biome/red_sand_caves")
    val HAS_FROZEN_CAVERNS = create("has_biome/frozen_caverns")

    val HAS_OCEAN_RUIN_RED_WARM = create("has_structure/ocean_ruin_red_warm")
    val VILLAGER_TAIGA = create("biome_tag_villagers", "villager_taiga")
    val VILLAGER_SNOWY = create("biome_tag_villagers", "villager_snowy")
    val VILLAGER_SWAMP = create("biome_tag_villagers", "villager_swamp")
    val VILLAGER_DESERT = create("biome_tag_villagers", "villager_desert")

    val HAS_SWAMP_CARBON = create("has_feature/swamp_carbon")

    fun create(id: String): TagKey<Biome> = TagKey.create(Registries.BIOME, id(id))
    fun create(namespace: String, id: String): TagKey<Biome> = TagKey.create(Registries.BIOME, id(namespace, id))
}
