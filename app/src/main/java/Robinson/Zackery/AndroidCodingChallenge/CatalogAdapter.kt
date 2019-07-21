package Robinson.Zackery.AndroidCodingChallenge

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CatalogAdapter internal constructor(private var context: Context) :
    RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var books = emptyList<Book>()

    inner class CatalogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.title)
        val authorTextView: TextView = itemView.findViewById(R.id.author)
        val imageView: ImageView = itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val itemView = inflater.inflate(R.layout.book_list_item, parent, false)
        return CatalogViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        val current = books[position]
        holder.titleTextView.text = current.title
        holder.authorTextView.text = current.author
        Glide.with(context)
            .load(current.imageURL)
            .override(200, 300)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.imageView)
    }

    internal fun setBooks(books: List<Book>) {
        this.books = books
        notifyDataSetChanged()
    }

}