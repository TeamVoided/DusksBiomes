package org.teamvoided.dusk_autumns_worldgen.init

import org.teamvoided.dusk_autumns_worldgen.init.worldgen.*

object DuskWorldgen {
    fun init() {
        DuskBiomes.init()
        DuskConfiguredFeatures.init()
        DuskPlacedFeatures.init()
        DuskStructures.init()
        DuskDensityFunction.init()

        VoidFeatures.init()
    }
}