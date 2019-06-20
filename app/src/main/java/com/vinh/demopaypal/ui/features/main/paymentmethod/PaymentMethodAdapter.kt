package com.vinh.demopaypal.ui.features.main.paymentmethod

import android.support.v7.util.DiffUtil
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.vinh.demopaypal.R
import com.vinh.demopaypal.data.entinies.Card
import com.vinh.demopaypal.ui.base.adapter.BaseAdapter
import com.vinh.demopaypal.ui.base.adapter.BaseViewHolder
import com.vinh.demopaypal.utils.setCardTypeImageBitmap
import kotlinx.android.synthetic.main.item_payment_method.view.*

class PaymentMethodAdapter(data: ArrayList<Card>) :
    BaseAdapter<Card, PaymentMethodAdapter.PaymentMethodViewHolder>(data) {

    override fun itemLayoutResource() = R.layout.item_payment_method

    override fun createViewHolder(itemView: View) = PaymentMethodViewHolder(itemView)

    private var onClickItemCardListener: ((Card) -> Unit)? = null

    fun setOnClickItemCardListener(onClickItemCardListener: ((Card) -> Unit)): PaymentMethodAdapter {
        this.onClickItemCardListener = onClickItemCardListener
        return this
    }

    override fun onBindViewHolder(vh: PaymentMethodViewHolder, dto: Card, position: Int) {
        vh.tvCardNumber.text = dto.last4
        vh.imvCardType.setCardTypeImageBitmap(dto.brand.toString())
        vh.itemView.setOnClickListener { onClickItemCardListener?.invoke(dto) }
    }

    class PaymentMethodViewHolder(view: View) : BaseViewHolder(view) {
        val tvCardNumber: TextView = itemView.tvCardNumber
        val imvCardType: ImageView = itemView.ivCardType
    }

    fun updatePaymentMethod(newList: List<Card>) {
        update(newList, PaymentMethodDiffCallBack(getData(), newList), false)
    }

    class PaymentMethodDiffCallBack(private val current: List<Card>, private val next: List<Card>) :
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