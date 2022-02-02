package com.duongntb94.kmmnetwork.services.impl

import ResultCollection
import com.duongntb94.kmmnetwork.services.ItunesService
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ItunesServiceImpl : ItunesService {
    private val httpClient = HttpClient() {
        install(Logging)
    };

    override suspend fun getResultCollection(): ResultCollection {
        val urlString = "http://itunes.apple.com/search?term=you&entity=song";
        val response: HttpResponse = httpClient.get(urlString);
        val text: String = response.readText();
        val resultCollection: ResultCollection =
            Json { ignoreUnknownKeys = true }.decodeFromString(text);
        return resultCollection;
    }

    override suspend fun getParallelResultCollection(): ResultCollection {
        val request: Deferred<ResultCollection> = GlobalScope.async {
            val urlString1 = "http://itunes.apple.com/search?term=you&entity=song";
            val urlString2 = "http://itunes.apple.com/search?term=me&entity=song";

            val request1: Deferred<HttpResponse> = async { httpClient.get(urlString1) }
            val request2: Deferred<HttpResponse> = async { httpClient.get(urlString2) }

            // Parallel requests
            val response1: HttpResponse = request1.await();
            val response2: HttpResponse = request2.await();

            val text1: String = response1.readText();
            val text2: String = response2.readText();

            val jsonSerialzer =  Json { ignoreUnknownKeys = true }

            val result1: ResultCollection =
                jsonSerialzer.decodeFromString(text1);
            val result2: ResultCollection =
                jsonSerialzer.decodeFromString(text2);
            return@async ResultCollection(
                result1.resultCount + result2.resultCount,
                result1.results + result2.results
            );
        }
        return request.await();
    }
}