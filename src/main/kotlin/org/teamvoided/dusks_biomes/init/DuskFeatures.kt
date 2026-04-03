package org.teamvoided.dusks_biomes.init

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import org.teamvoided.dusks_biomes.DusksBiomes.id
import org.teamvoided.dusks_biomes.world.level.levelgen.CaveSurfaceFeature
import org.teamvoided.dusks_biomes.world.level.levelgen.DirectionalBlockPileFeature
import org.teamvoided.dusks_biomes.world.level.levelgen.config.CaveSurfaceFeatureConfig
import org.teamvoided.dusks_biomes.world.level.levelgen.config.DirectionalBlockPileFeatureConfig
import org.teamvoided.reef.util.register

object DuskFeatures {
    val CaveSurfaceFeature = register("cave_surface_feature",
        CaveSurfaceFeature(CaveSurfaceFeatureConfig.CODEC)
    )

    fun init() = Unit

    fun <C : FeatureConfiguration, F : Feature<C>> register(name: String, feature: F): F {
        return BuiltInRegistries.FEATURE.register(id(name), feature)
    }
}