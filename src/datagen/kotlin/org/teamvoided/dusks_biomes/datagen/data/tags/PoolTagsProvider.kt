package org.teamvoided.dusks_biomes.datagen.data.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool
import org.teamvoided.dusks_biomes.DusksBiomes.mc
import org.teamvoided.dusks_biomes.data.tags.DuskPoolTags
import org.teamvoided.dusks_biomes.util.key
import java.util.concurrent.CompletableFuture

class PoolTagsProvider(o: FabricDataOutput, r: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider<StructureTemplatePool>(o, Registries.TEMPLATE_POOL, r) {
    override fun addTags(arg: HolderLookup.Provider) {
        builder(DuskPoolTags.TRIAL_CHAIN_FIX)
            .addAll(
                listOf(
                    "trial_chambers/corridors/addon/middle_upper",
                    "trial_chambers/corridor",
                    "trial_chambers/chamber/addon",
                    "trial_chambers/chamber/assembly"
                ).map { Registries.TEMPLATE_POOL.key(mc(it)) }
            )

    }
}
