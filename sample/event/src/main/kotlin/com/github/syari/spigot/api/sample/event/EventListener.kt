package com.github.syari.spigot.api.sample.event

import com.github.syari.spigot.api.event.events
import com.github.syari.spigot.api.sample.event.Main.Companion.plugin
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerQuitEvent

object EventListener {
    fun register() {
        plugin.events {
            // 入退出メッセージを変更する
            event<PlayerJoinEvent> {
                it.joinMessage = ">> Join ${it.player.displayName}"
            }
            event<PlayerQuitEvent> {
                it.quitMessage = ">> Quit ${it.player.displayName}"
            }

            // プレイヤーに対するダメージをキャンセルする
            cancelEventIf<EntityDamageEvent> {
                it.entity is Player
            }

            // OP 以外の移動をキャンセルする
            cancelEventIfNot<PlayerMoveEvent> {
                it.player.isOp
            }
        }
    }
}
