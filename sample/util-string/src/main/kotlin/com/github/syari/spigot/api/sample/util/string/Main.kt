package com.github.syari.spigot.api.sample.util.string

import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    companion object {
        internal lateinit var plugin: JavaPlugin
    }

    init {
        plugin = this
    }

    override fun onEnable() {
        ToColorCommand.register()
    }
}
