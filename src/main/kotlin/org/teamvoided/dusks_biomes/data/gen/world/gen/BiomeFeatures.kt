package org.teamvoided.dusks_biomes.data.gen.world.gen

import net.minecraft.data.worldgen.BiomeDefaultFeatures
import net.minecraft.data.worldgen.placement.AquaticPlacements
import net.minecraft.data.worldgen.placement.VegetationPlacements
import net.minecraft.world.level.biome.BiomeGenerationSettings
import org.teamvoided.dusks_biomes.data.world.gen.DuskPlacedFeatures
import net.minecraft.world.level.levelgen.GenerationStep.Decoration.LOCAL_MODIFICATIONS as lm2
import net.minecraft.world.level.levelgen.GenerationStep.Decoration.SURFACE_STRUCTURES as ss4
import net.minecraft.world.level.levelgen.GenerationStep.Decoration.UNDERGROUND_DECORATION as ud7
import net.minecraft.world.level.levelgen.GenerationStep.Decoration.UNDERGROUND_ORES as uo6
import net.minecraft.world.level.levelgen.GenerationStep.Decoration.UNDERGROUND_STRUCTURES as us3
import net.minecraft.world.level.levelgen.GenerationStep.Decoration.VEGETAL_DECORATION as vd9

/*just in case it gets deleted
import net.minecraft.world.gen.GenerationStep.Feature.RAW_GENERATION as rg0
import net.minecraft.world.gen.GenerationStep.Feature.LAKES as l1
import net.minecraft.world.level.levelgen.GenerationStep.Decoration.LOCAL_MODIFICATIONS as lm2
import net.minecraft.world.level.levelgen.GenerationStep.Decoration.UNDERGROUND_STRUCTURES as us3
import net.minecraft.world.level.levelgen.GenerationStep.Decoration.SURFACE_STRUCTURES as ss4
import net.minecraft.world.gen.GenerationStep.Feature.STRONGHOLDS as s5
import net.minecraft.world.level.levelgen.GenerationStep.Decoration.UNDERGROUND_ORES as uo6
import net.minecraft.world.level.levelgen.GenerationStep.Decoration.UNDERGROUND_DECORATION as ud7
import net.minecraft.world.gen.GenerationStep.Feature.FLUID_SPRINGS as fs8
import net.minecraft.world.level.levelgen.GenerationStep.Decoration.VEGETAL_DECORATION as vd9
*/

object BiomeFeatures {

    fun addBasicFeaturesNoDungeon(generationSettings: BiomeGenerationSettings.Builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(generationSettings)
        BiomeDefaultFeatures.addDefaultCrystalFormations(generationSettings)
        //DefaultBiomeFeatures.addUndergroundVariety(generationSettings)
        BiomeDefaultFeatures.addDefaultSprings(generationSettings)
        BiomeDefaultFeatures.addSurfaceFreezing(generationSettings)
    }

    fun addOldGrowthSwampFeatures(generationSettings: BiomeGenerationSettings.Builder) {
//        generationSettings.addFeature(vd9, DuskPlacedFeatures.TREES_OLD_GROWTH_SWAMP)
        generationSettings.addFeature(vd9, VegetationPlacements.FLOWER_SWAMP)
        generationSettings.addFeature(vd9, VegetationPlacements.PATCH_GRASS_NORMAL)
        generationSettings.addFeature(vd9, VegetationPlacements.PATCH_DEAD_BUSH)
        generationSettings.addFeature(vd9, VegetationPlacements.PATCH_WATERLILY)
        generationSettings.addFeature(vd9, VegetationPlacements.BROWN_MUSHROOM_SWAMP)
        generationSettings.addFeature(vd9, VegetationPlacements.RED_MUSHROOM_SWAMP)
    }

