package com.example.firebaseauthentication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_items.view.*

class BanditAdapter(val bandits : List<BanditDetail>): RecyclerView.Adapter<BanditAdapter.BanditView>() {

    // the viewholder shows the cards described on list items
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BanditView {
        return  BanditView(LayoutInflater.from(parent.context)
            .inflate(R.layout.list_items, parent, false)
        )
    }

    // item count is the number of elements in array of information provided
    override fun getItemCount(): Int {
        return bandits.size
    }

    // attach the information from the array into the cards
    override fun onBindViewHolder(holder: BanditView, position: Int) {
        val bandit = bandits[position]

        holder.itemview.banditName.text = bandit.name
        holder.itemview.banditDesc.text = bandit.desc

//        Glide.with(holder.itemview.context){
//            .load(bandit.image)
//            .into(holder.itemview,banditImage)
//        }
    }

    class BanditView( val itemview : View) : RecyclerView.ViewHolder(itemview)
}