/*------------------------------------------------------
My name: Sean Overton
My student number: 6421490
My course code: CSIT121
My email address: so412@uowmail.edu.au
Assignment number: Final Project
-------------------------------------------------------*/

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.io.*;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.lang.SecurityException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.List;

class Item{
    private String name; 
    private String description;
    private double price; 
    private int quantity; 

    //default constructor
    public Item(){
        this("default", "default", 0);
    }

    //paramterised constructor
    public Item(String name, String description, double price){
        this.name = name;
        this.description = description;
        this.price = price;
    }

    //other methods
    public void setName(String name){
        this.name = name;
    } 

    public String getName(){
        return this.name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public double getPrice(){
        return this.price;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    
    public int getQuantity(){
        return this.quantity;
    }

    public String toString(){
        //use get methods is better practice in case class is extended in future
        return getName() + "             $" + getPrice();
    }
}

class Menu{
    private ArrayList<Item> pizzas;
    private ArrayList<Item> burgers;
    private ArrayList<Item> sides;

    //default constructor initialises all fields
    public Menu(){
        this.pizzas = new ArrayList<Item>();
        this.burgers = new ArrayList<Item>();
        this.sides = new ArrayList<Item>();
    }

    public ArrayList<Item> getPizzas(){
        return this.pizzas;
    } 

    public ArrayList<Item> getBurgers(){
        return this.burgers;
    }

    public ArrayList<Item> getSides(){
        return this.sides;
    }

    public void setPizzas(ArrayList<Item> pizzas){
        this.pizzas = pizzas;
    }

    public void setBurgers(ArrayList<Item> burgers){
        this.burgers = burgers;
    } 
    
    public void setSides(ArrayList<Item> sides){
        this.sides = sides;
    }
}

class Order{
    private double totalPrice;
    private ArrayList<Item> items; 
    private String customerName;
    private int tableNumber;

    //default constructor
    public Order(){
        this.totalPrice = 0;
        this.items = new ArrayList<Item>();
    }

    public void setCustomerName(String name){
        this.customerName = name;
    }

    public String getCustomerName(){
        return this.customerName;
    }

    public void setTableNumber(int tableNumber){
        this.tableNumber = tableNumber;
    }

    public int getTableNumber(){
        return this.tableNumber;
    }

    public void addItem(Item item){
        //continually updates price as new items added
        this.totalPrice += item.getPrice() * item.getQuantity();
        this.items.add(item);
    }

    public double getTotalPrice(){
        return this.totalPrice;
    }
    
    public Item getItem(int index){
        return this.items.get(index);
    }

    public String toString(){
        String order = "";

        if(getCustomerName() != null || getTableNumber() != 0){
            order += "Customer name: " + getCustomerName() + ", Table Number: " + getTableNumber() + "\n\n";
        }

        for(int i = 0; i < this.items.size(); i++){
            Item item = this.items.get(i);
            double price = item.getPrice() * item.getQuantity();
            order += item.getName() + "      Quantity: " + item.getQuantity() 
            + "\t$" + Double.toString(price) + "\n";
        }
        
        order += "\n" + "--------------" + "\n" + "Total Price: $" + getTotalPrice();
        
        return order;
    }
}

//this object works as the controller type object
class CafeOrderSystem{
    private static Order order = new Order();
    private static MenuFrame mainFrame; 
    private static Formatter output; 

    public static void main(String[] args){
        mainFrame = new MenuFrame();

        Menu menu = createMenu();

        mainFrame.setMenu(menu);
        mainFrame.setVisible(true); 
    }

    public static Menu createMenu(){
        //create all items
        ArrayList<Item> burgers = new ArrayList<Item>();
        ArrayList<Item> pizzas = new ArrayList<Item>();
        ArrayList<Item> sides = new ArrayList<Item>();

        burgers.add(new Item("Wagyu Burger", "Wagyu beef, bacon, tomato, mesculin, beetroot and aoli on a lightly toasted brioche bun.\n Served with chips.", 16.5));
        burgers.add(new Item("Cheeseburger", "Milk bun toppped with a beef patty, cheese, tomato and mustard. Served with chips.", 10.0));
        burgers.add(new Item("Halloumi Burger", "Milk bun topped with rocker, halloumi, egg and tomato relish. Served with chips.", 10.0));
        burgers.add(new Item("Steak Sandwich", "120g rib fillet steak with caramelised onion, lettuce, cheese, tomato, beetroot and \nbarbeque sauce on a toasted sandwich ciabatta. Served with chips.", 18.5));
        burgers.add(new Item("Chicken Wrap", "Southern chicken tenders wrapped in soft tortilla with sweet chilli aioli, lettuce, \ncheese, tomato and carrot. Served with chips.", 9.9));

        pizzas.add(new Item("Tropicana Pizza", "Ham and pineapple served on a tomato base with mozzarella cheese.", 10));
        pizzas.add(new Item("BBQ Meat Lovers Pizza", "Beef, bacon, ham, pepperoni, spanish onion, cherry tomatoes and mozzarella \ncheese.", 16));
        pizzas.add(new Item("Pepperoni Pizza", "Tomato base, chilli flakes, pepperoni, spinach and mozzarella cheese.", 9));
        pizzas.add(new Item("Chicken, Mushroom and Brie Pizza", "Tomato base, chicken, mushroom, spinach, brie and mozzarella \ncheese.", 14));
        pizzas.add(new Item("Garlic Pizza", "Confit garlic, mozzarella cheese, rosemary topped with smoked salt.", 8));
        pizzas.add(new Item("Slow-Roasted Lamb Pizza", "Tomato base with slow-roasted lamb, rocket, sumac, tzatziki and mozzarella \ncheese.", 12));
        pizzas.add(new Item("Green Pizza", "Basil pesto base topped with rocket, broccoli, green olives and bocconcini.", 12));

        sides.add(new Item("Sweet potato fries (Bowl)", "Served with tomato relish", 6.5));
        sides.add(new Item("Chips (Bowl)", "Served with aioli, tomato sauce and barbeque sauce.", 5));
        sides.add(new Item("Wedges (Bowl)", "Served with sweet chilli sauce and sour cream.", 6.5));
        sides.add(new Item("Side salad", "", 5));


        //create menu
        Menu menu = new Menu();

        //add items to menu
        menu.setBurgers(burgers);
        menu.setPizzas(pizzas);
        menu.setSides(sides);

        return menu;
    }

    public static MenuFrame getMainFrame(){
        return mainFrame;
    }

    public static Order getOrder(){
        return order;
    }

    public static void openTextFile(String name, String tableNumber){
        try{  
            output = new Formatter(name + "_" + tableNumber + ".txt");
        }
        catch(SecurityException e){
            //change this to a pop up panel
            System.err.println("Write Permission Denied. Terminating.");
            System.exit(1); //terminates 
        }
        catch(FileNotFoundException e){
            //change this to a pop up panel
            System.err.println("Error opening file. Terminating.");
            System.exit(1); //terminates 
        }
    }

    public static void saveOrder(){
        try{
            output.format(getOrder().toString());
        }
        catch(FormatterClosedException e){
            //TODO change this to a JOption pop up
            System.err.println("Formatter closed. Terminating.");
        }
    }

    public static void closeTextFile(){
        if(output != null){
            output.close();
        }
    }
    
    public static void addItems(){
        //create item object from valid inputs
        Item item = getMainFrame().getItemPanel().getValue();

        /*TODO make a copy of the item because adding 
        currently causes bugs duplicates mess up*/

        int quantity = Integer.parseInt(getMainFrame().getQuantityPanel().getValue());
        item.setQuantity(quantity);

        //add item to order
        getOrder().addItem(item);

        //update order displayed on GUI using toString of order object
        getMainFrame().getOrderPanel().update(getOrder().toString());


    }

    public static void reset(){
        order = new Order();
        getMainFrame().reset();
    }
}

class MenuFrame extends JFrame{
    private GridLayout layout;
    private Menu menu;
    private CategoryPanel categoryPanel;
    private ItemPanel itemPanel; 
    private InputPanel namePanel;
    private InputPanel tableNumberPanel;
    private InputPanel quantityPanel;
    private DescriptionPanel descriptionPanel; 
    private OrderPanel orderPanel; 
    private ButtonPanel buttonPanel;

    public MenuFrame(){
        super("Cafe Order System");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        layout = new GridLayout(4, 2);
        setLayout(layout);

        this.menu = new Menu();
        String[] x = {"Pizzas", "Burgers", "Sides"};
        this.categoryPanel = new CategoryPanel(x);
        this.itemPanel = new ItemPanel();
        this.namePanel = new InputPanel("Name: ");
        this.tableNumberPanel = new InputPanel("Table Number: " );
        this.quantityPanel = new InputPanel("Quantity: ");
        this.orderPanel = new OrderPanel();
        this.descriptionPanel = new DescriptionPanel();
        this.buttonPanel = new ButtonPanel();

        add(this.namePanel);
        add(this.tableNumberPanel);
        add(this.categoryPanel);
        add(this.itemPanel);
        add(this.descriptionPanel);
        add(this.quantityPanel);
        add(this.orderPanel);
        add(this.buttonPanel);    
    }
    
    public Menu getMenu(){
        return this.menu;
    }

    public void setMenu(Menu menu){
        this.menu = menu;
    }

    public CategoryPanel getCategoryPanel(){
        return this.categoryPanel;
    }

    public DescriptionPanel getDescriptionPanel(){
        return this.descriptionPanel;
    }

    public ItemPanel getItemPanel(){
        return this.itemPanel;
    }

    public InputPanel getNamePanel(){
        return this.namePanel;
    }

    public InputPanel getTableNumberPanel(){
        return tableNumberPanel;
    }

    public InputPanel getQuantityPanel(){
        return this.quantityPanel;
    }

    public OrderPanel getOrderPanel(){
        return this.orderPanel;
    }

    public ButtonPanel getButtonPanel(){
        return this.buttonPanel;
    }

    public void reset(){
        getCategoryPanel().reset();
        getItemPanel().reset(); 
        getNamePanel().reset();
        getTableNumberPanel().reset();
        getQuantityPanel().reset(); 
        getOrderPanel().reset();
        getDescriptionPanel().reset(); 
    }

    public void validateCustomerName() throws Exception{
        try{
            if(getNamePanel().getValue().equals("")){
                throw new Exception();
            }

            CafeOrderSystem.getOrder().setCustomerName(getNamePanel().getValue());
        }
        catch(Exception e){
            //TODO add specific erro handlers
            JOptionPane.showMessageDialog(getNamePanel(), 
                "Please eneter a name.", 
                "Invalid name!", 
                JOptionPane.ERROR_MESSAGE);
            throw new Exception();
        }
    }

    public void validateTableNumber() throws Exception{
        try{
            int num = Integer.parseInt(getTableNumberPanel().getValue());

            CafeOrderSystem.getOrder().setTableNumber(num);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(getTableNumberPanel(), 
                "Please enter an integer.",  
                "Invalid Table Number!",
                JOptionPane.ERROR_MESSAGE);
            throw new Exception();
        }
    }

    public void successfulSubmitHandler(){
        JOptionPane.showMessageDialog(this, 
                "Text file has been generated.",  
                "Order was placed successfully!",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void unsuccessfulSubmitHandler(){
        JOptionPane.showMessageDialog(this, 
                "Text file has NOT been generated.",  
                "Order was unsuccessful!",
                JOptionPane.ERROR_MESSAGE);
    }
}

class ButtonPanel extends JPanel{
    private JButton addButton; 
    private JButton submitButton;
    private JButton resetButton;

    public ButtonPanel(){
        this.addButton = new JButton("Add item");
        this.resetButton = new JButton("Reset");
        this.submitButton = new JButton("Submit");

        this.addButton.addActionListener(new AddButtonHandler());
        this.resetButton.addActionListener(new ResetButtonHandler());
        this.submitButton.addActionListener(new SubmitButtonHandler());

        add(this.addButton); 
        add(this.resetButton);
        add(this.submitButton);   
        
    }

    public JButton getAddButton(){
        return this.addButton;
    }

    public JButton getSubmitButton(){
        return this.submitButton;
    }
    
    public JButton getResetButton(){
        return this.resetButton;
    }
}

class ItemPanel extends JPanel{
    private BorderLayout layout;
    private JLabel title = new JLabel("Items:"); 
    private JList options;
    private DefaultListModel optionsModel = new DefaultListModel();
    private ItemSelectionListener listener = new ItemSelectionListener();
    
    //default constructor
    public ItemPanel(){
        this.layout = new BorderLayout();

        this.options = new JList(this.optionsModel);

        this.options.addListSelectionListener(this.listener);

        //add items to panel
        add(this.title, BorderLayout.NORTH);
        add(this.options, BorderLayout.CENTER);
    }

    private class ItemSelectionListener implements ListSelectionListener{
        private boolean active = true;

        /*this function allows listener to be made not functional when
        adjustments need to be made to the model or jlist*/ 
        public void setActive(boolean state){
            this.active = state;
        }

        @Override 
        public void valueChanged(ListSelectionEvent event){
            if(this.active){
                String description = "";
                //needs to reference what the select 
                if (!event.getValueIsAdjusting()) {
                  Item item = (Item)options.getSelectedValue();
                  description = item.getDescription();
                }

                //change displayed options of items panel
                CafeOrderSystem.getMainFrame().getDescriptionPanel().changeValue(description);
            }
        }
    }

    public void changeOptions(ArrayList<Item> options){
        //also need to disable event listener
        this.disableListener();

        /*need to remove all previous options first 
        otherwise just continues to add to the JList*/
        this.optionsModel.removeAllElements();

        //adding new options from argument
        for(int i = 0; i < options.size(); i++){
            this.optionsModel.addElement(options.get(i));
        }

        //enable event listener for normal function again
        this.enableListener();
    }

    public void reset(){
        this.optionsModel.removeAllElements();
    }

    public Item getValue(){
        //have to check what this actually returns 
        return (Item)this.options.getSelectedValue();
    }
    
    public void disableListener(){
        this.listener.setActive(false);
    }

    public void enableListener(){
        this.listener.setActive(true);
    }
}

class CategoryPanel extends JPanel{
    private BorderLayout layout;
    private JLabel title = new JLabel("Category:"); 
    private JList options;
    private DefaultListModel optionsModel = new DefaultListModel();
    private CategorySelectionListener listener = new CategorySelectionListener();

    //paramterised constructor
    public CategoryPanel(String[] options){
        this.layout = new BorderLayout();

        //add options to model
        for(int i = 0; i < options.length; i++){
            this.optionsModel.addElement(options[i]);
        }

        this.options = new JList(this.optionsModel);

        this.options.addListSelectionListener(this.listener);

        //add items to panel
        add(this.title, BorderLayout.NORTH);
        add(this.options, BorderLayout.CENTER);
    }

    private class CategorySelectionListener implements ListSelectionListener{       
        private boolean active = true;

        /*this function allows listener to be made not functional when
        adjustments need to be made to the model or jlist*/ 
        public void setActive(boolean state){
            this.active = state;
        }

        @Override 
        public void valueChanged(ListSelectionEvent event){
            if(this.active){
                int choice = options.getSelectedIndex();

                ArrayList<Item> items = new ArrayList<Item>();

                switch(choice){
                    case 0:
                        items = CafeOrderSystem.getMainFrame().getMenu().getPizzas();
                        break;
                    case 1:
                        items = CafeOrderSystem.getMainFrame().getMenu().getBurgers();
                        break;
                    case 2:
                        items = CafeOrderSystem.getMainFrame().getMenu().getSides();
                        break;
                }

                //change displayed options of items panel
                CafeOrderSystem.getMainFrame().getItemPanel().changeOptions(items);
            }
        }
    }

    public void reset(){
        this.options.clearSelection();
    }

    public String getValue(){
        //have to check what this actually returns 
        return (String)this.options.getSelectedValue();
    }
}

class DescriptionPanel extends JPanel{ 
    private BorderLayout layout;
    private JLabel title = new JLabel("Description:");
    private JTextArea description = new JTextArea(2, 40);

    //default constructor
    public DescriptionPanel(){
        add(this.title, BorderLayout.NORTH);
        add(this.description, BorderLayout.CENTER);
    }

    public void changeValue(String description){
        this.description.setText(description);
    }

    public void reset(){
        this.description.setText("");
    }
}

class InputPanel extends JPanel{
    private final FlowLayout layout;
    private JLabel title; 
    private JTextField input;

    //default constructor
    public InputPanel(){
        this("default");
    }

    //parameterised constructor
    public InputPanel(String title){
        layout = new FlowLayout();
        this.title = new JLabel(title);
        this.input = new JTextField(25);
        
        add(this.title);
        add(this.input);
    }
    
    public void setTitle(String title){
        this.title.setText(title);
    }

    public void reset(){
        this.input.setText("");
    }
    
    public String getValue(){
        return this.input.getText();
    }
}

class OrderPanel extends JPanel{  
    private JLabel title; 
    private JTextArea order; 
    
    public OrderPanel(){
        this.title = new JLabel("Order: ");
        this.order = new JTextArea(15, 50);

        add(title);
        add(order);
    }
    
    public void update(String updatedOrder){
        this.order.setText(updatedOrder);
    }
    
    public void reset(){
        this.order.setText("");
    }
}

class ResetButtonHandler implements ActionListener{
    public void actionPerformed(ActionEvent e){
        CafeOrderSystem.reset();
    }
}

class AddButtonHandler implements ActionListener{
    public void actionPerformed(ActionEvent e){
        CafeOrderSystem.addItems();
    }
}

class SubmitButtonHandler implements ActionListener{
    public void actionPerformed(ActionEvent event){
        try{
            CafeOrderSystem.getMainFrame().validateCustomerName();
            CafeOrderSystem.getMainFrame().validateTableNumber();
            CafeOrderSystem.openTextFile(CafeOrderSystem.getMainFrame().getNamePanel().getValue(), 
            CafeOrderSystem.getMainFrame().getTableNumberPanel().getValue());
            CafeOrderSystem.saveOrder();
            CafeOrderSystem.closeTextFile();
            CafeOrderSystem.getMainFrame().successfulSubmitHandler();
        }
        catch(Exception exception){
            CafeOrderSystem.getMainFrame().unsuccessfulSubmitHandler();
        }
        
    }
}




