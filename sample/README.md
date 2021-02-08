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

[<< 戻る](../README.md)