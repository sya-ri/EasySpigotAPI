package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment

/**
 * [ConfigDataType.EnchantmentByKey]
 * @since 1.5.1
 */
object ConfigEnchantmentByKeyDataType : ConfigDataType<Enchantment> {
    /**
     * データ型の名前
     * @since 1.5.1
     */
    override val typeName = "Enchantment(Key)"

    /**
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param notFoundError 存在しないデータの場合にエラーを出す
     * @since 1.5.1
     */
    override fun get(
        config: CustomConfig,
        path: String,
        notFoundError: Boolean
    ): Enchantment? {
        return config.get(path, ConfigDataType.String, notFoundError)?.let {
            Enchantment.getByKey(NamespacedKey.minecraft(it.toLowerCase())) ?: config.nullError(path, typeName).run { null }
        }
    }

    /**
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
        config.set(path, ConfigDataType.String, value?.key?.key)
    }
}
