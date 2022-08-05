package jakub.jedrecki.midnite.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakub.jedrecki.midnite.network.MidniteService
import jakub.jedrecki.midnite.network.model.mappers.ContractsDtoMapper
import jakub.jedrecki.midnite.network.model.mappers.MarketDtoMapper
import jakub.jedrecki.midnite.network.model.mappers.MatchesDtoMapper
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideContractsDtoMapper(): ContractsDtoMapper {
        return ContractsDtoMapper()
    }

    @Singleton
    @Provides
    fun provideMarketDtoMapper(contractsMapper: ContractsDtoMapper): MarketDtoMapper {
        return MarketDtoMapper(contractsMapper)
    }

    @Singleton
    @Provides
    fun provideMatchesDtoMapper(marketMapper: MarketDtoMapper): MatchesDtoMapper {
        return MatchesDtoMapper(marketMapper)
    }

    @Singleton
    @Provides
    fun provideMidniteService(): MidniteService {
        return Retrofit.Builder()
            .baseUrl("https://api.midnite.com/v0/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(MidniteService::class.java)
    }
}