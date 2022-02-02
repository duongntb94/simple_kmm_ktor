package com.duongntb94.kmmnetwork.services

import ResultCollection

interface ItunesService {
    suspend fun getResultCollection(): ResultCollection
}