package com.github.syari.spigot.api.sample.util.string

import com.github.syari.spigot.api.command.command
import com.github.syari.spigot.api.sample.util.string.Main.Companion.plugin
import com.github.syari.spigot.api.util.string.toColor
import com.github.syari.spigot.api.util.string.toUncolor

object ToColorCommand {
    fun register() {
        plugin.command("to-color") {
            execute {
                val text = args.joinToString(" ")
                sender.sendMessage("toColor: " + text.toColor())
                sender.sendMessage("toUncolor: " + text.toUncolor())
                sender.sendMessage("toColor -> toUncolor: " + text.toColor().toUncolor())
            }
        }
    }
}
