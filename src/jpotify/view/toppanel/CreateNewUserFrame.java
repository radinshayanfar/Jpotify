package jpotify.view.toppanel;

import jpotify.controller.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CreateNewUserFrame extends JFrame implements MouseListener {
    private MainController controller;
    private JButton cancel, ok;
    private JLabel name, ip, port;
    private JTextField nameF, ipF, portF;

    public CreateNewUserFrame(MainController mainController) throws HeadlessException {

        controller = mainController;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(4,2));
        this.setSize(300, 150);
        this.setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().width / 2 - this.getSize().getWidth() / 2)
                , (int) (Toolkit.getDefaultToolkit().getScreenSize().height / 2 - this.getSize().getHeight() / 2));

        name = new JLabel("name");
        this.add(name);
        nameF = new JTextField();
        this.add(nameF);
        ip = new JLabel("ip");
        this.add(ip);
        ipF = new JTextField();
        this.add(ipF);
        port = new JLabel("port");
        this.add(port);
        portF = new JTextField();
        this.add(portF);
        cancel = new JButton("cancel");
        this.add(cancel);
        cancel.addMouseListener(this);
        ok = new JButton("ok");
        this.add(ok);
        ok.addMouseListener(this);

        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(cancel))
            this.dispose();
        if (e.getSource().equals(ok)){
            controller.addNewUser(nameF.getText(), ipF.getText(), portF.getText());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
