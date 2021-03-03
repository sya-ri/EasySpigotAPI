package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.config.type.ConfigDataType
import org.bukkit.Sound

/**
 * サウンドの種類。
 * @see ConfigDataType.Sound
 * @since 1.5.0
 */
@Deprecated("代わりに ConfigSoundByNameDataType を使用する。v1.8.0 で削除予定。", ReplaceWith("ConfigSoundByNameDataType"))
object ConfigSoundDataType : ConfigEnumDataType<Sound> {
    /**
     * データ型の名前。
     * @since 1.5.0
     */
    override val typeName = ConfigSoundByNameDataType.typeName

    /**
     * 列挙型の要素を名前から取得する。
     * @param name 名前
     * @since 1.5.0
     */
    override fun stringToEnum(name: String) = ConfigSoundByNameDataType.stringToEnum(name)
}
