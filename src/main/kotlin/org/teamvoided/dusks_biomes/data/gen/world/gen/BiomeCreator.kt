package org.teamvoided.dusks_biomes.data.gen.world.gen

import net.minecraft.block.Blocks
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.particle.BlockStateParticleEffect
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.Registerable
import net.minecraft.registry.RegistryKeys
import net.minecraft.sound.BiomeMoodSound
import net.minecraft.sound.MusicSound
import net.minecraft.sound.MusicType
import net.minecraft.sound.SoundEvents
import net.minecraft.world.biome.*
import net.minecraft.world.biome.Biome.TemperatureModifier
import net.minecraft.world.biome.BiomeEffects.GrassColorModifier
import net.minecraft.world.biome.SpawnSettings.SpawnEntry
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.feature.DefaultBiomeFeatures
import net.minecraft.world.gen.feature.OceanPlacedFeatures
import net.minecraft.world.gen.feature.VegetationPlacedFeatures
import org.teamvoided.dusks_biomes.data.gen.world.gen.biome_creator.SnowyVariants.createDenseGrove
import org.teamvoided.dusks_biomes.data.gen.world.gen.biome_creator.SnowyVariants.createFrozenBadlands
import org.teamvoided.dusks_biomes.data.gen.world.gen.biome_creator.SnowyVariants.createFrozenMangroveSwamp
import org.teamvoided.dusks_biomes.data.gen.world.gen.biome_creator.SnowyVariants.createSnowyCherryGrove
import org.teamvoided.dusks_biomes.data.gen.world.gen.biome_creator.SnowyVariants.createSnowyOldGrowthTaiga
import org.teamvoided.dusks_biomes.data.gen.world.gen.biome_creator.SnowyVariants.createSnowyWindsweptHills
import org.teamvoided.dusks_biomes.data.world.gen.DuskPlacedFeatures
import org.teamvoided.dusks_biomes.init.DuskBiomes
import org.teamvoided.dusks_biomes.mixin.OverworldBiomeCreatorAccessor
import net.minecraft.world.gen.GenerationStep.Feature.VEGETAL_DECORATION as vd9

@Suppress("MemberVisibilityCanBePrivate", "MagicNumber")
object BiomeCreator {
    const val DEFAULT_WATER_COLOR: Int = 4159204
    const val DEFAULT_WATER_FOG_COLOR: Int = 329011
    const val DEFAULT_FOG_COLOR = 12638463
    val DEFAULT_MUSIC: MusicSound? = null

    @Suppress("BooleanLiteralArgument")
    fun boostrap(c: Registerable<Biome>) {
        c.register(DuskBiomes.COLD_FOREST, c.createTemperatureForest(true, false))
        c.register(DuskBiomes.COLD_PLAINS, c.createTemperaturePlains(true, false))
        c.register(DuskBiomes.WARM_FOREST, c.createTemperatureForest(false, true))
        c.register(DuskBiomes.WARM_PLAINS, c.createTemperaturePlains(false, true))
        c.register(DuskBiomes.WINDSWEPT_BIRCH_FOREST, c.createWindsweptBirchForest())
        c.register(DuskBiomes.SNOWY_WINDSWEPT_HILLS, c.createSnowyWindsweptHills())
        c.register(DuskBiomes.SNOWY_WINDSWEPT_GRAVELLY_HILLS, c.createSnowyWindsweptHills())
        c.register(DuskBiomes.SNOWY_WINDSWEPT_FOREST, c.createSnowyWindsweptHills(true))
        c.register(DuskBiomes.SNOWY_OLD_GROWTH_PINE_TAIGA, c.createSnowyOldGrowthTaiga(false))
        c.register(DuskBiomes.SNOWY_OLD_GROWTH_SPRUCE_TAIGA, c.createSnowyOldGrowthTaiga(true))
        c.register(DuskBiomes.DARK_GROVE, c.createDenseGrove())
        c.register(DuskBiomes.PALE_GROVE, c.createDenseGrove(true))
        c.register(DuskBiomes.SNOWY_CHERRY_GROVE, c.createSnowyCherryGrove())
        c.register(DuskBiomes.FROZEN_BADLANDS, c.createFrozenBadlands())
        c.register(DuskBiomes.FROZEN_WOODED_BADLANDS, c.createFrozenBadlands(true))
        c.register(DuskBiomes.FROZEN_ERODED_BADLANDS, c.createFrozenBadlands())
        c.register(DuskBiomes.FROZEN_MANGROVE_SWAMP, c.createFrozenMangroveSwamp())
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


    fun Registerable<Biome>.createTemperatureForest(cold: Boolean, warm: Boolean): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)

