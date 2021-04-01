@file:Suppress("DEPRECATION")

package com.github.syari.spigot.api.config.type.data.list

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType

/**
 * 文字列型のリスト。
 * @see ConfigDataType.StringList
 * @since 1.3.0
 */
object ConfigStringListDataType : ConfigDataType<List<String>> {
    /**
     * データ型の名前。
     * @since 1.3.0
     */
    override val typeName = "String"

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
    ): List<String> {
        return config.getListUnsafe(path, "List<$typeName>", notFoundError)
    }

    /**
     * @param path コンフィグパス
     * @param value 設定する値
     * @since 1.3.0
     */
    override fun set(
        config: CustomConfig,
        path: String,
        value: List<String>?
    ) {
        config.setUnsafe(path, value)
    }
}
