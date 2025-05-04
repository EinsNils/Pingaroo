# Pingaroo

**Pingaroo** ist eine SaaS-Anwendung für **API-Monitoring mit automatischen Benachrichtigungen per E-Mail**. Nutzer können API-Endpoints überwachen, Benachrichtigungsgruppen verwalten und Regeln erstellen, um Alerts gezielt zu versenden. Ideal für Entwickler und Teams, die ihre APIs im Auge behalten möchten.

[![Deploy to Production](https://github.com/EinsNils/Pingaroo/actions/workflows/deploy.yml/badge.svg)](https://github.com/EinsNils/Pingaroo/actions/workflows/deploy.yml)

## 🚀 Features

- Benutzer-Registrierung & Login
- API-Endpoints hinzufügen, bearbeiten, löschen
- Periodisches Monitoring von APIs (Status-Checks)
- Konfigurierbare Benachrichtigungsregeln (z.B. bei Downtime)
- Verwaltung von Empfängergruppen für Benachrichtigungen
- Automatischer Versand von Status-E-Mails
- Dashboard mit Status-Übersicht
- Log der verschickten Benachrichtigungen

---

## 🏗️ Technologie-Stack

### Backend
- **Spring Boot**
- Spring Security (JWT-basierte Authentifizierung)
- Spring Data JPA (Datenbankzugriff)
- JavaMailSender (E-Mail-Versand)
- H2 / PostgreSQL (je nach Umgebung)

### Frontend
- **Angular**
- Angular Material (UI-Komponenten)
- RxJS, HttpClient (API-Integration)

---

## ⚙️ Setup

### 🔥 Backend starten

1. Repository klonen
2. In das Backend-Verzeichnis wechseln
3. Abhängigkeiten installieren und starten:

```bash
./gradlew bootRun
```

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
