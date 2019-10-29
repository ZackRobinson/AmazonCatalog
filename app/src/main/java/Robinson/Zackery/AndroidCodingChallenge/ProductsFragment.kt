package Robinson.Zackery.AndroidCodingChallenge

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.Observer
import android.app.Activity

class ProductsFragment : Fragment() {

    private lateinit var model: ProductsViewModel
    private lateinit var productsRecyclerView: RecyclerView
    private lateinit var productsAdapter: ProductsAdapter

    companion object {
        fun newInstance(): ProductsFragment {
            return ProductsFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model = ViewModelProviders.of(this).get(ProductsViewModel::class.java)
        model.loadProducts()
        productsAdapter = ProductsAdapter(requireContext())
        val productsObserver = Observer<List<Product>> { products ->
            productsAdapter.setProducts(products)
        }
        model.productsLiveData.observe(this, productsObserver)
        productsAdapter.setOnItemClickListener(object : ProductsAdapter.ClickListener {
            override fun onClick(product: Product) {
                val intent = Intent(activity, ProductsDetailActivity::class.java)
                intent.putExtra("product", product)
                startActivityForResult(intent, 1)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode === 1) {
            if (resultCode === Activity.RESULT_OK) {
                productsAdapter.notifyDataSetChanged()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_products, container, false)
        productsRecyclerView = view.findViewById(R.id.list)
        productsRecyclerView.adapter = productsAdapter
        productsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        return view
    }

}
