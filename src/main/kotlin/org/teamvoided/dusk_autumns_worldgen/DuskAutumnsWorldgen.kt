package org.teamvoided.dusk_autumns_worldgen

import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils
import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.teamvoided.dusk_autumns_worldgen.init.DuskBlocks
import org.teamvoided.dusk_autumns_worldgen.init.DuskItems
import org.teamvoided.dusk_autumns_worldgen.init.DuskWorldgen
import org.teamvoided.dusk_autumns_worldgen.init.structure.DuskStructurePools
import org.teamvoided.dusk_autumns_worldgen.init.structure.DuskStructureProcessorLists
import org.teamvoided.dusk_autumns_worldgen.init.worldgen.DuskBiomes
import org.teamvoided.dusk_autumns_worldgen.util.DebugInfo


@Suppress("unused")
object DuskAutumnsWorldgen {
    const val MODID = "dusk_autumns_worldgen"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(DuskAutumnsWorldgen::class.simpleName)

    fun commonInit() {
        log.info("Hello from Common")

        PolymerResourcePackUtils.addModAssets(MODID)
        PolymerResourcePackUtils.markAsRequired()

        DuskWorldgen.init()
        DuskBlocks.init()
        DuskItems.init()
    }

    fun clientInit() {
        log.info("Hello from Client")
        DebugInfo.init()
    }

    fun id(path: String) = Identifier(MODID, path)
}
