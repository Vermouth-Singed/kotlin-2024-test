### 계층형 아키텍처에서 헥사고날 아키텍처로 변경하게되면 기대한 점
- 브랜치 병합할 때 충돌을 방지할 수 있다?
- 나중에 MSA 로 분리할 때 편하다?
- API 별로 DTO 를 분리할 수 있어 필요한 값들만 보낼 수 있다?
### 지금까지 헥사고날 테스트하고 느낀 점
- 브랜치 병합 때 충돌은 어차피 컨트롤러와 서비스에서 난다!
- MSA 로 분리할거면 처음부터 멀티 프로젝트 구조로 계층형으로 만들면 된다!
- API 별 DTO 구분은 세부 패키지 추가를 통해 분리할 수 있다!
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
5. execute() 는 동기화, enqueue 비동기