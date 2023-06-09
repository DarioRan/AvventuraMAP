# Avventura testuale "Escape From Lab"

## Descrizione del caso di studio

Il caso di studio consiste in un'applicazione Java che simula un'avventura testuale all'interno di un misterioso laboratorio creata da Dario Ranieri, Julian Pajo e Emanuele Romito nell’ambito del corso di Metodi Avanzati di Programmazione tenuto dal prof. Pierpaolo Basile.

L'obiettivo del giocatore è quello di scappare dal laboratorio risolvendo enigmi, collezionando oggetti utili e interagendo con l'ambiente circostante.

Il laboratorio è ricco di stanze intriganti, ciascuna con la propria descrizione e caratteristiche uniche. Il giocatore dovrà esplorare attentamente ogni stanza, raccogliere oggetti utili e svelare segreti nascosti. Gli oggetti raccolti potrebbero essere fondamentali per risolvere enigmi o superare ostacoli lungo il percorso di fuga.

Per progredire nel gioco, il giocatore dovrà utilizzare il proprio ingegno e la capacità di analizzare gli indizi presenti nelle descrizioni delle stanze e degli oggetti. Potrebbero esserci indizi sottili o rompicapi da risolvere per scoprire nuove informazioni o accedere a stanze precedentemente bloccate.

## Stile architetturale scelto
Lo stile architetturale scelto può essere considerato un'implementazione del design pattern Remote Facade. Il Remote Facade è un design pattern strutturale che fornisce un'interfaccia semplificata per l'interazione con un sottosistema complesso, nascondendo i dettagli di implementazione e semplificando l'uso del sistema remoto.

Nel caso dell'architettura descritta, il Server svolge il ruolo di Remote Facade. Esso espone un'interfaccia semplice per i client, consentendo loro di inviare comandi e ricevere risorse di gioco. Il Server gestisce l'elaborazione delle richieste dei client, avvia nuove istanze di gioco e coordina la comunicazione tra il client e l'Engine.

## Diagramma delle classi

