package com.github.syari.spigot.api.sample.command

import com.github.syari.spigot.api.command.execute.CommandExecuteAction
import com.github.syari.spigot.api.sample.command.Main.Companion.plugin
import org.bukkit.entity.Player

fun CommandExecuteAction.getPlayer(index: Int): Player? {
    val name = args.getOrNull(index)
    return if (name != null) {
        plugin.server.getPlayer(name)
    } else {
        sender.sendMessage("§cプレイヤー名を入力してください")
        null
    }
}

val onlinePlayerNames
    get() = plugin.server.onlinePlayers.map(Player::getName)
