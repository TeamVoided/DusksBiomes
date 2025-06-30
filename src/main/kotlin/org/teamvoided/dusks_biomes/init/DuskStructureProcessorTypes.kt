package org.teamvoided.dusks_biomes.init

import com.mojang.serialization.MapCodec
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.structure.processor.StructureProcessor
import net.minecraft.structure.processor.StructureProcessorType
import org.teamvoided.dusks_biomes.structure.PaleOakReplacementStructureProcessor

object DuskStructureProcessorTypes {
    val PALE_OAK_REPLACE: StructureProcessorType<PaleOakReplacementStructureProcessor> =
        register("pale_oak_replace", PaleOakReplacementStructureProcessor.CODEC)
    fun init() {}
    private fun <P : StructureProcessor> register(id: String, codec: MapCodec<P>): StructureProcessorType<P> =
        Registry.register(Registries.STRUCTURE_PROCESSOR_TYPE, id, StructureProcessorType { codec })
}