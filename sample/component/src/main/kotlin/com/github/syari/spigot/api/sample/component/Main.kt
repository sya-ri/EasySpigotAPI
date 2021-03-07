package com.github.syari.spigot.api.sample.component

import com.github.syari.spigot.api.command.command
import com.github.syari.spigot.api.component.buildTextComponent
import com.github.syari.spigot.api.component.clickCopyToClipboard
import com.github.syari.spigot.api.component.clickOpenURL
import com.github.syari.spigot.api.component.clickRunCommand
import com.github.syari.spigot.api.component.clickTypeText
import com.github.syari.spigot.api.component.hoverText
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
        command("show_component") {
            execute {
                sender.spigot().sendMessage(
                    buildTextComponent {
                        append("&dTextComponent")
                        appendLine()
                        appendLine("&aRunCommand", hoverText("&6Click"), clickRunCommand("/say hello"))
                        appendLine("&aTypeText", hoverText("&6Click"), clickTypeText("Hello"))
                        appendLine("&aOpenURL", hoverText("&6Click"), clickOpenURL("https://github.com/sya-ri/EasySpigotAPI"))
                        appendLine("&aCopyToClipboard", hoverText("&6Click"), clickCopyToClipboard("Hello"))
                    }
                )
            }
        }
    }
}