```mermaid
classDiagram

    class DBManager {
        - conn: Connection
        + initTables()
        + getGameStatus(username: String): GameStatus
        + insertNewGameStatus(gamestatus: GameStatus): int
        + getAllSavedGame(): List<GameStatus>
    }

    class GameStatus {
        - username: String
        - lastRoomId: Integer
        - inventory: List[Integer]
        - time: LocalDateTime
        + getters and setters
    }


    
    class EscapeFromLabGame {
        - initCommands(): void
        - initRooms(): void
        - commands of direction
        - commands for interaction with objects
        - checkInventory(): String
        - checkRoom(): String
        - nextMove(p: ParserOutput): String
    }
    
    class AdvObject {
        - id: int
        - name: String
        - description: String
        - alias: Set&lt;String&gt;
        + getters and setters
    }

    class AdvObjectContainer {
         + add()
    }


    class CommandGUIOutput {
        - type : CommandGUIType
        - text : String
        - pathResource : String
    }


    class Room {
        - id: int
        - name: String
        - description: String
        - objects: List&lt;AdvObject&gt;
        + getters and setters
    }


    class Engine {
        + loadGame(username: String): void
        + saveGame(username: String): void
        + execute(): CommandGUIOutput
        + executeCommand(command: String): CommandGUIOutput
    }

    class GameDescription {
        <<Abstract>>
        - rooms: List[Room]
        - commands: List[Command]
        - inventory: List[AdvObject]
        - objectsInGame: List[AdvObject]
        - currentRoom: Room 
        + init(): void
        + nextMove(p: ParserOutput): String
        + getters and setters
    }

    class Parser {
        - stopwords: Set&lt;String&gt;
        + checkForCommand(token: String, commands: List&lt;Command&gt;): int
        + checkForObject(token: String, objects: List&lt;AdvObject&gt;): int
        + checkForAuxText(tokens: List&lt;String&gt;): String
        + parse(command: String, commands: List&lt;Command&gt;), objects: List&lt;AdvObject&gt;), inventory: List&lt;AdvObject&gt;)): ParserOutput
    }

    class AdventureGameGUI {
        - components of gui 
        + performCommand(command: CommandGUIOutput): void
        + appendAreaText(text: String): void
        + printHelp(): String
        + startGame(): void
        + startLoadedGame(id: int): void
        + loadGame(): void
        + showSavedGames(): void
        + die(string: String): void
    }

    class Client{
        - socket: Socket
        + executeCommand(command: String): CommandGUIOutput 
        + getResourceFromServer(command: String): Object
        + sendResourcesToServer(resource: String)
    }

    class Server{
        - clientSocket: Socket
        - serverSocket: ServerSocket 
        + start()
    }
    
    class PluginableClient{
        <<interface>>
    getResourcesFromServer(String) : T
    sendResourcesToServer(String)
    closeConnection()
    }
    
    class PluginableServer {
        <<interface>>
         sendCommand(CommandGUIOutput)
         start()
         setEngine(Engine);
    }
    
    class ParserOutput {
        - command Command
        - object: AdvObject
        - invObject: AdvObject
        - aux_text: String
    }

    Client ..|> PluginableClient : implements
    AdventureGameGUI -- PluginableClient 
    Client "1" ..> "1"  CommandGUIOutput : comunica
    Server ..> CommandGUIOutput
    Engine "1" -- "1" Server : comunica
    Engine ..> Parser
    Engine ..> ParserOutput
    Engine "1" --> GameDescription
    Engine ..|> PluginableServer
    Server ..|> PluginableServer : implements
    EscapeFromLabGame ..> ParserOutput
    EscapeFromLabGame ..|> GameDescription
    GameDescription "1" -- "*" Room
    Room "1" -- "*" AdvObject
    AdvObjectContainer --|> AdvObject
    Engine "1" ..> "1" DBManager
    Engine "1" --> "1" GameStatus
    DBManager "1" --> "1" GameStatus


direction RL
```
Come scelta progettuale abbiamo deciso di non includere nel diagramma UML:
- Metodi Get() e Set()
- Costruttori
- Attributi e metodi ritenuti di rilevanza marginale per ridurre la complessità del diagramma


## Descrizione diagramma delle classi

- Engine: è il cuore del gioco e gestisce l'esecuzione complessiva del gioco. È responsabile di inizializzare e mantenere lo stato del gioco, gestire l'interazione tra le diverse componenti e fornire le funzionalità di base del gioco come il caricamento e il salvataggio dei giochi, l'esecuzione dei comandi e la gestione dell'output. L'Engine comunica con altre componenti come il Parser, la GameDescription e il DBManager per ottenere le informazioni necessarie e controllare il flusso del gioco.

- GameDescription: La classe GameDescription rappresenta la descrizione completa del gioco, inclusi i dettagli sulle stanze, i comandi disponibili, l'inventario e gli oggetti nel gioco. È una classe astratta che può essere estesa per creare giochi specifici, nel nostro caso EscapeFromLabGame. La GameDescription viene inizializzata dall'Engine all'avvio del gioco e fornisce metodi per gestire le azioni dei giocatori, come la gestione delle mosse e la gestione delle interazioni con l'ambiente di gioco.

- Client e Server: Il Client e il Server gestiscono la comunicazione tra il gioco e gli utenti in un ambiente di rete locale. Il Client è responsabile di inviare i comandi al Server e di ricevere le risorse necessarie per l'esecuzione del gioco. Il Server gestisce le richieste dei client, avvia nuove istanze di gioco e coordina la comunicazione tra i client e il motore di gioco.

- AdventureGameGUI: L'AdventureGameGUI è responsabile dell'interfaccia utente grafica del gioco. Implementata in JSwing, fornisce i componenti grafici necessari per l'interazione con il giocatore, come pulsanti, aree di testo e finestre di dialogo. Comunica con l'Engine per inviare comandi e ricevere output da visualizzare all'utente. È in grado di gestire azioni come l'esecuzione dei comandi, l'aggiornamento dell'output e la gestione degli eventi dell'interfaccia utente.

- DBManager: Il DBManager gestisce la connessione al database e fornisce funzionalità per inizializzare le tabelle necessarie, ottenere lo stato di gioco di un utente, inserire nuovi stati di gioco e recuperare tutti i giochi salvati. Comunica con l'Engine per fornire le informazioni sullo stato di gioco richieste e per memorizzare i nuovi stati di gioco.

- Parser: Il Parser è responsabile di analizzare i comandi inseriti dal giocatore e di interpretarli in un formato comprensibile per l'Engine. Utilizzando algoritmi di parsing e tokenizzazione, il Parser estrae informazioni importanti dai comandi, come i verbi e gli oggetti, e li trasforma in una rappresentazione strutturata per l'esecuzione del gioco. Comunica con l'Engine per fornire l'output del parsing.

- EscapeFromLabGame: Implementa la logica del gioco "EscapeFromLab", con metodi per inizializzare i comandi, le stanze e gestire le azioni dei giocatori come spostarsi, interagire con gli oggetti, controllare l'inventario e la stanza corrente.

- Room: Rappresenta una stanza nel gioco, con attributi come ID, nome, descrizione e una lista di oggetti presenti nella stanza. Fornisce anche metodi per accedere agli attributi.

- AdvObject: Rappresenta un oggetto nel gioco, con attributi come ID, nome, descrizione e alias. Fornisce anche metodi per accedere agli attributi.

- AdvObjectContainer: Rappresenta un contenitore di oggetti nel gioco. Estende AdvObject

- GameStatus: Rappresenta lo stato di gioco di un utente, includendo informazioni come il nome utente, l'ultimo ID della stanza, l'inventario e il tempo di gioco.

- CommandGUIOutput: Rappresenta l'output di un comando nell'interfaccia utente grafica del gioco. Viene utilizzata per comunicare tra Client e AdventureGameGUI.

- PluginableClient : Interfaccia in cui sono dichiarati i metodi fondamentali per far si che ci sia una comunicazione con il Server. E' stato chiamato Pluginable poichè se in futuro si volesse implementare la comunicazione tra il Client e il Server in maniera diversa, questo lo si può fare semplicemente cambiando il modo in cui viene istanziato PluginableClient con la nuova classe concreta.

- PluginableServer : Interfaccia in cui sono dichiarati i metodi fondamentali per far si che il gioco rimanga in attesa di una richiesta del Client. Così come per PluginableClient, quest'archietettura fa si che cambiare l'implementazione della comunicazione con il Client comporterebbe semplicemente cambiare il modo in cui viene istanziata PluginableServer con la nuova classe concreta.


## Implementazione argomenti del corso

- Thread: L'architettura del gioco utilizza thread per gestire il timer e la progressione delle radiazioni all'interno del gioco. Un thread è dedicato al timer e viene utilizzato per aggiornare la JProgressBar della GUI, che rappresenta visivamente il livello delle radiazioni. Allo stesso tempo, il client utilizza un altro thread che rimane in ascolto per rilevare l'incremento del Timer e agire di conseguenza. Questa struttura permette di gestire in modo efficiente il timer e la progressione delle radiazioni nel gioco, fornendo un'esperienza di gioco fluida e reattiva.

- JBDC: Nell'architettura del gioco, si è fatto uso di JDBC (Java Database Connectivity) per la gestione dei salvataggi. Questo permette di salvare lo stato di gioco, includendo informazioni come l'ultima stanza visitata, l'inventario del giocatore e il tempo trascorso nel gioco.

- Socket: Nell'architettura del gioco, si è utilizzato il concetto di socket per implementare un'architettura client-server. Nel contesto del gioco, il server e il client si comunicano tra loro utilizzando socket per scambiare dati e comandi.

- File: Nell'architettura del gioco, i file vengono utilizzati per caricare le descrizioni e i titoli delle stanze. Ogni stanza può avere una descrizione testuale che fornisce dettagli sul suo ambiente, insieme a un titolo che ne identifica posizione nel gioco.

