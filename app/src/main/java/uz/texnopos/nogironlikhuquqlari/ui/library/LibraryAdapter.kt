package uz.texnopos.nogironlikhuquqlari.ui.library

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.texnopos.nogironlikhuquqlari.core.onClick
import uz.texnopos.nogironlikhuquqlari.data.entities.Book
import uz.texnopos.nogironlikhuquqlari.databinding.ItemBookBinding

class LibraryAdapter : RecyclerView.Adapter<LibraryAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(val bind: ItemBookBinding) : RecyclerView.ViewHolder(bind.root) {
        fun populateModel(book: Book) {
            bind.bookImage.setImageResource(
                bind.root.context.resources
                    .getIdentifier(book.bookName, "drawable", bind.root.context.packageName)
            )

            bind.bookImage.onClick {
                try{
                    onClick.invoke(book.bookName)
                }catch (e:Exception){}
            }
        }
    }

    private var onClick: (name: String) -> Unit = {}
    fun itemOnClick(onClick: (name: String) -> Unit) {
        this.onClick = onClick
    }

    var models = mutableListOf<Book>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val bind = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    override fun getItemCount() = models.size
}