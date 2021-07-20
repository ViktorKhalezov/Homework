import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class OnlineChat extends JFrame {


    public OnlineChat(){
       setBounds(500, 500, 400, 300);
       setTitle("Online chat");
       setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane);

        JPanel lowPanel = new JPanel();
        add(lowPanel, BorderLayout.SOUTH);
        lowPanel.setLayout(new GridLayout(3, 1));

        JLabel messageLabel = new JLabel("Введите сообщение:");
        lowPanel.add(messageLabel);

        JTextField textField = new JTextField();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                textField.requestFocus();
            }
        });
        lowPanel.add(textField, BorderLayout.WEST);
        textField.addActionListener(e -> insertText(textArea, textField));

        JButton button = new JButton("Отправить сообщение");
        lowPanel.add(button, BorderLayout.EAST);
        button.addActionListener(e -> insertText(textArea, textField));

        setVisible(true);
    }

    public static void main(String[] args) {
        new OnlineChat();
    }

    private void insertText(JTextArea textArea, JTextField textField){
        textArea.append(textField.getText() + "\n");
        textField.setText("");
        textField.requestFocus();
    }

}
