# SupplierController.java: Verwaltung von Lieferanten-APIs

## Übersicht

Die `SupplierController`-Klasse stellt REST-APIs für die Verwaltung von Lieferanten bereit. Sie ermöglicht das Abrufen, Erstellen, Aktualisieren und Löschen von Lieferantendaten. Die Klasse ist ein zentraler Bestandteil der Lieferantenverwaltung und interagiert mit der `SupplierService`-Schicht, um die Geschäftslogik auszuführen.

## Prozessablauf

```mermaid
flowchart TD
    Start("Anfrage an API") --> A["GET /api/suppliers"]
    A --> |"Alle Lieferanten abrufen"| B["Ruft alle Lieferanten aus SupplierService ab"]
    Start --> C["GET /api/suppliers/{id}"]
    C --> |"Lieferant nach ID abrufen"| D{"Lieferant gefunden?"}
    D --> |"Ja"| E["Gibt Lieferantendaten zurück"]
    D --> |"Nein"| F["Gibt 404 Not Found zurück"]
    Start --> G["POST /api/suppliers"]
    G --> |"Neuen Lieferanten erstellen"| H["Erstellt Lieferant über SupplierService"]
    H --> I["Gibt erstellten Lieferanten zurück"]
    Start --> J["PUT /api/suppliers/{id}"]
    J --> |"Lieferant aktualisieren"| K{"Lieferant existiert?"}
    K --> |"Ja"| L["Aktualisiert Lieferantendaten"]
    L --> M["Gibt aktualisierten Lieferanten zurück"]
    K --> |"Nein"| F
    Start --> N["DELETE /api/suppliers/{id}"]
    N --> |"Lieferant löschen"| O{"Löschung erfolgreich?"}
    O --> |"Ja"| P["Gibt 204 No Content zurück"]
    O --> |"Nein"| F
```

## Erkenntnisse

- Die Klasse implementiert CRUD-Operationen (Create, Read, Update, Delete) für Lieferanten.
- Die API-Endpunkte sind REST-konform und verwenden HTTP-Methoden wie `GET`, `POST`, `PUT` und `DELETE`.
- Die Klasse verwendet `ResponseEntity`, um HTTP-Statuscodes wie `200 OK`, `404 Not Found` und `204 No Content` zurückzugeben.
- Die Geschäftslogik wird an die `SupplierService`-Schicht delegiert, was die Trennung von Verantwortlichkeiten fördert.
- Die Klasse unterstützt die Verarbeitung von JSON-Daten durch die Verwendung von `@RequestBody` und `@PathVariable`.

## Abhängigkeiten

```mermaid
flowchart LR
    SupplierController --- |"Interacts"| SupplierService
    SupplierController --- |"Includes"| Supplier
```

- `SupplierService`: Führt die Geschäftslogik für die Verwaltung von Lieferanten aus.
- `Supplier`: Datenmodell, das die Lieferantendaten repräsentiert.
