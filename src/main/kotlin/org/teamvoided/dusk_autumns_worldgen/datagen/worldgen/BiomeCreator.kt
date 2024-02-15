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
import net.minecraft.world.gen.feature.*
import org.teamvoided.dusk_autumns_worldgen.init.worldgen.DuskBiomes
import org.teamvoided.dusk_autumns_worldgen.init.worldgen.DuskPlacedFeatures

object BiomeCreator {
    private val DEFAULT_MUSIC: MusicSound? = null

    fun boostrap(context: BootstrapContext<Biome?>) {
        context.register(DuskBiomes.COLD_FOREST, createForest(context, true, false))
        context.register(DuskBiomes.COLD_PLAINS, createPlains(context, true, false))
        context.register(DuskBiomes.WARM_FOREST, createForest(context, false, true))
        context.register(DuskBiomes.WARM_PLAINS, createPlains(context, false, true))
        context.register(DuskBiomes.WINDSWEPT_BIRCH_FOREST, createWindsweptBirchForest(context))
        context.register(DuskBiomes.SNOWY_CHERRY_GROVE, createSnowyCherryGrove(context))
        context.register(DuskBiomes.SNOWY_WINDSWEPT_HILLS, createWindsweptHills(context, false))
        context.register(DuskBiomes.SNOWY_WINDSWEPT_GRAVELLY_HILLS, createWindsweptHills(context, false))
        context.register(DuskBiomes.SNOWY_WINDSWEPT_FOREST, createWindsweptHills(context, true))
        context.register(DuskBiomes.SNOWY_OLD_GROWTH_PINE_TAIGA, createSnowyOldGrowthTaiga(context, false))
        context.register(DuskBiomes.SNOWY_OLD_GROWTH_SPRUCE_TAIGA, createSnowyOldGrowthTaiga(context, true))
        context.register(DuskBiomes.FROZEN_MANGROVE_SWAMP, createMangroveSwamp(context, false, true))
        context.register(DuskBiomes.FROZEN_WINDSWEPT_MANGROVE_SWAMP, createMangroveSwamp(context, true, true))
        context.register(DuskBiomes.WINDSWEPT_MANGROVE_SWAMP, createMangroveSwamp(context, true, false))
        context.register(DuskBiomes.OLD_GROWTH_SWAMP, createSwamp(context, true))
        context.register(DuskBiomes.WARM_RIVER, createWarmRiver(context, false))
        context.register(DuskBiomes.RED_WARM_RIVER, createWarmRiver(context, true))
        context.register(DuskBiomes.RED_WARM_OCEAN, createWarmOcean(context))
        context.register(DuskBiomes.RED_LUKEWARM_OCEAN, createLukewarmOcean(context, false))
        context.register(DuskBiomes.DEEP_RED_LUKEWARM_OCEAN, createLukewarmOcean(context, true))
        context.register(DuskBiomes.RED_BEACH, createBeach(context, false, false))
        context.register(DuskBiomes.SNOWY_RED_BEACH, createBeach(context, true, false))
        context.register(DuskBiomes.SNOWY_STONY_SHORE, createBeach(context, true, true))
        context.register(DuskBiomes.RED_DESERT, createDesert(context, true, false))
        context.register(DuskBiomes.MUSHROOM_GROVE, createMushroomIsland(context, true))
        context.register(DuskBiomes.ERODED_MUSHROOM_ISLAND, createMushroomIsland(context, true))
        context.register(DuskBiomes.MUSHROOM_CAVES, createMushroomCave(context))
        context.register(DuskBiomes.FROZEN_CAVERNS, createFrozenCaves(context))
        context.register(DuskBiomes.SAND_CAVES, createDesert(context, false, true))
        context.register(DuskBiomes.RED_SAND_CAVES, createDesert(context, true, true))

    }


