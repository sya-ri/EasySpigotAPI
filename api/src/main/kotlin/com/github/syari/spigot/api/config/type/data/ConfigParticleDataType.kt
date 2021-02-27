package com.github.syari.spigot.api.config.type.data

import com.github.syari.spigot.api.config.type.ConfigDataType
import org.bukkit.Particle

/**
 * [ConfigDataType.Particle]
 * @since 1.5.0
 */
object ConfigParticleDataType : ConfigEnumDataType<Particle> {
    /**
     * データ型の名前
     * @since 1.5.0
     */
    override val typeName = "Particle"

    /**
     * 列挙型の要素を名前から取得する。
     * @param name 名前
     * @since 1.5.0
     */
    override fun stringToEnum(name: String): Particle? {
        val upperName = name.toUpperCase()
        return Particle.values().firstOrNull { it.name == upperName }
    }
}
