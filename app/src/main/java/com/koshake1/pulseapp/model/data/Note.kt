package com.koshake1.pulseapp.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

private val idRandom = Random(0)
val noteId: Long
    get() = idRandom.nextLong()

@Parcelize
data class Note(
    val id : Long = noteId,
    val date : String = "",
    val time : String = "",
    val pressure : String = "",
    val pulse : String = ""
) : Parcelable
