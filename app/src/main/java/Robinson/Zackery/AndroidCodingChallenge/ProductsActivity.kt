package Robinson.Zackery.AndroidCodingChallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ProductsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        supportFragmentManager.beginTransaction()
            .add(R.id.products_container, ProductsFragment.newInstance())
            .commit()
    }
}










