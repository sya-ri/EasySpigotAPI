package com.github.syari.spigot.api.config.type.data.list

import com.github.syari.spigot.api.config.type.ConfigDataType
import com.github.syari.spigot.api.config.type.data.ConfigSoundByNameDataType
import org.bukkit.Sound

/**
 * サウンドの種類のリスト。名前から取得する。
 * @see ConfigDataType.SoundByNameList
 * @since 1.6.0
 */
object ConfigSoundByNameListDataType : ConfigEnumListDataType<Sound> {
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
    override fun stringToEnum(name: String) = ConfigSoundByNameDataType.stringToEnum(name)
}
