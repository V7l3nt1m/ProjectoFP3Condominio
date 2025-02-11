/*------------------------------------
Tema: Gestão de Condomínio
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: Analise.java
Data: 14/01/2025
--------------------------------------*/

/*
1. Objectivo
Este projeto tem o objetivo de gerenciar as informações das unidades, moradores, gastos e pagamentos de um condomínio.

2. Visao [Interfaces Graficas]
- ApresentacaoVisao
- LoginVisao
- MenuPrincipal

- UnidadeVisao
- MoradorVisao
- DespesaVisao
- GastosVisao
- PagamentoVisao

- InformacoesUnidadesVisao
- InformacoesMoradoresVisao
- InformacoesGastosVisao
- InformacoesPagamentoVisao
- VerInformacesUnidadeVisao

3. Entidades Fortes e Seus Atributos (Modelo)


- UnidadeModelo 
    int id
    String tipoUnidade     
    String numeroUnidade
    String bloco
    int andares
    double area
    int numQuartos
    int garagemCapacidade
    boolean statusUnidade
    String dataDeCadastro
    boolean statusRegistro

- MoradorModelo
    int id
    String nome
    String tipo_documento
	String numero_documento
    String telefone
    String email
    String nPorta; //000 se for casa
    boolean isResponsavel;
    String DataDeCadastro
    UnidadeModelo unidade; //id da casa ou apartamento
    int moradorResponsavelId;
    boolean statusRegistro

- PagamentoModelo
    int id;                   
    MoradorModelo morador;     
    UnidadeModelo unidade;    
    String tipoPagamento;    
    double valorPago;
    String descricao          
    String statusPagamento;   
    String dataPagamento;     
    String DataDeCadastro
    boolean statusRegistro


- GastosModelo
    int id;
    String tipoGasto;
    String categoriaGasto;
    double valor;
    String descricao;
    String dataGasto;
    String dataAgendamento;
    String statusManutencao;
    String fornecedor;
    String DataDeCadastro
    boolean statusRegistro

4. Ficheiro
- UnidadeFile.dat
- MoradorFile.dat
- PagamentoFile.dat
- GastosFile.dat

5.Nodes
- UnidadePNode.java
- MoradorPNode.java
- PagamentoPNode.java
- GastoPNode.java

6.Dados Table
- UnidadeDadosTable
- MoradorDadosTable
- PagamentoDadosTable
- GastoDadosTable

5. Tabelas de Apoio (Auxiliares) = Entidades Fracas

- TipoUnidade.tab
- Bloco.tab
- TipoDocumento.tab 
- CategoriaDespesa.tab
- TipoPagamento

6. Diversos
6.1 - Implementação: Java Swing
6.2 - IDE: VScode
*/
