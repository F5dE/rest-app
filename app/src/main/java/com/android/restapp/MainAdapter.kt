package com.android.restapp

import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Callback
import java.lang.Exception


class MainAdapter(private val dataSet: ArrayList<Cards>) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardText: TextView? = view.findViewById(R.id.card_text)
        val cardDescription: TextView? = view.findViewById(R.id.card_description)
        val card: RelativeLayout? = view.findViewById(R.id.card)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.card_image, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val curCard = dataSet[position]

        when (curCard.card_type) {
            "text" -> createTextCard(curCard.card, viewHolder)
            "title_description" -> createTitleCard(curCard.card, viewHolder)
            "image_title_description" -> createImageCard(curCard.card, viewHolder)
        }
    }

    override fun getItemCount() = dataSet.size

    private fun createTextCard(card: Card, viewHolder: ViewHolder) {
        viewHolder.cardText?.text = card.value
        viewHolder.cardText?.textSize = card.attributes.font.size.toFloat()
        viewHolder.cardText?.setTextColor(Color.parseColor(card.attributes.text_color))
    }

    private fun createTitleCard(card: Card, viewHolder: ViewHolder) {
        viewHolder.cardText?.text = card.title.value
        viewHolder.cardText?.textSize = card.title.attributes.font.size.toFloat()
        viewHolder.cardText?.setTextColor(Color.parseColor(card.title.attributes.text_color))
        viewHolder.cardDescription?.text = card.description.value
        viewHolder.cardDescription?.textSize = card.description.attributes.font.size.toFloat()
        viewHolder.cardDescription?.setTextColor(Color.parseColor(card.description.attributes.text_color))
    }

    private fun createImageCard(card: Card, viewHolder: ViewHolder) {
        viewHolder.cardText?.text = card.title.value
        viewHolder.cardText?.textSize = card.title.attributes.font.size.toFloat()
        viewHolder.cardText?.setTextColor(Color.parseColor(card.title.attributes.text_color))
        viewHolder.cardText?.gravity = Gravity.BOTTOM
        viewHolder.cardDescription?.text = card.description.value
        viewHolder.cardDescription?.textSize = card.description.attributes.font.size.toFloat()
        viewHolder.cardDescription?.setTextColor(Color.parseColor(card.description.attributes.text_color))

        val img = ImageView(viewHolder.card?.context)
        Picasso.get().load(card.image.url).resize(card.image.size.width, card.image.size.height)
            .centerCrop().into(img, object : Callback {
                override fun onSuccess() {
                    viewHolder.card?.setBackgroundDrawable(img.drawable)
                }

                override fun onError(e: Exception?) {}
            })
    }

}