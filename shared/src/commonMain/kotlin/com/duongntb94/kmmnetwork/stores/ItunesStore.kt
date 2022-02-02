package com.duongntb94.kmmnetwork.stores

import ResultCollection
import com.duongntb94.kmmnetwork.models.Song
import com.duongntb94.kmmnetwork.services.ItunesService
import com.duongntb94.kmmnetwork.services.impl.ItunesServiceImpl

class ItunesStore() {
    var resultCollection: ResultCollection = ResultCollection(0);
    var selectedSong: Song? = null

    private val itunesService: ItunesService = ItunesServiceImpl();

    /**
     * Fetch collection of songs.
     */
    suspend fun getResultCollection(): ResultCollection {
        // resultCollection = itunesService.getResultCollection();
        resultCollection = itunesService.getParallelResultCollection(); // call two apis parallel
        return resultCollection;
    }

    /**
     * Set selected song based on selected index.
     */
    fun setSelectedSong(id: Int) {
        if (id < 0 || id >= resultCollection.resultCount) {
            // TODO: Throw index not found
            return;
        }
        selectedSong = resultCollection.results[id];
    }
}