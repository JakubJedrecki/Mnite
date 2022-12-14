package jakub.jedrecki.midnite.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakub.jedrecki.midnite.repository.MatchesRepository
import jakub.jedrecki.midnite.repository.MatchesRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindMatchesRepository(matchesRepositoryImpl: MatchesRepositoryImpl): MatchesRepository
}