package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType

/**
 * 浮動小数型。
 * @see ConfigDataType.Float
 * @since 1.3.0
 */
object ConfigFloatDataType : ConfigDataType<Float> {
    /**
     * データ型の名前。
     * @since 1.3.0
     */
    override val typeName = "Float"

    /**
     * コンフィグから値を取得する。
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param notFoundError 存在しないデータの場合にエラーを出す
     * @return 取得した値
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
     * コンフィグの値を変更する。
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
        config.set(path, ConfigDataType.Number, value)
    }
}
