package org.teamvoided.dusk_autumns_worldgen

import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.teamvoided.dusk_autumns_worldgen.util.DebugInfo


@Suppress("unused")
object DuskAutumnsWorldgen {
    const val MODID = "dusk_autumns_worldgen"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(DuskAutumnsWorldgen::class.simpleName)

    fun commonInit() {
        log.info("Hello from Common")
    }

    fun clientInit() {
        log.info("Hello from Client")
        DebugInfo.init()
    }

    fun id(path: String) = Identifier(MODID, path)
}
