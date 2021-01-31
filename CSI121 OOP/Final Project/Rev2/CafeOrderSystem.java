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

class Item implements Cloneable{
    private String name; 
    private String description;
    private double price; 
    private int quantity; 
    private String dietInfo;

    //default constructor
    public Item(){
        this("default", "default", 0);
    }

    //paramterised constructor
    public Item(String name, String description, double price){
        this(name, description, price, "");
    }

    public Item(String name, String description, double price, String dietInfo){
        this.name = name;
        this.description = description;
        this.price = price;
        this.dietInfo = dietInfo;
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

    public String getDietInfo(){
        return this.dietInfo;
    }

    public void setDietInfo(String dietInfo){
        this.dietInfo = dietInfo;
    }

    public Item clone() throws CloneNotSupportedException{
        return (Item) super.clone();
    }

    public String toString(){
        //use get methods is better practice in case class is extended in future
        return getName() + "             $" + getPrice() + "   " + getDietInfo();
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
            + "\t$" + Double.toString(price) + "     " + item.getDietInfo() + "\n";
        }
        
        order += "\n" + "--------------" + "\n" + "Total Price: $" + getTotalPrice();
        
        return order;
    }
}

//this object works as the controller type object
class CafeOrderSystem{
    private static Order order = new Order();
    private static MenuFrame mainFrame;
    private static Printer printer = new Printer(); 

    public static void main(String[] args){
        mainFrame = new MenuFrame();

        Menu membersMenu = createMembersMenu();
        Menu nonMembersMenu = createNonMembersMenu();

        mainFrame.setMembersMenu(membersMenu);
        mainFrame.setNonMembersMenu(nonMembersMenu);

        mainFrame.setVisible(true); 
    }

    public static Menu createMembersMenu(){
        ArrayList<Item> burgers = new ArrayList<Item>();
        ArrayList<Item> pizzas = new ArrayList<Item>();
        ArrayList<Item> sides = new ArrayList<Item>();

        burgers.add(new Item("Wagyu Burger", "Wagyu beef, bacon, tomato, mesculin, beetroot and aoli on a lightly toasted brioche bun.\n Served with chips.", 16.5));
        burgers.add(new Item("Cheeseburger", "Milk bun toppped with a beef patty, cheese, tomato and mustard. Served with chips.", 10.0));
        burgers.add(new Item("Halloumi Burger", "Milk bun topped with rocker, halloumi, egg and tomato relish. Served with chips.", 10.0, "Vegetarian"));
        burgers.add(new Item("Steak Sandwich", "120g rib fillet steak with caramelised onion, lettuce, cheese, tomato, beetroot and \nbarbeque sauce on a toasted sandwich ciabatta. Served with chips.", 18.5));
        burgers.add(new Item("Chicken Wrap", "Southern chicken tenders wrapped in soft tortilla with sweet chilli aioli, lettuce, \ncheese, tomato and carrot. Served with chips.", 9.9));

        pizzas.add(new Item("Tropicana Pizza", "Ham and pineapple served on a tomato base with mozzarella cheese.", 10));
        pizzas.add(new Item("BBQ Meat Lovers Pizza", "Beef, bacon, ham, pepperoni, spanish onion, cherry tomatoes and mozzarella \ncheese.", 16));
        pizzas.add(new Item("Pepperoni Pizza", "Tomato base, chilli flakes, pepperoni, spinach and mozzarella cheese.", 9));
        pizzas.add(new Item("Chicken, Mushroom and Brie Pizza", "Tomato base, chicken, mushroom, spinach, brie and mozzarella \ncheese.", 14));
        pizzas.add(new Item("Garlic Pizza", "Confit garlic, mozzarella cheese, rosemary topped with smoked salt.", 8));
        pizzas.add(new Item("Slow-Roasted Lamb Pizza", "Tomato base with slow-roasted lamb, rocket, sumac, tzatziki and mozzarella \ncheese.", 12, "Vegetarian"));
        pizzas.add(new Item("Green Pizza", "Basil pesto base topped with rocket, broccoli, green olives and bocconcini.", 12, "Vegetarian"));

        sides.add(new Item("Sweet potato fries (Bowl)", "Served with tomato relish", 6.5, "Vegetarian"));
        sides.add(new Item("Chips (Bowl)", "Served with aioli, tomato sauce and barbeque sauce.", 5, "Vegetarian"));
        sides.add(new Item("Wedges (Bowl)", "Served with sweet chilli sauce and sour cream.", 6.5, "Vegetarian"));
        sides.add(new Item("Side salad", "", 5, "Vegetarian"));


        //create menu
        Menu menu = new Menu();

        //add items to menu
        menu.setBurgers(burgers);
        menu.setPizzas(pizzas);
        menu.setSides(sides);

        return menu;
    }

