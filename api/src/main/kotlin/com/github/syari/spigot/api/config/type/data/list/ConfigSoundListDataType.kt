package com.github.syari.spigot.api.config.type.data.list

import com.github.syari.spigot.api.config.type.ConfigDataType
import com.github.syari.spigot.api.config.type.data.ConfigSoundByNameDataType
import org.bukkit.Sound

/**
 * サウンドの種類のリスト。
 * @see ConfigDataType.SoundList
 * @since 1.5.0
 */
@Deprecated("代わりに ConfigSoundByNameListDataType を使用する。v1.8.0 で削除予定。", ReplaceWith("ConfigSoundByNameListDataType"))
object ConfigSoundListDataType : ConfigEnumListDataType<Sound> {
    /**
     * データ型の名前。
     * @since 1.5.0
     */
    override val typeName = ConfigSoundByNameListDataType.typeName

    /**
     * 列挙型の要素を名前から取得する。
     * @param name 名前
     * @since 1.5.0
     */
    override fun stringToEnum(name: String) = ConfigSoundByNameDataType.stringToEnum(name)
}
