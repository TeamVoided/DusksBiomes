package org.teamvoided.reef.utils

import dev.worldgen.lithostitched.worldgen.processor.UnboundReferenceProcessor
import net.minecraft.resources.ResourceLocation
import org.teamvoided.dusks_biomes.mixin.UnboundReferenceProcessorAccessor

fun unbound(id: ResourceLocation): UnboundReferenceProcessor = UnboundReferenceProcessorAccessor.`reef$new`(id)