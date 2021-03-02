package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.config.type.ConfigDataType
import org.bukkit.entity.EntityType

/**
 * [ConfigDataType.EntityType]
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
     * @since 1.5.0
     */
    override fun stringToEnum(name: String): EntityType? {
        val upperName = name.toUpperCase()
        return EntityType.values().firstOrNull { it.name == upperName }
    }
}
