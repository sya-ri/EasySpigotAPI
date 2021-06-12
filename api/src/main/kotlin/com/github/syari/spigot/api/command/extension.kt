package com.github.syari.spigot.api.command

import com.github.syari.spigot.api.command.execute.CommandExecuteParameter
import com.github.syari.spigot.api.command.tab.CommandTab
import org.bukkit.command.Command
import org.bukkit.command.CommandMap
import org.bukkit.plugin.java.JavaPlugin

/**
 * `CommandCreator.() -> Unit`
 * @since 2.3.3
 */
typealias CommandCreateAction = CommandCreator.() -> Unit

/**
 * `CommandTab.Container.() -> Unit`
 * @since 2.3.3
 */
typealias CommandTabAction = CommandTab.Container.() -> Unit

/**
 * `CommandTab.Element.() -> Unit`
 * @since 2.3.3
 */
typealias CommandTabCompleteAction = CommandTab.Element.() -> Unit

/**
 * `CommandExecuteParameter.() -> Unit`
 * @since 2.3.3
 */
typealias CommandExecuteAction = CommandExecuteParameter.() -> Unit

/**
 * コマンドを作成し、登録する。
 * @param label コマンド名 /label
 * @param action [CommandCreator] コマンドの設定を行う
 * @since 1.2.0
 */
fun JavaPlugin.command(label: String, action: CommandCreateAction) {
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
