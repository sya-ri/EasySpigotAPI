package com.github.syari.spigot.api.nms

import org.bukkit.Bukkit

/**
 * NMS のバージョン。
 * @since 1.8.0
 */
val NMS_VERSION = Bukkit.getServer()::class.java.`package`.name.substring(23)

/**
 * NMS のクラスを取得する。`%s` が [NMS_VERSION] に置き換わる。
 * ```
 * getNMSClass("net.minecraft.server.%s.NBTTagCompound")
 * getNMSClass("org.bukkit.craftbukkit.%s.inventory.CraftItemStack")
 * ```
 * @since 1.8.0
 */
fun getNMSClass(className: String): Class<*> = Class.forName(className.format(NMS_VERSION))
