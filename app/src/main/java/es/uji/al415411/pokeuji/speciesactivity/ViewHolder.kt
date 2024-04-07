package es.uji.al415411.pokeuji.speciesactivity

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.uji.al415411.pokeuji.R

class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val name: TextView = view.findViewById(R.id.VarietyName)
    val star: ImageView = view.findViewById(R.id.DefaultStar)
}