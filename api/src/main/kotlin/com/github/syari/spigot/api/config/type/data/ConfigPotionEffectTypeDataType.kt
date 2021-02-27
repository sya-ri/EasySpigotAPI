package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType
import org.bukkit.potion.PotionEffectType

/**
 * [ConfigDataType.PotionEffectType]
 * @since 1.5.0
 */
object ConfigPotionEffectTypeDataType : ConfigDataType<PotionEffectType> {
    /**
     * データ型の名前
     * @since 1.5.0
     */
    override val typeName = "PotionEffectType"

    /**
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param notFoundError 存在しないデータの場合にエラーを出す
     * @since 1.5.0
     */
    override fun get(
        config: CustomConfig,
        path: String,
        notFoundError: Boolean
    ): PotionEffectType? {
        return config.get(path, ConfigDataType.String, notFoundError)?.let {
            PotionEffectType.getByName(it) ?: config.nullError(path, typeName).run { null }
        }
    }

    /**
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
        config.set(path, ConfigDataType.String, value?.name)
    }
}
