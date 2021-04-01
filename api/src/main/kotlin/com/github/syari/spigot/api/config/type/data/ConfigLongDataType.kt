package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType

/**
 * [ConfigDataType.Int] より大きい整数型。
 * @see ConfigDataType.Long
 * @since 1.3.0
 */
object ConfigLongDataType : ConfigDataType<Long> {
    /**
     * データ型の名前。
     * @since 1.3.0
     */
    override val typeName = "Long"

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
    ): Long? {
        return config.get(path, ConfigDataType.Number, notFoundError)?.toLong()
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
        value: Long?
    ) {
        config.set(path, ConfigDataType.Number, value)
    }
}