- SWING: Nel progetto del gioco, si è utilizzata Java Swing per creare un'interfaccia grafica interattiva e consentire all'utente di interagire con il gioco. Sono state create diverse finestre e componenti grafici per visualizzare le informazioni di gioco e fornire mezzi per l'input dell'utente.

- lambda expression: Le lambda expression si sono utilizzate per ottenere una sintassi concisa. Ci ha permesso di scrivere funzioni anonime in modo più conciso rispetto all'utilizzo delle classi interne o delle classi anonime per rendere il codice più leggibile e per ridurre la quantità di codice di supporto necessario.
  Vengono utilizzate ad esempio nel controllo della presenza di un oggetto dell'inventario nella lista degli oggetti del gioco, o in metodi relativi agli action listener della GUI.

- Interfacce: Sono state utilizzate le interfacce per realizzare PluginableClient e PluginableServer in funzione di una possibile scalabilità del modo in cui avviene la comunicazione Client-Server. Il vantaggio delle interfacce risiede nel fatto che in futuro non verrà richiesta ingente variazione del codice, ma solo una vaziazione del modo in cui vengono implementate.

- Generics: Le generics si sono utilizzate per generalizzare la richiesta di risorse dal Server da parte del Client.

## Manuale Utente

### Introduzione
Ti trovi all'interno di un laboratorio scientifico avanzato, ma purtroppo, una catastrofe è imminente.
Il laboratorio è stato avvolto da nubi radioattive che si stanno espandendo rapidamente.
Il tuo obiettivo principale è quello di trovare un modo per uscire dal laboratorio il prima possibile e metterti in salvo.

Per avviare il programma, è necessario eseguire le seguenti operazioni:

- Avvia il server lanciando l'esecuzione dell'Engine. L'Engine si occuperà di creare e gestire il server per la comunicazione client-server. Questo ti permetterà di stabilire una connessione e scambiare dati tra il client e il server.

- Successivamente, avvia il client eseguendo l'AdventureGameGUI. Questa classe rappresenta l'interfaccia grafica del gioco, che permette all'utente di interagire con il sistema. Il client si connetterà al server precedentemente avviato e consentirà all'utente di giocare all'avventura testuale.

### Istruzioni di Gioco

Il gioco utilizza un sistema di riferimento relativo per la navigazione.
L'ambiente è suddiviso in stanze, ognuna delle quali è collegata ad altre stanze in base alla posizione cardinale.
Ad esempio, se ti sposti ad ovest da una stanza, per tornare indietro dovrai utilizzare il comando "sud", lo stesso
vale se ti sposi a nord, dovrai utilizzare il comando "sud" per tornare indietro.

### Comandi di Navigazione
- `nord` oppure `N` - Ti sposti verso nord nella stanza successiva.
- `sud` oppure `S`- Ti sposti verso sud nella stanza precedente.
- `est` oppure `E`- Ti sposti verso est nella stanza adiacente.
- `ovest` oppure `O` - Ti sposti verso ovest nella stanza adiacente.

### Comandi dell'Inventario
- `inventario` oppure `INV` - Mostra l'inventario degli oggetti che hai raccolto.
- `prendi [nome oggetto]` - Raccoglie un oggetto presente nella stanza e lo aggiunge al tuo inventario.

### Comandi di Interazione con gli Oggetti
- `accendi [nome oggetto]` - Accende un oggetto che può essere acceso, come una luce o un interruttore.
- `spegni [nome oggetto]` - Spegne un oggetto che è stato acceso in precedenza.
- `sblocca [nome oggetto] "[password]"` - Sblocca un oggetto che è stato chiuso o bloccato.
- `apri [nome oggetto]` - Apre un oggetto che può essere aperto, come una cassa.
- `usa [nome oggetto]` - Utilizza un oggetto che può essere utilizzato, come un computer.

### Comandi di Interazione con le Stanze
- `osserva` - Osserva la stanza in cui ti trovi e mostra una descrizione degli oggetti al suo interno.

