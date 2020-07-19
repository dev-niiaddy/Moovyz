package com.orbilax.moovyz.di

import android.app.Application
import androidx.annotation.NonNull
import androidx.room.Room
import com.orbilax.moovyz.api.TMDBService
import com.orbilax.moovyz.db.MoovyzDatabase
import com.orbilax.moovyz.db.dao.DBMovieItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    fun provideTMDBService(): TMDBService {
        return TMDBService.invoke()
    }

    @Singleton
    @Provides
    fun moovyzDatabase(@NonNull application: Application): MoovyzDatabase {
        return Room.databaseBuilder(application, MoovyzDatabase::class.java, "moovyz_databasse").build()
    }

    @Provides
    fun dbMovieItemDao(moovyzDatabase: MoovyzDatabase): DBMovieItemDao {
        return moovyzDatabase.dbMovieItemDao()
    }
}