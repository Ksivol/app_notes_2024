package com.ksivol_project.app_notes_2024.activities

import android.app.Application
import com.ksivol_project.app_notes_2024.data.MainDataBase

class MainApp : Application() {
    val database by lazy { MainDataBase.getDataBase(this)}
}