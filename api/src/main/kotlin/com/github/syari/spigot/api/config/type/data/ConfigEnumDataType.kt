package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType

/**
 * @since 1.5.0
 */
interface ConfigEnumDataType<T : Enum<*>> : ConfigDataType<T> {
    /**
     * 列挙型の要素を名前から取得する。
     * @param name 名前
     * @since 1.5.0
     */
    fun stringToEnum(name: String): T?

    /**
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param notFoundError 存在しないデータの場合にエラーを出す
     * @since 1.5.0
     */
    override fun get(
        config: CustomConfig,
        path: String,
        notFoundError: Boolean
    ): T? {
        return config.get(path, ConfigDataType.String, notFoundError)?.let {
            stringToEnum(it) ?: config.nullError(path, typeName).run { null }
        }
    }

    /**
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param value 設定する値
     * @since 1.5.0
     */
    override fun set(
        config: CustomConfig,
        path: String,
        value: T?
    ) {
        config.set(path, ConfigDataType.String, value?.name)
    }
}
