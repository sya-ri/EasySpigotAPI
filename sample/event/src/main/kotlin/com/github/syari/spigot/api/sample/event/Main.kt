package com.github.syari.spigot.api.sample.event

import com.github.syari.spigot.api.event.EventRegister.Companion.registerEvents
import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused")
class Main : JavaPlugin() {
    override fun onEnable() {
        registerEvents(EventListener)
    }
}
