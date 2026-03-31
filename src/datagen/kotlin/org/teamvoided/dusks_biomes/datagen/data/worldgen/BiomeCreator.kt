package org.teamvoided.dusks_biomes.datagen.data.worldgen


import net.minecraft.core.Holder
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
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.attribute.AmbientParticle
import net.minecraft.world.attribute.BackgroundMusic
import net.minecraft.world.attribute.EnvironmentAttributes
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.Biome.BiomeBuilder
import net.minecraft.world.level.biome.Biome.TemperatureModifier
import net.minecraft.world.level.biome.BiomeGenerationSettings
import net.minecraft.world.level.biome.BiomeSpecialEffects
import net.minecraft.world.level.biome.MobSpawnSettings
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData
import net.minecraft.world.level.block.Blocks
import org.teamvoided.dusks_biomes.datagen.data.worldgen.biome.SnowyVariants.createDenseGrove
import org.teamvoided.dusks_biomes.datagen.data.worldgen.biome.SnowyVariants.createFrozenBadlands
import org.teamvoided.dusks_biomes.datagen.data.worldgen.biome.SnowyVariants.createFrozenMangroveSwamp
import org.teamvoided.dusks_biomes.datagen.data.worldgen.biome.SnowyVariants.createSnowyCherryGrove
import org.teamvoided.dusks_biomes.datagen.data.worldgen.biome.SnowyVariants.createSnowyOldGrowthTaiga
import org.teamvoided.dusks_biomes.datagen.data.worldgen.biome.SnowyVariants.createSnowyWindsweptHills
import org.teamvoided.dusks_biomes.datagen.data.worldgen.biome.BiomeFeatures
import org.teamvoided.dusks_biomes.data.world.gen.DuskPlacedFeatures
import org.teamvoided.dusks_biomes.init.DuskBiomes
import org.teamvoided.dusks_biomes.mixin.OverworldBiomesAccessor
import net.minecraft.world.level.levelgen.GenerationStep.Decoration.VEGETAL_DECORATION as vd9

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

        val generation = BiomeGenerationSettings.Builder(features, carver)

        val spawns = MobSpawnSettings.Builder()

        BiomeDefaultFeatures.farmAnimals(spawns)
        BiomeDefaultFeatures.commonSpawns(spawns)
        if (cold) {
            spawns.addSpawn(MobCategory.CREATURE, 2, SpawnerData(EntityType.RABBIT, 2, 3))
            spawns.addSpawn(MobCategory.CREATURE, 6, SpawnerData(EntityType.FOX, 2, 4))
        }
        addBasicFeatures(generation)
        if (cold) BiomeDefaultFeatures.addFerns(generation)

        BiomeDefaultFeatures.addForestFlowers(generation)
        BiomeDefaultFeatures.addDefaultOres(generation)
        BiomeDefaultFeatures.addDefaultSoftDisks(generation)
        if (cold) generation.addFeature(vd9, DuskPlacedFeatures.TREES_COLD_FOREST)
        else if (warm) generation.addFeature(vd9, DuskPlacedFeatures.TREES_WARM_FOREST)
        else BiomeDefaultFeatures.addOtherBirchTrees(generation)

        BiomeDefaultFeatures.addBushes(generation)
        BiomeDefaultFeatures.addDefaultFlowers(generation)
        BiomeDefaultFeatures.addForestGrass(generation)
        BiomeDefaultFeatures.addDefaultMushrooms(generation)
        BiomeDefaultFeatures.addDefaultExtraVegetation(generation, false)

        val biome = OverworldBiomes.baseBiome(
            if (cold) 0.4f else 1.4f,
            if (cold) 0.8f else 0.3f,
        )
            .mobSpawnSettings(spawns.build())
            .generationSettings(generation.build())
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, BackgroundMusic(SoundEvents.MUSIC_BIOME_FOREST))

        if (warm) {
            biome.setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
        }

        return biome.build()
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

        val biome = OverworldBiomes.baseBiome(
            if (cold) 0.6f else 1.45f,
            if (cold) 0.8f else 0.2f,
        )
            .mobSpawnSettings(spawns.build())
            .generationSettings(generation.build())

        if (warm) {
            biome.setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
        }

        return biome.build()
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
        return createBiome(0.6f, 0.6f, spawns, generation, SoundEvents.MUSIC_BIOME_FOREST)
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

        return BiomeBuilder()
            .hasPrecipitation(true)
            .temperature(0.8f)
            .downfall(0.9f)
            .specialEffects(
                BiomeSpecialEffects.Builder()
                    .waterColor(6388580)
                    .foliageColorOverride(6975545)
                    .grassColorModifier(BiomeSpecialEffects.GrassColorModifier.SWAMP)
                    .build()
            )
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 2302743)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, DEFAULT_FOG_COLOR)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, OverworldBiomes.calculateSkyColor(0.8f))
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, BackgroundMusic(SoundEvents.MUSIC_BIOME_SWAMP))
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

        val biome = BiomeBuilder()
            .hasPrecipitation(false)
            .temperature(2f)
            .downfall(0f)
            .specialEffects(BiomeSpecialEffects.Builder().waterColor(4445678).build())
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 270131)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, DEFAULT_FOG_COLOR)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, OverworldBiomes.calculateSkyColor(2f))
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, BackgroundMusic(SoundEvents.MUSIC_BIOME_DESERT))
            .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
            .mobSpawnSettings(spawns.build())
            .generationSettings(generation.build())

        if (cave) {
            biome.setAttribute(
                EnvironmentAttributes.AMBIENT_PARTICLES,
                AmbientParticle.of(
                    BlockParticleOption(
                        ParticleTypes.FALLING_DUST,
                        (if (red) Blocks.RED_SAND else Blocks.SAND).defaultBlockState()
                    ), 0.00025F
                )
            )
        }

        return biome.build()
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
            false, 1.5f, 0.25f,
            4445678, 270131,
            null, null,
            spawns, generation
        )
        // TODO fix this and vanilla warm oceans
