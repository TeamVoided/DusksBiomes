package org.teamvoided.dusk_autumns_worldgen.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.block.Blocks
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.tag.BlockTags
import org.teamvoided.dusk_autumns_worldgen.data.DuskBlockTags
import java.util.concurrent.CompletableFuture

class BlockTagsProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider.BlockTagProvider(output, registriesFuture) {
    override fun configure(arg: HolderLookup.Provider?) {

        getOrCreateTagBuilder(DuskBlockTags.CANE_HYDRATION)
            .add(Blocks.WATER)
            .add(Blocks.ICE)
            .add(Blocks.FROSTED_ICE)
        getOrCreateTagBuilder(DuskBlockTags.CANE_SUPPORT)
            .forceAddTag(BlockTags.DIRT)
            .forceAddTag(BlockTags.SAND)
    }
}