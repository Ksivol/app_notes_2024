package com.ksivol_project.app_notes_2024.domain.entities

import java.sql.Timestamp

data class NoteDomain(

    val id: Int?,


    val dateStart: Timestamp,


    val dateFinish: Timestamp,


    val name: String,


    val description: String,
)
