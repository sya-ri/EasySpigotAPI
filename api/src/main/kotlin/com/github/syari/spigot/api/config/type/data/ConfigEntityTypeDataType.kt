package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.config.type.ConfigDataType
import org.bukkit.entity.EntityType

/**
 * エンティティの種類。
 * @see ConfigDataType.EntityType
 * @since 1.5.0
 */
object ConfigEntityTypeDataType : ConfigEnumDataType<EntityType> {
    /**
     * データ型の名前。
     * @since 1.5.0
     */
    override val typeName = "EntityType"

    /**
     * 列挙型の要素を名前から取得する。
     * @param name 名前
     * @return 取得した要素
     * @since 1.5.0
     */
    override fun stringToEnum(name: String): EntityType? {
        val upperName = name.uppercase()
        return EntityType.values().firstOrNull { it.name == upperName }
    }
}
