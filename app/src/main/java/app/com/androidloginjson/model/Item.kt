package app.com.androidloginjson.model

//import jdk.nashorn.internal.parser.JSONParser
import org.json.*


class Item {
    var id = ""
    var name = ""
    var apellido = ""
    var user = ""
    var contrasena = ""

    constructor(linea: String) {
        val json = JSONObject(linea)
        this.id = json.optString("id")
        this.name = json.optString("nombre")
        this.apellido = json.optString("Apellido")
        this.user = json.optString("user")
        this.contrasena = json.optString("contrasena")
    }
}