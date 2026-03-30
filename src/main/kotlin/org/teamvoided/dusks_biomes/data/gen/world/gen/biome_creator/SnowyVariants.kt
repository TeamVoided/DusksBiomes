package org.teamvoided.dusks_biomes.data.gen.world.gen.biome_creator

import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BiomeDefaultFeatures
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.biome.OverworldBiomes
import net.minecraft.data.worldgen.placement.AquaticPlacements
import net.minecraft.data.worldgen.placement.VegetationPlacements
import net.minecraft.sounds.Musics
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.biome.*
import org.teamvoided.dusks_biomes.data.gen.world.gen.BiomeCreator
import org.teamvoided.dusks_biomes.data.gen.world.gen.BiomeFeatures
import org.teamvoided.dusks_biomes.data.world.gen.DuskPlacedFeatures
import net.minecraft.world.level.levelgen.GenerationStep.Decoration.LOCAL_MODIFICATIONS as lm2
import net.minecraft.world.level.levelgen.GenerationStep.Decoration.VEGETAL_DECORATION as vd9

object SnowyVariants {
    fun BootstrapContext<Biome>.createSnowyWindsweptHills(forest: Boolean = false): Biome {
        val features = this.lookup(Registries.PLACED_FEATURE)
        val carver = this.lookup(Registries.CONFIGURED_CARVER)

        val spawns = MobSpawnSettings.Builder()
        val generation = BiomeGenerationSettings.Builder(features, carver)

        BiomeDefaultFeatures.farmAnimals(spawns)
        spawns.addSpawn(MobCategory.CREATURE, 5, MobSpawnSettings.SpawnerData(EntityType.LLAMA, 4, 6))
        BiomeDefaultFeatures.commonSpawns(spawns)

        BiomeCreator.addBasicFeatures(generation)
        BiomeDefaultFeatures.addDefaultOres(generation)
        BiomeDefaultFeatures.addDefaultSoftDisks(generation)
        if (forest) BiomeDefaultFeatures.addMountainForestTrees(generation)
        else BiomeDefaultFeatures.addMountainTrees(generation)

        BiomeDefaultFeatures.addDefaultFlowers(generation)
        BiomeDefaultFeatures.addDefaultGrass(generation)
        BiomeDefaultFeatures.addDefaultMushrooms(generation)
        BiomeDefaultFeatures.addDefaultExtraVegetation(generation, false)
        BiomeDefaultFeatures.addExtraEmeralds(generation)
        BiomeDefaultFeatures.addInfestedStone(generation)

        return BiomeCreator.createBiome(
            true,
            -0.55f,
            0.15f,
            spawns, generation, BiomeCreator.DEFAULT_MUSIC
        )
    }

    fun BootstrapContext<Biome>.createSnowyOldGrowthTaiga(spruce: Boolean): Biome {
        val features = this.lookup(Registries.PLACED_FEATURE)
        val carver = this.lookup(Registries.CONFIGURED_CARVER)

        val spawns = MobSpawnSettings.Builder()
        val generation = BiomeGenerationSettings.Builder(features, carver)

        BiomeDefaultFeatures.farmAnimals(spawns)
        spawns.addSpawn(MobCategory.CREATURE, 8, MobSpawnSettings.SpawnerData(EntityType.WOLF, 4, 4))
        spawns.addSpawn(MobCategory.CREATURE, 4, MobSpawnSettings.SpawnerData(EntityType.RABBIT, 2, 3))
        spawns.addSpawn(MobCategory.CREATURE, 8, MobSpawnSettings.SpawnerData(EntityType.FOX, 2, 4))
        if (spruce) {
            BiomeDefaultFeatures.commonSpawns(spawns)
        } else {
            BiomeDefaultFeatures.caveSpawns(spawns)
            BiomeDefaultFeatures.monsters(spawns, 100, 25, 0, 100, false)
        }

        BiomeCreator.addBasicFeatures(generation)
        generation.addFeature(lm2, DuskPlacedFeatures.COBBLESTONE_ROCK)
        BiomeDefaultFeatures.addFerns(generation)
        BiomeDefaultFeatures.addDefaultOres(generation)
        BiomeDefaultFeatures.addDefaultSoftDisks(generation)
        generation.addFeature(
            vd9,
            if (spruce) VegetationPlacements.TREES_OLD_GROWTH_SPRUCE_TAIGA
            else VegetationPlacements.TREES_OLD_GROWTH_PINE_TAIGA
        )
        BiomeDefaultFeatures.addDefaultFlowers(generation)
        BiomeDefaultFeatures.addGiantTaigaVegetation(generation)
        BiomeDefaultFeatures.addDefaultMushrooms(generation)
        BiomeDefaultFeatures.addDefaultExtraVegetation(generation, false)
        BiomeDefaultFeatures.addRareBerryBushes(generation)
        return BiomeCreator.createBiome(
            true,
            if (spruce) -0.45f else -0.5f,
            0.8f,
            spawns, generation,
            Musics.createGameMusic(SoundEvents.MUSIC_BIOME_OLD_GROWTH_TAIGA)
        )
    }

