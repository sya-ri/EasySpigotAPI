package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType

/**
 * 文字列型。
 * @see ConfigDataType.String
 * @since 1.3.0
 */
object ConfigStringDataType : ConfigDataType<String> {
    /**
     * データ型の名前。
     * @since 1.3.0
     */
    override val typeName = "String"

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
    ): String? {
        return config.getUnsafe(path, typeName, notFoundError)
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
        value: String?
    ) {
        config.setUnsafe(path, value)
    }
}
