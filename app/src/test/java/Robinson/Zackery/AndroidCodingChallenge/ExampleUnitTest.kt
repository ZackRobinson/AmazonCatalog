package Robinson.Zackery.AndroidCodingChallenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.reactivex.Observable
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(JUnit4::class)
class CatalogViewModelUnitTests {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var cvm: CatalogViewModel

    @Before
    fun setUp() {
        cvm = CatalogViewModel()
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun cvm_hasData() {
        assertEquals(cvm.num, 9)
    }

    @Test
    fun cvm_getsBook() {
        val bookObserver = Observer<Book> { book ->

        }

        val book = Book("Zack", "www.zackeryRobinson.com", "Title")

        val mutableLiveData = MutableLiveData<Book>()
        mutableLiveData.postValue(book)

        assertTrue(book.author == "Zack")
    }

    @Test
    fun make_observables() {
        Observable.create<Int> { subscriber -> System.out.println("meow")}
        Observable.just(1, 2, 3)
        Observable.interval(2, TimeUnit.SECONDS)
    }

}
