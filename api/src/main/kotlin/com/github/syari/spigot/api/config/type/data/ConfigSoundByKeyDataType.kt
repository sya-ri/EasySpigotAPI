package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.UnsupportedMinecraftVersion
import com.github.syari.spigot.api.config.type.ConfigDataType
import org.bukkit.Sound

/**
 * サウンドの種類。固有名から取得する。
 * @see ConfigDataType.SoundByKey
 * @since 1.6.0
 */
@UnsupportedMinecraftVersion(8, 9, 10, 11, 12, 13, 14, 15)
object ConfigSoundByKeyDataType : ConfigEnumDataType<Sound> {
    /**
     * データ型の名前。
     * @since 1.6.0
     */
    override val typeName = "Sound(Key)"

    /**
     * 列挙型の要素を名前から取得する。
     * @param name 名前
     * @since 1.6.0
     */
    override fun stringToEnum(name: String): Sound? {
        val lowerName = name.toLowerCase()
        return Sound.values().firstOrNull { it.key.key == lowerName }
    }
}
