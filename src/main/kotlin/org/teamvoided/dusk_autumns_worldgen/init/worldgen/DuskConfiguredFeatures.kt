package org.teamvoided.dusk_autumns_worldgen.init.worldgen

import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.FeatureConfig
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.id
import org.teamvoided.dusk_autumns_worldgen.worldgen.configured_feature.InvertedSpikeFeature
import org.teamvoided.dusk_autumns_worldgen.worldgen.configured_feature.MonsterRoomFeature
import org.teamvoided.dusk_autumns_worldgen.worldgen.configured_feature.SpikeFeature
import org.teamvoided.dusk_autumns_worldgen.worldgen.configured_feature.StructurePieceFeature
import org.teamvoided.dusk_autumns_worldgen.worldgen.configured_feature.config.MonsterRoomFeatureConfig
import org.teamvoided.dusk_autumns_worldgen.worldgen.configured_feature.config.SpikeFeatureConfig
import org.teamvoided.dusk_autumns_worldgen.worldgen.configured_feature.config.StructurePieceFeatureConfig


@Suppress("HasPlatformType")
object DuskConfiguredFeatures {
    val COBBLESTONE_ROCK = create("cobblestone_rock")
    val TREES_OAK_BIRCH_SPRUCE = create("trees_oak_birch_spruce")
    val TREES_OAK_BIRCH_JUNGLE = create("trees_oak_birch_jungle")
    val MANGROVE_FROZEN_CHECKED = create("mangrove_frozen_checked")
    val TALL_MANGROVE_FROZEN_CHECKED = create("tall_mangrove_frozen_checked")
    val MANGROVE_FROZEN_VEGETATION = create("mangrove_frozen_vegetation")
    val SWAMP_OAK = create("swamp_oak")
    val CHERRY_SNOW = create("cherry_snow")
    val CHERRY_SNOW_BEES = create("cherry_snow_bees")
    val TREES_SNOWY_CHERRY = create("trees_snowy_chery")
    val FLOWER_SNOWY_CHERRY = create("flower_snowy_cherry")
    val ICE_SPIKE_FLOOR = create ("cave/frozen_cavern/ice_spike_floor")
    val ICE_SPIKE_CEILING = create ("cave/frozen_cavern/ice_spike_ceiling")
    val ICE_SPIKE = create("cave/frozen_cavern/ice_spike")
    val INVERTED_ICE_SPIKE = create("cave/frozen_cavern/inverted_ice_spike")
    val BLUE_ICE_SPIKE = create("cave/frozen_cavern/blue_ice_spike")
    val INVERTED_BLUE_ICE_SPIKE = create("cave/frozen_cavern/inverted_blue_ice_spike")
    val ORE_ICE = create("cave/frozen_cavern/ore_ice")
    val ORE_BLUE_ICE = create("cave/frozen_cavern/ore_blue_ice")

    val DEEP_MONSTER_ROOM = create("deep_monster_room")
    val DESERT_WELL = create("desert_well")
    val RED_DESERT_WELL = create("red_desert_well")
    val FOSSIL_COAL = create("fossil_coal")
    val FOSSIL_DIAMOND = create("fossil_diamond")


//Register Configure Feature Types
    val INVERTED_SPIKE = register("inverted_spike", InvertedSpikeFeature(SpikeFeatureConfig.CODEC))
    val SPIKE = register("spike", SpikeFeature(SpikeFeatureConfig.CODEC))
    val MONSTER_ROOM = register("monster_room", MonsterRoomFeature(MonsterRoomFeatureConfig.CODEC))
    val STRUCTURE_PIECE = register("structure_piece", StructurePieceFeature(StructurePieceFeatureConfig.CODEC))



    fun init() {}
    fun create(id: String) = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, id(id))
    private fun <C : FeatureConfig?, F : Feature<C>> register(name: String, feature: F): F {
        return Registry.register(Registries.FEATURE, id(name), feature)
    }
}