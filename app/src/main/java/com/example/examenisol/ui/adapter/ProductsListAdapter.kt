package com.example.examenisol.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.examenisol.R
import com.example.examenisol.databinding.ItemProductBinding
import com.example.examenisol.retrofit.model.ProductResults
import com.example.examenisol.utils.createCircularVariantColor
import com.example.examenisol.utils.loadImageGlide
import com.example.examenisol.utils.setPaintFlagStrike
import com.google.android.material.circularreveal.cardview.CircularRevealCardView

class ProductsListAdapter(private var productsListArray: ArrayList<ProductResults.ProductResultsRecords>) :
    RecyclerView.Adapter<ProductsListAdapter.ProductsListHolder>() {

    class ProductsListHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsListHolder {
        return ProductsListHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = productsListArray.size

    override fun onBindViewHolder(holder: ProductsListHolder, position: Int) {
        val product = productsListArray[position]
        val context = holder.itemView.context

        holder.binding.apply {

            ivProduct.loadImageGlide(product.xlImage)

            tvNameProduct.text = product.productDisplayName

            tvAmount.text = context.getString(R.string.price_product, product.listPrice.toString())
            tvAmount.setPaintFlagStrike()

            tvAmountDiscount.text =
                context.getString(R.string.price_product, product.promoPrice.toString())

            context.loadVariantColor(product.variantsColor) { lamView ->
                llColors.addView(lamView)
            }

        }

    }

    private fun Context.loadVariantColor(
        variantsColor: ArrayList<ProductResults.ProductResultsRecords.VariantsColor>?,
        lamView: (CircularRevealCardView) -> Unit
    ) {
        variantsColor ?: return

        variantsColor.forEach { itVariantColor ->

            if (itVariantColor.colorHex.isNotEmpty()) {

                lamView(createCircularVariantColor(itVariantColor.colorHex))

            }

        }
    }

}