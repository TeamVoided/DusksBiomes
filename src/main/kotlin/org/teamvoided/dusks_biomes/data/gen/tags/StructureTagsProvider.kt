package org.teamvoided.dusks_biomes.data.gen.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.tags.StructureTags
import net.minecraft.world.level.levelgen.structure.Structure
import org.teamvoided.dusks_biomes.data.structure.DuskStructureFeatures
import org.teamvoided.reef.data.tags.ReefStructureTags
import java.util.concurrent.CompletableFuture

class StructureTagsProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider<Structure>(o, Registries.STRUCTURE, r) {
    override fun addTags(arg: HolderLookup.Provider) {
        builder(StructureTags.ON_SWAMP_EXPLORER_MAPS)
            .add(DuskStructureFeatures.SWAMP_VILLAGE)
            .add(DuskStructureFeatures.MANGROVE_SWAMP_VILLAGE)
        builder(StructureTags.VILLAGE)
            .add(DuskStructureFeatures.SWAMP_VILLAGE)
            .add(DuskStructureFeatures.MANGROVE_SWAMP_VILLAGE)

        builder(ReefStructureTags.HAS_INJECTED_PROCESSOR_LISTS)
            .add(DuskStructureFeatures.OCEAN_RUIN_WARM_RED)
    }
}
