package com.example.vmapp.viewmodel



import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.vmapp.api.ApiService
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`


class ViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()


    private lateinit var viewModel: ViewModel
    @MockK
    lateinit var apiService: ApiService

    @Mock
    private val mainThreadSurrogate = newSingleThreadContext("MainThread")

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(mainThreadSurrogate)

    }

    @Test
    suspend fun testNull() {
        `when`(apiService.getPeople()).thenReturn(null)
        assertNotNull(viewModel.getPeopleLists())
        assertTrue(viewModel.peopleList.hasObservers())
    }




}