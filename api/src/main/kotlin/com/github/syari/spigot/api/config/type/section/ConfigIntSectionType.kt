package com.github.syari.spigot.api.config.type.section

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigSectionType

/**
 * 整数型。
 * @see ConfigSectionType.Int
 * @since 1.3.0
 */
object ConfigIntSectionType : ConfigSectionType<Int> {
    /**
     * データ型の名前。
     * @since 1.3.0
     */
    override val typeName = "Int"

    /**
     * @param config [CustomConfig]
     * @param path セクションパス
     * @param value セクションキー
     * @since 1.3.0
     */
    override fun parse(
        config: CustomConfig,
        path: String,
        value: String
    ): Int? {
        return value.toIntOrNull() ?: config.typeMismatchError(path, typeName).run { null }
    }
}
