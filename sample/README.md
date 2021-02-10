# Sample

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

[<< 戻る](../README.md)