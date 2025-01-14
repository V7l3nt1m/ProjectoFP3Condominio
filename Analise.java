/*------------------------------------
Tema: Gestão de um Condomínio
Nome: Valentim Loth Simão Prado
Numero: 33031
Ficheiro: Analise.java
Data: 14/01/2025
--------------------------------------*/

/*
1. Objectivo
Este projeto tem o objetivo de gerenciar as informações de moradores, despesas, 
reservas de áreas comuns e manutenção do condomínio.

2. Visao [Interfaces Graficas]
- ApresentacaoVisao
- LoginVisao
- MenuPrincipal

- UnidadeVisao
- MoradorVisao
- DespesaVisao
- ManutencaoVisao

- InformacoesUnidadesVisao
- InformacoesMoradoresVisao
- InformacoesDespesasVisao
- InformacoesManutencaoVisao

- EditarUnidadeVisao
- EditarMoradorVisao
- EditarDespesaVisao
- EditarManutencaoVisao

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

- MoradorModelo
    int id
    String nome
    String tipo_documento
	String numero_documento
    String telefone
    String email
    UnidadeModelo unidade; //id da casa ou apartamento
    boolean statusAtivo;
    boolean isResponsavel;
    int moradorResponsavelId;

- DespesaModelo
    int id
    String descricao
    String dataDespesa
    double valor
    String categoriaDespesa // Ex.: Manutenção, Água, Energia, etc.

- ManutencaoModelo
    int id
    String descricao
    String dataAgendamento
    String statusManutencao // Ex.: Agendado, Em Andamento, Concluído
    double custo
    String fornecedor

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
- StatusManutencao.tab

6. Diversos
6.1 - Implementação: Java Swing
6.2 - IDE: VScode
*/
