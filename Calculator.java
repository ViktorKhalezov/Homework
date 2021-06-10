import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Calculator extends JFrame {

    JTextField inputArea;

    public Calculator() {

        setTitle("Calculator");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setBounds(100, 100, 500, 600);

        setJMenuBar(createMenuBar());

        Question();

        setVisible(true);

    }

    private void Question(){

        JLabel announcement = new JLabel("Выберите вариант калькулятора:");
        Font font = new Font("Arial", Font.BOLD, 20);
        announcement.setFont(font);
        add(announcement, BorderLayout.NORTH);

        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new GridLayout(2,1));

        JButton byScriptEngine = new JButton("Интерпретация строки с помощью Script Engine");
        byScriptEngine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangesAfterQuestion(CreateTopPanel(), questionPanel, announcement);
                add(CreateBottomPanelScriptEngine(), BorderLayout.CENTER);
            }
        });
        questionPanel.add(byScriptEngine);

        JButton withoutScriptEngine = new JButton("Интерпретация строки без Script Engine");
        withoutScriptEngine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangesAfterQuestion(CreateTopPanel(), questionPanel, announcement);
                add(CreateBottomPanelWithoutScriptEngine(), BorderLayout.CENTER);
            }
        });
        questionPanel.add(withoutScriptEngine);

        add(questionPanel, BorderLayout.CENTER);
    }

    private void ChangesAfterQuestion(JPanel topPanel, JPanel questionPanel, JLabel announcement){
        add(topPanel, BorderLayout.NORTH);
        announcement.setVisible(false);
        questionPanel.setVisible(false);
    }

    private JPanel CreateTopPanel(){
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        inputArea = new JTextField();
        inputArea.setEditable(false);
        top.add(inputArea, BorderLayout.CENTER);
        return top;
    }

    private JPanel CreateBottomPanelScriptEngine(){
        JPanel bottom = CreateEmptyBottomPanel();
        CreateBottomButtons(bottom);

        JButton calc = new JButton("=");
        calc.addActionListener(new CalcButtonActionListener(inputArea));
        bottom.add(calc);

        return bottom;
    }

    private JPanel CreateBottomPanelWithoutScriptEngine(){
        JPanel bottom = CreateEmptyBottomPanel();
        CreateBottomButtons(bottom);

        JButton calc = new JButton("=");
        calc.addActionListener(new CalcWithoutScriptEngine(inputArea));
        bottom.add(calc);

        return bottom;
    }

    private JPanel CreateEmptyBottomPanel() {
        JPanel bottom = new JPanel();
        bottom.setLayout(new GridLayout(5, 4));

        DigitButtonActionListener digitButtonActionListener = new DigitButtonActionListener(inputArea);

        for (int i = 0; i < 10; i++) {
            JButton btn = new JButton(String.valueOf(i));
            btn.addActionListener(digitButtonActionListener);
            bottom.add(btn);
        }
        return bottom;
    }

    private void CreateBottomButtons(JPanel bottom){

        JButton clear = new JButton("C");
        clear.addActionListener(e -> inputArea.setText(""));
        bottom.add(clear);

        JButton plus = new JButton("+");
        plus.addActionListener(e -> inputArea.setText(inputArea.getText() + "+"));
        bottom.add(plus);

        JButton minus = new JButton("-");
        minus.addActionListener(e -> inputArea.setText(inputArea.getText() + "-"));
        bottom.add(minus);

        JButton multiplySign = new JButton("*");
        multiplySign.addActionListener(e -> inputArea.setText(inputArea.getText() + "*"));
        bottom.add(multiplySign);

        JButton divideSign = new JButton("/");
        divideSign.addActionListener(e -> inputArea.setText(inputArea.getText() + "/"));
        bottom.add(divideSign);

        JButton leftBracket = new JButton("(");
        leftBracket.addActionListener(e -> inputArea.setText(inputArea.getText() + "("));
        bottom.add(leftBracket);

        JButton rightBracket = new JButton(")");
        rightBracket.addActionListener(e -> inputArea.setText(inputArea.getText() + ")"));
        bottom.add(rightBracket);

        JButton squareRoot = new JButton("sqr");
        squareRoot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(inputArea.getText());
                float squareRoot = (float) Math.sqrt(a);
                inputArea.setText(String.valueOf(squareRoot));
            }
        });
        bottom.add(squareRoot);
    }

    private JMenuBar createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Файл");

        JMenuItem openItem = new JMenu("Открыть");
        fileMenu.add(openItem);

        JMenuItem exitItem = new JMenu("Выйти");
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);
        return menuBar;
    }

}
