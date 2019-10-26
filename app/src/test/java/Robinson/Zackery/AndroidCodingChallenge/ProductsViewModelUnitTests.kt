package Robinson.Zackery.AndroidCodingChallenge

//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(JUnit4::class)
class ProductsViewModelUnitTests {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var cvm: ProductsViewModel

    @Before
    fun setUp() {
        cvm = ProductsViewModel()
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun cvm_getsBook() {
        val book = Product("Zack", "www.zackeryRobinson.com", "Title")

        val mutableLiveData = MutableLiveData<Product>()
        mutableLiveData.postValue(book)

        assertTrue(book.author == "Zack")
    }

    @Test
    fun make_observables() {
        Observable.create<Int> { subscriber -> System.out.println("meow")}
        Observable.just(1, 2, 3)
        Observable.interval(2, TimeUnit.SECONDS)
    }

    @Test
    fun test() {

        val lifecycle = mock(LifecycleOwner::class.java)

        val bookObserver = Observer<Product> { book ->
            println("n")
        }

        val catalogObserver = Observer<List<Product>> { catalog ->
            println("n")
        }
//
//        cvm.book.observe(lifecycle, bookObserver)
//        cvm.liveBooks.observe(lifecycle, catalogObserver)

    }

}