        val spawns = GenerationSettings.LookupBackedBuilder(features, carver)

        val generation = SpawnSettings.Builder()

        DefaultBiomeFeatures.addFarmAnimals(generation)
        DefaultBiomeFeatures.addBatsAndMonsters(generation)
        if (cold) {
            generation.spawn(SpawnGroup.CREATURE, 2, SpawnEntry(EntityType.RABBIT, 2, 3))
            generation.spawn(SpawnGroup.CREATURE, 6, SpawnEntry(EntityType.FOX, 2, 4))
        }
        addBasicFeatures(spawns)
        if (cold) DefaultBiomeFeatures.addLargeFerns(spawns)

        DefaultBiomeFeatures.addForestFlowers(spawns)
        DefaultBiomeFeatures.addDefaultOres(spawns)
        DefaultBiomeFeatures.addDefaultDisks(spawns)
        if (cold) spawns.feature(vd9, DuskPlacedFeatures.TREES_COLD_FOREST)
        else if (warm) spawns.feature(vd9, DuskPlacedFeatures.TREES_WARM_FOREST)
        else DefaultBiomeFeatures.addForestTrees(spawns)

        DefaultBiomeFeatures.addDefaultFlowers(spawns)
        DefaultBiomeFeatures.addDefaultGrass(spawns)
        DefaultBiomeFeatures.addForestGrass(spawns)
        DefaultBiomeFeatures.addDefaultMushrooms(spawns)
        DefaultBiomeFeatures.addDefaultVegetation(spawns, false)

