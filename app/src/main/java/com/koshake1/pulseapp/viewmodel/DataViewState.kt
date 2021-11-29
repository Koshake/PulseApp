package com.koshake1.pulseapp.viewmodel

import com.koshake1.pulseapp.model.data.Note

sealed class DataViewState {
    data class Value(val notes: List<Note>) : DataViewState()
    object EMPTY : DataViewState()
}
