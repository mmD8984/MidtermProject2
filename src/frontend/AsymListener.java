package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Base64;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import backend.AES;
import backend.RSA;

public class AsymListener implements ActionListener {
    private JTextField inputField;
    private JTextField encryptField;
    private JTextField decryptField;
    private JButton encryptBtn;
    private JButton decryptBtn;

    private RSA rsa;

    public AsymListener(JTextField inputField, JTextField encryptField, JTextField decryptField, JButton encryptBtn,
                        JButton decryptBtn) throws Exception {
        this.inputField = inputField;
        this.encryptField = encryptField;
        System.out.println("anh thư");
        this.decryptField = decryptField;
        this.encryptBtn = encryptBtn;
        this.decryptBtn = decryptBtn;

        this.rsa = new RSA();

        rsa.genKey();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            if (e.getSource() == encryptBtn) {
                encryptAction();
            } else if (e.getSource() == decryptBtn) {
                decryptAction();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
        }

    }
    //	1- Mã hóa
    private void encryptAction() throws Exception {
        String input = inputField.getText();
        encryptField.setText(Base64.getEncoder().encodeToString(rsa.encrypt(input)));
    }

    private void decryptAction() throws Exception {
        byte[] input = Base64.getDecoder().decode(encryptField.getText());
        decryptField.setText(rsa.decrypt(input));

    }

}
