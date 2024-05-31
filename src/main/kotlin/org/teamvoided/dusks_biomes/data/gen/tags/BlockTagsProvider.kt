package org.teamvoided.dusks_biomes.data.gen.tags

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags
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
            .add(
                Blocks.COBBLESTONE,
                Blocks.COBBLED_DEEPSLATE,
                Blocks.SNOW_BLOCK,
                Blocks.ICE
            )
        getOrCreateTagBuilder(BlockTags.MOOSHROOMS_SPAWNABLE_ON)
            .add(Blocks.PODZOL)
        getOrCreateTagBuilder(BlockTags.DEAD_BUSH_PLACEABLE_ON)
            .add(Blocks.SANDSTONE, Blocks.RED_SANDSTONE)

//Dusk Tags
        getOrCreateTagBuilder(DuskBlockTags.MUSHROOM_ROOT_PLACEABLE)
            .addOptionalTag(BlockTags.AZALEA_GROWS_ON)
            .add(Blocks.GRAVEL)
        getOrCreateTagBuilder(DuskBlockTags.ICE_ORE_REPLACEABLE)
            .addOptionalTag(BlockTags.BASE_STONE_OVERWORLD)
            .add(
                Blocks.POWDER_SNOW,
                Blocks.SNOW_BLOCK,
                Blocks.PACKED_ICE,
                Blocks.ICE
            )
        getOrCreateTagBuilder(DuskBlockTags.ICE_SPIKE_PLACEABLE_BLOCKS)
            .addOptionalTag(DuskBlockTags.ICE_SPIKE_IGNORE_BLOCKS)
            .add(Blocks.SNOW_BLOCK, Blocks.PACKED_ICE)
        getOrCreateTagBuilder(DuskBlockTags.ICE_SPIKE_IGNORE_BLOCKS)
            .addOptionalTag(BlockTags.REPLACEABLE)
            .add(Blocks.POWDER_SNOW, Blocks.ICE)
        getOrCreateTagBuilder(DuskBlockTags.CAVE_PILLAR_REPLACEABLE)
            .addOptionalTag(BlockTags.REPLACEABLE)
            .addOptionalTag(BlockTags.DIRT)
            .addOptionalTag(BlockTags.CAVE_VINES)
            .addOptionalTag(BlockTags.SAND)
            .addOptionalTag(BlockTags.SNOW)
            .add(Blocks.POWDER_SNOW, Blocks.ICE, Blocks.GRAVEL)
        getOrCreateTagBuilder(DuskBlockTags.CAVE_PILLAR_PLACEABLE)
            .addOptionalTag(BlockTags.BASE_STONE_OVERWORLD)
            .addOptionalTag(ConventionalBlockTags.STONES)
            .addOptionalTag(ConventionalBlockTags.COBBLESTONES)
            .addOptionalTag(ConventionalBlockTags.SANDSTONE_BLOCKS)
            .addOptionalTag(BlockTags.SAND)
            .add(
                Blocks.COBBLESTONE,
                Blocks.COBBLED_DEEPSLATE,
                Blocks.PACKED_ICE,
                Blocks.BLUE_ICE,
                Blocks.SANDSTONE,
                Blocks.RED_SANDSTONE
            )
    }
}
