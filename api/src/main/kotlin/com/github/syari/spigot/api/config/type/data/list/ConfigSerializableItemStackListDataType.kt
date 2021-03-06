@file:Suppress("DEPRECATION")

package com.github.syari.spigot.api.config.type.data.list

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType
import org.bukkit.inventory.ItemStack

/**
 * アイテムスタックのリスト。シリアライズを使用して保存・読込を行う。
 * @see ConfigDataType.SerializableItemStackList
 * @since 1.6.0
 */
object ConfigSerializableItemStackListDataType : ConfigDataType<List<ItemStack>> {
    /**
     * データ型の名前。
     * @since 1.6.0
     */
    override val typeName = "ItemStack(Serialize)"

    /**
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param notFoundError 存在しないデータの場合にエラーを出す
     * @since 1.6.0
     */
    override fun get(
        config: CustomConfig,
        path: String,
        notFoundError: Boolean
    ): List<ItemStack> {
        return config.getListUnsafe(path, "List<$typeName>", notFoundError)
    }

    /**
     * @param path コンフィグパス
     * @param value 設定する値
     * @since 1.6.0
     */
    override fun set(
        config: CustomConfig,
        path: String,
        value: List<ItemStack>?
    ) {
        config.setUnsafe(path, value)
    }
}
