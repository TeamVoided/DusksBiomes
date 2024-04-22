package org.teamvoided.dusk_autumns_worldgen.data.gen.structure

import net.minecraft.registry.BootstrapContext
import net.minecraft.registry.RegistryKeys
import net.minecraft.structure.RandomSpreadStructurePlacement
import net.minecraft.structure.RandomSpreadType
import net.minecraft.world.gen.structure.StructureSet
import org.teamvoided.dusk_autumns_worldgen.data.structure.DuskStructureFeatures
import org.teamvoided.dusk_autumns_worldgen.data.structure.DuskStructureSets

object StructureSetCreator {

    // StructureSets
    fun bootstrap(c: BootstrapContext<StructureSet>) {
        val structures = c.getRegistryLookup(RegistryKeys.STRUCTURE_FEATURE)

//        c.register(
//            DuskStructureSets.DESERT_RUINS,
//            StructureSet(
//                listOf(
//                    StructureSet.entry(structures.getHolderOrThrow(DuskStructureFeatures.DESERT_RUINS), 5),
//                    StructureSet.entry(structures.getHolderOrThrow(DuskStructureFeatures.RED_DESERT_RUINS), 5),
//                    StructureSet.entry(structures.getHolderOrThrow(DuskStructureFeatures.LARGE_DESERT_RUINS), 1),
//                    StructureSet.entry(structures.getHolderOrThrow(DuskStructureFeatures.LARGE_RED_DESERT_RUINS), 1)
//                ),
//                RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, 97015)
//            )
//        )
    }
}