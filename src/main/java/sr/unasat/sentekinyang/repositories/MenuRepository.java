package sr.unasat.sentekinyang.repositories;

import sr.unasat.sentekinyang.entities.Menu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuRepository {
    private Connection connection;

    public MenuRepository() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("De driver is geregistreerd!");

            String URL = "jdbc:mysql://localhost:3306/restaurant?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            String USER = "root";
            String PASS = "root";
            connection = DriverManager.getConnection(URL, USER, PASS);
//            System.out.println(connection);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Menu> findAllMenus() {
        List<Menu> menuList = new ArrayList<Menu>();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            String sql = "SELECT * FROM menu INNER JOIN restaurant ON restaurant.restaurant_id=menu.restaurant_id";
            ResultSet rs = stmt.executeQuery(sql);
            //STEP 5: Extract data from result set
            while (rs.next()) {
                int menu_id = rs.getInt("menu_id");
                int restaurant_id = rs.getInt("restaurant_id");
                String menu_naam = rs.getString("menu_naam");
                String restaurant_naam = rs.getString("restaurant_naam");
                String restaurant_adres = rs.getString("restaurant_adres");
                String restaurant_telefoon = rs.getString("restaurant_telefoon");
                int prijs = rs.getInt("prijs");

                menuList.add(new Menu(menu_id, restaurant_id, menu_naam, restaurant_naam, restaurant_adres, restaurant_telefoon, prijs));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
        return menuList;
    }
}
