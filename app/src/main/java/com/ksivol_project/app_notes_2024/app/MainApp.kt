package com.ksivol_project.app_notes_2024.app

import android.app.Application
import com.ksivol_project.app_notes_2024.data.database.MainDataBase

class MainApp : Application() {
    val database by lazy { MainDataBase.getDataBase(this)}
}