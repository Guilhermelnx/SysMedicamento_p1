package controller;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.ArquivoUtil;
import models.Fornecedor;
import models.Medicamento;

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
        try {
            // 1. Ler os dados digitados na interface
            String nome = txtNome.getText();
            String descricao = txtDescricao.getText();
            String principio = txtPrincipio.getText();
            LocalDate validade = dpValidade.getValue();
            boolean controlado = chkControlado.isSelected();

            // Converter textos para números (isto pode gerar erros se o utilizador digitar letras, por isso usamos try-catch)
            int estoque = Integer.parseInt(txtEstoque.getText());
            BigDecimal preco = new BigDecimal(txtPreco.getText()); // Descomente quando adicionar o preço à classe

            // Validações básicas (Exigência do projeto)
            if (nome == null || nome.trim().isEmpty()) {
                System.out.println("Erro: O nome não pode estar vazio.");
                return; // Interrompe o processo
            }

            // 2. Criar um Fornecedor fictício (ou ler de outros campos se os tiver)
            Fornecedor fornecedor = new Fornecedor("12.345.678/0001-99", "Farmacêutica SA", "", "", "", "");

            // 3. Criar o objeto Medicamento
            Medicamento novoMedicamento = new Medicamento(
                    "", // O UUID é gerado internamente no seu construtor
                    nome,
                    descricao,
                    principio,
                    validade,
                    estoque,
                    controlado,
                    fornecedor
            );
            novoMedicamento.setPreco(preco);

            // 4. Adicionar à lista observável (isto atualiza a Tabela instantaneamente!)
            listaObservable.add(novoMedicamento);

            // 5. Salvar a lista completa no ficheiro CSV
            ArquivoUtil.salvarMedicamentos(listaObservable);

            // 6. Limpar os campos para o próximo cadastro
            txtNome.clear();
            txtDescricao.clear();
            txtPrincipio.clear();
            txtEstoque.clear();
            txtPreco.clear();
            dpValidade.setValue(null);
            chkControlado.setSelected(false);

            System.out.println("Medicamento cadastrado com sucesso!");

        } catch (NumberFormatException e) {
            // Se o utilizador digitar "ABC" no campo de quantidade
            System.out.println("Erro: Verifique se os campos numéricos estão corretos (Estoque e Preço).");
        }

    }

    @FXML
    void onExcluirClick(ActionEvent event) {
        // 1. Pega o medicamento que está selecionado na tabela
        Medicamento medicamentoSelecionado = tabelaMedicamentos.getSelectionModel().getSelectedItem();

        // 2. Verifica se o utilizador realmente selecionou alguma coisa
        if (medicamentoSelecionado != null) {

            // 3. Remove da lista da interface gráfica
            listaObservable.remove(medicamentoSelecionado);

            // 4. Salva a nova lista (agora sem o item excluído) no arquivo CSV[cite: 2]
            ArquivoUtil.salvarMedicamentos(listaObservable);

            // 5. Exibe um alerta de sucesso na tela
            Alert alertaSucesso = new Alert(Alert.AlertType.INFORMATION);
            alertaSucesso.setTitle("Exclusão");
            alertaSucesso.setHeaderText(null);
            alertaSucesso.setContentText("O medicamento " + medicamentoSelecionado.getNome() + " foi excluído com sucesso!");
            alertaSucesso.showAndWait();

        } else {
            // Se o utilizador clicar no botão sem selecionar nada na tabela, mostra um aviso
            Alert alertaAviso = new Alert(Alert.AlertType.WARNING);
            alertaAviso.setTitle("Aviso");
            alertaAviso.setHeaderText(null);
            alertaAviso.setContentText("Por favor, selecione um medicamento na tabela antes de clicar em Excluir.");
            alertaAviso.showAndWait();
        }
    }

    @FXML
    void onConsultarClick(ActionEvent event) {
        // 1. Pega o código digitado no campo de busca
        String codigoBusca = txtBuscaCodigo.getText();

        // Verifica se o utilizador digitou algo
        if (codigoBusca == null || codigoBusca.trim().isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Aviso");
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor, digite um código para consultar.");
            alerta.showAndWait();
            return;
        }

        // 2. Busca o medicamento usando a Stream API (Moderno e elegante)
        Medicamento medicamentoEncontrado = listaObservable.stream()
                .filter(m -> m.getCodigo().equalsIgnoreCase(codigoBusca.trim()))
                .findFirst()
                .orElse(null);

        // 3. Exibe o resultado
        if (medicamentoEncontrado != null) {
            Alert alertaSucesso = new Alert(Alert.AlertType.INFORMATION);
            alertaSucesso.setTitle("Consulta de Medicamento");
            alertaSucesso.setHeaderText("Medicamento Encontrado!");
            alertaSucesso.setContentText(
                    "Código: " + medicamentoEncontrado.getCodigo() + "\n" +
                            "Nome: " + medicamentoEncontrado.getNome() + "\n" +
                            "Princípio Ativo: " + medicamentoEncontrado.getPrincipioAtivo() + "\n" +
                            "Estoque: " + medicamentoEncontrado.getQuantidadeEstoque() + " unidades\n" +
                            "Preço: R$ " + medicamentoEncontrado.getPreco() + "\n" +
                            "Validade: " + medicamentoEncontrado.getDataValidade() + "\n" +
                            "Controlado: " + (medicamentoEncontrado.isControlado() ? "Sim" : "Não") + "\n" +
                            "Fornecedor: " + medicamentoEncontrado.getFornecedor().getRazaoSocial()
            );
            alertaSucesso.showAndWait();
        } else {
            Alert alertaErro = new Alert(Alert.AlertType.ERROR);
            alertaErro.setTitle("Consulta de Medicamento");
            alertaErro.setHeaderText(null);
            alertaErro.setContentText("Nenhum medicamento encontrado com o código: " + codigoBusca);
            alertaErro.showAndWait();
        }
    }
    @FXML
    void onRelatorioEstoqueClick(ActionEvent event) {
        // Exigência: Filtrar medicamentos com estoque baixo (menos de 5 unidades)[cite: 3]
        List<Medicamento> estoqueBaixo = listaObservable.stream()
                .filter(m -> m.getQuantidadeEstoque() < 5)
                .toList(); // No Java 16+ pode usar .toList(), em versões mais antigas use .collect(Collectors.toList())

        // Atualiza a tabela apenas com os itens filtrados
        tabelaMedicamentos.setItems(FXCollections.observableArrayList(estoqueBaixo));

        // Feedback visual
        if (estoqueBaixo.isEmpty()) {
            System.out.println("Nenhum medicamento com estoque abaixo de 5 unidades.");
        } else {
            System.out.println("Relatório de Estoque Baixo gerado.");
        }
    }

    @FXML
    void onRelatorioVencimentoClick(ActionEvent event) {
        // Exigência: Filtrar medicamentos próximos ao vencimento (próximos 30 dias)[cite: 3]
        LocalDate hoje = LocalDate.now();
        LocalDate daquiA30Dias = hoje.plusDays(30);

        List<Medicamento> vencimentoProximo = listaObservable.stream()
                // Pega itens onde a data de validade seja <= hoje + 30 dias E que ainda não estejam vencidos (>= hoje)
                .filter(m -> !m.getDataValidade().isAfter(daquiA30Dias) && !m.getDataValidade().isBefore(hoje))
                .toList();

        // Atualiza a tabela
        tabelaMedicamentos.setItems(FXCollections.observableArrayList(vencimentoProximo));

        System.out.println("Relatório de Vencimento gerado.");
    }
    @FXML
    void onMostrarTodosClick(ActionEvent event) {
        // Devolve a lista original completa para a tabela
        tabelaMedicamentos.setItems(listaObservable);
    }
}
