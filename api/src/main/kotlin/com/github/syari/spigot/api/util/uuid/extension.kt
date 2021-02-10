package com.github.syari.spigot.api.util.uuid

import java.util.UUID

/**
 * [UUID.fromString] を実行する
 * @since 1.1.0
 */
fun uuid(name: String): UUID = UUID.fromString(name)

/**
 * [uuid] を実行するが、例外が発生した場合は null を返す
 * @since 1.1.0
 */
fun uuidOrNull(name: String): UUID? = try {
    uuid(name)
} catch (ex: IllegalArgumentException) {
    null
}
