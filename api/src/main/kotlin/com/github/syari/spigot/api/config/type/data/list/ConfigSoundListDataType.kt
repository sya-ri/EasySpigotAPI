package com.github.syari.spigot.api.config.type.data.list

import com.github.syari.spigot.api.config.type.ConfigDataType
import com.github.syari.spigot.api.config.type.data.ConfigSoundDataType
import org.bukkit.Sound

/**
 * [ConfigDataType.SoundList]
 * @since 1.5.0
 */
object ConfigSoundListDataType : ConfigEnumListDataType<Sound> {
    /**
     * データ型の名前
     * @since 1.5.0
     */
    override val typeName = "Sound"

    /**
     * 列挙型の要素を名前から取得する。
     * @param name 名前
     * @since 1.5.0
     */
    override fun stringToEnum(name: String) = ConfigSoundDataType.stringToEnum(name)
}
