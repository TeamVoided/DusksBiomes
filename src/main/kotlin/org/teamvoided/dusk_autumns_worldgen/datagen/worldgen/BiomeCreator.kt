package org.teamvoided.dusk_autumns_worldgen.datagen.worldgen

import net.minecraft.client.sound.MusicType
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.registry.RegistryKeys
import net.minecraft.sound.BiomeMoodSound
import net.minecraft.sound.MusicSound
import net.minecraft.sound.SoundEvents
import net.minecraft.world.biome.*
import net.minecraft.world.biome.BiomeEffects.GrassColorModifier
import net.minecraft.world.biome.SpawnSettings.SpawnEntry
import net.minecraft.world.gen.BootstrapContext
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.feature.DefaultBiomeFeatures
import net.minecraft.world.gen.feature.OceanPlacedFeatures
import net.minecraft.world.gen.feature.VegetationPlacedFeatures
import org.teamvoided.dusk_autumns_worldgen.init.worldgen.DuskBiomes
import org.teamvoided.dusk_autumns_worldgen.init.worldgen.DuskPlacedFeatures

@Suppress("MemberVisibilityCanBePrivate")
object BiomeCreator {
    private val DEFAULT_MUSIC: MusicSound? = null

    @Suppress("BooleanLiteralArgument")
    fun boostrap(c: BootstrapContext<Biome>) {
        c.register(DuskBiomes.COLD_FOREST, c.createForest(true, false))
        c.register(DuskBiomes.COLD_PLAINS, c.createPlains(true, false))
        c.register(DuskBiomes.WARM_FOREST, c.createForest(false, true))
        c.register(DuskBiomes.WARM_PLAINS, c.createPlains(false, true))
        c.register(DuskBiomes.WINDSWEPT_BIRCH_FOREST, c.createWindsweptBirchForest())
        c.register(DuskBiomes.SNOWY_CHERRY_GROVE, c.createSnowyCherryGrove())
        c.register(DuskBiomes.SNOWY_WINDSWEPT_HILLS, c.createWindsweptHills(false))
        c.register(DuskBiomes.SNOWY_WINDSWEPT_GRAVELLY_HILLS, c.createWindsweptHills(false))
        c.register(DuskBiomes.SNOWY_WINDSWEPT_FOREST, c.createWindsweptHills(true))
        c.register(DuskBiomes.SNOWY_OLD_GROWTH_PINE_TAIGA, c.createSnowyOldGrowthTaiga(false))
        c.register(DuskBiomes.SNOWY_OLD_GROWTH_SPRUCE_TAIGA, c.createSnowyOldGrowthTaiga(true))
        c.register(DuskBiomes.FROZEN_MANGROVE_SWAMP, c.createMangroveSwamp(false, true))
        c.register(DuskBiomes.FROZEN_WINDSWEPT_MANGROVE_SWAMP, c.createMangroveSwamp(true, true))
        c.register(DuskBiomes.WINDSWEPT_MANGROVE_SWAMP, c.createMangroveSwamp(true, false))
        c.register(DuskBiomes.OLD_GROWTH_SWAMP, c.createSwamp(true))
        c.register(DuskBiomes.WARM_RIVER, c.createWarmRiver(false))
        c.register(DuskBiomes.RED_WARM_RIVER, c.createWarmRiver(true))
        c.register(DuskBiomes.RED_WARM_OCEAN, c.createWarmOcean())
        c.register(DuskBiomes.RED_LUKEWARM_OCEAN, c.createLukewarmOcean(false))
        c.register(DuskBiomes.DEEP_RED_LUKEWARM_OCEAN, c.createLukewarmOcean(true))
        c.register(DuskBiomes.RED_BEACH, c.createBeach(false, false))
        c.register(DuskBiomes.SNOWY_RED_BEACH, c.createBeach(true, false))
        c.register(DuskBiomes.SNOWY_STONY_SHORE, c.createBeach(true, true))
        c.register(DuskBiomes.RED_DESERT, c.createDesert(true, false))
        c.register(DuskBiomes.MUSHROOM_GROVE, c.createMushroomIsland(true))
        c.register(DuskBiomes.ERODED_MUSHROOM_ISLAND, c.createMushroomIsland(true))
        c.register(DuskBiomes.MUSHROOM_CAVES, c.createMushroomCave())
        c.register(DuskBiomes.FROZEN_CAVERNS, c.createFrozenCaves())
        c.register(DuskBiomes.SAND_CAVES, c.createDesert(false, true))
        c.register(DuskBiomes.RED_SAND_CAVES, c.createDesert(true, true))

    }


