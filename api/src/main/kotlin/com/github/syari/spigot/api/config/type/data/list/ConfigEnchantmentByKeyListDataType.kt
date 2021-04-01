package com.github.syari.spigot.api.config.type.data.list

import com.github.syari.spigot.api.UnsupportedMinecraftVersion
import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType
import com.github.syari.spigot.api.config.type.data.ConfigEnchantmentByKeyDataType.enchantmentToString
import com.github.syari.spigot.api.config.type.data.ConfigEnchantmentByKeyDataType.stringToEnchantment
import org.bukkit.enchantments.Enchantment

/**
 * エンチャントの一覧。固有名から取得する。
 * @see ConfigDataType.EnchantmentByKeyList
 * @since 1.5.1
 */
@UnsupportedMinecraftVersion(8, 9, 10, 11, 12)
object ConfigEnchantmentByKeyListDataType : ConfigDataType<List<Enchantment>> {
    /**
     * データ型の名前。
     * @since 1.5.1
     */
    override val typeName = "Enchantment(Key)"

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
    ): List<Enchantment> {
        return config.get(path, ConfigDataType.StringList, notFoundError)?.mapNotNull {
            stringToEnchantment(it) ?: config.nullError("$path.$it", typeName).run { null }
        }.orEmpty()
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
        value: List<Enchantment>?
    ) {
        config.set(path, ConfigDataType.StringList, value?.map(::enchantmentToString))
    }
}
