# gitchallenge

Für Java Version 14.0.1

Anwendung starten:

    gradlew bootRun
    
Die Anwendung startet auf Port 8080.
Beispiel Input:

    curl -H "Accept: application/html" -i localhost:8080/user/ammon-s
    curl -H "Accept: application/json" -i localhost:8080/user/ammon-s
    curl -i localhost:8080/user/ammon-s

Die Anwendung wirft einen nicht in der Aufgabenstellung spezifizierten Fehlercode, wenn das Github Anfragelimit erreicht wurde:

    {
    "status" : 403,
    "message" : "API rate limit exceeded"
    }
