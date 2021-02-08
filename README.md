# EasySpigotAPI
[![GitHub release (latest by date)](https://img.shields.io/github/v/release/sya-ri/EasySpigotAPI)](https://github.com/sya-ri/EasySpigotAPI/releases/latest)
[![maven-central](https://img.shields.io/maven-central/v/com.github.sya-ri/EasySpigotAPI)](https://search.maven.org/artifact/com.github.sya-ri/EasySpigotAPI)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)

A library for easy use of the Spigot API.

## サンプルコード

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