    fun BootstrapContext<Biome>.createDenseGrove(pale: Boolean = false): Biome {
        val features = this.lookup(Registries.PLACED_FEATURE)
        val carver = this.lookup(Registries.CONFIGURED_CARVER)
        val spawns = MobSpawnSettings.Builder()
        if (!pale) {
            spawns.addSpawn(MobCategory.CREATURE, 1, MobSpawnSettings.SpawnerData(EntityType.WOLF, 1, 1))
                .addSpawn(MobCategory.CREATURE, 8, MobSpawnSettings.SpawnerData(EntityType.RABBIT, 2, 3))
                .addSpawn(MobCategory.CREATURE, 4, MobSpawnSettings.SpawnerData(EntityType.FOX, 2, 4))
        }
        BiomeDefaultFeatures.commonSpawns(spawns, 20)
        spawns.addSpawn(MobCategory.MONSTER, 80, MobSpawnSettings.SpawnerData(EntityType.STRAY, 4, 4))

        val generation = BiomeGenerationSettings.Builder(features, carver)
        BiomeFeatures.addBasicFeaturesNoDungeon(generation)
        BiomeFeatures.addFrozenDungeons(generation)
        if (pale) {
            generation.addFeature(vd9, DuskPlacedFeatures.TREES_SNOWY_PALE_GROVE)
            generation.addFeature(vd9, DuskPlacedFeatures.TREES_SNOWY_PALE_GROVE_ON_SNOW)
            generation.addFeature(vd9, VegetationPlacements.PALE_MOSS_PATCH)
            generation.addFeature(vd9, VegetationPlacements.PALE_GARDEN_FLOWERS)
        } else {
            generation.addFeature(vd9, DuskPlacedFeatures.TREES_SNOWY_DARK_GROVE)
            generation.addFeature(vd9, DuskPlacedFeatures.TREES_SNOWY_DARK_GROVE_ON_SNOW)
            BiomeDefaultFeatures.addForestFlowers(generation)
        }

        BiomeDefaultFeatures.addDefaultOres(generation)
        BiomeDefaultFeatures.addDefaultSoftDisks(generation)
        if (!pale) {
            BiomeDefaultFeatures.addDefaultFlowers(generation)
        } else {
            generation.addFeature(vd9, VegetationPlacements.FLOWER_PALE_GARDEN)
        }
        BiomeDefaultFeatures.addDefaultGrass(generation)
        if (!pale) {
            BiomeDefaultFeatures.addDefaultMushrooms(generation)
            BiomeDefaultFeatures.addLeafLitterPatch(generation)
        }
        BiomeDefaultFeatures.addDefaultExtraVegetation(generation, true)
        BiomeDefaultFeatures.addExtraEmeralds(generation)
        BiomeDefaultFeatures.addInfestedStone(generation)
        return Biome.BiomeBuilder().hasPrecipitation(true).temperature(-0.2f).downfall(0.8f).specialEffects(
            if (pale)
                BiomeSpecialEffects.Builder()
                    .waterColor(7768221)
                    .waterFogColor(5597568)
                    .fogColor(8484720)
                    .skyColor(12171705)
                    .grassColorOverride(7832178)
                    .foliageColorOverride(8883574)
                    .dryFoliageColorOverride(10528412)
                    .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                    .silenceAllBackgroundMusic()
                    .build()
            else
                BiomeSpecialEffects.Builder()
                    .waterColor(BiomeCreator.DEFAULT_WATER_COLOR)
                    .waterFogColor(BiomeCreator.DEFAULT_WATER_FOG_COLOR)
                    .fogColor(BiomeCreator.DEFAULT_FOG_COLOR)
                    .skyColor(OverworldBiomes.calculateSkyColor(-0.2f))
                    .grassColorModifier(BiomeSpecialEffects.GrassColorModifier.DARK_FOREST)
                    .dryFoliageColorOverride(8082228)
                    .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                    .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FOREST))
                    .build()
        ).mobSpawnSettings(spawns.build()).generationSettings(generation.build()).build()
    }

    fun BootstrapContext<Biome>.createSnowyCherryGrove(): Biome {
        val features = this.lookup(Registries.PLACED_FEATURE)
        val carver = this.lookup(Registries.CONFIGURED_CARVER)

        val spawns = MobSpawnSettings.Builder()
        val generation = BiomeGenerationSettings.Builder(features, carver)

        spawns.addSpawn(MobCategory.CREATURE, 1, MobSpawnSettings.SpawnerData(EntityType.PIG, 1, 2))
            .addSpawn(MobCategory.CREATURE, 2, MobSpawnSettings.SpawnerData(EntityType.RABBIT, 2, 6))
            .addSpawn(MobCategory.CREATURE, 2, MobSpawnSettings.SpawnerData(EntityType.SHEEP, 2, 4))
            .addSpawn(MobCategory.CREATURE, 1, MobSpawnSettings.SpawnerData(EntityType.FOX, 2, 4))
            .addSpawn(MobCategory.MONSTER, 80, MobSpawnSettings.SpawnerData(EntityType.STRAY, 4, 4))
        BiomeDefaultFeatures.commonSpawns(spawns, 20)

        BiomeCreator.addBasicFeatures(generation)
        BiomeDefaultFeatures.addDefaultOres(generation)
        BiomeDefaultFeatures.addDefaultSoftDisks(generation)
        generation.addFeature(vd9, VegetationPlacements.PATCH_GRASS_BADLANDS)
        generation.addFeature(vd9, DuskPlacedFeatures.FLOWER_SNOWY_CHERRY)
        generation.addFeature(vd9, DuskPlacedFeatures.TREES_SNOWY_CHERRY_GROVE)
        generation.addFeature(vd9, VegetationPlacements.TREES_CHERRY)
        BiomeDefaultFeatures.addExtraEmeralds(generation)
        BiomeDefaultFeatures.addInfestedStone(generation)
        return BiomeCreator.createBiome(
            true,
            -0.7f,
            0.6f,
            4428999,
            4428999,
            6528354,
            6339166,
            spawns, generation,
            Musics.createGameMusic(SoundEvents.MUSIC_BIOME_CHERRY_GROVE)
        )
    }

    fun BootstrapContext<Biome>.createFrozenBadlands(trees: Boolean = false): Biome {
        val features = this.lookup(Registries.PLACED_FEATURE)
        val carver = this.lookup(Registries.CONFIGURED_CARVER)
        val spawns = MobSpawnSettings.Builder()
        val generation = BiomeGenerationSettings.Builder(features, carver)

        // prob not accurate but idk
        BiomeDefaultFeatures.commonSpawns(spawns, 20)
        spawns.addSpawn(MobCategory.MONSTER, 80, MobSpawnSettings.SpawnerData(EntityType.STRAY, 4, 4))
        spawns.creatureGenerationProbability(0.03f)
        if (trees) {
            spawns.addSpawn(MobCategory.CREATURE, 2, MobSpawnSettings.SpawnerData(EntityType.WOLF, 4, 8))
            spawns.creatureGenerationProbability(0.04f)
        }

        BiomeCreator.addBasicFeatures(generation)
        BiomeDefaultFeatures.addDefaultOres(generation)
        BiomeDefaultFeatures.addExtraGold(generation)
        BiomeDefaultFeatures.addDefaultSoftDisks(generation)
        if (trees) {
            generation.addFeature(vd9, DuskPlacedFeatures.TREES_FROZEN_BADLANDS)
        }
        generation.addFeature(vd9, VegetationPlacements.PATCH_GRASS_BADLANDS)
        generation.addFeature(vd9, VegetationPlacements.PATCH_DEAD_BUSH)
        BiomeDefaultFeatures.addDefaultMushrooms(generation)
        BiomeDefaultFeatures.addBadlandExtraVegetation(generation)
        BiomeDefaultFeatures.addRareBerryBushes(generation)
        val temperature = -0.6f
        return Biome.BiomeBuilder().hasPrecipitation(true).temperature(temperature).downfall(0.5f).specialEffects(
            BiomeSpecialEffects.Builder()
                .waterColor(BiomeCreator.DEFAULT_WATER_COLOR)
                .waterFogColor(BiomeCreator.DEFAULT_WATER_FOG_COLOR)
                .fogColor(BiomeCreator.DEFAULT_FOG_COLOR)
                .skyColor(OverworldBiomes.calculateSkyColor(temperature))
                .foliageColorOverride(10387789)
                .grassColorOverride(9470285)
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_BADLANDS))
                .build()
        ).mobSpawnSettings(spawns.build()).generationSettings(generation.build()).build()
    }

    fun BootstrapContext<Biome>.createFrozenMangroveSwamp(): Biome {
        val features = this.lookup(Registries.PLACED_FEATURE)
        val carver = this.lookup(Registries.CONFIGURED_CARVER)

        val spawns = MobSpawnSettings.Builder()
        val generation = BiomeGenerationSettings.Builder(features, carver)


        BiomeDefaultFeatures.swampSpawns(spawns, 70)
        spawns.addSpawn(MobCategory.MONSTER, 80, MobSpawnSettings.SpawnerData(EntityType.STRAY, 4, 4))

        spawns.addSpawn(MobCategory.MONSTER, 1, MobSpawnSettings.SpawnerData(EntityType.SLIME, 1, 1))
        spawns.addSpawn(MobCategory.CREATURE, 10, MobSpawnSettings.SpawnerData(EntityType.FROG, 2, 5))
        BiomeDefaultFeatures.addFossilDecoration(generation)
        BiomeFeatures.addBasicFeaturesNoDungeon(generation)
        BiomeFeatures.addLushDungeons(generation)
        BiomeDefaultFeatures.addDefaultOres(generation)
        BiomeDefaultFeatures.addMangroveSwampDisks(generation)
        generation.addFeature(vd9, DuskPlacedFeatures.TREES_MANGROVE_FROZEN)
        generation.addFeature(vd9, VegetationPlacements.FLOWER_SWAMP)
        generation.addFeature(vd9, VegetationPlacements.PATCH_GRASS_NORMAL)
        generation.addFeature(vd9, VegetationPlacements.PATCH_DEAD_BUSH)
        generation.addFeature(vd9, VegetationPlacements.PATCH_WATERLILY)
        generation.addFeature(vd9, AquaticPlacements.SEAGRASS_SWAMP)
        val musicSound = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_SWAMP)

        val biomeEffects = BiomeSpecialEffects.Builder()
            .waterColor(8692872)
            .waterFogColor(3815975)
            .skyColor(OverworldBiomes.calculateSkyColor(-0.5f))
            .fogColor(BiomeCreator.DEFAULT_FOG_COLOR)
            .grassColorModifier(BiomeSpecialEffects.GrassColorModifier.SWAMP)
            .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
            .backgroundMusic(musicSound)


        return Biome.BiomeBuilder()
            .hasPrecipitation(true)
            .temperature(-0.5f)
            .downfall(0.9f)
            .temperatureAdjustment(Biome.TemperatureModifier.FROZEN)
            .specialEffects(biomeEffects.build())
            .mobSpawnSettings(spawns.build())
            .generationSettings(generation.build())
            .build()

    }
}