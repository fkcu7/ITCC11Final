/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pointofsale;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.logging.*;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author fluyg
 */

class ListDisplay 
{
    private String name;
    private int quantity;
    private float subtotal;
    private Runnable onUpdateCallback;

    ListDisplay(String name, int quantity, float subtotal) 
    {
        this.name = name;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public String getName() 
    {
        return name;
    }

    public int getQuantity() 
    {
        return quantity;
    }

    public float getSubtotal() 
    {
        return subtotal;
    }

    @Override
    public String toString() 
    {
        return String.format("%-10d %-25s PHP%-10.2f \n", quantity, name, subtotal);
    }

    void setQuantity(int newQuantity) 
    {
        this.quantity = newQuantity;
        notifyUpdate();
    }

    void setSubtotal(float newSubtotal) 
    {
        this.subtotal = newSubtotal;
        notifyUpdate();
    }

    void setOnUpdateCallback(Runnable onUpdateCallback) 
    {
        this.onUpdateCallback = onUpdateCallback;
    }

    private void notifyUpdate() 
    {
        if (onUpdateCallback != null) 
        {
            onUpdateCallback.run();
        }
    }
}

interface windows
{
    public void container();
}

class System implements windows 
{
    private Frame frame;
    private Panel panel, foodmenu, drinksmenu, order;
    private Label label;
    private Button cancel, confirm, foods, drinks, report, f1, f2, f3, f4, f5, f6, d1, d2, d3, d4, d5, d6;
    private LinkedList<ListDisplay> orderList = new LinkedList<>();
    private TextArea orderTextArea;
    
