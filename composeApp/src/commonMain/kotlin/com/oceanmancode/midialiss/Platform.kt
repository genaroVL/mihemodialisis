package com.oceanmancode.midialiss

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform