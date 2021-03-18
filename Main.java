import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Main {

    public static JFrame frame = new JFrame("Covid Equipments Tracker");
    public static JLabel mainLabel = new JLabel("  Inventory Management for PPE Equipments");
    public static Button addButton = new Button("ADD");
    public static Button removeButton = new Button("REMOVE");
    public static Button viewButton = new Button("VIEW");

    public static void mainFrame(){

        //Frame Size
        frame.setSize(500, 450);

        //Main Label Size
        mainLabel.setFont(new Font("Comic Sans MS", Font.BOLD,20));

        //Button Fonts
        addButton.setFont(new Font("Century Gothic", Font.BOLD,15));
        removeButton.setFont(new Font("Century Gothic", Font.BOLD,15));
        viewButton.setFont(new Font("Century Gothic", Font.BOLD,15));

        //Button Size
        addButton.setPreferredSize(new Dimension(250,100));
        removeButton.setPreferredSize(new Dimension(250,100));
        viewButton.setPreferredSize(new Dimension(250,100));

        //Panel-1
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(mainLabel, BorderLayout.NORTH);
        panel.add(Box.createRigidArea(new Dimension(30, 50)));
        panel.add(addButton,BorderLayout.CENTER);
        panel.add(removeButton,BorderLayout.SOUTH);
        panel.add(viewButton, BorderLayout.SOUTH);

        panel.setBackground(Color.CYAN);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
	// write your code here

        //mainFrame();
        String url = "jdbc:sqlserver://AAKASH-SURFACE;databaseName=Inventory";
        String username = "aakash_1999";
        String password = "test123";
        
        try {
            Connection connection = DriverManager.getConnection(url,username,password);

            /*String sql = "INSERT INTO tracker (Name, PPEType, Amount, Location)"
                    + "VALUES ('Aakash', 'Gloves', 15, 'Pennsylvania')";

            Statement statement = connection.createStatement();
            int rows = statement.executeUpdate(sql);

            if(rows > 0) {
                System.out.println("Row is inserted");
            }*/

             String sql = "SELECT * FROM tracker";
            Statement statement = connection.createStatement();

            ResultSet result = statement.executeQuery(sql);
            while(result.next()){
                int id = result.getInt("id");
                String name = result.getString("Name");
                String type = result.getString("PPEType");
                int amount = result.getInt("Amount");
                String location = result.getString("Location");

                System.out.printf("Id: %d | Name: %s | TypePPE: %s | Amount: %d | Location: %s|",id,name,type,amount,location);
                System.out.println("\n---------------------------------------------");
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Oops, there's an error: ");
            e.printStackTrace();
        }
    }
}
