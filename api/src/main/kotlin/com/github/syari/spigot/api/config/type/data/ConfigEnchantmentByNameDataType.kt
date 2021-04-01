package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType
import org.bukkit.enchantments.Enchantment

/**
 * エンチャント。名前から取得する。
 * @see ConfigDataType.EnchantmentByName
 * @since 1.5.1
 */
@Deprecated("Enchantment::getByName は非推奨です。", ReplaceWith("ConfigEnchantmentByKeyDataType"))
object ConfigEnchantmentByNameDataType : ConfigDataType<Enchantment> {
    /**
     * データ型の名前
     * @since 1.5.1
     */
    override val typeName = "Enchantment(Name)"

    /**
     * コンフィグから値を取得する。
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param notFoundError 存在しないデータの場合にエラーを出す
     * @return 取得した値
     * @since 1.5.1
     */
    override fun get(
        config: CustomConfig,
        path: String,
        notFoundError: Boolean
    ): Enchantment? {
        return config.get(path, ConfigDataType.String, notFoundError)?.let {
            stringToEnchantment(it) ?: config.nullError(path, typeName).run { null }
        }
    }

    /**
     * コンフィグの値を変更する。
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param value 設定する値
     * @since 1.5.1
     */
    override fun set(
        config: CustomConfig,
        path: String,
        value: Enchantment?
    ) {
        config.set(path, ConfigDataType.String, value?.let(::enchantmentToString))
    }

    /**
     * [String] を [Enchantment] に変換する。
     * @since 1.5.1.
     */
    fun stringToEnchantment(value: String): Enchantment? {
        @Suppress("DEPRECATION")
        return Enchantment.getByName(value.toUpperCase())
    }

    /**
     * [Enchantment] を [String] に変換する。
     * @since 1.5.1
     */
    fun enchantmentToString(value: Enchantment): String {
        @Suppress("DEPRECATION")
        return value.name
    }
}
