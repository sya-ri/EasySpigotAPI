package com.github.syari.spigot.api.nms

/**
 * `net.minecraft.server.%s.NBTTagCompound` を扱う。
 * @param instance NBTTagCompound のインスタンス
 * @since 1.8.0
 */
class NBTTagCompoundWrapper(override val instance: Any = clazz.getConstructor().newInstance()) : NMSWrapper() {
    companion object : NMSWrapper.Companion {
        /**
         * クラス。
         * @since 1.8.0
         */
        override val clazz = getNMSClass("net.minecraft.server.%s.NBTTagCompound")
    }

    init {
        require(clazz.isInstance(instance)) { "instance must be NBTTagCompound" }
    }
}
