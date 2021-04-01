package com.github.syari.spigot.api.config.type.data.list

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType
import com.github.syari.spigot.api.uuid.uuidOrNull
import java.util.UUID

/**
 * UUIDのリスト。
 * @see ConfigDataType.UUIDList
 * @since 1.3.4
 */
object ConfigUUIDListDataType : ConfigDataType<List<UUID>> {
    /**
     * データ型の名前。
     * @since 1.3.4
     */
    override val typeName = "UUID"

    /**
     * コンフィグから値を取得する。
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param notFoundError 存在しないデータの場合にエラーを出す
     * @return 取得した値
     * @since 1.3.4
     */
    override fun get(
        config: CustomConfig,
        path: String,
        notFoundError: Boolean
    ): List<UUID> {
        return config.get(path, ConfigDataType.StringList, notFoundError)?.mapNotNull {
            uuidOrNull(it) ?: config.formatMismatchError(path).run { null }
        }.orEmpty()
    }

    /**
     * コンフィグの値を変更する。
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param value 設定する値
     * @since 1.3.4
     */
    override fun set(
        config: CustomConfig,
        path: String,
        value: List<UUID>?
    ) {
        config.set(path, ConfigDataType.StringList, value?.map(UUID::toString))
    }
}
