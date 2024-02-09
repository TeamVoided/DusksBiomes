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

object BiomeCreator {
    private val DEFAULT_MUSIC: MusicSound? = null

    fun boostrap(context: BootstrapContext<Biome?>) {
        context.register(DuskBiomes.COLD_FOREST, createForest(context, true, false))
        context.register(DuskBiomes.COLD_PLAINS, createPlains(context, true, false))
        context.register(DuskBiomes.WARM_FOREST, createForest(context, false, true))
        context.register(DuskBiomes.WARM_PLAINS, createPlains(context, false, true))
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
        context.register(DuskBiomes.WARM_RIVER, createWarmRiver(context))
        context.register(DuskBiomes.RED_WARM_RIVER, createWarmRiver(context))
        context.register(DuskBiomes.RED_WARM_OCEAN, createWarmOcean(context))
        context.register(DuskBiomes.RED_LUKEWARM_OCEAN, createLukewarmOcean(context, false))
        context.register(DuskBiomes.DEEP_RED_LUKEWARM_OCEAN, createLukewarmOcean(context, true))
        context.register(DuskBiomes.RED_BEACH, createBeach(context, false, false))
        context.register(DuskBiomes.SNOWY_RED_BEACH, createBeach(context, true, false))
        context.register(DuskBiomes.SNOWY_STONY_SHORE, createBeach(context, true, true))
        context.register(DuskBiomes.RED_DESERT, createDesert(context))
        context.register(DuskBiomes.MUSHROOM_GROVE, createMushroomIsland(context, true))
        context.register(DuskBiomes.ERODED_MUSHROOM_ISLAND, createMushroomIsland(context, true))
        context.register(DuskBiomes.MUSHROOM_CAVES, createMushroomCave(context))
        context.register(DuskBiomes.FROZEN_CAVERNS, createFrozenCaves(context))
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

        val builder2 = GenerationSettings.Builder(feature, carver)
        OverworldBiomeCreator.addBasicFeatures(builder2)
        builder2.feature(
            GenerationStep.Feature.LOCAL_MODIFICATIONS,
            DuskPlacedFeatures.COBBLESTONE_ROCK
        )
        DefaultBiomeFeatures.addLargeFerns(builder2)
        DefaultBiomeFeatures.addDefaultOres(builder2)
        DefaultBiomeFeatures.addDefaultDisks(builder2)
        builder2.feature(
            GenerationStep.Feature.VEGETAL_DECORATION,
            if (spruce) VegetationPlacedFeatures.TREES_OLD_GROWTH_SPRUCE_TAIGA else VegetationPlacedFeatures.TREES_OLD_GROWTH_PINE_TAIGA
        )
        DefaultBiomeFeatures.addDefaultFlowers(builder2)
        DefaultBiomeFeatures.addGiantTaigaGrass(builder2)
        DefaultBiomeFeatures.addDefaultMushrooms(builder2)
        DefaultBiomeFeatures.addDefaultVegetation(builder2)
        DefaultBiomeFeatures.addCommonBerries(builder2)
        val musicSound = MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_OLD_GROWTH_TAIGA)
        return OverworldBiomeCreator.create(true, if (spruce) -0.45f else 0.5f, 0.8f, builder, builder2, musicSound)
    }

    fun createPlains(context: BootstrapContext<Biome?>, cold: Boolean, warm: Boolean): Biome {
        val feature = context.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = context.lookup(RegistryKeys.CONFIGURED_CARVER)
        val builder = SpawnSettings.Builder()
        val builder2 = GenerationSettings.Builder(feature, carver)
        OverworldBiomeCreator.addBasicFeatures(builder2)
        DefaultBiomeFeatures.addPlainsMobs(builder)
        if (warm) {
            DefaultBiomeFeatures.addLargeFerns(builder2)
        } else {
            DefaultBiomeFeatures.addPlainsTallGrass(builder2)
        }
        DefaultBiomeFeatures.addDefaultOres(builder2)
        DefaultBiomeFeatures.addDefaultDisks(builder2)
        if (cold) {
            builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeatures.TREES_COLD_PLAINS)
        } else if (warm) {
            builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeatures.TREES_WARM_PLAINS)
        }
        builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.FLOWER_PLAINS)
        builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_PLAIN)
        DefaultBiomeFeatures.addDefaultMushrooms(builder2)
        DefaultBiomeFeatures.addDefaultVegetation(builder2)
        if (warm) {
            DefaultBiomeFeatures.addSparseMelons(builder2)
        }
        return OverworldBiomeCreator.create(
            true,
            if (cold) 0.6f else 1.45f,
            if (cold) 0.8f else 0.2f,
            builder,
            builder2,
            DEFAULT_MUSIC
        )
    }

    fun createForest(context: BootstrapContext<Biome?>, cold: Boolean, warm: Boolean): Biome {
        val feature = context.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = context.lookup(RegistryKeys.CONFIGURED_CARVER)
        val builder = GenerationSettings.Builder(feature, carver)
        val builder2 = SpawnSettings.Builder()
        DefaultBiomeFeatures.addFarmAnimals(builder2)
        DefaultBiomeFeatures.addBatsAndMonsters(builder2)
        if (warm) {
            builder2.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.RABBIT, 2, 2, 3))
            builder2.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.FOX, 6, 2, 4))
        }
        OverworldBiomeCreator.addBasicFeatures(builder)
        DefaultBiomeFeatures.addLargeFerns(builder)
        DefaultBiomeFeatures.addDefaultOres(builder)
        DefaultBiomeFeatures.addDefaultDisks(builder)
        if (cold) {
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.TREES_FLOWER_FOREST)
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.FLOWER_FLOWER_FOREST)
            DefaultBiomeFeatures.addDefaultGrass(builder)
        } else {
            DefaultBiomeFeatures.addForestTrees(builder)
            DefaultBiomeFeatures.addDefaultFlowers(builder)
            DefaultBiomeFeatures.addForestGrass(builder)
        }
        DefaultBiomeFeatures.addDefaultMushrooms(builder)
        DefaultBiomeFeatures.addDefaultVegetation(builder)
        return OverworldBiomeCreator.create(
            true,
            if (cold) 0.4f else 1.4f,
            if (cold) 0.3f else 0.8f,
            builder2,
            builder,
            MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_FOREST)
        )
    }

    fun createSnowyCherryGrove(context: BootstrapContext<Biome?>): Biome {
        val feature = context.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = context.lookup(RegistryKeys.CONFIGURED_CARVER)
        val builder = GenerationSettings.Builder(feature, carver)
        val builder2 = SpawnSettings.Builder()
        builder2.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.PIG, 1, 1, 2))
            .spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.RABBIT, 2, 2, 6))
            .spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.SHEEP, 2, 2, 4))
            .spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.FOX, 1, 2, 4))
            .spawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.STRAY, 80, 4, 4))
        DefaultBiomeFeatures.addCaveMobs(builder2)
        DefaultBiomeFeatures.addMonsters(builder2, 95, 5, 20, false)
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
            builder2,
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
        val builder2 = GenerationSettings.Builder(feature, carver)
        OverworldBiomeCreator.addBasicFeatures(builder2)
        DefaultBiomeFeatures.addDefaultOres(builder2)
        DefaultBiomeFeatures.addDefaultDisks(builder2)
        if (forest) {
            DefaultBiomeFeatures.addWindsweptForestTrees(builder2)
        } else {
            DefaultBiomeFeatures.addMountainTrees(builder2)
        }
        DefaultBiomeFeatures.addDefaultFlowers(builder2)
        DefaultBiomeFeatures.addDefaultGrass(builder2)
        DefaultBiomeFeatures.addDefaultMushrooms(builder2)
        DefaultBiomeFeatures.addDefaultVegetation(builder2)
        DefaultBiomeFeatures.addEmeraldOre(builder2)
        DefaultBiomeFeatures.addInfestedStone(builder2)
        return OverworldBiomeCreator.create(true, -0.55f, 0.15f, builder, builder2, DEFAULT_MUSIC)
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
        OverworldBiomeCreator.addBasicFeatures(generationSettings)
        DefaultBiomeFeatures.addDefaultOres(generationSettings)
        DefaultBiomeFeatures.addGrassAndClayDisks(generationSettings)
        if (frozen) {
            if (windswept){
                generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeatures.TREES_WINDSWEPT_MANGROVE_FROZEN)
            }
            else {
                generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeatures.TREES_MANGROVE_FROZEN)
                generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.FLOWER_SWAMP)
            }
        } else {
            if (windswept){
                generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeatures.TREES_WINDSWEPT_MANGROVE)
            }
            else {
                DefaultBiomeFeatures.addMangroveSwampFeatures(generationSettings)
            }
        }
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION,VegetationPlacedFeatures.PATCH_GRASS_NORMAL)
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
        val builder2 = GenerationSettings.Builder(feature, carver)
        DefaultBiomeFeatures.addFossils(builder2)
        OverworldBiomeCreator.addBasicFeatures(builder2)
        DefaultBiomeFeatures.addDefaultOres(builder2)
        DefaultBiomeFeatures.addClayDisk(builder2)
        if (!oldGrowth) {
            DefaultBiomeFeatures.addSwampFeatures(builder2)
        }
        else {
            generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeatures.TREES_OLD_GROWTH_SWAMP)
            generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.FLOWER_SWAMP)
            generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_NORMAL)
            generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_DEAD_BUSH)
            generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_WATERLILY)
            generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.BROWN_MUSHROOM_SWAMP)
            generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.RED_MUSHROOM_SWAMP)
        }
        DefaultBiomeFeatures.addDefaultMushrooms(builder2)
        DefaultBiomeFeatures.addSwampVegetation(builder2)
        builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.SEAGRASS_SWAMP)
        val musicSound = MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_SWAMP)
        return Biome.Builder().hasPrecipitation(true).temperature(0.8f).downfall(0.9f).effects(
            BiomeEffects.Builder().waterColor(6388580).waterFogColor(2302743).fogColor(12638463)
                .skyColor(OverworldBiomeCreator.getSkyColor(0.8f)).foliageColor(6975545)
                .grassColorModifier(GrassColorModifier.SWAMP).moodSound(BiomeMoodSound.CAVE).music(musicSound).build()
        ).spawnSettings(builder.build()).generationSettings(builder2.build()).build()
    }

    fun createWarmRiver(context: BootstrapContext<Biome?>): Biome {
        val feature = context.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = context.lookup(RegistryKeys.CONFIGURED_CARVER)
        val builder = SpawnSettings.Builder()
            .spawn(SpawnGroup.WATER_AMBIENT, SpawnEntry(EntityType.PUFFERFISH, 15, 1, 3))
        DefaultBiomeFeatures.addWarmOceanMobs(builder, 10, 4)

        DefaultBiomeFeatures.addBatsAndMonsters(builder)
        builder.spawn(SpawnGroup.MONSTER, SpawnEntry(EntityType.DROWNED, 100, 1, 1))
        val builder2 = GenerationSettings.Builder(feature, carver)
        DefaultBiomeFeatures.addDefaultOres(builder2)
        DefaultBiomeFeatures.addDefaultDisks(builder2)
        DefaultBiomeFeatures.addDefaultFlowers(builder2)
        DefaultBiomeFeatures.addDefaultGrass(builder2)
        DefaultBiomeFeatures.addDesertDeadBushes(builder2)
        DefaultBiomeFeatures.addDefaultMushrooms(builder2)
        DefaultBiomeFeatures.addDesertVegetation(builder2)
        DefaultBiomeFeatures.addDesertFeatures(builder2)
        builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.SEAGRASS_RIVER)
        return OverworldBiomeCreator.create(
            false,
            1.5f,
            0.25f,
            4445678,
            270131,
            null as Int?,
            null as Int?,
            builder,
            builder2,
            DEFAULT_MUSIC
        )
    }

    fun createMushroomIsland(context: BootstrapContext<Biome?>, grove: Boolean): Biome {
        val feature = context.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = context.lookup(RegistryKeys.CONFIGURED_CARVER)
        val builder = SpawnSettings.Builder()
        DefaultBiomeFeatures.addMushroomMobs(builder)
        val builder2 = GenerationSettings.Builder(feature, carver)
        OverworldBiomeCreator.addBasicFeatures(builder2)
        DefaultBiomeFeatures.addDefaultOres(builder2)
        DefaultBiomeFeatures.addDefaultDisks(builder2)
        if (grove) {
            builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeatures.MUSHROOM_GROVE_VEGETATION)
            builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.BROWN_MUSHROOM_TAIGA)
            builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.RED_MUSHROOM_TAIGA)
        } else {
            DefaultBiomeFeatures.addMushroomFieldsFeatures(builder2)
        }
        DefaultBiomeFeatures.addDefaultVegetation(builder2)
        return OverworldBiomeCreator.create(true, 0.9f, 1.0f, builder, builder2, DEFAULT_MUSIC)
    }


    fun createDesert(context: BootstrapContext<Biome?>): Biome {
        val holderProvider = context.lookup(RegistryKeys.PLACED_FEATURE)
        val holderProvider2 = context.lookup(RegistryKeys.CONFIGURED_CARVER)
        return OverworldBiomeCreator.createDesert(holderProvider, holderProvider2)
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

    fun createMushroomCave(context: BootstrapContext<Biome?>): Biome {
        val feature = context.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = context.lookup(RegistryKeys.CONFIGURED_CARVER)
        val builder = SpawnSettings.Builder()
        DefaultBiomeFeatures.addMushroomMobs(builder)
        val builder2 = GenerationSettings.Builder(feature, carver)
        OverworldBiomeCreator.addBasicFeatures(builder2)
        DefaultBiomeFeatures.addDefaultOres(builder2)
        DefaultBiomeFeatures.addDefaultDisks(builder2)
        builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, DuskPlacedFeatures.MUSHROOM_GROVE_VEGETATION)
        builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.BROWN_MUSHROOM_TAIGA)
        builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.RED_MUSHROOM_TAIGA)
        DefaultBiomeFeatures.addDefaultVegetation(builder2)
        return OverworldBiomeCreator.create(true, 0.9f, 1.0f, builder, builder2, DEFAULT_MUSIC)
    }

    fun createFrozenCaves(context: BootstrapContext<Biome?>): Biome {
        val feature = context.lookup(RegistryKeys.PLACED_FEATURE)
        val carver = context.lookup(RegistryKeys.CONFIGURED_CARVER)
        val builder = SpawnSettings.Builder()
        DefaultBiomeFeatures.addDripstoneCaveMobs(builder)
        val builder2 = GenerationSettings.Builder(feature, carver)
        OverworldBiomeCreator.addBasicFeatures(builder2)
        DefaultBiomeFeatures.addPlainsTallGrass(builder2)
        DefaultBiomeFeatures.addDefaultOres(builder2, true)
        DefaultBiomeFeatures.addDefaultDisks(builder2)
        DefaultBiomeFeatures.addPlainsFeatures(builder2)
        DefaultBiomeFeatures.addDefaultMushrooms(builder2)
        DefaultBiomeFeatures.addDefaultVegetation(builder2)
        DefaultBiomeFeatures.addDripstone(builder2)
        val musicSound = MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_FROZEN_PEAKS)
        return OverworldBiomeCreator.create(true, 0.8f, 0.4f, builder, builder2, musicSound)
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