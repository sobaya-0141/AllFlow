package sobaya.example.allflow.repository

class GithubRepository(private val service: GithubService) {
    suspend fun getRepo(user: String) = service.listRepos(user)
}