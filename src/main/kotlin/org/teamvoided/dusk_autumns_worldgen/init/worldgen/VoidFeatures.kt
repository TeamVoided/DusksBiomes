package org.teamvoided.dusk_autumns_worldgen.init.worldgen

import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.FeatureConfig
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen
import org.teamvoided.dusk_autumns_worldgen.worldgen.configured_feature.*
import org.teamvoided.dusk_autumns_worldgen.worldgen.configured_feature.config.LargeCavePillarFeatureConfig
import org.teamvoided.dusk_autumns_worldgen.worldgen.configured_feature.config.MonsterRoomFeatureConfig
import org.teamvoided.dusk_autumns_worldgen.worldgen.configured_feature.config.SpikeFeatureConfig
import org.teamvoided.dusk_autumns_worldgen.worldgen.configured_feature.config.StructurePieceFeatureConfig


//This is going to the lib
object VoidFeatures {

    val INVERTED_SPIKE = register("inverted_spike", InvertedSpikeFeature(SpikeFeatureConfig.CODEC))
    val SPIKE = register("spike", SpikeFeature(SpikeFeatureConfig.CODEC))
    val MONSTER_ROOM = register("monster_room", MonsterRoomFeature(MonsterRoomFeatureConfig.CODEC))
    val STRUCTURE_PIECE = register("structure_piece", StructurePieceFeature(StructurePieceFeatureConfig.CODEC))
    val LARGE_CAVE_PILLAR = register("large_cave_pillar", LargeCavePillarFeature(LargeCavePillarFeatureConfig.CODEC))
    fun init() {}
    private fun <C : FeatureConfig?, F : Feature<C>> register(name: String, feature: F): F =
        Registry.register(Registries.FEATURE, DuskAutumnsWorldgen.id(name), feature)
}