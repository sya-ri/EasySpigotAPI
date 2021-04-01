@file:Suppress("DEPRECATION")

package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType

/**
 * 数字。別の数字型に変換して使う。
 * @see ConfigDataType.Number
 * @see ConfigDataType.Int
 * @see ConfigDataType.Long
 * @see ConfigDataType.Float
 * @see ConfigDataType.Double
 * @since 1.3.0
 */
object ConfigNumberDataType : ConfigDataType<Number> {
    /**
     * データ型の名前。
     * @since 1.3.0
     */
    override val typeName = "Number"

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
    ): Number? {
        return config.getUnsafe(path, typeName, notFoundError)
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
        value: Number?
    ) {
        config.setUnsafe(path, value)
    }
}
