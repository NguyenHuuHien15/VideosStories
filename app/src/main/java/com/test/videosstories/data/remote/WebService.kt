package com.test.videosstories.data.remote

import android.util.Log
import com.test.videosstories.BuildConfig
import com.test.videosstories.data.DataResult
import com.test.videosstories.data.ErrorType
import com.test.videosstories.domain.MediaCollection
import com.test.videosstories.domain.WebServiceParams
import hien.android.commonapi.toFormattedString
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WebService @Inject constructor() : IWebService {

    val LOG_TAG = WebService::class.simpleName
    private lateinit var retrofit: Retrofit
    private lateinit var webServicesAPI: IWebServiceAPI

    fun setOrUpdateWebServiceParams(webServiceParams: WebServiceParams) {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(webServiceParams.connectTimeout, TimeUnit.SECONDS)
        builder.readTimeout(webServiceParams.readTimeout, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }
        val okHttpClient: OkHttpClient = builder.build()

        retrofit = Retrofit.Builder()
            .baseUrl(createBaseUrl(webServiceParams))
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        webServicesAPI = retrofit.create(IWebServiceAPI::class.java)
    }

    fun createBaseUrl(webServiceParams: WebServiceParams): String {
        /*
        Exemples :
        http://localhost:8080/api/mobile/v1/
        http://10.146.110.49:8080/api/mobile/v1/
        http://192.168.1.13:8080/api/mobile/v1/
         */
        return webServiceParams.protocol + "://" + webServiceParams.serverAddress + webServiceParams.baseUri
    }

    override suspend fun getMediaCollection(): DataResult<MediaCollection> {
        try {
            val response = webServicesAPI.getMediaCollection()
            Log.d(LOG_TAG, response.toFormattedString())
            val code = response.code()
            if (response.isSuccessful) {
                val versionServeur = response.body()
                if (code == HttpURLConnection.HTTP_OK && versionServeur != null) {
                    return DataResult.Success(versionServeur)
                }
            }
            return DataResult.Failure(ErrorType.NETWORK, code)
        } catch (e: Exception) {
            //Logger.d(e.toFormattedString())
            return DataResult.Failure(ErrorType.NETWORK)
        }
    }
}