💊 Sistema de Gestão de Medicamentos

Uma aplicação desktop desenvolvida em JavaFX para o gerenciamento de inventário de medicamentos em uma farmácia. Este projeto tem como objetivo demonstrar a aplicação de conceitos de Programação Orientada a Objetos (POO), interfaces gráficas modernas e manipulação de dados.

✨ Funcionalidades

O sistema implementa um CRUD completo e relatórios avançados:

Cadastrar Medicamento: Inserção de novos medicamentos com atributos detalhados (Nome, Descrição, Princípio Ativo, Quantidade em Estoque, Preço, Data de Validade, Status de Controle e Fornecedor).

Geração de Código Único: Todo medicamento recebe automaticamente um código identificador único (UUID) no momento do cadastro.

Consultar por Código: Busca rápida e precisa de um medicamento específico através do seu UUID.

Excluir Medicamento: Remoção de itens do inventário com atualização imediata da interface.

Persistência de Dados (CSV): Salvamento e carregamento automático dos dados utilizando arquivos .csv, garantindo que nenhuma informação seja perdida ao fechar o programa.

Relatórios Inteligentes (Stream API):

📉 Estoque Baixo: Filtra e exibe medicamentos com menos de 5 unidades disponíveis.

⏳ Vencimento Próximo: Filtra e exibe medicamentos que expiram nos próximos 30 dias.

🔄 Mostrar Todos: Restaura a visualização da tabela completa após a aplicação de filtros.

🛠️ Tecnologias Utilizadas

Java 21+: Linguagem principal do projeto.

JavaFX & FXML: Construção da Interface Gráfica do Utilizador (GUI).

SceneBuilder: Ferramenta de design visual para os arquivos .fxml.

Java Streams API: Utilizada para a filtragem eficiente e moderna dos relatórios (filter, toList).

Manipulação de Arquivos (I/O): Uso de BufferedReader e BufferedWriter para manipulação do arquivo CSV.

📁 Estrutura do Projeto

O projeto adota o padrão de arquitetura MVC (Model-View-Controller) simplificado:

src/
 ├── controller/
 │   └── HelloController.java      # Lida com as ações dos botões e atualiza a Tabela
 ├── models/
 │   ├── ArquivoUtil.java          # Lógica de salvar/carregar do medicamentos.csv
 │   ├── Fornecedor.java           # Classe modelo para fornecedores
 │   └── Medicamento.java          # Classe modelo com os atributos e geração de UUID
 ├── application/
 │   └── HelloApplication.java     # Classe principal que inicia a janela (Stage/Scene)
 └── resources/
     └── hello-view.fxml           # Arquivo visual criado no SceneBuilder


🚀 Como Executar o Projeto

Pré-requisitos

JDK 21 ou superior instalado e configurado nas variáveis de ambiente.

Uma IDE compatível com projetos JavaFX (recomenda-se o IntelliJ IDEA).

Passos

Clone este repositório:

git clone https://github.com/SEU-USUARIO/Gestao-Medicamentos-JavaFX.git


Abra o projeto na sua IDE.

Certifique-se de que as dependências do JavaFX estão configuradas (caso não esteja usando Maven/Gradle, vincule a biblioteca JavaFX SDK ao projeto).

Navegue até a classe HelloApplication.java.

Execute o método main.

(Nota: O arquivo medicamentos.csv será gerado automaticamente na raiz do projeto logo após o primeiro cadastro).

Projeto desenvolvido como requisito de avaliação acadêmica.
