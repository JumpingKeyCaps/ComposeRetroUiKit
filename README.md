# Retro Display Components – Jetpack Compose

Un ensemble de composants UI réalistes en Jetpack Compose, incluant :
- Afficheurs 7 et 14 segments avec effets visuels poussés (bevel, glow, flicker, reflection).
- LED individuelles ou barres verticales type VU-mètre.
- Potentiomètres rotatifs analogiques (style Marshall / Moog).
- Support complet de personnalisation : couleur, alpha, taille, animation, état, style.

---

## 🧩 Composants inclus

### `SevenSegmentDigitDisplay`

Afficheur unique à 7 segments photoréaliste, proportions standard (segments verticaux et horizontaux égaux).

- Effets visuels : glow, flicker, biseau dynamique.
- Prend en charge chiffre (`digit`) ou segments manuels (`manualSegments`).
- Affichage centré, parfait pour composition précise.

---

### `SevenSegmentDigitDisplayAlt`

Afficheur 7 segments personnalisable avec longueur indépendante des segments horizontaux.

- Permet compression verticale/horizontale stylisée.
- Utilisé dans `FiveDigitDisplay`.
- Prend en charge `digit`, `char`, ou segments manuels.

---

### `SevenSegmentDigitDisplayExtended`

Afficheur 7 segments personnalisable avec toute les features ajouter.

---

### `FourteenSegmentDisplayExtended`

Afficheur 14 segments ultra-complet avec :
- Effets réalistes : glow, biseau, flicker, animation idle.
- Support des lettres, chiffres, et configuration manuelle.
- Animation `idle` type spinner configurable.

---

###  `FiveDigitDisplay`

Affiche horizontalement cinq digits à 7 segments avec personnalisation indépendante de chaque chiffre.

- `SevenSegmentDisplayVariante` utilisé pour chaque chiffre.
- Supporte : couleur, glow, flicker, alpha, épaisseur, biseau, espacement.
- Rotation 3D optionnelle (`rotationXAngle`, `rotationYAngle`, `cameraAdjustment`).

---

###  `FiveDigitDisplayWithReflect`

Double affichage avec effet de reflet réaliste sous l’afficheur principal.

- Utilise `FiveDigitDisplay` deux fois : affichage + reflet inversé.
- Contrôle des angles, de l’alpha, et de la perspective simulée.
- Parfait pour un effet miroir sur fond sombre.

---

###  `Multi7SegDisplay`

Afficheur multi-digit dynamique au format 7 segments (chiffres/lettres).

- Utilise une liste de `SevenSegmentConfig`.
- Supporte `overrideValue` (ex: `"1234"`), affichage inversé (`reversedOverride`), espacement conditionnel.
- Option d’affichage d’un reflet avec configuration personnalisée (`ReflectConfig`).

---

###  `Multi14SegDisplay`

Afficheur photoréaliste multi-caractères à 14 segments pour lettres majuscules, chiffres ou symboles.

- Affiche une chaîne de caractères (`text`) ou une liste de chiffres (`digits`).
- Supporte spacing configurable et une `FourteenSegmentConfig` appliquée globalement.

---

###  `LedBarGraph`

Barre verticale de LEDs simulant un vu-mètre audio ou indicateur de niveau.

- LEDs animées avec flicker, glow, et indicateur de `peak hold` avec fondu.
- Couleur indépendante par LED, personnalisable dynamiquement.
- Supporte : alpha global, glow radius, durée du pic, espacement vertical.

---

###  `RealisticLED`

Composant LED unique avec effet visuel réaliste, clignotement et halo lumineux.

- Couleur, taille, clignotement (`blinkInterval`), halo (`haloSpacer`) configurables.
- Simule une LED rétro avec un rendu doux et réaliste même éteinte.

---

###  `RotaryKnob`

Potentiomètre réaliste et interactif à rotation infinie.

- Style inspiré des amplis analogiques (Moog, Marshall).
- Effets visuels :
  - Bevel tournant simulant la lumière.
  - Reflet dynamique sur le cœur.
  - Curseur lumineux.
  - Graduation stylisée avec tick actif.
- Fonctionnalités :
  - Rotation 360° avec interpolation animée.
  - Crans configurables.
  - Retour haptique par cran.
  - Animation fluide entre les crans.

---

### `SimpleNeonOscilloscope`

Oscilloscope stylisé néon avec affichage de formes Lissajous.

- Formes : sinus, carré, triangle, chaos, random.
- Supporte forme personnalisée via lambda.
- Paramètres : épaisseur, couleur, style de glow, fréquence.

---

| Composant                        | Type                  | Spécificités clés                                           |
|----------------------------------|-----------------------|-------------------------------------------------------------|
| `SevenSegmentDigitDisplay`       | 7 segments            | Proportions standard, effets glow/flicker                   |
| `SevenSegmentDigitDisplayAlt`    | 7 segments            | Segment horizontal indépendant, stylisation avancée         |
| `SevenSegmentDigitDisplayExt`    | 7 segments            | Segment horizontal indépendant, stylisation avancée         |
| `SevenSegmentDisplay`            | 7 segments            | Segment horizontal indépendant, stylisation avancée         |
| `FourteenSegmentDisplay`         | 14 segments           | Lettres, chiffres, idle animation, segments manuels         |
| `FiveDigitDisplay`               | 7 segments x5         | Chiffres multiples personnalisables                         |
| `FiveDigitDisplayWithReflect`    | 7 segments x5 + ref   | Reflet miroir réaliste                                      |
| `Multi7SegDisplay`               | 7 segments dynamiques | Chaine / override / reflet optionnel                        |
| `Multi14SegDisplay`              | 14 segments dynamiques| Texte complet, config globale                               |
| `LedBarGraph`                    | LED verticale         | Vu-mètre, pic animé, couleurs multiples                     |
| `RealisticLED`                   | LED unique            | Glow, blink, halo                                           |
| `RotaryKnob`                     | Potentiomètre         | Réalisme max, crans, haptique, effets de lumière dynamiques |
| `RotaryKnobLimited`              | Potentiomètre         | Réalisme max, crans, haptique, effets de lumière dynamiques |
| `SimpleNeonOscilloscope`         | Decoration            | Formes animées, glow, custom waveform                       |
| `Fractal visualizer`             | Visualizer            | Formes animées, glow, custom waveform                       |
| `Wave forms visualizer`          | Visualizer            | Formes animées, glow, custom waveform                       |


---

##  Technologies utilisées

- **Jetpack Compose** : moteur de rendu UI natif.
- **Canvas 2D** : rendu manuel des segments, LEDs et courbes.
- **RenderEffect / BlurMaskFilter** : effets de glow et halo.
- **Animations Compose** : pour flicker, clignotement et interpolation.
- **Kotlin Multiplateforme ready** : code compatible UI Jetpack Compose Desktop (avec adaptation).

---

## 🛡️ Badges

![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-%E2%9C%94-green)
![Kotlin](https://img.shields.io/badge/Kotlin-%E2%9C%94-blue)
![License: MIT](https://img.shields.io/badge/license-MIT-lightgrey)
![Platform: Android & Desktop](https://img.shields.io/badge/platform-Android%20%7C%20Desktop-orange)
![UI Style: Retro](https://img.shields.io/badge/Style-Retro%20Display-%23ff69b4)

---
