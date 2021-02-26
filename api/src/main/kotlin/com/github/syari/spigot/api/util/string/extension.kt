package com.github.syari.spigot.api.util.string

import org.bukkit.ChatColor

/**
 * [ChatColor.translateAlternateColorCodes] を実行する。`&` を文字コードとして認識する。
 * @since 1.4.0
 */
fun String.toColor(): String = ChatColor.translateAlternateColorCodes('&', this)

/**
 * [ChatColor.stripColor] を実行する。
 * @since 1.4.0
 */
fun String.toUncolor(): String = ChatColor.stripColor(this)!!
