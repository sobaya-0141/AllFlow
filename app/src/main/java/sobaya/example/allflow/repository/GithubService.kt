package sobaya.example.allflow.repository

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import sobaya.example.allflow.repository.data.Repo

interface GithubService {
    @GET("/users/{user}/repos")
    suspend fun listRepos(@Path("user") user: String): Response<List<Repo>>
}
