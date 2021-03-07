package com.github.syari.spigot.api.nms

/**
 * NMS を扱う為の基底クラス。
 * @since 1.8.0
 */
abstract class NMSWrapper {
    /**
     * インスタンス。
     * @since 1.8.0
     */
    abstract val instance: Any

    override fun toString() = instance.toString()

    override fun hashCode() = instance.hashCode()

    override fun equals(other: Any?) = when {
        this === other -> true
        other is NMSWrapper -> instance == other.instance
        else -> false
    }

    /**
     * [NMSWrapper] の Companion に継承するインターフェース。
     * @since 1.8.0
     */
    interface Companion {
        /**
         * クラス。
         * @since 1.8.0
         */
        val clazz: Class<*>
    }
}
