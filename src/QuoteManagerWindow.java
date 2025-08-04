import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ColorModel;

public class QuoteManagerWindow extends JFrame implements ActionListener {
    public Color color = new Color(255, 255, 255);

    private final QuoteManager manager = new QuoteManager();
    private final JTextArea TEXT_AREA = new JTextArea(20,50);
    private final JTextField INPUT_FIELD = new JTextField(40);
    private final JTextField INDEX_FIELD = new JTextField(5);

    private final JButton BUTTON1 = new JButton("Add");
    private final JButton BUTTON2 = new JButton("Show All");
    private final JButton BUTTON3 = new JButton("Remove");
    private final JButton BUTTON4 = new JButton("Edit");
    private final JButton BUTTON5 = new JButton("Random");
    private final JButton BUTTON6 = new JButton("Search");

    public QuoteManagerWindow() throws HeadlessException {
        setTitle("Quote Manager");
        setLayout((new BorderLayout()));
        TEXT_AREA.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(TEXT_AREA);
        add(scrollPane, BorderLayout.CENTER);

        JPanel contentPane = new JPanel(new FlowLayout());
        contentPane.add(new JLabel("Quote:"));
        contentPane.add(INPUT_FIELD);
        contentPane.add(new JLabel("Index:"));
        contentPane.add(INDEX_FIELD);
        contentPane.add(BUTTON1);
        contentPane.add(BUTTON2);
        contentPane.add(BUTTON3);
        contentPane.add(BUTTON4);
        contentPane.add(BUTTON5);
        contentPane.add(BUTTON6);

        add(contentPane, BorderLayout.SOUTH);

        BUTTON1.addActionListener(this);
        BUTTON2.addActionListener(this);
        BUTTON3.addActionListener(this);
        BUTTON4.addActionListener(this);
        BUTTON5.addActionListener(this);
        BUTTON6.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) throws NullPointerException {
        String input = INPUT_FIELD.getText().trim();
        String indexText = INDEX_FIELD.getText().trim();
        if (e.getSource() == BUTTON1) {
            if (!input.isEmpty()){
                manager.addQuote(input);
                TEXT_AREA.setText("Quote Added. \n");
            } else {
                TEXT_AREA.setText("input is empty. \n");
            }
        }else if (e.getSource() == BUTTON2) {
            TEXT_AREA.setText(manager.showQuote());
        }else if (e.getSource() == BUTTON3) {
            try {
                int index = Integer.parseInt(indexText) - 1;
                if (manager.removeQuote(index)) {
                    TEXT_AREA.setText("Quote Removed. \n");
                } else {
                    TEXT_AREA.setText("invalid Index. \n");
                }
            } catch (NumberFormatException ex) {
                TEXT_AREA.setText("Please enter a valid Index. \n");
            }

        } else if (e.getSource() == BUTTON4) {
            try {
                int index = Integer.parseInt(indexText) - 1;
                if (!input.isEmpty() && manager.editQuote(index,input)){
                    TEXT_AREA.setText("Quote Edited. \n\n" + manager.showQuote());
                } else {
                    TEXT_AREA.setText("invalid Index or empty quote. \n");
                }
            }catch (NumberFormatException ex) {
                TEXT_AREA.setText("Please enter a valid index. \n");
            }
        }else if (e.getSource() == BUTTON5) {
            TEXT_AREA.setText("random quote: \n" + manager.randomQuote());
        } else if (e.getSource() == BUTTON6) {
            if(!input.isEmpty()){
                TEXT_AREA.setText(manager.searchQuote(input));
            } else {
                TEXT_AREA.setText("Please enter keyword to search.\n");
            }
        }
        INPUT_FIELD.setText("");
        INDEX_FIELD.setText("");
    }
}