    public static Menu createNonMembersMenu(){
        ArrayList<Item> burgers = new ArrayList<Item>();
        ArrayList<Item> pizzas = new ArrayList<Item>();
        ArrayList<Item> sides = new ArrayList<Item>();

        burgers.add(new Item("Wagyu Burger", "Wagyu beef, bacon, tomato, mesculin, beetroot and aoli on a lightly toasted brioche bun.\n Served with chips.", 19.5));
        burgers.add(new Item("Cheeseburger", "Milk bun toppped with a beef patty, cheese, tomato and mustard. Served with chips.", 13.0));
        burgers.add(new Item("Halloumi Burger", "Milk bun topped with rocker, halloumi, egg and tomato relish. Served with chips.", 13.0, "Vegetarian"));
        burgers.add(new Item("Steak Sandwich", "120g rib fillet steak with caramelised onion, lettuce, cheese, tomato, beetroot and \nbarbeque sauce on a toasted sandwich ciabatta. Served with chips.", 21.5));
        burgers.add(new Item("Chicken Wrap", "Southern chicken tenders wrapped in soft tortilla with sweet chilli aioli, lettuce, \ncheese, tomato and carrot. Served with chips.", 12.9));

        pizzas.add(new Item("Tropicana Pizza", "Ham and pineapple served on a tomato base with mozzarella cheese.", 13));
        pizzas.add(new Item("BBQ Meat Lovers Pizza", "Beef, bacon, ham, pepperoni, spanish onion, cherry tomatoes and mozzarella \ncheese.", 19));
        pizzas.add(new Item("Pepperoni Pizza", "Tomato base, chilli flakes, pepperoni, spinach and mozzarella cheese.", 12));
        pizzas.add(new Item("Chicken, Mushroom and Brie Pizza", "Tomato base, chicken, mushroom, spinach, brie and mozzarella \ncheese.", 17));
        pizzas.add(new Item("Garlic Pizza", "Confit garlic, mozzarella cheese, rosemary topped with smoked salt.", 11));
        pizzas.add(new Item("Slow-Roasted Lamb Pizza", "Tomato base with slow-roasted lamb, rocket, sumac, tzatziki and mozzarella \ncheese.", 15, "Vegetarian"));
        pizzas.add(new Item("Green Pizza", "Basil pesto base topped with rocket, broccoli, green olives and bocconcini.", 15, "Vegetarian"));

        sides.add(new Item("Sweet potato fries (Bowl)", "Served with tomato relish", 9.5, "Vegetarian"));
        sides.add(new Item("Chips (Bowl)", "Served with aioli, tomato sauce and barbeque sauce.", 8, "Vegetarian"));
        sides.add(new Item("Wedges (Bowl)", "Served with sweet chilli sauce and sour cream.", 9.5, "Vegetarian"));
        sides.add(new Item("Side salad", "", 8, "Vegetarian"));


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
    
    public static void addItems(){
        //create item object from valid inputs
        try{
            Item item = getMainFrame().getSecondPanel().getItemPanel().getValue().clone();

            int quantity = Integer.parseInt(getMainFrame().getThirdPanel().getQuantityPanel().getValue());

            item.setQuantity(quantity);

            //add item to order
            getOrder().addItem(item);

            //update order displayed on GUI using toString of order object
            getMainFrame().getThirdPanel().update(getOrder().toString());
        }
        catch(CloneNotSupportedException e){
            JOptionPane.showMessageDialog(CafeOrderSystem.getMainFrame(), 
                "CloneNotSupported Exception.", 
                "Unsuccessful adding item.", 
                JOptionPane.ERROR_MESSAGE);
        }
        catch(NullPointerException e){
            JOptionPane.showMessageDialog(CafeOrderSystem.getMainFrame().getSecondPanel(), 
                "Please select an item from menu.", 
                "Unsuccessful adding item.", 
                JOptionPane.ERROR_MESSAGE);
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(CafeOrderSystem.getMainFrame().getSecondPanel(), 
                "Please enter integer for quantity", 
                "Unsuccessful adding item.", 
                JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(CafeOrderSystem.getMainFrame().getSecondPanel(), 
                e, 
                "Unsuccessful adding item.", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void reset(){
        order = new Order();
        getMainFrame().getFirstPanel().reset();
        getMainFrame().getSecondPanel().reset();
        getMainFrame().getThirdPanel().reset();
        JOptionPane.showMessageDialog(CafeOrderSystem.getMainFrame(), 
                "Reset was successful.", 
                "Successful reset.", 
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static Printer getPrinter(){
        return printer;
    }

    public static void printTextFile(){
        getPrinter().openTextFile(getMainFrame().getFirstPanel().getNamePanel().getValue(), 
            getMainFrame().getFirstPanel().getTableNumberPanel().getValue());
        getPrinter().saveOrder();
        getPrinter().closeTextFile();
    }
}

class Printer{
    private Formatter output; 

    public Formatter getFormatter(){
        return this.output;
    }

    public void openTextFile(String name, String tableNumber){
        try{  
            this.output = new Formatter(name + "_" + tableNumber + ".txt");
        }
        catch(SecurityException e){
            JOptionPane.showMessageDialog(CafeOrderSystem.getMainFrame(), 
                "Write Permission Denied.", 
                "Terminating.", 
                JOptionPane.ERROR_MESSAGE);
            System.exit(1); //terminates 
        }
        catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(CafeOrderSystem.getMainFrame(), 
                "Error opening file.", 
                "Terminating.", 
                JOptionPane.ERROR_MESSAGE);
            System.exit(1); //terminates 
        }
    }

    public void saveOrder(){
        try{
            getFormatter().format(CafeOrderSystem.getOrder().toString());
        }
        catch(FormatterClosedException e){
            JOptionPane.showMessageDialog(CafeOrderSystem.getMainFrame(), 
                "Formatter closed.", 
                "Terminating.", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public void closeTextFile(){
        if(output != null){
            getFormatter().close();
        }
    }
}

class MenuFrame extends JFrame{
    private GridBagLayout layout;
    private Menu membersMenu;
    private Menu nonMembersMenu;
    private FirstPanel firstPanel;
    private SecondPanel secondPanel;
    private ThirdPanel thirdPanel; 
    private FourthPanel fourthPanel;

    public MenuFrame(){
        super("Cafe Order System");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setSize(1200, 650);

        layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(layout);

        this.firstPanel = new FirstPanel();
        this.secondPanel = new SecondPanel();
        this.thirdPanel = new ThirdPanel();
        this.fourthPanel = new FourthPanel();

        c.fill = GridBagConstraints.HORIZONTAL;
        
        c.gridx = 0;
        c.gridy = 0;

        add(this.firstPanel, c);

        c.gridx = 0;
        c.gridy = 1;

        add(this.secondPanel, c);

        c.gridx = 0;
        c.gridy = 2;

        add(this.thirdPanel, c);

        c.gridx = 0;
        c.gridy = 3;

        add(this.fourthPanel, c);     
    }
    
    public Menu getMembersMenu(){
        return this.membersMenu;
    }

    public void setMembersMenu(Menu menu){
        this.membersMenu = menu;
    }

    public Menu getNonMembersMenu(){
        return this.nonMembersMenu;
    }

    public void setNonMembersMenu(Menu menu){
        this.nonMembersMenu = menu;
    }

    public FirstPanel getFirstPanel(){
        return this.firstPanel;
    }

    public SecondPanel getSecondPanel(){
        return this.secondPanel;
    }

    public ThirdPanel getThirdPanel(){
        return this.thirdPanel;
    }

    public FourthPanel getFourthPanel(){
        return this.fourthPanel;
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

class FirstPanel extends JPanel{
    private FlowLayout layout = new FlowLayout();
    private InputPanel namePanel;
    private InputPanel tableNumberPanel; 
    private MemberPanel memberPanel;

    public FirstPanel(){
        setLayout(this.layout);

        this.namePanel = new InputPanel("Name: ");
        this.tableNumberPanel = new InputPanel("Table Number: " );
        this.memberPanel = new MemberPanel();

        add(this.namePanel);
        add(this.tableNumberPanel);
        add(this.memberPanel);
    }

    public MemberPanel getMemberPanel(){
        return this.memberPanel;
    }

    public InputPanel getNamePanel(){
        return this.namePanel;
    }

    public InputPanel getTableNumberPanel(){
        return tableNumberPanel;
    }

    public void reset(){
        getNamePanel().reset();
        getTableNumberPanel().reset(); 
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
}

class SecondPanel extends JPanel{
    private FlowLayout layout = new FlowLayout();
    private CategoryPanel categoryPanel;
    private ItemPanel itemPanel; 
    private DescriptionPanel descriptionPanel;

    public SecondPanel(){
        setLayout(this.layout);

        String[] x = {"Pizzas", "Burgers", "Sides"};
        this.categoryPanel = new CategoryPanel(x);
        this.itemPanel = new ItemPanel();
        this.descriptionPanel = new DescriptionPanel();

        add(this.categoryPanel);
        add(this.itemPanel);
        add(this.descriptionPanel);
    }

    public CategoryPanel getCategoryPanel(){
        return this.categoryPanel;
    }

    public ItemPanel getItemPanel(){
        return this.itemPanel;
    }

    public DescriptionPanel getDescriptionPanel(){
        return this.descriptionPanel;
    }

    public void reset(){
        getCategoryPanel().reset();
        getItemPanel().reset();
        getDescriptionPanel().reset(); 
    }
}

class ThirdPanel extends JPanel{
    private JLabel title; 
    private JTextArea order; 
    private InputPanel quantityPanel;
    private JButton addButton; 
    
    public ThirdPanel(){
        this.title = new JLabel("Order: ");
        this.order = new JTextArea(20, 30);
        this.addButton = new JButton("Add item");
        this.addButton.addActionListener(new AddButtonHandler());
        this.quantityPanel = new InputPanel("Quantity: ");

        add(this.quantityPanel);
        add(this.addButton);
        add(title);
        add(order);
    }
    
    public JButton getAddButton(){
        return this.addButton;
    }

    public InputPanel getQuantityPanel(){
        return this.quantityPanel;
    }

    public void update(String updatedOrder){
        this.order.setText(updatedOrder);
    }
    
    public void reset(){
        this.order.setText("");
        getQuantityPanel().reset();
    }
}

class FourthPanel extends JPanel{
    private FlowLayout layout = new FlowLayout();
    private JButton submitButton;
    private JButton resetButton;

    public FourthPanel(){
        setLayout(this.layout);

        this.resetButton = new JButton("Reset");
        this.submitButton = new JButton("Submit");

        this.resetButton.addActionListener(new ResetButtonHandler());
        this.submitButton.addActionListener(new SubmitButtonHandler());
 
        add(this.resetButton);
        add(this.submitButton);   
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
    private JList<Item> options;
    private DefaultListModel<Item> optionsModel = new DefaultListModel<Item>();
    private ItemSelectionListener listener = new ItemSelectionListener();
    
    //default constructor
    public ItemPanel(){
        this.layout = new BorderLayout();

        this.options = new JList<>(this.optionsModel);

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
                CafeOrderSystem.getMainFrame().getSecondPanel().getDescriptionPanel().changeValue(description);
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
    private JList<String> options;
    private DefaultListModel<String> optionsModel = new DefaultListModel<String>();
    private CategorySelectionListener listener = new CategorySelectionListener();

    //paramterised constructor
    public CategoryPanel(String[] options){
        this.layout = new BorderLayout();

        //add options to model
        for(int i = 0; i < options.length; i++){
            this.optionsModel.addElement(options[i]);
        }

        this.options = new JList<>(this.optionsModel);

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
                
                Menu menu = CafeOrderSystem.getMainFrame().getNonMembersMenu();

                if(CafeOrderSystem.getMainFrame().getFirstPanel().getMemberPanel().getValue().equals("Member")){
                    menu = CafeOrderSystem.getMainFrame().getMembersMenu();
                }
                
                ArrayList<Item> items = new ArrayList<Item>();

                switch(choice){
                    case 0:
                        items = menu.getPizzas();
                        break;
                    case 1:
                        items = menu.getBurgers();
                        break;
                    case 2:
                        items = menu.getSides();
                        break;
                }

                //change displayed options of items panel
                CafeOrderSystem.getMainFrame().getSecondPanel().getItemPanel().changeOptions(items);
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
        this.input = new JTextField(8);
        
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

class MemberPanel extends JPanel{
    //datafields 
    private final FlowLayout layout;
    private final ButtonGroup buttonGroup;
    JRadioButton[] buttons;

    public MemberPanel(){
        layout = new FlowLayout();
        add(new JLabel("Membership: "));
        buttonGroup = new ButtonGroup();
        JRadioButton memberButton = new JRadioButton("Member");
        JRadioButton nonMemberButton = new JRadioButton("Non-member");
        memberButton.addItemListener(new ChangeMembership());
        nonMemberButton.addItemListener(new ChangeMembership());
            
        //maintins array of options
        buttons = new JRadioButton[]{memberButton, nonMemberButton};

        //adds buttons to the actual panel for display
        add(memberButton);
        add(nonMemberButton);

        //adds buttons to button group
        buttonGroup.add(memberButton);
        buttonGroup.add(nonMemberButton);  
    }   

    //returns the selected radio button
    public String getValue(){
        String selected = "";

        //loops through array of button and returns text if selected
        for(JRadioButton button : buttons){
            if(button.isSelected()){
                selected = button.getText();
            }
        }

        return selected;
    }       
    
    private class ChangeMembership implements ItemListener {    
        @Override
        public void itemStateChanged(ItemEvent event){
            /*NOTE: what the selection value actually is checked/handled in
            the itemPanel listener*/ 
            // CafeOrderSystem.reset();
            CafeOrderSystem.getMainFrame().getSecondPanel().reset();
            CafeOrderSystem.getMainFrame().getThirdPanel().getQuantityPanel().reset();
            
        }
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
            CafeOrderSystem.getMainFrame().getFirstPanel().validateCustomerName();
            CafeOrderSystem.getMainFrame().getFirstPanel().validateTableNumber();
            CafeOrderSystem.printTextFile();
            CafeOrderSystem.getMainFrame().successfulSubmitHandler();
        }
        catch(Exception exception){
            CafeOrderSystem.getMainFrame().unsuccessfulSubmitHandler();
        }
        
    }
}
