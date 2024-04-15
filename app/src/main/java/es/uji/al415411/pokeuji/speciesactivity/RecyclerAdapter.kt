package es.uji.al415411.pokeuji.speciesactivity

import es.uji.al415411.pokeuji.models.Variety
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.uji.al415411.pokeuji.R

class RecyclerAdapter(val variety: List<Variety>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
    private var onClickListener: OnClickListener? = null
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.VarietyName)
        val star: ImageView = view.findViewById(R.id.DefaultStar)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.species_recycler, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        with(variety[pos]) {
            holder.name.text = pokemon.name
            if(is_default == true) {
                holder.star.visibility = View.VISIBLE
            }
            else {
                holder.star.visibility = View.INVISIBLE
            }
            holder.itemView.setOnClickListener{
                  if (onClickListener != null){
                      onClickListener!!.onClick(pos, variety[pos])
                  }
            }
        }
    }
    fun setOnClickListener(onClickListener: OnClickListener){
        this.onClickListener = onClickListener
    }
    interface OnClickListener{
        fun onClick(pos: Int, model: Variety)
    }
    override fun getItemCount(): Int {
        return variety.size
    }
}
