# Scheduler

## [Main.kt](src/main/kotlin/com/github/syari/spigot/api/sample/scheduler/Main.kt)
サーバー起動時にスケジューラを動作させている。`async` という引数の真偽値によって同期・非同期を決め流ことができる。

```kotlin
@Suppress("unused")
class Main : JavaPlugin() {
    override fun onEnable() {
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
    }
}
```

[<< 戻る](../README.md)