### Comando di Aiuto
- `help` - Mostra l'elenco dei comandi disponibili.

### Comando di Fine Gioco
- `muori` - Termina il gioco.

## Mappa di gioco![mappagioco.jpg](resources%2Fmappagioco.jpg)

## Soluzione del gioco

- (E,E)
- SPEGNI CISTERNA
- (S,S,N)
- PRENDI TORCIA
- (S,O,O)
- ACCENDI TORCIA
- PRENDI CHIAVE
- (S,S,E,O)
- APRI CASSETTA
- PRENDI MARTELLO
- (S,N,N)
- ACCENDI GENERATORE
- (S,S,S,N,O,N,N)
- PRENDI POSTIT
- (E)
- PRENDI BRACCIALE
- (S,O)
- APRI CASSETTO
- PRENDI PEZZO DI CARTA
- (S,N)
- PRENDI PALMARES
- SBLOCCA PALMARES "12311loco11721"
- USA PALMARES
- (S,S,S,S,N,N)
- PRENDI YELLOWKEYCARD
- (S,E,E,N,N,E,N,N)
- PRENDI REDKEYCARD
- (S,S,S,S,S,S,S,O,N,N)
- PRENDI TELECOMANDO
- SBLOCCA TELECOMANDO "3215"
- USA TELECOMANDO
- (S,O)
- APRI SARACINESCA



## Specifica algebrica di _Lista_
### SPECIFICA SINTATTICA
- **sorts** :
  - Object
  - int
  - boolean
  - List


- **operations** :
  - new() &rarr; List
  - add(List,Object) &rarr; List
  - indexOf(List,Object) &rarr; int
  - size(List) &rarr; int
  - isEmpty(List) &rarr; boolean
  - contains(List,Object) &rarr; boolean
  - removeElement(List,Object) &rarr; List
  - equals(List,List) &rarr; boolean
  - containsList(List,List) &rarr; boolean

### SPECIFICA SEMANTICA
**declare**:
- l : List&lt;Object&gt;
- i : int
- {true, false} : boolean
- obj : Object

![spec1](resources%2Fspec1)

- removeElement( new() , obj ) &rarr; new()
- removeElement( add(l,obj ) , obj) &rarr; l
- size( new() ) &rarr; 0
- size( add(l , obj) ) &rarr; size(l) + 1
- indexOf( new() , obj ) &rarr; -1
- indexOf( add( l , obj ) , obj) &rarr; size(l)
- contains( new() , obj ) &rarr; false
- contains( add( l , obj ) , obj) &rarr; true
- isEmpty( new() ) &rarr; true
- isEmpty( add(l , obj ) ) &rarr; false


**declare**:
- l : List&lt;Object&gt;
- k : List&lt;Object&gt;
- {true, false} : boolean
- obj1 : Object
- obj2 : Object
  
**equals**(k’,l’) // le List hanno gli stessi elementi, non perforza in ordine

![spec2](resources%2Fspec2)

- equals( new() , new() ) &rarr; true
- equals( new() , add( l , obj1 ) ) &rarr; false
- equals( add( k , obj2 ) , new() ) &rarr; false
- equals( add( k , obj2 ) , add( l , obj1 ) ) &rarr; <br>
**if** obj1 = obj2 **then** <br>
  equals(k, l) <br> **else** false

**declare**:
- l : List&lt;Object&gt;
- k : List&lt;Object&gt;
- {true, false} : boolean
- obj1 : Object
- obj2 : Object

**containsList**(k’,l’) // k’ contenuto in l’

![spec3](resources%2Fspec3)

- containsList( new() , new() ) &rarr; true
- containsList( new() , add( l , obj1 ) ) &rarr; false
- containsList( add( k , obj2 ) , new() ) &rarr; false
- containsList( add( k , obj2 ) , add( l , obj1 ) ) &rarr; <br>
**if** contains( k', obj1 ) **then** <br>
  containsList(k',l) <br>
**else** false

