# trabalho_analise
Trabalho 1 – Análise de Desempenho
Data de entrega: 18/09/2020 até as 23:55 no Moodle. Valor: 2,0.
Pode ser feito em dupla ou individualmente.
Prof. Flavio Barbieri Gonzaga – Universidade Federal de Alfenas – UNIFAL-MG

Tomando como base o código de simulação desenvolvido até aqui, vocês devem executar 4
cenários na simulação, com as respectivas ocupações:

  ````
  > Ocupação == 40%
  > Ocupação == 80%
  > Ocupação == 90%
  > Ocupação == 99%
  ````

Em todos os cenários a chegada será de 2 requisições por segundo. Portanto, você deve alterar
o tempo médio de atendimento de modo a obter cada um dos cenários acima.
A dupla deverá apresentar um relatório para cada cenário contendo:
  
  I) O tempo médio de atendimento utilizado na simulação de modo a se obter
  matematicamente a ocupação desejada.
  II) Os valores finais de ocupação, lambda, E[N] e E[W] obtidos ao se executar a
  simulação.
  III) O módulo da diferença entre E[N] e Lambda * E[W].
  IV) Gráficos das medidas de ocupação, E[N] e E[W]. As medidas devem ser coletadas a
  cada 100 segundos para a geração dos gráficos. Esse parâmetro (intervalo de
  tempo) deve ser possível de ser ajustado na simulação.
  V) A semente usada de modo a se obter o resultado apresentado.

Observação: O tempo de simulação deve ser de pelo menos 100.000 de segundos.

Forma de envio: Deverá ser submetido no Moodle um arquivo em formato .zip contendo
internamente o Projeto do NetBeans e o relatório em pdf. O trabalho pode ser desenvolvido
em linguagem C ou Java.
Bom trabalho! :-)
-------------------------------------------------------------------------------------------------------
Trabalho 2 – Análise de Desempenho
Data de entrega: 16/10/2020 até as 23:55 no Moodle. Valor: 4,0.
Pode ser feito em dupla ou individualmente.

Prof. Flavio Barbieri Gonzaga – Universidade Federal de Alfenas – UNIFAL-MG

No trabalho 2 pensaremos no nosso simulador como sendo uma loja, e cujo negócio deu muito
certo ($$$)! O proprietário da loja já possui um módulo de treinamento e sistema otimizados,
de modo que cada caixa da loja gasta em média 1 minuto (seguindo v.a. Exponencial) para
atender cada cliente.

Como o movimento da loja está em franca expansão, o seu simulador deve ser capaz de
suportar quantos caixas forem necessários (o limite é a memória do computador). Assim, no
início da simulação deve se informar quantos caixas estarão disponíveis ao longo da mesma.

Além da quantidade de caixas, outro parâmetro a ser informado no início da simulação é a
relação entre a chegada e a capacidade total de atendimento. Por exemplo, suponha que a
simulação seja executada com 10 caixas (como cada caixa leva em média 1 minuto para
atender cada cliente), constata-se que a loja possui a capacidade agregada de atender em
média 10 clientes por minuto, ou seja, um em cada caixa. Caso o parâmetro de relação entre a
chegada e a capacidade total de atendimento seja informado como sendo 0,8, isso significa
que a taxa de média de chegada deverá ser de 80% da capacidade total de atendimento da
loja, ou nesse caso, de 8 clientes/minuto (também de acordo com v.a. Exponencial).

Tomando como base o código de simulação desenvolvido até aqui, vocês devem executar 8
cenários na simulação, com as respectivas características:
 ```
- 5 caixas
  > Relação entre a chegada e capacidade total de atendimento == 0,4
  > Relação entre a chegada e capacidade total de atendimento == 0,8
  > Relação entre a chegada e capacidade total de atendimento == 0,9
  > Relação entre a chegada e capacidade total de atendimento == 0,99
- 10 caixas
  > Relação entre a chegada e capacidade total de atendimento == 0,4
  > Relação entre a chegada e capacidade total de atendimento == 0,8
  > Relação entre a chegada e capacidade total de atendimento == 0,9
  > Relação entre a chegada e capacidade total de atendimento == 0,99
```
A dupla deverá apresentar um relatório para cada cenário contendo:
  I) Estrutura de dados escolhida para se gerenciar os eventos da simulação, com a
  devida justificativa.
  II) Os valores finais de lambda, E[N] e E[W] obtidos ao se executar a simulação.
  III) O módulo da diferença entre E[N] e Lambda * E[W].
  IV) Gráficos das medidas de lambda, E[N] e E[W]. As medidas devem ser coletadas a
  cada 600 segundos para a geração dos gráficos. Esse parâmetro (intervalo de
  tempo) deve ser possível de ser ajustado na simulação.
  V) A semente usada de modo a se obter o resultado apresentado.

Observação: O tempo de simulação deve ser de pelo menos 1.200.000 de segundos.

Forma de envio: Deverá ser submetido no Moodle um arquivo em formato .zip contendo
internamente o Projeto do NetBeans e o relatório em pdf. O trabalho pode ser desenvolvido
em linguagem C ou Java.
Bom trabalho! :-)
