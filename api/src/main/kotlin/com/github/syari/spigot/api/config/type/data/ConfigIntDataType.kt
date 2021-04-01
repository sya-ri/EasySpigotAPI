package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType

/**
 * 整数型。
 * @see ConfigDataType.Int
 * @since 1.3.0
 */
object ConfigIntDataType : ConfigDataType<Int> {
    /**
     * データ型の名前。
     * @since 1.3.0
     */
    override val typeName = "Int"

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
    ): Int? {
        return config.get(path, ConfigDataType.Number, notFoundError)?.toInt()
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
        value: Int?
    ) {
        config.set(path, ConfigDataType.Number, value)
    }
}
