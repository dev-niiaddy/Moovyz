package com.orbilax.moovyz.di

import android.app.Application
import com.orbilax.moovyz.db.MoovyzDatabase
import com.orbilax.moovyz.db.dao.DBMovieItemDao
import com.orbilax.moovyz.api.TMDBService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    fun provideTMDBService(): TMDBService {
        return TMDBService.invoke()
    }

    @Provides
    fun moovyzDatabase(application: Application): MoovyzDatabase {
        return MoovyzDatabase.getDatabase(application)
    }

    @Provides
    fun dbMovieItemDao(moovyzDatabase: MoovyzDatabase): DBMovieItemDao {
        return moovyzDatabase.dbMovieItemDao()
    }
}