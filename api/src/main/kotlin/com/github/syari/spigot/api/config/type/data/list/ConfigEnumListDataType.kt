package com.github.syari.spigot.api.config.type.data.list

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType

/**
 * 列挙型の要素からリスト形式のデータタイプを作成する為のインターフェース。
 * @since 1.5.0
 */
interface ConfigEnumListDataType<T : Enum<*>> : ConfigDataType<List<T>> {
    /**
     * [String] を 列挙型の要素 に変換する。
     * @param name 名前
     * @return 変換した後の値
     * @since 1.5.0
     */
    fun stringToEnum(name: String): T?

    /**
     * コンフィグから値を取得する。
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param notFoundError 存在しないデータの場合にエラーを出す
     * @return 取得した値
     * @since 1.5.0
     */
    override fun get(
        config: CustomConfig,
        path: String,
        notFoundError: Boolean
    ): List<T> {
        return config.get(path, ConfigDataType.StringList, notFoundError)?.mapNotNull {
            stringToEnum(it) ?: config.nullError("$path.$it", typeName).run { null }
        }.orEmpty()
    }

    /**
     * コンフィグの値を変更する。
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param value 設定する値
     * @since 1.5.0
     */
    override fun set(
        config: CustomConfig,
        path: String,
        value: List<T>?
    ) {
        config.set(path, ConfigDataType.StringList, value?.map(Enum<*>::name))
    }
}