    System()
    {
        frame = new Frame("ITCC11 Final - Flores, Toyogon, Uyguangco, Ylanan");
        frame.setSize(1024, 768);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        panel = new Panel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(null);

        foodmenu = new Panel();
        drinksmenu = new Panel();
        order = new Panel();
        
        container();
        orders();
        showFoodMenu();

        frame.add(panel);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent e) 
            {
                java.lang.System.exit(0);
            }
        });
    }

    @Override
    public void container() 
    {
        label = new Label("Point of Sale System");
        label.setBounds(300, 2, 1024, 45);
        label.setForeground(Color.white);
        label.setFont(new Font("Verdana", Font.BOLD, 40));
        panel.add(label);

        foods = new Button("Food Menu");
        foods.setBounds(0, 50, 350, 50);
        foods.setFont(new Font("Verdana", Font.BOLD, 12));
        foods.setBackground(Color.black);
        foods.setForeground(Color.white);
        foods.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                showFoodMenu();
            }
        });
        panel.add(foods);

        drinks = new Button("Drinks Menu");
        drinks.setBounds(350, 50, 350, 50);
        drinks.setFont(new Font("Verdana", Font.BOLD, 12));
        drinks.setBackground(Color.black);
        drinks.setForeground(Color.white);
        drinks.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                showDrinksMenu();
            }
        });
        panel.add(drinks);

        foodmenu.setBackground(new Color(0xCB534F)); // Red
        foodmenu.setBounds(0, 100, 700, 600);
        foodmenu.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        foodmenu.setVisible(false);
        panel.add(foodmenu);
        
        drinksmenu.setBackground(new Color(0x4FAED9)); // Blue
        drinksmenu.setBounds(0, 100, 700, 600);
        drinksmenu.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        drinksmenu.setVisible(false);
        panel.add(drinksmenu);
    }

    private void orders() 
    {
        order.setBackground(Color.WHITE);
        order.setBounds(701, 50, 350, 650);
        order.setLayout(null);
        order.setVisible(true);
        panel.add(order);

        orderTextArea = new TextArea();
        orderTextArea.setBounds(10, 10, 290, 480);
        orderTextArea.setEditable(false);
        orderTextArea.setEnabled(false);
        order.add(orderTextArea);

        confirm = new Button("Place Order");
        confirm.setPreferredSize(new Dimension(200, 50));
        confirm.setBackground(Color.GREEN);
        confirm.setForeground(Color.white);
        confirm.setBounds(30, 500, 120, 50);
        confirm.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                confirm();
            }
        });
        order.add(confirm);

        cancel = new Button("Cancel Order");
        cancel.setPreferredSize(new Dimension(200, 50));
        cancel.setBackground(Color.RED);
        cancel.setForeground(Color.white);
        cancel.setBounds(165, 500, 120, 50);
        cancel.addActionListener(new ActionListener() 
        {   
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                cancel();
            }
        });
        order.add(cancel);
        
        report = new Button("Sales Report");
        report.setPreferredSize(new Dimension(200, 50));
        report.setBackground(Color.BLUE);
        report.setForeground(Color.white);
        report.setBounds(100, 580, 120, 50);
        report.addActionListener(new ActionListener() 
        {   
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                SalesReport report = new SalesReport();
                report.container();
            }
        });
        order.add(report);
    }
    
    private void showFoodMenu()
    {       
        foodmenu.setVisible(true);
        drinksmenu.setVisible(false);

        foodmenu.removeAll();

        f1 = new Button("Spicy Buffalo Wings");
        f1.setPreferredSize(new Dimension(200, 50));
        f1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                add("Spicy Buffalo Wings", 1, (float) 10.00);
            }
        });
        foodmenu.add(f1);

        f2 = new Button("Honey Lemon Glazed Wings");
        f2.setPreferredSize(new Dimension(200, 50));
        f2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                add("Honey Lemon Glazed Wings", 1, (float) 12.00);
            }
        });
        foodmenu.add(f2);

        f3 = new Button("Hawaiian Overload Pizza");
        f3.setPreferredSize(new Dimension(200, 50));
        f3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                add("Hawaiian Overload Pizza", 1, (float) 15.00);
            }
        });
        foodmenu.add(f3);

        f4 = new Button("Spicy Ramen Noodles");
        f4.setPreferredSize(new Dimension(200, 50));
        f4.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                add("Spicy Ramen Noodles", 1, (float) 8.00);
            }
        });
        foodmenu.add(f4);

        f5 = new Button("Beef Bulgogi");
        f5.setPreferredSize(new Dimension(200, 50));
        f5.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                add("Beef Bulgogi", 1, (float) 14.00);
            }
        });
        foodmenu.add(f5);

        f6 = new Button("Chicken Katsu");
        f6.setPreferredSize(new Dimension(200, 50));
        f6.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                add("Chicken Katsu", 1, (float) 12.00);
            }
        });
        foodmenu.add(f6);

        foodmenu.revalidate();
        foodmenu.repaint();
    }
    
    private void showDrinksMenu() 
    {
        drinksmenu.setVisible(true);
        foodmenu.setVisible(false);

        drinksmenu.removeAll();

        d1 = new Button("Coca Cola");
        d1.setPreferredSize(new Dimension(200, 50));
        d1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                add("Coca Cola", 1, (float) 2.00);
            }
        });
        drinksmenu.add(d1);

        d2 = new Button("Sprite");
        d2.setPreferredSize(new Dimension(200, 50));
        d2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                add("Sprite", 1, (float) 2.00);
            }
        });
        drinksmenu.add(d2);

        d3 = new Button("Orange Juice");
        d3.setPreferredSize(new Dimension(200, 50));
        d3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                add("Orange Juice", 1, (float) 3.00);
            }
        });
        drinksmenu.add(d3);

        d4 = new Button("Lemonade");
        d4.setPreferredSize(new Dimension(200, 50));
        d4.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                add("Lemonade", 1, (float) 3.00);
            }
        });
        drinksmenu.add(d4);

        d5 = new Button("Iced Tea");
        d5.setPreferredSize(new Dimension(200, 50));
        d5.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                add("Iced Tea", 1, (float) 2.50);
            }
        });
        drinksmenu.add(d5);

        d6 = new Button("Coffee");
        d6.setPreferredSize(new Dimension(200, 50));
        d6.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                add("Coffee", 1, (float) 2.50);
            }
        });
        drinksmenu.add(d6);

        drinksmenu.revalidate();
        drinksmenu.repaint();
    }
    
    private void add(String name, int quantity, float subtotal) 
    {
        boolean itemExists = false;
        for (ListDisplay item : orderList) 
        {
            if (item.getName().equals(name)) 
            {
                int newQuantity = item.getQuantity() + quantity;
                float newSubtotal = item.getSubtotal() + subtotal;
                item.setQuantity(newQuantity);
                item.setSubtotal(newSubtotal);
                itemExists = true;
                break;
            }
        }
        if (!itemExists) 
        {
            ListDisplay newItem = new ListDisplay(name, quantity, subtotal);
            newItem.setOnUpdateCallback(this::updateOrderDisplay);
            orderList.add(newItem);
        }
        updateOrderDisplay();
    }
    
    private void updateOrderDisplay() 
    {
        StringBuilder display = new StringBuilder();
        for (ListDisplay item : orderList) 
        {
            display.append(item.toString());
        }
        orderTextArea.setText(display.toString());
    }
    
    private void confirm() 
    {
        if (orderList.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"No items has been added. Order cancelled.");
        }
        else
        {
            try 
            {
               Class.forName("com.mysql.cj.jdbc.Driver");
            } 
            catch (ClassNotFoundException ex) 
            {
                Logger.getLogger(System.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }

            try (Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/salessystem", "root", "1234")) 
            {
                float totalSubtotal = 0;
                for (ListDisplay item : orderList) 
                {
                    totalSubtotal += item.getSubtotal();
                }

                String insertOrderSQL = "INSERT INTO orders (total) VALUES (?)";
                try (PreparedStatement orderStmt = connect.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS)) 
                {
                    orderStmt.setFloat(1, totalSubtotal);
                    int affectedRows = orderStmt.executeUpdate();

                    if (affectedRows == 0) 
                    {
                        Logger.getLogger(System.class.getName()).log(Level.SEVERE, "Inserting order failed, no rows affected.");
                        return;
                    }
                    try (ResultSet generatedKeys = orderStmt.getGeneratedKeys()) 
                    {
                        if (generatedKeys.next()) 
                        {
                            int orderNum = generatedKeys.getInt(1);

                            String insertSalesSQL = "INSERT INTO sales (orderNum, itemName, quantity, subtotal) VALUES (?, ?, ?, ?)";
                            try (PreparedStatement salesStmt = connect.prepareStatement(insertSalesSQL)) 
                            {
                                for (ListDisplay item : orderList) 
                                {
                                    salesStmt.setInt(1, orderNum);
                                    salesStmt.setString(2, item.getName());
                                    salesStmt.setInt(3, item.getQuantity());
                                    salesStmt.setFloat(4, item.getSubtotal());
                                    salesStmt.executeUpdate();
                                }
                            }
                            Logger.getLogger(System.class.getName()).log(Level.INFO, "Sales data successfully added to the database.");
                        } 
                        else 
                        {
                            Logger.getLogger(System.class.getName()).log(Level.SEVERE, "No orderNum generated.");
                        }
                    }
                }
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(System.class.getName()).log(Level.SEVERE, null, ex);
            }
            orderList.clear();
            updateOrderDisplay();
        }
    }

    private void cancel() 
    {
        orderList.clear();
        updateOrderDisplay();
    }
}

