import java.util.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.StyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MovieStore{
    private static String currentAccount;
    static ArrayList<SimpleEntry<String, String>> accounts = new ArrayList<>(Arrays.asList(
            new SimpleEntry<>("Shakif", "1234")
    ));
    static Map<SimpleEntry<String, Integer>, String> bkash = new HashMap<>();
    static Map<SimpleEntry<String, Integer>, String> inventory1 = new HashMap<>();
    static ArrayList<SimpleEntry<String, Integer>> action = new ArrayList<>(Arrays.asList(
            new SimpleEntry<>("Superman", 500),
            new SimpleEntry<>("Fantastic 4", 500),
            new SimpleEntry<>("The Batman", 300),
            new SimpleEntry<>("The Fast and Furious", 250),
            new SimpleEntry<>("Twisters", 200)
    ));
    static ArrayList<SimpleEntry<String, Integer>> comedy = new ArrayList<>(Arrays.asList(
            new SimpleEntry<>("The Nice Guys", 500),
            new SimpleEntry<>("The Big Lebowski", 500),
            new SimpleEntry<>("Groundhog Day", 300),
            new SimpleEntry<>("Meet the Parents", 250),
            new SimpleEntry<>("Click", 200)
    ));
    static ArrayList<SimpleEntry<String, Integer>> romance = new ArrayList<>(Arrays.asList(
            new SimpleEntry<>("The Notebook", 500),
            new SimpleEntry<>("Love Actually", 450),
            new SimpleEntry<>("Crazy, Stupid, Love", 400),
            new SimpleEntry<>(" After", 150),
            new SimpleEntry<>("Twilight", 100)
    ));
    static ArrayList<SimpleEntry<String, Integer>> horror = new ArrayList<>(Arrays.asList(
            new SimpleEntry<>("IT", 500),
            new SimpleEntry<>("The Conjuring", 500),
            new SimpleEntry<>("Texas Chainsaw Massacre", 300),
            new SimpleEntry<>("Five Night at Freddy's", 250),
            new SimpleEntry<>("Birdemic", 200)
    ));
    static ArrayList<SimpleEntry<String, Integer>> thriller = new ArrayList<>(Arrays.asList(
            new SimpleEntry<>("Who Killed Captain Alex", 1000),
            new SimpleEntry<>("Psycho", 500),
            new SimpleEntry<>("The Silence of the Lambs", 500),
            new SimpleEntry<>("Pulp Fiction", 350),
            new SimpleEntry<>("Doom", 200)

    ));

    public static void sortArray(ArrayList<SimpleEntry<String, Integer>> list){
        Collections.sort(list, new Comparator<SimpleEntry<String, Integer>> () {
            @Override
            public int compare(SimpleEntry<String, Integer> o1, SimpleEntry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
    }

    public static void addMovie(ArrayList<SimpleEntry<String, Integer>> list){
        JFrame addFrame = new JFrame("Add Movies");
        JPanel addPanel = new JPanel();
        addFrame.setSize(300, 150);
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.PAGE_AXIS));

        JPanel moviePanel = new JPanel();
        JTextField userTextField = new JTextField(15);
        JLabel userText = new JLabel("Movie Title: ");
        moviePanel.add(userText);
        moviePanel.add(userTextField);

        JPanel pricePanel = new JPanel();
        JTextField userTextField2 = new JTextField(15);
        JLabel userText2 = new JLabel("          Price: ");
        pricePanel.add(userText2);
        pricePanel.add(userTextField2);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton addButton = new JButton("Add");
        addButton.setFocusable(false);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFocusable(false);

        Dimension buttonSize = new Dimension(100, 30);
        addButton.setPreferredSize(buttonSize);
        cancelButton.setPreferredSize(buttonSize);

        addButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        cancelButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addPanel.add(moviePanel);
        addPanel.add(pricePanel);
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);
        addPanel.add(buttonPanel);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String movieName = userTextField.getText();
                String priceText = userTextField2.getText();

                try{
                    int moviePrice = Integer.parseInt(priceText);
                    list.add(new SimpleEntry<>(movieName, moviePrice));
                    sortArray(list);
                    JOptionPane.showMessageDialog(addFrame, "Movie Successfully Added.");
                    addFrame.dispose();
                    changeMovies();
                }
                catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(addFrame, "Invalid input for the price.", "Error", JOptionPane.ERROR_MESSAGE);
                    addFrame.dispose();
                    addMovie(list);
                }

            }
        });

        cancelButton.addActionListener(e -> {
            addFrame.dispose();
            changeMovies();
        });

        addPanel.add(Box.createVerticalStrut(15));
        addFrame.add(addPanel);
        addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addFrame.setLocationRelativeTo(null);
        addFrame.setVisible(true);
    }

    public static void updateMovies(ArrayList<SimpleEntry<String, Integer>> list, int option){
        JFrame updateFrame = new JFrame("Change Movie Price");
        JPanel updatePanel = new JPanel();
        updateFrame.setSize(300, 150);
        updatePanel.setLayout(new BoxLayout(updatePanel, BoxLayout.PAGE_AXIS));

        JPanel moviePanel = new JPanel();
        JTextField userTextField = new JTextField(15);
        JLabel userText = new JLabel("New Price: ");
        moviePanel.add(userText);
        moviePanel.add(userTextField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton changeButton = new JButton("Change");
        changeButton.setFocusable(false);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFocusable(false);

        Dimension buttonSize = new Dimension(100, 30);
        changeButton.setPreferredSize(buttonSize);
        cancelButton.setPreferredSize(buttonSize);

        changeButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        cancelButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        updatePanel.add(moviePanel);
        buttonPanel.add(changeButton);
        buttonPanel.add(cancelButton);
        updatePanel.add(buttonPanel);

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String priceText = userTextField.getText();

                try{
                    int moviePrice = Integer.parseInt(priceText);
                    int number = 1;
                    for (int i = 0; i < list.size(); i++) {
                        SimpleEntry<String, Integer> entry = list.get(i);
                        if (option == number) {
                            list.set(i, new SimpleEntry<>(entry.getKey(), moviePrice));
                            sortArray(list);
                            JOptionPane.showMessageDialog(updateFrame, "Movie Price Successfully Changed.");
                            updateFrame.dispose();
                            changeMovies();
                        }
                        number++;
                    }
                }
                catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(updateFrame, "Invalid input for the price.", "Error", JOptionPane.ERROR_MESSAGE);
                    updateFrame.dispose();
                    updateMovies(list, option);
                }

            }
        });

        cancelButton.addActionListener(e -> {
            updateFrame.dispose();
            changeMovies();
        });

        updatePanel.add(Box.createVerticalStrut(15));
        updateFrame.add(updatePanel);
        updateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        updateFrame.setLocationRelativeTo(null);
        updateFrame.setVisible(true);
    }

    public static void deleteMovies(ArrayList<SimpleEntry<String, Integer>> list, int option){
        JFrame delFrame = new JFrame("Remove Movie");
        delFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        delFrame.setSize(430, 110);
        delFrame.setLayout(new BorderLayout());

        JPanel delPanel = new JPanel();
        delPanel.setLayout(new BorderLayout());
        delPanel.setBackground(new Color(240, 240, 240));

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Dialog", Font.BOLD, 12));
        textArea.setMargin(new Insets(10, 20, 10, 10));
        textArea.setBackground(new Color(240, 240, 240));
        textArea.getCaret().setVisible(false);
        textArea.getCaret().deinstall(textArea);

        textArea.append("Are you sure you want to PERMANENTLY DELETE this movie?");
        delPanel.add(textArea, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton yesButton = new JButton("Yes");
        yesButton.setFocusable(false);
        JButton noButton = new JButton("No");
        noButton.setFocusable(false);

        Dimension buttonSize = new Dimension(100, 30);
        yesButton.setPreferredSize(buttonSize);
        noButton.setPreferredSize(buttonSize);

        yesButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        noButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int number = 1;
                for (int i = 0; i < list.size(); i++) {
                    SimpleEntry<String, Integer> entry = list.get(i);
                    if (option == number) {
                        list.remove(i);
                        JOptionPane.showMessageDialog(delFrame, "Movie Successfully Deleted.");
                        delFrame.dispose();
                        changeMovies();
                    }
                    number++;
                }
            }
        });

        noButton.addActionListener(e -> {
            delFrame.dispose();
            changeMovies();
        });

        delFrame.add(delPanel, BorderLayout.CENTER);
        delFrame.add(buttonPanel, BorderLayout.SOUTH);
        delFrame.setLocationRelativeTo(null);
        delFrame.setVisible(true);
    }

    public static void printGenre(int menuNum){
        JFrame genreFrame = new JFrame();
        genreFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        genreFrame.setSize(300, 430);
        genreFrame.setLayout(null);

        JButton button1 = new JButton("1. " + String.valueOf(Genre.values()[0]));
        button1.setHorizontalAlignment(SwingConstants.LEFT);
        button1.setFocusable(false);
        button1.setBounds(50, 50, 200, 30);
        genreFrame.add(button1);

        JButton button2 = new JButton("2. " + String.valueOf(Genre.values()[1]));
        button2.setHorizontalAlignment(SwingConstants.LEFT);
        button2.setFocusable(false);
        button2.setBounds(50, 100, 200, 30);
        genreFrame.add(button2);

        JButton button3 = new JButton("3. " + String.valueOf(Genre.values()[2]));
        button3.setHorizontalAlignment(SwingConstants.LEFT);
        button3.setFocusable(false);
        button3.setBounds(50, 150, 200, 30);
        genreFrame.add(button3);

        JButton button4 = new JButton("4. " + String.valueOf(Genre.values()[3]));
        button4.setHorizontalAlignment(SwingConstants.LEFT);
        button4.setFocusable(false);
        button4.setBounds(50, 200, 200, 30);
        genreFrame.add(button4);

        JButton button5 = new JButton("5. " + String.valueOf(Genre.values()[4]));
        button5.setHorizontalAlignment(SwingConstants.LEFT);
        button5.setFocusable(false);
        button5.setBounds(50, 250, 200, 30);
        genreFrame.add(button5);

        JButton button6 = new JButton("6. " + String.valueOf(Genre.values()[5]));
        button6.setHorizontalAlignment(SwingConstants.LEFT);
        button6.setFocusable(false);
        button6.setBounds(50, 300, 200, 30);
        genreFrame.add(button6);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genreFrame.dispose();
                if(menuNum == 21){
                    addMovie(action);
                }
                else{
                    selectMovie(action, menuNum);
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genreFrame.dispose();
                if(menuNum == 21){
                    addMovie(comedy);
                }
                else{
                    selectMovie(comedy, menuNum);
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genreFrame.dispose();
                if(menuNum == 21){
                    addMovie(romance);
                }
                else{
                    selectMovie(romance, menuNum);
                }
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genreFrame.dispose();
                if(menuNum == 21){
                    addMovie(horror);
                }
                else{
                    selectMovie(horror, menuNum);
                }
            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genreFrame.dispose();
                if(menuNum == 21){
                    addMovie(thriller);
                }
                else{
                    selectMovie(thriller, menuNum);
                }
            }
        });

        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genreFrame.dispose();
                firstScreen();
            }
        });

        genreFrame.setLocationRelativeTo(null);
        genreFrame.setVisible(true);
    }

    public static void selectMovie(ArrayList<SimpleEntry<String, Integer>> list, int menuNum) {
        JFrame movieFrame = new JFrame("Select Movie");
        movieFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        movieFrame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(240, 240, 240));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        FontMetrics buttonLength = panel.getFontMetrics(new Font("Dialog", Font.PLAIN, 12));
        int maxWidth = 0;
        for (SimpleEntry<String, Integer> entry : list) {
            int width = buttonLength.stringWidth(entry.getKey());
            if (width > maxWidth) {
                maxWidth = width;
            }
        }

        int buttonWidth = maxWidth + 200;
        int buttonHeight = 40;

        int i = 1;
        for (SimpleEntry<String, Integer> entry : list) {
            JButton button = new JButton(i + ". " + entry.getKey() + "   Price: Tk" + entry.getValue());
            button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
            button.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
            button.setHorizontalAlignment(SwingConstants.LEFT);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setFocusable(false);

            int option = i;
            button.addActionListener(e -> {
                movieFrame.dispose();
                if(menuNum == 1) {
                    movieFrame.dispose();
                    buyMovie(list, option);
                }
                else if(menuNum == 21){
                    movieFrame.dispose();
                    addMovie(list);
                }
                else if(menuNum == 22){
                    movieFrame.dispose();
                    updateMovies(list, option);
                }
                else if(menuNum == 23){
                    movieFrame.dispose();
                    deleteMovies(list, option);
                }
            });

            panel.add(button);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            i++;
        }

        JButton button = new JButton("Back");
        button.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        button.setMaximumSize(new Dimension(buttonWidth, buttonHeight));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusable(false);
        button.addActionListener(e -> {
            movieFrame.dispose();
            printGenre(1);
        });
        panel.add(button);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        movieFrame.add(panel, BorderLayout.CENTER);
        movieFrame.pack();
        movieFrame.setLocationRelativeTo(null);
        movieFrame.setVisible(true);
    }

    public static void buyMovie(ArrayList<SimpleEntry<String, Integer>> list, int option){
        JFrame buyFrame = new JFrame();
        JPanel buyPanel = new JPanel();
        buyFrame.setSize(300, 115);
        buyPanel.setLayout(new BoxLayout(buyPanel, BoxLayout.Y_AXIS));

        JPanel pinPanel = new JPanel();
        JPasswordField passTextField = new JPasswordField(20);
        JLabel passText = new JLabel("Pin: ");
        pinPanel.add(passText);
        pinPanel.add(passTextField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton buyButton = new JButton("Buy Movie");
        buyButton.setFocusable(false);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFocusable(false);

        Dimension buttonSize = new Dimension(100, 30);
        buyButton.setPreferredSize(buttonSize);
        cancelButton.setPreferredSize(buttonSize);

        buyButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        cancelButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        buyPanel.add(pinPanel);
        buttonPanel.add(buyButton);
        buttonPanel.add(cancelButton);
        buyPanel.add(buttonPanel);

        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pass = new String(passTextField.getPassword());

                try {
                    int pin = Integer.parseInt(pass);

                    boolean accountFound = false;
                    for (Map.Entry<SimpleEntry<String, Integer>, String> entry : bkash.entrySet()) {
                        if (Objects.equals(entry.getValue(), currentAccount)) {
                            SimpleEntry<String, Integer> key = entry.getKey();
                            accountFound = true;

                            if (Objects.equals(key.getValue(), pin)) {
                                JOptionPane.showMessageDialog(buyFrame, "Movie Successfully Bought.");
                                int i = 1;
                                for (SimpleEntry<String, Integer> entry1 : list) {
                                    if (option == i) {
                                        SimpleEntry<String, Integer> key1 = new SimpleEntry<>(entry1.getKey(), entry1.getValue());
                                        inventory1.put(key1, currentAccount);
                                    }
                                    i++;
                                }
                                buyFrame.dispose();
                                selectMovie(list, 1);
                                return;
                            } else {
                                JOptionPane.showMessageDialog(buyFrame, "Incorrect PIN.", "Error", JOptionPane.ERROR_MESSAGE);
                                buyFrame.dispose();
                                buyMovie(list, option);
                                return;
                            }
                        }
                    }

                    if (!accountFound) {
                        JOptionPane.showMessageDialog(buyFrame, "No bKash number added to the Account.", "Error", JOptionPane.ERROR_MESSAGE);
                        buyFrame.dispose();
                        buyMovie(list, option);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(buyFrame, "Invalid PIN. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
                    buyFrame.dispose();
                    buyMovie(list, option);
                }
            }
        });


        cancelButton.addActionListener(e -> {
            buyFrame.dispose();
            selectMovie(list, 1);
        });

        buyPanel.add(Box.createVerticalStrut(15));
        buyFrame.add(buyPanel);
        buyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        buyFrame.setLocationRelativeTo(null);
        buyFrame.setVisible(true);
    }

    public static void updateAccountName(){
        JFrame nameFrame = new JFrame();
        JPanel namePanel = new JPanel();
        nameFrame.setSize(300, 120);
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));


        JPanel bKashPanel = new JPanel();
        JTextField userTextField = new JTextField(15);
        JLabel userText = new JLabel("New Username: ");
        bKashPanel.add(userText);
        bKashPanel.add(userTextField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton changeButton = new JButton("Change");
        changeButton.setFocusable(false);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFocusable(false);

        Dimension buttonSize = new Dimension(100, 30);
        changeButton.setPreferredSize(buttonSize);
        cancelButton.setPreferredSize(buttonSize);

        changeButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        cancelButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        namePanel.add(bKashPanel);
        buttonPanel.add(changeButton);
        buttonPanel.add(cancelButton);
        namePanel.add(buttonPanel);

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = userTextField.getText();
                for (int i = 0; i < accounts.size(); i++) {
                    SimpleEntry<String, String> entry = accounts.get(i);
                    if (entry.getKey().equals(currentAccount)) {
                        accounts.set(i, new SimpleEntry<>(name, entry.getValue()));
                        currentAccount = name;
                        JOptionPane.showMessageDialog(nameFrame, "Username Successfully Changed.");
                        nameFrame.dispose();
                        firstScreen();
                    }
                }
            }
        });

        cancelButton.addActionListener(e -> {
            nameFrame.dispose();
            firstScreen();
        });

        namePanel.add(Box.createVerticalStrut(15));
        nameFrame.add(namePanel);
        nameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        nameFrame.setLocationRelativeTo(null);
        nameFrame.setVisible(true);

    }

    public static void updatePassword(){
        JFrame passFrame = new JFrame();
        JPanel passPanel = new JPanel();
        passFrame.setSize(300, 200);
        passPanel.setLayout(new BoxLayout(passPanel, BoxLayout.Y_AXIS));

        JPanel oldPassPanel = new JPanel();
        JPasswordField oldPassTextField = new JPasswordField(15);
        JLabel passText = new JLabel("Current Password: ");
        oldPassPanel.add(passText);
        oldPassPanel.add(oldPassTextField);

        JPanel newPassPanel = new JPanel();
        JPasswordField newPassTextField = new JPasswordField(15);
        JLabel newPassText = new JLabel("New Password: ");
        newPassPanel.add(newPassText);
        newPassPanel.add(newPassTextField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton changeButton = new JButton("Change");
        changeButton.setFocusable(false);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFocusable(false);

        Dimension buttonSize = new Dimension(100, 30);
        changeButton.setPreferredSize(buttonSize);
        cancelButton.setPreferredSize(buttonSize);

        changeButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        cancelButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        passPanel.add(oldPassPanel);
        passPanel.add(newPassPanel);
        buttonPanel.add(changeButton);
        buttonPanel.add(cancelButton);
        passPanel.add(buttonPanel);

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldPass = new String(oldPassTextField.getPassword());
                String newPass = new String(newPassTextField.getPassword());

                for (int i = 0; i < accounts.size(); i++) {
                    SimpleEntry<String, String> entry = accounts.get(i);
                    if (entry.getKey().equals(currentAccount)) {
                        if(oldPass.equals(entry.getValue())){
                            accounts.set(i, new SimpleEntry<>(entry.getKey(), newPass));
                            JOptionPane.showMessageDialog(passFrame, "Password Successfully Changed.");
                            passFrame.dispose();
                            firstScreen();
                        }
                        else{
                            JOptionPane.showMessageDialog(passFrame, "Incorrect Current Password.", "Error", JOptionPane.ERROR_MESSAGE);
                            passFrame.dispose();
                            firstScreen();
                        }
                    }
                }
            }
        });

        cancelButton.addActionListener(e -> {
            passFrame.dispose();
            firstScreen();
        });

        passPanel.add(Box.createVerticalStrut(15));
        passFrame.add(passPanel);
        passFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        passFrame.setLocationRelativeTo(null);
        passFrame.setVisible(true);
    }

    public static void accountInfo(){
        JFrame infoFrame = new JFrame("Account Info");
        infoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        infoFrame.setSize(400, 300);
        infoFrame.setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoPanel.setBackground(new Color(240, 240, 240));

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Dialog", Font.BOLD, 12));
        textArea.setMargin(new Insets(10, 20, 10, 10));
        textArea.setBackground(new Color(240, 240, 240));

        textArea.getCaret().setVisible(false);
        textArea.getCaret().deinstall(textArea);

        LocalDate date = LocalDate.now();
        String formattedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(date);
        textArea.append("Date: " + formattedDate + "\n\n");
        textArea.append("Username: " + currentAccount + "\n\n");
        textArea.append("No movies have been bought on this account.");
        if(inventory1.isEmpty()){
            textArea.append("\n\nbKash Number: ");
            boolean accountFound = false;
            for (Map.Entry<SimpleEntry<String, Integer>, String> entry : bkash.entrySet()) {
                if (Objects.equals(entry.getValue(), currentAccount)) {
                    SimpleEntry<String, Integer> key = entry.getKey();
                    accountFound = true;
                }
            }
            if(accountFound){
                for (Map.Entry<SimpleEntry<String, Integer>, String> entry : bkash.entrySet()) {
                    SimpleEntry<String, Integer> key = entry.getKey();
                    if(Objects.equals(entry.getValue(), currentAccount)){
                        textArea.append(key.getKey()+"\n");
                    }
                }
            }
            else{
                textArea.append("No bKash number added to this Account");
            }
        }
        else{
            textArea.append("Movies bought: " + "\n");
            int i = 1;
            int totalSpent = 0;
            for (Map.Entry<SimpleEntry<String, Integer>, String> entry : inventory1.entrySet()) {
                SimpleEntry<String, Integer> key = entry.getKey();
                if(Objects.equals(entry.getValue(), currentAccount)){
                    textArea.append(i+". "+ key.getKey() +";  Price:" + key.getValue() + "Taka" + "\n");
                    totalSpent+=key.getValue();
                    i++;
                }
            }
            textArea.append("\nbKash Number: ");
            boolean accountFound = false;
            for (Map.Entry<SimpleEntry<String, Integer>, String> entry : bkash.entrySet()) {
                if (Objects.equals(entry.getValue(), currentAccount)) {
                    SimpleEntry<String, Integer> key = entry.getKey();
                    accountFound = true;
                }
            }
            if(accountFound){
                for (Map.Entry<SimpleEntry<String, Integer>, String> entry : bkash.entrySet()) {
                    SimpleEntry<String, Integer> key = entry.getKey();
                    if(Objects.equals(entry.getValue(), currentAccount)){
                        textArea.append(key.getKey()+"\n");
                    }
                }
            }
            else{
                textArea.append("No bKash number added to this Account");
            }
            textArea.append("\nTotal Spent: " + totalSpent + "Taka");
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(80, 30));
        backButton.addActionListener(e -> {
            infoFrame.dispose();
            firstScreen();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(backButton);

        infoPanel.add(scrollPane, BorderLayout.CENTER);

        infoFrame.add(infoPanel, BorderLayout.CENTER);
        infoFrame.add(buttonPanel, BorderLayout.SOUTH);
        infoFrame.setLocationRelativeTo(null);
        infoFrame.setVisible(true);
    }

    public static void deleteAccount(){
        JFrame delFrame = new JFrame("Delete Account");
        delFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        delFrame.setSize(430, 110);
        delFrame.setLayout(new BorderLayout());

        JPanel delPanel = new JPanel();
        delPanel.setLayout(new BorderLayout());
        delPanel.setBackground(new Color(240, 240, 240));

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Dialog", Font.BOLD, 12));
        textArea.setMargin(new Insets(10, 20, 10, 10));
        textArea.setBackground(new Color(240, 240, 240));
        textArea.getCaret().setVisible(false);
        textArea.getCaret().deinstall(textArea);

        textArea.append("Are you sure you want to PERMANENTLY DELETE your account?");
        delPanel.add(textArea, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton yesButton = new JButton("Yes");
        yesButton.setFocusable(false);
        JButton noButton = new JButton("No");
        noButton.setFocusable(false);

        Dimension buttonSize = new Dimension(100, 30);
        yesButton.setPreferredSize(buttonSize);
        noButton.setPreferredSize(buttonSize);

        yesButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        noButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < accounts.size(); i++) {
                    SimpleEntry<String, String> entry = accounts.get(i);
                    if (entry.getKey().equals(currentAccount)) {
                        accounts.remove(i);
                        JOptionPane.showMessageDialog(delFrame, "Account Successfully Deleted.");
                        delFrame.dispose();
                        loginScreen();
                    }
                }
            }
        });

        noButton.addActionListener(e -> {
            delFrame.dispose();
            firstScreen();
        });

        delFrame.add(delPanel, BorderLayout.CENTER);
        delFrame.add(buttonPanel, BorderLayout.SOUTH);
        delFrame.setLocationRelativeTo(null);
        delFrame.setVisible(true);
    }

    public static void employeeLogin(){
        JFrame loginFrame = new JFrame();
        JPanel loginPanel = new JPanel();
        loginFrame.setSize(300, 165);
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.PAGE_AXIS));

        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        textPane.setFont(new Font("Dialog", Font.BOLD, 12));
        textPane.setMargin(new Insets(10, 20, 10, 10));
        textPane.setBackground(new Color(240, 240, 240));
        textPane.setFocusable(false);

        StyledDocument doc = textPane.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        textPane.setText("Only Employees can Update Movies.\nPlease Enter Employee Password.");

        loginPanel.add(textPane, BorderLayout.CENTER);

        JPanel passPanel = new JPanel();
        JPasswordField passTextField = new JPasswordField(15);
        JLabel passText = new JLabel("Password: ");
        passPanel.add(passText);
        passPanel.add(passTextField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton changeButton = new JButton("Login");
        changeButton.setFocusable(false);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFocusable(false);

        Dimension buttonSize = new Dimension(100, 30);
        changeButton.setPreferredSize(buttonSize);
        cancelButton.setPreferredSize(buttonSize);

        changeButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        cancelButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        loginPanel.add(passPanel);
        buttonPanel.add(changeButton);
        buttonPanel.add(cancelButton);
        loginPanel.add(buttonPanel);

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(passTextField.getPassword());

                if(password.equals("Movies1234")){
                    loginFrame.dispose();
                    changeMovies();
                }
                else{

                    JOptionPane.showMessageDialog(loginFrame, "Incorrect Username or Password.", "Error", JOptionPane.ERROR_MESSAGE);
                    loginFrame.dispose();
                    employeeLogin();
                }
            }
        });

        cancelButton.addActionListener(e -> {
            loginFrame.dispose();
            firstScreen();
        });

        loginPanel.add(Box.createVerticalStrut(15));
        loginFrame.add(loginPanel);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }

    public static void addBkash(){
        JFrame bkashFrame = new JFrame("Add Bkash");
        JPanel bkashPanel = new JPanel();
        bkashFrame.setSize(300, 150);
        bkashPanel.setLayout(new BoxLayout(bkashPanel, BoxLayout.PAGE_AXIS));

        JPanel numPanel = new JPanel();
        JTextField userTextField = new JTextField(15);
        JLabel userText = new JLabel("Number: ");
        numPanel.add(userText);
        numPanel.add(userTextField);

        JPanel pinPanel = new JPanel();
        JPasswordField passTextField = new JPasswordField(15);
        JLabel passText = new JLabel("        PIN: ");
        pinPanel.add(passText);
        pinPanel.add(passTextField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addButton = new JButton("Add");
        addButton.setFocusable(false);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFocusable(false);

        Dimension buttonSize = new Dimension(100, 30);
        addButton.setPreferredSize(buttonSize);
        cancelButton.setPreferredSize(buttonSize);

        addButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        cancelButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        bkashPanel.add(numPanel);
        bkashPanel.add(pinPanel);
        bkashPanel.add(Box.createVerticalStrut(10));
        bkashPanel.add(buttonPanel);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bkashNum = userTextField.getText();
                String pass = new String(passTextField.getPassword());

                try {
                    int pin = Integer.parseInt(pass);

                    if(bkashNum.length() == 11 && pass.length() == 5) {
                        JOptionPane.showMessageDialog(bkashFrame, "bKash Number Successfully Added.");
                        SimpleEntry<String, Integer> key = new SimpleEntry<>(bkashNum, pin);
                        bkash.put(key, currentAccount);
                        bkashFrame.dispose();
                        firstScreen();
                    } else {
                        JOptionPane.showMessageDialog(bkashFrame, "Invalid bKash Number or PIN.", "Error", JOptionPane.ERROR_MESSAGE);
                        bkashFrame.dispose();
                        addBkash();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(bkashFrame, "PIN must be numeric.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(e -> {
            bkashFrame.dispose();
            firstScreen();
        });
        bkashFrame.add(bkashPanel);
        bkashFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        bkashFrame.setLocationRelativeTo(null);
        bkashFrame.setVisible(true);
    }

    public static void firstScreen(){
        JFrame fsFrame = new JFrame();
        fsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fsFrame.setSize(300, 460);
        fsFrame.setLayout(null);

        JButton button1 = new JButton("1. Select Movie");
        button1.setHorizontalAlignment(SwingConstants.LEFT);
        button1.setFocusable(false);
        button1.setBounds(50, 20, 200, 30);
        fsFrame.add(button1);

        JButton button2 = new JButton("2. Update Movie");
        button2.setHorizontalAlignment(SwingConstants.LEFT);
        button2.setFocusable(false);
        button2.setBounds(50, 70, 200, 30);
        fsFrame.add(button2);

        JButton button3 = new JButton("3. Change Username");
        button3.setHorizontalAlignment(SwingConstants.LEFT);
        button3.setFocusable(false);
        button3.setBounds(50, 120, 200, 30);
        fsFrame.add(button3);

        JButton button4 = new JButton("4. Change Password");
        button4.setHorizontalAlignment(SwingConstants.LEFT);
        button4.setFocusable(false);
        button4.setBounds(50, 170, 200, 30);
        fsFrame.add(button4);

        JButton button5 = new JButton("5. Account Info");
        button5.setHorizontalAlignment(SwingConstants.LEFT);
        button5.setFocusable(false);
        button5.setBounds(50, 220, 200, 30);
        fsFrame.add(button5);

        JButton addBkash = new JButton("6. Add/Update bKash");
        addBkash.setHorizontalAlignment(SwingConstants.LEFT);
        addBkash.setFocusable(false);
        addBkash.setBounds(50, 270, 200, 30);
        fsFrame.add(addBkash);

        JButton button6 = new JButton("7. Delete Account");
        button6.setHorizontalAlignment(SwingConstants.LEFT);
        button6.setFocusable(false);
        button6.setBounds(50, 320, 200, 30);
        fsFrame.add(button6);

        JButton button7 = new JButton("8. Logout");
        button7.setHorizontalAlignment(SwingConstants.LEFT);
        button7.setFocusable(false);
        button7.setBounds(50, 370, 200, 30);
        fsFrame.add(button7);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fsFrame.dispose();
                printGenre(1);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fsFrame.dispose();
                employeeLogin();
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fsFrame.dispose();
                updateAccountName();
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fsFrame.dispose();
                updatePassword();
            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fsFrame.dispose();
                accountInfo();
            }
        });

        addBkash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fsFrame.dispose();
                addBkash();
            }
        });

        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fsFrame.dispose();
                deleteAccount();
            }
        });

        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fsFrame.dispose();
                loginScreen();
            }
        });

        fsFrame.setLocationRelativeTo(null);
        fsFrame.setVisible(true);
    }

    public static void changeMovies(){
        JFrame changeFrame = new JFrame("Update Movies");
        changeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        changeFrame.setSize(300, 260);
        changeFrame.setLayout(null);

        JButton button1 = new JButton("1. Add Movie");
        button1.setHorizontalAlignment(SwingConstants.LEFT);
        button1.setFocusable(false);
        button1.setBounds(50, 20, 200, 30);
        changeFrame.add(button1);

        JButton button2 = new JButton("2. Update Price");
        button2.setHorizontalAlignment(SwingConstants.LEFT);
        button2.setFocusable(false);
        button2.setBounds(50, 70, 200, 30);
        changeFrame.add(button2);

        JButton button3 = new JButton("3. Remove Movie");
        button3.setHorizontalAlignment(SwingConstants.LEFT);
        button3.setFocusable(false);
        button3.setBounds(50, 120, 200, 30);
        changeFrame.add(button3);

        JButton button4 = new JButton("4. Back");
        button4.setHorizontalAlignment(SwingConstants.LEFT);
        button4.setFocusable(false);
        button4.setBounds(50, 170, 200, 30);
        changeFrame.add(button4);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeFrame.dispose();
                printGenre(21);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeFrame.dispose();
                printGenre(22);
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeFrame.dispose();
                printGenre(23);
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeFrame.dispose();
                firstScreen();
            }
        });

        changeFrame.setLocationRelativeTo(null);
        changeFrame.setVisible(true);
    }

    public static void login(){
        JFrame loginFrame = new JFrame("Login");
        JPanel loginPanel = new JPanel();
        loginFrame.setSize(300, 150);
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.PAGE_AXIS));


        JPanel userPanel = new JPanel();
        JTextField userTextField = new JTextField(15);
        JLabel userText = new JLabel("Username: ");
        userPanel.add(userText);
        userPanel.add(userTextField);

        JPanel passPanel = new JPanel();
        JPasswordField passTextField = new JPasswordField(15);
        JLabel passText = new JLabel("Password: ");
        passPanel.add(passText);
        passPanel.add(passTextField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton changeButton = new JButton("Login");
        changeButton.setFocusable(false);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFocusable(false);

        Dimension buttonSize = new Dimension(100, 30);
        changeButton.setPreferredSize(buttonSize);
        cancelButton.setPreferredSize(buttonSize);

        changeButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        cancelButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        loginPanel.add(userPanel);
        loginPanel.add(userPanel);
        loginPanel.add(passPanel);
        buttonPanel.add(changeButton);
        buttonPanel.add(cancelButton);
        loginPanel.add(buttonPanel);

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userTextField.getText();
                String password = new String(passTextField.getPassword());

                boolean accfound = false;
                for (SimpleEntry<String, String> i : accounts) {
                    if (i.getKey().equals(username) && i.getValue().equals(password)) {
                        accfound = true;
                        break;
                    }
                }
                if(accfound){
                    currentAccount = username;
                    loginFrame.dispose();
                    firstScreen();
                }
                else{
                    JOptionPane.showMessageDialog(loginFrame, "Incorrect Username or Password.", "Error", JOptionPane.ERROR_MESSAGE);
                    loginFrame.dispose();
                    login();
                }
            }
        });

        cancelButton.addActionListener(e -> {
            loginFrame.dispose();
            loginScreen();
        });

        loginPanel.add(Box.createVerticalStrut(15));
        loginFrame.add(loginPanel);
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }

    public static void createAccount(){
        JFrame createAccFrame = new JFrame("Create Account");
        JPanel accPanel = new JPanel();
        createAccFrame.setSize(300, 150);
        accPanel.setLayout(new BoxLayout(accPanel, BoxLayout.PAGE_AXIS));


        JPanel userPanel = new JPanel();
        JTextField userTextField = new JTextField(15);
        JLabel userText = new JLabel("Username: ");
        userPanel.add(userText);
        userPanel.add(userTextField);

        JPanel passPanel = new JPanel();
        JPasswordField passTextField = new JPasswordField(15);
        JLabel passText = new JLabel("Password: ");
        passPanel.add(passText);
        passPanel.add(passTextField);

        JButton loginButton = new JButton("Create Account");
        loginButton.setFocusable(false);

        accPanel.add(userPanel);
        accPanel.add(passPanel);
        accPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userTextField.getText();
                String password = new String(passTextField.getPassword());

                accounts.add(new SimpleEntry<>(username, password));
                createAccFrame.dispose();
                loginScreen();
            }
        });

        accPanel.add(userPanel);
        accPanel.add(passPanel);
        accPanel.add(loginButton);
        accPanel.add(Box.createVerticalStrut(15));
        createAccFrame.add(accPanel);
        createAccFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createAccFrame.setLocationRelativeTo(null);
        createAccFrame.setVisible(true);
    }

    public static void loginScreen(){
        JFrame firstFrame = new JFrame("MovieStore");
        firstFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        firstFrame.setSize(300, 280);
        firstFrame.setLayout(null);

        JButton button1 = new JButton("1. Login");
        button1.setHorizontalAlignment(SwingConstants.LEFT);
        button1.setFocusable(false);
        button1.setBounds(50, 50, 200, 30);
        firstFrame.add(button1);

        JButton button2 = new JButton("2. Create Account");
        button2.setHorizontalAlignment(SwingConstants.LEFT);
        button2.setFocusable(false);
        button2.setBounds(50, 100, 200, 30);
        firstFrame.add(button2);

        JButton button3 = new JButton("3. Exit");
        button3.setHorizontalAlignment(SwingConstants.LEFT);
        button3.setFocusable(false);
        button3.setBounds(50, 150, 200, 30);
        firstFrame.add(button3);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstFrame.dispose();
                login();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstFrame.dispose();
                createAccount();
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        firstFrame.setLocationRelativeTo(null);
        firstFrame.setVisible(true);
    }

    public static void main(String[] args){
        loginScreen();
    }
}