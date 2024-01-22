# TAUSCHRING-Donauswap

## Kurzbeschreibung: 
Es handelt sich um eine Webapplikation mit Xampp, MariaDB, Angular Frontend und Springboot Backend. Es können Güter und Dienstleistungen 
getauscht werden. Tauschmittel sind Donaucoin, ein Token der 1:1 gegen Euro getauscht werden kann.
Jeder kann sich registrieren, jeder eingeloggte User kann Tauschangebote erstellen, oder Waren bzw. Dienstleistungen gegen
Donaucoin tauschen. Donaucoin können für Euro gekauft werden.

## Installation
Original läuft das Projekt mit Windows:

Xampp Version: 8.2.4
Java-Version: JDK17
Springboot-Version: 3.1.6
Node: 16.10.0
npm: 10.2.4
ng: 14.2.13

1. ## Xampp herunterladen und installieren
Besuchen Sie die offizielle Website: https://www.apachefriends.org/de/index.html - Wählen Sie die entsprechende Version für Ihr Betriebssystem (Windows, macOS, Linux) und laden Sie sie herunter.
2. ## Java JDK herunterladen und installieren

Besuchen Sie die offizielle Oracle-Website:
    - [Oracle JDK Downloads für JDK 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
    - [Oracle JDK Downloads für JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
Wählen Sie die entsprechende Version für Ihr Betriebssystem (Windows, macOS, Linux) und laden Sie sie herunter.
Führen Sie die heruntergeladene Installationsdatei aus und folgen Sie den Anweisungen zur Installation.
Während der Installation, notieren Sie sich den Pfad, in dem das JDK installiert wird. Dieser wird benötigt, um die Umgebungsvariable `JAVA_HOME` zu setzen.

3. ### Schritte zum Setzen der `JAVA_HOME` Umgebungsvariable
**Öffnen Sie die Systemeigenschaften:**
    - Klicken Sie mit der rechten Maustaste auf das Windows-Symbol und wählen Sie „System“.
    - Klicken Sie auf „Erweiterte Systemeinstellungen“ auf der linken Seite.
**Zugriff auf Umgebungsvariablen:**
    - Im Systemeigenschaften-Fenster, gehen Sie zum Tab „Erweitert“.
    - Klicken Sie auf den Button „Umgebungsvariablen“.
**Setzen der `JAVA_HOME` Variable:**
    - Unter „Systemvariablen“, klicken Sie auf „Neu“.
    - Geben Sie `JAVA_HOME` als Variablennamen und `C:\Program Files\Java\jdk-17` (oder Ihren Installationspfad) als Variablenwert ein.
    - Klicken Sie auf OK.
**Aktualisieren des Systempfades:**
    - In den „Systemvariablen“, suchen Sie die Variable `Path` (oder `PATH`) und wählen Sie sie aus.
    - Klicken Sie auf „Bearbeiten“.
    - Klicken Sie auf „Neu“ und fügen Sie `%JAVA_HOME%\bin` hinzu.
    - Klicken Sie auf OK.
 **Übernehmen der Änderungen:**
    - Klicken Sie in allen offenen Fenstern auf OK, um die Änderungen zu übernehmen.

4. ### Überprüfen der Installation:
- Öffnen Sie ein neues Kommandozeilenfenster (nicht das bereits geöffnete, da dieses die Änderungen noch nicht übernommen hat).
- Geben Sie `java -version` ein, um sicherzustellen, dass Java korrekt installiert ist und die Version anzeigt.

5. ### Spring Initializr (webbasiertes Tool, das das Erstellen von Spring Boot-Projektstrukturen erleichtert.)
Öffnen Sie Spring Initializr: Gehen Sie zu [start.spring.io](https://start.spring.io).
Projektdetails angeben:
- Wählen Sie als Projekt Maven.
    - Wählen Sie als Sprache Java.
    - Wählen Sie die Spring Boot-Version, die Sie verwenden möchten (z.B. 3.1.6).
    - Geben Sie Gruppen- und Artefakt-IDs an, die Ihr Projekt identifizieren.
    - Wählen Sie Java 17 als Java-Version.
Spring Boot-Abhängigkeiten für dieses Projekt empfohlen:
    - **Spring Web:** Ermöglicht das Bauen von Web-Anwendungen, einschließlich RESTful-Anwendungen, mithilfe von Spring MVC.
    - **Spring Data JPA:** Vereinfacht den Datenzugriff im Kontext von JPA.
    - **MariaDB JDBC Treiber:** Eine JDBC-Erweiterung, die die Konnektivität mit der MySQL-Datenbank ermöglicht. Wählen Sie die Abhängigkeit `mysql-connector-java`.
    - **Spring Security:** Für automatische Authentifizierung.
    - **Spring Boot DevTools:** Bietet nützliche Features wie automatisches Neuladen von Codeänderungen.

6. ### Erstellen eines neuen Angular-Projekts

- Erstellen Sie an einer beliebigen Stelle einen Projektordner.
- Öffnen Sie diesen in Ihrer IDE.
- Erstellen Sie darin zwei weitere Ordner: `frontend` und `backend`.
- Im Ordner `backend` wird der Download von Spring Boot extrahiert.

7. ### Anleitung zur Installation der Angular CLI und Einrichtung eines Angular-Projekts

- Voraussetzungen
- Installiertes Node.js.

- Schritte

 **Node.js installieren:** Falls noch nicht geschehen, besuchen Sie die [Node.js Download-Seite](https://nodejs.org/) und laden Sie die neueste LTS-Version herunter und installieren Sie diese.

  **Öffnen eines Terminals in VS Code:**
    - Starten Sie Visual Studio Code.
    - Öffnen Sie ein neues Terminal über das Menü oder die integrierte Terminal-Option.

 **Installation der Angular CLI:**
    - Führen Sie den folgenden Befehl aus, um die Angular CLI global zu installieren:
      ```bash
      npm install -g @angular/cli
      ```

 **Einrichtung der Authentifizierung mit Auth0:**
    - Fügen Sie die folgende Abhängigkeit in Ihre `pom.xml` ein, um Auth0 (JWT-Token) zu verwenden:
      ```xml
      <dependency>
        <groupId>com.auth0</groupId>
        <artifactId>java-jwt</artifactId>
        <version>3.18.2</version>
      </dependency>
      ```

8. ### Erstellen eines neuen Angular-Projekts

 *Projektverzeichnis auswählen:**
    - Navigieren Sie im Terminal zu dem übergeordneten Verzeichnis Ihres Spring Boot-Projekts oder einem anderen gewünschten Ort.
 **Angular-Projekt erstellen:**
    - Führen Sie den folgenden Befehl aus, um ein neues Angular-Projekt zu erstellen:
      ```bash
      ng new mein-angular-projekt
      ```
      Ersetzen Sie `mein-angular-projekt` mit Ihrem gewünschten Projektnamen.
  **Projektkonfiguration:**
    - Folgen Sie den Anweisungen im Terminal, um das Projekt zu konfigurieren, einschließlich Routing und CSS-Optionen.

9. ### Öffnen des Angular-Projekts in Ihrer IDE (Bspw. VS Code)

  **Projekt in VS Code öffnen:**
    - Wechseln Sie in VS Code zum Angular-Projektordner.
    - Sie können dies tun, indem Sie das aktuelle Fenster schließen und den neuen Projektordner öffnen oder den Angular-Projektordner zum aktuellen Workspace hinzufügen.

10. ### Starten und Testen des Angular-Projekts

   **Terminal im Projektordner öffnen:**
    - Öffnen Sie ein neues Terminal in VS Code im Angular-Projektordner.

  **Projekt starten:**
    - Führen Sie den Befehl `ng serve` aus, während Sie sich im Projektverzeichnis befinden, das mit `ng new mein-angular-projekt` erstellt wurde.

   **Projekt im Browser testen:**
    - Öffnen Sie einen Webbrowser und navigieren Sie zu `http://localhost:4200`, um zu überprüfen, ob das Angular-Projekt erfolgreich läuft.

Nach Abschluss dieser Schritte sollten Sie ein lauffähiges Angular-Projekt in Ihrer Entwicklungsumgebung haben.

11. ### Datenbank erstellen: 

Neue Datenbank erstellen:**
- Verwenden Sie PHPMyAdmin, um eine neue Datenbank zu erstellen. 
- Hinzufügen der MySQL-Abhängigkeit in Maven
  
**Bearbeiten der `pom.xml`:**
    - Fügen Sie die folgende Abhängigkeit in Ihre `pom.xml`-Datei ein, um MySQL mit Ihrem Spring Boot-Projekt zu verbinden:
      ```xml
      <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.23</version> <!-- oder eine andere kompatible Version -->
      </dependency>
      ```

12. ### Konfigurieren der Datenbankverbindung in `application.properties`
  
**Bearbeiten der `application.properties`-Datei:**
    - In Ihrer `application.properties`-Datei, die sich im `src/main/resources`-Verzeichnis befindet, fügen Sie die folgenden Konfigurationseinstellungen hinzu:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/donauswap
      spring.datasource.username=IHR USERNAME
      spring.datasource.password=IHR PASSWORT
      spring.jpa.hibernate.ddl-auto=update      ``` 

Nachdem Sie diese Schritte abgeschlossen haben, sollte Ihr Spring Boot-Projekt erfolgreich mit Ihrer MySQL-Datenbank verbunden sein.

13. ### mitgelieferte Beispieldaten vorbereiten:
- Erstellen Sie einen Ordner uploaded_images auf der selben Ebene wie JAV41, aber außerhalb der in der IDE geöffneten Ordner. Kopieren Sie die Inhalte von frontend/assets/images/offers in uploaded_images.
- Erstellen Sie einen weiteren Ordner categories auf der selben Ebene wie JAV41, aber außerhalb der in der IDE geöffneten Ordner. Kopieren Sie die Inhalte von frontend/assets/images/category in categories.
14. ### mitgelieferte Beispieldaten einspielen:
- Sie können nun die mitgelieferten Beispieldaten einspielen. Sie liegen direkt unter JAV41 im Ordner ProjectExampleDatabase
15. ### Anwendung starten: 

- Starten der Anwendung. Öffnen Sie ein Terminal geben sie nacheinander folgende Befehle ein:

- cd frontend
- cd donauswap
- .\mvnw clean install
- .\mvnw.cmd spring-boot:run 

- Öffnen Sie ein Terminalfenster geben Sie nacheinander folgende Befehle ein: 
- cd frontend
- cd donauswap
- ng serve
Ihr localhost ist : http://localhost:4200/
