package org.teamvoided.dusk_autumns_worldgen.datagen.worldgen

import net.minecraft.world.biome.GenerationSettings
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.feature.DefaultBiomeFeatures
import net.minecraft.world.gen.feature.UndergroundPlacedFeatures
import net.minecraft.world.gen.feature.VegetationPlacedFeatures
import org.teamvoided.dusk_autumns_worldgen.init.worldgen.DuskPlacedFeatures

object BiomeFeatures {
    fun BiomeFeatures() {
        //Why is this here?
    }

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
            if (red) {
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

    fun addFrozenCavernsFeatures(generationSettings: GenerationSettings.Builder) {
        addFrozenDungeons(generationSettings)
        generationSettings.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, DuskPlacedFeatures.ORE_ICE)
        generationSettings.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, DuskPlacedFeatures.ORE_BLUE_ICE)
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, DuskPlacedFeatures.ICE_SPIKE_FLOOR)
        generationSettings.feature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, DuskPlacedFeatures.ICE_SPIKE_CEILING)
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
        if (!red) {
            builder.feature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, DuskPlacedFeatures.SAND_MONSTER_ROOM)
            builder.feature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, DuskPlacedFeatures.DEEP_SAND_MONSTER_ROOM)
        } else {
            builder.feature(GenerationStep.Feature.UNDERGROUND_STRUCTURES, DuskPlacedFeatures.RED_SAND_MONSTER_ROOM)
            builder.feature(
                GenerationStep.Feature.UNDERGROUND_STRUCTURES,
                DuskPlacedFeatures.DEEP_RED_SAND_MONSTER_ROOM
            )
        }
    }
}