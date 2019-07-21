package Robinson.Zackery.AndroidCodingChallenge

import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CatalogViewModel : ViewModel() {

    private val tag: String = "CatalogViewModel"

    var books: List<Book>? = null

    private var catalogDisposable: Disposable? = null

    private val amazonApiService by lazy {
        AmazonApiService.create()
    }

    fun loadBooks() {
        catalogDisposable =
            amazonApiService.getCatalog()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { catalog -> this.books = catalog }
    }

}