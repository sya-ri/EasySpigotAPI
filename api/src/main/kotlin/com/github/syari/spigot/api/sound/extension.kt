package com.github.syari.spigot.api.sound

import org.bukkit.Location
import org.bukkit.Sound
import org.bukkit.SoundCategory
import org.bukkit.entity.Player

/**
 * 座標の周囲に音を再生する。
 * @param sound サウンドの種類
 * @param soundCategory サウンドソース default: [SoundCategory.MASTER]
 * @param volume 音量 0~1.0 で変更可能。1.0 より大きい値では、聞こえる範囲が [volume] * 16 ブロックになる。
 * @param pitch ピッチ 0.5~2.0 で変更可能。0.5から1.0までの値が2倍になると1オクターブ高くなる。
 * @since 2.1.0
 */
fun Location.playSound(
    sound: Sound,
    soundCategory: SoundCategory = SoundCategory.MASTER,
    volume: Float = 1F,
    pitch: Float = 1F,
) = world?.playSound(this, sound, soundCategory, volume, pitch)

/**
 * 座標の周囲に音を再生する。
 * @param sound サウンド
 * @param soundCategory サウンドの種類 default: [SoundCategory.MASTER]
 * @param volume 音量 0~1.0 で変更可能。1.0 より大きい値では、聞こえる範囲が [volume] * 16 ブロックになる。
 * @param pitch ピッチ 0.5~2.0 で変更可能。0.5から1.0までの値が2倍になると1オクターブ高くなる。
 * @since 2.1.0
 */
fun Location.playSound(
    sound: String,
    soundCategory: SoundCategory = SoundCategory.MASTER,
    volume: Float = 1F,
    pitch: Float = 1F,
) = world?.playSound(this, sound, soundCategory, volume, pitch)

/**
 * プレイヤーに対してに音を再生する。
 * @param sound サウンド
 * @param soundCategory サウンドの種類 default: [SoundCategory.MASTER]
 * @param volume 音量 0~1.0 で変更可能。1.0 より大きい値では、聞こえる範囲が [volume] * 16 ブロックになる。
 * @param pitch ピッチ 0.5~2.0 で変更可能。0.5から1.0までの値が2倍になると1オクターブ高くなる。
 * @since 2.1.0
 */
fun Player.playSound(
    sound: Sound,
    soundCategory: SoundCategory = SoundCategory.MASTER,
    volume: Float = 1F,
    pitch: Float = 1F,
) = playSound(location, sound, soundCategory, volume, pitch)

/**
 * プレイヤーに対してに音を再生する。
 * @param sound サウンド
 * @param soundCategory サウンドの種類 default: [SoundCategory.MASTER]
 * @param volume 音量 0~1.0 で変更可能。1.0 より大きい値では、聞こえる範囲が [volume] * 16 ブロックになる。
 * @param pitch ピッチ 0.5~2.0 で変更可能。0.5から1.0までの値が2倍になると1オクターブ高くなる。
 * @since 2.1.0
 */
fun Player.playSound(
    sound: String,
    soundCategory: SoundCategory = SoundCategory.MASTER,
    volume: Float = 1F,
    pitch: Float = 1F,
) = playSound(location, sound, soundCategory, volume, pitch)
