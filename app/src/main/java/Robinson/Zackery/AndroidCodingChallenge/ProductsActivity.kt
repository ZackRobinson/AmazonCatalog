package Robinson.Zackery.AndroidCodingChallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductsActivity : AppCompatActivity() {

    private lateinit var model: ProductsViewModel

    private lateinit var productsRecyclerView: RecyclerView
    private lateinit var productsAdapter: ProductsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        model = ViewModelProviders.of(this).get(ProductsViewModel::class.java)
        model.loadProducts()

        productsAdapter = ProductsAdapter(this)
        productsRecyclerView = findViewById(R.id.list)
        productsRecyclerView.adapter = productsAdapter
        productsRecyclerView.layoutManager = GridLayoutManager(this, 2)

        val productsObserver = Observer<List<Product>> { products ->
            productsAdapter.setProducts(products)
        }

        model.productsLiveData.observe(this, productsObserver)

    }
}










