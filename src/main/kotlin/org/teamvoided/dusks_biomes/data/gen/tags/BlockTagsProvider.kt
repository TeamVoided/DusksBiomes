package org.teamvoided.dusks_biomes.data.gen.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBlockTags
import net.minecraft.block.Blocks
import net.minecraft.registry.HolderLookup
import net.minecraft.registry.tag.BlockTags
import org.teamvoided.dusks_biomes.data.tags.DuskBlockTags
import java.util.concurrent.CompletableFuture

class BlockTagsProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider.BlockTagProvider(output, registriesFuture) {
    override fun configure(arg: HolderLookup.Provider) {

//VANILLA
        getOrCreateTagBuilder(BlockTags.OVERWORLD_CARVER_REPLACEABLES)
            .add(Blocks.SNOW_BLOCK)
            .add(Blocks.ICE)
        getOrCreateTagBuilder(BlockTags.MOOSHROOMS_SPAWNABLE_ON)
            .add(Blocks.PODZOL)
        getOrCreateTagBuilder(BlockTags.DEAD_BUSH_PLACEABLE_ON)
            .add(Blocks.SANDSTONE)
            .add(Blocks.RED_SANDSTONE)

//Dusk Tags
        getOrCreateTagBuilder(DuskBlockTags.MUSHROOM_ROOT_PLACEABLE)
            .forceAddTag(BlockTags.AZALEA_GROWS_ON)
            .add(Blocks.GRAVEL)
        getOrCreateTagBuilder(DuskBlockTags.ICE_ORE_REPLACEABLE)
            .forceAddTag(BlockTags.BASE_STONE_OVERWORLD)
            .add(Blocks.POWDER_SNOW)
            .add(Blocks.SNOW_BLOCK)
            .add(Blocks.PACKED_ICE)
            .add(Blocks.ICE)
        getOrCreateTagBuilder(DuskBlockTags.ICE_SPIKE_PLACEABLE_BLOCKS)
            .forceAddTag(DuskBlockTags.ICE_SPIKE_IGNORE_BLOCKS)
            .add(Blocks.SNOW_BLOCK)
            .add(Blocks.PACKED_ICE)
        getOrCreateTagBuilder(DuskBlockTags.ICE_SPIKE_IGNORE_BLOCKS)
            .forceAddTag(BlockTags.REPLACEABLE)
            .add(Blocks.POWDER_SNOW)
            .add(Blocks.ICE)
        getOrCreateTagBuilder(DuskBlockTags.CAVE_PILLAR_REPLACEABLE)
            .forceAddTag(BlockTags.REPLACEABLE)
            .forceAddTag(BlockTags.DIRT)
            .forceAddTag(BlockTags.CAVE_VINES)
            .forceAddTag(BlockTags.SAND)
            .forceAddTag(BlockTags.SNOW)
            .add(Blocks.POWDER_SNOW)
            .add(Blocks.ICE)
        getOrCreateTagBuilder(DuskBlockTags.CAVE_PILLAR_PLACEABLE)
            .forceAddTag(BlockTags.BASE_STONE_OVERWORLD)
            .add(Blocks.PACKED_ICE)
            .add(Blocks.BLUE_ICE)
            .addOptionalTag(ConventionalBlockTags.UNCOLORED_SANDSTONE_BLOCKS)
            .add(Blocks.SANDSTONE)
            .add(Blocks.RED_SANDSTONE)
            .forceAddTag(BlockTags.SAND)

    }
}
