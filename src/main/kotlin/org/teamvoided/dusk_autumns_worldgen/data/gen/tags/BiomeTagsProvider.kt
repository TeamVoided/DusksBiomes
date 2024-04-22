package org.teamvoided.dusk_autumns_worldgen.data.gen.tags

import com.theendercore.biome_tag_villagers.BiomeTagVillagers
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBiomeTags
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.BiomeTags
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.Biomes
import org.teamvoided.dusk_autumns_worldgen.data.tags.DuskBiomeTags
import org.teamvoided.dusk_autumns_worldgen.init.DuskBiomes
import org.teamvoided.reef.data.ReefTags
import java.util.concurrent.CompletableFuture

class BiomeTagsProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider<Biome>(o, RegistryKeys.BIOME, r) {
    override fun configure(arg: HolderLookup.Provider?) {
        getOrCreateTagBuilder(ReefTags.HAS_ERODED_PILLAR)
            .add(DuskBiomes.ERODED_MUSHROOM_ISLAND)
        getOrCreateTagBuilder(DuskBiomeTags.DUSK_BIOMES)
            .add(DuskBiomes.COLD_FOREST)
            .add(DuskBiomes.COLD_PLAINS)
            .add(DuskBiomes.WARM_FOREST)
            .add(DuskBiomes.WARM_PLAINS)
            .add(DuskBiomes.WINDSWEPT_BIRCH_FOREST)
            .add(DuskBiomes.SNOWY_CHERRY_GROVE)
            .add(DuskBiomes.SNOWY_WINDSWEPT_HILLS)
            .add(DuskBiomes.SNOWY_WINDSWEPT_GRAVELLY_HILLS)
            .add(DuskBiomes.SNOWY_WINDSWEPT_FOREST)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_PINE_TAIGA)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_SPRUCE_TAIGA)
            .add(DuskBiomes.FROZEN_MANGROVE_SWAMP)
            .add(DuskBiomes.FROZEN_WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.OLD_GROWTH_SWAMP)
            .add(DuskBiomes.WARM_RIVER)
            .add(DuskBiomes.RED_DESERT)
            .add(DuskBiomes.SAND_CAVES)
            .add(DuskBiomes.RED_SAND_CAVES)
            .add(DuskBiomes.RED_WARM_RIVER)
            .add(DuskBiomes.RED_WARM_OCEAN)
            .add(DuskBiomes.RED_LUKEWARM_OCEAN)
            .add(DuskBiomes.DEEP_RED_LUKEWARM_OCEAN)
            .add(DuskBiomes.RED_BEACH)
            .add(DuskBiomes.SNOWY_RED_BEACH)
            .add(DuskBiomes.SNOWY_STONY_SHORE)
            .add(DuskBiomes.MUSHROOM_GROVE)
            .add(DuskBiomes.ERODED_MUSHROOM_ISLAND)
            .add(DuskBiomes.MUSHROOM_CAVES)
            .add(DuskBiomes.FROZEN_CAVERNS)
        getOrCreateTagBuilder(DuskBiomeTags.HAS_FROZEN_VARIANTS)
            .add(DuskBiomes.COLD_FOREST)
            .add(DuskBiomes.COLD_PLAINS)
            .add(Biomes.DEEP_DARK)
            .add(Biomes.DEEP_FROZEN_OCEAN)
            .add(DuskBiomes.SNOWY_CHERRY_GROVE)
            .add(DuskBiomes.SNOWY_WINDSWEPT_HILLS)
            .add(DuskBiomes.SNOWY_WINDSWEPT_GRAVELLY_HILLS)
            .add(DuskBiomes.SNOWY_WINDSWEPT_FOREST)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_PINE_TAIGA)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_SPRUCE_TAIGA)
            .add(DuskBiomes.FROZEN_MANGROVE_SWAMP)
            .add(DuskBiomes.FROZEN_WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.SNOWY_RED_BEACH)
            .add(DuskBiomes.SNOWY_STONY_SHORE)
            .add(DuskBiomes.FROZEN_CAVERNS)
        getOrCreateTagBuilder(DuskBiomeTags.HAS_HOT_VARIANTS)
            .add(DuskBiomes.WARM_FOREST)
            .add(DuskBiomes.WARM_PLAINS)
            .add(DuskBiomes.WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.WARM_RIVER)
            .add(DuskBiomes.RED_DESERT)
            .add(DuskBiomes.SAND_CAVES)
            .add(DuskBiomes.RED_SAND_CAVES)
            .add(DuskBiomes.RED_WARM_RIVER)
            .add(DuskBiomes.RED_WARM_OCEAN)
