package org.teamvoided.dusks_biomes.data.gen.world.gen


import net.minecraft.core.particles.BlockParticleOption
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BiomeDefaultFeatures
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.biome.OverworldBiomes
import net.minecraft.data.worldgen.placement.AquaticPlacements
import net.minecraft.data.worldgen.placement.VegetationPlacements
import net.minecraft.sounds.Music
import net.minecraft.sounds.Musics
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.biome.*
import net.minecraft.world.level.biome.Biome.TemperatureModifier
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData
import net.minecraft.world.level.block.Blocks
import org.teamvoided.dusks_biomes.data.gen.world.gen.biome_creator.SnowyVariants.createDenseGrove
import org.teamvoided.dusks_biomes.data.gen.world.gen.biome_creator.SnowyVariants.createFrozenBadlands
import org.teamvoided.dusks_biomes.data.gen.world.gen.biome_creator.SnowyVariants.createFrozenMangroveSwamp
import org.teamvoided.dusks_biomes.data.gen.world.gen.biome_creator.SnowyVariants.createSnowyCherryGrove
import org.teamvoided.dusks_biomes.data.gen.world.gen.biome_creator.SnowyVariants.createSnowyOldGrowthTaiga
import org.teamvoided.dusks_biomes.data.gen.world.gen.biome_creator.SnowyVariants.createSnowyWindsweptHills
import org.teamvoided.dusks_biomes.data.world.gen.DuskPlacedFeatures
import org.teamvoided.dusks_biomes.init.DuskBiomes
import org.teamvoided.dusks_biomes.mixin.OverworldBiomesAccessor
import net.minecraft.world.level.levelgen.GenerationStep.Decoration.VEGETAL_DECORATION as vd9

@Suppress("MemberVisibilityCanBePrivate", "MagicNumber")
object BiomeCreator {
    const val DEFAULT_WATER_COLOR: Int = 4159204
    const val DEFAULT_WATER_FOG_COLOR: Int = 329011
    const val DEFAULT_FOG_COLOR = 12638463
    val DEFAULT_MUSIC: Music? = null

    @Suppress("BooleanLiteralArgument")
    fun boostrap(c: BootstrapContext<Biome>) {
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


    fun BootstrapContext<Biome>.createTemperatureForest(cold: Boolean, warm: Boolean): Biome {
        val features = this.lookup(Registries.PLACED_FEATURE)
        val carver = this.lookup(Registries.CONFIGURED_CARVER)

        val spawns = BiomeGenerationSettings.Builder(features, carver)

        val generation = MobSpawnSettings.Builder()

        BiomeDefaultFeatures.farmAnimals(generation)
        BiomeDefaultFeatures.commonSpawns(generation)
        if (cold) {
            generation.addSpawn(MobCategory.CREATURE, 2, SpawnerData(EntityType.RABBIT, 2, 3))
            generation.addSpawn(MobCategory.CREATURE, 6, SpawnerData(EntityType.FOX, 2, 4))
        }
        addBasicFeatures(spawns)
        if (cold) BiomeDefaultFeatures.addFerns(spawns)

        BiomeDefaultFeatures.addForestFlowers(spawns)
        BiomeDefaultFeatures.addDefaultOres(spawns)
        BiomeDefaultFeatures.addDefaultSoftDisks(spawns)
        if (cold) spawns.addFeature(vd9, DuskPlacedFeatures.TREES_COLD_FOREST)
        else if (warm) spawns.addFeature(vd9, DuskPlacedFeatures.TREES_WARM_FOREST)
        else BiomeDefaultFeatures.addOtherBirchTrees(spawns)

        BiomeDefaultFeatures.addBushes(spawns)
        BiomeDefaultFeatures.addDefaultFlowers(spawns)
        BiomeDefaultFeatures.addForestGrass(spawns)
        BiomeDefaultFeatures.addDefaultMushrooms(spawns)
        BiomeDefaultFeatures.addDefaultExtraVegetation(spawns, false)

        return createBiome(
            true,
            if (cold) 0.4f else 1.4f,
            if (cold) 0.8f else 0.3f,
            generation, spawns,
            Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FOREST)
        )
    }