    fun createSnowyOldGrowthTaiga(context: BootstrapContext<Biome?>, spruce: Boolean): Biome {
        val feature = context.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = context.lookup(RegistryKeys.CONFIGURED_CARVER)
        val builder = SpawnSettings.Builder()
        DefaultBiomeFeatures.addFarmAnimals(builder)
        builder.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.WOLF, 8, 4, 4))
        builder.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.RABBIT, 4, 2, 3))
        builder.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.FOX, 8, 2, 4))
        if (spruce) {
            DefaultBiomeFeatures.addBatsAndMonsters(builder)
        } else {
            DefaultBiomeFeatures.addCaveMobs(builder)
            DefaultBiomeFeatures.addMonsters(builder, 100, 25, 100, false)
        }

        val generationSettings = GenerationSettings.Builder(feature, carver)
        OverworldBiomeCreator.addBasicFeatures(generationSettings)
        generationSettings.feature(
            GenerationStep.Feature.LOCAL_MODIFICATIONS,
            DuskPlacedFeatures.COBBLESTONE_ROCK
        )
        DefaultBiomeFeatures.addLargeFerns(generationSettings)
        DefaultBiomeFeatures.addDefaultOres(generationSettings)
        DefaultBiomeFeatures.addDefaultDisks(generationSettings)
        generationSettings.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            if (spruce) VegetationPlacedFeatures.TREES_OLD_GROWTH_SPRUCE_TAIGA else VegetationPlacedFeatures.TREES_OLD_GROWTH_PINE_TAIGA
        )
        DefaultBiomeFeatures.addDefaultFlowers(generationSettings)
        DefaultBiomeFeatures.addGiantTaigaGrass(generationSettings)
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings)
        DefaultBiomeFeatures.addDefaultVegetation(generationSettings)
        DefaultBiomeFeatures.addCommonBerries(generationSettings)
        val musicSound = MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_OLD_GROWTH_TAIGA)
        return OverworldBiomeCreator.create(
            true,
            if (spruce) -0.45f else 0.5f,
            0.8f,
            builder,
            generationSettings,
            musicSound
        )
    }

    fun createPlains(context: BootstrapContext<Biome?>, cold: Boolean, warm: Boolean): Biome {
        val feature = context.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = context.lookup(RegistryKeys.CONFIGURED_CARVER)
        val builder = SpawnSettings.Builder()
        val generationSettings = GenerationSettings.Builder(feature, carver)
        OverworldBiomeCreator.addBasicFeatures(generationSettings)
        DefaultBiomeFeatures.addPlainsMobs(builder)
        if (warm) {
            DefaultBiomeFeatures.addLargeFerns(generationSettings)
        } else {
            DefaultBiomeFeatures.addPlainsTallGrass(generationSettings)
        }
        DefaultBiomeFeatures.addDefaultOres(generationSettings)
        DefaultBiomeFeatures.addDefaultDisks(generationSettings)
        generationSettings.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            if (cold) DuskPlacedFeatures.TREES_COLD_PLAINS else DuskPlacedFeatures.TREES_WARM_PLAINS
        )
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.FLOWER_PLAINS)
        generationSettings.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            VegetationPlacedFeatures.PATCH_GRASS_PLAIN
        )
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings)
        DefaultBiomeFeatures.addDefaultVegetation(generationSettings)
        if (warm) {
            DefaultBiomeFeatures.addSparseMelons(generationSettings)
        }
        return OverworldBiomeCreator.create(
            true,
            if (cold) 0.6f else 1.45f,
            if (cold) 0.8f else 0.2f,
            builder,
            generationSettings,
            DEFAULT_MUSIC
        )
    }

    fun createForest(context: BootstrapContext<Biome?>, cold: Boolean, warm: Boolean): Biome {
        val feature = context.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = context.lookup(RegistryKeys.CONFIGURED_CARVER)
        val builder = GenerationSettings.Builder(feature, carver)
        val generationSettings = SpawnSettings.Builder()
        DefaultBiomeFeatures.addFarmAnimals(generationSettings)
        DefaultBiomeFeatures.addBatsAndMonsters(generationSettings)
        if (cold) {
            generationSettings.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.RABBIT, 2, 2, 3))
            generationSettings.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.FOX, 6, 2, 4))
        }
        OverworldBiomeCreator.addBasicFeatures(builder)
        if (cold) {
            DefaultBiomeFeatures.addLargeFerns(builder)
        }
        DefaultBiomeFeatures.addForestFlowers(builder)
        DefaultBiomeFeatures.addDefaultOres(builder)
        DefaultBiomeFeatures.addDefaultDisks(builder)
        if (cold) {
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeatures.TREES_COLD_FOREST)
        } else if (warm) {
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeatures.TREES_WARM_FOREST)
        } else {
            DefaultBiomeFeatures.addForestTrees(builder)
        }
        DefaultBiomeFeatures.addDefaultFlowers(builder)
        DefaultBiomeFeatures.addDefaultGrass(builder)
        DefaultBiomeFeatures.addForestGrass(builder)
        DefaultBiomeFeatures.addDefaultMushrooms(builder)
        DefaultBiomeFeatures.addDefaultVegetation(builder)
        return OverworldBiomeCreator.create(
            true,
            if (cold) 0.4f else 1.4f,
            if (cold) 0.8f else 0.3f,
            generationSettings,
            builder,
            MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_FOREST)
        )
    }

    fun createWindsweptBirchForest(context: BootstrapContext<Biome?>): Biome {
        val feature = context.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = context.lookup(RegistryKeys.CONFIGURED_CARVER)
        val generationSettings = GenerationSettings.Builder(feature, carver)
        val builder = SpawnSettings.Builder()
        OverworldBiomeCreator.addBasicFeatures(generationSettings)
        DefaultBiomeFeatures.addForestFlowers(generationSettings)
        DefaultBiomeFeatures.addDefaultOres(generationSettings)
        DefaultBiomeFeatures.addDefaultDisks(generationSettings)
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeatures.TREES_WINDSWEPT_BIRCH)
        DefaultBiomeFeatures.addDefaultFlowers(generationSettings)
        generationSettings.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            VegetationPlacedFeatures.PATCH_GRASS_NORMAL
        )
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings)
        DefaultBiomeFeatures.addDefaultVegetation(generationSettings)
        DefaultBiomeFeatures.addFarmAnimals(builder)
        DefaultBiomeFeatures.addBatsAndMonsters(builder)
        return OverworldBiomeCreator.create(
            true,
            0.6f,
            0.6f,
            builder,
            generationSettings,
            MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_FOREST)
        )
    }

    fun createSnowyCherryGrove(context: BootstrapContext<Biome?>): Biome {
        val feature = context.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = context.lookup(RegistryKeys.CONFIGURED_CARVER)
        val builder = GenerationSettings.Builder(feature, carver)
        val generationSettings = SpawnSettings.Builder()
        generationSettings.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.PIG, 1, 1, 2))
            .spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.RABBIT, 2, 2, 6))
            .spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.SHEEP, 2, 2, 4))
            .spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.FOX, 1, 2, 4))
            .spawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.STRAY, 80, 4, 4))
        DefaultBiomeFeatures.addCaveMobs(generationSettings)
        DefaultBiomeFeatures.addMonsters(generationSettings, 95, 5, 20, false)
        OverworldBiomeCreator.addBasicFeatures(builder)
        DefaultBiomeFeatures.addDefaultOres(builder)
        DefaultBiomeFeatures.addDefaultDisks(builder)
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_BADLANDS)
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeatures.FLOWER_SNOWY_CHERRY)
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeatures.TREES_SNOWY_CHERRY_GROVE)
        DefaultBiomeFeatures.addEmeraldOre(builder)
        DefaultBiomeFeatures.addInfestedStone(builder)
        return OverworldBiomeCreator.create(
            true,
            -0.7f,
            0.6f,
            4428999,
            4428999,
            6528354,
            6339166,
            generationSettings,
            builder,
            MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_CHERRY_GROVE)
        )
    }

    fun createWindsweptHills(context: BootstrapContext<Biome?>, forest: Boolean): Biome {
        val feature = context.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = context.lookup(RegistryKeys.CONFIGURED_CARVER)
        val builder = SpawnSettings.Builder()
        DefaultBiomeFeatures.addFarmAnimals(builder)
        builder.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.LLAMA, 5, 4, 6))
        DefaultBiomeFeatures.addBatsAndMonsters(builder)
        val generationSettings = GenerationSettings.Builder(feature, carver)
        OverworldBiomeCreator.addBasicFeatures(generationSettings)
        DefaultBiomeFeatures.addDefaultOres(generationSettings)
        DefaultBiomeFeatures.addDefaultDisks(generationSettings)
        if (forest) {
            DefaultBiomeFeatures.addWindsweptForestTrees(generationSettings)
        } else {
            DefaultBiomeFeatures.addMountainTrees(generationSettings)
        }
        DefaultBiomeFeatures.addDefaultFlowers(generationSettings)
        DefaultBiomeFeatures.addDefaultGrass(generationSettings)
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings)
        DefaultBiomeFeatures.addDefaultVegetation(generationSettings)
        DefaultBiomeFeatures.addEmeraldOre(generationSettings)
        DefaultBiomeFeatures.addInfestedStone(generationSettings)
        return OverworldBiomeCreator.create(true, -0.55f, 0.15f, builder, generationSettings, DEFAULT_MUSIC)
    }

    fun createMangroveSwamp(context: BootstrapContext<Biome?>, windswept: Boolean, frozen: Boolean): Biome {
        val feature = context.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = context.lookup(RegistryKeys.CONFIGURED_CARVER)
        val generationSettings = GenerationSettings.Builder(feature, carver)
        val spawnSettings = SpawnSettings.Builder()
        if (frozen) {
            DefaultBiomeFeatures.addCaveMobs(spawnSettings)
            DefaultBiomeFeatures.addMonsters(spawnSettings, 95, 5, 20, false)
            spawnSettings.spawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.STRAY, 80, 4, 4))
        } else {
            DefaultBiomeFeatures.addBatsAndMonsters(spawnSettings)
        }
        spawnSettings.spawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.SLIME, 1, 1, 1))
        spawnSettings.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.FROG, 10, 2, 5))
        DefaultBiomeFeatures.addFossils(generationSettings)
        BiomeFeatures.addBasicFeaturesNoDungeon(generationSettings)
        BiomeFeatures.addLushDungeons(generationSettings)
        DefaultBiomeFeatures.addDefaultOres(generationSettings)
        DefaultBiomeFeatures.addGrassAndClayDisks(generationSettings)
        if (frozen) {
            if (windswept) {
                generationSettings.feature(
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    DuskPlacedFeatures.TREES_WINDSWEPT_MANGROVE_FROZEN
                )
            } else {
                generationSettings.feature(
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    DuskPlacedFeatures.TREES_MANGROVE_FROZEN
                )
                generationSettings.feature(
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    VegetationPlacedFeatures.FLOWER_SWAMP
                )
            }
        } else {
            if (windswept) {
                generationSettings.feature(
                    GenerationStep.Feature.VEGETAL_DECORATION,
                    DuskPlacedFeatures.TREES_WINDSWEPT_MANGROVE
                )
            } else {
                DefaultBiomeFeatures.addMangroveSwampFeatures(generationSettings)
            }
        }
        generationSettings.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            VegetationPlacedFeatures.PATCH_GRASS_NORMAL
        )
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_DEAD_BUSH)
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_WATERLILY)
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.SEAGRASS_SWAMP)
        val musicSound = MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_SWAMP)

        if (frozen) {
            return Biome.Builder().hasPrecipitation(true).temperatureModifier(Biome.TemperatureModifier.FROZEN)
                .temperature(-0.5f).downfall(0.9f).effects(
                    BiomeEffects.Builder().waterColor(8692872).waterFogColor(3815975).fogColor(12638463)
                        .skyColor(OverworldBiomeCreator.getSkyColor(-0.5f))
                        .grassColorModifier(GrassColorModifier.SWAMP).moodSound(BiomeMoodSound.CAVE).music(musicSound)
                        .build()
                ).spawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build()
        } else {
            return Biome.Builder().hasPrecipitation(true).temperature(0.8f).downfall(0.9f).effects(
                BiomeEffects.Builder().waterColor(3832426).waterFogColor(5077600).fogColor(12638463)
                    .skyColor(OverworldBiomeCreator.getSkyColor(0.8f)).grassColorModifier(GrassColorModifier.SWAMP)
                    .foliageColor(9285927).moodSound(BiomeMoodSound.CAVE).music(musicSound).build()
            ).spawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build()
        }
    }

    fun createSwamp(context: BootstrapContext<Biome?>, oldGrowth: Boolean): Biome {
        val feature = context.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = context.lookup(RegistryKeys.CONFIGURED_CARVER)
        val generationSettings = GenerationSettings.Builder(feature, carver)
        val builder = SpawnSettings.Builder()
        DefaultBiomeFeatures.addFarmAnimals(builder)
        DefaultBiomeFeatures.addBatsAndMonsters(builder)
        builder.spawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.SLIME, 1, 1, 1))
        builder.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.FROG, 10, 2, 5))
        DefaultBiomeFeatures.addFossils(generationSettings)
        OverworldBiomeCreator.addBasicFeatures(generationSettings)
        DefaultBiomeFeatures.addDefaultOres(generationSettings)
        DefaultBiomeFeatures.addClayDisk(generationSettings)
        if (oldGrowth) {
            BiomeFeatures.addOldGrowthSwampFeatures(generationSettings)
        } else {
            DefaultBiomeFeatures.addSwampFeatures(generationSettings)
        }
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings)
        DefaultBiomeFeatures.addSwampVegetation(generationSettings)
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.SEAGRASS_SWAMP)
        val musicSound = MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_SWAMP)
        return Biome.Builder().hasPrecipitation(true).temperature(0.8f).downfall(0.9f).effects(
            BiomeEffects.Builder().waterColor(6388580).waterFogColor(2302743).fogColor(12638463)
                .skyColor(OverworldBiomeCreator.getSkyColor(0.8f)).foliageColor(6975545)
                .grassColorModifier(GrassColorModifier.SWAMP).moodSound(BiomeMoodSound.CAVE).music(musicSound).build()
        ).spawnSettings(builder.build()).generationSettings(generationSettings.build()).build()
    }

    fun createDesert(context: BootstrapContext<Biome?>, red: Boolean, cave: Boolean): Biome {
        val feature = context.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = context.lookup(RegistryKeys.CONFIGURED_CARVER)
        val builder = SpawnSettings.Builder()
        DefaultBiomeFeatures.addDesertMobs(builder)
        val generationSettings = GenerationSettings.Builder(feature, carver)
        DefaultBiomeFeatures.addFossils(generationSettings)
        BiomeFeatures.addBasicFeaturesNoDungeon(generationSettings)
        DefaultBiomeFeatures.addDefaultOres(generationSettings)
        DefaultBiomeFeatures.addDefaultDisks(generationSettings)
        DefaultBiomeFeatures.addDefaultFlowers(generationSettings)
        DefaultBiomeFeatures.addDefaultGrass(generationSettings)
        DefaultBiomeFeatures.addDesertDeadBushes(generationSettings)
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings)
        BiomeFeatures.addDesertsFeatures(generationSettings, red, cave)
        if (cave) {
            generationSettings.feature(
                GenerationStep.Feature.VEGETAL_DECORATION,
                OceanPlacedFeatures.WARM_OCEAN_VEGETATION
            )
            generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.SEAGRASS_WARM)
            generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.SEA_PICKLE)
        }
        return OverworldBiomeCreator.create(
            false,
            2.0f,
            0.0f,
            4445678,
            270131,
            null,
            null,
            builder,
            generationSettings,
            MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_DESERT)
        )
    }

    fun createWarmRiver(context: BootstrapContext<Biome?>, red: Boolean): Biome {
        val feature = context.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = context.lookup(RegistryKeys.CONFIGURED_CARVER)
        val generationSettings = GenerationSettings.Builder(feature, carver)
        val builder = SpawnSettings.Builder()
            .spawn(SpawnGroup.WATER_AMBIENT, SpawnEntry(EntityType.PUFFERFISH, 15, 1, 3))
        DefaultBiomeFeatures.addWarmOceanMobs(builder, 10, 4)
        DefaultBiomeFeatures.addBatsAndMonsters(builder)
        builder.spawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.DROWNED, 100, 1, 1))
        OverworldBiomeCreator.addBasicFeatures(generationSettings)
        DefaultBiomeFeatures.addDefaultOres(generationSettings)
        DefaultBiomeFeatures.addDefaultDisks(generationSettings)
        DefaultBiomeFeatures.addDefaultFlowers(generationSettings)
        DefaultBiomeFeatures.addDefaultGrass(generationSettings)
        DefaultBiomeFeatures.addDesertDeadBushes(generationSettings)
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings)
        BiomeFeatures.addDesertsFeatures(generationSettings, red, false)
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.SEAGRASS_RIVER)
        return OverworldBiomeCreator.create(
            false,
            1.5f,
            0.25f,
            4445678,
            270131,
            null as Int?,
            null as Int?,
            builder,
            generationSettings,
            DEFAULT_MUSIC
        )
    }

    fun createWarmOcean(context: BootstrapContext<Biome?>): Biome {
        val holderProvider = context.lookup(RegistryKeys.PLACED_FEATURE)
        val holderProvider2 = context.lookup(RegistryKeys.CONFIGURED_CARVER)
        return OverworldBiomeCreator.createWarmOcean(holderProvider, holderProvider2)
    }

    fun createLukewarmOcean(context: BootstrapContext<Biome?>, deep: Boolean): Biome {
        val holderProvider = context.lookup(RegistryKeys.PLACED_FEATURE)
        val holderProvider2 = context.lookup(RegistryKeys.CONFIGURED_CARVER)
        return OverworldBiomeCreator.createLukewarmOcean(holderProvider, holderProvider2, deep)
    }

    fun createBeach(context: BootstrapContext<Biome?>, snowy: Boolean, stony: Boolean): Biome {
        val holderProvider = context.lookup(RegistryKeys.PLACED_FEATURE)
        val holderProvider2 = context.lookup(RegistryKeys.CONFIGURED_CARVER)
        return OverworldBiomeCreator.createBeach(holderProvider, holderProvider2, snowy, stony)
    }

    fun createMushroomIsland(context: BootstrapContext<Biome?>, grove: Boolean): Biome {
        val feature = context.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = context.lookup(RegistryKeys.CONFIGURED_CARVER)
        val builder = SpawnSettings.Builder()
        DefaultBiomeFeatures.addMushroomMobs(builder)
        val generationSettings = GenerationSettings.Builder(feature, carver)
        OverworldBiomeCreator.addBasicFeatures(generationSettings)
        DefaultBiomeFeatures.addDefaultOres(generationSettings)
        DefaultBiomeFeatures.addDefaultDisks(generationSettings)
        if (grove) {
            BiomeFeatures.addMushroomGroveFeatures(generationSettings)
        } else {
            DefaultBiomeFeatures.addMushroomFieldsFeatures(generationSettings)
        }
        DefaultBiomeFeatures.addDefaultVegetation(generationSettings)
        return OverworldBiomeCreator.create(true, 0.9f, 1.0f, builder, generationSettings, DEFAULT_MUSIC)
    }

    fun createMushroomCave(context: BootstrapContext<Biome?>): Biome {
        val feature = context.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = context.lookup(RegistryKeys.CONFIGURED_CARVER)
        val builder = SpawnSettings.Builder()
        DefaultBiomeFeatures.addMushroomMobs(builder)
        val generationSettings = GenerationSettings.Builder(feature, carver)
        OverworldBiomeCreator.addBasicFeatures(generationSettings)
        DefaultBiomeFeatures.addDefaultOres(generationSettings)
        DefaultBiomeFeatures.addDefaultDisks(generationSettings)
        BiomeFeatures.addMushroomGroveFeatures(generationSettings)
        DefaultBiomeFeatures.addDefaultVegetation(generationSettings)
        return OverworldBiomeCreator.create(true, 0.9f, 1.0f, builder, generationSettings, DEFAULT_MUSIC)
    }

    fun createFrozenCaves(context: BootstrapContext<Biome?>): Biome {
        val feature = context.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = context.lookup(RegistryKeys.CONFIGURED_CARVER)
        val builder = SpawnSettings.Builder()
        DefaultBiomeFeatures.addCaveMobs(builder)
        DefaultBiomeFeatures.addMonsters(builder, 95, 5, 20, false)
        builder.spawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.STRAY, 80, 4, 4))
        val generationSettings = GenerationSettings.Builder(feature, carver)
        DefaultBiomeFeatures.addFossils(generationSettings)
        OverworldBiomeCreator.addBasicFeatures(generationSettings)
        DefaultBiomeFeatures.addDefaultOres(generationSettings)
        DefaultBiomeFeatures.addDefaultDisks(generationSettings)
        DefaultBiomeFeatures.addSnowySpruceTrees(generationSettings)
        DefaultBiomeFeatures.addDefaultFlowers(generationSettings)
        DefaultBiomeFeatures.addDefaultGrass(generationSettings)
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings)
        DefaultBiomeFeatures.addDefaultVegetation(generationSettings)
        BiomeFeatures.addFrozenCavernsFeatures(generationSettings)
        val musicSound = MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_FROZEN_PEAKS)
        return OverworldBiomeCreator.create(true, 0f, 0.4f, builder, generationSettings, musicSound)
    }


