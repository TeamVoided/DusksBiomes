package org.teamvoided.dusks_biomes.datagen.data.worldgen.lihto

import dev.worldgen.lithostitched.api.worldgen.modifier.WorldgenModifier
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import org.teamvoided.dusks_biomes.data.structure.DuskStructureProcessorLists
import org.teamvoided.dusks_biomes.data.tags.DuskPoolTags
import org.teamvoided.dusks_biomes.data.world.gen.litho.DuskWorldgenModifiers

object WordgenModifiers {

    fun init(c: BootstrapContext<WorldgenModifier>) = c.boostrap()

    fun BootstrapContext<WorldgenModifier>.boostrap() {
        val templatePools = lookup(Registries.TEMPLATE_POOL)
        val processorLists = lookup(Registries.PROCESSOR_LIST)

        register(
            DuskWorldgenModifiers.TRIAL_CHAMBERS_FIX,
            WorldgenModifier.builder()
                .setPoolElementProcessors(
                    templatePools.getOrThrow(DuskPoolTags.TRIAL_CHAIN_FIX),
                    processorLists.getOrThrow(DuskStructureProcessorLists.TRIAL_CHAMBERS_FIX),
                    true
                )
        )
    }
}