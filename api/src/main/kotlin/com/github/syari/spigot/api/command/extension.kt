package com.github.syari.spigot.api.command

import org.bukkit.command.Command
import org.bukkit.command.CommandMap
import org.bukkit.plugin.java.JavaPlugin

/**
 * コマンドを作成し、登録する。
 * @param label コマンド名 /label
 * @param action [CommandCreator] コマンドの設定を行う
 * @since 1.2.0
 */
fun JavaPlugin.command(label: String, action: CommandCreator.() -> Unit) {
    registerCommand(this, CommandCreator(label).apply(action).create())
}

/**
 * コマンド登録の処理を行う。
 * @param plugin 登録するプラグイン
 * @param command 登録するコマンド
 * @since 1.2.0
 */
private fun registerCommand(plugin: JavaPlugin, command: Command) {
    try {
        val commandMapField = plugin.server.javaClass.getDeclaredField("commandMap")
        commandMapField.isAccessible = true
        val commandMap = commandMapField.get(plugin.server) as CommandMap
        commandMap.register(plugin.name, command)
        commandMapField.isAccessible = false
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}
