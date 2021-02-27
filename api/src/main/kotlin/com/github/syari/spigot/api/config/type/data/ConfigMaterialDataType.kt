package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.config.type.ConfigDataType
import org.bukkit.Material

/**
 * [ConfigDataType.Material]
 * @since 1.3.0
 */
object ConfigMaterialDataType : ConfigEnumDataType<Material> {
    /**
     * データ型の名前
     * @since 1.3.0
     */
    override val typeName = "Material"

    /**
     * 列挙型の要素を名前から取得する。
     * @param name 名前
     * @since 1.5.0
     */
    override fun stringToEnum(name: String): Material? {
        return Material.getMaterial(name.toUpperCase())
    }
}
