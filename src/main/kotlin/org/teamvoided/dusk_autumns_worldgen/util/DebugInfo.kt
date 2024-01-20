package org.teamvoided.dusk_autumns_worldgen.util

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.minecraft.client.option.KeyBind
import org.lwjgl.glfw.GLFW
import org.teamvoided.dusk_autumns_worldgen.util.DebugRenderer.addToRenderer
import org.teamvoided.dusk_autumns_worldgen.util.DebugRenderer.getCords

object DebugInfo {
    val debugKey = keybind("custom key", GLFW.GLFW_KEY_R)
    fun init() {
        ClientTickEvents.END_CLIENT_TICK.register { _ ->
            DebugRenderer.clear()
            cords()
            if (debugKey.wasPressed()) DebugRenderer.toggle()
        }
        ClientTickEvents.END_CLIENT_TICK.register { _ -> }

    }

    fun cords() {
        val cords = getCords() ?: return
        "x=${cords.x},y=${cords.y},z=${cords.z}".addToRenderer("Cords")
    }

    fun keybind(translationKey: String, keyCode: Int, category: String = "") =
        KeyBindingHelper.registerKeyBinding(KeyBind(translationKey, keyCode, category))
}