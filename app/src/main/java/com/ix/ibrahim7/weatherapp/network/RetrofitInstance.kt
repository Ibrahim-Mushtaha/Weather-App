package com.ix.ibrahim7.weatherapp.network


import com.ix.ibrahim7.weatherapp.util.Constant.API_KEY
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitInstance {


    companion object {
        private const val BASE_URL = "http://api.openweathermap.org/data/2.5/"
        private const val QUERY = "q"
        private const val APIKEY = "appid"
        private const val UNIT = "units"

        var api: Api? = null

        init {

            val client = OkHttpClient.Builder()
                .build()

            api = getInstantRetrofit(BASE_URL, client).create(Api::class.java)


        }

        private fun getInstantRetrofit(url: String, client: OkHttpClient) =
            Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(client)
                .build()

        fun getQueryParams(country:String,units:String): HashMap<String, String>? {
            val map: HashMap<String, String> = HashMap()
            map[QUERY] = country
            map[UNIT] = units
            map[APIKEY] = API_KEY
            return map
        }
    }

}