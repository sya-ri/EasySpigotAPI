package com.github.syari.spigot.api.command.tab

import com.github.syari.spigot.api.command.CommandArgument
import org.bukkit.command.CommandSender

/**
 * 入力済みの引数のみで補完内容を決める
 * @param arg 入力済み引数の中で一致したものが存在していた場合 [action] を実行する。`*` や `**` といったワイルドカードを利用できる。
 * @param action 補完する内容を決める処理
 * @see argument
 * @since 1.2.0
 */
class CommandTabArgument internal constructor(
    private val arg: List<String>,
    private val action: CommandTab.Element.() -> Unit
) : CommandTab {
    companion object {
        /**
         * 入力済みの引数のみで補完内容を決める
         * @param arg 入力済み引数の中で一致したものが存在していた場合 [action] を実行する。`*` や `**` といったワイルドカードを利用できる。
         * @param action 補完する内容を決める処理
         * @see argument
         * @since 1.2.0
         */
        fun CommandTab.Container.argument(
            vararg arg: String,
            action: CommandTab.Element.() -> Unit
        ) {
            add(CommandTabArgument(arg.toList(), action))
        }
    }

    /**
     *　コマンド補完の内容を決定する。
     * @param sender タブ補完者
     * @param args 入力済みの引数
     * @since 1.2.0
     */
    override fun complete(
        sender: CommandSender,
        args: Array<out String>
    ): Iterable<String> {
        val element = CommandTab.Element(sender, CommandArgument(args.toList())).apply(action)
        return when {
            arg.isNotEmpty() -> arg.flatMap { arg ->
                val splitArg = arg.split("\\s+".toRegex())
                if (splitArg.size <= args.size && splitArg.last() == "**") {
                    element
                } else if (splitArg.size == args.lastIndex) {
                    val completed = if (arg.contains('*')) {
                        buildString {
                            splitArg.forEachIndexed { index, word ->
                                append(if (word == "*") args[index] else word)
                            }
                        }.substringBeforeLast(" ")
                    } else {
                        arg
                    }
                    val joinArg = args.joinToString(" ").toLowerCase()
                    element.filter {
                        "$completed $it".toLowerCase().startsWith(joinArg)
                    }
                } else {
                    listOf()
                }
            }
            args.size == 1 -> element
            else -> listOf()
        }
    }
}
