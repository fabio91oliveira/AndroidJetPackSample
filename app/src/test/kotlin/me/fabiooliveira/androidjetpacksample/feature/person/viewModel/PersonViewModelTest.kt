package me.fabiooliveira.androidjetpacksample.feature.person.viewModel

import io.reactivex.Flowable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import me.fabiooliveira.androidjetpacksample.entity.Person
import me.fabiooliveira.androidjetpacksample.persistence.dao.PersonDao
import me.fabiooliveira.androidjetpacksample.repository.PersonRepository
import me.fabiooliveira.androidjetpacksample.util.RxImmediateSchedulerRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PersonViewModelTest {

    @Rule
    @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var personDao: PersonDao
    private lateinit var personViewModel: PersonViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        personViewModel = (PersonViewModel(PersonRepository(personDao)))
    }

    @Test
    fun should_execute_onCreate(){
        val personList = listOf<Person>()
        val flowablePersonList = Flowable.just(personList)
        Mockito.`when`(personDao.findAll()).thenReturn(flowablePersonList)
8        personViewModel.onCreate()
    }

    @Test
    fun should_execute_onDestroy(){
//        personViewModel.onDestroy()
    }
}