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
import android.content.SharedPreferences
import com.google.gson.Gson
import android.R.id.edit





class ProductsDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_detail)

        val product = intent.getParcelableExtra<Product>("product")

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

        val prefsEditor = getPreferences(Context.MODE_PRIVATE).edit()
            .putString("favorite", Gson().toJson(product))
        prefsEditor.commit()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.products_detail_menu, menu);
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_favorite -> {
                item.isChecked = !item.isChecked

                val product = Gson().fromJson<Product>(getPreferences(Context.MODE_PRIVATE).getString("favorite", ""), Product::class.java)

                println(product.title)

                if(item.isChecked) {
                    item.setIcon(R.drawable.ic_favorite_red_24dp)

                } else item.setIcon(R.drawable.ic_favorite_border_red_24dp)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}
