# Story: Calcul de taxes


## Enoncé

Une taxe sur la valeur ajoutée de 10% est appliquée sur chaque produit, à l'exception des livres, de
la nourriture et des médicaments, qui en sont exemptés. Une taxe additionnelle de 5% sur les
produits importés, sans exception.

Le montant de chacune des taxes est arrondi aux 5 cents supérieurs, selon la règle suivante


Taxe calculée Taxe imputée
0.99 1.00
1.00 1.00
1.01 1.05
1.02 1.05

Lorsque l'on passe une commande, une facture est émise listant chacun des produits ainsi que leur
prix TTC ; au bas de la facture figurent le montant total (TTC) ainsi que le montant total des taxes.
Le montant TTC est calculé comme suit : Pttc = Pht + somme(arrondi(Pht*t/100)) Pttc: Prix TTC Pht :
Prix hors taxes t : taxe applicable



## compile
```
mvn clean install
```

## Test
This example is to run under JBoss Wildfly. Put the compiled war under the deployment directory: {jboss install dir}/standalone/deployments

### Input1
```
curl -X POST \
  http://localhost:8080/tax-calcule/tax \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: b5341525-8147-692c-9af1-b44a9b346f38' \
  -d '{"orders":[{"id":"1","name":"livre","quantity":1,"priceHt":12.49},{"id":"2","name":"CD musical","quantity":1,"priceHt":14.99},{"id":"3","name":"barre de chocolat","quantity":1,"priceHt":0.85}]}
'
```

should give you:
```
1	livre	:	12.49
1	CD musical	:	16.49
1	barre de chocolat	:	0.85
Montant des taxes	:	1.50
Total	:	29.83
```
### Input 2
```
curl -X POST \
  http://localhost:8080/tax-calcule/tax \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 22e6d35a-7acb-b5d1-2f53-478b39c137d0' \
  -d '{"orders":[{"id":"4","name":"boîte de chocolats importée","quantity":1,"priceHt":10.00},{"id":"5","name":"flacon de parfum importé","quantity":1,"priceHt":47.50}]}'
  ```
  
  should give you
  
 ```
1	boîte de chocolats importée	:	10.50
1	flacon de parfum importé	:	54.65
Montant des taxes	:	7.65
Total	:	65.15
```

