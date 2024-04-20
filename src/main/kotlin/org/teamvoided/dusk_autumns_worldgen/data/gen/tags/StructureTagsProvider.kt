package org.teamvoided.dusk_autumns_worldgen.data.gen.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.data.server.tag.StructureTags
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.RegistryKeys
import net.minecraft.world.gen.feature.StructureFeature
import net.minecraft.world.gen.structure.BuiltInStructures
import org.teamvoided.dusk_autumns_worldgen.data.structure.DuskStructureFeatures
import java.util.concurrent.CompletableFuture

class StructureTagsProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider<StructureFeature>(o, RegistryKeys.STRUCTURE_FEATURE, r) {


    override fun configure(arg: HolderLookup.Provider) {

        getOrCreateTagBuilder(StructureTags.EYE_OF_ENDER_LOCATED)
            .add(BuiltInStructures.END_CITY)
        getOrCreateTagBuilder(StructureTags.ON_SWAMP_EXPLORER_MAPS)
            .add(DuskStructureFeatures.SWAMP_VILLAGE)
            .add(DuskStructureFeatures.MANGROVE_SWAMP_VILLAGE)
        getOrCreateTagBuilder(StructureTags.VILLAGE)
            .add(DuskStructureFeatures.SWAMP_VILLAGE)
            .add(DuskStructureFeatures.MANGROVE_SWAMP_VILLAGE)
    }
}