package uz.texnopos.mehrtilsimi.ui.rights

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.texnopos.mehrtilsimi.core.onClick
import uz.texnopos.mehrtilsimi.data.entities.Right
import uz.texnopos.mehrtilsimi.databinding.ItemBookBinding
import uz.texnopos.mehrtilsimi.databinding.ItemRightBinding

class RightsAdapter : RecyclerView.Adapter<RightsAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(private val bind: ItemRightBinding) : RecyclerView.ViewHolder(bind.root) {
        fun populateModel(model: Right) {
        bind.tvRight.text=model.title
            bind.card.onClick {
                onClick.invoke(model)
            }
        }
    }
    private var onClick:(right:Right)->Unit={}
    fun itemOnClick(onClick:(right:Right)->Unit){
        this.onClick=onClick
    }
    var models = listOf<Right>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = models.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val bind = ItemRightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.populateModel(models[position])
    }
}