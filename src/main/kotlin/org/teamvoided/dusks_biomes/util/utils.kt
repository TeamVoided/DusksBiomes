package org.teamvoided.dusks_biomes.util

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

fun printHell(holder: Holder<Structure>, structureStart: StructureStart, chunkPos: ChunkPos) {
    if (holder.unwrapKey().get() == OCEAN_RUIN_WARM_RED) {
        log.info("Structure {}", holder.unwrapKey().get().location())
        log.info("Pos {}", chunkPos)
        log.info("BlockPos: ${chunkPos.x * 16} ~ ${chunkPos.z * 16}")
        for (it in structureStart.pieces) {
            log.info("St piece(${it.javaClass.simpleName}): ")
            if (it !is TemplateStructurePiece) continue
            for (processor in it.placeSettings().processors) {
                processor(processor)
            }
        }
    }
}

fun processor(processor: StructureProcessor, depth: Int = 0) {
    log.info("${indent(depth)}- {}", processor.javaClass.getSimpleName())
    if (processor is UnboundReferenceProcessor) {
        log.info("${indent(depth + 1)}- {}", processor.name())
    }

    if (processor is ReferenceStructureProcessor) {
        for (proc in processor.processorLists().flatMap { it.value().list() }) {
            processor(proc, depth + 1)
        }
    }
}

fun indent(depth: Int) = "\t".repeat(depth)