# Braingain- ein Alexa Skill
Ein neues Spiel für dich und deine Freunde, oder zum Alleine-Spielen!Teste dein Wissen und erweitere deine Fähigkeit des Logischen Denkens!

## 

Im Rahmen der Vorlesung Software Engineering unserer Hochschule wollen wir, vier junge Studenten, ein Spiel eingebettet in der Alexa App programmieren.

## Das ist Braingain!
Du kannst dir eine Kategorie sowie ein Schwierigkeitslevel aussuchen, in dem du getestet wirst. Alexa stellt dir dann eine Runde von Aufgaben, die du lösen musst, um Punkte zu gewinnen. Dadurch, dass Alexa deinen Highscore speichern kann, kannst du sogar den Fortschritt beobachten, denn du im Verlauf mehrerer Spiele machen kannst. Und- weil geteilte Freude doppelte Freude ist- gibt es auch die Möglichkeit, Braingain zusammen mit bis zu drei Freunden zusammen zu spielen, im Duellmodus, um zu sehen, wer von euch der Champion ist!

## Konzept

Für ein etwas komplexeres Spiel wie dieses strukturieren wir unseren Code nach dem Model-View-Controller Konzept.  Controller sind hierin die EventHandler- Klassen, die auf neue Events reagiert und die erlangten Informationen an das Model weiterleitet welches diese speichert und verarbeitet, und damit die Logik unseres Spieles darstellt. Unsere View stellt dieses Mal kein Visuelles Element dar, sondern kontrolliert die auditiven Ausgaben von Alexa.
