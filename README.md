# Paperless

Paperless is a Document Management System designed for archiving documents in a FileStore. It features automatic OCR (Optical Character Recognition), tagging, and full-text search capabilities using ElasticSearch.

## Key Features
- **Document Upload:** Allows users to upload documents, which are automatically processed through OCR and indexed for full-text search in ElasticSearch
- **Search Functionality:** Enables users to perform fuzzy searches in ElasticSearch to find documents
- **Document Management:** Supports operations like updating, deleting, and managing document metadata
- **Document Download:** Allows users to download documents


## Setup
```sh  
$ docker-compose up --build
```

## URLs
- **Frontend:** http://localhost:8080
- **Backend:** http://localhost:8081
- **MinIO:** http://localhost:9001
- **Kibana:** http://localhost:5601
- **RabbitMQ:** http://localhost:9093
