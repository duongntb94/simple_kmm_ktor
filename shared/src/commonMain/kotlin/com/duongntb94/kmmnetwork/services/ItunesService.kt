package com.duongntb94.kmmnetwork.services

import ResultCollection

interface ItunesService {
    /**
     * Get single result collection from iTunes.
     */
    suspend fun getResultCollection(): ResultCollection

    /**
     * Parallel request 2 apis from iTunes.
     */
    suspend fun getParallelResultCollection(): ResultCollection
}