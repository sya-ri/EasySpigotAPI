package com.github.syari.spigot.api.sample.uuid

import com.github.syari.spigot.api.event.EventRegister.Companion.registerEvents
import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused")
class Main : JavaPlugin() {
    override fun onEnable() {
        registerEvents(PlayerJoinChecker)
    }
}
