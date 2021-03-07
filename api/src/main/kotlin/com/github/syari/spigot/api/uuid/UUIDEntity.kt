package com.github.syari.spigot.api.uuid

import org.bukkit.Bukkit
import org.bukkit.entity.Entity
import java.util.UUID

/**
 * [Entity] が持つ [UUID] を操作しやすくしたクラス。
 *
 * 変数に保存する際には [Entity] ではなく [UUID] で保存することを推奨する。
 * @since 1.1.0
 */
data class UUIDEntity(val uniqueId: UUID) : Comparable<UUIDEntity> {
    /**
     * [Entity] として取得する。
     * @since 1.1.0
     */
    val entity: Entity?
        get() = Bukkit.getEntity(uniqueId)

    /**
     * [UUIDEntity.uniqueId] を文字列として取得する。
     * @see [UUID.toString]
     * @since 1.1.0
     */
    override fun toString() = uniqueId.toString()

    /**
     * [UUIDEntity.uniqueId] を比較する。
     * @see [UUID.compareTo]
     * @since 1.1.0
     */
    override fun compareTo(other: UUIDEntity) = uniqueId.compareTo(other.uniqueId)

    /**
     * @see UUIDEntity.from
     */
    companion object {
        /**
         * [Entity] を [UUIDEntity] に変換する。
         */
        fun from(entity: Entity) = UUIDEntity(entity.uniqueId)

        /**
         * [UUID] を表す文字列から [UUIDEntity] に変換する。
         */
        fun from(uniqueId: String) = uuidOrNull(uniqueId)?.let(::UUIDEntity)
    }
}
