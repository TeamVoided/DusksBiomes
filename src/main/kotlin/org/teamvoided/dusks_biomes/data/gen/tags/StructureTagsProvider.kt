package org.teamvoided.dusks_biomes.data.gen.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.StructureTags
import net.minecraft.world.gen.structure.Structure
import org.teamvoided.dusks_biomes.data.structure.DuskStructureFeatures
import java.util.concurrent.CompletableFuture

class StructureTagsProvider(o: FabricDataOutput, r: CompletableFuture<RegistryWrapper.WrapperLookup>) :
    FabricTagProvider<Structure>(o, RegistryKeys.STRUCTURE, r) {


    override fun configure(arg: RegistryWrapper.WrapperLookup) {
        builder(StructureTags.ON_SWAMP_EXPLORER_MAPS)
            .add(DuskStructureFeatures.SWAMP_VILLAGE)
            .add(DuskStructureFeatures.MANGROVE_SWAMP_VILLAGE)
        builder(StructureTags.VILLAGE)
            .add(DuskStructureFeatures.SWAMP_VILLAGE)
            .add(DuskStructureFeatures.MANGROVE_SWAMP_VILLAGE)
    }
}
