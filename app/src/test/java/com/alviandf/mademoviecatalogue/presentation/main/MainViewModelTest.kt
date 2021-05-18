package com.alviandf.mademoviecatalogue.presentation.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.alviandf.core.data.Resource
import com.alviandf.core.domain.model.MovieOrTvShow
import com.alviandf.core.domain.usecase.CatalogueAppUseCase
import com.alviandf.mademoviecatalogue.TestCoroutineRule
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.*
import org.junit.rules.*
import org.junit.runner.*
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.*

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: CatalogueAppUseCase

    @Mock
    private lateinit var observerMovie: Observer<Resource<List<MovieOrTvShow>>>

    @Mock
    private lateinit var observerTvShow: Observer<Resource<List<MovieOrTvShow>>>

    @Before
    fun setUp() {
        viewModel = MainViewModel(useCase)
    }

    @Test
    fun getMovieResponse() {
        testCoroutineRule.runBlockingTest {
            val data: Flow<Resource<List<MovieOrTvShow>>> = flow {
                emit(Resource.Success(emptyList()))
            }

            `when`(useCase.getMovies()).thenReturn(data)

            viewModel.getMovies().observeForever(observerMovie)
            verify(useCase).getMovies()
        }
    }

    @Test
    fun getTvResponse() {
        testCoroutineRule.runBlockingTest {
            val data: Flow<Resource<List<MovieOrTvShow>>> = flow {
                emit(Resource.Success(emptyList()))
            }

            `when`(useCase.getTvShows()).thenReturn(data)

            viewModel.getTvShows().observeForever(observerTvShow)
            verify(useCase).getTvShows()
        }
    }
}