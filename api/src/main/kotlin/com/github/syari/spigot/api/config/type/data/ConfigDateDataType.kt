package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType
import java.util.Date

/**
 * 日付型。
 * @see ConfigDataType.Date
 * @since 1.3.0
 */
object ConfigDateDataType : ConfigDataType<Date> {
    /**
     * データ型の名前。
     * @since 1.3.0
     */
    override val typeName = "Date"

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
    ): Date? {
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
        value: Date?
    ) {
        config.setUnsafe(path, value)
    }
}
