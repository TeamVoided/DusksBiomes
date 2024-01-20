package org.teamvoided.dusk_autumns_worldgen

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.option.KeyBind
import net.minecraft.structure.pool.StructurePool
import net.minecraft.util.Identifier
import org.lwjgl.glfw.GLFW
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.teamvoided.dusk_autumns_worldgen.util.DebugInfo
import org.teamvoided.dusk_autumns_worldgen.util.DebugRenderer


@Suppress("unused")
object DuskAutumnsWorldgen {
    const val MODID = "dusk_autumns_worldgen"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(DuskAutumnsWorldgen::class.simpleName)

    fun commonInit() {
        log.info("Hello from Common")
    }


    val debugKey = keybind("custom key", GLFW.GLFW_KEY_R)

    fun clientInit() {
        log.info("Hello from Client")
        DebugInfo.init()
        ClientTickEvents.END_CLIENT_TICK.register { _ -> if (debugKey.wasPressed()) DebugRenderer.toggle() }
    }

    fun id(path: String) = Identifier(MODID, path)

    fun keybind(translationKey: String, keyCode: Int, category: String = "") =
        KeyBindingHelper.registerKeyBinding(KeyBind(translationKey, keyCode, category))
}
