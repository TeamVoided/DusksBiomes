package org.teamvoided.dusks_biomes.init

import com.mojang.serialization.MapCodec
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType
import org.teamvoided.dusks_biomes.DusksBiomesMod.id
import org.teamvoided.dusks_biomes.structure.BlockReplacementStructureProcessor

object DuskStructureProcessorTypes {
    val BLOCK_REPLACE: StructureProcessorType<BlockReplacementStructureProcessor> =
        register("block_replace", BlockReplacementStructureProcessor.CODEC)

    fun init() {}
    private fun <P : StructureProcessor> register(id: String, codec: MapCodec<P>): StructureProcessorType<P> =
        Registry.register(BuiltInRegistries.STRUCTURE_PROCESSOR, id(id), StructureProcessorType { codec })
}