//    fun createDevilsRoar(context: BootstrapContext<Biome?>): Biome {
//        val feature = context.lookup(RegistryKeys.PLACED_FEATURE)
//        val carver = context.lookup(RegistryKeys.CONFIGURED_CARVER)
//        val generationSettings = GenerationSettings.Builder(feature, carver)
//        val spawnSettings = SpawnSettings.Builder()
//        DefaultBiomeFeatures.addCaveMobs(spawnSettings)
//        DefaultBiomeFeatures.addMonsters(spawnSettings, 95, 5, 100, true)
//        DefaultBiomeFeatures.addFossils(generationSettings)
//        OverworldBiomeCreator.addBasicFeatures(generationSettings)
//        DefaultBiomeFeatures.addDefaultOres(generationSettings)
//        DefaultBiomeFeatures.addDefaultDisks(generationSettings)
//        val musicSound = MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_DRIPSTONE_CAVES)
//        return Biome.Builder().hasPrecipitation(true).temperature(0.5f).downfall(0.5f)
//            .effects(
//                BiomeEffects.Builder().skyColor(3750977).fogColor(4605784).waterColor(4676715).waterFogColor(66051)
//                    .grassColor(6574650).foliageColor(7877401)
//                    .moodSound(BiomeMoodSound.CAVE).music(musicSound)
//                    .particleConfig(BiomeParticleConfig(ParticleTypes.ASH, 0.0025f))
//                    .loopSound(SoundEvents.AMBIENT_BASALT_DELTAS_LOOP)
//                    .build()
//            ).spawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build()
//    }

//    Generation Steps Reference:
//      RAW_GENERATION
//      LAKES
//      LOCAL_MODIFICATIONS
//      UNDERGROUND_STRUCTURES
//      SURFACE_STRUCTURES
//      STRONGHOLDS
//      UNDERGROUND_ORES
//      UNDERGROUND_DECORATION
//      FLUID_SPRINGS
//      VEGETAL_DECORATION
//      TOP_LAYER_MODIFICATION

}