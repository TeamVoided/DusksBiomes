package org.teamvoided.dusks_biomes.data.gen.tags

//import com.theendercore.biome_tag_villagers.BiomeTagVillagers
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBiomeTags
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.BiomeTags
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.Biomes
import org.teamvoided.dusks_biomes.data.tags.DuskBiomeTags
import org.teamvoided.dusks_biomes.init.DuskBiomes
import org.teamvoided.reef.data.ReefTags
import java.util.concurrent.CompletableFuture

class BiomeTagsProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider<Biome>(o, RegistryKeys.BIOME, r) {
    override fun configure(arg: HolderLookup.Provider) {
        duskTags()
        duskTagsHasStructure()
        vanillaTags()
        vanillaTagIsBiome()
        vanillaTagsHasStructure()
        conventionalTagsDimension()
        conventionalTagsCategory()
        conventionalTagsOtherBiomeTypes()
        conventionalTagsClimateAndVegetation()
        conventionalTagsTerrainDescriptor()
        villagerBiomeTags()
        reefTags()
    }

    fun duskTags() {
        getOrCreateTagBuilder(DuskBiomeTags.DUSKS_BIOMES)
            .add(DuskBiomes.COLD_FOREST)
            .add(DuskBiomes.COLD_PLAINS)
            .add(DuskBiomes.WARM_FOREST)
            .add(DuskBiomes.WARM_PLAINS)
            .add(DuskBiomes.WINDSWEPT_BIRCH_FOREST)
            .add(DuskBiomes.SNOWY_WINDSWEPT_HILLS)
            .add(DuskBiomes.SNOWY_WINDSWEPT_GRAVELLY_HILLS)
            .add(DuskBiomes.SNOWY_WINDSWEPT_FOREST)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_PINE_TAIGA)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_SPRUCE_TAIGA)
            .add(DuskBiomes.SNOWY_DARK_GROVE)
            .add(DuskBiomes.SNOWY_CHERRY_GROVE)
            .add(DuskBiomes.FROZEN_BADLANDS)
            .add(DuskBiomes.FROZEN_WOODED_BADLANDS)
            .add(DuskBiomes.FROZEN_ERODED_BADLANDS)
            .add(DuskBiomes.FROZEN_MANGROVE_SWAMP)
            .add(DuskBiomes.FROZEN_WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.OLD_GROWTH_SWAMP)
            .add(DuskBiomes.WARM_RIVER)
            .add(DuskBiomes.RED_DESERT)
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
            .add(DuskBiomes.SAND_CAVES)
            .add(DuskBiomes.RED_SAND_CAVES)
            .add(DuskBiomes.GRAVEL_CAVES)
        getOrCreateTagBuilder(DuskBiomeTags.IS_FROZEN_BADLANDS)
            .add(DuskBiomes.FROZEN_BADLANDS)
            .add(DuskBiomes.FROZEN_WOODED_BADLANDS)
            .add(DuskBiomes.FROZEN_ERODED_BADLANDS)
        getOrCreateTagBuilder(DuskBiomeTags.IS_SNOWY_HILL)
            .add(DuskBiomes.WINDSWEPT_BIRCH_FOREST)
            .add(DuskBiomes.SNOWY_WINDSWEPT_HILLS)
            .add(DuskBiomes.SNOWY_WINDSWEPT_GRAVELLY_HILLS)

        getOrCreateTagBuilder(DuskBiomeTags.HAS_FROZEN_VARIANTS)
            .add(Biomes.DEEP_DARK)
            .add(Biomes.DEEP_FROZEN_OCEAN)
            .add(DuskBiomes.COLD_FOREST)
            .add(DuskBiomes.COLD_PLAINS)
            .add(DuskBiomes.SNOWY_DARK_GROVE)
            .add(DuskBiomes.SNOWY_CHERRY_GROVE)
            .forceAddTag(DuskBiomeTags.IS_SNOWY_HILL)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_PINE_TAIGA)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_SPRUCE_TAIGA)
            .forceAddTag(DuskBiomeTags.IS_FROZEN_BADLANDS)
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
            .add(DuskBiomes.RED_WARM_RIVER)
            .add(DuskBiomes.RED_WARM_OCEAN)
            .add(DuskBiomes.SAND_CAVES)
            .add(DuskBiomes.RED_SAND_CAVES)
    }

    fun duskTagsHasStructure() {
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
    }

    fun vanillaTags() {
        getOrCreateTagBuilder(BiomeTags.HAS_CLOSER_WATER_FOG)
            .add(DuskBiomes.FROZEN_MANGROVE_SWAMP)
            .add(DuskBiomes.FROZEN_WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.OLD_GROWTH_SWAMP)
        getOrCreateTagBuilder(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS)
            .add(DuskBiomes.FROZEN_MANGROVE_SWAMP)
            .add(DuskBiomes.FROZEN_WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.OLD_GROWTH_SWAMP)
        getOrCreateTagBuilder(BiomeTags.ALLOWS_TROPICAL_FISH_SPAWNS_AT_ANY_HEIGHT)
            .add(DuskBiomes.SAND_CAVES)
            .add(DuskBiomes.RED_SAND_CAVES)
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
            .add(DuskBiomes.SAND_CAVES)
            .add(DuskBiomes.RED_SAND_CAVES)
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
    }

    fun vanillaTagIsBiome() {
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
            .add(DuskBiomes.SNOWY_DARK_GROVE)
            .add(DuskBiomes.SNOWY_CHERRY_GROVE)
        getOrCreateTagBuilder(BiomeTags.HILL)
            .forceAddTag(DuskBiomeTags.IS_SNOWY_HILL)
        getOrCreateTagBuilder(BiomeTags.MOUNTAIN)
            .add(Biomes.GROVE)
            .add(DuskBiomes.SNOWY_DARK_GROVE)
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
            .forceAddTag(DuskBiomeTags.DUSKS_BIOMES)
    }

    fun vanillaTagsHasStructure() {
        getOrCreateTagBuilder(BiomeTags.HAS_IGLOO_STRUCTURE)
            .forceAddTag(DuskBiomeTags.IS_SNOWY_HILL)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_PINE_TAIGA)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_SPRUCE_TAIGA)
            .add(DuskBiomes.SNOWY_DARK_GROVE)
            .add(DuskBiomes.SNOWY_CHERRY_GROVE)
            .forceAddTag(DuskBiomeTags.IS_FROZEN_BADLANDS)
            .add(DuskBiomes.FROZEN_MANGROVE_SWAMP)
            .add(DuskBiomes.FROZEN_WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.FROZEN_CAVERNS)
        getOrCreateTagBuilder(BiomeTags.HAS_MINESHAFT_STRUCTURE)
            .add(DuskBiomes.COLD_FOREST)
            .add(DuskBiomes.COLD_PLAINS)
            .add(DuskBiomes.WARM_FOREST)
            .add(DuskBiomes.WARM_PLAINS)
            .forceAddTag(DuskBiomeTags.IS_SNOWY_HILL)
            .add(DuskBiomes.SNOWY_WINDSWEPT_FOREST)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_PINE_TAIGA)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_SPRUCE_TAIGA)
            .add(DuskBiomes.SNOWY_DARK_GROVE)
            .add(DuskBiomes.SNOWY_CHERRY_GROVE)
            .add(DuskBiomes.FROZEN_MANGROVE_SWAMP)
            .add(DuskBiomes.FROZEN_WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.OLD_GROWTH_SWAMP)
            .add(DuskBiomes.WARM_RIVER)
            .add(DuskBiomes.RED_DESERT)
            .add(DuskBiomes.RED_WARM_RIVER)
            .add(DuskBiomes.RED_WARM_OCEAN)
            .add(DuskBiomes.RED_LUKEWARM_OCEAN)
            .add(DuskBiomes.DEEP_RED_LUKEWARM_OCEAN)
            .add(DuskBiomes.RED_BEACH)
            .add(DuskBiomes.SNOWY_RED_BEACH)
            .add(DuskBiomes.SNOWY_STONY_SHORE)
            .add(DuskBiomes.MUSHROOM_GROVE)
            .add(DuskBiomes.MUSHROOM_CAVES)
            .add(DuskBiomes.FROZEN_CAVERNS)
            .add(DuskBiomes.SAND_CAVES)
            .add(DuskBiomes.RED_SAND_CAVES)
            .add(DuskBiomes.GRAVEL_CAVES)
        getOrCreateTagBuilder(BiomeTags.HAS_MINESHAFT_MESA_STRUCTURE)
            .forceAddTag(DuskBiomeTags.IS_FROZEN_BADLANDS)
            .add(DuskBiomes.ERODED_MUSHROOM_ISLAND)
        getOrCreateTagBuilder(BiomeTags.HAS_PILLAGER_OUTPOST_STRUCTURE)
            .add(Biomes.SUNFLOWER_PLAINS)
            .add(Biomes.SAVANNA_PLATEAU)
            .add(DuskBiomes.COLD_PLAINS)
            .add(DuskBiomes.WARM_PLAINS)
            .forceAddTag(DuskBiomeTags.IS_FROZEN_BADLANDS)
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
            .forceAddTag(DuskBiomeTags.IS_FROZEN_BADLANDS)
            .add(DuskBiomes.WINDSWEPT_BIRCH_FOREST)
            .add(DuskBiomes.SNOWY_STONY_SHORE)
            .add(DuskBiomes.ERODED_MUSHROOM_ISLAND)
        getOrCreateTagBuilder(BiomeTags.HAS_RUINED_PORTAL_OCEAN_STRUCTURE)
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
            .add(DuskBiomes.SNOWY_DARK_GROVE)
        getOrCreateTagBuilder(BiomeTags.HAS_SWAMP_HUT_STRUCTURE)
            .add(DuskBiomes.OLD_GROWTH_SWAMP)
        getOrCreateTagBuilder(BiomeTags.HAS_VILLAGE_PLAINS_STRUCTURE)
            .add(Biomes.SUNFLOWER_PLAINS)
            .add(DuskBiomes.COLD_PLAINS)
            .add(DuskBiomes.WARM_PLAINS)
        getOrCreateTagBuilder(BiomeTags.STRONGHOLD_BIASED_TO)
            .add(DuskBiomes.COLD_FOREST)
            .add(DuskBiomes.COLD_PLAINS)
            .add(DuskBiomes.WARM_FOREST)
            .add(DuskBiomes.WARM_PLAINS)
            .add(DuskBiomes.WINDSWEPT_BIRCH_FOREST)
            .forceAddTag(DuskBiomeTags.IS_SNOWY_HILL)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_PINE_TAIGA)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_SPRUCE_TAIGA)
            .add(DuskBiomes.SNOWY_DARK_GROVE)
            .add(DuskBiomes.SNOWY_CHERRY_GROVE)
            .forceAddTag(DuskBiomeTags.IS_FROZEN_BADLANDS)
            .add(DuskBiomes.FROZEN_MANGROVE_SWAMP)
            .add(DuskBiomes.RED_DESERT)
            .add(DuskBiomes.MUSHROOM_GROVE)
            .add(DuskBiomes.ERODED_MUSHROOM_ISLAND)
            .add(DuskBiomes.MUSHROOM_CAVES)
            .add(DuskBiomes.FROZEN_CAVERNS)
            .add(DuskBiomes.SAND_CAVES)
            .add(DuskBiomes.RED_SAND_CAVES)
            .add(DuskBiomes.GRAVEL_CAVES)
    }

    fun conventionalTagsDimension() {
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_OVERWORLD)
            .addOptionalTag(DuskBiomeTags.DUSKS_BIOMES)
    }

    fun conventionalTagsCategory() {
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_WINDSWEPT)
            .addOptionalTag(DuskBiomeTags.IS_SNOWY_HILL)
            .add(DuskBiomes.WINDSWEPT_BIRCH_FOREST)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_PLAINS)
            .add(DuskBiomes.COLD_PLAINS)
            .add(DuskBiomes.WARM_PLAINS)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_ICY)
            .add(DuskBiomes.FROZEN_MANGROVE_SWAMP)
            .add(DuskBiomes.FROZEN_WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.FROZEN_CAVERNS)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_SNOWY)
            .addOptionalTag(DuskBiomeTags.IS_SNOWY_HILL)
            .add(DuskBiomes.SNOWY_DARK_GROVE)
            .add(DuskBiomes.SNOWY_CHERRY_GROVE)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_PINE_TAIGA)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_SPRUCE_TAIGA)
            .add(DuskBiomes.FROZEN_MANGROVE_SWAMP)
            .add(DuskBiomes.FROZEN_WINDSWEPT_MANGROVE_SWAMP)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_BIRCH_FOREST)
            .add(DuskBiomes.WINDSWEPT_BIRCH_FOREST)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_DESERT)
            .add(DuskBiomes.RED_DESERT)
            .add(DuskBiomes.SAND_CAVES)
            .add(DuskBiomes.RED_SAND_CAVES)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_SWAMP)
            .add(DuskBiomes.FROZEN_MANGROVE_SWAMP)
            .add(DuskBiomes.FROZEN_WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.OLD_GROWTH_SWAMP)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_MUSHROOM)
            .add(DuskBiomes.MUSHROOM_GROVE)
            .add(DuskBiomes.ERODED_MUSHROOM_ISLAND)
            .add(DuskBiomes.MUSHROOM_CAVES)
    }

    fun conventionalTagsOtherBiomeTypes() {
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_BADLANDS)
            .addOptionalTag(DuskBiomeTags.IS_FROZEN_BADLANDS)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_CAVE)
            .add(DuskBiomes.MUSHROOM_CAVES)
            .add(DuskBiomes.FROZEN_CAVERNS)
            .add(DuskBiomes.SAND_CAVES)
            .add(DuskBiomes.RED_SAND_CAVES)
            .add(DuskBiomes.GRAVEL_CAVES)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_SHALLOW_OCEAN)
            .add(DuskBiomes.RED_LUKEWARM_OCEAN, DuskBiomes.RED_WARM_OCEAN)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_DEEP_OCEAN)
            .add(DuskBiomes.DEEP_RED_LUKEWARM_OCEAN)
        getOrCreateTagBuilder(ConventionalBiomeTags.NO_DEFAULT_MONSTERS)
            .add(DuskBiomes.MUSHROOM_GROVE)
            .add(DuskBiomes.ERODED_MUSHROOM_ISLAND)
            .add(DuskBiomes.MUSHROOM_CAVES)
    }

    fun conventionalTagsClimateAndVegetation() {
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_COLD_OVERWORLD)
            .addOptionalTag(DuskBiomeTags.IS_SNOWY_HILL)
            .addOptionalTag(DuskBiomeTags.IS_FROZEN_BADLANDS)
            .add(DuskBiomes.COLD_FOREST)
            .add(DuskBiomes.COLD_PLAINS)
            .add(DuskBiomes.SNOWY_DARK_GROVE)
            .add(DuskBiomes.SNOWY_CHERRY_GROVE)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_PINE_TAIGA)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_SPRUCE_TAIGA)
            .add(DuskBiomes.FROZEN_MANGROVE_SWAMP)
            .add(DuskBiomes.FROZEN_WINDSWEPT_MANGROVE_SWAMP)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_TEMPERATE_OVERWORLD)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_HOT_OVERWORLD)
            .add(DuskBiomes.WARM_FOREST)
            .add(DuskBiomes.WARM_PLAINS)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_WET_OVERWORLD)
            .add(DuskBiomes.FROZEN_MANGROVE_SWAMP)
            .add(DuskBiomes.FROZEN_WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.WINDSWEPT_MANGROVE_SWAMP)
            .add(DuskBiomes.OLD_GROWTH_SWAMP)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_DRY_OVERWORLD)
            .addOptionalTag(DuskBiomeTags.IS_FROZEN_BADLANDS)
            .add(DuskBiomes.RED_DESERT)
            .add(DuskBiomes.SAND_CAVES)
            .add(DuskBiomes.RED_SAND_CAVES)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_VEGETATION_DENSE_OVERWORLD)
            .add(DuskBiomes.SNOWY_DARK_GROVE)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_SPRUCE_TAIGA)
            .add(DuskBiomes.MUSHROOM_GROVE)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_VEGETATION_SPARSE_OVERWORLD)
            .addOptionalTag(DuskBiomeTags.IS_SNOWY_HILL)
            .add(DuskBiomes.FROZEN_WOODED_BADLANDS)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_CONIFEROUS_TREE)
            .add(DuskBiomes.COLD_FOREST)
            .add(DuskBiomes.SNOWY_DARK_GROVE)
            .add(DuskBiomes.FROZEN_WOODED_BADLANDS)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_DECIDUOUS_TREE)
            .add(DuskBiomes.COLD_FOREST)
            .add(DuskBiomes.WARM_FOREST)
            .add(DuskBiomes.WINDSWEPT_BIRCH_FOREST)
            .add(DuskBiomes.SNOWY_DARK_GROVE)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_JUNGLE_TREE)
            .add(DuskBiomes.WARM_FOREST)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_FLORAL)
            .add(DuskBiomes.SNOWY_CHERRY_GROVE)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_OLD_GROWTH)
            .add(DuskBiomes.OLD_GROWTH_SWAMP)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_SPRUCE_TAIGA)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_PINE_TAIGA)
    }

    fun conventionalTagsTerrainDescriptor() {
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_MOUNTAIN_SLOPE)
            .add(DuskBiomes.SNOWY_DARK_GROVE)
            .add(DuskBiomes.SNOWY_CHERRY_GROVE)
        getOrCreateTagBuilder(ConventionalBiomeTags.IS_STONY_SHORES)
            .add(DuskBiomes.SNOWY_STONY_SHORE)
    }

    fun reefTags() {
        getOrCreateTagBuilder(ReefTags.HAS_ERODED_PILLAR)
            .add(DuskBiomes.FROZEN_ERODED_BADLANDS, DuskBiomes.ERODED_MUSHROOM_ISLAND)
    }

    fun villagerBiomeTags() {
      /*  getOrCreateTagBuilder(BiomeTagVillagers.VILLAGER_TAIGA)
            .add(DuskBiomes.COLD_FOREST)
            .add(DuskBiomes.COLD_PLAINS)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_PINE_TAIGA)
            .add(DuskBiomes.SNOWY_OLD_GROWTH_SPRUCE_TAIGA)
        getOrCreateTagBuilder(BiomeTagVillagers.VILLAGER_SNOWY)
            .addOptionalTag(DuskBiomeTags.IS_SNOWY_HILL)
            .addOptionalTag(DuskBiomeTags.IS_FROZEN_BADLANDS)
            .add(DuskBiomes.SNOWY_DARK_GROVE)
            .add(DuskBiomes.SNOWY_CHERRY_GROVE)
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
        getOrCreateTagBuilder(BiomeTagVillagers.VILLAGER_DESERT)
            .add(DuskBiomes.WARM_RIVER)
            .add(DuskBiomes.RED_WARM_RIVER)
            .add(DuskBiomes.RED_DESERT)
            .add(DuskBiomes.SAND_CAVES)
            .add(DuskBiomes.RED_SAND_CAVES)*/
    }
}
