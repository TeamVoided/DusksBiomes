package org.teamvoided.dusk_autumns_worldgen

import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.teamvoided.dusk_autumns_worldgen.init.DuskBiomes


@Suppress("unused")
object DuskAutumnsWorldgen {
    const val MODID = "dusk_autumns_worldgen"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(DuskAutumnsWorldgen::class.simpleName)

    fun commonInit() {
        log.info("Hello from Common")

        DuskBiomes.init()
    }

    fun clientInit() {
        log.info("Hello from Client")
    }

    fun id(path: String) = Identifier(MODID, path)
}
