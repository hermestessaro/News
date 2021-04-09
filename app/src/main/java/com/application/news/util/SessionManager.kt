package com.application.news.util

class SessionManager() {

    companion object {
        const val USER_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MjU5LCJlbWFpbCI6ImRpbWFzLmdhYnJpZWxAenJvYmFuay5jb20uYnIifQ.a3j7sRx8FIedZCfDGLocduOYpcibfIenX7TVJjv6Sis"
    }

    fun fetchToken(): String {
        return USER_TOKEN
    }
}