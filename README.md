# Braingain- ein Alexa Skill
Ein neues Spiel für dich und deine Freunde, oder zum Alleine-Spielen!Teste dein Wissen und erweitere deine Fähigkeit des Logischen Denkens!

## 

Im Rahmen der Vorlesung Software Engineering unserer Hochschule wollen wir, vier junge Studenten, ein Spiel eingebettet in der Alexa App programmieren.

## Konzept

Für ein etwas komplexeres Spiel wie dieses strukturieren wir unseren Code nach dem Model-View-Controller Konzept.  Controller sind hierin die EventHandler- Klassen, die auf neue Events reagiert und die erlangten Informationen an das Model weiterleitet welches diese speichert und verarbeitet, und damit die Logik unseres Spieles darstellt. Unsere View stellt dieses Mal kein Visuelles Element dar, sondern kontrolliert die auditiven Ausgaben von Alexa.

Amazon Intents, die wir von Amazon so übernehmen konnten, sind der AMAZON.CancelIntent, AMAZON.HelpIntent und der AMAZON.StopIntent.

Nach einem gegebenen Beispiel eines Skills für Alexa haben wir die JSON Datei soweit umgeschrieben, dass sie die Ansprüche unseres Programmes erfüllt. Eine andere Möglichkeit, an die für euren Skill benötigte JSON-Datei zu kommen ist, sie über die Amazon Developer Console erstellen zu lassen. Hier ist unser momentaner JSON-Code, der sich im Verlauf der Spiel-Entwicklung noch verändern kann:
