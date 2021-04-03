package com.github.syari.spigot.api.nms

import org.bukkit.Bukkit

/**
 * NMS のバージョン。
 * @since 1.8.0
 */
val NMS_VERSION = Bukkit.getServer()::class.java.`package`.name.substring(23)

/**
 * サーバーのメジャーバージョン。1.16.5 であれば 16 になる。
 * @since 2.3.0
 */
val SERVER_VERSION = NMS_VERSION.split('_')[1].toInt()

/**
 * NMS のクラスを取得する。`%s` が [NMS_VERSION] に置き換わる。
 * ```
 * getNMSClass("net.minecraft.server.%s.NBTTagCompound")
 * getNMSClass("org.bukkit.craftbukkit.%s.inventory.CraftItemStack")
 * ```
 * @since 1.8.0
 */
fun getNMSClass(className: String): Class<*> = Class.forName(className.format(NMS_VERSION))
