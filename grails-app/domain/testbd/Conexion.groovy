package testbd

class Conexion {
    String nombre
    String password

    static constraints = {
      nombre nombre :true, nullable: false
      password password :true, nullable: false
    }
    static mapping = {
        table schema: "[conexion]"
    }
}
