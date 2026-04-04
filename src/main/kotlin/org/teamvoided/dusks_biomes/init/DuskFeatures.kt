package org.teamvoided.dusks_biomes.init

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration
import org.teamvoided.dusks_biomes.DusksBiomes.id
import org.teamvoided.dusks_biomes.world.level.levelgen.BuriedHeartFeature
import org.teamvoided.dusks_biomes.world.level.levelgen.CaveSurfaceFeature
import org.teamvoided.dusks_biomes.world.level.levelgen.CreepingVineFeature
import org.teamvoided.dusks_biomes.world.level.levelgen.config.CaveSurfaceFeatureConfig
import org.teamvoided.reef.util.register

object DuskFeatures {
    val CAVE_SURFACE = register("cave_surface_feature", CaveSurfaceFeature(CaveSurfaceFeatureConfig.CODEC))
    val CREEPING_VINE = register("creeping_vine", CreepingVineFeature(NoneFeatureConfiguration.CODEC))
    val BURIED_HEART = register("buried_heart_feature", BuriedHeartFeature(NoneFeatureConfiguration.CODEC))

    fun init() = Unit

    fun <C : FeatureConfiguration, F : Feature<C>> register(name: String, feature: F): F {
        return BuiltInRegistries.FEATURE.register(id(name), feature)
    }
}