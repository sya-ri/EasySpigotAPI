package com.github.syari.spigot.api.particle

import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.entity.Player

/**
 * 指定の座標にパーティクルを表示する。
 * @param particle パーティクルの種類
 * @param count パーティクルの量 default: 1
 * @param offsetX X方向の最大誤差 default: 0
 * @param offsetY Y方向の最大誤差 default: 0
 * @param offsetZ Z方向の最大誤差 default: 0
 * @param speed パーティクルの速度 default: 1.0
 * @param data パーティクルデータ [Particle.getDataType] default: null
 * @param force クライアント設定に関係なく表示するか default: true
 * @since 2.1.0
 */
fun Location.spawnParticle(
    particle: Particle,
    count: Int = 1,
    offsetX: Double = 0.0,
    offsetY: Double = 0.0,
    offsetZ: Double = 0.0,
    speed: Double = 1.0,
    data: Any? = null,
    force: Boolean = true
) = world?.spawnParticle(particle, this, count, offsetX, offsetY, offsetZ, speed, data, force)

/**
 * 指定の座標にパーティクルを表示する。
 * @param particle パーティクルの種類
 * @param count パーティクルの量 default: 1
 * @param offsetX X方向の最大誤差 default: 0
 * @param offsetY Y方向の最大誤差 default: 0
 * @param offsetZ Z方向の最大誤差 default: 0
 * @param speed パーティクルの速度 default: 1.0
 * @param data パーティクルデータ [Particle.getDataType] default: null
 * @since 2.1.0
 */
fun Player.spawnParticle(
    particle: Particle,
    count: Int = 1,
    offsetX: Double = 0.0,
    offsetY: Double = 0.0,
    offsetZ: Double = 0.0,
    speed: Double = 1.0,
    data: Any? = null
) = spawnParticle(particle, location, count, offsetX, offsetY, offsetZ, speed, data)
