package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType
import org.bukkit.potion.PotionEffectType

/**
 * ポーションエフェクトの種類。
 * @see ConfigDataType.PotionEffectType
 * @since 1.5.0
 */
object ConfigPotionEffectTypeDataType : ConfigDataType<PotionEffectType> {
    /**
     * データ型の名前。
     * @since 1.5.0
     */
    override val typeName = "PotionEffectType"

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
    ): PotionEffectType? {
        return config.get(path, ConfigDataType.String, notFoundError)?.let {
            stringToPotionEffectType(it) ?: config.nullError(path, typeName).run { null }
        }
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
        value: PotionEffectType?
    ) {
        config.set(path, ConfigDataType.String, value?.let(::potionEffectTypeToString))
    }

    /**
     * [String] を [PotionEffectType] に変換する。
     * @since 1.5.1.
     */
    fun stringToPotionEffectType(value: String): PotionEffectType? {
        return PotionEffectType.getByName(value)
    }

    /**
     * [PotionEffectType] を [String] に変換する。
     * @since 1.5.1
     */
    fun potionEffectTypeToString(value: PotionEffectType): String {
        return value.name
    }
}
