package com.github.syari.spigot.api.sample.event.register

import com.github.syari.spigot.api.event.register.EventRegister.Companion.registerEvents
import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused")
class Main : JavaPlugin() {
    override fun onEnable() {
        registerEvents(EventListener)
    }
}
