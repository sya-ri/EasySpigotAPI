package com.github.syari.spigot.api.uuid

import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import java.util.UUID

/**
 * [Player] が持つ [UUID] を操作しやすくしたクラス。
 *
 * 変数に保存する際には [Player] や [OfflinePlayer] ではなく [UUID] で保存することを推奨する。
 * @param uniqueId [UUID]
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
     * 同じプレイヤーであるか
     * @return [Boolean]
     * @since 2.5.0
     */
    fun match(other: UUID) = uniqueId == other

    /**
     * 同じプレイヤーであるか
     * @return [Boolean]
     * @since 2.5.0
     */
    fun match(other: OfflinePlayer) = match(other.uniqueId)

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
         * @return [UUIDPlayer]
         * @since 2.5.0
         */
        val OfflinePlayer.uuidPlayer
            get() = UUIDPlayer(uniqueId)

        /**
         * [OfflinePlayer] を [UUIDPlayer] に変換する。
         * @param player [OfflinePlayer]
         * @return [UUIDPlayer]
         * @since 1.1.0
         */
        fun from(player: OfflinePlayer) = player.uuidPlayer

        /**
         * [UUID] を表す文字列から [UUIDPlayer] に変換する。
         * @param uniqueId [UUID] の文字列
         * @return [UUIDPlayer]
         * @since 1.1.0
         */
        fun from(uniqueId: String) = uuidOrNull(uniqueId)?.let(::UUIDPlayer)
    }
}
