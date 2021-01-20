# cqCheque
Para que serve o plugin?
Este é um plugin de criação de cheques, para possui coins literalmente em suas mãos!

## Como Criar um Cheque?

A única coisa que você precisa fazer é digitar o comando: /cheque <valor>.
O plugin possui um sisteminha de valor mínimo e máximo para a criação de cheques e é completamente configurável pelo seu arquivo config.yml.
Após a criação de cheques, os coins são retirados da conta do jogador e o jogador que apertar com o botão direito em cima do cheque, adquire o valor nele.
  
## Developer API

Para criar um cheque:
```yaml
Cheque cheque = new Cheque(String playerName, double amount);
```

Para pegar o cheque criado:
```yaml
Cheque cheque = new Cheque(String playerName, double amount);
ItemStack chequeCreated = cheque.createCheque();
```
