package com.github.syari.spigot.api.sample.uuid

import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused")
class Main : JavaPlugin() {
    companion object {
        internal lateinit var plugin: JavaPlugin
    }

    init {
        plugin = this
    }

    override fun onEnable() {
        PlayerJoinChecker.register()
    }
}
