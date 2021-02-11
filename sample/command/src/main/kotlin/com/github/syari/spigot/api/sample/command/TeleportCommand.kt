package com.github.syari.spigot.api.sample.command

import com.github.syari.spigot.api.command.command
import com.github.syari.spigot.api.command.tab.CommandTabArgument.Companion.argument
import com.github.syari.spigot.api.sample.command.Main.Companion.plugin
import org.bukkit.Bukkit
import org.bukkit.entity.Player

object TeleportCommand {
    fun register() {
        plugin.command("teleport") {
            description = "テレポート系の処理を行う"
            aliases = listOf("tp", "sample-teleport")

            // タブ補完の設定を行える
            tab {
                // 引数が何も入力されていない場合の補完候補
                argument {
                    add("here")
                    addAll(onlinePlayerNames)
                }

                // 最初の引数が here の場合の補完候補
                argument("here **") {
                    addAll(onlinePlayerNames)
                }
            }

            // 実行時の処理を設定できる
            execute {
                when (args.lowerOrNull(0)) {
                    // 0 番目の引数が here だった時の処理
                    "here" -> {
                        val player = sender as? Player
                        if (player is Player) {
                            args.subList(1, args.size).mapNotNull { Bukkit.getPlayer(it) }.forEach {
                                it.teleport(player)
                            }
                        } else {
                            sender.sendMessage("§cプレイヤーからのみ実行できるコマンドです")
                        }
                    }
                    // 0 番目の引数が何も入力されていない時の処理
                    null -> {
                        sender.sendMessage(
                            """
                            §a/$label here [Player...] §7複数のプレイヤーを自分の場所へテレポートさせます
                            §a/$label [Player] §7別のプレイヤーの場所へテレポートします
                            """.trimIndent()
                        )
                    }
                    // here 以外の引数が入力されている時の処理
                    else -> {
                        // スマートキャストが機能しないのでキャストする必要がある
                        val player = sender as? Player
                        if (player != null) {
                            val target = getPlayer(0) ?: return@execute
                            player.teleport(target)
                        } else {
                            sender.sendMessage("§cプレイヤーからのみ実行できるコマンドです")
                        }
                    }
                }
            }
        }
    }
}
