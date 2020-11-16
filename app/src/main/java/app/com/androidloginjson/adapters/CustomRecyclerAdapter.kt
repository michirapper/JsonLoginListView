package app.com.androidloginjson.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.com.androidloginjson.R
import app.com.androidloginjson.UserList
import app.com.androidloginjson.model.Item



class CustomRecyclerAdapter(val itemList: ArrayList<Item>,private val id : String, private val actividad: UserList, private val click : (Item)-> Unit) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MiViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.custom_item_list,parent,false)
        val viewHolder = MiViewHolder(v)
        viewHolder.actividad = actividad
        viewHolder.id = id
        return viewHolder
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MiViewHolder, position: Int, payloads: MutableList<Any>) {
        holder.bindItems(itemList[position])
        holder.itemView.setOnClickListener{click(itemList[position])}
    }


    class MiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bandera: ImageView
        val campo1: TextView
        val campo2: TextView
        lateinit var actividad: UserList
        lateinit var id : String

        init {
            bandera = itemView.findViewById(R.id.bandera) as ImageView
            campo1 = itemView.findViewById<TextView>(R.id.campo1)
            campo2 = itemView.findViewById(R.id.campo2) as TextView
        }

        fun bindItems(item: Item) {
            val bandera = itemView.findViewById(R.id.bandera) as ImageView
            val campo1 = itemView.findViewById<TextView>(R.id.campo1)
            val campo2 = itemView.findViewById(R.id.campo2) as TextView



            campo1.text = item.name + " " + item.apellido
            campo2.text = item.user


            if (item.id.equals(id) ) {
                bandera.setImageResource(R.drawable.ic_launcher_background)
                bandera.setOnClickListener{actividad.clickImage("conectado")}
            } else {
                bandera.setImageResource(R.drawable.ic_launcher_foreground)
                bandera.setOnClickListener{actividad.clickImage("No conectado")}
            }
        }
    }

    override fun onBindViewHolder(holder: MiViewHolder, position: Int) {
        holder.bindItems(itemList[position])
    }
}
