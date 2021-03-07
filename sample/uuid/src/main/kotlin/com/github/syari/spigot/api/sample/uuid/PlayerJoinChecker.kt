package com.github.syari.spigot.api.sample.uuid

import com.github.syari.spigot.api.event.EventRegister
import com.github.syari.spigot.api.event.Events
import com.github.syari.spigot.api.uuid.UUIDPlayer
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerJoinEvent

object PlayerJoinChecker : EventRegister {
    /**
     * [UUIDPlayer] を使うと再ログインしても同一プレイヤーとして認識される
     *
     * 2回目のログイン以降は true と出力される
     */
    private val useUuidPlayer = mutableSetOf<UUIDPlayer>()

    /**
     * [Player] を使うと再ログインすると別のインスタンスになる
     *
     * 2回目のログイン以降も false と出力される
     */
    private val usePlayer = mutableSetOf<Player>()

    override fun Events.register() {
        event<PlayerJoinEvent> {
            val player = it.player
            val uuidPlayer = UUIDPlayer.from(player)
            player.sendMessage(
                """
                UUIDPlayer を使用: ${useUuidPlayer.contains(uuidPlayer)} (${useUuidPlayer.size})
                Player を使用: ${usePlayer.contains(player)} (${usePlayer.size})
                """.trimIndent()
            )
            useUuidPlayer.add(uuidPlayer)
            usePlayer.add(player)
        }
    }
}
