package Robinson.Zackery.AndroidCodingChallenge

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProductsViewModel : ViewModel() {

    private val tag: String = "ProductsViewModel"

    val productsLiveData: MutableLiveData<List<Product>> by lazy { MutableLiveData<List<Product>>() }

    private var catalogDisposable: Disposable? = null

    private val amazonApiService by lazy { AmazonApiService.create() }

    fun loadProducts() {
        catalogDisposable =
            amazonApiService.getProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { x -> this.productsLiveData.value = x }
                .also { Log.d(this.javaClass.simpleName, "loadProducts()") }
    }

}