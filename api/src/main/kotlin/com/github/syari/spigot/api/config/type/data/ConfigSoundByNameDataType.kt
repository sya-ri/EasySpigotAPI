package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.config.type.ConfigDataType
import org.bukkit.Sound

/**
 * サウンドの種類。名前から取得する。
 * @see ConfigDataType.SoundByName
 * @since 1.6.0
 */
object ConfigSoundByNameDataType : ConfigEnumDataType<Sound> {
    /**
     * データ型の名前。
     * @since 1.6.0
     */
    override val typeName = "Sound(Name)"

    /**
     * 列挙型の要素を名前から取得する。
     * @param name 名前
     * @since 1.6.0
     */
    override fun stringToEnum(name: String): Sound? {
        val upperName = name.toUpperCase()
        return Sound.values().firstOrNull { it.name == upperName }
    }
}
