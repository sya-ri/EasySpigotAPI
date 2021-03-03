package com.github.syari.spigot.api.sample.util.component

import com.github.syari.spigot.api.command.command
import com.github.syari.spigot.api.util.component.buildTextComponent
import com.github.syari.spigot.api.util.component.clickCopyToClipboard
import com.github.syari.spigot.api.util.component.clickOpenURL
import com.github.syari.spigot.api.util.component.clickRunCommand
import com.github.syari.spigot.api.util.component.clickTypeText
import com.github.syari.spigot.api.util.component.hoverText
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
