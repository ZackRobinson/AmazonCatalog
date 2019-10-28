package Robinson.Zackery.AndroidCodingChallenge

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_products_detail.*

class ProductsDetailActivity : AppCompatActivity() {

    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_detail)

        product = intent.getParcelableExtra<Product>("product")

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = product.title
        setSupportActionBar(toolbar)

        detailTitleView.text = product.title
        detailAuthorView.text = product.author
        Glide.with(this)
            .load(product.imageURL)
            .override(200, 300)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .into(detailImageView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.products_detail_menu, menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_favorite -> {
                item.isChecked = !item.isChecked
                val prefsEditor = PreferenceManager.getDefaultSharedPreferences(this)

                if(item.isChecked) {
                    item.setIcon(R.drawable.ic_favorite_red_24dp)
                    prefsEditor.edit()
                        .putString(product.title, product.title).commit() // Very lazy - will revisit
                } else {
                    item.setIcon(R.drawable.ic_favorite_border_red_24dp)
                    prefsEditor.edit()
                        .remove(product.title).commit() // Very lazy - will revisit
                }
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}
