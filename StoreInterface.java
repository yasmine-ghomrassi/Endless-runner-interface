import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class StoreInterface {

    private Map<String, Integer> items;
    private Map<String, String> itemDescriptions; // Added item descriptions
    private int playerScore = 200; // Initialized player score

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StoreInterface store = new StoreInterface();
            store.createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Game Store");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Welcome to Game Store");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.PINK);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel itemsPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        itemsPanel.setBackground(Color.WHITE);
        itemsPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        items = new HashMap<>();
        itemDescriptions = new HashMap<>();

        items.put("Item1", 50);
        itemDescriptions.put("Item1", "Get a 15% discount on your second purchase.");

        items.put("Item2", 75);
        itemDescriptions.put("Item2", "A stylish CHACHIA hat.");

        items.put("Item3", 90);
        itemDescriptions.put("Item3", "A PALASTINE FLAG to show your support.");

        items.put("Item4", 100);
        itemDescriptions.put("Item4", "Earn an extra life in the next game.");

        items.put("Item5", 120);
        itemDescriptions.put("Item5", "Receive 50 points if you collect 10 CROSTINA.");

        items.put("Item6", 150);
        itemDescriptions.put("Item6", "Unlock a SECRET ABILITY.");

        for (int i = 1; i <= 6; i++) {
            String itemName = "Item" + i;
            int itemPrice = items.get(itemName);
            String itemDescription = itemDescriptions.get(itemName);

            ImageIcon icon = createImageIcon("images/item" + i + ".png");
            Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(image);

            JButton button = new JButton("<html><center>Buy<br>" + itemName + "<br>for " + itemPrice + " points<br><br>" + itemDescription + "</center></html>", scaledIcon);
            button.setVerticalTextPosition(SwingConstants.BOTTOM);
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setFont(new Font("Arial", Font.PLAIN, 14));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);

            button.setBackground(Color.GRAY); 
            button.setBorder(BorderFactory.createLineBorder(new Color(81, 70, 68), 2)); // Emperor Gray border

            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(Color.pink); 
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(Color.GRAY); 
                }
            });

            button.addActionListener(e -> {
             
                    if (playerScore >= itemPrice) {
                        playerScore -= itemPrice;
                        String message = "Item '" + itemName + "' purchased! Remaining score: " + playerScore;
                        JOptionPane.showMessageDialog(frame, message);
                    } else {
                        String errorMessage = "Not enough score to buy item '" + itemName + "'!";
                        JOptionPane.showMessageDialog(frame, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                    }
            });

            itemsPanel.add(button);
        }

        mainPanel.add(itemsPanel, BorderLayout.CENTER);
        frame.add(mainPanel);
        frame.setVisible(true);
        frame.pack();
    }

    private ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
