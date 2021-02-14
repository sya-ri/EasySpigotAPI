package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType

/**
 * [ConfigDataType.Int]
 * @since 1.3.0
 */
object ConfigIntDataType : ConfigDataType<Int> {
    /**
     * データ型の名前
     * @since 1.3.0
     */
    override val typeName = "Int"

    /**
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param notFoundError 存在しないデータの場合にエラーを出す
     * @since 1.3.0
     */
    override fun get(
        config: CustomConfig,
        path: String,
        notFoundError: Boolean
    ): Int? {
        return config.get(path, ConfigDataType.Number, notFoundError)?.toInt()
    }

    /**
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param value 設定する値
     * @since 1.3.0
     */
    override fun set(
        config: CustomConfig,
        path: String,
        value: Int?
    ) {
        config.setUnsafe(path, value)
    }
}