//Dusk Structure Tags
        getOrCreateTagBuilder(DuskBiomeTags.HAS_VILLAGE_SWAMP_STRUCTURE)
            .add(Biomes.SWAMP)
            .add(DuskBiomes.OLD_GROWTH_SWAMP)
        getOrCreateTagBuilder(DuskBiomeTags.HAS_VILLAGE_MANGROVE_SWAMP_STRUCTURE)
            .add(DuskBiomes.FROZEN_MANGROVE_SWAMP)
            .add(DuskBiomes.FROZEN_WINDSWEPT_MANGROVE_SWAMP)
            .add(Biomes.MANGROVE_SWAMP)
            .add(DuskBiomes.WINDSWEPT_MANGROVE_SWAMP)
//        getOrCreateTagBuilder(DuskBiomeTags.HAS_DESERT_RUIN)
//            .add(Biomes.DESERT)
//            .add(DuskBiomes.WARM_RIVER)
//        getOrCreateTagBuilder(DuskBiomeTags.HAS_RED_DESERT_RUIN)
//            .add(DuskBiomes.RED_DESERT)
//            .add(DuskBiomes.RED_WARM_RIVER)
        getOrCreateTagBuilder(DuskBiomeTags.HAS_OCEAN_RUIN_RED_WARM)
            .add(DuskBiomes.RED_WARM_OCEAN)
            .add(DuskBiomes.RED_LUKEWARM_OCEAN)
            .add(DuskBiomes.DEEP_RED_LUKEWARM_OCEAN)
//Vanilla Tags
        getOrCreateTagBuilder(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS)
            .add(DuskBiomes.FROZEN_MANGROVE_SWAMP)
            .add(DuskBiomes.FROZEN_WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.OLD_GROWTH_SWAMP)
        getOrCreateTagBuilder(BiomeTags.HAS_CLOSER_WATER_FOG)
            .add(DuskBiomes.FROZEN_MANGROVE_SWAMP)
            .add(DuskBiomes.FROZEN_WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.OLD_GROWTH_SWAMP)
        getOrCreateTagBuilder(BiomeTags.INCREASED_FIRE_BURNOUT)
            .add(DuskBiomes.FROZEN_MANGROVE_SWAMP)
            .add(DuskBiomes.FROZEN_WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.OLD_GROWTH_SWAMP)
            .add(DuskBiomes.MUSHROOM_GROVE)
            .add(DuskBiomes.ERODED_MUSHROOM_ISLAND)
            .add(DuskBiomes.MUSHROOM_CAVES)
            .add(DuskBiomes.FROZEN_CAVERNS)
        getOrCreateTagBuilder(BiomeTags.MORE_FREQUENT_DROWNED_SPAWNS)
        getOrCreateTagBuilder(BiomeTags.PLAYS_UNDERWATER_MUSIC)
            .add(DuskBiomes.ERODED_MUSHROOM_ISLAND)
        getOrCreateTagBuilder(BiomeTags.PRODUCES_CORAL_FROM_BONE_MEAL)
            .add(DuskBiomes.RED_WARM_OCEAN)
            .add(DuskBiomes.SAND_CAVES)
            .add(DuskBiomes.RED_SAND_CAVES)
        getOrCreateTagBuilder(BiomeTags.REQUIRED_OCEAN_MONUMENT_SURROUNDING)
            .add(DuskBiomes.ERODED_MUSHROOM_ISLAND)
        getOrCreateTagBuilder(BiomeTags.SNOW_GOLEM_MELTS)
            .forceAddTag(DuskBiomeTags.HAS_HOT_VARIANTS)
        getOrCreateTagBuilder(BiomeTags.SPAWNS_COLD_TYPED_FROGS)
            .forceAddTag(DuskBiomeTags.HAS_FROZEN_VARIANTS)
        getOrCreateTagBuilder(BiomeTags.SPAWNS_SNOW_FOXES)
            .forceAddTag(DuskBiomeTags.HAS_FROZEN_VARIANTS)
        getOrCreateTagBuilder(BiomeTags.SPAWNS_WHITE_RABBITS)
            .forceAddTag(DuskBiomeTags.HAS_FROZEN_VARIANTS)
        getOrCreateTagBuilder(BiomeTags.SPAWNS_WARM_TYPED_FROGS)
            .forceAddTag(DuskBiomeTags.HAS_HOT_VARIANTS)
        getOrCreateTagBuilder(BiomeTags.STRONGHOLD_BIASED_TO)
            .forceAddTag(DuskBiomeTags.DUSK_BIOMES)
        getOrCreateTagBuilder(BiomeTags.WATER_ON_MAP_OUTLINES)
            .add(DuskBiomes.FROZEN_MANGROVE_SWAMP)
            .add(DuskBiomes.FROZEN_WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.OLD_GROWTH_SWAMP)
            .add(DuskBiomes.ERODED_MUSHROOM_ISLAND)
        getOrCreateTagBuilder(BiomeTags.NO_PATROL_SPAWNS)
            .add(DuskBiomes.MUSHROOM_GROVE)
            .add(DuskBiomes.ERODED_MUSHROOM_ISLAND)
            .add(DuskBiomes.MUSHROOM_CAVES)
        getOrCreateTagBuilder(BiomeTags.NO_ZOMBIE_SIEGES)
            .add(DuskBiomes.MUSHROOM_GROVE)
            .add(DuskBiomes.ERODED_MUSHROOM_ISLAND)
            .add(DuskBiomes.MUSHROOM_CAVES)
