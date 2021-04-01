# Event

## [Main.kt](src/main/kotlin/com/github/syari/spigot/api/sample/event/Main.kt)
プラグインのメイン処理です。

```kotlin
@Suppress("unused")
class Main : JavaPlugin() {
    companion object {
        internal lateinit var plugin: JavaPlugin
    }
    
    init {
        plugin = this
    }
    
    override fun onEnable() {
        EventListener.register()
    }
}
```

## [EventListener.kt](src/main/kotlin/com/github/syari/spigot/api/sample/event/EventListener.kt)

イベントの定義をします。`events {}` の中でイベントの定義を羅列していきます。

- `event<>{}` 基本的なイベントです。これを主に使います。
- `cancelEventIf<>{}` 条件に一致した時にイベントをキャンセルします。単純な処理のみで用いることを推奨します。
- `cancelEventIfNot<>{}` 条件に一致しなかった時にイベントをキャンセルします。単純な処理のみで用いることを推奨します。

```kotlin
object EventListener {
    fun register() {
        plugin.events {
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
}
```

[<< 戻る](../README.md)