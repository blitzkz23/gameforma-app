package com.naufaldystd.core.data.source.local.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.naufaldystd.core.data.source.remote.response.EsrbRatingItem
import com.naufaldystd.core.data.source.remote.response.GameResponse
import com.naufaldystd.core.utils.DataMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
@ExperimentalCoroutinesApi
class RawgDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: RawgDatabase
    private lateinit var dao: RawgDao
    private lateinit var game: GameResponse
    private lateinit var gameList: List<GameResponse>

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            RawgDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.rawgDao()
        game = GameResponse(
            1, "", 0.2, 0, 0, "",
            "", EsrbRatingItem("", 1, ""), listOf(), listOf(), listOf(),
            "")
        gameList = listOf(game)
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertGameList() = runTest {
        val gameEntities = DataMapper.mapResponsesToEntities(gameList)
        dao.insertGameList(gameEntities)
        val allGameList = dao.getAllGame().asLiveData().getOrAwaitValue()

        assertThat(gameEntities).isEqualTo(allGameList)
    }

    @Test
    fun insertGame() = runTest {
        val gameEntity = DataMapper.mapResponseToEntity(game)
        dao.insertGame(gameEntity)
        val insertedGame = dao.getGameById(1)?.asLiveData()?.getOrAwaitValue()

        assertThat(gameEntity).isEqualTo(insertedGame)
    }

    @Test
    fun getFavoriteGame() = runTest {
        val gameEntity = DataMapper.mapResponseToEntity(game)
        gameEntity.isFavorite = true
        dao.insertGame(gameEntity)
        val favoriteGame = dao.getFavoriteGame().asLiveData().getOrAwaitValue()

        assertThat(gameEntity).isIn(favoriteGame)
    }
}