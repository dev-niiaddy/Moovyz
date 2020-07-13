package com.orbilax.moovyz

import android.app.Application
import com.orbilax.moovyz.database.MoovyzDatabase
import com.orbilax.moovyz.database.dao.DBMovieItemDao
import com.orbilax.moovyz.service.TMDBService
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