//Vanilla Structure Tags
        getOrCreateTagBuilder(BiomeTags.HAS_IGLOO_STRUCTURE)
            .add(DuskBiomes.SNOWY_CHERRY_GROVE)
            .add(DuskBiomes.SNOWY_WINDSWEPT_HILLS)
            .add(DuskBiomes.SNOWY_WINDSWEPT_GRAVELLY_HILLS)
            .add(DuskBiomes.SNOWY_WINDSWEPT_FOREST)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_PINE_TAIGA)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_SPRUCE_TAIGA)
            .add(DuskBiomes.FROZEN_CAVERNS)
        getOrCreateTagBuilder(BiomeTags.HAS_MINESHAFT_STRUCTURE)
            .forceAddTag(DuskBiomeTags.DUSK_BIOMES)
        getOrCreateTagBuilder(BiomeTags.HAS_MINESHAFT_MESA_STRUCTURE)
            .add(DuskBiomes.ERODED_MUSHROOM_ISLAND)
        getOrCreateTagBuilder(BiomeTags.HAS_SWAMP_HUT_STRUCTURE)
            .add(DuskBiomes.OLD_GROWTH_SWAMP)
        getOrCreateTagBuilder(BiomeTags.HAS_PILLAGER_OUTPOST_STRUCTURE)
            .forceAddTag(BiomeTags.HAS_VILLAGE_PLAINS_STRUCTURE)
            .forceAddTag(BiomeTags.HAS_VILLAGE_TAIGA_STRUCTURE)
            .add(Biomes.SAVANNA_PLATEAU)
            .add(DuskBiomes.RED_DESERT)
        getOrCreateTagBuilder(BiomeTags.HAS_RUINED_PORTAL_STANDARD_STRUCTURE)
            .add(DuskBiomes.COLD_PLAINS)
            .add(DuskBiomes.WARM_PLAINS)
            .add(DuskBiomes.MUSHROOM_GROVE)
            .add(DuskBiomes.ERODED_MUSHROOM_ISLAND)
            .add(DuskBiomes.MUSHROOM_CAVES)
            .add(DuskBiomes.FROZEN_CAVERNS)
        getOrCreateTagBuilder(BiomeTags.HAS_RUINED_PORTAL_DESERT_STRUCTURE)
            .add(DuskBiomes.RED_DESERT)
            .add(DuskBiomes.SAND_CAVES)
            .add(DuskBiomes.RED_SAND_CAVES)
        getOrCreateTagBuilder(BiomeTags.HAS_RUINED_PORTAL_MOUNTAIN_STRUCTURE)
            .add(DuskBiomes.WINDSWEPT_BIRCH_FOREST)
            .add(DuskBiomes.SNOWY_STONY_SHORE)
            .add(DuskBiomes.ERODED_MUSHROOM_ISLAND)
        getOrCreateTagBuilder(BiomeTags.HAS_RUINED_PORTAL_SWAMP_STRUCTURE)
            .add(DuskBiomes.FROZEN_MANGROVE_SWAMP)
            .add(DuskBiomes.FROZEN_WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.OLD_GROWTH_SWAMP)
        getOrCreateTagBuilder(BiomeTags.TRAIL_RUINS_HAS_STRUCTURE)
            .add(Biomes.DARK_FOREST)
            .add(Biomes.BIRCH_FOREST)
            .add(Biomes.BAMBOO_JUNGLE)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_PINE_TAIGA)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_SPRUCE_TAIGA)
        getOrCreateTagBuilder(BiomeTags.HAS_VILLAGE_PLAINS_STRUCTURE)
            .add(Biomes.SUNFLOWER_PLAINS)
            .add(DuskBiomes.COLD_PLAINS)
            .add(DuskBiomes.WARM_PLAINS)
        getOrCreateTagBuilder(BiomeTags.HAS_VILLAGE_TAIGA_STRUCTURE)
            .add(Biomes.SNOWY_TAIGA)
        getOrCreateTagBuilder(BiomeTags.STRONGHOLD_BIASED_TO)
            .add(DuskBiomes.COLD_FOREST)
            .add(DuskBiomes.COLD_PLAINS)
            .add(DuskBiomes.WARM_FOREST)
            .add(DuskBiomes.WARM_PLAINS)
            .add(DuskBiomes.WINDSWEPT_BIRCH_FOREST)
            .add(DuskBiomes.SNOWY_CHERRY_GROVE)
            .add(DuskBiomes.SNOWY_WINDSWEPT_HILLS)
            .add(DuskBiomes.SNOWY_WINDSWEPT_GRAVELLY_HILLS)
            .add(DuskBiomes.SNOWY_WINDSWEPT_FOREST)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_PINE_TAIGA)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_SPRUCE_TAIGA)
            .add(DuskBiomes.RED_DESERT)
            .add(DuskBiomes.SAND_CAVES)
            .add(DuskBiomes.RED_SAND_CAVES)
            .add(DuskBiomes.RED_BEACH)
            .add(DuskBiomes.SNOWY_RED_BEACH)
            .add(DuskBiomes.SNOWY_STONY_SHORE)
            .add(DuskBiomes.MUSHROOM_GROVE)
            .add(DuskBiomes.MUSHROOM_CAVES)
            .add(DuskBiomes.FROZEN_CAVERNS)
