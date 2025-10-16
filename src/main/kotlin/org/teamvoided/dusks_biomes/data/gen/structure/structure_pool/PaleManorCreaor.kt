package org.teamvoided.dusks_biomes.data.gen.structure.structure_pool

import com.mojang.datafixers.util.Pair
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.data.worldgen.Pools
import net.minecraft.data.worldgen.ProcessorLists
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList
import org.teamvoided.dusks_biomes.DusksBiomes
import org.teamvoided.dusks_biomes.data.structure.DuskStructurePools
import java.util.function.Function

object PaleManorCreaor {
    fun BootstrapContext<StructureTemplatePool>.generatePaleManor() {
        this.register(
            DuskStructurePools.PALE_MANOR_FOUNDATION,
            listOf(Piece("pale_manor/main").piece(this))
        )
        this.manorRooms()
    }

    private fun BootstrapContext<StructureTemplatePool>.manorRooms() {
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
        var processors: ResourceKey<StructureProcessorList> = ProcessorLists.EMPTY,
        var weight: Int = 1
    ) {
        fun id(it: String): Piece {
            id = it
            return this
        }

        fun clear() {
            id = ""
            processors = ProcessorLists.EMPTY
            weight = 1
        }

        fun incrementing(
            c: BootstrapContext<StructureTemplatePool>,
            count: Int
        ): List<Pair<Function<StructureTemplatePool.Projection, out StructurePoolElement>, Int>> {
            val listRoom =
                arrayOfNulls<Pair<Function<StructureTemplatePool.Projection, out StructurePoolElement>, Int>>(count)

            repeat(count) {
                id = (it + 1).toString()
                listRoom[it] = piece(c)
            }
            return listRoom.asList().requireNoNulls()
        }

        fun piece(c: BootstrapContext<StructureTemplatePool>): Pair<Function<StructureTemplatePool.Projection, out StructurePoolElement>, Int> =
            singleStructure(
                "$prefix$id",
                c.lookup(Registries.PROCESSOR_LIST).getOrThrow(processors),
                weight
            )
    }

    private fun id(str: String) = "${DusksBiomes.MODID}:$str"

    private fun singleStructure(
        str: String,
        processors: Holder.Reference<StructureProcessorList>,
        weight: Int = 1
    ): Pair<Function<StructureTemplatePool.Projection, out StructurePoolElement>, Int> =
        Pair(StructurePoolElement.single(id(str), processors), weight)

    fun BootstrapContext<StructureTemplatePool>.register(
        pool: ResourceKey<StructureTemplatePool>,
        pieces: List<Pair<Function<StructureTemplatePool.Projection, out StructurePoolElement>, Int>>
    ): Holder.Reference<StructureTemplatePool>? {
        return this.register(
            pool,
            StructureTemplatePool(
                this.lookup(Registries.TEMPLATE_POOL).getOrThrow(Pools.EMPTY),
                pieces,
                StructureTemplatePool.Projection.RIGID
            )
        )
    }
}