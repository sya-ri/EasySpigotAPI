package com.github.syari.spigot.api.config.type.data.list

import com.github.syari.spigot.api.UnsupportedMinecraftVersion
import com.github.syari.spigot.api.config.type.ConfigDataType
import com.github.syari.spigot.api.config.type.data.ConfigSoundByKeyDataType
import org.bukkit.Sound

/**
 * サウンドの種類のリスト。固有名から取得する。
 * @see ConfigDataType.SoundByKeyList
 * @since 1.6.0
 */
@UnsupportedMinecraftVersion(8, 9, 10, 11, 12, 13, 14, 15)
object ConfigSoundByKeyListDataType : ConfigEnumListDataType<Sound> {
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
    override fun stringToEnum(name: String) = ConfigSoundByKeyDataType.stringToEnum(name)
}