//Vanilla Is Biome Tags
        getOrCreateTagBuilder(BiomeTags.BEACH)
            .add(DuskBiomes.RED_BEACH)
            .add(DuskBiomes.SNOWY_RED_BEACH)
        getOrCreateTagBuilder(BiomeTags.DEEP_OCEAN)
            .add(DuskBiomes.DEEP_RED_LUKEWARM_OCEAN)
        getOrCreateTagBuilder(BiomeTags.FOREST)
            .add(DuskBiomes.COLD_FOREST)
            .add(DuskBiomes.WARM_FOREST)
            .add(DuskBiomes.WINDSWEPT_BIRCH_FOREST)
            .add(Biomes.CHERRY_GROVE)
            .add(DuskBiomes.SNOWY_CHERRY_GROVE)
        getOrCreateTagBuilder(BiomeTags.HILL)
            .add(DuskBiomes.SNOWY_WINDSWEPT_HILLS)
            .add(DuskBiomes.SNOWY_WINDSWEPT_FOREST)
            .add(DuskBiomes.SNOWY_WINDSWEPT_GRAVELLY_HILLS)
        getOrCreateTagBuilder(BiomeTags.MOUNTAIN)
            .add(Biomes.GROVE)
            .add(DuskBiomes.SNOWY_CHERRY_GROVE)
        getOrCreateTagBuilder(BiomeTags.OCEAN)
            .add(DuskBiomes.RED_LUKEWARM_OCEAN)
            .add(DuskBiomes.RED_WARM_OCEAN)
        getOrCreateTagBuilder(BiomeTags.RIVER)
            .add(DuskBiomes.WARM_RIVER)
            .add(DuskBiomes.RED_WARM_RIVER)
        getOrCreateTagBuilder(BiomeTags.TAIGA)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_PINE_TAIGA)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_SPRUCE_TAIGA)
        getOrCreateTagBuilder(BiomeTags.OVERWORLD)
            .forceAddTag(DuskBiomeTags.DUSK_BIOMES)

