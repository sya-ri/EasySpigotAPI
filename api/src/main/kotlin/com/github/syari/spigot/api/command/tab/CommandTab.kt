package com.github.syari.spigot.api.command.tab

import com.github.syari.spigot.api.command.CommandArgument
import org.bukkit.command.CommandSender

/**
 * コマンド補完の処理を行う基底クラス。
 * @since 1.2.0
 */
interface CommandTab {
    /**
     *　コマンド補完の内容を決定する。
     * @param sender タブ補完者
     * @param args 入力済みの引数
     * @since 1.2.0
     */
    fun complete(sender: CommandSender, args: Array<out String>): Iterable<String>

    /**
     * 補完の内容を保持する。
     * @param sender タブ補完者
     * @param args 入力済みの引数
     * @since 1.2.0
     */
    class Element(val sender: CommandSender, val args: CommandArgument) : LinkedHashSet<String>() {
        /**
         * @since 1.2.0
         */
        @Deprecated("代わりに addAll を使用する。v1.7.0 で削除予定。", ReplaceWith("addAll(value)"))
        fun add(vararg value: String) = addAll(value)

        /**
         * @since 1.5.1
         */
        fun addAll(vararg value: String) = addAll(value)
    }

    /**
     * コマンド補完クラスを格納するクラス。
     * @since 1.2.0
     */
    class Container {
        private val tabList = mutableSetOf<CommandTab>()

        /**
         * コマンド補完クラスを追加する。
         * @since 1.2.0
         */
        fun add(commandTab: CommandTab) {
            tabList.add(commandTab)
        }

        /**
         * コマンド補完を行う。
         * @since 1.2.0
         */
        internal fun get(sender: CommandSender, args: Array<out String>): List<String> {
            return tabList.flatMap { it.complete(sender, args) }.sorted()
        }
    }
}
