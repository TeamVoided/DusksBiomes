package org.teamvoided.dusk_autumns_worldgen.init

import net.minecraft.block.*
import net.minecraft.block.enums.NoteBlockInstrument
import net.minecraft.block.piston.PistonBehavior
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.sound.SoundEvents
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.id
import org.teamvoided.dusk_autumns_worldgen.block.BrushablePolyBlock

object DuskBlocks {


    val SUSPICIOUS_RED_SAND = register(
        "suspicious_red_sand",
        BrushablePolyBlock(
            Blocks.RED_SAND,
            SoundEvents.ITEM_BRUSH_BRUSHING_SAND,
            SoundEvents.ITEM_BRUSH_BRUSHING_SAND_COMPLETE,
            AbstractBlock.Settings.create().mapColor(MapColor.ORANGE).instrument(NoteBlockInstrument.SNARE)
                .strength(0.25f).sounds(BlockSoundGroup.SUSPICIOUS_SAND).pistonBehavior(PistonBehavior.DESTROY)
        )
    )

    fun init() {}

    private  fun <T : Block> register(id: String, block: T): T = Registry.register(Registries.BLOCK, id(id), block)

}