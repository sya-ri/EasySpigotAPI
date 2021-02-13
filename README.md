# EasySpigotAPI
[![GitHub release (latest by date)](https://img.shields.io/github/v/release/sya-ri/EasySpigotAPI)](https://github.com/sya-ri/EasySpigotAPI/releases/latest)
[![maven-central](https://img.shields.io/maven-central/v/com.github.sya-ri/EasySpigotAPI)](https://search.maven.org/artifact/com.github.sya-ri/EasySpigotAPI)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)
[![EasySpigotAPI](https://img.shields.io/badge/EasySpigotAPI-%E2%AC%85-4D4.svg)](https://github.com/sya-ri/EasySpigotAPI)

A library for easy use of the Spigot API.

## サンプルコード


### [Command](sample/README.md#command)
コマンドを簡単に作成することができます。

#### Without EasySpigotAPI

```yaml
# plugin.yml
commands:
  teleport:
    # ...
```

```kotlin
override fun onEnable() {
    getCommand("teleport")?.setExecutor(TeleportCommand)
}

class TeleportCommand : CommandExecutor, TabExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        // ...
    }

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String> {
        // ...
    }
}
```

#### With EasySpigotAPI
`plugin.yml` にコマンドを書く必要はありません。

```kotlin
plugin.command("teleport") {
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

### [Event / Register](sample/README.md#event--register)
イベント定義をラムダ式で書くことができます。

#### Without EasySpigotAPI
```kotlin
@EventHandler
fun on(e: PlayerJoinEvent) {
    e.joinMessage = ">> Join ${e.player.displayName}"
}
```

#### With EasySpigotAPI
```kotlin
event<PlayerJoinEvent> {
    it.joinMessage = ">> Join ${it.player.displayName}"
}
```

### [Scheduler](sample/README.md#scheduler)
スケジューラを簡単に使うことができます。

#### Without EasySpigotAPI
```kotlin
object : BukkitRunnable() {
    override fun run() {
        // ...
    }
}.runTaskTimer(plugin, 0, 30 * 20)

object : BukkitRunnable() {
    override fun run() {
        // ...
    }
}.runTaskTimerAsynchronously(plugin, 0, 30 * 20)
```

#### With EasySpigotAPI
```kotlin
plugin.runTaskTimer(30 * 20) {
    // ...
}

plugin.runTaskTimer(30 * 20, async = true) {
    // ...
}
```

### その他
- [Util / UUID](sample/README.md#util--uuid)

## 導入

### EasySpigotAPI をプラグインとして導入する
EasySpigotAPI を導入したプラグインに加えて EasySpigotAPI もサーバーのプラグインに追加する必要があります。

#### build.gradle

```groovy
repositories {
    mavenCentral()
}

dependencies {
    api('com.github.sya-ri:EasySpigotAPI:1.0.0') {
        exclude group: 'org.spigotmc', module: 'spigot-api'
    }
}
```

#### build.gradle.kts

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    api("com.github.sya-ri:EasySpigotAPI:1.0.0") {
        exclude(group = "org.spigotmc", module = "spigot-api")
    }
}
```

### 一つのプラグインの中に EasySpigotAPI もまとめる
EasySpigotAPI を導入したプラグインの中に必要なものが同梱され、EasySpigotAPI を別で導入する必要がありません。

#### build.gradle

```groovy
plugins {
    id 'com.github.johnrengelman.shadow' version '6.1.0'
}

repositories {
    mavenCentral()
}

configurations {
    shadowApi
    api.extendsFrom shadowApi
}

dependencies {
    shadowApi('com.github.sya-ri:EasySpigotAPI:1.0.0') {
        exclude group: 'org.spigotmc', module: 'spigot-api'
    }
}

shadowJar {
    configurations = [project.configurations.shadowApi]
}
```

#### build.gradle.kts

```kotlin
plugins {
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

val shadowApi by configurations.creating
configurations["api"].extendsFrom(shadowApi)

repositories {
    mavenCentral()
}

dependencies {
    shadowApi("com.github.sya-ri:EasySpigotAPI:1.0.0") {
        exclude(group = "org.spigotmc", module = "spigot-api")
    }
}

tasks.withType<ShadowJar> {
    configurations = listOf(shadowApi)
}
```

## バッジ
[![EasySpigotAPI](https://img.shields.io/badge/EasySpigotAPI-%E2%AC%85-4D4.svg)](https://github.com/sya-ri/EasySpigotAPI)

```
[![EasySpigotAPI](https://img.shields.io/badge/EasySpigotAPI-%E2%AC%85-4D4.svg)](https://github.com/sya-ri/EasySpigotAPI)
```