//        getOrCreateTagBuilder(BiomeTags.)
//            .add(DuskBiomes.)

//      Fabric Tags
        getOrCreateTagBuilder(ConventionalBiomeTags.IN_OVERWORLD)
            .forceAddTag(DuskBiomeTags.DUSK_BIOMES)
        getOrCreateTagBuilder(ConventionalBiomeTags.EXTREME_HILLS)
            .add(DuskBiomes.SNOWY_WINDSWEPT_HILLS)
            .add(DuskBiomes.SNOWY_WINDSWEPT_GRAVELLY_HILLS)
        getOrCreateTagBuilder(ConventionalBiomeTags.WINDSWEPT)
            .add(DuskBiomes.WINDSWEPT_BIRCH_FOREST)
            .add(DuskBiomes.SNOWY_WINDSWEPT_HILLS)
            .add(DuskBiomes.SNOWY_WINDSWEPT_GRAVELLY_HILLS)
            .add(DuskBiomes.SNOWY_WINDSWEPT_FOREST)
        getOrCreateTagBuilder(ConventionalBiomeTags.PLAINS)
            .add(DuskBiomes.COLD_PLAINS)
            .add(DuskBiomes.WARM_PLAINS)
        getOrCreateTagBuilder(ConventionalBiomeTags.ICY)
            .add(DuskBiomes.FROZEN_MANGROVE_SWAMP)
            .add(DuskBiomes.FROZEN_WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.FROZEN_CAVERNS)
        getOrCreateTagBuilder(ConventionalBiomeTags.BEACH)
            .add(DuskBiomes.SNOWY_STONY_SHORE)
        getOrCreateTagBuilder(ConventionalBiomeTags.BIRCH_FOREST)
            .add(DuskBiomes.WINDSWEPT_BIRCH_FOREST)
        getOrCreateTagBuilder(ConventionalBiomeTags.DESERT)
            .add(DuskBiomes.RED_DESERT)
            .add(DuskBiomes.SAND_CAVES)
            .add(DuskBiomes.RED_SAND_CAVES)
        getOrCreateTagBuilder(ConventionalBiomeTags.MUSHROOM)
            .add(DuskBiomes.MUSHROOM_GROVE)
            .add(DuskBiomes.ERODED_MUSHROOM_ISLAND)
            .add(DuskBiomes.MUSHROOM_CAVES)
        getOrCreateTagBuilder(ConventionalBiomeTags.UNDERGROUND)
            .add(DuskBiomes.MUSHROOM_CAVES)
            .add(DuskBiomes.FROZEN_CAVERNS)
            .add(DuskBiomes.SAND_CAVES)
            .add(DuskBiomes.RED_SAND_CAVES)
        getOrCreateTagBuilder(ConventionalBiomeTags.CLIMATE_HOT)
            .add(DuskBiomes.WARM_FOREST)
            .add(DuskBiomes.WARM_PLAINS)
        getOrCreateTagBuilder(ConventionalBiomeTags.CLIMATE_TEMPERATE)
            .add(DuskBiomes.RED_BEACH)
        getOrCreateTagBuilder(ConventionalBiomeTags.CLIMATE_COLD)
            .add(DuskBiomes.COLD_FOREST)
            .add(DuskBiomes.COLD_PLAINS)
            .add(DuskBiomes.SNOWY_CHERRY_GROVE)
            .add(DuskBiomes.SNOWY_WINDSWEPT_HILLS)
            .add(DuskBiomes.SNOWY_WINDSWEPT_GRAVELLY_HILLS)
            .add(DuskBiomes.SNOWY_WINDSWEPT_FOREST)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_PINE_TAIGA)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_SPRUCE_TAIGA)
            .add(DuskBiomes.FROZEN_MANGROVE_SWAMP)
            .add(DuskBiomes.FROZEN_WINDSWEPT_MANGROVE_SWAMP)
        getOrCreateTagBuilder(ConventionalBiomeTags.VEGETATION_DENSE)
            .add(DuskBiomes.MUSHROOM_GROVE)
        getOrCreateTagBuilder(ConventionalBiomeTags.TREE_CONIFEROUS)
            .add(DuskBiomes.COLD_FOREST)
        getOrCreateTagBuilder(ConventionalBiomeTags.TREE_DECIDUOUS)
            .add(DuskBiomes.COLD_FOREST)
            .add(DuskBiomes.WARM_FOREST)
        getOrCreateTagBuilder(ConventionalBiomeTags.TREE_JUNGLE)
            .add(DuskBiomes.WARM_FOREST)
        getOrCreateTagBuilder(ConventionalBiomeTags.SNOWY)
            .add(DuskBiomes.SNOWY_CHERRY_GROVE)
            .add(DuskBiomes.SNOWY_WINDSWEPT_HILLS)
            .add(DuskBiomes.SNOWY_WINDSWEPT_GRAVELLY_HILLS)
            .add(DuskBiomes.SNOWY_WINDSWEPT_FOREST)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_PINE_TAIGA)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_SPRUCE_TAIGA)
            .add(DuskBiomes.FROZEN_MANGROVE_SWAMP)
            .add(DuskBiomes.FROZEN_WINDSWEPT_MANGROVE_SWAMP)
        getOrCreateTagBuilder(ConventionalBiomeTags.CAVES)
            .add(DuskBiomes.MUSHROOM_CAVES)
            .add(DuskBiomes.FROZEN_CAVERNS)
            .add(DuskBiomes.SAND_CAVES)
            .add(DuskBiomes.RED_SAND_CAVES)
        getOrCreateTagBuilder(ConventionalBiomeTags.STONY_SHORES)
            .add(DuskBiomes.SNOWY_STONY_SHORE)
        getOrCreateTagBuilder(ConventionalBiomeTags.DEEP_OCEAN)
            .add(DuskBiomes.DEEP_RED_LUKEWARM_OCEAN)
        getOrCreateTagBuilder(ConventionalBiomeTags.SHALLOW_OCEAN)
            .add(DuskBiomes.RED_LUKEWARM_OCEAN)
            .add(DuskBiomes.RED_WARM_OCEAN)
        getOrCreateTagBuilder(ConventionalBiomeTags.SWAMP)
            .add(DuskBiomes.FROZEN_MANGROVE_SWAMP)
            .add(DuskBiomes.FROZEN_WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.OLD_GROWTH_SWAMP)

