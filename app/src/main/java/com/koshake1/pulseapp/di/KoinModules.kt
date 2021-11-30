package com.koshake1.pulseapp.di

import com.google.firebase.firestore.FirebaseFirestore
import com.koshake1.pulseapp.db.DatabaseProvider
import com.koshake1.pulseapp.db.FireBaseProvider
import com.koshake1.pulseapp.model.data.Note
import com.koshake1.pulseapp.model.repository.NotesRepository
import com.koshake1.pulseapp.model.repository.NotesRepositoryImpl
import com.koshake1.pulseapp.viewmodel.MainViewModel
import com.koshake1.pulseapp.viewmodel.NoteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single<NotesRepository> { NotesRepositoryImpl(get()) }
    single { FirebaseFirestore.getInstance() }
    single<DatabaseProvider> { FireBaseProvider(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { (note: Note?) -> NoteViewModel(get(), note) }
}