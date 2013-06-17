infix-interpreter
=================

Interpreter und Auswerter von Termen in Infix-Notation (Version 0.4)

Lizenz
------
Die Quelltexte in diesem Projekt stehen unter der MIT-Lizenz. Der Lizenztext findet sich in der Datei LICENSE.

Features
--------
Folgende Funktionen sind bereits implementiert:
- Verarbeitung der 4 Grundrechenarten und der Potenzierung durch "^" (mit rechtsassoziativem Verhalten)
- Negation von Werten durch vorangestelltes "-", wenn folgend auf einen anderen Operator



Ablaufbeschreibung InfixInterpreter
-----------------------------------

__Vorbereitungen__

Zu Beginn der Kalkulation in der Methode calc() wird der uebergebene Term um eventuelle Leerzeichen
bereinigt und eine Validitaetsueberpruefung in der Methode isValidTerm() findet statt.
Anschliessend findet die Initialisierung der beiden benoetigten Stacks fuer die Operatoren und
Operanden statt. Des Weiteren wird Term mit Klammern umgeben, die im spaeteren Verlauf fuer eine
Evaluierung verantwortlich ist.
Abschliessend wird der Term durch einen Tokenizer in einzelne Teile zerlegt, getrennt nach
Operatoren (+-*/()^) und Operanden. Hierbei werden ebenfalls Klammern bei Potenzen eingefügt, um die Rechtsassoziativität zu gewährleisten.

__Hauptschleife__

Solange weitere Elemente (Tokens) im Tokenizer vorliegen, wird fuer jedes Element der
Schleifenkoerper durchlaufen. In diesem finden Fallunterscheidungen statt:

a)	Es handelt sich um eine oeffnende Klammer:
	Die Klammer wird auf den Operatorstack gelegt.
	
b)	Es handelt sich um einen Operanden:
	Der Wert wird auf den Operandenstack gelegt.
	
c)	Es handelt sich um eine schliessende Klammer:
	Es wird eine innere Schleife durchlaufen, die abgebrochen wird, bis das naechste Element auf dem Operatorstack eine oeffnende Klammer ist. Dies bedeutet, dass alle notwendigen Kalkulationen durchgefuehrt wurden.
Innerhalb der Schleife wird ein Operator vom Operatorstack genommen und zwei Operanden vom Operandenstack. Diese werden nun entsprechend ihrer Kommutativitaet berechnet und das Ergebnis auf den Operandenstack gelegt.
	Abschliessend (nach Durchlauf der Schleife) wird die oeffnende Klammer vom Operatorstack entfernt, da diese mit der ausloesenden, schliessenden Klammer korrespondierte und nun aufgeloest werden kann.

d)	Es handelt sich um einen Operator +-*/^:
	Es wird eine innere Schleife durchlaufen, die abgebrochen wird, wenn der Operatorstock leer ist oder die Praezedenz des oben auf dem Operatorstack liegenden Operators geringer ist als die Praezedenz des aktuellen Operators.
Innerhalb der Schleife wird ein Operator vom Operatorstack genommen und zwei Operanden vom Operandenstack. Diese werden nun entsprechend ihrer Kommutativitaet berechnet und das Ergebnis auf den Operandenstack gelegt.
Abschliessend (nach Durchlauf der Schleife) wird der aktuelle Operator auf den Stack gelegt.

__Ausgabe__

Zum Abschluss wird das letzte verbliebene Element auf dem Operandenstack ausgegeben. Dieses
entspricht dem Ergebnis der Berechnung des Terms.

ChangeLog
---------

- 0.4: Potenzierung arbeitet nun rechtsassoziativ / Negation wird nun auch vor Klammern erkannt
- 0.3: Erweiterung um Verarbeitung der Potenzierung mit dem Symbol "^"
- 0.2: Erweiterung um eigenen Tokenizer zur Verarbeitung negativer Werte
- 0.1: Initiale Implementierung
