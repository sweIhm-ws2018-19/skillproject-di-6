# Braingain- ein Alexa Skill
Ein neues Spiel für dich und deine Freunde, oder zum Alleine-Spielen!Teste dein Wissen und erweitere deine Fähigkeit des Logischen Denkens!

## 

Im Rahmen der Vorlesung Software Engineering unserer Hochschule wollen wir, vier junge Studenten, ein Spiel eingebettet in der Alexa App programmieren.

## Das ist Braingain!
Du kannst dir eine Kategorie sowie ein Schwierigkeitslevel aussuchen, in dem du getestet wirst. Alexa stellt dir dann eine Runde von Aufgaben, die du lösen musst, um Punkte zu gewinnen. Dadurch, dass Alexa deinen Highscore speichern kann, kannst du sogar den Fortschritt beobachten, denn du im Verlauf mehrerer Spiele machen kannst. Und- weil geteilte Freude doppelte Freude ist- gibt es auch die Möglichkeit, Braingain zusammen mit bis zu drei Freunden zusammen zu spielen, im Duellmodus, um zu sehen, wer von euch der Champion ist!
## Konzept
Für ein etwas komplexeres Spiel wie dieses strukturieren wir unseren Code nach dem Model-View-Controller Konzept.  Controller sind hierin die EventHandler- Klassen, die auf neue Events reagiert und die erlangten Informationen an das Model weiterleitet welches diese speichert und verarbeitet, und damit die Logik unseres Spieles darstellt. Unsere View stellt dieses Mal kein Visuelles Element dar, sondern kontrolliert die auditiven Ausgaben von Alexa.
## Herangehensweise
Der Skill wird über mehrere Plattformen aufgebaut- die AWS-Developement Konsole stellt die Backendelemente zur Verfügung und die Alexa Developement Konsole die Frontendelemente.
Um die Backendelemente zu verwalten, lädt man den zugehörigen Code als Maven Build in AWS hoch. Für  die Frontendelemente erstellt die Alexa Developement Console eine JSoN File mit den InvocationName, also dem Namen des Skills, den man Alexa sagen muss, damit sie den Skill öffnet, den ganzen Intentnamen mit zugehörigen Textsamples, welche den Intent auslösen können, und Slots. Slots stellen hierbei eine Ansammlung an Werten dar, aus denen der User innerhalb bestimmten Intents wählen kann. Zum Beispiel haben wir in unseren Skill Brain Gain eine Reihe aus Kategorien, aus denen man als User wählen kann: Logik, Mathe, Geographie und Gedächtnistraining.

Zu den einzelnen Intents muss man Intenthandler in Form von Code schreiben, die genauer festlegen, was Alexa machen soll, wenn ein Intent durch den User über ein Textsample ausgelöst wird.
