@file:Suppress("DEPRECATION")

package com.github.syari.spigot.api.config

import com.github.syari.spigot.api.config.def.DefaultConfig
import com.github.syari.spigot.api.config.type.ConfigDataType
import com.github.syari.spigot.api.config.type.ConfigSectionType
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.IOException

/**
 * コンフィグを操作するクラス
 * @param plugin コンフィグがあるプラグイン
 * @param output メッセージの出力先
 * @param file コンフィグファイル
 * @param default デフォルト設定
 * @since 1.3.0
 */
class CustomConfig internal constructor(
    val plugin: JavaPlugin,
    private val output: CommandSender?,
    val file: File,
    default: DefaultConfig?
) {
    /**
     * コンフィグファイルの相対パス。
     * @since 1.3.0
     */
    val filePath = file.toRelativeString(plugin.dataFolder)

    /**
     * [YamlConfiguration] のインスタンス。
     * @since 1.3.0
     */
    var config: YamlConfiguration
        private set

    init {
        val fileNotExists = file.exists().not()
        if (fileNotExists) {
            try {
                file.parentFile?.mkdirs()
                file.createNewFile()
            } catch (ex: IOException) {
                throw IOException("$filePath の作成に失敗しました")
            }
        }
        config = YamlConfiguration.loadConfiguration(file)
        if (fileNotExists) {
            default?.set(this)
        }
    }

    /**
     * [YamlConfiguration.get] を使って値の取得をする。取得できない可能性がある。
     * @param path コンフィグパス
     * @param typeName データ型の名前
     * @param notFoundError 存在しないデータの場合にエラーを出す default: true
     * @return 取得した値
     * @since 1.3.0
     */
    @Deprecated("安全ではありません。get を使ってください。")
    inline fun <reified T> getUnsafe(
        path: String,
        typeName: String,
        notFoundError: Boolean
    ): T? {
        if (config.contains(path)) {
            val value = config.get(path)
            try {
                return value as T
            } catch (ex: ClassCastException) {
                typeMismatchError(path, typeName)
            }
        } else if (notFoundError) {
            notFoundError(path)
        }
        return null
    }

    /**
     * [YamlConfiguration.getList] を使って値の取得をする。取得できない可能性がある。
     * @param path コンフィグパス
     * @param typeName データ型の名前
     * @param notFoundError 存在しないデータの場合にエラーを出す default: true
     * @return 取得した値
     * @since 1.3.0
     */
    @Deprecated("安全ではありません。get を使ってください。")
    inline fun <reified T> getListUnsafe(
        path: String,
        typeName: String,
        notFoundError: Boolean = true
    ): List<T> {
        return mutableListOf<T>().apply {
            if (config.contains(path)) {
                if (config.isList(path)) {
                    getUnsafe<List<*>>(path, typeName, notFoundError)?.forEachIndexed { index, value ->
                        try {
                            add(value as T)
                        } catch (ex: ClassCastException) {
                            typeMismatchError("$path:$index", typeName)
                        }
                    }
                } else {
                    getUnsafe<T>(path, typeName, notFoundError)?.let(::add)
                }
            } else if (notFoundError) {
                notFoundError(path)
            }
        }
    }

    /**
     * [ConfigDataType] を使って値の取得をする。
     * @param path コンフィグパス
     * @param type データタイプ
     * @param notFoundError 存在しないデータの場合にエラーを出す default: true
     * @return 取得した値
     * @since 1.3.0
     */
    fun <T> get(
        path: String,
        type: ConfigDataType<T>,
        notFoundError: Boolean = true
    ): T? {
        return type.get(this, path, notFoundError)
    }

    /**
     * [ConfigDataType] を使って値の取得をするが、存在しない場合はデフォルトの値を利用する。
     * @param path コンフィグパス
     * @param type データタイプ
     * @param default デフォルト値
     * @param notFoundError 存在しないデータの場合にエラーを出す default: true
     * @return 取得した値
     * @since 1.3.0
     */
    @Deprecated("不要な処理が発生する可能性があるので、エルビス演算子を直接使用した方がいい。", ReplaceWith("get(path, type, notFoundError) ?: default"))
    fun <T> get(
        path: String,
        type: ConfigDataType<T>,
        default: T,
        notFoundError: Boolean = true
    ): T {
        return get(path, type, notFoundError) ?: default
    }

    /**
     * セクションを取得する。
     * @param path コンフィグパス
     * @param notFoundError 存在しないデータの場合にエラーを出す default: true
     * @return 取得したセクション
     * @since 1.3.0
     */
    fun section(
        path: String,
        notFoundError: Boolean = true
    ): Set<String>? {
        val section = config.getConfigurationSection(path)?.getKeys(false)
        return section.apply { if (section == null && notFoundError) notFoundError(path) }
    }

    /**
     * セクションを取得し、[ConfigSectionType] で任意の型に変換する。
     * @param path コンフィグパス
     * @param type セクションタイプ
     * @param notFoundError 存在しないデータの場合にエラーを出す default: true
     * @return 取得したセクション
     * @since 1.3.0
     */
    fun <T> section(
        path: String,
        type: ConfigSectionType<T>,
        notFoundError: Boolean = true
    ): List<T>? {
        return section(path, notFoundError)?.mapNotNull { type.parse(this, path, it) }
    }

    /**
     * 存在するコンフィグパスかを取得する。
     * @param path コンフィグパス
     * @return 存在するか
     * @since 1.3.0
     */
    fun contains(path: String) = config.contains(path)

    /**
     * [YamlConfiguration.set] を使って値の変更をする。変更できない可能性がある。
     * @param path コンフィグパス
     * @param value 上書きする値
     * @param save 上書き後に保存する default: false
     * @since 1.3.0
     */
    @Deprecated("安全ではありません。set, setNull を使ってください。")
    fun setUnsafe(
        path: String,
        value: Any?,
        save: Boolean = false
    ) {
        config.set(path, value)
        if (save) save()
    }

    /**
     * [ConfigDataType] を使って値の変更をする。
     * @param path コンフィグパス
     * @param value 上書きする値
     * @param save 上書き後に保存する default: false
     * @since 1.3.0
     */
    fun <T> set(
        path: String,
        type: ConfigDataType<T>,
        value: T?,
        save: Boolean = false
    ) {
        type.set(this, path, value)
        if (save) save()
    }

    /**
     * コンフィグから値を削除する。
     * @param path コンフィグパス
     * @param save 上書き後に保存する default: false
     * @since 1.7.0
     */
    fun setNull(
        path: String,
        save: Boolean = false
    ) {
        setUnsafe(path, null, save)
    }

    /**
     * ファイルの名前を変更する。
     * @param newName 新しい名前
     * @return 変更できたか
     * @since 1.3.0
     */
    fun rename(newName: String): Boolean {
        if (file.list()?.contains(newName) != false) return false
        return try {
            file.renameTo(File(file.parent, newName))
            true
        } catch (ex: SecurityException) {
            false
        } catch (ex: NullPointerException) {
            false
        }
    }

    /**
     * ファイルの変更を保存する。
     * @since 1.3.0
     */
    fun save() {
        if (config.getValues(false).isNotEmpty()) {
            config.save(file)
        } else {
            plugin.logger.info("[$filePath] 中身がないので削除します")
            delete()
        }
    }

    /**
     * コンフィグファイルの再読み込みを行う。
     * @since 2.3.3
     */
    fun reload() {
        config = YamlConfiguration.loadConfiguration(file)
    }

    /**
     * ファイルを削除する。
     * @since 1.3.0
     */
    fun delete() {
        if (file.delete()) {
            plugin.logger.info("[$filePath] 削除に成功しました")
        } else {
            plugin.logger.severe("[$filePath] 削除に失敗しました")
        }
    }

    /**
     * メッセージを出力する。
     * ```
     * Format: "&6[$filePath|$path] &f$message"
     * ```
     * @param path コンフィグパス
     * @param message 本文
     * @since 1.3.4
     */
    fun sendMessage(
        path: String,
        message: String
    ) {
        output?.sendMessage("§6[$filePath $path] §f$message")
    }

    /**
     * エラーを出力する。
     * ```
     * Format: "&6[$filePath|$path] &c$message"
     * ```
     * @param path コンフィグパス
     * @param message 本文
     * @since 1.3.0
     */
    fun sendError(
        path: String,
        message: String
    ) {
        sendMessage(path, "§c$message")
    }

    /**
     * ```
     * Format: "$thing が null です"
     * ```
     * @param path コンフィグパス
     * @param thing データ名
     * @since 1.3.0
     */
    fun nullError(
        path: String,
        thing: String
    ) {
        sendError(path, "$thing が null です")
    }

    /**
     * ```
     * Format: "フォーマットを間違っています"
     * ```
     * @param path コンフィグパス
     * @since 1.3.0
     */
    fun formatMismatchError(path: String) {
        sendError(path, "フォーマットを間違っています")
    }

    /**
     * ```
     * Format: "データタイプが $typeName ではありませんでした"
     * ```
     * @param path コンフィグパス
     * @param typeName データタイプ
     * @since 1.3.0
     */
    fun typeMismatchError(
        path: String,
        typeName: String
    ) {
        sendError(path, "データタイプが $typeName ではありませんでした")
    }

    /**
     * ```
     * Format: "見つかりませんでした"
     * ```
     * @param path コンフィグパス
     * @since 1.3.0
     */
    fun notFoundError(path: String) {
        sendError(path, "見つかりませんでした")
    }
}