    fun addDesertsFeatures(generationSettings: BiomeGenerationSettings.Builder, red: Boolean, cave: Boolean) {
        BiomeDefaultFeatures.addDesertExtraVegetation(generationSettings)
        addSandDungeons(generationSettings, red)
        if (!cave) {
            generationSettings.addFeature(
                ss4,
                if (red) DuskPlacedFeatures.RED_DESERT_WELL else DuskPlacedFeatures.DESERT_WELL
            )
        } else {
//            generationSettings.addFeature(vd9, DuskPlacedFeatures.SAND_CAVE_VINES)
            generationSettings.addFeature(vd9, DuskPlacedFeatures.SAND_CACTUS)
            generationSettings.addFeature(vd9, DuskPlacedFeatures.CAVE_DEAD_BUSH)
            generationSettings.addFeature(vd9, DuskPlacedFeatures.ORE_COARSE_DIRT)
            generationSettings.addFeature(vd9, DuskPlacedFeatures.CAVE_GLOW_LICHEN_EXTRA)
            generationSettings.addFeature(vd9, DuskPlacedFeatures.SAND_CAVE_CORAL)
            generationSettings.addFeature(vd9, DuskPlacedFeatures.SAND_CAVE_SEAGRASS)
            generationSettings.addFeature(vd9, DuskPlacedFeatures.SAND_CAVE_PICKLE)
            if (red) {
                generationSettings.addFeature(uo6, DuskPlacedFeatures.ORE_RED_SAND)
                generationSettings.addFeature(lm2, DuskPlacedFeatures.RED_SAND_CAVE_PILLAR)
                generationSettings.addFeature(us3, DuskPlacedFeatures.CAVE_RED_DESERT_WELL)
                generationSettings.addFeature(ud7, DuskPlacedFeatures.RED_SAND_SPIKES)
                generationSettings.addFeature(ud7, DuskPlacedFeatures.RED_SAND_SPIKES_ROOF)
            } else {
                generationSettings.addFeature(uo6, DuskPlacedFeatures.ORE_SAND)
                generationSettings.addFeature(lm2, DuskPlacedFeatures.SAND_CAVE_PILLAR)
                generationSettings.addFeature(us3, DuskPlacedFeatures.CAVE_DESERT_WELL)
                generationSettings.addFeature(ud7, DuskPlacedFeatures.SAND_SPIKES)
                generationSettings.addFeature(ud7, DuskPlacedFeatures.SAND_SPIKES_ROOF)
            }
        }
    }

    fun addGravelCaveFeatures(generationSettings: BiomeGenerationSettings.Builder) {
        generationSettings.addFeature(vd9, DuskPlacedFeatures.CAVE_DEAD_BUSH)
        generationSettings.addFeature(vd9, DuskPlacedFeatures.ORE_COARSE_DIRT)
        generationSettings.addFeature(vd9, DuskPlacedFeatures.CAVE_GLOW_LICHEN_EXTRA)
        generationSettings.addFeature(uo6, DuskPlacedFeatures.ORE_COBBLESTONE)
        generationSettings.addFeature(lm2, DuskPlacedFeatures.COBBLESTONE_CAVE_PILLAR)
        generationSettings.addFeature(ud7, DuskPlacedFeatures.COBBLESTONE_SPIKES)
        generationSettings.addFeature(ud7, DuskPlacedFeatures.COBBLESTONE_SPIKES_ROOF)
        generationSettings.addFeature(lm2, DuskPlacedFeatures.COBBLED_DEEPSLATE_CAVE_PILLAR)
        generationSettings.addFeature(ud7, DuskPlacedFeatures.COBBLED_DEEPSLATE_SPIKES)
        generationSettings.addFeature(ud7, DuskPlacedFeatures.COBBLED_DEEPSLATE_SPIKES_ROOF)
    }

    fun addMushroomGroveFeatures(generationSettings: BiomeGenerationSettings.Builder) {
        generationSettings.addFeature(vd9, DuskPlacedFeatures.MUSHROOM_GROVE_VEGETATION)
        generationSettings.addFeature(vd9, VegetationPlacements.BROWN_MUSHROOM_TAIGA)
        generationSettings.addFeature(vd9, VegetationPlacements.RED_MUSHROOM_TAIGA)
    }

