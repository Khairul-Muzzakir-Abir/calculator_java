import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Basic Calculator");
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 24));
        frame.add(textField, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            panel.add(button);

            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String current = textField.getText();
                    if (text.equals("=")) {
                        try {
                            double result = eval(current);
                            textField.setText(Double.toString(result));
                        } catch (Exception ex) {
                            textField.setText("Error");
                        }
                    } else {
                        textField.setText(current + text);
                    }
                }
            });
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // Simple expression evaluator (basic only)
    public static double eval(String expr) {
        return new javax.script.ScriptEngineManager()
            .getEngineByName("JavaScript")
            .eval(expr) instanceof Number
            ? ((Number) new javax.script.ScriptEngineManager()
                .getEngineByName("JavaScript")
                .eval(expr)).doubleValue()
            : 0;
    }
}
