package app.com.androidloginjson

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.com.androidloginjson.adapters.CustomRecyclerAdapter
import app.com.androidloginjson.model.Item
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader

class UserList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        val contexto: Context = this
//List View

        //var listview = findViewById<ListView>(R.id.listview)

//Grid View

//        var listview = findViewById<GridView>(R.id.gridview)
//
//        var arrayItems = leerLista()
//
//        val objetoIntent: Intent = intent
//        var id = objetoIntent.getStringExtra("id")
//        Toast.makeText(this, id, Toast.LENGTH_LONG).show()
//
//        var adapter = ItemListAdapter(this, arrayItems, id!!)
//
//        listview.adapter = adapter
//
//        listview.onItemClickListener = object : AdapterView.OnItemClickListener {
//
//            override fun onItemClick(parent: AdapterView<*>, view: View,
//                                     position: Int, id: Long) {
//
//                val itemValue = listview.getItemAtPosition(position) as Item
//                Toast.makeText(applicationContext, "Posicion :$position\nValor : ${itemValue.id + "-" + itemValue.name+ "-" + itemValue.apellido}", Toast.LENGTH_LONG).show()
//
//                var miIntent = Intent(contexto, Informacion::class.java)
//                miIntent.putExtra("id", itemValue.id)
//                startActivity(miIntent)
//
//
//            }
//        }

//Recycler View
        val objetoIntent: Intent = intent
        var id = objetoIntent.getStringExtra("id")
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        var arrayList = leerLista()
        var customAdapter = CustomRecyclerAdapter(arrayList, id!!,this){item -> click(item) }
        recyclerView.adapter = customAdapter

    }


    fun leerLista() : ArrayList<Item>{
        var arrayRdo = ArrayList<Item>()
        var nombreFichero = "ficheroInterno.json"
        var bufferedReader = BufferedReader(InputStreamReader(openFileInput(nombreFichero)))
        var textoLeido = bufferedReader.readLine()
        val todo = StringBuilder()
        while (textoLeido != null) {
            todo.append(textoLeido + "\n")
            textoLeido = bufferedReader.readLine()
        }
        textoLeido = todo.toString()
        bufferedReader.close()
        val jsonObject = JSONObject(textoLeido)
        val jsonArray = jsonObject.optJSONArray("usuarios")
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            arrayRdo.add(Item(jsonObject.toString()))
        }

//        var bufferedReaderRaw: BufferedReader = BufferedReader(InputStreamReader(resources!!.openRawResource(R.raw.lista)))
//        bufferedReaderRaw.forEachLine { linea -> arrayRdo.add(Item(linea)) }
//        bufferedReaderRaw.close()


        return arrayRdo

    }

    fun goRegistro(view: View) {
        var miIntent = Intent(this, Registro::class.java)
        startActivity(miIntent)
    }
    fun click(itemValue : Item){
        var miIntent = Intent(this, Informacion::class.java)
        miIntent.putExtra("id", itemValue.id)
        startActivity(miIntent)
        //Toast.makeText(applicationContext, "Valor :  ${itemValue.id}", Toast.LENGTH_LONG).show()
    }
    fun clickImage(texto : String){
        Toast.makeText(applicationContext, "Bandera : $texto", Toast.LENGTH_LONG).show()
    }
}