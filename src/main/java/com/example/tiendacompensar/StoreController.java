package com.example.tiendacompensar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class StoreController {



    Stage stage;
    Parent root;
    Scene scene;

    private ObservableList<Product> products = FXCollections.observableArrayList();

    private ObservableList<Employee> employees = FXCollections.observableArrayList();
    @FXML
    TableView tableProduct;

    @FXML
    TableView tableEmployee;


    @FXML
    PieChart pieChartProducts;

    @FXML
    PieChart pieChartEmployees;

    @FXML
    Label labelTotalDiurna;
    @FXML
    Label labelTotalNocturna;

    @FXML
    private TableColumn<Product, String> columnName;
    @FXML
    private TableColumn<Product, String> columnCategory;
    @FXML
    private TableColumn<Product, Integer> columnUnits;
    @FXML
    private TableColumn<Product, Double> columnPrice;
    @FXML
    private TableColumn<Product, Double> columnIVA;
    @FXML
    private TableColumn<Product, Double> columnTotalWithIVA;



    @FXML
    private TableColumn<Employee, String> columnNameEmployee;
    @FXML
    private TableColumn<Employee, Integer> columnIdEmployee;
    @FXML
    private TableColumn<Employee, Integer> columnAgeEmployee;
    @FXML
    private TableColumn<Employee, String> columnShiftEmployee;
    @FXML
    private TableColumn<Employee, String> columnTenureEmployee;
    @FXML
    private TableColumn<Employee, String> columnBenefitEmployee;



@FXML
TextField textFieldNombre;
@FXML
ChoiceBox<String> choiceBoxCategoria;
@FXML
TextField textFieldUnidades;
@FXML
TextField textFieldPrecio;



    @FXML
    Label labelAseo;
    @FXML
    Label labelPapeleria;
    @FXML
    Label labelViveres;
    @FXML
    Label labelOtros;
    @FXML
    Label labelMascotas;


    @FXML
    TextField txtNombreEmployee;
    @FXML
    TextField txtEmployeeId;
    @FXML
    DatePicker datePickerTenure;
    @FXML
    ComboBox choiceBoxAge;
    @FXML
    ChoiceBox choiceBoxShift;

    private Stack<Scene> previousScenes = new Stack<>();





    @FXML
    private void SwitchScene1(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("home.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();


    }

    @FXML
    private void SwitchScene2(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("aboutUs.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }


    @FXML
    private void SwitchScene3(ActionEvent event) throws IOException {

        Scene currentScene = ((Node) event.getSource()).getScene();
        previousScenes.push(currentScene);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("products.fxml"));
        fxmlLoader.setController(this);
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


        tableProduct.setItems(products);



        choiceBoxCategoria.getItems().addAll("Aseo", "Papeleria", "Viveres", "Mascotas", "Otros");

        columnName.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnCategory.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        columnUnits.setCellValueFactory(new PropertyValueFactory<>("unidades"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("precio"));
        columnIVA.setCellValueFactory(new PropertyValueFactory<>("iva"));
        columnTotalWithIVA.setCellValueFactory(new PropertyValueFactory<>("totalConIva"));


    }

    @FXML
    private void SwitchScene4(ActionEvent event) throws IOException {


        Scene currentScene = ((Node) event.getSource()).getScene();
        previousScenes.push(currentScene);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("employees.fxml"));
        fxmlLoader.setController(this);
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


        ObservableList<Integer> items = FXCollections.observableArrayList();
        for (int i = 1; i <= 120; i++) {
            items.add(i);
        }

        choiceBoxAge.setItems(items);

        tableEmployee.setItems(employees);


        choiceBoxShift.getItems().addAll("Diurna", "Nocturna");

        columnNameEmployee.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnIdEmployee.setCellValueFactory(new PropertyValueFactory<>("identificacion"));
        columnAgeEmployee.setCellValueFactory(new PropertyValueFactory<>("edad"));
        columnShiftEmployee.setCellValueFactory(new PropertyValueFactory<>("jornadaDeTrabajo"));
        columnTenureEmployee.setCellValueFactory(new PropertyValueFactory<>("antiguedad"));
        columnBenefitEmployee.setCellValueFactory(new PropertyValueFactory<>("beneficio"));


    }

    @FXML
    private void SwitchScene5(ActionEvent event) throws IOException {

        Scene currentScene = ((Node) event.getSource()).getScene();
        previousScenes.push(currentScene);


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("graphics.fxml"));
        fxmlLoader.setController(this);
        root = fxmlLoader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        loadEmployeePieChartData();

        loadProductPieChartData();


    }

    @FXML
    private void goBack(ActionEvent event) {
        if (!previousScenes.isEmpty()) {
            Scene previousScene = previousScenes.pop();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(previousScene);
            stage.show();
        }
    }


    public void loadEmployeePieChartData() {



        Map<String, Integer> shiftCounts = countEmployeesByShift();

        List<PieChart.Data> pieChartDataEmployee = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : shiftCounts.entrySet()) {
            String shift = entry.getKey();
            int count = entry.getValue();

            if (count > 0) {
                pieChartDataEmployee.add(new PieChart.Data(shift, count));
            }
        }


        labelTotalDiurna.setText(String.valueOf(shiftCounts.get("Diurna")));
        labelTotalNocturna.setText(String.valueOf(shiftCounts.get("Nocturna")));


        // Establecer los datos del gráfico de pastel


        pieChartEmployees.setData(FXCollections.observableArrayList(pieChartDataEmployee));

    }


    public void loadProductPieChartData() {


        Map<String, Integer> categoryCounts = countProductsByCategory();

        List<PieChart.Data> pieChartData = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : categoryCounts.entrySet()) {
            String category = entry.getKey();
            int count = entry.getValue();

            if (count > 0) {
                pieChartData.add(new PieChart.Data(category, count));
            }
        }


        labelAseo.setText(String.valueOf(categoryCounts.get("Aseo")));
        labelPapeleria.setText(String.valueOf(categoryCounts.get("Papeleria")));
        labelViveres.setText(String.valueOf(categoryCounts.get("Viveres")));
        labelMascotas.setText(String.valueOf(categoryCounts.get("Mascotas")));
        labelOtros.setText(String.valueOf(categoryCounts.get("Otros")));


        pieChartProducts.setData(FXCollections.observableArrayList(pieChartData));


    }


    private Map<String, Integer> countProductsByCategory() {

        Map<String, Integer> categoryCounts = new HashMap<>();


        categoryCounts.put("Aseo", 0);
        categoryCounts.put("Papeleria", 0);
        categoryCounts.put("Viveres", 0);
        categoryCounts.put("Otros", 0);
        categoryCounts.put("Mascotas", 0);

        for (Product product : products) {
            String category = product.getCategoria();
            int currentCount = categoryCounts.getOrDefault(category, 0);
            categoryCounts.put(category, currentCount + 1);
        }

        return categoryCounts;
    }




    private Map<String, Integer> countEmployeesByShift() {

        Map<String, Integer> shiftCounts = new HashMap<>();

        // Agrega las categorías con un conteo inicial de 0
        shiftCounts.put("Diurna", 0);
        shiftCounts.put("Nocturna", 0);


        for (Employee employee : employees) {
            String shift = employee.getJornadaDeTrabajo();
            int currentCount = shiftCounts.getOrDefault(shift, 0); // Usa getOrDefault en lugar de get
            shiftCounts.put(shift, currentCount + 1);
        }

        return shiftCounts;
    }





    @FXML
    public void onBtnProductoNuevoClicked() {


        if (!validateFields()) {

             return;

        }else{
            String nombre = textFieldNombre.getText();
            String categoria = (String) choiceBoxCategoria.getValue();


            String cleanStringUnidades = textFieldUnidades.getText();
            cleanStringUnidades = cleanStringUnidades.replace(".", "");


            int unidades = Integer.parseInt(cleanStringUnidades);

            String cleanStringPrecio = textFieldPrecio.getText();
            cleanStringPrecio = cleanStringPrecio.replace(".", "");

            double precio = Double.parseDouble(cleanStringPrecio);

            double iva = 0;

            if (categoria.trim() == "Aseo"){
                iva = 19;
            }else if(categoria.trim() == "Papeleria"){
                iva = 9;
            }else if(categoria.trim() == "Viveres"){
                iva = 15;
            }else if(categoria.trim() == "Mascotas"){
                iva = 16;
            }else if(categoria.trim() == "Otros"){
                iva = 10;
            }

            double totalConIva = precio + (precio * (iva / 100));


            Product newProduct = new Product(nombre, categoria, unidades, precio, iva, totalConIva);
            products.add(newProduct);
        }


    }

    String discountString = "";

    @FXML
    public void onBtnEmployeeNuevoClicked() {


        if (!validateEmployeeFields()) {
            return;
        }else{
            String nombre = txtNombreEmployee.getText();


            // Limpimos los puntos producidos en el textfield
            String cleanStringId = txtEmployeeId.getText();
            cleanStringId = cleanStringId.replace(".", "");

            String identificacion = cleanStringId;


            String tenure = String.valueOf(datePickerTenure.getValue());
            LocalDate fechaActual = LocalDate.now();
            LocalDate fechaTenure = LocalDate.parse(tenure);

            long diffYears = ChronoUnit.YEARS.between(fechaTenure, fechaActual);

            Integer edad = (Integer) choiceBoxAge.getValue();

            String shift = (String) choiceBoxShift.getValue();


            double descuentoTienda = 0;
            double descuentoRecreacional = 0;

            System.out.println(diffYears);

            if (diffYears < 1) {
                descuentoTienda = 15;
                descuentoRecreacional = 20;
            } else if (diffYears >= 1 && diffYears < 5) {
                descuentoTienda = 30;
                descuentoRecreacional = 30;
            } else if (diffYears >= 5) {
                descuentoTienda = 50;
                descuentoRecreacional = 60;
            }

            discountString = "Tienda: " + descuentoTienda + "% dto & Recreacion: " + descuentoRecreacional + "% dto";

            Employee newEmployee = new Employee(nombre, identificacion, tenure, edad, shift, discountString);

            employees.add(newEmployee);

        }


    }

    @FXML
    public void onBtnEditarClicked() {
        Product selectedProduct = (Product) tableProduct.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            textFieldNombre.setText(selectedProduct.getNombre());
            choiceBoxCategoria.setValue(selectedProduct.getCategoria());
            textFieldUnidades.setText(String.valueOf(selectedProduct.getUnidades()));
            textFieldPrecio.setText(String.valueOf(selectedProduct.getPrecio()));
        }
    }

    @FXML
    private void onBtnEditarEmpleadoClicked() {
        Employee selectedEmployee = (Employee) tableEmployee.getSelectionModel().getSelectedItem();

        if (selectedEmployee != null) {

            txtNombreEmployee.setText(selectedEmployee.getNombre());
            txtEmployeeId.setText(selectedEmployee.getIdentificacion());
            datePickerTenure.setValue(LocalDate.parse(selectedEmployee.getAntiguedad()));
            choiceBoxAge.setValue(selectedEmployee.getEdad());
            choiceBoxShift.setValue(selectedEmployee.getJornadaDeTrabajo());

        }
    }

    @FXML
    private void onBtnEliminarEmpleadoClicked() {
        Employee selectedEmployee = (Employee) tableEmployee.getSelectionModel().getSelectedItem();

        if (selectedEmployee != null) {
            employees.remove(selectedEmployee);
        }
    }

    @FXML
    public void onBtnEliminarClicked() {
        Product selectedProduct = (Product) tableProduct.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            products.remove(selectedProduct);
        }
    }


    @FXML
    public void onBtnActualizarClicked() {


        Product selectedProduct = (Product) tableProduct.getSelectionModel().getSelectedItem();

        if (selectedProduct != null) {
            // Actualiza el producto seleccionado con los nuevos datos de los TextField y ChoiceBox
            selectedProduct.setNombre(textFieldNombre.getText());
            selectedProduct.setCategoria((String) choiceBoxCategoria.getValue());
            selectedProduct.setUnidades(Integer.parseInt(textFieldUnidades.getText()));
            selectedProduct.setPrecio(Double.parseDouble(textFieldPrecio.getText()));


            double updatedPrecio = selectedProduct.getPrecio();
            double updatedIva = selectedProduct.getIva();
            selectedProduct.setTotalConIva(updatedPrecio * (1 + updatedIva));

            // Refresca la tabla para mostrar los cambios
            tableProduct.refresh();
        }
    }

    @FXML
    private void onBtnActualizarEmpleadoClicked() {
        Employee selectedEmployee = (Employee) tableEmployee.getSelectionModel().getSelectedItem();

        if (selectedEmployee != null) {
            selectedEmployee.setNombre(txtNombreEmployee.getText());
            selectedEmployee.setIdentificacion(txtEmployeeId.getText());
            selectedEmployee.setAntiguedad(datePickerTenure.getValue().toString());
            selectedEmployee.setEdad((Integer) choiceBoxAge.getValue());
            selectedEmployee.setJornadaDeTrabajo((String) choiceBoxShift.getValue());
            selectedEmployee.setBeneficio(discountString);

            // Actualiza la tabla para mostrar los cambios
            tableEmployee.refresh();
        }
    }




    @FXML
    private void onKeyTyped(KeyEvent event) {
        TextField sourceTextField = (TextField) event.getSource();
        restrictCharacters(sourceTextField);
    }


    private void restrictCharacters(TextField textField) {
        String text = textField.getText().replaceAll("[^\\d]", "");
        StringBuilder formattedText = new StringBuilder();
        int textLength = text.length();

        for (int i = textLength - 1; i >= 0; i--) {
            formattedText.insert(0, text.charAt(i));
            if ((textLength - i) % 3 == 0 && i != 0) {
                formattedText.insert(0, ".");
            }
        }

        textField.setText(formattedText.toString());
        textField.positionCaret(formattedText.length());
    }

    private boolean validateFields() {
        boolean isValid = true;

        if (textFieldNombre.getText() == null || textFieldNombre.getText().trim().isEmpty()) {
            isValid = false;
        }

        if (choiceBoxCategoria.getSelectionModel().isEmpty()) {
            isValid = false;
        }

        if (textFieldUnidades.getText() == null || textFieldUnidades.getText().trim().isEmpty()) {
            isValid = false;
        }

        if (textFieldPrecio.getText() == null || textFieldPrecio.getText().trim().isEmpty()) {
            isValid = false;
        }

        return isValid;
    }




    private boolean validateEmployeeFields() {
        boolean isValid = true;

        if (txtNombreEmployee.getText() == null || txtNombreEmployee.getText().trim().isEmpty()) {
            isValid = false;

        }

        if (txtEmployeeId.getText() == null || txtEmployeeId.getText().trim().isEmpty()) {
            isValid = false;
        }

        if (datePickerTenure.getValue() == null) {
            isValid = false;
        }

        if (choiceBoxAge.getSelectionModel().isEmpty()) {
            isValid = false;
        }

        if (choiceBoxShift.getSelectionModel().isEmpty()) {
            isValid = false;
        }

        return isValid;
    }


}