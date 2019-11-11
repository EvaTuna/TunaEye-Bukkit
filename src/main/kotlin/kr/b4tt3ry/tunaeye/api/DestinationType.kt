package kr.b4tt3ry.tunaeye.api

enum class DestinationType(private val str: String) {
    EMAIL("email"),
    SMS("sms"),
    TELEGRAM("telegram");

    override fun toString(): String {
        return str
    }
}