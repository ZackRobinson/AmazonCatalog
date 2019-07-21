package Robinson.Zackery.AndroidCodingChallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CatalogActivity : AppCompatActivity() {

    private lateinit var model: CatalogViewModel

    private lateinit var catalogRecyclerView: RecyclerView
    private lateinit var catalogAdapter: CatalogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog)

        model = ViewModelProviders.of(this).get(CatalogViewModel::class.java)
        model.loadBooks()

        catalogAdapter = CatalogAdapter(this)
        catalogRecyclerView = findViewById(R.id.list)
        catalogRecyclerView.adapter = catalogAdapter
        catalogRecyclerView.layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.button).setOnClickListener { showCatalog() } // Skipping LiveData
    }

    private fun showCatalog() {
        catalogAdapter.setBooks(model.books!!)
    }
}
