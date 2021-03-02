package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType

/**
 * [ConfigDataType.Float]
 * @since 1.3.0
 */
object ConfigFloatDataType : ConfigDataType<Float> {
    /**
     * データ型の名前。
     * @since 1.3.0
     */
    override val typeName = "Float"

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
    ): Float? {
        return config.get(path, ConfigDataType.Number, notFoundError)?.toFloat()
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
        value: Float?
    ) {
        config.setUnsafe(path, value)
    }
}
