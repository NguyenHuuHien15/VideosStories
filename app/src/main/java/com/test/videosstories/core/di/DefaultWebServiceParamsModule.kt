package com.test.videosstories.core.di

import com.test.videosstories.domain.WebServiceParams
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DefaultWebServiceParamsModule {

    @Singleton
    @Provides
    // A paramétrer/maj plus tard
    fun provideDefaultWebServiceParams(): WebServiceParams = WebServiceParams(

        "https",
        "extendsclass.com",
        8080, "/api/json-storage/bin/",
        5,
        120
    )

}