package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Base64;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import backend.MD5;

//Xử lý sự kiện - thuật toán đối xứng
public class HashListener implements ActionListener {
    private JComboBox<String> typeBox;
    private JTextField inputField;
    private JTextField resultField;
    private JButton btn;

    private MD5 md5;

    private String selectType = "Text";

    public HashListener(JComboBox<String> typeBox, JTextField inputField,
                        JTextField resultField, JButton btn) throws Exception {
        this.typeBox = typeBox;
        this.inputField = inputField;
        this.resultField = resultField;
        this.btn = btn;

        this.md5 = new MD5();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == typeBox) {
                typeSelection();
            } else if (e.getSource() == btn) {
                hashAction();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
        }
    }

    //	1- Xác định loại dữ liệu
    private void typeSelection() {
        selectType = (String) typeBox.getSelectedItem();
    }

    //	2- Mã hóa
    private void hashAction() throws Exception {
        String input = inputField.getText();

		if (selectType.equals("Text")) {
            resultField.setText((md5.hash(input)));
		} else if (selectType.equals("File")) {
            resultField.setText((md5.hashFile(input)));
        }
    }
}
