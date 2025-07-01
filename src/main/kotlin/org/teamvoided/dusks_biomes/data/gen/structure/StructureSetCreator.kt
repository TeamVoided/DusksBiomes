package org.teamvoided.dusks_biomes.data.gen.structure

import net.minecraft.registry.Registerable
import net.minecraft.registry.RegistryKeys
import net.minecraft.structure.StructureSet

object StructureSetCreator {

    // StructureSets
    fun bootstrap(c: Registerable<StructureSet>) {
        val structures = c.getRegistryLookup(RegistryKeys.STRUCTURE)

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
