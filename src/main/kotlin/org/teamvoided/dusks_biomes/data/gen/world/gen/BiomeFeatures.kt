package org.teamvoided.dusks_biomes.data.gen.world.gen

import net.minecraft.world.biome.GenerationSettings
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.feature.DefaultBiomeFeatures
import net.minecraft.world.gen.feature.OceanPlacedFeatures
import net.minecraft.world.gen.feature.VegetationPlacedFeatures
import org.teamvoided.dusks_biomes.data.world.gen.DuskPlacedFeatures
import net.minecraft.world.gen.GenerationStep.Feature.RAW_GENERATION as rg0
import net.minecraft.world.gen.GenerationStep.Feature.LAKES as l1
import net.minecraft.world.gen.GenerationStep.Feature.LOCAL_MODIFICATIONS as lm2
import net.minecraft.world.gen.GenerationStep.Feature.UNDERGROUND_STRUCTURES as us3
import net.minecraft.world.gen.GenerationStep.Feature.SURFACE_STRUCTURES as ss4
import net.minecraft.world.gen.GenerationStep.Feature.STRONGHOLDS as s5
import net.minecraft.world.gen.GenerationStep.Feature.UNDERGROUND_ORES as uo6
import net.minecraft.world.gen.GenerationStep.Feature.UNDERGROUND_DECORATION as ud7
import net.minecraft.world.gen.GenerationStep.Feature.FLUID_SPRINGS as fs8
import net.minecraft.world.gen.GenerationStep.Feature.VEGETAL_DECORATION as vd9

/*just in case it gets deleted
import net.minecraft.world.gen.GenerationStep.Feature.RAW_GENERATION as rg0
import net.minecraft.world.gen.GenerationStep.Feature.LAKES as l1
import net.minecraft.world.gen.GenerationStep.Feature.LOCAL_MODIFICATIONS as lm2
import net.minecraft.world.gen.GenerationStep.Feature.UNDERGROUND_STRUCTURES as us3
import net.minecraft.world.gen.GenerationStep.Feature.SURFACE_STRUCTURES as ss4
import net.minecraft.world.gen.GenerationStep.Feature.STRONGHOLDS as s5
import net.minecraft.world.gen.GenerationStep.Feature.UNDERGROUND_ORES as uo6
import net.minecraft.world.gen.GenerationStep.Feature.UNDERGROUND_DECORATION as ud7
import net.minecraft.world.gen.GenerationStep.Feature.FLUID_SPRINGS as fs8
import net.minecraft.world.gen.GenerationStep.Feature.VEGETAL_DECORATION as vd9
*/

object BiomeFeatures {

