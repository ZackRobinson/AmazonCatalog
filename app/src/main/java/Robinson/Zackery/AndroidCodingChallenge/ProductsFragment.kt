package Robinson.Zackery.AndroidCodingChallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration

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
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_products, container, false)
        productsRecyclerView = view.findViewById(R.id.list)
        productsRecyclerView.adapter = productsAdapter
//        productsRecyclerView.addItemDecoration( DividerItemDecoration(requireContext(),
//            DividerItemDecoration.HORIZONTAL))
//        productsRecyclerView.addItemDecoration(DividerItemDecoration(requireContext(),
//            DividerItemDecoration.VERTICAL))
        productsRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        return view
    }

}
