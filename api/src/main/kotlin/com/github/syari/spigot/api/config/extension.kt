package com.github.syari.spigot.api.config

import com.github.syari.spigot.api.config.def.DefaultConfig
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

/**
 * `CustomConfig.() -> Unit`
 * @since 2.3.3
 */
typealias CustomConfigAction = CustomConfig.() -> Unit

/**
 * コンフィグをロードする。
 * @param output メッセージの出力先
 * @param file ファイル
 * @param default デフォルト設定
 * @return コンフィグ
 * @since 2.3.5
 */
fun JavaPlugin.config(
    output: CommandSender?,
    file: File,
    default: DefaultConfig? = null
) = CustomConfig(this, output, file, default)

/**
 * コンフィグをロードする。
 * @param output メッセージの出力先
 * @param file ファイル
 * @param default デフォルト設定
 * @param action コンフィグに対して実行する処理
 * @return コンフィグ
 * @since 2.3.5
 */
fun JavaPlugin.config(
    output: CommandSender?,
    file: File,
    default: DefaultConfig? = null,
    action: CustomConfigAction
) = config(output, file, default).apply(action)

/**
 * コンフィグをロードする。
 * @param output メッセージの出力先
 * @param fileName ファイル名
 * @param default デフォルト設定
 * @return コンフィグ
 * @since 1.7.0
 */
fun JavaPlugin.config(
    output: CommandSender?,
    fileName: String,
    default: DefaultConfig? = null
) = config(output, File(dataFolder, fileName), default)

/**
 * コンフィグをロードする。
 * @param output メッセージの出力先
 * @param fileName ファイル名
 * @param default デフォルト設定
 * @param action コンフィグに対して実行する処理
 * @return コンフィグ
 * @since 1.7.0
 */
fun JavaPlugin.config(
    output: CommandSender?,
    fileName: String,
    default: DefaultConfig? = null,
    action: CustomConfigAction
) = config(output, fileName, default).apply(action)

/**
 * フォルダ内のコンフィグを全てロードする。
 * @param output メッセージの出力先
 * @param directory フォルダ
 * @return ファイルパスとコンフィグ
 * @since 2.3.5
 */
fun JavaPlugin.configDirectory(
    output: CommandSender?,
    directory: File
): Map<String, CustomConfig> {
    directory.mkdirs()
    return mutableMapOf<String, CustomConfig>().apply {
        fun File.loadFiles() {
            listFiles()?.forEach { file ->
                if (file.isFile) {
                    CustomConfig(this@configDirectory, output, file, null).run {
                        put(filePath, this)
                    }
                } else if (file.isDirectory) {
                    file.loadFiles()
                }
            }
        }
        directory.loadFiles()
    }
}

/**
 * フォルダ内のコンフィグを全てロードする。
 * @param output メッセージの出力先
 * @param directory フォルダ
 * @param action コンフィグに対して実行する処理
 * @return ファイルパスとコンフィグ
 * @since 2.3.5
 */
fun JavaPlugin.configDirectory(
    output: CommandSender?,
    directory: File,
    action: CustomConfigAction
) = configDirectory(output, directory).onEach { it.value.action() }

/**
 * フォルダ内のコンフィグを全てロードする。
 * @param output メッセージの出力先
 * @param directoryName フォルダ名
 * @return ファイルパスとコンフィグ
 * @since 1.3.0
 */
fun JavaPlugin.configDirectory(
    output: CommandSender?,
    directoryName: String
) = configDirectory(output, dataFolder.resolve(directoryName))

/**
 * フォルダ内のコンフィグを全てロードする。
 * @param output メッセージの出力先
 * @param directoryName フォルダ名
 * @param action コンフィグに対して実行する処理
 * @return ファイルパスとコンフィグ
 * @since 1.3.0
 */
fun JavaPlugin.configDirectory(
    output: CommandSender?,
    directoryName: String,
    action: CustomConfigAction
) = configDirectory(output, directoryName).onEach { it.value.action() }

/**
 * ファイルが存在するか取得する。
 * @param fileName ファイル名
 * @return 存在するか
 * @since 1.5.1
 */
fun JavaPlugin.existsConfig(fileName: String) = dataFolder.resolve(fileName).exists()
