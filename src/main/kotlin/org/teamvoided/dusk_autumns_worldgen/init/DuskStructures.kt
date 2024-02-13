package org.teamvoided.dusk_autumns_worldgen.init

import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier
import net.minecraft.world.gen.feature.StructureFeature
import org.teamvoided.dusk_autumns_worldgen.DuskAutumnsWorldgen.id
import org.teamvoided.dusk_autumns_worldgen.init.structure.DuskStructureFeatures
import org.teamvoided.dusk_autumns_worldgen.init.structure.DuskStructurePools
import org.teamvoided.dusk_autumns_worldgen.init.structure.DuskStructureProcessorLists
import org.teamvoided.dusk_autumns_worldgen.init.structure.DuskStructureSets

object DuskStructures {

    fun init() {
        DuskStructureFeatures.init()
        DuskStructurePools.init()
        DuskStructureProcessorLists.init()
        DuskStructureSets.init()
    }
}