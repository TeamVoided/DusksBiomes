package org.teamvoided.dusk_autumns_worldgen.datagen.structure

import net.minecraft.registry.RegistryKeys
import net.minecraft.structure.RandomSpreadStructurePlacement
import net.minecraft.structure.RandomSpreadType
import net.minecraft.world.gen.BootstrapContext
import net.minecraft.world.gen.structure.StructureSet
import org.teamvoided.dusk_autumns_worldgen.init.structure.DuskStructureFeatures
import org.teamvoided.dusk_autumns_worldgen.init.structure.DuskStructureSets

object StructureSetCreator {

    // StructureSets
    fun bootstrap(c: BootstrapContext<StructureSet>) {
        val structures = c.lookup(RegistryKeys.STRUCTURE_FEATURE)
        val biomes = c.lookup(RegistryKeys.BIOME)

        c.register(
            DuskStructureSets.DESERT_RUINS,
            StructureSet(
                listOf(
                    StructureSet.entry(structures.getHolderOrThrow(DuskStructureFeatures.DESERT_RUINS), 1),
                    StructureSet.entry(structures.getHolderOrThrow(DuskStructureFeatures.RED_DESERT_RUINS), 1)
                ),
                RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, 14357617)
            )
        )
    }
}