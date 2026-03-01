package com.oceanmancode.midialiss.data.local.db

import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask


    @OptIn(ExperimentalForeignApi::class)
    fun iosAppDatabaseFilePath(): String {
        val url = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null
        )
        val documentsPath = requireNotNull(url?.path)
        return "$documentsPath/${DatabaseConfig.DATABASE_NAME}"
    }