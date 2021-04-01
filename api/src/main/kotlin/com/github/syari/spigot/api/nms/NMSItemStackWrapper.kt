package com.github.syari.spigot.api.nms

import org.bukkit.inventory.ItemStack

/**
 * `net.minecraft.server.%s.ItemStack` を扱う。
 * @param itemStack インスタンスを生成する元のアイテム
 * @since 1.8.0
 */
class NMSItemStackWrapper(val itemStack: ItemStack) : NMSWrapper() {
    companion object : NMSWrapper.Companion {
        /**
         * クラス。
         * @since 1.8.0
         */
        override val clazz = getNMSClass("net.minecraft.server.%s.ItemStack")
    }

    override val instance: Any = CraftItemStackWrapper.clazz.getDeclaredMethod("asNMSCopy", ItemStack::class.java).invoke(null, itemStack)

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
