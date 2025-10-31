package org.teamvoided.dusks_biomes.util

/*
import dev.worldgen.lithostitched.worldgen.processor.ReferenceStructureProcessor
import dev.worldgen.lithostitched.worldgen.processor.UnboundReferenceProcessor
import net.minecraft.core.Holder
import net.minecraft.world.level.ChunkPos
import net.minecraft.world.level.levelgen.structure.Structure
import net.minecraft.world.level.levelgen.structure.StructureStart
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor
import org.teamvoided.dusks_biomes.DusksBiomes.log
import org.teamvoided.dusks_biomes.data.structure.DuskStructureFeatures.OCEAN_RUIN_WARM_RED
import org.teamvoided.dusks_biomes.mixin.debug.CappedProcessorAccessor
import org.teamvoided.reef.util.mixin.StructureRefHolder

fun printHell(holder: Holder<Structure>, structureStart: StructureStart, chunkPos: ChunkPos) {
    if (holder.unwrapKey().get() == OCEAN_RUIN_WARM_RED) {
        log.info("Structure {}", holder.unwrapKey().get().location())
        log.info("Pos {}", chunkPos)
        log.info("BlockPos: ${chunkPos.x * 16} ~ ${chunkPos.z * 16}")
        for (it in structureStart.pieces) {
            val addon = if (it is StructureRefHolder) "(${it.reef_getStructureRef()})" else ""
            log.info("${it.javaClass.simpleName}$addon: ")

            if (it !is TemplateStructurePiece) continue
            for (processor in it.placeSettings().processors) {
                processor(processor, 1)
            }
        }
    }
}

fun printProcHell(id: String, processors: List<StructureProcessor>) {
    log.info("ID {}", id)
    for (processor in processors) {
        processor(processor, 1)
    }
}

fun processor(processor: StructureProcessor, depth: Int) {
    log.info("${indent(depth)}- {}", processor.javaClass.getSimpleName())
    when (processor) {
        is CappedProcessorAccessor -> {
            log.info(
                "${indent(depth + 1)}- Limit: [{}, {}]",
                processor.reef_getLimit().minValue, processor.reef_getLimit().maxValue
            )
            processor(processor.reef_getDelegate(), depth + 1)
        }

        is UnboundReferenceProcessor -> log.info("${indent(depth + 1)}- {}", processor.name())
        is ReferenceStructureProcessor -> {
            val list = processor.processorLists().flatMap { it.value().list() }
            if (list.isEmpty()) log.info("${indent(depth + 1)}- Empy")
            for (proc in list) {
                processor(proc, depth + 1)
            }
        }
    }
}

fun indent(depth: Int) = "\t".repeat(depth)*/
