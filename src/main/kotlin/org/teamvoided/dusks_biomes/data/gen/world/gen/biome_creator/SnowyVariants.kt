package org.teamvoided.dusks_biomes.data.gen.world.gen.biome_creator

import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.registry.Registerable
import net.minecraft.registry.RegistryKeys
import net.minecraft.sound.BiomeMoodSound
import net.minecraft.sound.MusicType
import net.minecraft.sound.SoundEvents
import net.minecraft.world.biome.*
import net.minecraft.world.biome.BiomeEffects.GrassColorModifier
import net.minecraft.world.biome.GenerationSettings.LookupBackedBuilder
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.feature.DefaultBiomeFeatures
import net.minecraft.world.gen.feature.OceanPlacedFeatures
import net.minecraft.world.gen.feature.VegetationPlacedFeatures
import org.teamvoided.dusks_biomes.data.gen.world.gen.BiomeCreator
import org.teamvoided.dusks_biomes.data.gen.world.gen.BiomeFeatures
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

object SnowyVariants {
    fun Registerable<Biome>.createSnowyWindsweptHills(forest: Boolean = false): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)

        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.LookupBackedBuilder(features, carver)

        DefaultBiomeFeatures.addFarmAnimals(spawns)
        spawns.spawn(SpawnGroup.CREATURE, 5, SpawnSettings.SpawnEntry(EntityType.LLAMA, 4, 6))
        DefaultBiomeFeatures.addBatsAndMonsters(spawns)

        BiomeCreator.addBasicFeatures(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        if (forest) DefaultBiomeFeatures.addWindsweptForestTrees(generation)
        else DefaultBiomeFeatures.addWindsweptHillsTrees(generation)

        DefaultBiomeFeatures.addDefaultFlowers(generation)
        DefaultBiomeFeatures.addDefaultGrass(generation)
        DefaultBiomeFeatures.addDefaultMushrooms(generation)
        DefaultBiomeFeatures.addDefaultVegetation(generation, false)
        DefaultBiomeFeatures.addEmeraldOre(generation)
        DefaultBiomeFeatures.addInfestedStone(generation)

        return BiomeCreator.createBiome(
            true,
            -0.55f,
            0.15f,
            spawns, generation, BiomeCreator.DEFAULT_MUSIC
        )
    }

    fun Registerable<Biome>.createSnowyOldGrowthTaiga(spruce: Boolean): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)

        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.LookupBackedBuilder(features, carver)

        DefaultBiomeFeatures.addFarmAnimals(spawns)
        spawns.spawn(SpawnGroup.CREATURE, 8, SpawnSettings.SpawnEntry(EntityType.WOLF, 4, 4))
        spawns.spawn(SpawnGroup.CREATURE, 4, SpawnSettings.SpawnEntry(EntityType.RABBIT, 2, 3))
        spawns.spawn(SpawnGroup.CREATURE, 8, SpawnSettings.SpawnEntry(EntityType.FOX, 2, 4))
        if (spruce) {
            DefaultBiomeFeatures.addBatsAndMonsters(spawns)
        } else {
            DefaultBiomeFeatures.addCaveMobs(spawns)
            DefaultBiomeFeatures.addMonsters(spawns, 100, 25, 100, false)
        }

        BiomeCreator.addBasicFeatures(generation)
        generation.feature(lm2, DuskPlacedFeatures.COBBLESTONE_ROCK)
        DefaultBiomeFeatures.addLargeFerns(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        generation.feature(
            vd9,
            if (spruce) VegetationPlacedFeatures.TREES_OLD_GROWTH_SPRUCE_TAIGA
            else VegetationPlacedFeatures.TREES_OLD_GROWTH_PINE_TAIGA
        )
        DefaultBiomeFeatures.addDefaultFlowers(generation)
        DefaultBiomeFeatures.addGiantTaigaGrass(generation)
        DefaultBiomeFeatures.addDefaultMushrooms(generation)
        DefaultBiomeFeatures.addDefaultVegetation(generation, false)
        DefaultBiomeFeatures.addSweetBerryBushesSnowy(generation)
        return BiomeCreator.createBiome(
            true,
            if (spruce) -0.45f else -0.5f,
            0.8f,
            spawns, generation,
            MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_OLD_GROWTH_TAIGA)
        )
    }

    fun Registerable<Biome>.createDenseGrove(pale: Boolean = false): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
        val spawns = SpawnSettings.Builder()
        if (!pale) {
            spawns.spawn(SpawnGroup.CREATURE, 1, SpawnSettings.SpawnEntry(EntityType.WOLF, 1, 1))
                .spawn(SpawnGroup.CREATURE, 8, SpawnSettings.SpawnEntry(EntityType.RABBIT, 2, 3))
                .spawn(SpawnGroup.CREATURE, 4, SpawnSettings.SpawnEntry(EntityType.FOX, 2, 4))
        }
        DefaultBiomeFeatures.addBatsAndMonsters(spawns)
        DefaultBiomeFeatures.addCaveMobs(spawns)
        DefaultBiomeFeatures.addMonsters(spawns, 95, 5, 20, false)
        spawns.spawn(SpawnGroup.MONSTER, 80, SpawnSettings.SpawnEntry(EntityType.STRAY, 4, 4))

        val generation = LookupBackedBuilder(features, carver)
        BiomeFeatures.addBasicFeaturesNoDungeon(generation)
        BiomeFeatures.addFrozenDungeons(generation)
        if (pale) generation.feature(vd9, VegetationPlacedFeatures.PALE_GARDEN_VEGETATION) else {
            generation.feature(vd9, DuskPlacedFeatures.TREES_SNOWY_DARK_GROVE)
            generation.feature(vd9, DuskPlacedFeatures.TREES_SNOWY_DARK_GROVE_ON_SNOW)
        }
        if (!pale) {
            DefaultBiomeFeatures.addForestFlowers(generation)
        } else {
            generation.feature(vd9, VegetationPlacedFeatures.PALE_MOSS_PATCH)
            generation.feature(vd9, VegetationPlacedFeatures.PALE_GARDEN_FLOWERS)
        }

        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        if (!pale) {
            DefaultBiomeFeatures.addDefaultFlowers(generation)
        } else {
            generation.feature(vd9, VegetationPlacedFeatures.FLOWER_PALE_GARDEN)
        }
        DefaultBiomeFeatures.addDefaultGrass(generation)
        if (!pale) {
            DefaultBiomeFeatures.addDefaultMushrooms(generation)
            DefaultBiomeFeatures.addLeafLitter(generation)
        }
        DefaultBiomeFeatures.addDefaultVegetation(generation, true)
        DefaultBiomeFeatures.addEmeraldOre(generation)
        DefaultBiomeFeatures.addInfestedStone(generation)
        return Biome.Builder().precipitation(true).temperature(-0.2f).downfall(0.8f).effects(
            if (pale)
                BiomeEffects.Builder()
                    .waterColor(7768221).waterFogColor(5597568).fogColor(8484720).skyColor(12171705).grassColor(7832178)
                    .foliageColor(8883574).dryFoliageColor(10528412).moodSound(BiomeMoodSound.CAVE).noMusic()
                    .build()
            else
                BiomeEffects.Builder()
                    .waterFogColor(BiomeCreator.DEFAULT_WATER_FOG_COLOR)
                    .fogColor(BiomeCreator.DEFAULT_FOG_COLOR)
                    .skyColor(OverworldBiomeCreator.getSkyColor(-0.2f))
                    .grassColorModifier(GrassColorModifier.DARK_FOREST).dryFoliageColor(8082228)
                    .moodSound(BiomeMoodSound.CAVE)
                    .music(MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_FOREST)).build()
        ).spawnSettings(spawns.build()).generationSettings(generation.build()).build()
    }

    fun Registerable<Biome>.createSnowyCherryGrove(): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)

        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.LookupBackedBuilder(features, carver)

        spawns.spawn(SpawnGroup.CREATURE, 1, SpawnSettings.SpawnEntry(EntityType.PIG, 1, 2))
            .spawn(SpawnGroup.CREATURE, 2, SpawnSettings.SpawnEntry(EntityType.RABBIT, 2, 6))
            .spawn(SpawnGroup.CREATURE, 2, SpawnSettings.SpawnEntry(EntityType.SHEEP, 2, 4))
            .spawn(SpawnGroup.CREATURE, 1, SpawnSettings.SpawnEntry(EntityType.FOX, 2, 4))
            .spawn(SpawnGroup.MONSTER, 80, SpawnSettings.SpawnEntry(EntityType.STRAY, 4, 4))
        DefaultBiomeFeatures.addCaveMobs(spawns)
        DefaultBiomeFeatures.addMonsters(spawns, 95, 5, 20, false)

        BiomeCreator.addBasicFeatures(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        generation.feature(vd9, VegetationPlacedFeatures.PATCH_GRASS_BADLANDS)
        generation.feature(vd9, DuskPlacedFeatures.FLOWER_SNOWY_CHERRY)
        generation.feature(vd9, DuskPlacedFeatures.TREES_SNOWY_CHERRY_GROVE)
        generation.feature(vd9, VegetationPlacedFeatures.TREES_CHERRY)
        DefaultBiomeFeatures.addEmeraldOre(generation)
        DefaultBiomeFeatures.addInfestedStone(generation)
        return BiomeCreator.createBiome(
            true,
            -0.7f,
            0.6f,
            4428999,
            4428999,
            6528354,
            6339166,
            spawns, generation,
            MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_CHERRY_GROVE)
        )
    }

    fun Registerable<Biome>.createFrozenBadlands(trees: Boolean = false): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.LookupBackedBuilder(features, carver)

        DefaultBiomeFeatures.addCaveMobs(spawns)
        DefaultBiomeFeatures.addMonsters(spawns, 95, 5, 20, false)
        spawns.spawn(SpawnGroup.MONSTER, 80, SpawnSettings.SpawnEntry(EntityType.STRAY, 4, 4))
        spawns.creatureSpawnProbability(0.03f)
        if (trees) {
            spawns.spawn(SpawnGroup.CREATURE, 2, SpawnSettings.SpawnEntry(EntityType.WOLF, 4, 8))
            spawns.creatureSpawnProbability(0.04f)
        }

        BiomeCreator.addBasicFeatures(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addExtraGoldOre(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        if (trees) {
            generation.feature(vd9, DuskPlacedFeatures.TREES_FROZEN_BADLANDS)
        }
        generation.feature(vd9, VegetationPlacedFeatures.PATCH_GRASS_BADLANDS)
        generation.feature(vd9, VegetationPlacedFeatures.PATCH_DEAD_BUSH)
        DefaultBiomeFeatures.addDefaultMushrooms(generation)
        DefaultBiomeFeatures.addBadlandsVegetation(generation)
        DefaultBiomeFeatures.addSweetBerryBushesSnowy(generation)
        val temperature = -0.6f
        return Biome.Builder().precipitation(true).temperature(temperature).downfall(0.5f).effects(
            BiomeEffects.Builder()
                .waterColor(BiomeCreator.DEFAULT_WATER_COLOR)
                .waterFogColor(BiomeCreator.DEFAULT_WATER_FOG_COLOR)
                .fogColor(BiomeCreator.DEFAULT_FOG_COLOR)
                .skyColor(OverworldBiomeCreator.getSkyColor(temperature))
                .foliageColor(10387789)
                .grassColor(9470285)
                .moodSound(BiomeMoodSound.CAVE)
                .music(MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_BADLANDS))
                .build()
        ).spawnSettings(spawns.build()).generationSettings(generation.build()).build()
    }

    fun Registerable<Biome>.createFrozenMangroveSwamp(): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)

        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.LookupBackedBuilder(features, carver)


        DefaultBiomeFeatures.addCaveMobs(spawns)
        DefaultBiomeFeatures.addMonsters(spawns, 95, 5, 20, false)
        spawns.spawn(SpawnGroup.MONSTER, 80, SpawnSettings.SpawnEntry(EntityType.STRAY, 4, 4))

        spawns.spawn(SpawnGroup.MONSTER, 1, SpawnSettings.SpawnEntry(EntityType.SLIME, 1, 1))
        spawns.spawn(SpawnGroup.CREATURE, 10, SpawnSettings.SpawnEntry(EntityType.FROG, 2, 5))
        DefaultBiomeFeatures.addFossils(generation)
        BiomeFeatures.addBasicFeaturesNoDungeon(generation)
        BiomeFeatures.addLushDungeons(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addGrassAndClayDisks(generation)
        generation.feature(vd9, DuskPlacedFeatures.TREES_MANGROVE_FROZEN)
        generation.feature(vd9, VegetationPlacedFeatures.FLOWER_SWAMP)
        generation.feature(vd9, VegetationPlacedFeatures.PATCH_GRASS_NORMAL)
        generation.feature(vd9, VegetationPlacedFeatures.PATCH_DEAD_BUSH)
        generation.feature(vd9, VegetationPlacedFeatures.PATCH_WATERLILY)
        generation.feature(vd9, OceanPlacedFeatures.SEAGRASS_SWAMP)
        val musicSound = MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_SWAMP)

        val biomeEffects = BiomeEffects.Builder()
            .waterColor(8692872)
            .waterFogColor(3815975)
            .skyColor(OverworldBiomeCreator.getSkyColor(-0.5f))
            .fogColor(BiomeCreator.DEFAULT_FOG_COLOR)
            .grassColorModifier(BiomeEffects.GrassColorModifier.SWAMP)
            .moodSound(BiomeMoodSound.CAVE)
            .music(musicSound)


        return Biome.Builder()
            .precipitation(true)
            .temperature(-0.5f)
            .downfall(0.9f)
            .temperatureModifier(Biome.TemperatureModifier.FROZEN)
            .effects(biomeEffects.build())
            .spawnSettings(spawns.build())
            .generationSettings(generation.build())
            .build()

    }
}