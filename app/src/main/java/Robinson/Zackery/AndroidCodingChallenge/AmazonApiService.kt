package Robinson.Zackery.AndroidCodingChallenge

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface AmazonApiService {

    @GET("books.json")
    fun getProducts(): Observable<List<Product>>

    companion object {
        fun create(): AmazonApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://de-coding-test.s3.amazonaws.com/")
                .build()

            return retrofit.create(AmazonApiService::class.java)
        }
    }

}