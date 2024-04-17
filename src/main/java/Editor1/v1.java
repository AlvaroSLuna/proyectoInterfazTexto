package Editor1;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;

import static java.awt.Color.black;

public class v1 extends JFrame implements ItemListener {

    private JComboBox combo1, combo2;
    private JButton boton, boton2, boton3, boton4;
    private JTextPane areaTexto;
    private JPanel panel;

    public v1() {

        setTitle("Editor de Texto");
        setIconImage(new ImageIcon("src\\main\\java\\Editor1\\original_icon.png").getImage());
        setSize(800, 600);
        setResizable(false);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.pink);
        panel.setBounds(0, 0, 35000, 200000);
        add(panel);

        setLayout(null);

        combo1 = new JComboBox();
        combo1.setBounds(380, 20, 100, 30);
        combo1.setBackground(Color.lightGray);
        combo1.setBorder(BorderFactory.createLineBorder(black));
        panel.add(combo1);
        for (String fuentes : Arrays.asList("Arial", "Verdana", "Times New Roman", "Tahoma", "Comic Sans MS", "Courier New", "Lucida Console", "Monospaced", "Dialog")) {
            combo1.addItem(fuentes);
        }
        combo1.addItemListener(this);

        combo2 = new JComboBox();
        combo2.setBounds(500, 20, 100, 30);
        combo2.setBackground(Color.lightGray);
        combo2.setBorder(BorderFactory.createLineBorder(black));
        panel.add(combo2);
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 8; i <= 40; i++) {
            list.add(i);
        }
        for (int i = 0; i < list.size(); i++) {
            combo2.addItem(list.get(i));
        }
        combo2.addItemListener(this);

        areaTexto = new JTextPane();
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        scrollPane.setBounds(20, 60, 700, 400);
        areaTexto.setBackground(java.awt.Color.lightGray);
        scrollPane.setBorder(BorderFactory.createLineBorder(black));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setAutoscrolls(true);
        panel.add(scrollPane);

        JButton boton = new JButton("Negrita");
        boton.addActionListener(new BoldButtonListener());
        boton.setBounds(20, 20, 100, 30);
        boton.setBackground(Color.lightGray);
        boton.setBorder(BorderFactory.createLineBorder(black));
        panel.add(boton);

        JButton boton2 = new JButton("Cursiva");
        boton2.addActionListener(new CursivaButtonListener());
        boton2.setBounds(140, 20, 100, 30);
        boton2.setBackground(Color.lightGray);
        boton2.setBorder(BorderFactory.createLineBorder(black));
        panel.add(boton2);

        JButton boton3 = new JButton("Subrayado");
        boton3.addActionListener(new SubrayadoButtonListener());
        boton3.setBounds(260, 20, 100, 30);
        boton3.setBackground(Color.lightGray);
        boton3.setBorder(BorderFactory.createLineBorder(black));
        panel.add(boton3);

        JButton boton4 = new JButton("Color");
        boton4.addActionListener(new ColorButtonListener());
        boton4.setBounds(620, 20, 100, 30);
        boton4.setBackground(Color.lightGray);
        boton4.setBorder(BorderFactory.createLineBorder(black));
        panel.add(boton4);

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        JButton saveButton = new JButton();
        saveButton.setText("Guardar");
        saveButton.setBounds(620, 470, 100, 30);
        saveButton.setBackground(Color.lightGray);
        saveButton.setBorder(BorderFactory.createLineBorder(black));
        panel.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    JFileChooser fileChooser = new JFileChooser();

                    int result = fileChooser.showSaveDialog(null);
                    File selectedFile = fileChooser.getSelectedFile();


                    if (result == JFileChooser.APPROVE_OPTION) {

                        File file = selectedFile;


                        Files.write(file.toPath(), areaTexto.getText().getBytes());

                        JOptionPane.showMessageDialog(null, "El archivo ha sido guardado exitosamente.", "Guardar", JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al guardar el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    private class BoldButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            StyledDocument doc = areaTexto.getStyledDocument();
            int start = areaTexto.getSelectionStart();
            int end = areaTexto.getSelectionEnd();
            Style style = doc.addStyle("negrita", null);
            StyleConstants.setBold(style, !StyleConstants.isBold(doc.getCharacterElement(start).getAttributes()));
            doc.setCharacterAttributes(start, end - start, style, false);
        }
    }

    private class CursivaButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent f) {
            StyledDocument doc = areaTexto.getStyledDocument();
            int start = areaTexto.getSelectionStart();
            int end = areaTexto.getSelectionEnd();
            Style style = doc.addStyle("cursiva", null);
            StyleConstants.setItalic(style, !StyleConstants.isItalic(doc.getCharacterElement(start).getAttributes()));
            doc.setCharacterAttributes(start, end - start, style, false);
        }
    }

    private class SubrayadoButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent g) {
            StyledDocument doc = areaTexto.getStyledDocument();
            int start = areaTexto.getSelectionStart();
            int end = areaTexto.getSelectionEnd();
            Style style = doc.addStyle("subrayado", null);
            StyleConstants.setUnderline(style, !StyleConstants.isUnderline(doc.getCharacterElement(start).getAttributes()));
            doc.setCharacterAttributes(start, end - start, style, false);
        }
    }

    private class ColorButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Color selectedColor = JColorChooser.showDialog(null, "Seleccione un color", Color.BLACK);
            if (selectedColor != null) {

                int start = areaTexto.getSelectionStart();
                int end = areaTexto.getSelectionEnd();

                StyledDocument doc = areaTexto.getStyledDocument();

                Style style = doc.addStyle("Color", null);

                StyleConstants.setForeground(style, selectedColor);

                doc.setCharacterAttributes(start, end - start, style, false);
            }
        }
    }


    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == combo1) {
            String fontName = (String) combo1.getSelectedItem();
            int start = areaTexto.getSelectionStart();
            int end = areaTexto.getSelectionEnd();
            StyledDocument doc = areaTexto.getStyledDocument();
            Style style = doc.addStyle("fontStyle", null);
            StyleConstants.setFontFamily(style, fontName);
            doc.setCharacterAttributes(start, end - start, style, false);
        } else if (e.getSource() == combo2) {
            int fontSize = (Integer) combo2.getSelectedItem();
            int start = areaTexto.getSelectionStart();
            int end = areaTexto.getSelectionEnd();
            StyledDocument doc = areaTexto.getStyledDocument();
            Style style = doc.addStyle("fontSize", null);
            StyleConstants.setFontSize(style, fontSize);
            doc.setCharacterAttributes(start, end - start, style, false);
        }
    }


    public static void main(String[] args) {
        v1 formulario1 = new v1();
        formulario1.setBounds(0, 0, 755, 545);
        formulario1.setVisible(true);
    }
}
