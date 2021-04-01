package com.github.syari.spigot.api.nms

/**
 * `org.bukkit.craftbukkit.%s.inventory.CraftItemStack` を扱う。
 * @param instance CraftItemStack のインスタンス
 * @since 1.8.0
 */
class CraftItemStackWrapper(override val instance: Any) : NMSWrapper() {
    companion object : NMSWrapper.Companion {
        /**
         * クラス。
         * @since 1.8.0
         */
        override val clazz = getNMSClass("org.bukkit.craftbukkit.%s.inventory.CraftItemStack")
    }
}