    fun BootstrapContext<Biome>.createTemperaturePlains(cold: Boolean, warm: Boolean): Biome {
        val features = this.lookup(Registries.PLACED_FEATURE)
        val carver = this.lookup(Registries.CONFIGURED_CARVER)

        val spawns = MobSpawnSettings.Builder()
        val generation = BiomeGenerationSettings.Builder(features, carver)

        addBasicFeatures(generation)
        BiomeDefaultFeatures.plainsSpawns(spawns)
        if (warm) BiomeDefaultFeatures.addFerns(generation)
        else BiomeDefaultFeatures.addPlainGrass(generation)

        BiomeDefaultFeatures.addBushes(generation)
        BiomeDefaultFeatures.addDefaultOres(generation)
        BiomeDefaultFeatures.addDefaultSoftDisks(generation)
        generation.addFeature(
            vd9,
            if (cold) DuskPlacedFeatures.TREES_COLD_PLAINS
            else DuskPlacedFeatures.TREES_WARM_PLAINS
        )
        generation.addFeature(vd9, VegetationPlacements.FLOWER_PLAINS)
        generation.addFeature(vd9, VegetationPlacements.PATCH_GRASS_PLAIN)
        BiomeDefaultFeatures.addDefaultMushrooms(generation)
        BiomeDefaultFeatures.addDefaultExtraVegetation(generation, true)
        if (warm) BiomeDefaultFeatures.addSparseJungleMelons(generation)

        return createBiome(
            true,
            if (cold) 0.6f else 1.45f,
            if (cold) 0.8f else 0.2f,
            spawns, generation, DEFAULT_MUSIC
        )
    }

