package com.github.syari.spigot.api.string

import org.bukkit.ChatColor

/**
 * [ChatColor.translateAlternateColorCodes] を実行する。`&` を文字コードとして認識する。
 * @return 変換後の文字列
 * @since 1.4.0
 */
fun String.toColor(): String = ChatColor.translateAlternateColorCodes('&', this)

/**
 * [ChatColor.stripColor] を実行する。
 * @return 変換後の文字列
 * @since 1.4.0
 */
fun String.toUncolor(): String = ChatColor.stripColor(this)!!
