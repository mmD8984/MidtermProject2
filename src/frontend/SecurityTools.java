package frontend;

import javax.swing.*;
import java.awt.*;

public class SecurityTools {
    public void securityTools() throws Exception {
//        Khung giao diện chính
        JFrame frame = new JFrame("Security Tools");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
//        Navigation
        JTabbedPane nav = new JTabbedPane();

        GridBagConstraints loc = new GridBagConstraints();
        System.out.println("anh thư");

        Dimension fieldSize = new Dimension(300, 25);

        //  ---------------------------------------------------------------     Thuật toán đối xứng
        JPanel sym = new JPanel();
        sym.setLayout(new GridBagLayout());
        loc.insets = new Insets(5, 5, 5, 5);
        loc.fill = GridBagConstraints.HORIZONTAL;

        loc.gridx = 0;
        loc.gridy = 0;
        sym.add(new JLabel("Chọn thuật toán:"), loc);
        loc.gridx = 1;
        JComboBox<String> algoSymBox = new JComboBox<>(new String[]{"AES", "DES"});
        algoSymBox.setPreferredSize(fieldSize);
        sym.add(algoSymBox, loc);

        loc.gridx = 0;
        loc.gridy = 1;
        sym.add(new JLabel("Loại dữ liệu:"), loc);
        loc.gridx = 1;
        JComboBox<String> typeSymBox = new JComboBox<>(new String[]{"Text", "File"});
        typeSymBox.setPreferredSize(fieldSize);
        sym.add(typeSymBox, loc);

        loc.gridx = 0;
        loc.gridy = 2;
        sym.add(new JLabel("Nhập dữ liệu:"), loc);
        loc.gridx = 1;
        JTextField inputSym = new JTextField();
        inputSym.setPreferredSize(fieldSize);
        sym.add(inputSym, loc);

        loc.gridx = 0;
        loc.gridy = 3;
        JButton btnEnSym = new JButton("Mã hóa");
        sym.add(btnEnSym, loc);
        loc.gridx = 1;
        JTextField resultEnSym = new JTextField();
        resultEnSym.setEditable(false);
        resultEnSym.setPreferredSize(fieldSize);
        sym.add(resultEnSym, loc);

        loc.gridx = 0;
        loc.gridy = 4;
        JButton btnDeSym = new JButton("Giải mã");
        sym.add(btnDeSym, loc);
        loc.gridx = 1;
        JTextField resultDeSym = new JTextField();
        resultDeSym.setEditable(false);
        resultDeSym.setPreferredSize(fieldSize);
        sym.add(resultDeSym, loc);

//        Thêm vào thanh nav
        nav.add("Thuật toán đối xứng", sym);

//      -------------------------------------------------------------------  Thuật toán bất đối xứng
        JPanel asym = new JPanel();
        asym.setLayout(new GridBagLayout());
        loc = new GridBagConstraints();
        loc.insets = new Insets(5, 5, 5, 5);
        loc.fill = GridBagConstraints.HORIZONTAL;

        loc.gridx = 0;
        loc.gridy = 0;
        asym.add(new JLabel("Thuật toán:"), loc);
        loc.gridx = 1;
        asym.add(new JLabel("RSA"), loc);

        loc.gridx = 0;
        loc.gridy = 1;
        asym.add(new JLabel("Loại dữ liệu:"), loc);
        loc.gridx = 1;
        asym.add(new JLabel("Text"), loc);

        loc.gridx = 0;
        loc.gridy = 2;
        asym.add(new JLabel("Nhập dữ liệu:"), loc);
        loc.gridx = 1;
        JTextField inputAsym = new JTextField();
        inputAsym.setPreferredSize(fieldSize);
        asym.add(inputAsym, loc);

        loc.gridx = 0;
        loc.gridy = 3;
        JButton btnEnAsym = new JButton("Mã hóa");
        asym.add(btnEnAsym, loc);
        loc.gridx = 1;
        JTextField resultEnAsym = new JTextField();
        resultEnAsym.setEditable(false);
        resultEnAsym.setPreferredSize(fieldSize);
        asym.add(resultEnAsym, loc);

        loc.gridx = 0;
        loc.gridy = 4;
        JButton btnDeAsym = new JButton("Giải mã");
        asym.add(btnDeAsym, loc);
        loc.gridx = 1;
        JTextField resultDeAsym = new JTextField();
        resultDeAsym.setEditable(false);
        resultDeAsym.setPreferredSize(fieldSize);
        asym.add(resultDeAsym, loc);

//        Thêm vào thanh nav
        nav.add("Thuật toán bất đối xứng", asym);

//      --------------------------------------------------------------------------  Thuật toán băm
        JPanel hash = new JPanel();
        hash.setLayout(new GridBagLayout());
        loc.insets = new Insets(5, 5, 5, 5);
        loc.fill = GridBagConstraints.HORIZONTAL;

        loc.gridx = 0;
        loc.gridy = 0;
        hash.add(new JLabel("Thuật toán:"), loc);
        loc.gridx = 1;
        hash.add(new JLabel("MD5"), loc);

        loc.gridx = 0;
        loc.gridy = 1;
        hash.add(new JLabel("Loại dữ liệu:"), loc);
        loc.gridx = 1;
        JComboBox<String> typesHashBox = new JComboBox<>(new String[]{"Text", "File"});
        typesHashBox.setPreferredSize(fieldSize);
        hash.add(typesHashBox, loc);

        loc.gridx = 0;
        loc.gridy = 2;
        hash.add(new JLabel("Nhập dữ liệu:"), loc);
        loc.gridx = 1;
        JTextField inputHash = new JTextField();
        inputHash.setPreferredSize(fieldSize);
        hash.add(inputHash, loc);

        loc.gridx = 0;
        loc.gridy = 3;
        JButton btnHash = new JButton("Băm");
        hash.add(btnHash, loc);
        loc.gridx = 1;
        JTextField resultHash = new JTextField();
        resultHash.setEditable(false);
        resultHash.setPreferredSize(fieldSize);
        hash.add(resultHash, loc);

//        Thêm vào thanh nav
        nav.add("Thuật toán băm", hash);

//      ------------------------------------------------------------------  Chữ ký điện tử
        JPanel sign = new JPanel();
        sign.setLayout(new GridBagLayout());
        loc.insets = new Insets(5, 5, 5, 5);
        loc.fill = GridBagConstraints.HORIZONTAL;

        loc.gridx = 0;
        loc.gridy = 0;
        sign.add(new JLabel("Chữ ký điện tử"), loc);

        loc.gridx = 0;
        loc.gridy = 1;
        sign.add(new JLabel("Loại dữ liệu:"), loc);
        loc.gridx = 1;
        JComboBox<String> typeSignBox = new JComboBox<>(new String[]{"Text", "File"});
        typeSignBox.setPreferredSize(fieldSize);
        sign.add(typeSignBox, loc);

        loc.gridx = 0;
        loc.gridy = 2;
        sign.add(new JLabel("Nhập dữ liệu:"), loc);
        loc.gridx = 1;
        JTextField inputSign1 = new JTextField();
        inputSign1.setPreferredSize(fieldSize);
        sign.add(inputSign1, loc);

        loc.gridx = 0;
        loc.gridy = 3;
        JButton btnSign = new JButton("Ký:");
        sign.add(btnSign, loc);
        loc.gridx = 1;
        JTextField resultSign = new JTextField();
        resultSign.setEditable(false);
        resultSign.setPreferredSize(fieldSize);
        sign.add(resultSign, loc);

        loc.gridx = 0;
        loc.gridy = 4;
        sign.add(new JLabel("Nhập dữ liệu xác thực:"), loc);
        loc.gridx = 1;
        JTextField inputSign2 = new JTextField();
        inputSign1.setPreferredSize(fieldSize);
        sign.add(inputSign2, loc);

        loc.gridx = 0;
        loc.gridy = 5;
        JButton btnVerify = new JButton("Xác nhận");
        sign.add(btnVerify, loc);
        loc.gridx = 1;
        JTextField resultVerify = new JTextField();
        resultVerify.setEditable(false);
        resultVerify.setPreferredSize(fieldSize);
        sign.add(resultVerify, loc);

//        Thêm vào thanh nav
        nav.add("Chữ ký điện tử", sign);

//        Thêm nav vào frame
        frame.add(nav);
//        Hiển thị frame
        frame.setVisible(true);

//        Sự kiện thuật toán đối xứng
        SymListener symListener = new SymListener(algoSymBox, typeSymBox, inputSym, resultEnSym, resultDeSym,
                btnEnSym, btnDeSym);
        algoSymBox.addActionListener(symListener);
        typeSymBox.addActionListener(symListener);
        btnEnSym.addActionListener(symListener);
        btnDeSym.addActionListener(symListener);

//        Sự kiên thuật toán bất đối xứng
        AsymListener asymListener =new AsymListener(inputAsym, resultEnAsym, resultDeAsym, btnEnAsym, btnDeAsym);
        btnEnAsym.addActionListener(asymListener);
        btnDeAsym.addActionListener(asymListener);

//        Sự kiên thuật toán băm
        HashListener hashListener=new HashListener(typesHashBox, inputHash, resultHash, btnHash);
        btnHash.addActionListener(hashListener);

//        Sự kiên chữ ký điện tử
         SignListener signListener=new SignListener(typeSignBox,inputSign1,inputSign2,resultSign,resultVerify,btnSign,btnVerify);
         btnSign.addActionListener(signListener);
         btnVerify.addActionListener(signListener);
    }
}
