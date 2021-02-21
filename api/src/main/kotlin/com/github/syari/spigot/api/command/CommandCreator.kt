package com.github.syari.spigot.api.command

import com.github.syari.spigot.api.command.execute.CommandExecuteAction
import com.github.syari.spigot.api.command.tab.CommandTab
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

/**
 * コマンドの設定を行うクラス
 * @param label コマンド名
 * @since 1.2.0
 */
class CommandCreator(val label: String) {
    /**
     * コマンドの説明文
     * @since 1.2.0
     */
    var description = ""

    /**
     * コマンドの使い方
     * @since 1.2.0
     */
    var usageMessage = "/"

    /**
     * コマンドの別名
     * @since 1.2.0
     */
    var aliases = listOf<String>()

    /**
     * コマンドの実行権限
     * @since 1.3.4
     */
    var permission: String? = null

    /**
     * 実行権限がない場合のメッセージ
     * @since 1.3.4
     */
    var permissionMessage: String? = null

    private val tabContainer = CommandTab.Container()
    private var executeAction: CommandExecuteAction.() -> Unit = {}

    /**
     * タブ補完の処理を設定する
     * @param action タブ補完した時の処理
     * @since 1.2.0
     */
    fun tab(action: CommandTab.Container.() -> Unit) {
        tabContainer.action()
    }

    /**
     * コマンド実行の処理を設定する
     * @param action コマンドを実行した時の処理
     * @since 1.2.0
     */
    fun execute(action: CommandExecuteAction.() -> Unit) {
        executeAction = action
    }

    /**
     * 設定から [Command] のインスタンスを生成する
     * @since 1.2.0
     */
    fun create(): Command {
        return object : Command(label, description, usageMessage, aliases) {
            init {
                permission = this@CommandCreator.permission
                permissionMessage = this@CommandCreator.permissionMessage
            }

            override fun execute(
                sender: CommandSender,
                commandLabel: String,
                args: Array<out String>
            ): Boolean {
                if (testPermission(sender)) {
                    executeAction(CommandExecuteAction(sender, commandLabel, CommandArgument(args.toList())))
                }
                return true
            }

            override fun tabComplete(
                sender: CommandSender,
                alias: String,
                args: Array<out String>
            ): List<String> {
                return tabContainer.get(sender, args)
            }
        }
    }
}
