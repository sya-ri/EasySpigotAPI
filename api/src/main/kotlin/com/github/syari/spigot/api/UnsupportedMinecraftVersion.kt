package com.github.syari.spigot.api

/**
 * 非対応のマインクラフトバージョンを羅列する。非対応の場合に使用しても [NoSuchMethodError] 等の例外が投げられてしまう。
 * @since 2.2.0
 */
annotation class UnsupportedMinecraftVersion(vararg val version: Int)
