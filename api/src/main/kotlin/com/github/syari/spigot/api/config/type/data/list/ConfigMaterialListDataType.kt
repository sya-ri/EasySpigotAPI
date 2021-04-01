package com.github.syari.spigot.api.config.type.data.list

import com.github.syari.spigot.api.config.type.ConfigDataType
import com.github.syari.spigot.api.config.type.data.ConfigMaterialDataType
import org.bukkit.Material

/**
 * マテリアルのリスト。
 * @see ConfigDataType.MaterialList
 * @since 1.3.0
 */
object ConfigMaterialListDataType : ConfigEnumListDataType<Material> {
    /**
     * データ型の名前。
     * @since 1.3.0
     */
    override val typeName = "Material"

    /**
     * 列挙型の要素を名前から取得する。
     * @param name 名前
     * @return 取得した要素
     * @since 1.5.0
     */
    override fun stringToEnum(name: String) = ConfigMaterialDataType.stringToEnum(name)
}
