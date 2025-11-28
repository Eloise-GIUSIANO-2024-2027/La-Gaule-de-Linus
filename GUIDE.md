# Guide d'utilisation - La Gaule de Linus

## 🎮 Comment jouer

### Lancer le jeu
```bash
cd /amuhome/c24003506/LINUS/La-Gaule-de-Linus
javac src/consomable/*.java src/*.java
java -cp src Main
```

### Lancer les tests de la potion
```bash
javac -cp src TestPotion.java
java -cp src:. TestPotion
```

## 🍖 Système de nourriture

### Aliments disponibles
- Sanglier
- Poisson passablement frais / Poisson pas frais
- Gui, Homard, Fraises, Carottes, Sel
- Trèfle à quatre feuilles (frais / pas frais)
- Huile de roche, Jus de betterave, Miel, Vin, Hydromel
- Lait de licorne à deux têtes
- Poils d'Idéfix
- Ingrédient secret

### Règles alimentaires
**Gaulois** : Sanglier, Poisson passablement frais, Vin
**Romains** : Sanglier, Miel, Vin, Hydromel

### Règles de santé
⚠️ **Dangers** :
- Le poisson pas frais est toujours mauvais
- Manger 2 végétaux consécutifs est mauvais pour la santé

## 🧪 Système de potion magique

### Recette de base
**Ingrédients obligatoires** :
- Gui
- Carottes
- Sel
- Trèfle à quatre feuilles frais
- Poisson passablement frais
- Huile de roche (ou jus de betterave)
- Miel
- Hydromel
- Ingrédient secret

### Options nourrissantes
Ajouter **l'un** de ces ingrédients rend la potion nourrissante :
- Homard
- Fraises
- Jus de betterave (en remplacement de l'huile de roche)

### Pouvoirs additionnels
- **Lait de licorne à deux têtes** → Pouvoir de dédoublement
- **Poils d'Idéfix** → Métamorphosis (lycanthrope)

### Effets selon les doses

| Doses | Effet |
|-------|-------|
| 1-9 doses | Force surhumaine + Invincibilité (temporaire) |
| 10 doses (1 marmite) | Effets PERMANENTS |
| 20+ doses (2 marmites) | ⚠️ Transformation en statue de granit ! |

## 📋 Menu du jeu

1. **Voir tous les aliments disponibles** - Liste complète
2. **Voir les aliments consommables** - Selon votre personnage
3. **Manger un aliment** - Avec vérification de santé
4. **Gérer la potion magique** - Créer, modifier, boire
5. **Quitter** - Fin du jeu

### Sous-menu Potion
- Ajouter des ingrédients spéciaux
- Voir la composition
- Boire la potion
- Créer une nouvelle marmite
- Voir la recette

## 🎯 Exemples d'utilisation

### Créer une potion puissante
1. Menu → 4 (Gérer la potion)
2. Créer une nouvelle potion
3. Ajouter lait de licorne (dédoublement)
4. Ajouter poils d'Idéfix (lycanthrope)
5. Ajouter homard (nourrissante)
6. Boire 10 doses → Effets permanents !

### Attention aux dangers
- Ne pas boire 20 doses d'un coup !
- Ne pas manger poisson pas frais
- Alterner végétaux et autres aliments

Par Toutatis ! 🐗⚡

