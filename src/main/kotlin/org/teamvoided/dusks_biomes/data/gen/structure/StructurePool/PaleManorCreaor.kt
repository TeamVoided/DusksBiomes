package org.teamvoided.dusks_biomes.data.gen.structure.StructurePool

import com.mojang.datafixers.util.Pair
import net.minecraft.registry.Registerable
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.structure.pool.StructurePool
import net.minecraft.structure.pool.StructurePoolElement
import net.minecraft.structure.pool.StructurePools
import net.minecraft.structure.processor.StructureProcessorList
import net.minecraft.structure.processor.StructureProcessorLists
import org.teamvoided.dusks_biomes.DusksBiomesMod
import org.teamvoided.dusks_biomes.data.gen.structure.StructurePool.PaleManorCreaor.manorRooms
import org.teamvoided.dusks_biomes.data.structure.DuskStructurePools
import java.util.function.Function

object PaleManorCreaor {
    fun Registerable<StructurePool>.generatePaleManor() {
        this.register(
            DuskStructurePools.PALE_MANOR_FOUNDATION,
            listOf(Piece("pale_manor/main").piece(this))
        )
        this.manorRooms()
    }

    private fun Registerable<StructurePool>.manorRooms() {
        this.register(
            DuskStructurePools.PALE_MANOR_ATTIC_ROOM,
            listOf(Piece("pale_manor/room/attic/").piece(this))
        )
        this.register(
            DuskStructurePools.PALE_MANOR_ROOM_BEDROOM,
            Piece("pale_manor/room/bedroom/small/").incrementing(this, 8) +
                    Piece("pale_manor/room/bedroom/large/").incrementing(this, 5),
        )
        this.register(
            DuskStructurePools.PALE_MANOR_ROOM_OFFICE,
            Piece("pale_manor/room/office/small/").incrementing(this, 8) +
                    Piece("pale_manor/room/bedroom/large/").incrementing(this, 7),
        )
        this.register(
            DuskStructurePools.PALE_MANOR_ROOM_GARDEN,
            Piece("pale_manor/room/bedroom/small/").incrementing(this, 6) +
                    Piece("pale_manor/room/bedroom/large/").incrementing(this, 5),
        )
        this.register(
            DuskStructurePools.PALE_MANOR_ROOM_CHEST,
            Piece("pale_manor/room/bedroom/small/").incrementing(this, 4) +
                    Piece("pale_manor/room/bedroom/large/").incrementing(this, 3),
        )
        this.register(
            DuskStructurePools.PALE_MANOR_ROOM_MISC,
            Piece("pale_manor/room/bedroom/small/").incrementing(this, 7) +
                    Piece("pale_manor/room/bedroom/large/").incrementing(this, 5),
        )
        this.register(
            DuskStructurePools.PALE_MANOR_ROOM_SECRET,
            Piece("pale_manor/room/bedroom/small/").incrementing(this, 8) +
                    Piece("pale_manor/room/bedroom/large/").incrementing(this, 3),
        )
        this.register(
            DuskStructurePools.PALE_MANOR_ROOM_TRAP,
            Piece("pale_manor/room/bedroom/small/").incrementing(this, 4) +
                    Piece("pale_manor/room/bedroom/large/").incrementing(this, 1),
        )
    }

    private data class Piece(
        var prefix: String,
        var id: String = "",
        var processors: RegistryKey<StructureProcessorList> = StructureProcessorLists.EMPTY,
        var weight: Int = 1
    ) {
        fun id(it: String): Piece {
            id = it
            return this
        }

        fun clear() {
            id = ""
            processors = StructureProcessorLists.EMPTY
            weight = 1
        }

        fun incrementing(
            c: Registerable<StructurePool>,
            count: Int
        ): List<Pair<Function<StructurePool.Projection, out StructurePoolElement>, Int>> {
            val listRoom =
                arrayOfNulls<Pair<Function<StructurePool.Projection, out StructurePoolElement>, Int>>(count)

            repeat(count) {
                id = (it + 1).toString()
                listRoom[it] = piece(c)
            }
            return listRoom.asList().requireNoNulls()
        }

        fun piece(c: Registerable<StructurePool>): Pair<Function<StructurePool.Projection, out StructurePoolElement>, Int> =
            singleStructure(
                "$prefix$id",
                c.getRegistryLookup(RegistryKeys.PROCESSOR_LIST).getOrThrow(processors),
                weight
            )
    }

    private fun id(str: String) = "${DusksBiomesMod.MODID}:$str"

    private fun singleStructure(
        str: String,
        processors: RegistryEntry<StructureProcessorList>,
        weight: Int = 1
    ): Pair<Function<StructurePool.Projection, out StructurePoolElement>, Int> =
        Pair(StructurePoolElement.ofProcessedSingle(id(str), processors), weight)

    fun Registerable<StructurePool>.register(
        pool: RegistryKey<StructurePool>,
        pieces: List<Pair<Function<StructurePool.Projection, out StructurePoolElement>, Int>>
    ): RegistryEntry.Reference<StructurePool>? {
        return this.register(
            pool,
            StructurePool(
                this.getRegistryLookup(RegistryKeys.TEMPLATE_POOL).getOrThrow(StructurePools.EMPTY),
                pieces,
                StructurePool.Projection.RIGID
            )
        )
    }
}