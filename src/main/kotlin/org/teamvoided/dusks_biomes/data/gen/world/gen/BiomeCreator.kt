package org.teamvoided.dusks_biomes.data.gen.world.gen

import net.minecraft.block.Blocks
import net.minecraft.client.sound.MusicType
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.particle.BlockStateParticleEffect
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.BootstrapContext
import net.minecraft.registry.RegistryKeys
import net.minecraft.sound.BiomeMoodSound
import net.minecraft.sound.MusicSound
import net.minecraft.sound.SoundEvents
import net.minecraft.world.biome.*
import net.minecraft.world.biome.Biome.TemperatureModifier
import net.minecraft.world.biome.BiomeEffects.GrassColorModifier
import net.minecraft.world.biome.SpawnSettings.SpawnEntry
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.feature.DefaultBiomeFeatures
import net.minecraft.world.gen.feature.OceanPlacedFeatures
import net.minecraft.world.gen.feature.VegetationPlacedFeatures
import org.teamvoided.dusks_biomes.data.world.gen.DuskPlacedFeatures
import org.teamvoided.dusks_biomes.init.DuskBiomes

@Suppress("MemberVisibilityCanBePrivate", "MagicNumber")
object BiomeCreator {
    private const val DEFAULT_WATER_COLOR: Int = 4159204
    private const val DEFAULT_WATER_FOG_COLOR: Int = 329011
    private const val DEFAULT_FOG_COLOR = 12638463
    private val DEFAULT_MUSIC: MusicSound? = null

    @Suppress("BooleanLiteralArgument")
    fun boostrap(c: BootstrapContext<Biome>) {
        c.register(DuskBiomes.COLD_FOREST, c.createTemperatureForest(true, false))
        c.register(DuskBiomes.COLD_PLAINS, c.createTemperaturePlains(true, false))
        c.register(DuskBiomes.WARM_FOREST, c.createTemperatureForest(false, true))
        c.register(DuskBiomes.WARM_PLAINS, c.createTemperaturePlains(false, true))
        c.register(DuskBiomes.WINDSWEPT_BIRCH_FOREST, c.createWindsweptBirchForest())
        c.register(DuskBiomes.SNOWY_WINDSWEPT_HILLS, c.createSnowyWindsweptHills(false))
        c.register(DuskBiomes.SNOWY_WINDSWEPT_GRAVELLY_HILLS, c.createSnowyWindsweptHills(false))
        c.register(DuskBiomes.SNOWY_WINDSWEPT_FOREST, c.createSnowyWindsweptHills(true))
        c.register(DuskBiomes.SNOWY_OLD_GROWTH_PINE_TAIGA, c.createSnowyOldGrowthTaiga(false))
        c.register(DuskBiomes.SNOWY_OLD_GROWTH_SPRUCE_TAIGA, c.createSnowyOldGrowthTaiga(true))
        c.register(DuskBiomes.DARK_GROVE, c.createDarkGrove())
        c.register(DuskBiomes.SNOWY_CHERRY_GROVE, c.createSnowyCherryGrove())
        c.register(DuskBiomes.FROZEN_BADLANDS, c.createFrozenBadlands(false))
        c.register(DuskBiomes.FROZEN_WOODED_BADLANDS, c.createFrozenBadlands(true))
        c.register(DuskBiomes.FROZEN_ERODED_BADLANDS, c.createFrozenBadlands(false))
        c.register(DuskBiomes.FROZEN_MANGROVE_SWAMP, c.createMangroveSwamp(true))
        c.register(DuskBiomes.WARM_RIVER, c.createWarmRiver(false))
        c.register(DuskBiomes.RED_DESERT, c.createDesert(true, false))
        c.register(DuskBiomes.RED_WARM_RIVER, c.createWarmRiver(true))
        c.register(DuskBiomes.RED_WARM_OCEAN, c.createWarmOcean())
        c.register(DuskBiomes.RED_LUKEWARM_OCEAN, c.createLukewarmOcean(false))
        c.register(DuskBiomes.DEEP_RED_LUKEWARM_OCEAN, c.createLukewarmOcean(true))
        c.register(DuskBiomes.RED_BEACH, c.createBeach(false, false))
        c.register(DuskBiomes.SNOWY_RED_BEACH, c.createBeach(true, false))
        c.register(DuskBiomes.SNOWY_STONY_SHORE, c.createBeach(true, true))
        c.register(DuskBiomes.MUSHROOM_GROVE, c.createMushroomIsland(true, false))
        c.register(DuskBiomes.ERODED_MUSHROOM_ISLAND, c.createMushroomIsland(true, true))
        c.register(DuskBiomes.MUSHROOM_CAVES, c.createMushroomCave())
        c.register(DuskBiomes.FROZEN_CAVERNS, c.createFrozenCaves())
        c.register(DuskBiomes.SAND_CAVES, c.createDesert(false, true))
        c.register(DuskBiomes.RED_SAND_CAVES, c.createDesert(true, true))
        c.register(DuskBiomes.GRAVEL_CAVES, c.createGravelCave())
    }


