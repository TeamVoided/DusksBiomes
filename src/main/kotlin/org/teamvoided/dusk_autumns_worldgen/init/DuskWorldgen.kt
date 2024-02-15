package org.teamvoided.dusk_autumns_worldgen.init

import org.teamvoided.dusk_autumns_worldgen.init.worldgen.DuskBiomes
import org.teamvoided.dusk_autumns_worldgen.init.worldgen.DuskConfiguredFeatures
import org.teamvoided.dusk_autumns_worldgen.init.worldgen.DuskPlacedFeatures
import org.teamvoided.dusk_autumns_worldgen.init.worldgen.VoidFeatures

object DuskWorldgen {
    fun init() {
        DuskBiomes.init()
        DuskConfiguredFeatures.init()
        DuskPlacedFeatures.init()
        DuskStructures.init()
        VoidFeatures.init()
    }
}