    fun addMushroomErodedFeatures(generationSettings: BiomeGenerationSettings.Builder) {
        generationSettings.addFeature(vd9, AquaticPlacements.SEAGRASS_NORMAL)
        BiomeDefaultFeatures.addColdOceanExtraVegetation(generationSettings)
    }

    fun addMushroomCaveFeatures(generationSettings: BiomeGenerationSettings.Builder) {
        generationSettings.addFeature(vd9, DuskPlacedFeatures.CAVE_DEAD_BUSH)
        generationSettings.addFeature(vd9, DuskPlacedFeatures.ORE_COARSE_DIRT)
        generationSettings.addFeature(vd9, DuskPlacedFeatures.CAVE_GLOW_LICHEN_EXTRA)
        generationSettings.addFeature(vd9, DuskPlacedFeatures.MUSHROOM_CAVE_MUSHROOMS)
        generationSettings.addFeature(vd9, DuskPlacedFeatures.MUSHROOM_CAVE_VEGETATION)
        generationSettings.addFeature(vd9, DuskPlacedFeatures.MUSHROOM_CAVE_SURFACE)
    }

    fun addFrozenCavernsFeatures(generationSettings: BiomeGenerationSettings.Builder) {
        addFrozenDungeons(generationSettings)
        generationSettings.addFeature(vd9, DuskPlacedFeatures.CAVE_GLOW_LICHEN_EXTRA)
        generationSettings.addFeature(lm2, DuskPlacedFeatures.ICE_CAVE_PILLAR)
        generationSettings.addFeature(lm2, DuskPlacedFeatures.ORE_ICE)
        generationSettings.addFeature(lm2, DuskPlacedFeatures.ORE_BLUE_ICE)
        generationSettings.addFeature(us3, DuskPlacedFeatures.ICE_SPIKE_FLOOR)
        generationSettings.addFeature(us3, DuskPlacedFeatures.ICE_SPIKE_CEILING)
//        generationSettings.addFeature(us3, DuskPlacedFeatures.ICE_CAVE_FOSSIL)
    }

    fun addLushDungeons(builder: BiomeGenerationSettings.Builder) {
        builder.addFeature(us3, DuskPlacedFeatures.LUSH_MONSTER_ROOM)
        builder.addFeature(us3, DuskPlacedFeatures.DEEP_LUSH_MONSTER_ROOM)
    }

    fun addFrozenDungeons(builder: BiomeGenerationSettings.Builder) {
        builder.addFeature(us3, DuskPlacedFeatures.FROZEN_MONSTER_ROOM)
        builder.addFeature(us3, DuskPlacedFeatures.DEEP_FROZEN_MONSTER_ROOM)
    }

    fun addSandDungeons(builder: BiomeGenerationSettings.Builder, red: Boolean) {
        if (red) {
            builder.addFeature(us3, DuskPlacedFeatures.RED_SAND_MONSTER_ROOM)
            builder.addFeature(us3, DuskPlacedFeatures.DEEP_RED_SAND_MONSTER_ROOM)
        } else {
            builder.addFeature(us3, DuskPlacedFeatures.SAND_MONSTER_ROOM)
            builder.addFeature(us3, DuskPlacedFeatures.DEEP_SAND_MONSTER_ROOM)
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
    fun addWindsweptValleyFeatures(generationSettings: BiomeGenerationSettings.Builder, variant: String) {
        if (variant == "topaz") {
            generationSettings.addFeature(uo6, OrePlacedFeatures.ORE_EMERALD)
        } else if (variant == "sapphire") {
            generationSettings.addFeature(uo6, OrePlacedFeatures.ORE_EMERALD)
        } else if (variant == "ruby") {
            generationSettings.addFeature(uo6, OrePlacedFeatures.ORE_EMERALD)
        } else {
            generationSettings.addFeature(uo6, OrePlacedFeatures.ORE_EMERALD)
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
