package sobaya.example.allflow.repository.data

import com.squareup.moshi.Json

data class Owner(
    @Json(name = "avatar_url")
    val avatar: String,
    val login: String)