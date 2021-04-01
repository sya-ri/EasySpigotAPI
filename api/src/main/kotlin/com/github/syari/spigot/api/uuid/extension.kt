package com.github.syari.spigot.api.uuid

import java.util.UUID

/**
 * [UUID.fromString] を実行する。
 * @param name [UUID] の文字列
 * @return [UUID]
 * @since 1.1.0
 */
fun uuid(name: String): UUID = UUID.fromString(name)

/**
 * [uuid] を実行するが、例外が発生した場合は null を返す。
 * @param name [UUID] の文字列
 * @return [UUID]?
 * @since 1.1.0
 */
fun uuidOrNull(name: String): UUID? = try {
    uuid(name)
} catch (ex: IllegalArgumentException) {
    null
}
