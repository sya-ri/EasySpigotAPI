package com.github.syari.spigot.api.util.uuid

import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import java.util.UUID

/**
 * [Player] が持つ [UUID] を操作しやすくしたクラス。
 *
 * 変数に保存する際には [Player] や [OfflinePlayer] ではなく [UUID] で保存することを推奨する。
 * @since 1.1.0
 */
data class UUIDPlayer(val uniqueId: UUID) : Comparable<UUIDPlayer> {
    /**
     * [Player] として取得する。
     * @since 1.1.0
     */
    val player: Player?
        get() = Bukkit.getPlayer(uniqueId)

    /**
     * [OfflinePlayer] として取得する。
     * @since 1.1.0
     */
    val offlinePlayer: OfflinePlayer
        get() = Bukkit.getOfflinePlayer(uniqueId)

    /**
     * [UUIDPlayer.uniqueId] を文字列として取得する。
     * @see [UUID.toString]
     * @since 1.1.0
     */
    override fun toString() = uniqueId.toString()

    /**
     * [UUIDPlayer.uniqueId] を比較する。
     * @see [UUID.compareTo]
     * @since 1.1.0
     */
    override fun compareTo(other: UUIDPlayer) = uniqueId.compareTo(other.uniqueId)

    /**
     * @see UUIDPlayer.from
     */
    companion object {
        /**
         * [OfflinePlayer] を [UUIDPlayer] に変換する。
         */
        fun from(player: OfflinePlayer) = UUIDPlayer(player.uniqueId)

        /**
         * [UUID] を表す文字列から [UUIDPlayer] に変換する。
         */
        fun from(uniqueId: String) = uuidOrNull(uniqueId)?.let(::UUIDPlayer)
    }
}
