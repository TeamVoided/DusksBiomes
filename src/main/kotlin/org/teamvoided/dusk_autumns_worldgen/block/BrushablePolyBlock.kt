package org.teamvoided.dusk_autumns_worldgen.block

import eu.pb4.polymer.blocks.api.BlockModelType
import eu.pb4.polymer.blocks.api.PolymerBlockModel
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils
import eu.pb4.polymer.blocks.api.PolymerTexturedBlock
import eu.pb4.polymer.core.api.block.PolymerBlockUtils
import io.netty.buffer.Unpooled
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.BrushableBlock
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.network.PacketByteBuf
import net.minecraft.network.packet.Packet
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.sound.SoundEvent
import net.minecraft.state.property.Properties.DUSTED
import net.minecraft.util.math.BlockPos
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.id


class BrushablePolyBlock(
    block: Block, brushingSound: SoundEvent, brushingCompleteSound: SoundEvent, settings: Settings
) : BrushableBlock(block, brushingSound, brushingCompleteSound, settings), PolymerTexturedBlock {
    private val states = arrayOf(
        fullBlock("block/suspicious_red_sand_0"),
        fullBlock("block/suspicious_red_sand_1"),
        fullBlock("block/suspicious_red_sand_2"),
        fullBlock("block/suspicious_red_sand_3")
    )

    override fun getPolymerBlock(state: BlockState): Block = Blocks.RED_SAND

    override fun getPolymerBlockState(state: BlockState, player: ServerPlayerEntity): BlockState =
        states[state.get(DUSTED)]!!

    override fun onPolymerBlockSend(state: BlockState, pos: BlockPos.Mutable, player: ServerPlayerEntity) {
        val be = player.world.getBlockEntity(pos.toImmutable())
        if (times < 3) {
            var buf = PacketByteBuf(Unpooled.buffer())
            be!!.toUpdatePacket()?.write(buf)
//            println(buf)

            buf = PacketByteBuf(Unpooled.buffer())
            BlockEntityUpdateS2CPacket.of(be).write(buf)
//            println(buf)

            buf = PacketByteBuf(Unpooled.buffer())
            getBrushableBlockPacket(be, pos).write(buf)
//            println(buf)

            buf = PacketByteBuf(Unpooled.buffer())
            PolymerBlockUtils.createBlockEntityPacket(pos, BlockEntityType.BRUSHABLE_BLOCK, be.toSyncedNbt()).write(buf)
//            println(buf)

//            println(be.toNbt())
//            println(be.toSyncedNbt())
//            println(be.toString())
//            println(be.pos)
//            println(pos)
//            println(be.type)
            times++
        }
        player.networkHandler.send(
            PolymerBlockUtils.createBlockEntityPacket(pos, BlockEntityType.BRUSHABLE_BLOCK, be?.toSyncedNbt())
        )


//        val world = player.world
//        val be = BrushableBlockEntity(pos.toImmutable(),  Blocks.SUSPICIOUS_GRAVEL.defaultState.with(DUSTED, state.get(DUSTED)))
//        world?.addBlockEntity(be)
    }

    fun getBrushableBlockPacket(be: BlockEntity, pos: BlockPos): Packet<*> {
        return PolymerBlockUtils.createBlockEntityPacket(pos, BlockEntityType.BRUSHABLE_BLOCK, be.toNbt())
    }

    fun fullBlock(id: String) =
        PolymerBlockResourceUtils.requestBlock(BlockModelType.FULL_BLOCK, PolymerBlockModel.of(id(id)))

    companion object {
        var times = 4
    }
}