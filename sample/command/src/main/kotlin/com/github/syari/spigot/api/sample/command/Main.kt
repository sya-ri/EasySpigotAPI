package com.github.syari.spigot.api.sample.command

import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    companion object {
        // インスタンスを扱いたい場合、この書き方だとスマートに書ける
        internal lateinit var plugin: JavaPlugin
    }

    init {
        plugin = this
    }

    override fun onEnable() {
        TeleportCommand.register()
    }
}