        return createBiome(
            true,
            if (cold) 0.4f else 1.4f,
            if (cold) 0.8f else 0.3f,
            generation, spawns,
            MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_FOREST)
        )
    }

    fun Registerable<Biome>.createTemperaturePlains(cold: Boolean, warm: Boolean): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)

        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.LookupBackedBuilder(features, carver)

        addBasicFeatures(generation)
        DefaultBiomeFeatures.addPlainsMobs(spawns)
        if (warm) DefaultBiomeFeatures.addLargeFerns(generation)
        else DefaultBiomeFeatures.addPlainsTallGrass(generation)

        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        generation.feature(
            vd9,
            if (cold) DuskPlacedFeatures.TREES_COLD_PLAINS
            else DuskPlacedFeatures.TREES_WARM_PLAINS
        )
        generation.feature(vd9, VegetationPlacedFeatures.FLOWER_PLAIN)
        generation.feature(vd9, VegetationPlacedFeatures.PATCH_GRASS_PLAIN)
        DefaultBiomeFeatures.addDefaultMushrooms(generation)
        DefaultBiomeFeatures.addDefaultVegetation(generation, true)
        if (warm) DefaultBiomeFeatures.addSparseMelons(generation)

        return createBiome(
            true,
            if (cold) 0.6f else 1.45f,
            if (cold) 0.8f else 0.2f,
            spawns, generation, DEFAULT_MUSIC
        )
    }

    fun Registerable<Biome>.createWindsweptBirchForest(): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)

        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.LookupBackedBuilder(features, carver)

        addBasicFeatures(generation)
        DefaultBiomeFeatures.addForestFlowers(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        generation.feature(vd9, DuskPlacedFeatures.TREES_WINDSWEPT_BIRCH)
        DefaultBiomeFeatures.addDefaultFlowers(generation)
        generation.feature(vd9, VegetationPlacedFeatures.PATCH_GRASS_NORMAL)
        DefaultBiomeFeatures.addDefaultMushrooms(generation)
        DefaultBiomeFeatures.addDefaultVegetation(generation, true)
        DefaultBiomeFeatures.addFarmAnimals(spawns)
        DefaultBiomeFeatures.addBatsAndMonsters(spawns)
        return createBiome(
            true,
            0.6f,
            0.6f,
            spawns, generation,
            MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_FOREST)
        )
    }



    fun Registerable<Biome>.createSwamp(oldGrowth: Boolean): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.LookupBackedBuilder(features, carver)

        DefaultBiomeFeatures.addFarmAnimals(spawns)
        DefaultBiomeFeatures.addBatsAndMonsters(spawns)
        spawns.spawn(SpawnGroup.MONSTER, 1, SpawnEntry(EntityType.SLIME, 1, 1))
        spawns.spawn(SpawnGroup.CREATURE, 10, SpawnEntry(EntityType.FROG, 2, 5))
        DefaultBiomeFeatures.addFossils(generation)
        addBasicFeatures(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addClayDisk(generation)
        if (oldGrowth) BiomeFeatures.addOldGrowthSwampFeatures(generation)
        else DefaultBiomeFeatures.addSwampFeatures(generation)

        DefaultBiomeFeatures.addDefaultMushrooms(generation)
        DefaultBiomeFeatures.addSwampVegetation(generation)
        generation.feature(vd9, OceanPlacedFeatures.SEAGRASS_SWAMP)

        return Biome.Builder()
            .precipitation(true)
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

    fun Registerable<Biome>.createDesert(red: Boolean, cave: Boolean): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.LookupBackedBuilder(features, carver)

        DefaultBiomeFeatures.addDesertMobs(spawns)
        if (cave) spawns.spawn(SpawnGroup.MONSTER, 95, SpawnEntry(EntityType.DROWNED, 4, 4))
        DefaultBiomeFeatures.addFossils(generation)
        BiomeFeatures.addBasicFeaturesNoDungeon(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        DefaultBiomeFeatures.addDefaultFlowers(generation)
        DefaultBiomeFeatures.addDefaultGrass(generation)
        DefaultBiomeFeatures.addDesertDryVegetation(generation)
        DefaultBiomeFeatures.addDefaultMushrooms(generation)
        BiomeFeatures.addDesertsFeatures(generation, red, cave)

        val biomeEffects = if (cave) {
            BiomeEffects.Builder().particleConfig(
                BiomeParticleConfig(
                    BlockStateParticleEffect(
                        ParticleTypes.FALLING_DUST,
                        if (red) Blocks.RED_SAND.defaultState
                        else Blocks.SAND.defaultState
                    ), 0.00025F
                )
            )
        } else {
            BiomeEffects.Builder()
        }

        return Biome.Builder()
            .precipitation(false)
            .temperature(2f)
            .downfall(0f)
            .effects(
                biomeEffects
                    .waterColor(4445678)
                    .waterFogColor(270131)
                    .fogColor(DEFAULT_FOG_COLOR)
                    .skyColor(OverworldBiomeCreator.getSkyColor(2f))
                    .moodSound(BiomeMoodSound.CAVE)
                    .music(MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_DESERT)).build()
            )
            .spawnSettings(spawns.build())
            .generationSettings(generation.build())
            .build()
    }

    fun Registerable<Biome>.createWarmRiver(red: Boolean): Biome {
        val feature = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.LookupBackedBuilder(feature, carver)

        spawns.spawn(SpawnGroup.WATER_AMBIENT, 15, SpawnEntry(EntityType.PUFFERFISH, 1, 3))
        spawns.spawn(SpawnGroup.WATER_CREATURE, 10, SpawnEntry(EntityType.SQUID, 4, 4))
        spawns.spawn(SpawnGroup.WATER_AMBIENT, 25, SpawnEntry(EntityType.TROPICAL_FISH, 8, 8))
        spawns.spawn(SpawnGroup.WATER_CREATURE, 1, SpawnEntry(EntityType.DOLPHIN, 1, 2))
        spawns.spawn(SpawnGroup.MONSTER, 100, SpawnEntry(EntityType.DROWNED, 1, 1))
        DefaultBiomeFeatures.addBatsAndMonsters(spawns)

        addBasicFeatures(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        DefaultBiomeFeatures.addDefaultFlowers(generation)
        DefaultBiomeFeatures.addDefaultGrass(generation)
        DefaultBiomeFeatures.addDesertDryVegetation(generation)
        DefaultBiomeFeatures.addDefaultMushrooms(generation)
        BiomeFeatures.addDesertsFeatures(generation, red, false)
        generation.feature(vd9, OceanPlacedFeatures.SEAGRASS_RIVER)

        return createBiome(
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

    fun Registerable<Biome>.createWarmOcean(): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)

        return OverworldBiomeCreator.createWarmOcean(features, carver)
    }

    fun Registerable<Biome>.createLukewarmOcean(deep: Boolean): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)

        return OverworldBiomeCreator.createLukewarmOcean(features, carver, deep)
    }

    fun Registerable<Biome>.createBeach(snowy: Boolean, stony: Boolean): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
        val spawns = SpawnSettings.Builder()
        if (!stony && !snowy) {
            spawns.spawn(SpawnGroup.CREATURE, 5, SpawnEntry(EntityType.TURTLE, 2, 5))
        }
        DefaultBiomeFeatures.addBatsAndMonsters(spawns)
        val generation = GenerationSettings.LookupBackedBuilder(features, carver)
        addBasicFeatures(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        DefaultBiomeFeatures.addDefaultFlowers(generation)
        DefaultBiomeFeatures.addDefaultGrass(generation)
        DefaultBiomeFeatures.addDefaultMushrooms(generation)
        DefaultBiomeFeatures.addDefaultVegetation(generation, true)
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
            .precipitation(true)
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

    fun Registerable<Biome>.createMushroomIsland(grove: Boolean, eroded: Boolean): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)

        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.LookupBackedBuilder(features, carver)

        DefaultBiomeFeatures.addMushroomMobs(spawns)

        addBasicFeatures(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        if (grove) BiomeFeatures.addMushroomGroveFeatures(generation)
        else DefaultBiomeFeatures.addMushroomFieldsFeatures(generation)
        DefaultBiomeFeatures.addDefaultVegetation(generation, true)
        if (eroded) BiomeFeatures.addMushroomErodedFeatures(generation)

        return createBiome(
            true,
            0.9f,
            1.0f,
            spawns, generation, DEFAULT_MUSIC
        )
    }

    fun Registerable<Biome>.createMushroomCave(): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.LookupBackedBuilder(features, carver)
        DefaultBiomeFeatures.addMushroomMobs(spawns)
        addBasicFeatures(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        DefaultBiomeFeatures.addDefaultVegetation(generation, false)
        BiomeFeatures.addMushroomCaveFeatures(generation)
        return createBiome(
            true,
            0.9f,
            1.0f,
            spawns, generation, DEFAULT_MUSIC
        )
    }

    fun Registerable<Biome>.createFrozenCaves(): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)

        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.LookupBackedBuilder(features, carver)

        DefaultBiomeFeatures.addCaveMobs(spawns)
        DefaultBiomeFeatures.addMonsters(spawns, 95, 5, 20, false)
        spawns.spawn(SpawnGroup.MONSTER, 80, SpawnEntry(EntityType.STRAY, 4, 4))

        DefaultBiomeFeatures.addFossils(generation)
        BiomeFeatures.addBasicFeaturesNoDungeon(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        DefaultBiomeFeatures.addSnowySpruceTrees(generation)
        DefaultBiomeFeatures.addDefaultFlowers(generation)
        DefaultBiomeFeatures.addDefaultGrass(generation)
        DefaultBiomeFeatures.addDefaultMushrooms(generation)
        DefaultBiomeFeatures.addDefaultVegetation(generation, false)
        BiomeFeatures.addFrozenCavernsFeatures(generation)

        return createBiome(
            true,
            0f,
            0.4f,
            spawns, generation,
            MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_FROZEN_PEAKS)
        )
    }

    fun Registerable<Biome>.createGravelCave(): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
        val spawns = SpawnSettings.Builder()
        DefaultBiomeFeatures.addBatsAndMonsters(spawns)
        val generation = GenerationSettings.LookupBackedBuilder(features, carver)
        addBasicFeatures(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        DefaultBiomeFeatures.addDefaultFlowers(generation)
        DefaultBiomeFeatures.addDefaultGrass(generation)
        DefaultBiomeFeatures.addDefaultMushrooms(generation)
        DefaultBiomeFeatures.addDefaultVegetation(generation, false)
        BiomeFeatures.addGravelCaveFeatures(generation)
        DefaultBiomeFeatures.addEmeraldOre(generation)
        DefaultBiomeFeatures.addInfestedStone(generation)
        val temp = 0.2f
        return Biome.Builder()
            .precipitation(true)
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
    fun Registerable<Biome>.createExample(): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)

        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.LookupBackedBuilder(features, carver)

        /* Add Spawns */

        /* Add Features */

        return createBiome(
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

    /*    fun Registerable<Biome>.createDevilsRoar(): Biome {
            val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
            val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)

            val spawns = SpawnSettings.Builder()
            val generation = GenerationSettings.LookupBackedBuilder(features, carver)

            DefaultBiomeFeatures.addCaveMobs(spawns)
            DefaultBiomeFeatures.addMonsters(spawns, 95, 5, 100, true)
            DefaultBiomeFeatures.addFossils(generation)
            addBasicFeatures(generation)
            DefaultBiomeFeatures.addDefaultOres(generation)
            DefaultBiomeFeatures.addDefaultDisks(generation)
            val musicSound = MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_DRIPSTONE_CAVES)
            return Biome.Builder()
                .precipitation(true)
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
    fun Registerable<Biome>.createWindsweptValley(variant: String): Biome {
        val features = this.getRegistryLookup(RegistryKeys.PLACED_FEATURE)
        val carver = this.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER)
        val spawns = SpawnSettings.Builder()
        val generation = GenerationSettings.LookupBackedBuilder(features, carver)

        spawns.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.GOAT, 5, 1, 3))
        spawns.spawn(SpawnGroup.CREATURE, SpawnEntry(EntityType.LLAMA, 5, 4, 6))
        BiomeFeatures.addWindsweptValleyMobs(spawns, variant)
        addBasicFeatures(generation)
        DefaultBiomeFeatures.addLargeFerns(generation)
        DefaultBiomeFeatures.addDefaultOres(generation)
        DefaultBiomeFeatures.addDefaultDisks(generation)
        DefaultBiomeFeatures.addDefaultFlowers(generation)
        DefaultBiomeFeatures.addGiantTaigaGrass(generation)
        DefaultBiomeFeatures.addDefaultMushrooms(generation)
        DefaultBiomeFeatures.addDefaultVegetation(generation, false)
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
            .precipitation(true)
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

    fun createBiome(
        precipitation: Boolean,
        temperate: Float,
        downfall: Float,
        spawnSettings: SpawnSettings.Builder?,
        generationSettings: GenerationSettings.LookupBackedBuilder?,
        music: MusicSound?,
    ): Biome = OverworldBiomeCreatorAccessor.db_invokeCreate(
        precipitation,
        temperate,
        downfall,
        DEFAULT_WATER_COLOR,
        DEFAULT_WATER_FOG_COLOR,
        null,
        null,
        null,
        spawnSettings,
        generationSettings,
        music
    );
    //OverworldBiomeCreatorAccessor.db_invokeCreate(
    //    precipitation, temperate, downfall, spawnSettings, generationSettings, music
    //)

    fun createBiome(
        bl: Boolean,
        temperature: Float,
        f: Float,
        i: Int,
        j: Int,
        integer: Int?,
        integer2: Int?,
        builder: SpawnSettings.Builder?,
        builder2: GenerationSettings.LookupBackedBuilder?,
        value: MusicSound?,
    ): Biome = OverworldBiomeCreatorAccessor.db_invokeCreate(
        bl, temperature, f, i, j, integer, null, integer2, builder, builder2, value
    )

    fun addBasicFeatures(generation: GenerationSettings.LookupBackedBuilder) = OverworldBiomeCreatorAccessor.db_invokerAddBasicFeatures(generation)

}
