package org.teamvoided.dusks_biomes.datagen.data.worldgen.structure

import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.world.level.levelgen.structure.StructureSet

object StructureSetCreator {

    // StructureSets
    fun bootstrap(c: BootstrapContext<StructureSet>) {
        val structures = c.lookup(Registries.STRUCTURE)

//        c.register(
//            DuskStructureSets.DESERT_RUINS,
//            StructureSet(
//                listOf(
//                    StructureSet.entry(structures.getOrThrow(DuskStructureFeatures.DESERT_RUINS), 5),
//                    StructureSet.entry(structures.getOrThrow(DuskStructureFeatures.RED_DESERT_RUINS), 5),
//                    StructureSet.entry(structures.getOrThrow(DuskStructureFeatures.LARGE_DESERT_RUINS), 1),
//                    StructureSet.entry(structures.getOrThrow(DuskStructureFeatures.LARGE_RED_DESERT_RUINS), 1)
//                ),
//                RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, 97015)
//            )
//        )
    }
}
