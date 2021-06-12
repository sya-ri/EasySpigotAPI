package com.github.syari.spigot.api.command.tab

import com.github.syari.spigot.api.command.CommandArgument
import com.github.syari.spigot.api.command.CommandTabCompleteAction
import org.bukkit.command.CommandSender

/**
 * 入力済みの引数のみで補完内容を決める。
 * @param requires 入力済み引数の中で一致したものが存在していた場合 [action] を実行する。`*` や `**` といったワイルドカードを利用できる。
 * @param action 補完する内容を決める処理
 * @see argument
 * @since 1.2.0
 */
class CommandTabArgument internal constructor(
    private val requires: List<String>,
    private val action: CommandTabCompleteAction
) : CommandTab {
    companion object {
        /**
         * 入力済みの引数のみで補完内容を決める。
         * @param require 入力済み引数の中で一致したものが存在していた場合 [action] を実行する。`*` や `**` といったワイルドカードを利用できる。
         * @param action 補完する内容を決める処理
         * @see argument
         * @since 1.2.0
         */
        fun CommandTab.Container.argument(
            vararg require: String,
            action: CommandTabCompleteAction
        ) {
            add(CommandTabArgument(require.toList(), action))
        }
    }

    /**
     *　コマンド補完の内容を決定する。
     * @param sender タブ補完者
     * @param args 入力済みの引数
     * @return 補完結果
     * @since 1.2.0
     */
    override fun complete(
        sender: CommandSender,
        args: Array<out String>
    ): Iterable<String> {
        val element = CommandTab.Element(sender, CommandArgument(args.toList())).apply(action)

        fun complete(line: String, transform: (String) -> String = { it }): Iterable<String> {
            return element.filter {
                transform(it).lowercase().startsWith(line)
            }
        }

        return when {
            requires.isNotEmpty() -> requires.flatMap { _require ->
                val requireSplit = _require.split("\\s+".toRegex()).toMutableList()
                val isDoubleWildcard = requireSplit.size <= args.size && requireSplit.last() == "**"
                when {
                    isDoubleWildcard || requireSplit.size == args.lastIndex -> {
                        val require = if (_require.contains('*')) {
                            buildString {
                                if (isDoubleWildcard) {
                                    requireSplit.removeLast()
                                }
                                requireSplit.forEachIndexed { index, it ->
                                    append(if (it == "*") args[index] else it)
                                    append(" ")
                                }
                            }.substringBeforeLast(" ")
                        } else {
                            _require
                        }
                        val argsJoin = args.joinToString(" ").lowercase()
                        when {
                            isDoubleWildcard.not() -> complete(argsJoin) { "$require $it" }
                            argsJoin.startsWith(require) -> complete(args.last().lowercase())
                            else -> listOf()
                        }
                    }
                    else -> listOf()
                }
            }
            args.size == 1 -> complete(args[0].lowercase())
            else -> listOf()
        }
    }
}
