package com.github.syari.spigot.api.config.type.data.list

import com.github.syari.spigot.api.config.type.ConfigDataType
import com.github.syari.spigot.api.config.type.data.ConfigParticleDataType
import org.bukkit.Particle

/**
 * パーティクルの種類のリスト。
 * @see ConfigDataType.ParticleList
 * @since 1.5.0
 */
object ConfigParticleListDataType : ConfigEnumListDataType<Particle> {
    /**
     * データ型の名前。
     * @since 1.5.0
     */
    override val typeName = "Particle"

    /**
     * 列挙型の要素を名前から取得する。
     * @param name 名前
     * @since 1.5.0
     */
    override fun stringToEnum(name: String) = ConfigParticleDataType.stringToEnum(name)
}
