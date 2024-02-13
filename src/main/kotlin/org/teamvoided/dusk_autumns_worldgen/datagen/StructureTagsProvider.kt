package org.teamvoided.dusk_autumns_worldgen.datagen

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.RegistryKeys
import net.minecraft.world.gen.feature.StructureFeature
import java.util.concurrent.CompletableFuture

class StructureTagsProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider<StructureFeature>(o, RegistryKeys.STRUCTURE_FEATURE, r) {
    override fun configure(arg: HolderLookup.Provider) {

//        getOrCreateTagBuilder(DuskFluidTags.CANE_HYDRATION)
//            .forceAddTag(FluidTags.WATER)
    }
}