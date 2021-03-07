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

object TeleportCommand : CommandExecutor, TabExecutor {
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

### [Component](sample/README.md#component)
TextComponentを簡単に生成するための関数・クラスを追加します。

### [Config](sample/README.md#config)
コンフィグを簡単に読み込むことができます。

#### Without EasySpigotAPI

```kotlin
val file = File(plugin.dataFolder, "config.yml")
if (file.exists()) {
    val config = YamlConfiguration.loadConfiguration(file)
    val message = config.getString("message")
    // ...
}

// 複数ファイル
File(plugin.dataFolder, "mobs").listFiles()?.forEach { file ->
    val config = YamlConfiguration.loadConfiguration(file)
    val id = config.getString("id", file.nameWithoutExtension)
    config.getConfigurationSection("attribute")?.getKeys(false)?.forEach { name ->
        val level = config.getInt("attribute.$name.level", 1)
        // ...
    }
    // ...
}
```

#### With EasySpigotAPI
```kotlin
plugin.config(sender, "config.yml") {
    val message = get("message", ConfigDataType.String)
    // ...
}

// 複数ファイル
plugin.configDirectory(sender, "mobs") {
    val id = get("id", ConfigDataType.String, file.nameWithoutExtension)
    section("attribute")?.forEach { name ->
        val level = get("attribute.$name.level", ConfigDataType.Int, 1)
        // ...
    }
    // ...
}
```

### [Event](sample/README.md#event)
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
event<PlayerJoinEvent> { e ->
    e.joinMessage = ">> Join ${it.player.displayName}"
}
```

### [Item](sample/README.md#item)
アイテム関連の便利な関数を追加します。

### [NMS](sample/README.md#nms)
NMSを使うための関数を追加します。

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

### [String](sample/README.md#string)
文字列関連の便利な関数を追加します。

### [UUID](sample/README.md#uuid)
UUIDの便利な関数・クラスを追加します。

### [World](sample/README.md#world)
ワールド関連の便利なクラスを追加します。

### その他
- [Plugin Using EasySpigotAPI](sample/README.md#plugin-using-easyspigotapi)

## テンプレートプロジェクト

[![Spigot](https://github-readme-stats.vercel.app/api/pin/?username=sya-ri&repo=SpigotPluginTemplate)](https://github.com/sya-ri/SpigotPluginTemplate)
[![Paper](https://github-readme-stats.vercel.app/api/pin/?username=sya-ri&repo=PaperPluginTemplate)](https://github.com/sya-ri/PaperPluginTemplate)
[![Purpur](https://github-readme-stats.vercel.app/api/pin/?username=sya-ri&repo=PurpurPluginTemplate)](https://github.com/sya-ri/PurpurPluginTemplate)
[![Yatopia](https://github-readme-stats.vercel.app/api/pin/?username=sya-ri&repo=YatopiaPluginTemplate)](https://github.com/sya-ri/YatopiaPluginTemplate)

## 導入

### EasySpigotAPI をプラグインとして導入する
EasySpigotAPI を導入したプラグインに加えて EasySpigotAPI もサーバーのプラグインに追加する必要があります。

#### build.gradle

```groovy
repositories {
    mavenCentral()
}

dependencies {
    api('com.github.sya-ri:EasySpigotAPI:1.5.1') {
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
    api("com.github.sya-ri:EasySpigotAPI:1.5.1") {
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
    shadowApi('com.github.sya-ri:EasySpigotAPI:1.5.1') {
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
    shadowApi("com.github.sya-ri:EasySpigotAPI:1.5.1") {
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
