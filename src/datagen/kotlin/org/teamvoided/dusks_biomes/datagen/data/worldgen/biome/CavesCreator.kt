package org.teamvoided.dusks_biomes.datagen.data.worldgen.biome

import net.minecraft.core.HolderGetter
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BiomeDefaultFeatures
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.biome.OverworldBiomes
import net.minecraft.util.TriState
import net.minecraft.world.attribute.EnvironmentAttributes
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.Biome.BiomeBuilder
import net.minecraft.world.level.biome.BiomeGenerationSettings
import net.minecraft.world.level.biome.BiomeSpecialEffects
import net.minecraft.world.level.biome.MobSpawnSettings
import net.minecraft.world.level.levelgen.GenerationStep
import org.teamvoided.dusks_biomes.data.world.gen.DuskPlacedFeatures
import net.minecraft.world.level.levelgen.GenerationStep.Decoration.VEGETAL_DECORATION as vd9


object CavesCreator {

    fun BootstrapContext<Biome>.createPaleCave(): Biome {
        val features = this.lookup(Registries.PLACED_FEATURE)
        val carver = this.lookup(Registries.CONFIGURED_CARVER)
        val spawns = MobSpawnSettings.Builder()
        val gen = BiomeGenerationSettings.Builder(features, carver)
        //BiomeDefaultFeatures.commonSpawns(spawns)
        OverworldBiomes.globalOverworldGeneration(gen)
        BiomeDefaultFeatures.addPlainGrass(gen)
        BiomeDefaultFeatures.addDefaultOres(gen)
        //BiomeDefaultFeatures.addLushCavesSpecialOres(gen)
        BiomeDefaultFeatures.addDefaultSoftDisks(gen)
        addPaleCavesVegetationFeatures(gen)
        return BiomeBuilder()
            .hasPrecipitation(true)
            .temperature(0.7f)
            .downfall(0.8f)
            .specialEffects(
                BiomeSpecialEffects.Builder()
                    .waterColor(7768221)
                    .grassColorOverride(7832178)
                    .foliageColorOverride(8883574)
                    .dryFoliageColorOverride(10528412)
                    .build()
            )
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 5597568)
            .setAttribute(EnvironmentAttributes.FOG_COLOR, 8484720)
            .setAttribute(EnvironmentAttributes.SKY_COLOR, 12171705)
            .setAttribute(EnvironmentAttributes.MUSIC_VOLUME, 0f)
            .setAttribute(EnvironmentAttributes.EYEBLOSSOM_OPEN, TriState.TRUE)
            .setAttribute(EnvironmentAttributes.CREAKING_ACTIVE, true)
            .setAttribute(EnvironmentAttributes.FOG_START_DISTANCE, 0f)
            .setAttribute(EnvironmentAttributes.FOG_END_DISTANCE, 512f)
            .mobSpawnSettings(spawns.build())
            .generationSettings(gen.build()).build()
    }

    fun addPaleCavesVegetationFeatures(builder: BiomeGenerationSettings.Builder) {
        builder.addFeature(vd9, DuskPlacedFeatures.PALE_CAVES_CEILING_VEGETATION)
        builder.addFeature(vd9, DuskPlacedFeatures.PALE_CAVES_VEGETATION)
        builder.addFeature(vd9, DuskPlacedFeatures.PALE_CAVE_ROOTS)
        builder.addFeature(vd9, DuskPlacedFeatures.PALE_CAVE_LEAVES_CEILING)
        builder.addFeature(vd9, DuskPlacedFeatures.PALE_CAVES_VINES)
        builder.addFeature(vd9, DuskPlacedFeatures.PALE_CAVE_LEAVES)
        builder.addFeature(vd9, DuskPlacedFeatures.PALE_CAVE_RESIN)
        builder.addFeature(vd9, DuskPlacedFeatures.PALE_HEART_CEILING)
        //builder.addFeature(vd9, CavePlacements.ROOTED_AZALEA_TREE)
        builder.addFeature(vd9, DuskPlacedFeatures.FLOWER_PALE_CAVE)

    }
}