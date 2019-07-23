package Robinson.Zackery.AndroidCodingChallenge

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CatalogViewModel : ViewModel() {

    private val tag: String = "CatalogViewModel"

    val liveBooks: MutableLiveData<List<Book>> by lazy {
        MutableLiveData<List<Book>>().also { loadLiveBooksWithRx() }
    }

    private var catalogDisposable: Disposable? = null

    private val amazonApiService by lazy {
        AmazonApiService.create()
    }

    private fun loadLiveBooksWithRx() {
        catalogDisposable =
            amazonApiService.getCatalog()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { catalog -> this.liveBooks.setValue(catalog) }
    }

    private fun loadLiveBooksWithCoroutines() {

    }

}