package examen2;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.io.FileWriter;
import java.io.IOException;

public class Examen extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNombre;
    private JTextField txtCedula;
    private JTextField txtFecha;
    private JComboBox<String> comboCarrera;
    private JRadioButton rdbtnMasculino;
    private JRadioButton rdbtnFemenino;
    private JCheckBox chckbxDeporte;
    private JCheckBox chckbxMusica;
    private JCheckBox chckbxLectura;
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<String[]> registros; // Almacena los registros

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Examen frame = new Examen();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Examen() {
        setTitle("Registro de Estudiantes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        registros = new ArrayList<>();

        JLabel lblNombre = new JLabel("Nombre Completo:");
        lblNombre.setBounds(30, 50, 120, 20);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(160, 50, 200, 20);
        contentPane.add(txtNombre);

        JLabel lblCedula = new JLabel("Cédula Identidad:");
        lblCedula.setBounds(30, 80, 120, 20);
        contentPane.add(lblCedula);

        txtCedula = new JTextField();
        txtCedula.setBounds(160, 80, 200, 20);
        contentPane.add(txtCedula);

        JLabel lblFecha = new JLabel("Fecha de Nacimiento:");
        lblFecha.setBounds(30, 110, 130, 20);
        contentPane.add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setBounds(160, 110, 200, 20);
        contentPane.add(txtFecha);

        JLabel lblCarrera = new JLabel("Carrera:");
        lblCarrera.setBounds(30, 140, 120, 20);
        contentPane.add(lblCarrera);

        comboCarrera = new JComboBox<>();
        comboCarrera.setBounds(160, 140, 200, 20);
        comboCarrera.addItem("Seleccione...");
        comboCarrera.addItem("Ingeniería");
        comboCarrera.addItem("Derecho");
        comboCarrera.addItem("Arquitectura");
        comboCarrera.addItem("Medicina");
        comboCarrera.addItem("Administración");
        contentPane.add(comboCarrera);

        JLabel lblSexo = new JLabel("Sexo:");
        lblSexo.setBounds(30, 170, 120, 20);
        contentPane.add(lblSexo);

        rdbtnMasculino = new JRadioButton("Masculino");
        rdbtnMasculino.setBounds(160, 170, 100, 20);
        contentPane.add(rdbtnMasculino);

        rdbtnFemenino = new JRadioButton("Femenino");
        rdbtnFemenino.setBounds(260, 170, 100, 20);
        contentPane.add(rdbtnFemenino);

        ButtonGroup bgSexo = new ButtonGroup();
        bgSexo.add(rdbtnMasculino);
        bgSexo.add(rdbtnFemenino);

        JLabel lblHobbies = new JLabel("Hobbies:");
        lblHobbies.setBounds(30, 200, 120, 20);
        contentPane.add(lblHobbies);

        chckbxDeporte = new JCheckBox("Deporte");
        chckbxDeporte.setBounds(160, 200, 100, 20);
        contentPane.add(chckbxDeporte);

        chckbxMusica = new JCheckBox("Música");
        chckbxMusica.setBounds(260, 200, 100, 20);
        contentPane.add(chckbxMusica);

        chckbxLectura = new JCheckBox("Lectura");
        chckbxLectura.setBounds(360, 200, 100, 20);
        contentPane.add(chckbxLectura);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(500, 50, 150, 30);
        contentPane.add(btnRegistrar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(500, 90, 150, 30);
        contentPane.add(btnLimpiar);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(500, 130, 150, 30);
        contentPane.add(btnSalir);

        JButton btnExportar = new JButton("Exportar");
        btnExportar.setBounds(500, 170, 150, 30);
        contentPane.add(btnExportar);

        // Estilos de botones
        btnRegistrar.setBackground(new Color(0, 153, 76)); // Verde
        btnRegistrar.setForeground(Color.BLACK);
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 14));

        btnLimpiar.setBackground(new Color(0, 102, 204)); // Azul
        btnLimpiar.setForeground(Color.BLACK);
        btnLimpiar.setFont(new Font("Arial", Font.BOLD, 14));

        btnSalir.setBackground(new Color(204, 0, 0)); // Rojo
        btnSalir.setForeground(Color.BLACK);
        btnSalir.setFont(new Font("Arial", Font.BOLD, 14));

        btnExportar.setBackground(new Color(255, 153, 0)); // Naranja
        btnExportar.setForeground(Color.BLACK);
        btnExportar.setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 250, 620, 200);
        contentPane.add(scrollPane);

        table = new JTable();
        model = new DefaultTableModel(
            new Object[][] {},
            new String[] { "Nombre", "Cédula", "Nacimiento", "Carrera", "Sexo", "Hobbies" }
        );
        table.setModel(model);
        scrollPane.setViewportView(table);

        // Acción del botón Registrar
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarEstudiante();
            }
        });

        // Acción del botón Limpiar
        btnLimpiar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
            }
        });

        // Acción del botón Salir
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Acción del botón Exportar
        btnExportar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exportarDatos();
            }
        });
    }

    // Método para registrar estudiante
    private void registrarEstudiante() {
        String nombre = txtNombre.getText().trim();
        String cedula = txtCedula.getText().trim();
        String fecha = txtFecha.getText().trim();
        String carrera = comboCarrera.getSelectedItem().toString();
        String sexo = rdbtnMasculino.isSelected() ? "Masculino" : rdbtnFemenino.isSelected() ? "Femenino" : "";
        String hobbies = "";

        if (chckbxDeporte.isSelected()) hobbies += "Deporte ";
        if (chckbxMusica.isSelected()) hobbies += "Música ";
        if (chckbxLectura.isSelected()) hobbies += "Lectura ";

        // Validaciones
        if (nombre.isEmpty() || cedula.isEmpty() || fecha.isEmpty() || carrera.equals("Seleccione...") || sexo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!cedula.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "La cédula debe ser numérica.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (hobbies.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar al menos un hobby.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Guardar registro en ArrayList
        String[] registro = { nombre, cedula, fecha, carrera, sexo, hobbies.trim() };
        registros.add(registro);

        // Agregar registro a la tabla
        model.addRow(registro);

        // Limpiar formulario
        limpiarFormulario();
    }

    // Método para limpiar el formulario
    private void limpiarFormulario() {
        txtNombre.setText("");
        txtCedula.setText("");
        txtFecha.setText("");
        comboCarrera.setSelectedIndex(0);
        rdbtnMasculino.setSelected(false);
        rdbtnFemenino.setSelected(false);
        chckbxDeporte.setSelected(false);
        chckbxMusica.setSelected(false);
        chckbxLectura.setSelected(false);
    }

    // Método para exportar los datos a CSV
    private void exportarDatos() {
        try {
            FileWriter writer = new FileWriter("estudiantes.csv");

            // Escribir encabezados
            writer.write("Nombre,Cédula,Nacimiento,Carrera,Sexo,Hobbies\n");

            // Escribir registros
            for (int i = 0; i < table.getRowCount(); i++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                    Object value = table.getValueAt(i, j);
                    writer.write(value != null ? value.toString() : "");
                    if (j < table.getColumnCount() - 1) {
                        writer.write(",");
                    }
                }
                writer.write("\n");
            }

            writer.close();
            JOptionPane.showMessageDialog(this, "Datos exportados exitosamente en 'estudiantes.csv'.", "Exportación", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al exportar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}


