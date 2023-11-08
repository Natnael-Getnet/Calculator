import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener {
    JFrame jFrame;
    JPanel jPanel;
    JTextField jTextField;
    JTextArea jTextArea;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[15];
    Font buttonFont = new Font("Courier", Font.PLAIN, 20);
    Font textFeildFont = new Font("Courier", Font.PLAIN, 18);
    Font textAreaFont = new Font("Courier", Font.PLAIN, 16);

    double operand1 = 0;
    double operand2 = 0;
    char operator = 0;

    public Main() {
        jFrame = new JFrame("Calculator");
        jFrame.setSize(350, 410);
        jFrame.setBackground(Color.BLACK);
        jFrame.setLayout(null);

        // Create and add the JTextArea
        jTextArea = new JTextArea();
        jTextArea.setBounds(0, 0, 350, 150);
        jTextArea.setEditable(false);
        jTextArea.setBackground(Color.GRAY);
        jTextArea.setForeground(Color.WHITE);
        jTextArea.setFont(textAreaFont);
        jFrame.add(jTextArea);

        // Create and add the JTextField
        jTextField = new JTextField();
        jTextField.setBounds(0, 150, 350, 60);
        jTextField.setBackground(Color.DARK_GRAY);
        jTextField.setFont(textFeildFont);
        jTextField.setForeground(Color.WHITE);
        jTextField.setEditable(false);
        jTextField.setBorder(null);
        jFrame.add(jTextField);

        // Create the jPanel and set its layout
        jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(5, 5, 5, 5)); // 5x5 grid with gaps
        jPanel.setBounds(0, 210, 350, 200); // Start at the bottom (y=100)
        jPanel.setBackground(Color.BLACK);
        jPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setBackground(Color.gray);
            numberButtons[i].setForeground(Color.WHITE);
            numberButtons[i].setFont(buttonFont);
            numberButtons[i].setBorder(null);
        }

        functionButtons[0] = new JButton("X");
        functionButtons[1] = new JButton("(");
        functionButtons[2] = new JButton(")");
        functionButtons[3] = new JButton("mod");
        functionButtons[4] = new JButton("π");
        functionButtons[5] = new JButton("÷");
        functionButtons[6] = new JButton("√");
        functionButtons[7] = new JButton("*");
        functionButtons[8] = new JButton("x²");
        functionButtons[9] = new JButton("-");
        functionButtons[10] = new JButton(".");
        functionButtons[11] = new JButton("%");
        functionButtons[12] = new JButton("+");
        functionButtons[13] = new JButton("=");
        functionButtons[13].setBackground(Color.darkGray);
        functionButtons[13].setForeground(Color.WHITE);
        functionButtons[13].setFont(buttonFont);
        functionButtons[13].setBorder(null);

        // Set the size and apply rounded border to function buttons
        for (int i = 0; i < 14; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setPreferredSize(new Dimension(70, 70));
            functionButtons[i].setBackground(Color.darkGray);
            functionButtons[i].setForeground(Color.WHITE);
            functionButtons[i].setFont(buttonFont);
            functionButtons[i].setBorder(null);
        }

        jPanel.add(functionButtons[0]);
        jPanel.add(functionButtons[1]);
        jPanel.add(functionButtons[2]);
        jPanel.add(functionButtons[3]);
        jPanel.add(functionButtons[4]);

        jPanel.add(numberButtons[7]);
        jPanel.add(numberButtons[8]);
        jPanel.add(numberButtons[9]);
        jPanel.add(functionButtons[5]);
        jPanel.add(functionButtons[6]);

        jPanel.add(numberButtons[4]);
        jPanel.add(numberButtons[5]);
        jPanel.add(numberButtons[6]);
        jPanel.add(functionButtons[7]);
        jPanel.add(functionButtons[8]);

        jPanel.add(numberButtons[1]);
        jPanel.add(numberButtons[2]);
        jPanel.add(numberButtons[3]);
        jPanel.add(functionButtons[9]);
        jPanel.add(functionButtons[13]);

        jPanel.add(numberButtons[0]);
        jPanel.add(functionButtons[10]);
        jPanel.add(functionButtons[11]);
        jPanel.add(functionButtons[12]);

        // Add the jPanel to the frame
        jFrame.add(jPanel);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        for (int i = 0; i < 10; i++) {
            if (actionEvent.getSource() == numberButtons[i]) {
                jTextField.setText(jTextField.getText().concat(String.valueOf(i)));
                break;
            }
        }


        if (actionEvent.getSource() == functionButtons[13]) {
            operand2 = Double.parseDouble(jTextField.getText());
            double result = calculate(operand1, operand2, operator);
//            String equation = jTextField.getText().concat("=").concat(String.valueOf(result));
            jTextArea.setText(
                    jTextArea.getText()
                            .concat("\n")
                            .concat(String.valueOf(operand1))
                            .concat(String.valueOf(operator))
                            .concat(String.valueOf(operand2))
                            .concat("=")
                            .concat(String.valueOf(result)));
            jTextField.setText("");
        }

        if (actionEvent.getSource() == functionButtons[12]) {
            operator = '+';
            operand1 = Double.parseDouble(jTextField.getText());
//            jTextField.setText(jTextField.getText().concat("+"));
            jTextField.setText("");

        } else if (actionEvent.getSource() == functionButtons[9]) {
            operator = '-';
            operand1 = Double.parseDouble(jTextField.getText());
//            jTextField.setText(jTextField.getText().concat("-"));
            jTextField.setText("");
        } else if (actionEvent.getSource() == functionButtons[5]) {
            operator = '/';
            operand1 = Double.parseDouble(jTextField.getText());
//            jTextField.setText(jTextField.getText().concat("/"));
            jTextField.setText("");
        } else if (actionEvent.getSource() == functionButtons[7]) {
            operator = '*';
            operand1 = Double.parseDouble(jTextField.getText());
//            jTextField.setText(jTextField.getText().concat("*"));
            jTextField.setText("");
        } else if (actionEvent.getSource() == functionButtons[10]) {
            jTextField.setText(jTextField.getText().concat("."));
        }
    }

    public static double calculate(double operand1, double operand2, char operator) {
        return switch (operator) {
            case '+' -> operand1 + operand2;
            case '-' -> operand1 - operand2;
            case '*' -> operand1 * operand2;
            case '/' -> operand1 / operand2;
            default -> 0.0;
        };

    }
}