class SalesReport implements windows
{
    private Frame frame;
    private Panel panel;
    private Label label;
    private JTextArea ordernumArea, itemsArea, totalArea;
    private JScrollPane ordernumScroll, itemsScroll, totalScroll;
    
    @Override
    public void container() 
    {
        frame = new Frame("ITCC11 Final - Flores, Toyogon, Uyguangco, Ylanan");
        frame.setSize(550, 768);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        panel = new Panel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null);
        
        label = new Label("Sales Report");
        label.setBounds(151, 2, 530, 45);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Verdana", Font.BOLD, 40));
        panel.add(label);  
        
        UIManager.put("ScrollPane.border", BorderFactory.createLineBorder(Color.WHITE));
                
        ordernumArea = new JTextArea();
        ordernumArea.setEditable(false);
        ordernumArea.setEnabled(false);
        ordernumScroll = new JScrollPane(ordernumArea);
        ordernumScroll.setBounds(0, 50, 150, 750);
        ordernumScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        ordernumScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(ordernumScroll);
        
        itemsArea = new JTextArea();
        itemsArea.setEditable(false);
        itemsArea.setEnabled(false);
        itemsScroll = new JScrollPane(itemsArea);
        itemsScroll.setBounds(150, 50, 250, 750);
        itemsScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        itemsScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(itemsScroll);
        
        totalArea = new JTextArea();
        totalArea.setEditable(false);
        totalArea.setEnabled(false);
        totalScroll = new JScrollPane(totalArea);
        totalScroll.setBounds(400, 50, 150, 750);
        totalScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        totalScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(totalScroll);
        
        Report();
        
        frame.setVisible(true);
        frame.add(panel);

        frame.addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent e) 
            {
                frame.setVisible(false);
            }
        });
    }
    
    private void Report()
    {
        int counter;
        try 
        {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/salessystem", "root", "1234");

            String ordersQuery = "SELECT orderNum, total FROM orders";
            Statement statement = connection.createStatement();
            ResultSet ordersResult = statement.executeQuery(ordersQuery);

            while (ordersResult.next()) 
            {
                counter = 0;
                StringBuilder result = new StringBuilder();
                String orderNum = ordersResult.getString("orderNum");
                String total = ordersResult.getString("total");

                String itemsQuery = "SELECT itemName, quantity FROM sales WHERE orderNum = ?";
                PreparedStatement itemsStatement = connection.prepareStatement(itemsQuery);
                itemsStatement.setString(1, orderNum);
                ResultSet salesResult = itemsStatement.executeQuery();
                ordernumArea.append("ORDER NUMBER: " + orderNum + "\n");
                totalArea.append("TOTAL: " + total + "PHP\n");
                
                while (salesResult.next()) 
                {
                    String itemName = salesResult.getString("itemName");
                    String quantity = salesResult.getString("quantity");
                    result.append(itemName).append(" x").append(quantity).append("\n");
                    if (counter == 1)
                    {
                        ordernumArea.append("\n");
                        totalArea.append("\n");
                    }
                    else
                    {
                        counter++;
                    }
                }
                itemsArea.append(result.toString());
                ordernumArea.append("------------------------------------------------------\n");
                itemsArea.append("--------------------------------------------------------------\n");
                totalArea.append("------------------------------------------------------\n");
                
                salesResult.close();
                itemsStatement.close();
            }

            ordersResult.close();
            statement.close();
            connection.close();
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
}

public class PointOfSale 
{
    public static void main(String[] args) 
    {
        new System();
    }  
}