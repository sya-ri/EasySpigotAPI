package com.github.syari.spigot.api.command.execute

import com.github.syari.spigot.api.command.CommandArgument
import org.bukkit.command.CommandSender

/**
 * コマンド実行の処理で使用するクラス
 * @param sender コマンド実行者
 * @param label コマンド名
 * @param args コマンドの引数
 * @since 1.2.0
 */
class CommandExecuteAction(val sender: CommandSender, val label: String, val args: CommandArgument)
