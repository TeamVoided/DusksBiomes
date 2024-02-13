package org.teamvoided.dusk_autumns_worldgen.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.block.Blocks
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.tag.BlockTags
import org.teamvoided.dusk_autumns_worldgen.data.DuskBlockTags
import org.teamvoided.dusk_autumns_worldgen.init.DuskBlocks
import java.util.concurrent.CompletableFuture

class BlockTagsProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider.BlockTagProvider(output, registriesFuture) {
    override fun configure(arg: HolderLookup.Provider?) {

//VANILLA
        getOrCreateTagBuilder(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
            .add(Blocks.SNOW_BLOCK)
            .add(Blocks.ICE)
        getOrCreateTagBuilder(BlockTags.MOOSHROOMS_SPAWNABLE_ON)
            .add(Blocks.PODZOL)
//Dusk Tags
        getOrCreateTagBuilder(DuskBlockTags.CANE_HYDRATION)
            .add(Blocks.ICE)
            .add(Blocks.FROSTED_ICE)
        getOrCreateTagBuilder(DuskBlockTags.CANE_SUPPORT)
            .forceAddTag(BlockTags.DIRT)
            .forceAddTag(BlockTags.SAND)
            .forceAddTag(BlockTags.WITHER_SUMMON_BASE_BLOCKS)
        getOrCreateTagBuilder(DuskBlockTags.ICE_SPIKE_PLACEABLE_BLOCKS)
            .forceAddTag(DuskBlockTags.ICE_SPIKE_IGNORE_BLOCKS)
            .add(Blocks.SNOW_BLOCK)
            .add(Blocks.PACKED_ICE)
        getOrCreateTagBuilder(DuskBlockTags.ICE_SPIKE_IGNORE_BLOCKS)
            .forceAddTag(BlockTags.REPLACEABLE)
            .add(Blocks.POWDER_SNOW)
            .add(Blocks.ICE)

//SUSPICIOUS
        getOrCreateTagBuilder(BlockTags.SAND)
            .add(DuskBlocks.SUSPICIOUS_RED_SAND)
    }
}