    fun addBasicFeaturesNoDungeon(generationSettings: GenerationSettings.LookupBackedBuilder?) {
        DefaultBiomeFeatures.addLandCarvers(generationSettings)
        DefaultBiomeFeatures.addAmethystGeodes(generationSettings)
        //DefaultBiomeFeatures.addUndergroundVariety(generationSettings)
        DefaultBiomeFeatures.addSprings(generationSettings)
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings)
    }

    fun addOldGrowthSwampFeatures(generationSettings: GenerationSettings.LookupBackedBuilder) {
//        generationSettings.feature(vd9, DuskPlacedFeatures.TREES_OLD_GROWTH_SWAMP)
        generationSettings.feature(vd9, VegetationPlacedFeatures.FLOWER_SWAMP)
        generationSettings.feature(vd9, VegetationPlacedFeatures.PATCH_GRASS_NORMAL)
        generationSettings.feature(vd9, VegetationPlacedFeatures.PATCH_DEAD_BUSH)
        generationSettings.feature(vd9, VegetationPlacedFeatures.PATCH_WATERLILY)
        generationSettings.feature(vd9, VegetationPlacedFeatures.BROWN_MUSHROOM_SWAMP)
        generationSettings.feature(vd9, VegetationPlacedFeatures.RED_MUSHROOM_SWAMP)
    }

    fun addDesertsFeatures(generationSettings: GenerationSettings.LookupBackedBuilder, red: Boolean, cave: Boolean) {
        DefaultBiomeFeatures.addDesertVegetation(generationSettings)
        addSandDungeons(generationSettings, red)
        if (!cave) {
            generationSettings.feature(
                ss4,
                if (red) DuskPlacedFeatures.RED_DESERT_WELL else DuskPlacedFeatures.DESERT_WELL
            )
        } else {
//            generationSettings.feature(vd9, DuskPlacedFeatures.SAND_CAVE_VINES)
            generationSettings.feature(vd9, DuskPlacedFeatures.SAND_CACTUS)
            generationSettings.feature(vd9, DuskPlacedFeatures.CAVE_DEAD_BUSH)
            generationSettings.feature(vd9, DuskPlacedFeatures.ORE_COARSE_DIRT)
            generationSettings.feature(vd9, DuskPlacedFeatures.CAVE_GLOW_LICHEN_EXTRA)
            generationSettings.feature(vd9, DuskPlacedFeatures.SAND_CAVE_CORAL)
            generationSettings.feature(vd9, DuskPlacedFeatures.SAND_CAVE_SEAGRASS)
            generationSettings.feature(vd9, DuskPlacedFeatures.SAND_CAVE_PICKLE)
            if (red) {
                generationSettings.feature(uo6, DuskPlacedFeatures.ORE_RED_SAND)
                generationSettings.feature(lm2, DuskPlacedFeatures.RED_SAND_CAVE_PILLAR)
                generationSettings.feature(us3, DuskPlacedFeatures.CAVE_RED_DESERT_WELL)
                generationSettings.feature(ud7, DuskPlacedFeatures.RED_SAND_SPIKES)
                generationSettings.feature(ud7, DuskPlacedFeatures.RED_SAND_SPIKES_ROOF)
            } else {
                generationSettings.feature(uo6, DuskPlacedFeatures.ORE_SAND)
                generationSettings.feature(lm2, DuskPlacedFeatures.SAND_CAVE_PILLAR)
                generationSettings.feature(us3, DuskPlacedFeatures.CAVE_DESERT_WELL)
                generationSettings.feature(ud7, DuskPlacedFeatures.SAND_SPIKES)
                generationSettings.feature(ud7, DuskPlacedFeatures.SAND_SPIKES_ROOF)
            }
        }
    }

    fun addGravelCaveFeatures(generationSettings: GenerationSettings.LookupBackedBuilder) {
        generationSettings.feature(vd9, DuskPlacedFeatures.CAVE_DEAD_BUSH)
        generationSettings.feature(vd9, DuskPlacedFeatures.ORE_COARSE_DIRT)
        generationSettings.feature(vd9, DuskPlacedFeatures.CAVE_GLOW_LICHEN_EXTRA)
        generationSettings.feature(uo6, DuskPlacedFeatures.ORE_COBBLESTONE)
        generationSettings.feature(lm2, DuskPlacedFeatures.COBBLESTONE_CAVE_PILLAR)
        generationSettings.feature(ud7, DuskPlacedFeatures.COBBLESTONE_SPIKES)
        generationSettings.feature(ud7, DuskPlacedFeatures.COBBLESTONE_SPIKES_ROOF)
        generationSettings.feature(lm2, DuskPlacedFeatures.COBBLED_DEEPSLATE_CAVE_PILLAR)
        generationSettings.feature(ud7, DuskPlacedFeatures.COBBLED_DEEPSLATE_SPIKES)
        generationSettings.feature(ud7, DuskPlacedFeatures.COBBLED_DEEPSLATE_SPIKES_ROOF)
    }

    fun addMushroomGroveFeatures(generationSettings: GenerationSettings.LookupBackedBuilder) {
        generationSettings.feature(vd9, DuskPlacedFeatures.MUSHROOM_GROVE_VEGETATION)
        generationSettings.feature(vd9, VegetationPlacedFeatures.BROWN_MUSHROOM_TAIGA)
        generationSettings.feature(vd9, VegetationPlacedFeatures.RED_MUSHROOM_TAIGA)
    }

    fun addMushroomErodedFeatures(generationSettings: GenerationSettings.LookupBackedBuilder) {
        generationSettings.feature(vd9, OceanPlacedFeatures.SEAGRASS_NORMAL)
        DefaultBiomeFeatures.addKelp(generationSettings)
    }

    fun addMushroomCaveFeatures(generationSettings: GenerationSettings.LookupBackedBuilder) {
        generationSettings.feature(vd9, DuskPlacedFeatures.CAVE_DEAD_BUSH)
        generationSettings.feature(vd9, DuskPlacedFeatures.ORE_COARSE_DIRT)
        generationSettings.feature(vd9, DuskPlacedFeatures.CAVE_GLOW_LICHEN_EXTRA)
        generationSettings.feature(vd9, DuskPlacedFeatures.MUSHROOM_CAVE_MUSHROOMS)
        generationSettings.feature(vd9, DuskPlacedFeatures.MUSHROOM_CAVE_VEGETATION)
        generationSettings.feature(vd9, DuskPlacedFeatures.MUSHROOM_CAVE_SURFACE)
    }

    fun addFrozenCavernsFeatures(generationSettings: GenerationSettings.LookupBackedBuilder) {
        addFrozenDungeons(generationSettings)
        generationSettings.feature(vd9, DuskPlacedFeatures.CAVE_GLOW_LICHEN_EXTRA)
        generationSettings.feature(lm2, DuskPlacedFeatures.ICE_CAVE_PILLAR)
        generationSettings.feature(lm2, DuskPlacedFeatures.ORE_ICE)
        generationSettings.feature(lm2, DuskPlacedFeatures.ORE_BLUE_ICE)
        generationSettings.feature(us3, DuskPlacedFeatures.ICE_SPIKE_FLOOR)
        generationSettings.feature(us3, DuskPlacedFeatures.ICE_SPIKE_CEILING)
//        generationSettings.feature(us3, DuskPlacedFeatures.ICE_CAVE_FOSSIL)
    }

    fun addLushDungeons(builder: GenerationSettings.LookupBackedBuilder) {
        builder.feature(us3, DuskPlacedFeatures.LUSH_MONSTER_ROOM)
        builder.feature(us3, DuskPlacedFeatures.DEEP_LUSH_MONSTER_ROOM)
    }

    fun addFrozenDungeons(builder: GenerationSettings.LookupBackedBuilder) {
        builder.feature(us3, DuskPlacedFeatures.FROZEN_MONSTER_ROOM)
        builder.feature(us3, DuskPlacedFeatures.DEEP_FROZEN_MONSTER_ROOM)
    }

    fun addSandDungeons(builder: GenerationSettings.LookupBackedBuilder, red: Boolean) {
        if (red) {
            builder.feature(us3, DuskPlacedFeatures.RED_SAND_MONSTER_ROOM)
            builder.feature(us3, DuskPlacedFeatures.DEEP_RED_SAND_MONSTER_ROOM)
        } else {
            builder.feature(us3, DuskPlacedFeatures.SAND_MONSTER_ROOM)
            builder.feature(us3, DuskPlacedFeatures.DEEP_SAND_MONSTER_ROOM)
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
    fun addWindsweptValleyFeatures(generationSettings: GenerationSettings.LookupBackedBuilder, variant: String) {
        if (variant == "topaz") {
            generationSettings.feature(uo6, OrePlacedFeatures.ORE_EMERALD)
        } else if (variant == "sapphire") {
            generationSettings.feature(uo6, OrePlacedFeatures.ORE_EMERALD)
        } else if (variant == "ruby") {
            generationSettings.feature(uo6, OrePlacedFeatures.ORE_EMERALD)
        } else {
            generationSettings.feature(uo6, OrePlacedFeatures.ORE_EMERALD)
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
