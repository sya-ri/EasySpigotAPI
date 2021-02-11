# Command

## [Main.kt](src/main/kotlin/com/github/syari/spigot/api/sample/command/Main.kt)
プラグインのメイン処理です。`onEnable` 内で `TeleportCommand::register` を呼び出すことによりコマンドの登録をしています。

```kotlin
class Main : JavaPlugin() {
    companion object {
        // インスタンスを扱いたい場合、この書き方だとスマートに書ける
        internal lateinit var plugin: JavaPlugin
    }

    init {
        plugin = this
    }

    override fun onEnable() {
        TeleportCommand.register()
    }
}
```

## [TeleportCommand.kt](src/main/kotlin/com/github/syari/spigot/api/sample/command/TeleportCommand.kt)
コマンドを定義しています。例としてテレポートのコマンドを実装しています。
`JavaPlugin::command` を呼び出すことによりコマンドを作成することができます。

```kotlin
object TeleportCommand {
    fun register() {
        plugin.command("teleport") {
            description = "テレポート系の処理を行う"
            aliases = listOf("tp", "sample-teleport")

            // タブ補完の設定を行える
            tab {
                // 引数が何も入力されていない場合の補完候補
                argument {
                    add("here")
                    addAll(onlinePlayerNames)
                }

                // 最初の引数が here の場合の補完候補
                argument("here **") {
                    addAll(onlinePlayerNames)
                }
            }

            // 実行時の処理を設定できる
            execute {
                when (args.lowerOrNull(0)) {
                    // 0 番目の引数が here だった時の処理
                    "here" -> {
                        // ...
                    }
                    // 0 番目の引数が何も入力されていない時の処理
                    null -> {
                        sender.sendMessage(
                            """
                            §a/$label here [Player...] §7複数のプレイヤーを自分の場所へテレポートさせます
                            §a/$label [Player] §7別のプレイヤーの場所へテレポートします
                            """.trimIndent()
                        )
                    }
                    // here 以外の引数が入力されている時の処理
                    else -> {
                        // ...
                    }
                }
            }
        }
    }
}
```

## [extension.kt](src/main/kotlin/com/github/syari/spigot/api/sample/command/extension.kt)
拡張関数を定義しています。よく使うものはこのように定義しておくと綺麗に書くことができます。

```kotlin
fun CommandExecuteAction.getPlayer(index: Int): Player? {
    val name = args.getOrNull(index)
    return if (name != null) {
        plugin.server.getPlayer(name)
    } else {
        sender.sendMessage("§cプレイヤー名を入力してください")
        null
    }
}

val onlinePlayerNames
    get() = plugin.server.onlinePlayers.map(Player::getName)
```

[<< 戻る](../README.md)