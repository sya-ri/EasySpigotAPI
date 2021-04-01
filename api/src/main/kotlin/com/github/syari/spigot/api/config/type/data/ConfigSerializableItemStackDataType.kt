@file:Suppress("DEPRECATION")

package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.config.CustomConfig
import com.github.syari.spigot.api.config.type.ConfigDataType
import org.bukkit.inventory.ItemStack

/**
 * アイテムスタック。シリアライズを使用して保存・読込を行う。
 * @see ConfigDataType.SerializableItemStack
 * @since 1.6.0
 */
object ConfigSerializableItemStackDataType : ConfigDataType<ItemStack> {
    /**
     * データ型の名前。
     * @since 1.6.0
     */
    override val typeName = "ItemStack(Serialize)"

    /**
     * コンフィグから値を取得する。
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param notFoundError 存在しないデータの場合にエラーを出す
     * @return 取得した値
     * @since 1.6.0
     */
    override fun get(
        config: CustomConfig,
        path: String,
        notFoundError: Boolean
    ): ItemStack? {
        return config.getUnsafe(path, typeName, notFoundError)
    }

    /**
     * コンフィグの値を変更する。
     * @param config [CustomConfig]
     * @param path コンフィグパス
     * @param value 設定する値
     * @since 1.6.0
     */
    override fun set(
        config: CustomConfig,
        path: String,
        value: ItemStack?
    ) {
        config.setUnsafe(path, value)
    }
}
