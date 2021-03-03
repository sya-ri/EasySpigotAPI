package com.github.syari.spigot.api.config.type.data.list

import com.github.syari.spigot.api.config.type.ConfigDataType
import com.github.syari.spigot.api.config.type.data.ConfigEntityTypeDataType
import org.bukkit.entity.EntityType

/**
 * エンティティの種類のリスト。
 * @see ConfigDataType.EntityTypeList
 * @since 1.5.0
 */
object ConfigEntityTypeListDataType : ConfigEnumListDataType<EntityType> {
    /**
     * データ型の名前。
     * @since 1.5.0
     */
    override val typeName = "EntityType"

    /**
     * 列挙型の要素を名前から取得する。
     * @param name 名前
     * @since 1.5.0
     */
    override fun stringToEnum(name: String) = ConfigEntityTypeDataType.stringToEnum(name)
}
