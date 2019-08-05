package Robinson.Zackery.AndroidCodingChallenge

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CatalogViewModel : ViewModel() {

    private val tag = "CatalogViewModel"

    val num: Int = 9

    val book: MutableLiveData<Book> by lazy {
        MutableLiveData<Book>().also { loadBookWithRx() }
    }

    val liveBooks: MutableLiveData<List<Book>> by lazy {
        MutableLiveData<List<Book>>().also { loadLiveBooksWithRx() }
    }

    private var catalogDisposable: Disposable? = null

    private val amazonApiService by lazy {
        AmazonApiService.create()
    }

    private fun loadBookWithRx() {
        catalogDisposable =
            amazonApiService.getCatalog()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { catalog -> this.book.setValue(catalog?.get(0)) }
    }

    private fun loadLiveBooksWithRx() {
        catalogDisposable =
            amazonApiService.getCatalog()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { catalog -> this.liveBooks.setValue(catalog) }
    }

}