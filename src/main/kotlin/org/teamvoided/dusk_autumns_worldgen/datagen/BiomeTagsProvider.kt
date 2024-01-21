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



        getOrCreateTagBuilder(BiomeTags.BEACH)
    }
}