//            .setAttribute(EnvironmentAttributes.SNOW_GOLEM_MELTS, true)
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
        return BiomeBuilder()
            .hasPrecipitation(true)
            .temperature(temperature)
            .temperatureAdjustment(tempMod)
            .downfall(if (!stony && !snowy) 0.4f else 0.3f)
            .specialEffects(
                BiomeSpecialEffects.Builder()
                    .waterColor(if (snowy) 4020182 else DEFAULT_WATER_COLOR)
                    .build()
            )
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, DEFAULT_WATER_FOG_COLOR)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, DEFAULT_FOG_COLOR)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, OverworldBiomes.calculateSkyColor(temperature))
            .mobSpawnSettings(spawns.build())
            .generationSettings(generation.build())
            .build()
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

        val biome = OverworldBiomes.baseBiome(0.9f, 1f)
            .mobSpawnSettings(spawns.build())
            .generationSettings(generation.build())

        if (eroded) {
            biome.setAttribute(
                EnvironmentAttributes.BACKGROUND_MUSIC,
                BackgroundMusic.OVERWORLD.withUnderwater(Musics.UNDER_WATER)
            )
        }

        return biome
            .setAttribute(EnvironmentAttributes.CAN_PILLAGER_PATROL_SPAWN, false)
            .setAttribute(EnvironmentAttributes.INCREASED_FIRE_BURNOUT, true)
            .build()
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

        return OverworldBiomes.baseBiome(0.9f, 1f)
            .mobSpawnSettings(spawns.build())
            .generationSettings(generation.build())
            .setAttribute(EnvironmentAttributes.CAN_PILLAGER_PATROL_SPAWN, false)
            .setAttribute(EnvironmentAttributes.INCREASED_FIRE_BURNOUT, true)
            .build()
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

        return OverworldBiomes.baseBiome(0f, 0.4f)
            .mobSpawnSettings(spawns.build())
            .generationSettings(generation.build())
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, BackgroundMusic(SoundEvents.MUSIC_BIOME_FROZEN_PEAKS))
            .setAttribute(EnvironmentAttributes.INCREASED_FIRE_BURNOUT, true)
            .build()
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
        return BiomeBuilder()
            .hasPrecipitation(true)
            .temperature(0.2f)
            .downfall(0.3f)
            .specialEffects(
                BiomeSpecialEffects.Builder()
                    .waterColor(DEFAULT_WATER_COLOR)
                    .build()
            )
            .setAttribute(
                EnvironmentAttributes.AMBIENT_PARTICLES, AmbientParticle.of(
                    BlockParticleOption(ParticleTypes.FALLING_DUST, Blocks.GRAVEL.defaultBlockState()), 0.00025F
                )
            )
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, DEFAULT_WATER_FOG_COLOR)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, DEFAULT_FOG_COLOR)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, OverworldBiomes.calculateSkyColor(0.2f))
            .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, BackgroundMusic(SoundEvents.MUSIC_BIOME_STONY_PEAKS))
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
        temperate: Float,
        downfall: Float,
        mobSpawnSettings: MobSpawnSettings.Builder,
        generationSettings: BiomeGenerationSettings.Builder,
        backgroundMusic: Holder<SoundEvent>? = null,
    ): Biome {
        val biome = OverworldBiomes
            .baseBiome(temperate, downfall)
            .mobSpawnSettings(mobSpawnSettings.build())
            .generationSettings(generationSettings.build())

        if (backgroundMusic != null) {
            biome.setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, BackgroundMusic(backgroundMusic))
        }

        return biome.build()
    }

    fun createBiome(
        precipitation: Boolean,
        temperature: Float,
        downfall: Float,
        waterColor: Int,
        waterFogColor: Int,
        grassColor: Int?,
        dryFoliage: Int?,
        spawns: MobSpawnSettings.Builder,
        generators: BiomeGenerationSettings.Builder,
        music: Holder<SoundEvent>? = null,
    ): Biome {
        val effects = BiomeSpecialEffects.Builder().waterColor(waterColor)

        if (grassColor != null) effects.grassColorOverride(grassColor)
        if (dryFoliage != null) effects.dryFoliageColorOverride(dryFoliage)

        val biome = BiomeBuilder()
            .hasPrecipitation(precipitation)
            .temperature(temperature)
            .downfall(downfall)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, OverworldBiomes.calculateSkyColor(temperature))
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, waterFogColor)
            .specialEffects(effects.build())
            .mobSpawnSettings(spawns.build())
            .generationSettings(generators.build())

        if (music != null) {
            biome.setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, BackgroundMusic(music))
        }

        return biome.build()
    }

    // TODO remove all uses
    @Deprecated(
        "Original Function is now public!",
        ReplaceWith("OverworldBiomes.globalOverworldGeneration(generation)", "net.minecraft.data.worldgen.biome")
    )
    fun addBasicFeatures(generation: BiomeGenerationSettings.Builder) =
        OverworldBiomesAccessor.db_invokerGlobalOverworldGeneration(generation)

}
