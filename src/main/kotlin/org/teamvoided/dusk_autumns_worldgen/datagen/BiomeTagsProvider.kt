package org.teamvoided.dusk_autumns_worldgen.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.BiomeTags
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.Biomes
import org.teamvoided.dusk_autumns_worldgen.data.DuskBiomeTags
import org.teamvoided.dusk_autumns_worldgen.init.worldgen.DuskBiomes
import java.util.concurrent.CompletableFuture

class BiomeTagsProvider(output: FabricDataOutput?, registriesFuture: CompletableFuture<HolderLookup.Provider>?): FabricTagProvider<Biome>(output, RegistryKeys.BIOME, registriesFuture) {
    override fun configure(arg: HolderLookup.Provider?) {
        getOrCreateTagBuilder(DuskBiomeTags.HAS_ERODED_PILLAR)
            .add(Biomes.ERODED_BADLANDS)
            .add(DuskBiomes.ERODED_MUSHROOM_ISLAND)
            .add(DuskBiomes.DEVILS_ROAR)
        getOrCreateTagBuilder(DuskBiomeTags.HAS_FROZEN_VARIANTS)
            .add(Biomes.DEEP_DARK)
            .add(Biomes.DEEP_FROZEN_OCEAN)
        getOrCreateTagBuilder(DuskBiomeTags.HAS_HOT_VARIANTS)
            .add(DuskBiomes.RED_DESERT)


        getOrCreateTagBuilder(BiomeTags.BEACH)
            .add(DuskBiomes.RED_BEACH)
            .add(DuskBiomes.SNOWY_RED_BEACH)
        getOrCreateTagBuilder(BiomeTags.HAS_CLOSER_WATER_FOG)
            .add(DuskBiomes.FROZEN_MANGROVE_SWAMP)
            .add(DuskBiomes.DEVILS_ROAR)
        getOrCreateTagBuilder(BiomeTags.SPAWNS_WHITE_RABBITS)
            .forceAddTag(DuskBiomeTags.HAS_FROZEN_VARIANTS)
        getOrCreateTagBuilder(BiomeTags.SPAWNS_SNOW_FOXES)
            .forceAddTag(DuskBiomeTags.HAS_FROZEN_VARIANTS)
        getOrCreateTagBuilder(BiomeTags.SPAWNS_COLD_TYPED_FROGS)
            .forceAddTag(DuskBiomeTags.HAS_FROZEN_VARIANTS)
        getOrCreateTagBuilder(BiomeTags.SPAWNS_WARM_TYPED_FROGS)
            .forceAddTag(DuskBiomeTags.HAS_HOT_VARIANTS)
    }
}