# Config

## [Main.kt](src/main/kotlin/com/github/syari/spigot/api/sample/config/Main.kt)
プラグインのメイン処理です。`onEnable` 内で `ConfigLoader::load` を呼び出すことによりコンフィグを読み込んでいます。
また、`/reload-sample-config` コマンドを実行することでも読み込めるようにしています。

```kotlin
class Main : JavaPlugin() {
    companion object {
        internal lateinit var plugin: JavaPlugin
    }

    init {
        plugin = this
    }

    override fun onEnable() {
        ConfigLoader.load(server.consoleSender)
        command("reload-sample-config") {
            execute {
                ConfigLoader.load(sender)
            }
        }
    }
}
```

## [ConfigLoader.kt](src/main/kotlin/com/github/syari/spigot/api/sample/config/ConfigLoader.kt)
コンフィグを読み込んでいます。`JavaPlugin::config` を呼び出すことで単一ファイルのコンフィグを読み込めます。
また、`JavaPlugin::configDirectory` を呼び出すことでフォルダに存在する全てのファイルを読み込めます。
`CustomConfig::get` によって値の取得を行いますが、第２引数で値の種類を決定します。

```kotlin
object ConfigLoader {
    fun load(sender: CommandSender) {
        // 特定のファイルを読み込みます。固有の設定をコンフィグに格納する時に使います。
        plugin.config(sender, "depth1/depth2/config.yml") {
            val stringValue = get("string_value", ConfigDataType.String)
            plugin.server.broadcastMessage("($filePath) string_value: $stringValue")
        }
        // フォルダの中に存在する全てのファイルを読み込みます。
        plugin.configDirectory(sender, "depth1/depth2/depth3") {
            val intValue = get("int_value", ConfigDataType.Int)
            plugin.server.broadcastMessage("($filePath) int_value: $intValue")
        }
    }
}
```

[<< 戻る](../README.md)