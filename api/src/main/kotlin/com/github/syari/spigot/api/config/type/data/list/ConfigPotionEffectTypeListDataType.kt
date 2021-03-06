package com.github.syari.spigot.api.config.type.data.list

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType
import com.github.syari.spigot.api.config.type.data.ConfigPotionEffectTypeDataType.potionEffectTypeToString
import com.github.syari.spigot.api.config.type.data.ConfigPotionEffectTypeDataType.stringToPotionEffectType
import org.bukkit.potion.PotionEffectType

/**
 * ポーションエフェクトの種類のリスト。
 * @see ConfigDataType.PotionEffectTypeList
 * @since 1.5.0
 */
object ConfigPotionEffectTypeListDataType : ConfigDataType<List<PotionEffectType>> {
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
    ): List<PotionEffectType> {
        return config.get(path, ConfigDataType.StringList, notFoundError)?.mapNotNull {
            stringToPotionEffectType(it) ?: config.nullError("$path.$it", typeName).run { null }
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
        value: List<PotionEffectType>?
    ) {
        config.set(path, ConfigDataType.StringList, value?.map(::potionEffectTypeToString))
    }
}
