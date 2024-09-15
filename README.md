# Getting Started

### Start MSSQL Server Container
Install Docker Desktop, then run docker compose:
```
# Windows
cd src\main\docker

docker-compose -f .\docker-compose.yml up -d
```

### Setup DB
1. Connect using SQL Server Management Studio

<img src="img/connect_db.png" width="300">

2. Create "mbb_assessment" database

<img src="img/create_database.png" width="600">

### Start Application
1. Open mbb-java project in IntelliJ IDEA, then click `MbbJavaApplication`
 
<img src="img/start_application.png" width="600">

### Import Postman Collection
1. Import postman_collection.json

<img src="img/import_collection.png" width="300">
