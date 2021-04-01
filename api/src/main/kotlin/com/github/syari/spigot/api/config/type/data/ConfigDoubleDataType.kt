package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType

/**
 * [ConfigDataType.Float] よりも精度の高い浮動小数型。
 * @see ConfigDataType.Double
 * @since 1.3.0
 */
object ConfigDoubleDataType : ConfigDataType<Double> {
    /**
     * データ型の名前。
     * @since 1.3.0
     */
    override val typeName = "Double"

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
    ): Double? {
        return config.get(path, ConfigDataType.Number, notFoundError)?.toDouble()
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
        value: Double?
    ) {
        config.set(path, ConfigDataType.Number, value)
    }
}
