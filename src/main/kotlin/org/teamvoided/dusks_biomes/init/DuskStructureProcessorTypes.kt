package org.teamvoided.dusks_biomes.init

import com.mojang.serialization.MapCodec
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.structure.processor.StructureProcessor
import net.minecraft.structure.processor.StructureProcessorType
import org.teamvoided.dusks_biomes.DusksBiomesMod.id
import org.teamvoided.dusks_biomes.structure.BlockReplacementStructureProcessor

object DuskStructureProcessorTypes {
    val BLOCK_REPLACE: StructureProcessorType<BlockReplacementStructureProcessor> =
        register("block_replace", BlockReplacementStructureProcessor.CODEC)

    fun init() {}
    private fun <P : StructureProcessor> register(id: String, codec: MapCodec<P>): StructureProcessorType<P> =
        Registry.register(Registries.STRUCTURE_PROCESSOR, id(id), StructureProcessorType { codec })
}