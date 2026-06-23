# Abilet

Etkinlik bilet satış API'si. Spring Boot 4.1 / Java 25.

## Teknolojiler
- Spring Boot (Web MVC, Data JPA, Validation, Security)
- PostgreSQL (prod) / H2 (dev, test)
- Flyway (şema migration — prod)
- MapStruct (DTO ↔ entity dönüşümü)
- springdoc-openapi (Swagger UI)
- Testcontainers + Mockito (test)

## Mimari
Package-by-feature. Her özellik kendi katmanlarını barındırır:
`controller / service / repository / dto / mapper / exception / entity`.
Ortak parçalar `common` altında (`BaseEntity`, `GlobalExceptionHandler`, `ErrorResponse`).

- **Soft-delete:** tüm entity'ler `BaseEntity.deletedAt` taşır; `@SQLRestriction` ile
  silinmiş kayıtlar sorgulardan dışlanır. Benzersizlik aktif kayıtlar için
  partial unique index ile DB seviyesinde uygulanır.
- **Auditing:** `createdAt/updatedAt` ve `createdBy/updatedBy` (JWT sonrası dolacak).

## Çalıştırma

### Gereksinimler
- JDK 25, Docker (PostgreSQL ve Testcontainers için)

### Geliştirme (H2, varsayılan profil `dev`)
```bash
./mvnw spring-boot:run
```
- H2 console: http://localhost:8080/h2-console
- Swagger UI: http://localhost:8080/swagger-ui.html

### Production (PostgreSQL + Flyway)
```bash
cp .env.example .env   # değerleri doldurun
docker compose up -d   # PostgreSQL
SPRING_PROFILES_ACTIVE=prod ./mvnw spring-boot:run
```
Ortam değişkenleri: `DB_NAME`, `DB_USER`, `DB_PASSWORD`, `DB_HOST`, `DB_PORT` (bkz. `.env.example`).

## Test
```bash
./mvnw test     # unit + slice testleri (Docker yoksa Testcontainers testleri atlanır)
./mvnw verify   # tümü
```

## API
Taban yol: `/api/v1`
- `POST /auth/register` — kullanıcı kaydı
- `GET|PUT|DELETE /users[/{id}]` — kullanıcı (listede pagination: `?page=&size=&sort=`)
- `GET|POST|PUT|DELETE /categories[/{id}]` — kategori

> Not: JWT henüz eklenmedi; `login` ve endpoint yetkilendirmesi sonraki aşamada.
