# Sample

## [Command](command)
コマンドを簡単に作成することができます。`plugin.yml` にコマンドを書く必要はありません。

```kotlin
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
        // ...
    }
}
```

## [Config](config)
コンフィグを簡単に読み込むことができます。

```kotlin
plugin.config(sender, "config.yml") {
    val message: String? = get("message", ConfigDataType.String)
    // ...
}

// 複数ファイルにまたがったコンフィグも扱えます。
plugin.configDirectory(sender, "mobs") {
    // id が指定されていなければファイル名を使用します。
    val id: String = get("id", ConfigDataType.String, file.nameWithoutExtension)
    section("attribute")?.forEach { name ->
        val level = get("attribute.$name.level", ConfigDataType.Int, 1)
        // ...
    }
    // ...
}
```

## [Event / Register](event-register)
イベントを簡単に定義・登録することができます。

```kotlin
// event<>{} で定義できる
object EventListener : EventRegister {
    override fun Events.register() {
        // 入退出メッセージを変更する
        event<PlayerJoinEvent> {
            it.joinMessage = ">> Join ${it.player.displayName}"
        }
        event<PlayerQuitEvent> {
            it.quitMessage = ">> Quit ${it.player.displayName}"
        }

        // プレイヤーに対するダメージをキャンセルする
        cancelEventIf<EntityDamageEvent> {
            it.entity is Player
        }

        // OP 以外の移動をキャンセルする
        cancelEventIfNot<PlayerMoveEvent> {
            it.player.isOp
        }
    }
}
```

## [Scheduler](scheduler)
スケジューラを簡単に使うことができます。

```kotlin
// 10秒後に実行されます
runTaskLater(10 * 20) {
    // async: false ... サーバーに同期した処理です
    server.broadcastMessage("プラグインが有効になってから10秒経ちました")
}

// 30秒毎に実行されます
runTaskTimer(30 * 20, async = true) {
    // async: true ... サーバーに同期していない処理です
    server.onlinePlayers.forEach {
        it.giveExpLevels(1)
    }
}
```

## [Util / UUID](util-uuid)
UUID 関連の便利な関数・クラスを追加します。

```kotlin
/**
 * [UUID.fromString] を実行する
 */
fun uuid(name: String): UUID

/**
 * [uuid] を実行するが、例外が発生した場合は null を返す
 */
fun uuidOrNull(name: String): UUID?

/**
 * [Entity] が持つ [UUID] を操作しやすくしたクラス
 *
 * 変数に保存する際には [Entity] ではなく [UUID] で保存することを推奨する
 */
data class UUIDEntity(val uniqueId: UUID)

/**
 * [Player] が持つ [UUID] を操作しやすくしたクラス
 *
 * 変数に保存する際には [Player] や [OfflinePlayer] ではなく [UUID] で保存することを推奨する
 */
data class UUIDPlayer(val uniqueId: UUID)
```

例として UUIDPlayer を使用した処理を紹介しています。

```kotlin
object PlayerJoinChecker : EventRegister {
    /**
     * [UUIDPlayer] を使うと再ログインしても同一プレイヤーとして認識される
     *
     * 2回目のログイン以降は true と出力される
     */
    private val useUuidPlayer = mutableSetOf<UUIDPlayer>()

    /**
     * [Player] を使うと再ログインすると別のインスタンスになる
     *
     * 2回目のログイン以降も false と出力される
     */
    private val usePlayer = mutableSetOf<Player>()
    
    // ... 省略
}
```

## Plugin Using EasySpigotAPI
EasySpigotAPI を使用してプラグインを製作した場合は以下に追加してください。

- [SecondStoryServer/SS-Plugins](https://github.com/SecondStoryServer/SS-Plugins)

[<< 戻る](../README.md)