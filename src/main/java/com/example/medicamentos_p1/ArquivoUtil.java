package com.example.medicamentos_p1;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArquivoUtil {
    private static final String CAMINHO_ARQUIVO = "medicamentos.csv";
    private static final String SEPARADOR = ";";

    // Salva toda a lista no arquivo, sobrescrevendo o conteúdo anterior
    public static void salvarMedicamentos(List<Medicamento> medicamentos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {
            for (Medicamento m : medicamentos) {
                // Formato exigido: separados por ponto-e-vírgula
                String linha = m.getCodigo() + SEPARADOR +
                        m.getNome() + SEPARADOR +
                        m.getDescricao() + SEPARADOR +
                        m.getPrincipioAtivo() + SEPARADOR +
                        m.getDataValidade().toString() + SEPARADOR +
                        m.getQuantidadeEstoque() + SEPARADOR +
                        m.getPreco() + SEPARADOR + // Atributo que precisa ser adicionado
                        m.isControlado() + SEPARADOR +
                        m.getFornecedor().getCnpj() + SEPARADOR +
                        m.getFornecedor().getRazaoSocial();

                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    // Lê o arquivo CSV e retorna a lista preenchida
    public static List<Medicamento> carregarMedicamentos() {
        List<Medicamento> lista = new ArrayList<>();
        File arquivo = new File(CAMINHO_ARQUIVO);

        if (!arquivo.exists()) {
            return lista; // Se for a primeira execução, retorna lista vazia
        }

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(SEPARADOR);

                // Recriando o fornecedor com os dados salvos
                Fornecedor f = new Fornecedor(dados[8], dados[9], "", "", "", "");

                Medicamento m = new Medicamento(
                        dados[0], // codigo
                        dados[1], // nome
                        dados[2], // descricao
                        dados[3], // principioAtivo
                        LocalDate.parse(dados[4]), // dataValidade
                        Integer.parseInt(dados[5]), // quantidadeEstoque
                        Boolean.parseBoolean(dados[7]), // controlado
                        f // fornecedor
                );
                m.setPreco(new BigDecimal(dados[6]));
                lista.add(m);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao carregar arquivo: " + e.getMessage());
        }
        return lista;
    }
}