package com.github.syari.spigot.api.sample.config

import com.github.syari.spigot.api.command.command
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    companion object {
        internal lateinit var plugin: JavaPlugin
    }

    init {
        plugin = this
    }

    override fun onEnable() {
        ConfigLoader.load(server.consoleSender)
        command("reload-sample-config") {
            execute {
                ConfigLoader.load(sender)
            }
        }
    }
}
