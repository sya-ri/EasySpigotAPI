package com.github.syari.spigot.api.config.type

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.section.ConfigIntSectionType

/**
 * コンフィグセクションタイプ
 * @param T データ型
 * @since 1.3.0
 */
interface ConfigSectionType<T> {
    /**
     * データ型の名前。
     * @since 1.3.0
     */
    val typeName: String

    /**
     * @param config [CustomConfig]
     * @param path セクションパス
     * @param value セクションキー
     * @since 1.3.0
     */
    fun parse(
        config: CustomConfig,
        path: String,
        value: String
    ): T?

    /**
     * 定義済みのコンフィグセクションタイプの一覧。
     * @since 1.3.0
     */
    companion object {
        /**
         * 整数型。
         * @since 1.3.0
         */
        val Int = ConfigIntSectionType
    }
}
