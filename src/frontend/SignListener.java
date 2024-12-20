package frontend;

import backend.DS;

import javax.crypto.SecretKey;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Base64;
import java.util.StringTokenizer;

//Xử lý sự kiện - chữ ký điện tử
public class SignListener implements ActionListener {
    private JComboBox<String> typeBox;
    private JTextField inputField1;
    private JTextField inputField2;
    private JTextField signField;
    private JTextField verifyField;
    private JButton signBtn;
    private JButton verifyBtn;

    private DS ds;

    private String selectType = "Text";

    private String input1,reInput;


    public SignListener(JComboBox<String> typeBox, JTextField inputField1, JTextField inputField2,
                       JTextField signField, JTextField verifyField, JButton signBtn, JButton verifyBtn) throws Exception {
        this.typeBox = typeBox;
        this.inputField1 = inputField1;
        this.inputField2 = inputField2;
        this.signField = signField;
        this.verifyField = verifyField;
        this.signBtn = signBtn;
        this.verifyBtn = verifyBtn;

        this.ds = new DS("DSA","SHA1PRNG","SUN");

        ds.genKey();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == typeBox) {
                typeSelection();
            } else if (e.getSource() == signBtn) {
                signAction();
            } else if (e.getSource() == verifyBtn) {
                verifyAction();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
        }
    }


    //	2- Xác định loại dữ liệu
    private void typeSelection() {
        selectType = (String) typeBox.getSelectedItem();
    }

    //	3- Mã hóa
    private void signAction() throws Exception {
        input1 = inputField1.getText();

        if (selectType.equals("Text")) {
                signField.setText(ds.sign(input1));
                System.out.println("nofile");
        } else if (selectType.equals("File")) {
                signField.setText(String.valueOf(ds.signFile(input1)));
            System.out.println("isfile");
        }
    }

    //	4- Giải mã
    private void verifyAction() throws Exception {
        String input2= inputField2.getText();
        if (selectType.equals("Text")) {
                verifyField.setText(String.valueOf(ds.verify(input2,ds.sign(input1))));
        } else if (selectType.equals("File")) {
                verifyField.setText(String.valueOf(ds.verifyFile(input2,ds.signFile(input1))));
        }
    }
}


//package frontend;
////change
//import backend.DS;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.security.NoSuchProviderException;
//import java.security.SignatureException;
//import java.util.Base64;
//import java.util.StringTokenizer;
//
////Xử lý sự kiện - Chữ Ký điện tử
//public class SignListener implements ActionListener {
//    private JComboBox<String> typeBox;
//    private JTextField inputField1;
//    private JTextField inputField2;
//    private JTextField signField;
//    private JTextField verifyField;
//    private JButton signBtn;
//    private JButton verifyBtn;
//
//    private DS ds;
//
//    private String  selectType = "Text";
//    private String reInput;
//
//    public SignListener(JComboBox<String> typeBox, JTextField inputField1, JTextField inputField2, JTextField signField,
//                        JTextField verifyField, JButton signBtn, JButton verifyBtn) throws NoSuchAlgorithmException, NoSuchProviderException {
//        this.typeBox = typeBox;
//        this.inputField1 = inputField1;
//        this.inputField2 = inputField2;
//        this.signField = signField;
//        this.verifyField = verifyField;
//        this.signBtn = signBtn;
//        this.verifyBtn = verifyBtn;
//
//        this.ds=new DS("DSA","SHA1PRNG","SUN");
//
//        ds.genKey();
//    }
//
//    public void actionPerformed(ActionEvent e) {
//        try {
//            if (e.getSource() == typeBox) {
//                typeSelection();
//            } else if (e.getSource() == signBtn) {
//                signAction();
//            } else if (e.getSource() == verifyBtn) {
//                verifyAction();
//            }
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
//        }
//    }
//
//    //    1- Xác định loại dữ liệu
//    private void typeSelection() {
//        selectType = (String) typeBox.getSelectedItem();
//    }
//
//    //    2- Thực hiện ký
//    private void signAction() throws SignatureException, InvalidKeyException, IOException {
//        String input = inputField1.getText();
//
//        if (selectType.equals("Text")) {
//            signField.setText(ds.sign(input));
//            System.out.println("nofile");
//        } else if (selectType.equals("File")) {
//			signField.setText(ds.signFile(input));
//            System.out.println("file");
//        }
//
////        if (selectType.equals("Text")) {
////            reInput=ds.sign(input);
////            signField.setText(ds.sign(input));
////        }else if (selectType.equals("File")){
////            reInput=ds.signFile(input);
////
////            if(reInput!=null){
////                signField.setText("Đã ký");
////            }else{
////                signField.setText("Không ký được");
////            }
////        }
//    }
//
//    //    3- Xác thực chữ ký
//    public void verifyAction() throws SignatureException, InvalidKeyException, IOException {
//        String input=inputField2.getText();
//        if (selectType.equals("Text")) {
//            if(ds.verify(input,reInput)){
//                verifyField.setText("Chữ ký đúng");
//            }else {
//                verifyField.setText("Chữ ký không đúng");
//            }
//        }else if (selectType.equals("File")) {
//            if(ds.verifyFile(input,reInput)){
//                verifyField.setText("Chữ ký hợp lệ");
//            }else {
//                verifyField.setText("Chữ ký không hợp lệ");
//            }
//        }
//    }
//}