    fun BootstrapContext<Biome>.createTemperatureForest(cold: Boolean, warm: Boolean): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)

        val spawns = GenerationSettings.Builder(features, carver)
        val generation = SpawnSettings.Builder()

        DefaultBiomeFeatures.addFarmAnimals(generation)
        DefaultBiomeFeatures.method_30581(generation)
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

    fun BootstrapContext<Biome>.createTemperaturePlains(cold: Boolean, warm: Boolean): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)

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

    fun BootstrapContext<Biome>.createWindsweptBirchForest(): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)

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
        DefaultBiomeFeatures.method_30581(spawns)
        return OverworldBiomeCreator.create(
            true,
            0.6f,
            0.6f,
            spawns, generation,
            MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_FOREST)
        )
    }

    fun BootstrapContext<Biome>.createSnowyWindsweptHills(forest: Boolean): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)

        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.Builder(features, carver)

        DefaultBiomeFeatures.addFarmAnimals(spawns)
        spawns.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.LLAMA, 5, 4, 6))
        DefaultBiomeFeatures.method_30581(spawns)

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

    fun BootstrapContext<Biome>.createSnowyOldGrowthTaiga(spruce: Boolean): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)

        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.Builder(features, carver)

        DefaultBiomeFeatures.addFarmAnimals(spawns)
        spawns.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.WOLF, 8, 4, 4))
        spawns.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.RABBIT, 4, 2, 3))
        spawns.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.FOX, 8, 2, 4))
        if (spruce) {
            DefaultBiomeFeatures.method_30581(spawns)
        } else {
            DefaultBiomeFeatures.addCaveMobs(spawns)
            DefaultBiomeFeatures.addMonsters(spawns, 100, 25, 100, false)
        }

        OverworldBiomeCreator.addBasicFeatures(generation)
        generation.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, DuskPlacedFeatures.COBBLESTONE_ROCK)
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
            if (spruce) -0.45f else -0.5f,
            0.8f,
            spawns, generation,
            MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_OLD_GROWTH_TAIGA)
        )
    }

    fun BootstrapContext<Biome>.createDarkGrove(): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)

        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.Builder(features, carver)

        spawns.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.WOLF, 1, 1, 1))
            .spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.RABBIT, 8, 2, 3))
            .spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.FOX, 4, 2, 4))
        DefaultBiomeFeatures.addCaveMobs(spawns)
        DefaultBiomeFeatures.addMonsters(spawns, 95, 5, 20, false)
        spawns.spawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.STRAY, 80, 4, 4))

        BiomeFeatures.addBasicFeaturesNoDungeon(generation)
        BiomeFeatures.addFrozenDungeons(generation)
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeatures.TREES_SNOWY_DARK_GROVE)
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeatures.TREES_SNOWY_DARK_GROVE_ON_SNOW)
        DefaultBiomeFeatures.addForestFlowers(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        DefaultBiomeFeatures.addDefaultFlowers(generation)
        DefaultBiomeFeatures.addDefaultGrass(generation)
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_DEAD_BUSH)
        generation.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            VegetationPlacedFeatures.BROWN_MUSHROOM_OLD_GROWTH
        )
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.RED_MUSHROOM_OLD_GROWTH)
        DefaultBiomeFeatures.addDefaultMushrooms(generation)
        DefaultBiomeFeatures.addDefaultVegetation(generation)
        DefaultBiomeFeatures.addEmeraldOre(generation)
        DefaultBiomeFeatures.addInfestedStone(generation)
        val musicSound = MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_GROVE)
        return Biome.Builder().hasPrecipitation(true).temperature(-0.2f).downfall(0.8f).effects(
            BiomeEffects.Builder().waterColor(DEFAULT_WATER_COLOR).waterFogColor(DEFAULT_WATER_FOG_COLOR)
                .fogColor(DEFAULT_FOG_COLOR)
                .skyColor(OverworldBiomeCreator.getSkyColor(-0.2f)).grassColorModifier(GrassColorModifier.DARK_FOREST)
                .moodSound(BiomeMoodSound.CAVE).music(musicSound).build()
        ).spawnSettings(spawns.build()).generationSettings(generation.build()).build()
    }

    fun BootstrapContext<Biome>.createSnowyCherryGrove(): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)

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
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.TREES_CHERRY)
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

    fun BootstrapContext<Biome>.createFrozenBadlands(plateau: Boolean): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.Builder(features, carver)

        DefaultBiomeFeatures.addCaveMobs(spawns)
        DefaultBiomeFeatures.addMonsters(spawns, 95, 5, 20, false)
        spawns.spawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.STRAY, 80, 4, 4))
        spawns.creatureSpawnProbability(0.03f)
        if (plateau) {
            spawns.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.WOLF, 2, 4, 8))
            spawns.creatureSpawnProbability(0.04f)
        }

        OverworldBiomeCreator.addBasicFeatures(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addExtraGoldOre(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        if (plateau) {
            generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeatures.TREES_FROZEN_BADLANDS)
        }
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_BADLANDS)
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_DEAD_BUSH)
        DefaultBiomeFeatures.addDefaultMushrooms(generation)
        DefaultBiomeFeatures.addBadlandsVegetation(generation)
        DefaultBiomeFeatures.addCommonBerries(generation)
        val temperature = -0.6f
        return Biome.Builder().hasPrecipitation(true).temperature(temperature).downfall(0.5f).effects(
            BiomeEffects.Builder()
                .waterColor(DEFAULT_WATER_COLOR)
                .waterFogColor(DEFAULT_WATER_FOG_COLOR)
                .fogColor(DEFAULT_FOG_COLOR)
                .skyColor(OverworldBiomeCreator.getSkyColor(temperature))
                .foliageColor(10387789)
                .grassColor(9470285)
                .moodSound(BiomeMoodSound.CAVE)
                .music(MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_BADLANDS))
                .build()
        ).spawnSettings(spawns.build()).generationSettings(generation.build()).build()
    }

    fun BootstrapContext<Biome>.createMangroveSwamp(frozen: Boolean): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)

        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.Builder(features, carver)

        if (frozen) {
            DefaultBiomeFeatures.addCaveMobs(spawns)
            DefaultBiomeFeatures.addMonsters(spawns, 95, 5, 20, false)
            spawns.spawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.STRAY, 80, 4, 4))
        } else DefaultBiomeFeatures.method_30581(spawns)

        spawns.spawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.SLIME, 1, 1, 1))
        spawns.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.FROG, 10, 2, 5))
        DefaultBiomeFeatures.addFossils(generation)
        BiomeFeatures.addBasicFeaturesNoDungeon(generation)
        BiomeFeatures.addLushDungeons(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addGrassAndClayDisks(generation)
        if (frozen) {
                generation.feature(
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    DuskPlacedFeatures.TREES_MANGROVE_FROZEN
                )
            generation.feature(
                GenerationStep.Feature.VEGETAL_DECORATION,
                VegetationPlacedFeatures.FLOWER_SWAMP
            )
        } else {
            DefaultBiomeFeatures.addMangroveSwampFeatures(generation)
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
            .fogColor(DEFAULT_FOG_COLOR)
            .grassColorModifier(GrassColorModifier.SWAMP)
            .moodSound(BiomeMoodSound.CAVE)
            .music(musicSound)


        return if (frozen) {
            Biome.Builder()
                .hasPrecipitation(true)
                .temperature(-0.5f)
                .downfall(0.9f)
                .temperatureModifier(TemperatureModifier.FROZEN)
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
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.Builder(features, carver)

        DefaultBiomeFeatures.addFarmAnimals(spawns)
        DefaultBiomeFeatures.method_30581(spawns)
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
                    .fogColor(DEFAULT_FOG_COLOR)
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
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.Builder(features, carver)

        DefaultBiomeFeatures.addDesertMobs(spawns)
        if (cave) spawns.spawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.DROWNED, 95, 4, 4))
        DefaultBiomeFeatures.addFossils(generation)
        BiomeFeatures.addBasicFeaturesNoDungeon(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        DefaultBiomeFeatures.addDefaultFlowers(generation)
        DefaultBiomeFeatures.addDefaultGrass(generation)
        DefaultBiomeFeatures.addDesertDeadBushes(generation)
        DefaultBiomeFeatures.addDefaultMushrooms(generation)
        BiomeFeatures.addDesertsFeatures(generation, red, cave)

        val biomeEffects = if (cave) {
            BiomeEffects.Builder().particleConfig(
                BiomeParticleConfig(
                    BlockStateParticleEffect(
                        ParticleTypes.FALLING_DUST,
                        if (red) {
                            Blocks.RED_SAND.defaultState
                        } else {
                            Blocks.SAND.defaultState
                        }
                    ), 0.00025F
                )
            )
        } else {
            BiomeEffects.Builder()
        }

        return Biome.Builder()
            .hasPrecipitation(false)
            .temperature(2.0f)
            .downfall(0.0f)
            .effects(
                biomeEffects
                    .waterColor(4445678)
                    .waterFogColor(270131)
                    .fogColor(DEFAULT_FOG_COLOR)
                    .skyColor(OverworldBiomeCreator.getSkyColor(0.8f))
                    .moodSound(BiomeMoodSound.CAVE)
                    .music(MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_DESERT)).build()
            )
            .spawnSettings(spawns.build())
            .generationSettings(generation.build())
            .build()
    }

    fun BootstrapContext<Biome>.createWarmRiver(red: Boolean): Biome {
        val feature = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.Builder(feature, carver)

        spawns.spawn(SpawnGroup.WATER_AMBIENT, SpawnEntry(EntityType.PUFFERFISH, 15, 1, 3))
        DefaultBiomeFeatures.addWarmOceanMobs(spawns, 10, 4)
        DefaultBiomeFeatures.method_30581(spawns)
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
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)

        return OverworldBiomeCreator.createWarmOcean(features, carver)
    }

    fun BootstrapContext<Biome>.createLukewarmOcean(deep: Boolean): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)

        return OverworldBiomeCreator.createLukewarmOcean(features, carver, deep)
    }

    fun BootstrapContext<Biome>.createBeach(snowy: Boolean, stony: Boolean): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
        val spawns = SpawnSettings.Builder()
        if (!stony && !snowy) {
            spawns.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.TURTLE, 5, 2, 5))
        }
        DefaultBiomeFeatures.method_30581(spawns)
        val generation = GenerationSettings.Builder(features, carver)
        OverworldBiomeCreator.addBasicFeatures(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        DefaultBiomeFeatures.addDefaultFlowers(generation)
        DefaultBiomeFeatures.addDefaultGrass(generation)
        DefaultBiomeFeatures.addDefaultMushrooms(generation)
        DefaultBiomeFeatures.addDefaultVegetation(generation)
        val temperature = if (snowy) {
            0.05f
        } else if (stony) {
            0.2f
        } else {
            0.8f
        }
        val tempMod = if (snowy && stony) {
            TemperatureModifier.FROZEN
        } else {
            TemperatureModifier.NONE
        }
        return Biome.Builder()
            .hasPrecipitation(true)
            .temperature(temperature)
            .temperatureModifier(tempMod)
            .downfall(if (!stony && !snowy) 0.4f else 0.3f)
            .effects(
                BiomeEffects.Builder()
                    .waterColor(if (snowy) 4020182 else DEFAULT_WATER_COLOR)
                    .waterFogColor(DEFAULT_WATER_FOG_COLOR)
                    .fogColor(DEFAULT_FOG_COLOR)
                    .skyColor(OverworldBiomeCreator.getSkyColor(temperature))
                    .moodSound(BiomeMoodSound.CAVE).build()
            ).spawnSettings(spawns.build()).generationSettings(generation.build()).build()
    }

    fun BootstrapContext<Biome>.createMushroomIsland(grove: Boolean, eroded: Boolean): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)

        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.Builder(features, carver)

        DefaultBiomeFeatures.addMushroomMobs(spawns)

        OverworldBiomeCreator.addBasicFeatures(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        if (grove) BiomeFeatures.addMushroomGroveFeatures(generation)
        else DefaultBiomeFeatures.addMushroomFieldsFeatures(generation)
        DefaultBiomeFeatures.addDefaultVegetation(generation)
        if (eroded) BiomeFeatures.addMushroomErodedFeatures(generation)

        return OverworldBiomeCreator.create(
            true,
            0.9f,
            1.0f,
            spawns, generation, DEFAULT_MUSIC
        )
    }

    fun BootstrapContext<Biome>.createMushroomCave(): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.Builder(features, carver)
        DefaultBiomeFeatures.addMushroomMobs(spawns)
        OverworldBiomeCreator.addBasicFeatures(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        DefaultBiomeFeatures.addDefaultVegetation(generation)
        BiomeFeatures.addMushroomCaveFeatures(generation)
        return OverworldBiomeCreator.create(
            true,
            0.9f,
            1.0f,
            spawns, generation, DEFAULT_MUSIC
        )
    }

    fun BootstrapContext<Biome>.createFrozenCaves(): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)

        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.Builder(features, carver)

        DefaultBiomeFeatures.addCaveMobs(spawns)
        DefaultBiomeFeatures.addMonsters(spawns, 95, 5, 20, false)
        spawns.spawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.STRAY, 80, 4, 4))

        DefaultBiomeFeatures.addFossils(generation)
        BiomeFeatures.addBasicFeaturesNoDungeon(generation)
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

    fun BootstrapContext<Biome>.createGravelCave(): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
        val spawns = SpawnSettings.Builder()
        DefaultBiomeFeatures.method_30581(spawns)
        val generation = GenerationSettings.Builder(features, carver)
        OverworldBiomeCreator.addBasicFeatures(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        DefaultBiomeFeatures.addDefaultFlowers(generation)
        DefaultBiomeFeatures.addDefaultGrass(generation)
        DefaultBiomeFeatures.addDefaultMushrooms(generation)
        DefaultBiomeFeatures.addDefaultVegetation(generation)
        BiomeFeatures.addGravelCaveFeatures(generation)
        DefaultBiomeFeatures.addEmeraldOre(generation)
        DefaultBiomeFeatures.addInfestedStone(generation)
        val temp = 0.2f
        return Biome.Builder()
            .hasPrecipitation(true)
            .temperature(temp)
            .downfall(0.3f)
            .effects(
                BiomeEffects.Builder().particleConfig(
                    BiomeParticleConfig(
                        BlockStateParticleEffect(
                            ParticleTypes.FALLING_DUST,
                            Blocks.GRAVEL.defaultState
                        ), 0.00025F
                    )
                )
                    .waterColor(DEFAULT_WATER_COLOR)
                    .waterFogColor(DEFAULT_WATER_FOG_COLOR)
                    .fogColor(DEFAULT_FOG_COLOR)
                    .skyColor(OverworldBiomeCreator.getSkyColor(temp))
                    .moodSound(BiomeMoodSound.CAVE)
                    .music(MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_STONY_PEAKS)).build()
            )
            .spawnSettings(spawns.build())
            .generationSettings(generation.build())
            .build()
    }

    /*BIOME TEMPLATE
    fun BootstrapContext<Biome>.createExample(): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)

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

    /*    fun BootstrapContext<Biome>.createDevilsRoar(): Biome {
            val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
            val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)

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
    /*
    fun BootstrapContext<Biome>.createWindsweptValley(variant: String): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.Builder(features, carver)

        spawns.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.GOAT, 5, 1, 3))
        spawns.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.LLAMA, 5, 4, 6))
        BiomeFeatures.addWindsweptValleyMobs(spawns, variant)
        OverworldBiomeCreator.addBasicFeatures(generation)
        DefaultBiomeFeatures.addLargeFerns(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        DefaultBiomeFeatures.addDefaultFlowers(generation)
        DefaultBiomeFeatures.addGiantTaigaGrass(generation)
        DefaultBiomeFeatures.addDefaultMushrooms(generation)
        DefaultBiomeFeatures.addDefaultVegetation(generation)
        BiomeFeatures.addWindsweptValleyFeatures(generation, variant)

        var temperature = 1.4f
        var downfall = 0.3f
        var biomeEffects = BiomeEffects.Builder()
        var waterColor = DEFAULT_WATER_COLOR
        if (variant == "topaz") {
            temperature = 1.5f
            downfall = 0f
            biomeEffects = BiomeEffects.Builder().foliageColor(16771402)
            waterColor = 1853568
        } else if (variant == "sapphire") {
            temperature = 0.175f
            downfall = 0.7f
            biomeEffects = BiomeEffects.Builder().foliageColor(2910144).grassColorModifier(GrassColorModifier.DARK_FOREST)
        } else if (variant == "ruby") {
            temperature = -0.5f
            downfall = 0.5f
            biomeEffects = BiomeEffects.Builder().foliageColor(12332875).grassColorModifier(GrassColorModifier.DARK_FOREST)
            waterColor = 1853568
        }

        return Biome.Builder()
            .hasPrecipitation(true)
            .temperature(temperature)
            .downfall(downfall)
            .effects(
                biomeEffects
                    .waterColor(waterColor)
                    .waterFogColor(DEFAULT_WATER_FOG_COLOR)
                    .fogColor(DEFAULT_FOG_COLOR)
                    .skyColor(OverworldBiomeCreator.getSkyColor(temperature))
                    .moodSound(BiomeMoodSound.CAVE)
                    .music(MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_BAMBOO_JUNGLE))
                    .build()
            )
            .spawnSettings(spawns.build())
            .generationSettings(generation.build())
            .build()
    }
     */
}
