package com.vinh.demopaypal.ui.features.main.shoppingcart

import android.support.v7.util.DiffUtil
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.vinh.demopaypal.R
import com.vinh.demopaypal.data.entinies.Beer
import com.vinh.demopaypal.ui.base.adapter.BaseAdapter
import com.vinh.demopaypal.ui.base.adapter.BaseViewHolder
import com.vinh.demopaypal.utils.loadImage
import kotlinx.android.synthetic.main.item_beer.view.*

class ShoppingCartAdapter(data: ArrayList<Beer>) : BaseAdapter<Beer, ShoppingCartAdapter.ShoppingCartViewHolder>(data) {

    override fun itemLayoutResource() = R.layout.item_beer

    override fun createViewHolder(itemView: View) = ShoppingCartViewHolder(itemView)

    private var onClickItemListener: ((Beer) -> Unit)? = null

    fun onClickItemListener(onClickItemListener: ((Beer) -> Unit)): ShoppingCartAdapter {
        this.onClickItemListener = onClickItemListener
        return this
    }

    override fun onBindViewHolder(vh: ShoppingCartViewHolder, dto: Beer, position: Int) {
        vh.imv.loadImage(dto.urlPreview!!)
        vh.tvName.text = dto.name
        vh.tvPrice.text = String.format("Price ${dto.price}")
        vh.tvPay.setOnClickListener { onClickItemListener?.invoke(dto) }
    }

    class ShoppingCartViewHolder(view: View) : BaseViewHolder(view) {
        val tvPay: TextView = itemView.tvPay
        val tvName: TextView = itemView.tvName
        val tvPrice: TextView = itemView.tvPrice
        val imv: ImageView = itemView.imv
    }

    fun updateShoppingCart(newList: List<Beer>) {
        update(newList, ShoppingCartDiffCallBack(getData(), newList), false)
    }

    class ShoppingCartDiffCallBack(private val current: List<Beer>, private val next: List<Beer>) :
        DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return current.size
        }

        override fun getNewListSize(): Int {
            return next.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val currentItem = current[oldItemPosition]
            val nextItem = next[newItemPosition]
            return currentItem.name == nextItem.name
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val currentItem = current[oldItemPosition]
            val nextItem = next[newItemPosition]
            return currentItem == nextItem
        }
    }
}