    fun BootstrapContext<Biome>.createSnowyOldGrowthTaiga(spruce: Boolean): Biome {
        val features = this.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.lookup(RegistryKeys.CONFIGURED_CARVER)

        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.Builder(features, carver)

        DefaultBiomeFeatures.addFarmAnimals(spawns)
        spawns.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.WOLF, 8, 4, 4))
        spawns.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.RABBIT, 4, 2, 3))
        spawns.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.FOX, 8, 2, 4))
        if (spruce) {
            DefaultBiomeFeatures.addBatsAndMonsters(spawns)
        } else {
            DefaultBiomeFeatures.addCaveMobs(spawns)
            DefaultBiomeFeatures.addMonsters(spawns, 100, 25, 100, false)
        }

        OverworldBiomeCreator.addBasicFeatures(generation)
        generation.feature(
            GenerationStep.Feature.LOCAL_MODIFICATIONS,
            DuskPlacedFeatures.COBBLESTONE_ROCK
        )
        DefaultBiomeFeatures.addLargeFerns(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        generation.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            if (spruce) VegetationPlacedFeatures.TREES_OLD_GROWTH_SPRUCE_TAIGA
            else VegetationPlacedFeatures.TREES_OLD_GROWTH_PINE_TAIGA
        )
        DefaultBiomeFeatures.addDefaultFlowers(generation)
        DefaultBiomeFeatures.addGiantTaigaGrass(generation)
        DefaultBiomeFeatures.addDefaultMushrooms(generation)
        DefaultBiomeFeatures.addDefaultVegetation(generation)
        DefaultBiomeFeatures.addCommonBerries(generation)
        return OverworldBiomeCreator.create(
            true,
            if (spruce) -0.45f else 0.5f,
            0.8f,
            spawns, generation,
            MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_OLD_GROWTH_TAIGA)
        )
    }

    fun BootstrapContext<Biome>.createPlains(cold: Boolean, warm: Boolean): Biome {
        val features = this.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.lookup(RegistryKeys.CONFIGURED_CARVER)

        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.Builder(features, carver)

        OverworldBiomeCreator.addBasicFeatures(generation)
        DefaultBiomeFeatures.addPlainsMobs(spawns)
        if (warm) DefaultBiomeFeatures.addLargeFerns(generation)
        else DefaultBiomeFeatures.addPlainsTallGrass(generation)

        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        generation.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            if (cold) DuskPlacedFeatures.TREES_COLD_PLAINS
            else DuskPlacedFeatures.TREES_WARM_PLAINS
        )
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.FLOWER_PLAINS)
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_PLAIN)
        DefaultBiomeFeatures.addDefaultMushrooms(generation)
        DefaultBiomeFeatures.addDefaultVegetation(generation)
        if (warm) DefaultBiomeFeatures.addSparseMelons(generation)

        return OverworldBiomeCreator.create(
            true,
            if (cold) 0.6f else 1.45f,
            if (cold) 0.8f else 0.2f,
            spawns, generation, DEFAULT_MUSIC
        )
    }

    fun BootstrapContext<Biome>.createForest(cold: Boolean, warm: Boolean): Biome {
        val features = this.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.lookup(RegistryKeys.CONFIGURED_CARVER)

        val spawns = GenerationSettings.Builder(features, carver)
        val generation = SpawnSettings.Builder()

        DefaultBiomeFeatures.addFarmAnimals(generation)
        DefaultBiomeFeatures.addBatsAndMonsters(generation)
        if (cold) {
            generation.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.RABBIT, 2, 2, 3))
            generation.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.FOX, 6, 2, 4))
        }
        OverworldBiomeCreator.addBasicFeatures(spawns)
        if (cold) DefaultBiomeFeatures.addLargeFerns(spawns)

        DefaultBiomeFeatures.addForestFlowers(spawns)
        DefaultBiomeFeatures.addDefaultOres(spawns)
        DefaultBiomeFeatures.addDefaultDisks(spawns)
        if (cold) spawns.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeatures.TREES_COLD_FOREST)
        else if (warm) spawns.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeatures.TREES_WARM_FOREST)
        else DefaultBiomeFeatures.addForestTrees(spawns)

        DefaultBiomeFeatures.addDefaultFlowers(spawns)
        DefaultBiomeFeatures.addDefaultGrass(spawns)
        DefaultBiomeFeatures.addForestGrass(spawns)
        DefaultBiomeFeatures.addDefaultMushrooms(spawns)
        DefaultBiomeFeatures.addDefaultVegetation(spawns)

        return OverworldBiomeCreator.create(
            true,
            if (cold) 0.4f else 1.4f,
            if (cold) 0.8f else 0.3f,
            generation, spawns,
            MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_FOREST)
        )
    }

    fun BootstrapContext<Biome>.createWindsweptBirchForest(): Biome {
        val features = this.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.lookup(RegistryKeys.CONFIGURED_CARVER)

        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.Builder(features, carver)

        OverworldBiomeCreator.addBasicFeatures(generation)
        DefaultBiomeFeatures.addForestFlowers(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeatures.TREES_WINDSWEPT_BIRCH)
        DefaultBiomeFeatures.addDefaultFlowers(generation)
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_NORMAL)
        DefaultBiomeFeatures.addDefaultMushrooms(generation)
        DefaultBiomeFeatures.addDefaultVegetation(generation)
        DefaultBiomeFeatures.addFarmAnimals(spawns)
        DefaultBiomeFeatures.addBatsAndMonsters(spawns)
        return OverworldBiomeCreator.create(
            true,
            0.6f,
            0.6f,
            spawns, generation,
            MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_FOREST)
        )
    }

    fun BootstrapContext<Biome>.createSnowyCherryGrove(): Biome {
        val features = this.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.lookup(RegistryKeys.CONFIGURED_CARVER)

        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.Builder(features, carver)

        spawns.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.PIG, 1, 1, 2))
            .spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.RABBIT, 2, 2, 6))
            .spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.SHEEP, 2, 2, 4))
            .spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.FOX, 1, 2, 4))
            .spawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.STRAY, 80, 4, 4))
        DefaultBiomeFeatures.addCaveMobs(spawns)
        DefaultBiomeFeatures.addMonsters(spawns, 95, 5, 20, false)

        OverworldBiomeCreator.addBasicFeatures(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_BADLANDS)
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeatures.FLOWER_SNOWY_CHERRY)
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeatures.TREES_SNOWY_CHERRY_GROVE)
        DefaultBiomeFeatures.addEmeraldOre(generation)
        DefaultBiomeFeatures.addInfestedStone(generation)
        return OverworldBiomeCreator.create(
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

    fun BootstrapContext<Biome>.createWindsweptHills(forest: Boolean): Biome {
        val features = this.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.lookup(RegistryKeys.CONFIGURED_CARVER)

        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.Builder(features, carver)

        DefaultBiomeFeatures.addFarmAnimals(spawns)
        spawns.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.LLAMA, 5, 4, 6))
        DefaultBiomeFeatures.addBatsAndMonsters(spawns)

        OverworldBiomeCreator.addBasicFeatures(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        if (forest) DefaultBiomeFeatures.addWindsweptForestTrees(generation)
        else DefaultBiomeFeatures.addMountainTrees(generation)

        DefaultBiomeFeatures.addDefaultFlowers(generation)
        DefaultBiomeFeatures.addDefaultGrass(generation)
        DefaultBiomeFeatures.addDefaultMushrooms(generation)
        DefaultBiomeFeatures.addDefaultVegetation(generation)
        DefaultBiomeFeatures.addEmeraldOre(generation)
        DefaultBiomeFeatures.addInfestedStone(generation)

        return OverworldBiomeCreator.create(
            true,
            -0.55f,
            0.15f,
            spawns, generation, DEFAULT_MUSIC
        )
    }

    fun BootstrapContext<Biome>.createMangroveSwamp(windswept: Boolean, frozen: Boolean): Biome {
        val features = this.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.lookup(RegistryKeys.CONFIGURED_CARVER)

        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.Builder(features, carver)

        if (frozen) {
            DefaultBiomeFeatures.addCaveMobs(spawns)
            DefaultBiomeFeatures.addMonsters(spawns, 95, 5, 20, false)
            spawns.spawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.STRAY, 80, 4, 4))
        } else DefaultBiomeFeatures.addBatsAndMonsters(spawns)

        spawns.spawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.SLIME, 1, 1, 1))
        spawns.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.FROG, 10, 2, 5))
        DefaultBiomeFeatures.addFossils(generation)
        BiomeFeatures.addBasicFeaturesNoDungeon(generation)
        BiomeFeatures.addLushDungeons(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addGrassAndClayDisks(generation)
        if (frozen) {
            if (windswept) {
                generation.feature(
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    DuskPlacedFeatures.TREES_WINDSWEPT_MANGROVE_FROZEN
                )
            } else {
                generation.feature(
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    DuskPlacedFeatures.TREES_MANGROVE_FROZEN
                )
                generation.feature(
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    VegetationPlacedFeatures.FLOWER_SWAMP
                )
            }
        } else {
            if (windswept) generation.feature(
                GenerationStep.Feature.VEGETAL_DECORATION,
                DuskPlacedFeatures.TREES_WINDSWEPT_MANGROVE
            )
            else DefaultBiomeFeatures.addMangroveSwampFeatures(generation)

        }
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_NORMAL)
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_DEAD_BUSH)
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_WATERLILY)
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.SEAGRASS_SWAMP)
        val musicSound = MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_SWAMP)

        val biomeEffects = BiomeEffects.Builder()
            .waterColor(8692872)
            .waterFogColor(3815975)
            .skyColor(OverworldBiomeCreator.getSkyColor(-0.5f))
            .fogColor(12638463)
            .grassColorModifier(GrassColorModifier.SWAMP)
            .moodSound(BiomeMoodSound.CAVE)
            .music(musicSound)


        return if (frozen) {
            Biome.Builder()
                .hasPrecipitation(true)
                .temperature(-0.5f)
                .downfall(0.9f)
                .temperatureModifier(Biome.TemperatureModifier.FROZEN)
                .effects(biomeEffects.build())
                .spawnSettings(spawns.build())
                .generationSettings(generation.build())
                .build()
        } else {
            Biome.Builder()
                .hasPrecipitation(true)
                .temperature(0.8f)
                .downfall(0.9f)
                .effects(
                    biomeEffects
                        .foliageColor(9285927)
                        .waterColor(3832426)
                        .waterFogColor(5077600)
                        .skyColor(OverworldBiomeCreator.getSkyColor(0.8f))
                        .build()
                )
                .spawnSettings(spawns.build())
                .generationSettings(generation.build())
                .build()
        }
    }

    fun BootstrapContext<Biome>.createSwamp(oldGrowth: Boolean): Biome {
        val features = this.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.lookup(RegistryKeys.CONFIGURED_CARVER)

        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.Builder(features, carver)

        DefaultBiomeFeatures.addFarmAnimals(spawns)
        DefaultBiomeFeatures.addBatsAndMonsters(spawns)
        spawns.spawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.SLIME, 1, 1, 1))
        spawns.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.FROG, 10, 2, 5))
        DefaultBiomeFeatures.addFossils(generation)
        OverworldBiomeCreator.addBasicFeatures(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addClayDisk(generation)
        if (oldGrowth) BiomeFeatures.addOldGrowthSwampFeatures(generation)
        else DefaultBiomeFeatures.addSwampFeatures(generation)

        DefaultBiomeFeatures.addDefaultMushrooms(generation)
        DefaultBiomeFeatures.addSwampVegetation(generation)
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.SEAGRASS_SWAMP)

        return Biome.Builder()
            .hasPrecipitation(true)
            .temperature(0.8f)
            .downfall(0.9f)
            .effects(
                BiomeEffects.Builder()
                    .waterColor(6388580)
                    .waterFogColor(2302743)
                    .fogColor(12638463)
                    .skyColor(OverworldBiomeCreator.getSkyColor(0.8f))
                    .foliageColor(6975545)
                    .grassColorModifier(GrassColorModifier.SWAMP)
                    .moodSound(BiomeMoodSound.CAVE)
                    .music(MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_SWAMP))
                    .build()
            )
            .spawnSettings(spawns.build())
            .generationSettings(generation.build())
            .build()
    }

    fun BootstrapContext<Biome>.createDesert(red: Boolean, cave: Boolean): Biome {
        val features = this.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.lookup(RegistryKeys.CONFIGURED_CARVER)

        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.Builder(features, carver)
        DefaultBiomeFeatures.addDesertMobs(spawns)
        DefaultBiomeFeatures.addFossils(generation)
        BiomeFeatures.addBasicFeaturesNoDungeon(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        DefaultBiomeFeatures.addDefaultFlowers(generation)
        DefaultBiomeFeatures.addDefaultGrass(generation)
        DefaultBiomeFeatures.addDesertDeadBushes(generation)
        DefaultBiomeFeatures.addDefaultMushrooms(generation)
        BiomeFeatures.addDesertsFeatures(generation, red, cave)
        if (cave) {
            generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.WARM_OCEAN_VEGETATION)
            generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.SEAGRASS_WARM)
            generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.SEA_PICKLE)
        }

        return OverworldBiomeCreator.create(
            false,
            2.0f,
            0.0f,
            4445678,
            270131,
            null,
            null,
            spawns, generation,
            MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_DESERT)
        )
    }

    fun BootstrapContext<Biome>.createWarmRiver(red: Boolean): Biome {
        val feature = this.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.lookup(RegistryKeys.CONFIGURED_CARVER)

        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.Builder(feature, carver)


        spawns.spawn(SpawnGroup.WATER_AMBIENT, SpawnEntry(EntityType.PUFFERFISH, 15, 1, 3))
        DefaultBiomeFeatures.addWarmOceanMobs(spawns, 10, 4)
        DefaultBiomeFeatures.addBatsAndMonsters(spawns)
        spawns.spawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.DROWNED, 100, 1, 1))

        OverworldBiomeCreator.addBasicFeatures(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        DefaultBiomeFeatures.addDefaultFlowers(generation)
        DefaultBiomeFeatures.addDefaultGrass(generation)
        DefaultBiomeFeatures.addDesertDeadBushes(generation)
        DefaultBiomeFeatures.addDefaultMushrooms(generation)
        BiomeFeatures.addDesertsFeatures(generation, red, false)
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.SEAGRASS_RIVER)

        return OverworldBiomeCreator.create(
            false,
            1.5f,
            0.25f,
            4445678,
            270131,
            null,
            null,
            spawns, generation, DEFAULT_MUSIC
        )
    }

    fun BootstrapContext<Biome>.createWarmOcean(): Biome {
        val features = this.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.lookup(RegistryKeys.CONFIGURED_CARVER)

        return OverworldBiomeCreator.createWarmOcean(features, carver)
    }

    fun BootstrapContext<Biome>.createLukewarmOcean(deep: Boolean): Biome {
        val features = this.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.lookup(RegistryKeys.CONFIGURED_CARVER)

        return OverworldBiomeCreator.createLukewarmOcean(features, carver, deep)
    }

    fun BootstrapContext<Biome>.createBeach(snowy: Boolean, stony: Boolean): Biome {
        val features = this.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.lookup(RegistryKeys.CONFIGURED_CARVER)

        return OverworldBiomeCreator.createBeach(features, carver, snowy, stony)
    }

    fun BootstrapContext<Biome>.createMushroomIsland(grove: Boolean): Biome {
        val features = this.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.lookup(RegistryKeys.CONFIGURED_CARVER)

        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.Builder(features, carver)

        DefaultBiomeFeatures.addMushroomMobs(spawns)

        OverworldBiomeCreator.addBasicFeatures(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        if (grove) BiomeFeatures.addMushroomGroveFeatures(generation)
        else DefaultBiomeFeatures.addMushroomFieldsFeatures(generation)
        DefaultBiomeFeatures.addDefaultVegetation(generation)

        return OverworldBiomeCreator.create(
            true,
            0.9f,
            1.0f,
            spawns, generation, DEFAULT_MUSIC
        )
    }

    fun BootstrapContext<Biome>.createMushroomCave(): Biome {
        val features = this.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.lookup(RegistryKeys.CONFIGURED_CARVER)

        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.Builder(features, carver)

        DefaultBiomeFeatures.addMushroomMobs(spawns)

        OverworldBiomeCreator.addBasicFeatures(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        BiomeFeatures.addMushroomGroveFeatures(generation)
        DefaultBiomeFeatures.addDefaultVegetation(generation)

        return OverworldBiomeCreator.create(
            true,
            0.9f,
            1.0f,
            spawns, generation, DEFAULT_MUSIC
        )
    }

    fun BootstrapContext<Biome>.createFrozenCaves(): Biome {
        val features = this.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.lookup(RegistryKeys.CONFIGURED_CARVER)

        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.Builder(features, carver)

        DefaultBiomeFeatures.addCaveMobs(spawns)
        DefaultBiomeFeatures.addMonsters(spawns, 95, 5, 20, false)
        spawns.spawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.STRAY, 80, 4, 4))

        DefaultBiomeFeatures.addFossils(generation)
        OverworldBiomeCreator.addBasicFeatures(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        DefaultBiomeFeatures.addSnowySpruceTrees(generation)
        DefaultBiomeFeatures.addDefaultFlowers(generation)
        DefaultBiomeFeatures.addDefaultGrass(generation)
        DefaultBiomeFeatures.addDefaultMushrooms(generation)
        DefaultBiomeFeatures.addDefaultVegetation(generation)
        BiomeFeatures.addFrozenCavernsFeatures(generation)

        return OverworldBiomeCreator.create(
            true,
            0f,
            0.4f,
            spawns, generation,
            MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_FROZEN_PEAKS)
        )
    }


    /*    fun BootstrapContext<Biome>.createDevilsRoar(): Biome {
            val features = this.lookup(RegistryKeys.PLACED_FEATURE)
            val carver = this.lookup(RegistryKeys.CONFIGURED_CARVER)

            val spawns = SpawnSettings.Builder()
            val generation = GenerationSettings.Builder(features, carver)

            DefaultBiomeFeatures.addCaveMobs(spawns)
            DefaultBiomeFeatures.addMonsters(spawns, 95, 5, 100, true)
            DefaultBiomeFeatures.addFossils(generation)
            OverworldBiomeCreator.addBasicFeatures(generation)
            DefaultBiomeFeatures.addDefaultOres(generation)
            DefaultBiomeFeatures.addDefaultDisks(generation)
            val musicSound = MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_DRIPSTONE_CAVES)
            return Biome.Builder()
                .hasPrecipitation(true)
                .temperature(0.5f)
                .downfall(0.5f)
                .effects(
                    BiomeEffects.Builder()
                        .skyColor(3750977).fogColor(4605784)
                        .waterColor(4676715).waterFogColor(66051)
                        .grassColor(6574650).foliageColor(7877401)
                        .moodSound(BiomeMoodSound.CAVE).music(musicSound)
                        .particleConfig(BiomeParticleConfig(ParticleTypes.ASH, 0.0025f))
                        .loopSound(SoundEvents.AMBIENT_BASALT_DELTAS_LOOP)
                        .build()
                )
                .spawnSettings(spawns.build())
                .generationSettings(generation.build())
                .build()
        }
     */

    /*BIOME TEMPLATE
    fun BootstrapContext<Biome>.createExample(): Biome {
        val features = this.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.lookup(RegistryKeys.CONFIGURED_CARVER)

        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.Builder(features, carver)

        /* Add Spawns */

        /* Add Features */

        return OverworldBiomeCreator.create(
            true,
            0f,
            0f,
            spawns, generation, DEFAULT_MUSIC
        )
    }
    */

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

}