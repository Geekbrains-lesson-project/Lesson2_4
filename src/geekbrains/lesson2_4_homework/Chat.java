package geekbrains.lesson2_4_homework;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Chat extends JFrame{
    JPanel jpN = new JPanel(new GridLayout());
    JPanel jpS = new JPanel(new GridLayout());

    JButton jbs = new JButton("Отправить >");
    JTextArea jta = new JTextArea();
    JScrollPane jsp = new JScrollPane(jta);
    JTextField jtf = new JTextField();

    JMenuBar mainMenu = new JMenuBar();
    JMenu mFile = new JMenu("Файл");
    JMenu mInfo = new JMenu("Информация");
    JMenuItem mFileExit = new JMenuItem("Выход");
    JMenuItem mHelpAbout = new JMenuItem("О программе ");

    PrintWriter pw = new PrintWriter(new FileWriter("chat.txt"));

    Chat() throws IOException {
        super("chat");
        setSize(500, 600);
        setMinimumSize(new Dimension(400, 500));

        jta.setLineWrap(true);
        jta.setEditable(false);


        //события

        jbs.addActionListener(e -> {
            sendMessage();
        });
        jtf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) sendMessage();
            }
        });

        jpN.add(jsp);
        jpS.add(jtf);
        jpS.add(jbs);

        add(jpN);
        add("South", jpS);

        //меню

        setJMenuBar(mainMenu);
        mainMenu.add(mFile);
        mainMenu.add(mInfo);
        mFile.add(mFileExit);
        mInfo.add(mHelpAbout);
        mFileExit.addActionListener(e -> System.exit(0));
        mHelpAbout.addActionListener(e -> JOptionPane.showMessageDialog(null,
                "chat v 1.0", "О прграмме:", JOptionPane.INFORMATION_MESSAGE));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // отправка сообщения


    void sendMessage() {
        String out = jtf.getText();
        jta.append(getTime() + ": " + out + "\n");
        pw.append(getTime() + ": " + out + "\n");
        pw.flush();
        jtf.setText("");
        jtf.grabFocus();
    }


    public String getTime() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

}