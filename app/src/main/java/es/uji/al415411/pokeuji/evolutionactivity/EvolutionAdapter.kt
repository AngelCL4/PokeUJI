package es.uji.al415411.pokeuji.evolutionactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import es.uji.al415411.pokeuji.R
import es.uji.al415411.pokeuji.models.Chain

class EvolutionAdapter(val evolutions: List<Chain?>): RecyclerView.Adapter<EvolutionAdapter.ViewHolder>() {
    private var onClickListener: OnClickListener? = null
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.evolutionName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.evolution_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        with(evolutions[pos]) {
            var tree : String = ""
            if (pos+1 == evolutions.size){
                tree = "└─ " + this!!.species.name
                holder.name.text = tree
            }
            else {
                tree = "├─ " + this!!.species.name
                holder.name.text = tree
            }
            holder.itemView.setOnClickListener{
                if (onClickListener != null){
                    onClickListener!!.onClick(pos, evolutions[pos])
                }
            }
        }
    }

    fun setOnClickListener(onClickListener: OnClickListener){
        this.onClickListener = onClickListener
    }
    interface OnClickListener{
        fun onClick(pos: Int, model: Chain?)
    }
    override fun getItemCount(): Int {
        return evolutions.size
    }
}