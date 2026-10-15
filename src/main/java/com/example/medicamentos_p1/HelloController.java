package com.example.medicamentos_p1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public class HelloController {
    @FXML private TextField txtNome;
    @FXML private TextField txtDescricao;
    @FXML private TextField txtPrincipio;
    @FXML private TextField txtEstoque;
    @FXML private TextField txtPreco;
    @FXML private DatePicker dpValidade;
    @FXML private CheckBox chkControlado;
    @FXML private TextField txtBuscaCodigo;

    @FXML private TableView<Medicamento> tabelaMedicamentos;

    @FXML private TableColumn<Medicamento, String> colNome;
    @FXML private TableColumn<Medicamento, String> colPrincipio;
    @FXML private TableColumn<Medicamento, Integer> colEstoque;
    @FXML private TableColumn<Medicamento, BigDecimal> colPreco;
    @FXML private TableColumn<Medicamento, LocalDate> colValidade;


    private ObservableList<Medicamento> listaObservable;

    @FXML
    public void initialize() {
        // CONFIGURAÇÃO DA TABELA (Crucial):
        // O texto entre aspas DEVE ser exatamente o nome dos atributos da sua classe Medicamento.
        // O JavaFX vai usar isso para chamar os seus getters (ex: getNome(), getPrincipioAtivo()).
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colPrincipio.setCellValueFactory(new PropertyValueFactory<>("principioAtivo"));
        colEstoque.setCellValueFactory(new PropertyValueFactory<>("quantidadeEstoque"));
        colPreco.setCellValueFactory(new PropertyValueFactory<>("preco")); // Exigido no projeto[cite: 2]
        colValidade.setCellValueFactory(new PropertyValueFactory<>("dataValidade"));

        // Carrega os dados que já existem no arquivo .csv
        List<Medicamento> dadosSalvos = ArquivoUtil.carregarMedicamentos();

        // Coloca os dados na lista do JavaFX e vincula à tabela
        listaObservable = FXCollections.observableArrayList(dadosSalvos);
        tabelaMedicamentos.setItems(listaObservable);
    }

    @FXML
    void onSalvarClick(ActionEvent event) {
        // Teste simples para ver se o botão funciona
        System.out.println("Iniciando cadastro do medicamento: " + txtNome.getText());

        // Aqui virá a lógica de pegar os textos, converter (ex: Integer.parseInt),
        // validar os dados[cite: 2], criar o new Medicamento() e adicionar na listaObservable.
    }

    @FXML
    void onExcluirClick(ActionEvent event) {
        // Lógica de exclusão[cite: 1]
    }

    @FXML
    void onConsultarClick(ActionEvent event) {
        // Lógica de consultar por código[cite: 1]
        System.out.println("Buscando código: " + txtBuscaCodigo.getText());
    }

    @FXML
    void onRelatorioEstoqueClick(ActionEvent event) {
        // Lógica de Stream API: filter para estoque < 5[cite: 3]
    }

    @FXML
    void onRelatorioVencimentoClick(ActionEvent event) {
        // Lógica de Stream API: filter para vencimento nos próximos 30 dias[cite: 3]
    }


}
