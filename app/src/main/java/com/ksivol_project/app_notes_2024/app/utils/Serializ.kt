package com.ksivol_project.app_notes_2024.app.utils
import android.content.Intent
import android.os.Build
import java.io.Serializable

fun <T : Serializable?> Intent.getSerializable(name: String?, clazz: Class<T>): T? {
    @Suppress("UNCHECKED_CAST", "DEPRECATION")
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        this.getSerializableExtra(name, clazz)
    else
        this.getSerializableExtra(name) as T
}