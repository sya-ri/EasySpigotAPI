package com.github.syari.spigot.api.config

import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

/**
 * コンフィグをロードする。
 * @param output メッセージの出力先
 * @param fileName ファイル名
 * @since 1.3.0
 */
fun JavaPlugin.config(
    output: CommandSender,
    fileName: String,
    default: Map<String, Any> = emptyMap()
): CustomConfig {
    return CustomConfig(this, output, File(dataFolder, fileName), default)
}

/**
 * コンフィグをロードする。
 * @param output メッセージの出力先
 * @param fileName ファイル名
 * @param action コンフィグに対して実行する処理
 * @since 1.3.0
 */
fun JavaPlugin.config(
    output: CommandSender,
    fileName: String,
    default: Map<String, Any> = emptyMap(),
    action: CustomConfig.() -> Unit
): CustomConfig {
    return config(output, fileName, default).apply(action)
}

/**
 * フォルダ内のコンフィグを全てロードする。
 * @param output メッセージの出力先
 * @param directoryName フォルダ名
 * @since 1.3.0
 */
fun JavaPlugin.configDirectory(
    output: CommandSender,
    directoryName: String
): Map<String, CustomConfig> {
    val directory = File(dataFolder, directoryName).apply(File::mkdirs)
    return mutableMapOf<String, CustomConfig>().apply {
        fun File.loadFiles() {
            listFiles()?.forEach { file ->
                if (file.isFile) {
                    CustomConfig(this@configDirectory, output, file).run {
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
 * @param directoryName フォルダ名
 * @param action コンフィグに対して実行する処理
 * @since 1.3.0
 */
fun JavaPlugin.configDirectory(
    output: CommandSender,
    directoryName: String,
    action: CustomConfig.() -> Unit
): Map<String, CustomConfig> {
    return configDirectory(output, directoryName).onEach { it.value.action() }
}

/**
 * ファイルが存在するか取得する。
 * @param fileName ファイル名
 * @since 1.5.1
 */
fun JavaPlugin.existsConfig(fileName: String) = dataFolder.resolve(fileName).exists()
