package org.teamvoided.dusks_biomes.mixin;

import dev.worldgen.lithostitched.worldgen.processor.ReferenceStructureProcessor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(ReferenceStructureProcessor.class)
public abstract class ReferenceStructureProcessorMixin extends StructureProcessor {

    @Shadow
    @Final
    private HolderSet<StructureProcessorList> processorLists;

    @Override
    public @NotNull List<StructureTemplate.StructureBlockInfo> finalizeProcessing(ServerLevelAccessor levelAccessor, BlockPos pos, BlockPos pos2, List<StructureTemplate.StructureBlockInfo> list, List<StructureTemplate.StructureBlockInfo> list2, StructurePlaceSettings settings) {
        var procList = list2;

        for(Holder<StructureProcessorList> processorList : this.processorLists) {
            for(StructureProcessor processor : processorList.value().list()) {
                procList = processor.finalizeProcessing(levelAccessor, pos, pos2, list, procList, settings);
            }
        }

        return procList;
    }
}
