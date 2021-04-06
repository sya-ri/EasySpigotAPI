package com.github.syari.spigot.api.nms

import org.bukkit.inventory.ItemStack

/**
 * `net.minecraft.server.%s.ItemStack` を扱う。
 * @param instance NMS の ItemStack のインスタンス
 * @since 1.8.0
 */
class NMSItemStackWrapper(override val instance: Any) : NMSWrapper() {
    companion object : NMSWrapper.Companion {
        /**
         * クラス。
         * @since 1.8.0
         */
        override val clazz = getNMSClass("net.minecraft.server.%s.ItemStack")
    }

    /**
     * Bukkit の ItemStack から NMS の ItemStack を操作する。
     * @param itemStack Bukkit の ItemStack
     * @since 1.8.0
     */
    constructor(itemStack: ItemStack) : this(CraftItemStackWrapper.clazz.getDeclaredMethod("asNMSCopy", ItemStack::class.java).invoke(null, itemStack))

    /**
     * [org.bukkit.inventory.ItemStack] から [NBTTagCompoundWrapper] のインスタンスを取得する。
     * @return NBTTagCompound
     * @since 1.8.0
     */
    fun getTag(): NBTTagCompoundWrapper? {
        return clazz.getDeclaredMethod("getTag").invoke(instance)?.let(::NBTTagCompoundWrapper)
    }

    /**
     * [org.bukkit.inventory.ItemStack] の NBTTagCompound を変更する。
     * @param nbtTagCompound NBTTagCompound
     * @since 1.8.0
     */
    fun setTag(nbtTagCompound: NBTTagCompoundWrapper) {
        clazz.getDeclaredMethod("setTag", NBTTagCompoundWrapper.clazz).invoke(instance, nbtTagCompound.instance)
    }

    /**
     * [org.bukkit.inventory.ItemStack] から NBTTagCompound を取得する。存在しない場合は空のインスタンスを作成する
     * @return NBTTagCompound
     * @since 1.8.0
     */
    fun getOrCreateTag(): NBTTagCompoundWrapper {
        return getTag() ?: NBTTagCompoundWrapper().apply(::setTag)
    }
}
