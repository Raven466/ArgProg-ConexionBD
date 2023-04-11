import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String[]> resultados = leerResultados();
        List<String[]> pronosticos = leerPronosticos();

        System.out.println("Ronda\tNombre equipo 1\tNombre equipo 2\tGoles equipo 1\tGoles equipo 2");
        for (String[] esteResultado : resultados) {
            System.out.println(esteResultado[0] + "\t" + esteResultado[1] + "\t" + esteResultado[2] + "\t" + esteResultado[3] + "\t" + esteResultado[4]);
        }

        System.out.println("\n\nNombre persona\tRonda\tNombre equipo 1\tNombre equipo 2\tGanador");
        for (String[] estepronosticos : pronosticos) {
            System.out.println(estepronosticos[0] + "\t" + estepronosticos[1] + "\t" + estepronosticos[2] + "\t" + estepronosticos[3] + "\t" + estepronosticos[4]);
        }

    }



    // Va a devolver una Lista con un arreglo de String que va a contener:
    // Posicion 0: Ronda
    // Posicion 1: Nombre equipo 1
    // Posicion 2: Nombre equipo 2
    // Posicion 3: Goles equipo 1
    // Posicion 4: Goles equipo 2
    public static List<String[]> leerResultados() {
        List<String[]> resultados = new ArrayList<>();

        // Cargamos el Driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error cargando el driver");
        }

        try {
            // Creamos la conexión
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10612293",
                    "sql10612293", "ACwUKDKvbY");
            Statement stmt = con.createStatement();

            // El Query que vamos a correr
            ResultSet rs = stmt.executeQuery("SELECT RONDA, E1.EQUIPO AS EQUIPO_1, E2.EQUIPO AS EQUIPO_2, GOLES_1, GOLES_2 FROM RESULTADOS R JOIN EQUIPOS E1 on R.ID_EQUIPO_1 = E1.ID_EQUIPO JOIN EQUIPOS E2 on R.ID_EQUIPO_2 = E2.ID_EQUIPO");
            while (rs.next()) {
                String[] fila = new String[5];
                fila[0] = rs.getString("RONDA");
                fila[1] = rs.getString("EQUIPO_1");
                fila[2] = rs.getString("EQUIPO_2");
                fila[3] = rs.getString("GOLES_1");
                fila[4] = rs.getString("GOLES_2");
                resultados.add(fila);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error con SQL");
        }

        return resultados;
    }

    // Va a devolver una Lista con un arreglo de String que va a contener:
    // Posicion 0: Nombre de la persona
    // Posicion 1: Ronda
    // Posicion 2: Nombre equipo 1
    // Posicion 3: Nombre equipo 2
    // Posicion 4: Ganador
    public static List<String[]> leerPronosticos() {
        List<String[]> pronosticos = new ArrayList<>();

        // Cargamos el Driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error cargando el driver");
        }

        try {
            // Creamos la conexión
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10612293",
                    "sql10612293", "ACwUKDKvbY");
            Statement stmt = con.createStatement();

            // El Query que vamos a correr
            ResultSet rs = stmt.executeQuery("SELECT NOMBRE, RONDA, E1.EQUIPO AS EQUIPO_1, E2.EQUIPO AS EQUIPO_2, GANADOR FROM PRONOSTICOS P JOIN RESULTADOS R on P.ID_RESULTADO = R.ID_RESULTADO JOIN EQUIPOS E1 on R.ID_EQUIPO_1 = E1.ID_EQUIPO JOIN EQUIPOS E2 on R.ID_EQUIPO_2 = E2.ID_EQUIPO");
            while (rs.next()) {
                String[] fila = new String[5];
                fila[0] = rs.getString("NOMBRE");
                fila[1] = rs.getString("RONDA");
                fila[2] = rs.getString("EQUIPO_1");
                fila[3] = rs.getString("EQUIPO_2");
                fila[4] = rs.getString("GANADOR");
                pronosticos.add(fila);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error con SQL");
        }

        return pronosticos;
    }
}
