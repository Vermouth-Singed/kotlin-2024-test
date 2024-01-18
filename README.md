### Retrofit2 세팅
1. build.gradle.kts 세팅
```
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")
```
2. Connection getInstance 용 메소드 생성
```
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConnection {
    companion object {
        fun getInstance(baseURL: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(
                    GsonConverterFactory
                        .create(
                            GsonBuilder()
                                .setLenient()
                                .create()
                        )
                )
                .build()
        }
    }
}
```
3. 인터페이스 생성
```
//예시
import retrofit2.Call
import retrofit2.http.GET

interface SampleRetrofitService {
    @GET("/api/sample/test")
    fun test(): Call<String>
}
```
4. 샘플 소스코드
```
@GetMapping
fun sample(): LoginCheckUseCase.Result {
    val retrofitApi = RetrofitConnection
        .getInstance("http://localhost:8080/")
        .create(SampleRetrofitService::class.java)
        .test()
        .execute()

    return LoginCheckUseCase.Result(
        email = retrofitApi.body().orEmpty(),
        success = retrofitApi.isSuccessful
    )
}

@GetMapping("/test")
fun test(): String = "test"
```
5. execute() 는 동기화, enqueue 비동기는 조만간 업로드 예정!