//Villager Biome Tags
        getOrCreateTagBuilder(BiomeTagVillagers.VILLAGER_TAIGA)
            .add(DuskBiomes.COLD_FOREST)
            .add(DuskBiomes.COLD_PLAINS)
            .add(Biomes.SNOWY_TAIGA)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_PINE_TAIGA)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_SPRUCE_TAIGA)
        getOrCreateTagBuilder(BiomeTagVillagers.VILLAGER_SNOWY)
            .add(DuskBiomes.SNOWY_CHERRY_GROVE)
            .add(DuskBiomes.SNOWY_WINDSWEPT_HILLS)
            .add(DuskBiomes.SNOWY_WINDSWEPT_GRAVELLY_HILLS)
            .add(DuskBiomes.SNOWY_WINDSWEPT_FOREST)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_PINE_TAIGA)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_SPRUCE_TAIGA)
            .add(DuskBiomes.FROZEN_MANGROVE_SWAMP)
            .add(DuskBiomes.FROZEN_WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.SNOWY_RED_BEACH)
            .add(DuskBiomes.SNOWY_STONY_SHORE)
            .add(DuskBiomes.FROZEN_CAVERNS)
        getOrCreateTagBuilder(BiomeTagVillagers.VILLAGER_SWAMP)
            .add(DuskBiomes.FROZEN_MANGROVE_SWAMP)
            .add(DuskBiomes.FROZEN_WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.OLD_GROWTH_SWAMP)
        getOrCreateTagBuilder(BiomeTagVillagers.VILLAGER_SAVANNA)
        getOrCreateTagBuilder(BiomeTagVillagers.VILLAGER_DESERT)
            .add(DuskBiomes.WARM_RIVER)
            .add(DuskBiomes.RED_WARM_RIVER)
            .add(DuskBiomes.RED_DESERT)
            .add(DuskBiomes.SAND_CAVES)
            .add(DuskBiomes.RED_SAND_CAVES)
    }
}