    fun BootstrapContext<Biome>.createWindsweptBirchForest(): Biome {
        val features = this.lookup(Registries.PLACED_FEATURE)
        val carver = this.lookup(Registries.CONFIGURED_CARVER)

        val spawns = MobSpawnSettings.Builder()
        val generation = BiomeGenerationSettings.Builder(features, carver)

        addBasicFeatures(generation)
        BiomeDefaultFeatures.addForestFlowers(generation)
        BiomeDefaultFeatures.addDefaultOres(generation)
        BiomeDefaultFeatures.addDefaultSoftDisks(generation)
        BiomeDefaultFeatures.addBirchForestFlowers(generation)
        generation.addFeature(vd9, DuskPlacedFeatures.TREES_WINDSWEPT_BIRCH)
        BiomeDefaultFeatures.addDefaultFlowers(generation)
        generation.addFeature(vd9, VegetationPlacements.PATCH_GRASS_NORMAL)
        BiomeDefaultFeatures.addDefaultMushrooms(generation)
        BiomeDefaultFeatures.addDefaultExtraVegetation(generation, true)
        BiomeDefaultFeatures.farmAnimals(spawns)
        BiomeDefaultFeatures.commonSpawns(spawns)
        return createBiome(
            true,
            0.6f,
            0.6f,
            spawns, generation,
            Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FOREST)
        )
    }


    fun BootstrapContext<Biome>.createSwamp(oldGrowth: Boolean): Biome {
        val features = this.lookup(Registries.PLACED_FEATURE)
        val carver = this.lookup(Registries.CONFIGURED_CARVER)
        val spawns = MobSpawnSettings.Builder()
        val generation = BiomeGenerationSettings.Builder(features, carver)

        BiomeDefaultFeatures.farmAnimals(spawns)
        BiomeDefaultFeatures.commonSpawns(spawns)
        spawns.addSpawn(MobCategory.MONSTER, 1, SpawnerData(EntityType.SLIME, 1, 1))
        spawns.addSpawn(MobCategory.CREATURE, 10, SpawnerData(EntityType.FROG, 2, 5))
        BiomeDefaultFeatures.addFossilDecoration(generation)
        addBasicFeatures(generation)
        BiomeDefaultFeatures.addDefaultOres(generation)
        BiomeDefaultFeatures.addSwampClayDisk(generation)
        if (oldGrowth) BiomeFeatures.addOldGrowthSwampFeatures(generation)
        else BiomeDefaultFeatures.addSwampVegetation(generation)

        BiomeDefaultFeatures.addDefaultMushrooms(generation)
        BiomeDefaultFeatures.addSwampExtraVegetation(generation)
        generation.addFeature(vd9, AquaticPlacements.SEAGRASS_SWAMP)

        return Biome.BiomeBuilder()
            .hasPrecipitation(true)
            .temperature(0.8f)
            .downfall(0.9f)
            .specialEffects(
                BiomeSpecialEffects.Builder()
                    .waterColor(6388580)
                    .waterFogColor(2302743)
                    .fogColor(DEFAULT_FOG_COLOR)
                    .skyColor(OverworldBiomes.calculateSkyColor(0.8f))
                    .foliageColorOverride(6975545)
                    .grassColorModifier(BiomeSpecialEffects.GrassColorModifier.SWAMP)
                    .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                    .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_SWAMP))
                    .build()
            )
            .mobSpawnSettings(spawns.build())
            .generationSettings(generation.build())
            .build()
    }

    fun BootstrapContext<Biome>.createDesert(red: Boolean, cave: Boolean): Biome {
        val features = this.lookup(Registries.PLACED_FEATURE)
        val carver = this.lookup(Registries.CONFIGURED_CARVER)
        val spawns = MobSpawnSettings.Builder()
        val generation = BiomeGenerationSettings.Builder(features, carver)

        BiomeDefaultFeatures.desertSpawns(spawns)
        if (cave) spawns.addSpawn(MobCategory.MONSTER, 95, SpawnerData(EntityType.DROWNED, 4, 4))
        BiomeDefaultFeatures.addFossilDecoration(generation)
        BiomeFeatures.addBasicFeaturesNoDungeon(generation)
        BiomeDefaultFeatures.addDefaultOres(generation)
        BiomeDefaultFeatures.addDefaultSoftDisks(generation)
        BiomeDefaultFeatures.addDefaultFlowers(generation)
        BiomeDefaultFeatures.addDefaultGrass(generation)
        BiomeDefaultFeatures.addDesertVegetation(generation)
        BiomeDefaultFeatures.addDefaultMushrooms(generation)
        BiomeFeatures.addDesertsFeatures(generation, red, cave)

        val biomeEffects = if (cave) {
            BiomeSpecialEffects.Builder().ambientParticle(
                AmbientParticleSettings(
                    BlockParticleOption(
                        ParticleTypes.FALLING_DUST,
                        if (red) Blocks.RED_SAND.defaultBlockState()
                        else Blocks.SAND.defaultBlockState()
                    ), 0.00025F
                )
            )
        } else {
            BiomeSpecialEffects.Builder()
        }

        return Biome.BiomeBuilder()
            .hasPrecipitation(false)
            .temperature(2f)
            .downfall(0f)
            .specialEffects(
                biomeEffects
                    .waterColor(4445678)
                    .waterFogColor(270131)
                    .fogColor(DEFAULT_FOG_COLOR)
                    .skyColor(OverworldBiomes.calculateSkyColor(2f))
                    .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                    .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_DESERT)).build()
            )
            .mobSpawnSettings(spawns.build())
            .generationSettings(generation.build())
            .build()
    }

    fun BootstrapContext<Biome>.createWarmRiver(red: Boolean): Biome {
        val feature = this.lookup(Registries.PLACED_FEATURE)
        val carver = this.lookup(Registries.CONFIGURED_CARVER)
        val spawns = MobSpawnSettings.Builder()
        val generation = BiomeGenerationSettings.Builder(feature, carver)

        spawns.addSpawn(MobCategory.WATER_AMBIENT, 15, SpawnerData(EntityType.PUFFERFISH, 1, 3))
        spawns.addSpawn(MobCategory.WATER_CREATURE, 10, SpawnerData(EntityType.SQUID, 4, 4))
        spawns.addSpawn(MobCategory.WATER_AMBIENT, 25, SpawnerData(EntityType.TROPICAL_FISH, 8, 8))
        spawns.addSpawn(MobCategory.WATER_CREATURE, 1, SpawnerData(EntityType.DOLPHIN, 1, 2))
        spawns.addSpawn(MobCategory.MONSTER, 100, SpawnerData(EntityType.DROWNED, 1, 1))
        BiomeDefaultFeatures.commonSpawns(spawns)

        addBasicFeatures(generation)
        BiomeDefaultFeatures.addDefaultOres(generation)
        BiomeDefaultFeatures.addDefaultSoftDisks(generation)
        BiomeDefaultFeatures.addDefaultFlowers(generation)
        BiomeDefaultFeatures.addDefaultGrass(generation)
        BiomeDefaultFeatures.addDesertVegetation(generation)
        BiomeDefaultFeatures.addDefaultMushrooms(generation)
        BiomeFeatures.addDesertsFeatures(generation, red, false)
        generation.addFeature(vd9, AquaticPlacements.SEAGRASS_RIVER)

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

    fun BootstrapContext<Biome>.createWarmOcean(): Biome {
        val features = this.lookup(Registries.PLACED_FEATURE)
        val carver = this.lookup(Registries.CONFIGURED_CARVER)

        return OverworldBiomes.warmOcean(features, carver)
    }

    fun BootstrapContext<Biome>.createLukewarmOcean(deep: Boolean): Biome {
        val features = this.lookup(Registries.PLACED_FEATURE)
        val carver = this.lookup(Registries.CONFIGURED_CARVER)

        return OverworldBiomes.lukeWarmOcean(features, carver, deep)
    }

    fun BootstrapContext<Biome>.createBeach(snowy: Boolean, stony: Boolean): Biome {
        val features = this.lookup(Registries.PLACED_FEATURE)
        val carver = this.lookup(Registries.CONFIGURED_CARVER)
        val spawns = MobSpawnSettings.Builder()
        if (!stony && !snowy) {
            spawns.addSpawn(MobCategory.CREATURE, 5, SpawnerData(EntityType.TURTLE, 2, 5))
        }
        BiomeDefaultFeatures.commonSpawns(spawns)
        val generation = BiomeGenerationSettings.Builder(features, carver)
        addBasicFeatures(generation)
        BiomeDefaultFeatures.addDefaultOres(generation)
        BiomeDefaultFeatures.addDefaultSoftDisks(generation)
        BiomeDefaultFeatures.addDefaultFlowers(generation)
        BiomeDefaultFeatures.addDefaultGrass(generation)
        BiomeDefaultFeatures.addDefaultMushrooms(generation)
        BiomeDefaultFeatures.addDefaultExtraVegetation(generation, true)
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
        return Biome.BiomeBuilder()
            .hasPrecipitation(true)
            .temperature(temperature)
            .temperatureAdjustment(tempMod)
            .downfall(if (!stony && !snowy) 0.4f else 0.3f)
            .specialEffects(
                BiomeSpecialEffects.Builder()
                    .waterColor(if (snowy) 4020182 else DEFAULT_WATER_COLOR)
                    .waterFogColor(DEFAULT_WATER_FOG_COLOR)
                    .fogColor(DEFAULT_FOG_COLOR)
                    .skyColor(OverworldBiomes.calculateSkyColor(temperature))
                    .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build()
            ).mobSpawnSettings(spawns.build()).generationSettings(generation.build()).build()
    }

    fun BootstrapContext<Biome>.createMushroomIsland(grove: Boolean, eroded: Boolean): Biome {
        val features = this.lookup(Registries.PLACED_FEATURE)
        val carver = this.lookup(Registries.CONFIGURED_CARVER)

        val spawns = MobSpawnSettings.Builder()
        val generation = BiomeGenerationSettings.Builder(features, carver)

        BiomeDefaultFeatures.mooshroomSpawns(spawns)

        addBasicFeatures(generation)
        BiomeDefaultFeatures.addDefaultOres(generation)
        BiomeDefaultFeatures.addDefaultSoftDisks(generation)
        if (grove) BiomeFeatures.addMushroomGroveFeatures(generation)
        else BiomeDefaultFeatures.addMushroomFieldVegetation(generation)
        BiomeDefaultFeatures.addDefaultExtraVegetation(generation, true)
        if (eroded) BiomeFeatures.addMushroomErodedFeatures(generation)

        return createBiome(
            true,
            0.9f,
            1.0f,
            spawns, generation, DEFAULT_MUSIC
        )
    }

    fun BootstrapContext<Biome>.createMushroomCave(): Biome {
        val features = this.lookup(Registries.PLACED_FEATURE)
        val carver = this.lookup(Registries.CONFIGURED_CARVER)
        val spawns = MobSpawnSettings.Builder()
        val generation = BiomeGenerationSettings.Builder(features, carver)
        BiomeDefaultFeatures.mooshroomSpawns(spawns)
        addBasicFeatures(generation)
        BiomeDefaultFeatures.addDefaultOres(generation)
        BiomeDefaultFeatures.addDefaultSoftDisks(generation)
        BiomeDefaultFeatures.addDefaultExtraVegetation(generation, false)
        BiomeFeatures.addMushroomCaveFeatures(generation)
        return createBiome(
            true,
            0.9f,
            1.0f,
            spawns, generation, DEFAULT_MUSIC
        )
    }

    fun BootstrapContext<Biome>.createFrozenCaves(): Biome {
        val features = this.lookup(Registries.PLACED_FEATURE)
        val carver = this.lookup(Registries.CONFIGURED_CARVER)

        val spawns = MobSpawnSettings.Builder()
        val generation = BiomeGenerationSettings.Builder(features, carver)

        BiomeDefaultFeatures.commonSpawns(spawns, 20)
        spawns.addSpawn(MobCategory.MONSTER, 80, SpawnerData(EntityType.STRAY, 4, 4))

        BiomeDefaultFeatures.addFossilDecoration(generation)
        BiomeFeatures.addBasicFeaturesNoDungeon(generation)
        BiomeDefaultFeatures.addDefaultOres(generation)
        BiomeDefaultFeatures.addDefaultSoftDisks(generation)
        BiomeDefaultFeatures.addSnowyTrees(generation)
        BiomeDefaultFeatures.addDefaultFlowers(generation)
        BiomeDefaultFeatures.addDefaultGrass(generation)
        BiomeDefaultFeatures.addDefaultMushrooms(generation)
        BiomeDefaultFeatures.addDefaultExtraVegetation(generation, false)
        BiomeFeatures.addFrozenCavernsFeatures(generation)

        return createBiome(
            true,
            0f,
            0.4f,
            spawns, generation,
            Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FROZEN_PEAKS)
        )
    }

    fun BootstrapContext<Biome>.createGravelCave(): Biome {
        val features = this.lookup(Registries.PLACED_FEATURE)
        val carver = this.lookup(Registries.CONFIGURED_CARVER)
        val spawns = MobSpawnSettings.Builder()
        BiomeDefaultFeatures.commonSpawns(spawns)
        val generation = BiomeGenerationSettings.Builder(features, carver)
        addBasicFeatures(generation)
        BiomeDefaultFeatures.addDefaultOres(generation)
        BiomeDefaultFeatures.addDefaultSoftDisks(generation)
        BiomeDefaultFeatures.addDefaultFlowers(generation)
        BiomeDefaultFeatures.addDefaultGrass(generation)
        BiomeDefaultFeatures.addDefaultMushrooms(generation)
        BiomeDefaultFeatures.addDefaultExtraVegetation(generation, false)
        BiomeFeatures.addGravelCaveFeatures(generation)
        BiomeDefaultFeatures.addExtraEmeralds(generation)
        BiomeDefaultFeatures.addInfestedStone(generation)
        val temp = 0.2f
        return Biome.BiomeBuilder()
            .hasPrecipitation(true)
            .temperature(temp)
            .downfall(0.3f)
            .specialEffects(
                BiomeSpecialEffects.Builder().ambientParticle(
                    AmbientParticleSettings(
                        BlockParticleOption(
                            ParticleTypes.FALLING_DUST,
                            Blocks.GRAVEL.defaultBlockState()
                        ), 0.00025F
                    )
                )
                    .waterColor(DEFAULT_WATER_COLOR)
                    .waterFogColor(DEFAULT_WATER_FOG_COLOR)
                    .fogColor(DEFAULT_FOG_COLOR)
                    .skyColor(OverworldBiomes.calculateSkyColor(temp))
                    .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                    .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_STONY_PEAKS)).build()
            )
            .mobSpawnSettings(spawns.build())
            .generationSettings(generation.build())
            .build()
    }

    /*BIOME TEMPLATE
    fun BootstrapContext<Biome>.createExample(): Biome {
        val features = this.lookup(Registries.PLACED_FEATURE)
        val carver = this.lookup(Registries.CONFIGURED_CARVER)

        val spawns = MobSpawnSettings.Builder()
        val generation = BiomeGenerationSettings.Builder(features, carver)

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

    /*    fun BootstrapContext<Biome>.createDevilsRoar(): Biome {
            val features = this.lookup(Registries.PLACED_FEATURE)
            val carver = this.lookup(Registries.CONFIGURED_CARVER)

            val spawns = MobSpawnSettings.Builder()
            val generation = BiomeGenerationSettings.Builder(features, carver)

            BiomeDefaultFeatures.caveSpawns(spawns)
            BiomeDefaultFeatures.monsters(spawns, 95, 5, 100, true)
            BiomeDefaultFeatures.addFossilDecoration(generation)
            addBasicFeatures(generation)
            BiomeDefaultFeatures.addDefaultOres(generation)
            BiomeDefaultFeatures.addDefaultSoftDisks(generation)
            val musicSound = Musics.createGameMusic(SoundEvents.MUSIC_OVERWORLD_DRIPSTONE_CAVES)
            return Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.5f)
                .downfall(0.5f)
                .specialEffects(
                    BiomeSpecialEffects.Builder()
                        .skyColor(3750977).fogColor(4605784)
                        .waterColor(4676715).waterFogColor(66051)
                        .grassColor(6574650).foliageColor(7877401)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(musicSound)
                        .ambientParticle(AmbientParticleSettings(ParticleTypes.ASH, 0.0025f))
                        .loopSound(SoundEvents.AMBIENT_BASALT_DELTAS_LOOP)
                        .build()
                )
                .mobSpawnSettings(spawns.build())
                .generationSettings(generation.build())
                .build()
        }
     */
    /*
    fun BootstrapContext<Biome>.createWindsweptValley(variant: String): Biome {
        val features = this.lookup(Registries.PLACED_FEATURE)
        val carver = this.lookup(Registries.CONFIGURED_CARVER)
        val spawns = MobSpawnSettings.Builder()
        val generation = BiomeGenerationSettings.Builder(features, carver)

        spawns.addSpawn(MobCategory.CREATURE, SpawnerData(EntityType.GOAT, 5, 1, 3))
        spawns.addSpawn(MobCategory.CREATURE, SpawnerData(EntityType.LLAMA, 5, 4, 6))
        BiomeFeatures.addWindsweptValleyMobs(spawns, variant)
        addBasicFeatures(generation)
        BiomeDefaultFeatures.addFerns(generation)
        BiomeDefaultFeatures.addDefaultOres(generation)
        BiomeDefaultFeatures.addDefaultSoftDisks(generation)
        BiomeDefaultFeatures.addDefaultFlowers(generation)
        DefaultBiomeFeatures.addGiantTaigaGrass(generation)
        BiomeDefaultFeatures.addDefaultMushrooms(generation)
        BiomeDefaultFeatures.addDefaultExtraVegetation(generation, false)
        BiomeFeatures.addWindsweptValleyFeatures(generation, variant)

        var temperature = 1.4f
        var downfall = 0.3f
        var biomeEffects = BiomeSpecialEffects.Builder()
        var waterColor = DEFAULT_WATER_COLOR
        if (variant == "topaz") {
            temperature = 1.5f
            downfall = 0f
            biomeEffects = BiomeSpecialEffects.Builder().foliageColor(16771402)
            waterColor = 1853568
        } else if (variant == "sapphire") {
            temperature = 0.175f
            downfall = 0.7f
            biomeEffects = BiomeSpecialEffects.Builder().foliageColor(2910144).grassColorModifier(GrassColorModifier.DARK_FOREST)
        } else if (variant == "ruby") {
            temperature = -0.5f
            downfall = 0.5f
            biomeEffects = BiomeSpecialEffects.Builder().foliageColor(12332875).grassColorModifier(GrassColorModifier.DARK_FOREST)
            waterColor = 1853568
        }

        return Biome.BiomeBuilder()
            .hasPrecipitation(true)
            .temperature(temperature)
            .downfall(downfall)
            .specialEffects(
                biomeEffects
                    .waterColor(waterColor)
                    .waterFogColor(DEFAULT_WATER_FOG_COLOR)
                    .fogColor(DEFAULT_FOG_COLOR)
                    .skyColor(OverworldBiomes.calculateSkyColor(temperature))
                    .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                    .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_OVERWORLD_BAMBOO_JUNGLE))
                    .build()
            )
            .mobSpawnSettings(spawns.build())
            .generationSettings(generation.build())
            .build()
    }
     */

    fun createBiome(
        precipitation: Boolean,
        temperate: Float,
        downfall: Float,
        mobSpawnSettings: MobSpawnSettings.Builder?,
        generationSettings: BiomeGenerationSettings.Builder?,
        backgroundMusic: Music?,
    ): Biome = OverworldBiomesAccessor.db_invokeBiome(
        precipitation,
        temperate,
        downfall,
        DEFAULT_WATER_COLOR,
        DEFAULT_WATER_FOG_COLOR,
        null,
        null,
        null,
        mobSpawnSettings,
        generationSettings,
        backgroundMusic
    )
    //OverworldBiomeCreatorAccessor.db_invokeCreate(
    //    precipitation, temperate, downfall, mobSpawnSettings, generationSettings, backgroundMusic
    //)

    fun createBiome(
        bl: Boolean,
        temperature: Float,
        f: Float,
        i: Int,
        j: Int,
        integer: Int?,
        integer2: Int?,
        builder: MobSpawnSettings.Builder?,
        builder2: BiomeGenerationSettings.Builder?,
        value: Music?,
    ): Biome = OverworldBiomesAccessor.db_invokeBiome(
        bl, temperature, f, i, j, integer, null, integer2, builder, builder2, value
    )

    fun addBasicFeatures(generation: BiomeGenerationSettings.Builder) = OverworldBiomesAccessor.db_invokerGlobalOverworldGeneration(generation)

}
