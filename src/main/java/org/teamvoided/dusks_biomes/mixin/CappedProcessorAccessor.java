package org.teamvoided.dusks_biomes.mixin;

import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.CappedProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CappedProcessor.class)
public interface CappedProcessorAccessor {

    @Accessor("delegate")
    StructureProcessor reef_getDelegate();
    @Accessor("limit")
    IntProvider reef_getLimit();
}
