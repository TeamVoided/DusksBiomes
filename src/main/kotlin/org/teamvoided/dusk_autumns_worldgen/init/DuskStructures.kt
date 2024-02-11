package org.teamvoided.dusk_autumns_worldgen.init

import org.teamvoided.dusk_autumns_worldgen.init.structure.DuskStructureFeatures
import org.teamvoided.dusk_autumns_worldgen.init.structure.DuskStructurePools
import org.teamvoided.dusk_autumns_worldgen.init.structure.DuskStructureProcessorLists
import org.teamvoided.dusk_autumns_worldgen.init.structure.DuskStructureSets

object DuskStructures {
    fun init()  {
        DuskStructureFeatures.init()
        DuskStructurePools.init()
        DuskStructureProcessorLists.init()
        DuskStructureSets.init()
    }
}