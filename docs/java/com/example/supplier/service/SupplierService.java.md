# SupplierService.java: Lieferantenmanagement-Service

## Übersicht
Die Klasse `SupplierService` bietet Funktionen zur Verwaltung von Lieferanten. Sie ermöglicht das Erstellen, Abrufen, Aktualisieren und Löschen von Lieferanten sowie die Validierung von Lieferantendaten, insbesondere der CNPJ (Cadastro Nacional da Pessoa Jurídica), einer brasilianischen Unternehmensidentifikationsnummer.

## Prozessfluss
```mermaid
flowchart TD
    Start("Start")
    CreateSupplier["createSupplier(Supplier supplier)"]
    ValidateCNPJ{"Ist CNPJ gültig?"}
    SaveSupplier["Speichern des Lieferanten"]
    GetAllSuppliers["getAllSuppliers()"]
    GetSupplierById["getSupplierById(Long id)"]
    UpdateSupplier["updateSupplier(Long id, Supplier supplierDetails)"]
    FindSupplierById{"Lieferant gefunden?"}
    UpdateDetails["Aktualisierung der Lieferantendetails"]
    DeleteSupplier["deleteSupplier(Long id)"]
    End("Ende")

    Start --> CreateSupplier
    CreateSupplier --> ValidateCNPJ
    ValidateCNPJ -->|Ja| SaveSupplier
    ValidateCNPJ -->|Nein| End
    Start --> GetAllSuppliers
    Start --> GetSupplierById
    Start --> UpdateSupplier
    UpdateSupplier --> ValidateCNPJ
    ValidateCNPJ -->|Ja| FindSupplierById
    ValidateCNPJ -->|Nein| End
    FindSupplierById -->|Ja| UpdateDetails
    UpdateDetails --> SaveSupplier
    FindSupplierById -->|Nein| End
    Start --> DeleteSupplier
    DeleteSupplier --> FindSupplierById
    FindSupplierById -->|Ja| End
    FindSupplierById -->|Nein| End
```

## Erkenntnisse
- Die Klasse validiert die CNPJ eines Lieferanten vor dem Speichern oder Aktualisieren, um sicherzustellen, dass die Daten korrekt sind.
- Es wird eine Ausnahme ausgelöst, wenn die CNPJ ungültig ist oder ein Lieferant nicht gefunden wird.
- Die Klasse verwendet das Repository `SupplierRepository`, um Datenbankoperationen durchzuführen.
- Die Methode `deleteSupplier` gibt einen booleschen Wert zurück, um den Erfolg des Löschvorgangs zu bestätigen.
- Die Klasse ist stark von der Validierungslogik in der Hilfsklasse `CodigoUtil` abhängig.

## Abhängigkeiten
```mermaid
flowchart LR
    SupplierService --- |"Interacts"| SupplierRepository
    SupplierService --- |"Uses"| CodigoUtil
    SupplierService --- |"References"| Supplier
```

- `SupplierRepository`: Wird für Datenbankoperationen wie Speichern, Abrufen, Aktualisieren und Löschen von Lieferanten verwendet.
- `CodigoUtil`: Validiert die CNPJ eines Lieferanten.
- `Supplier`: Datenmodell, das die Lieferantendetails wie Name, CNPJ, Kontaktname, E-Mail und Telefonnummer enthält.
