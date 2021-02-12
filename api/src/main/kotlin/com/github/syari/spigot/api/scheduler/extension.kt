package com.github.syari.spigot.api.scheduler

import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scheduler.BukkitTask

/**
 * タスクを実行する。
 * @param async 非同期か default: false
 * @param action 実行する処理
 * @return [BukkitTask]
 * @since 1.2.0
 */
inline fun JavaPlugin.runTask(
    async: Boolean = false,
    crossinline action: BukkitRunnable.() -> Unit
): BukkitTask {
    val runnable = object : BukkitRunnable() {
        override fun run() = action()
    }
    return if (async) {
        runnable.runTaskAsynchronously(this)
    } else {
        runnable.runTask(this)
    }
}

/**
 * 遅らせてタスクを実行する。
 * @param delay 遅らせる時間 tick
 * @param async 非同期か default: false
 * @param action 遅らせて実行する処理
 * @return [BukkitTask]
 * @since 1.2.0
 */
inline fun JavaPlugin.runTaskLater(
    delay: Long,
    async: Boolean = false,
    crossinline action: BukkitRunnable.() -> Unit
): BukkitTask {
    val runnable = object : BukkitRunnable() {
        override fun run() = action()
    }
    return if (async) {
        runnable.runTaskLaterAsynchronously(this, delay)
    } else {
        runnable.runTaskLater(this, delay)
    }
}

/**
 * 繰り返しタスクを実行する。
 * @param period 繰り返し間隔 tick
 * @param delay 遅らせる時間 tick default: 0
 * @param async 非同期か default: false
 * @param action 繰り返し実行する処理
 * @return [BukkitTask]
 * @since 1.2.0
 */
inline fun JavaPlugin.runTaskTimer(
    period: Long,
    delay: Long = 0,
    async: Boolean = false,
    crossinline action: BukkitRunnable.() -> Unit
): BukkitTask {
    val runnable = object : BukkitRunnable() {
        override fun run() = action()
    }
    return if (async) {
        runnable.runTaskTimerAsynchronously(this, delay, period)
    } else {
        runnable.runTaskTimer(this, delay, period)
    }
}
