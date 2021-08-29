package com.github.syari.spigot.api.uuid

import com.github.syari.spigot.api.UnsupportedMinecraftVersion
import org.bukkit.Bukkit
import org.bukkit.entity.Entity
import java.util.UUID

/**
 * [Entity] が持つ [UUID] を操作しやすくしたクラス。
 *
 * 変数に保存する際には [Entity] ではなく [UUID] で保存することを推奨する。
 * @param uniqueId [UUID]
 * @since 1.1.0
 */
@UnsupportedMinecraftVersion(8, 9, 10)
data class UUIDEntity(val uniqueId: UUID) : Comparable<UUIDEntity> {
    /**
     * [Entity] として取得する。
     * @since 1.1.0
     */
    val entity: Entity?
        get() = Bukkit.getEntity(uniqueId)

    /**
     * 同じエンティティであるか
     * @return [Boolean]
     * @since 2.5.0
     */
    fun match(other: UUID) = uniqueId == other

    /**
     * 同じエンティティであるか
     * @return [Boolean]
     * @since 2.5.0
     */
    fun match(other: Entity) = match(other.uniqueId)

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
         * @return [UUIDEntity]
         * @since 2.5.0
         */
        val Entity.uuidEntity
            get() = UUIDEntity(uniqueId)

        /**
         * [Entity] を [UUIDEntity] に変換する。
         * @param entity [Entity]
         * @return [UUIDEntity]
         * @since 1.1.0
         */
        fun from(entity: Entity) = entity.uuidEntity

        /**
         * [UUID] を表す文字列から [UUIDEntity] に変換する。
         * @param uniqueId [UUID] の文字列
         * @return [UUIDEntity]
         * @since 1.1.0
         */
        fun from(uniqueId: String) = uuidOrNull(uniqueId)?.let(::UUIDEntity)
    }
}
