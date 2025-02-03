import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingCalculator extends JFrame implements ActionListener {

    private JTextField display;
    private double num1 = 0, num2 = 0, result = 0;
    private char operator = ' ';


    public SwingCalculator() {
        setTitle("Swing Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C" // Clear button
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (Character.isDigit(command.charAt(0)) || command.equals(".")) {
            display.setText(display.getText() + command);
        } else if (command.equals("=")) {
            calculate();
        } else if (command.equals("C")) {
            clear();
        } else { // Operator (+, -, *, /)
            operator = command.charAt(0);
            num1 = Double.parseDouble(display.getText());
            display.setText("");
        }
    }

    private void calculate() {
        if (operator == ' ') return; // No operation selected

        num2 = Double.parseDouble(display.getText());
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 == 0) {
                    display.setText("Error: Division by zero");
                    return;
                }
                result = num1 / num2;
                break;
        }
        display.setText(String.valueOf(result));
        operator = ' '; // Reset operator for next calculation
        num1 = result; // Allow chaining operations
    }

    private void clear() {
        display.setText("");
        num1 = 0;
        num2 = 0;
        result = 0;
        operator = ' ';
    }

    public static void main(String[] args) {
        new SwingCalculator();
    }
}
