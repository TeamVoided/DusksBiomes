package org.teamvoided.dusks_biomes.data.gen.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.data.server.tag.StructureTags
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.RegistryKeys
import net.minecraft.world.gen.feature.StructureFeature
import org.teamvoided.dusks_biomes.data.structure.DuskStructureFeatures
import java.util.concurrent.CompletableFuture

class StructureTagsProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider<StructureFeature>(o, RegistryKeys.STRUCTURE_FEATURE, r) {


    override fun configure(arg: HolderLookup.Provider) {
        getOrCreateTagBuilder(StructureTags.ON_SWAMP_EXPLORER_MAPS)
            .add(DuskStructureFeatures.SWAMP_VILLAGE)
            .add(DuskStructureFeatures.MANGROVE_SWAMP_VILLAGE)
        getOrCreateTagBuilder(StructureTags.VILLAGE)
            .add(DuskStructureFeatures.SWAMP_VILLAGE)
            .add(DuskStructureFeatures.MANGROVE_SWAMP_VILLAGE)
    }
}
