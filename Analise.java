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
- InformacoesDespesasVisao
- InformacoesGastosVisao
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
    boolean status

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

- GastosModelo
    int id;
    String descricao;
    String dataGasto;
    double valor;
    String categoriaGasto; // Ex.: Manutenção, Água, Energia, etc.
    String tipoGasto; // "DESPESA" ou "MANUTENÇÃO"
    String dataCadastro;

    // Campos opcionais para manutenção
    String dataAgendamento;
    String statusManutencao;
    String fornecedor;

4. Ficheiro
- UnidadeFile.dat
- MoradorFile.dat
- DespesaFile.dat
- GastosFile.dat

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
