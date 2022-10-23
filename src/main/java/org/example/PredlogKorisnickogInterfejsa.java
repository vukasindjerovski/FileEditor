package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PredlogKorisnickogInterfejsa extends JDialog {
    private JPanel contentPane;
    private JButton buttonClose;
    private JButton buttonSave;
    private JButton buttonOpenTop;
    private JButton buttonOpenBottom;
    private JButton buttonGetSelectionTop;
    private JButton buttonGetSelectionBottom;
    private JTextArea textAreaTop; //

    private JTextArea textAreaBottom;
    private JTextArea textAreaNew; // dodat lineWrap, wordWrap


    String directory; // The default directory to display in the FileDialog
    String selectionTop, selectionBottom; // String za gornji i za donji tekst

    public PredlogKorisnickogInterfejsa() {
        setContentPane(contentPane);
        setModal(true);
        //    getRootPane().setDefaultButton(buttonOpen);

        buttonClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onButtonClose();
            }
        });

        buttonOpenTop.addActionListener(new ActionListener() { //Button Open Top
            public void actionPerformed(ActionEvent e) {
                onButtonOpenTop();
            }
        });
        buttonOpenBottom.addActionListener(new ActionListener() { //Button Open Bottom
            public void actionPerformed(ActionEvent e) {
                onButtonOpenBottom();
            }
        });
        buttonClose.addActionListener(new ActionListener() { //Close Button
            public void actionPerformed(ActionEvent e) {

                onButtonClose();
            }
        });
        buttonSave.addActionListener(new ActionListener() { // Save Button
            public void actionPerformed(ActionEvent e) {

                onButtonSave();
            }
        });
        buttonGetSelectionTop.addActionListener(new ActionListener() { //Selection Top
            public void actionPerformed(ActionEvent e) {

                onButtonGetSelectionTop();

            }
        });
        buttonGetSelectionBottom.addActionListener(new ActionListener() { //Selection Bottom
            public void actionPerformed(ActionEvent e) {

                onButtonGetSelectionBottom();
            }
        });


        // call onCancel() when cross is clicked
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onButtonClose();
            }
        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    // Save file from textNewArea - polje sa desne strane
    public void saveFile(String directory, String filename) {
        if ((filename == null) || (filename.length() == 0))
            return;
        File file;
        FileWriter out = null;
        try {
            file = new File(directory, filename); // Create a file object
            out = new FileWriter(file); // And a char stream to write it
            textAreaNew.getLineCount(); // Get text from the text area
            String s = textAreaNew.getText();
            out.write(s);
        }
        // Display messages if something goes wrong
        catch (IOException e) {
            textAreaNew.setText(e.getClass().getName() + ": " + e.getMessage());
            this.setTitle("FileViewer: " + filename + ": I/O Exception");
        }
        // Always be sure to close the input stream!
        finally {
            try {
                if (out != null)
                    out.close();
            }
            catch (IOException e) {
            }
        }
    }

    //Load file "Top Section"
    public void loadAndDisplayFileTop(String directory, String filename) {
        if ((filename == null) || (filename.length() == 0))
            return;
        File file;
        FileReader in = null;
        // Read and display the file contents. Since we're reading text, we
        // use a FileReader instead of a FileInputStream.
        try {
            file = new File(directory, filename); // Create a file object
            in = new FileReader(file); // And a char stream to read it
            char[] buffer = new char[4096]; // Read 4K characters at a time
            int len; // How many chars read each time
            textAreaTop.setText(""); // Clear the text area
            while ((len = in.read(buffer)) != -1) { // Read a batch of chars
                String s = new String(buffer, 0, len); // Convert to a string
                textAreaTop.append(s); // And display them
            }
            this.setTitle("FileViewer: " + filename); // Set the window title
            textAreaTop.setCaretPosition(0); // Go to start of file
        }
        // Display messages if something goes wrong
        catch (IOException e) {
            textAreaTop.setText(e.getClass().getName() + ": " + e.getMessage());
            this.setTitle("FileViewer: " + filename + ": I/O Exception");
        }
        // Always be sure to close the input stream!
        finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
            }
        }
    }

    // Load File "Bottom section"
    public void loadAndDisplayFileBottom(String directory, String filename) {
        if ((filename == null) || (filename.length() == 0))
            return;
        File file;
        FileReader in = null;
        // Read and display the file contents. Since we're reading text, we
        // use a FileReader instead of a FileInputStream.
        try {
            file = new File(directory, filename); // Create a file object
            in = new FileReader(file); // And a char stream to read it
            char[] buffer = new char[4096]; // Read 4K characters at a time
            int len; // How many chars read each time
            textAreaBottom.setText(""); // Clear the text area
            while ((len = in.read(buffer)) != -1) { // Read a batch of chars
                String s = new String(buffer, 0, len); // Convert to a string
                textAreaBottom.append(s); // And display them
            }
            this.setTitle("FileViewer: " + filename); // Set the window title
            textAreaBottom.setCaretPosition(0); // Go to start of file
        }
        // Display messages if something goes wrong
        catch (IOException e) {
            textAreaBottom.setText(e.getClass().getName() + ": " + e.getMessage());
            this.setTitle("FileViewer: " + filename + ": I/O Exception");
        }
        // Always be sure to close the input stream!
        finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
            }
        }
    }

    //Open "TopButton"
    private void onButtonOpenTop() {
        // Create a file dialog box to prompt for a new file to display
        FileDialog f = new FileDialog(this, "Otvori fajl", FileDialog.LOAD);
        f.setDirectory(directory); // Set the default directory
        // Display the dialog and wait for the user's response
        f.setVisible(true);
        directory = f.getDirectory(); // Remember new default directory
        loadAndDisplayFileTop(directory, f.getFile()); // Load and display selection
        f.dispose(); // Get rid of the dialog box
    }
    // Open "BottomButton"
    private void onButtonOpenBottom() {
        // Create a file dialog box to prompt for a new file to display
        FileDialog f = new FileDialog(this, "Otvori fajl", FileDialog.LOAD);
        f.setDirectory(directory); // Set the default directory
        // Display the dialog and wait for the user's response
        f.setVisible(true);
        directory = f.getDirectory(); // Remember new default directory
        loadAndDisplayFileBottom(directory, f.getFile()); // Load and display selection
        f.dispose(); // Get rid of the dialog box
    }
    // Save Button
    private void onButtonSave() {
        // Create a file dialog box to prompt for a new file to display
        FileDialog f = new FileDialog(this, "Otvori fajl", FileDialog.SAVE);
        f.setDirectory(directory); // Set the default directory
        // Display the dialog and wait for the user's response
        f.setVisible(true);
        directory = f.getDirectory(); // Remember new default directory
        saveFile(directory, f.getFile()); // Load and display selection
        f.dispose(); // Get rid of the dialog box
    }
    // Get selection Top
    private void onButtonGetSelectionTop()
    {

        selectionTop = textAreaTop.getSelectedText(); // getSelectedText - Vraca selektovani tekst
        textAreaNew.append(selectionTop); //Dodaje selektovani tekst na kraj dokumenta (u ovom slucaju gornji)
    }
    // Get Selection Bottom
    private void onButtonGetSelectionBottom() {
        selectionBottom = textAreaBottom.getSelectedText();
        textAreaNew.append(selectionBottom);

    }
    //Close Button
    private void onButtonClose() {
        // add your code here if necessary
        dispose();
    }
    public static void main(String[] args) {
        PredlogKorisnickogInterfejsa dialog = new PredlogKorisnickogInterfejsa();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
