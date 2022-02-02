package com.duongntb94.kmmnetwork.services.impl

import ResultCollection
import com.duongntb94.kmmnetwork.services.ItunesService
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ItunesServiceImpl: ItunesService {
    private val httpClient = HttpClient();

    override suspend fun getResultCollection(): ResultCollection {
        val urlString = "http://itunes.apple.com/search?term=you&entity=song";
        val response: HttpResponse = httpClient.get(urlString);
        val text: String = response.readText();
        val resultCollection: ResultCollection = Json { ignoreUnknownKeys = true } .decodeFromString(text);
        return resultCollection;
    }
}