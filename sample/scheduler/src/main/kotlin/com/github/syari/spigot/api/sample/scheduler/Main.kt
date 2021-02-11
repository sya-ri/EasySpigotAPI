package com.github.syari.spigot.api.sample.scheduler

import com.github.syari.spigot.api.scheduler.runTaskLater
import com.github.syari.spigot.api.scheduler.runTaskTimer
import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused")
class Main : JavaPlugin() {
    override fun onEnable() {
        // 10秒後に実行されます
        runTaskLater(10 * 20) {
            // async: false ... サーバーに同期した処理です
            server.broadcastMessage("プラグインが有効になってから10秒経ちました")
        }

        // 30秒毎に実行されます
        runTaskTimer(30 * 20, async = true) {
            // async: true ... サーバーに同期していない処理です
            server.onlinePlayers.forEach {
                it.giveExpLevels(1)
            }
        }
    }
}
