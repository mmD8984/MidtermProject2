package frontend;

import backend.AES;
import backend.DES;

import javax.crypto.SecretKey;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Base64;
import java.util.StringTokenizer;

//Xử lý sự kiện - thuật toán đối xứng
public class SymListener implements ActionListener {
    private JComboBox<String> algoBox;
    private JComboBox<String> typeBox;
    private JTextField inputField;
    private JTextField encryptField;
    private JTextField decryptField;
    private JButton encryptBtn;
    private JButton decryptBtn;

    private AES aes;
    private DES des;

    private String selectAlgo = "AES";
    private String selectType = "File";

    private String srcf, desf, de, path, ext;

    public SymListener(JComboBox<String> algoBox, JComboBox<String> typeBox, JTextField inputField,
                       JTextField encryptField, JTextField decryptField, JButton encryptBtn, JButton decryptBtn) throws Exception {
        this.algoBox = algoBox;
        this.typeBox = typeBox;
        this.inputField = inputField;
        this.encryptField = encryptField;
        this.decryptField = decryptField;
        this.encryptBtn = encryptBtn;
        this.decryptBtn = decryptBtn;

        this.aes = new AES();
        this.des = new DES();

        aes.genKey();
        des.genKey();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == algoBox) {
                algoSelection();
            } else if (e.getSource() == typeBox) {
                typeSelection();
            } else if (e.getSource() == encryptBtn) {
                encryptAction();
            } else if (e.getSource() == decryptBtn) {
                decryptAction();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
        }
    }

    //	1- Xác định thuật toán
    private void algoSelection() {
        selectAlgo = (String) algoBox.getSelectedItem();
    }

    //	2- Xác định loại dữ liệu
    private void typeSelection() {
        selectType = (String) typeBox.getSelectedItem();
    }

    //	3- Mã hóa
    private void encryptAction() throws Exception {
        String input = inputField.getText();
        srcf = inputField.getText();

        if (selectType.equals("Text")) {
            if (selectAlgo.equals("AES")) {
                encryptField.setText(Base64.getEncoder().encodeToString(aes.encrypt(input)));
            } else if (selectAlgo.equals("DES")) {
                encryptField.setText(Base64.getEncoder().encodeToString(des.encrypt(input)));
            }
        } else if (selectType.equals("File")) {
//			Xác định desf
            StringTokenizer st = new StringTokenizer(srcf, ".");
            path = st.nextToken();
            ext = st.nextToken();
            desf = path + "2" + ext;
            if (selectAlgo.equals("AES")) {
                encryptField.setText(String.valueOf(aes.encryptFile(srcf, desf)));
            } else if (selectAlgo.equals("DES")) {
                encryptField.setText(String.valueOf(des.encryptFile(srcf, desf)));
            }
        }
    }

    //	4- Giải mã
    private void decryptAction() throws Exception {
        byte[] input = Base64.getDecoder().decode(encryptField.getText());

        if (selectType.equals("Text")) {
            if (selectAlgo.equals("AES")) {
                decryptField.setText(aes.decrypt(input));
            } else if (selectAlgo.equals("DES")) {
                decryptField.setText(des.decrypt(input));
            }
        } else if (selectType.equals("File")) {
//			Xác định de
            StringTokenizer st = new StringTokenizer(srcf, ".");
            path = st.nextToken();
            ext = st.nextToken();
            de = path + "3" + ext;
            if (selectAlgo.equals("AES")) {
                decryptField.setText(String.valueOf(aes.decryptFile(desf, de)));
            } else if (selectAlgo.equals("DES")) {
                decryptField.setText(String.valueOf(des.decryptFile(desf, de)));

            }
        }
    }
}
