package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType
import com.github.syari.spigot.api.util.uuid.uuidOrNull
import java.util.UUID

/**
 * [ConfigDataType.UUID]
 * @since 1.3.4
 */
object ConfigUUIDDataType : ConfigDataType<UUID> {
    /**
     * データ型の名前
     * @since 1.3.4
     */
    override val typeName = "UUID"

    /**
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param notFoundError 存在しないデータの場合にエラーを出す
     * @since 1.3.4
     */
    override fun get(
        config: CustomConfig,
        path: String,
        notFoundError: Boolean
    ): UUID? {
        return config.get(path, ConfigDataType.String, notFoundError)?.let {
            uuidOrNull(it) ?: config.formatMismatchError(path).run { null }
        }
    }

    /**
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param value 設定する値
     * @since 1.3.4
     */
    override fun set(
        config: CustomConfig,
        path: String,
        value: UUID?
    ) {
        config.set(path, ConfigDataType.String, value?.toString())
    }
}
