/*------------------------------------
Tema: Gestão de Condomínio
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: Analise.java
Data: 14/01/2025
--------------------------------------*/

/*
1. Objectivo
Este projeto tem o objetivo de gerenciar as informações das unidades, moradores, despesas, pagamentos
e manutenção de um condomínio.

2. Visao [Interfaces Graficas]
- ApresentacaoVisao
- LoginVisao
- MenuPrincipal

- UnidadeVisao
- MoradorVisao
- DespesaVisao
- ManutencaoVisao
- PagamentoVisao

- InformacoesUnidadesVisao
- InformacoesMoradoresVisao
- InformacoesDespesasVisao
- InformacoesManutencaoVisao
- InformacoesPagamentoVisao

3. Entidades Fortes e Seus Atributos (Modelo)


- UnidadeModelo 
    int id
    String numeroUnidade
    String tipoUnidade     
    double area
    int andares
    int numQuartos
    String bloco
    int garagemCapacidade
    boolean statusUnidade
    int andaresDisponivel
    String dataDeCadastro

- MoradorModelo
    int id
    String nome
    String tipo_documento
	String numero_documento
    String telefone
    String email
    UnidadeModelo unidade; //id da casa ou apartamento
    boolean statusAtivo;
    String nPorta; //000 se for casa
    boolean isResponsavel;
    int moradorResponsavelId;
    String DataDeCadastro

- PagamentoModelo
    int id;                   // Identificador único do pagamento
    MoradorModelo morador;    // Morador que fez o pagamento
    UnidadeModelo unidade;    // Unidade associada ao pagamento (se necessário)
    double valorPago;         // Valor pago
    String dataPagamento;     // Data do pagamento
    String tipoPagamento;     // Tipo de pagamento (ex: Mensalidade, Multa, Taxa Extra)
    String statusPagamento;   // Status do pagamento (ex: Concluído, Pendente, Atrasado)

- DespesaModelo
    int id
    String descricao
    String dataDespesa
    double valor
    String categoriaDespesa // Ex.: Manutenção, Água, Energia, etc.
    String DataDeCadastro

- ManutencaoModelo
    int id
    String descricao
    String dataAgendamento
    String statusManutencao // Ex.: Agendado, Em Andamento, Concluído
    double custo
    String fornecedor
    String DataDeCadastro
    UnidadeModelo unidade;
    DespesaModelo despesa;

4. Ficheiro
- UnidadeFile.dat
- MoradorFile.dat
- DespesaFile.dat
- ManutencaoFile.dat

5. Tabelas de Apoio (Auxiliares) = Entidades Fracas

- TipoUnidade.tab
- Bloco.tab
- TipoDocumento.tab 
- CategoriaDespesa.tab
- TipoPagamento
- StatusManutencao.tab

6. Diversos
6.1 - Implementação: Java Swing
6.2 - IDE: VScode
*/
