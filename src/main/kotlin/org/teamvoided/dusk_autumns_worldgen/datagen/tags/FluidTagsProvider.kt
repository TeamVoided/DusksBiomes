package org.teamvoided.dusk_autumns_worldgen.datagen.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.tag.FluidTags
import org.teamvoided.dusk_autumns_worldgen.data.DuskFluidTags
import java.util.concurrent.CompletableFuture

class FluidTagsProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider.FluidTagProvider(o, r) {
    override fun configure(arg: HolderLookup.Provider) {

        getOrCreateTagBuilder(DuskFluidTags.CANE_HYDRATION)
            .forceAddTag(FluidTags.WATER)
    }
}