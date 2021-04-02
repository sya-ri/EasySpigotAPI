package com.github.syari.spigot.api.sample.inventory

import com.github.syari.spigot.api.inventory.CustomInventory
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    companion object {
        internal lateinit var plugin: JavaPlugin
    }

    init {
        plugin = this
    }

    override fun onEnable() {
        CustomInventory.onEnable(this)
        OpenInventoryCommand.register()
    }
}
