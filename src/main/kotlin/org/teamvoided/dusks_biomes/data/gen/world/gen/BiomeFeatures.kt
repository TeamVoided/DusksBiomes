package org.teamvoided.dusks_biomes.data.gen.world.gen

import net.minecraft.world.biome.GenerationSettings
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.feature.DefaultBiomeFeatures
import net.minecraft.world.gen.feature.OceanPlacedFeatures
import net.minecraft.world.gen.feature.VegetationPlacedFeatures
import org.teamvoided.dusks_biomes.data.world.gen.DuskPlacedFeatures

object BiomeFeatures {

    fun addBasicFeaturesNoDungeon(generationSettings: GenerationSettings.Builder?) {
        DefaultBiomeFeatures.addLandCarvers(generationSettings)
        DefaultBiomeFeatures.addAmethystGeodes(generationSettings)
        DefaultBiomeFeatures.addUndergroundVariety(generationSettings)
        DefaultBiomeFeatures.addSprings(generationSettings)
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings)
    }

    fun addOldGrowthSwampFeatures(generationSettings: GenerationSettings.Builder) {
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeatures.TREES_OLD_GROWTH_SWAMP)
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.FLOWER_SWAMP)
        generationSettings.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            VegetationPlacedFeatures.PATCH_GRASS_NORMAL
        )
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_DEAD_BUSH)
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_WATERLILY)
        generationSettings.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            VegetationPlacedFeatures.BROWN_MUSHROOM_SWAMP
        )
        generationSettings.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            VegetationPlacedFeatures.RED_MUSHROOM_SWAMP
        )
    }

    fun addDesertsFeatures(generationSettings: GenerationSettings.Builder, red: Boolean, cave: Boolean) {
        DefaultBiomeFeatures.addDesertVegetation(generationSettings)
        addSandDungeons(generationSettings, red)
        if (!cave) {
            generationSettings.feature(
                GenerationStep.Feature.SURFACE_STRUCTURES,
                if (red) DuskPlacedFeatures.RED_DESERT_WELL else DuskPlacedFeatures.DESERT_WELL
            )
        } else {
            generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeatures.SAND_CAVE_VINES)
            generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeatures.SAND_CACTUS)
            generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeatures.CAVE_DEAD_BUSH)
            generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeatures.ORE_COARSE_DIRT)
            generationSettings.feature(
                GenerationStep.Feature.VEGETAL_DECORATION,
                DuskPlacedFeatures.CAVE_GLOW_LICHEN_EXTRA
            )
            generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeatures.SAND_CAVE_CORAL)
            generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeatures.SAND_CAVE_SEAGRASS)
            generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeatures.SAND_CAVE_PICKLE)
            if (red) {
                generationSettings.feature(
                    GenerationStep.Feature.UNDERGROUND_ORES,
                    DuskPlacedFeatures.ORE_RED_SAND
                )
                generationSettings.feature(
                    GenerationStep.Feature.LOCAL_MODIFICATIONS,
                    DuskPlacedFeatures.RED_SAND_CAVE_PILLAR
                )
                generationSettings.feature(
                    GenerationStep.Feature.UNDERGROUND_STRUCTURES,
                    DuskPlacedFeatures.CAVE_RED_DESERT_WELL
                )
                generationSettings.feature(
                    GenerationStep.Feature.UNDERGROUND_DECORATION,
                    DuskPlacedFeatures.RED_SAND_SPIKES
                )
                generationSettings.feature(
                    GenerationStep.Feature.UNDERGROUND_DECORATION,
                    DuskPlacedFeatures.RED_SAND_SPIKES_ROOF
                )
            } else {
                generationSettings.feature(
                    GenerationStep.Feature.UNDERGROUND_ORES,
                    DuskPlacedFeatures.ORE_SAND
                )
                generationSettings.feature(
                    GenerationStep.Feature.LOCAL_MODIFICATIONS,
                    DuskPlacedFeatures.SAND_CAVE_PILLAR
                )
                generationSettings.feature(
                    GenerationStep.Feature.UNDERGROUND_STRUCTURES,
                    DuskPlacedFeatures.CAVE_DESERT_WELL
                )
                generationSettings.feature(
                    GenerationStep.Feature.UNDERGROUND_DECORATION,
                    DuskPlacedFeatures.SAND_SPIKES
                )
                generationSettings.feature(
                    GenerationStep.Feature.UNDERGROUND_DECORATION,
                    DuskPlacedFeatures.SAND_SPIKES_ROOF
                )
            }
        }
    }

    fun addMushroomGroveFeatures(generationSettings: GenerationSettings.Builder) {
        generationSettings.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            DuskPlacedFeatures.MUSHROOM_GROVE_VEGETATION
        )
        generationSettings.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            VegetationPlacedFeatures.BROWN_MUSHROOM_TAIGA
        )
        generationSettings.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            VegetationPlacedFeatures.RED_MUSHROOM_TAIGA
        )
    }

    fun addMushroomErodedFeatures(generationSettings: GenerationSettings.Builder) {
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.SEAGRASS_NORMAL)
        DefaultBiomeFeatures.addSeagrassOnStone(generationSettings)
        DefaultBiomeFeatures.addKelp(generationSettings)
    }

    fun addMushroomCaveFeatures(generationSettings: GenerationSettings.Builder) {
        generationSettings.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            DuskPlacedFeatures.CAVE_DEAD_BUSH
        )
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeatures.ORE_COARSE_DIRT)
        generationSettings.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            DuskPlacedFeatures.CAVE_GLOW_LICHEN_EXTRA
        )
        generationSettings.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            DuskPlacedFeatures.MUSHROOM_CAVE_MUSHROOMS
        )
        generationSettings.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            DuskPlacedFeatures.MUSHROOM_CAVE_VEGETATION
        )
        generationSettings.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            DuskPlacedFeatures.MUSHROOM_CAVE_SURFACE
        )
    }

    fun addFrozenCavernsFeatures(generationSettings: GenerationSettings.Builder) {
        addFrozenDungeons(generationSettings)
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeatures.CAVE_GLOW_LICHEN_EXTRA)
        generationSettings.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, DuskPlacedFeatures.ICE_CAVE_PILLAR)
        generationSettings.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, DuskPlacedFeatures.ORE_ICE)
        generationSettings.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, DuskPlacedFeatures.ORE_BLUE_ICE)
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, DuskPlacedFeatures.ICE_SPIKE_FLOOR)
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, DuskPlacedFeatures.ICE_SPIKE_CEILING)
//        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, DuskPlacedFeatures.ICE_CAVE_FOSSIL)
    }

    fun addLushDungeons(builder: GenerationSettings.Builder) {
        builder.feature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, DuskPlacedFeatures.LUSH_MONSTER_ROOM)
        builder.feature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, DuskPlacedFeatures.DEEP_LUSH_MONSTER_ROOM)
    }

    fun addFrozenDungeons(builder: GenerationSettings.Builder) {
        builder.feature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, DuskPlacedFeatures.FROZEN_MONSTER_ROOM)
        builder.feature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, DuskPlacedFeatures.DEEP_FROZEN_MONSTER_ROOM)
    }

    fun addSandDungeons(builder: GenerationSettings.Builder, red: Boolean) {
        if (red) {
            builder.feature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, DuskPlacedFeatures.RED_SAND_MONSTER_ROOM)
            builder.feature(
                GenerationStep.Feature.UNDERGROUND_STRUCTURES,
                DuskPlacedFeatures.DEEP_RED_SAND_MONSTER_ROOM
            )
        } else {
            builder.feature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, DuskPlacedFeatures.SAND_MONSTER_ROOM)
            builder.feature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, DuskPlacedFeatures.DEEP_SAND_MONSTER_ROOM)
        }
    }
}
/*    Generation Steps Reference:
      RAW_GENERATION
      LAKES
      LOCAL_MODIFICATIONS
      UNDERGROUND_STRUCTURES
      SURFACE_STRUCTURES
      STRONGHOLDS
      UNDERGROUND_ORES
      UNDERGROUND_DECORATION
      FLUID_SPRINGS
      VEGETAL_DECORATION
      TOP_LAYER_MODIFICATION
 */
/*
    fun addWindsweptValleyFeatures(generationSettings: GenerationSettings.Builder, variant: String) {
        if (variant == "topaz") {
            generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_EMERALD)
        } else if (variant == "sapphire") {
            generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_EMERALD)
        } else if (variant == "ruby") {
            generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_EMERALD)
        } else {
            generationSettings.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_EMERALD)
        }
    }
    fun addWindsweptValleyMobs(spawns: SpawnSettings.Builder, variant: String) {
        if (variant == "ruby") {
            DefaultBiomeFeatures.addCaveMobs(spawns)
            DefaultBiomeFeatures.addMonsters(spawns, 95, 5, 20, false)
            spawns.spawn(SpawnGroup.MONSTER, SpawnSettings.SpawnEntry(EntityType.STRAY, 80, 4, 4))
        } else if (variant == "topaz") {
            DefaultBiomeFeatures.addCaveMobs(spawns)
            DefaultBiomeFeatures.addMonsters(spawns, 19, 1, 100, false)
            spawns.spawn(SpawnGroup.MONSTER, SpawnSettings.SpawnEntry(EntityType.HUSK, 80, 4, 4))
        } else {
            DefaultBiomeFeatures.addBatsAndMonsters(spawns)
        }
    }
 */
