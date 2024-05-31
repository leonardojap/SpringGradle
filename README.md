# Spring Gradle (https / SOAP)

## Description

This is a base project, configured to do services  HTTPS and SOAP

## Requirements

- Java 17
- Gradle

## Config

Configure your DB with the DDL.sql file

## How to run it

- clone this repositorie:

```bash
git clone https://github.com/leonardojap/SpringGradle.git
cd SpringGradle
```

- Execute theese comands

```bash
./gradlew compileJava
./gradlew bootRun
```

- To run test:

```bash
./gradlew test
```

## Services

- Create Product
```bash
POST
http://localhost:3000/product
Body:

{
  "internalProductId": "INT-PROD-1234",
  "internalName": "My Internal Product",
  "externalProductId": 12345,
  "internalPartId": "INT-PART-567",
  "externalPartId": "EXT-PART-890",
  "productAttributeName": "Color",
  "productAttributeValue": "Blue"
}

Response: 

{
    "data": {
        "id": 2,
        "internalProductId": "INT-PROD-1234",
        "internalName": "My Internal Product",
        "externalProductId": 12345,
        "externalName": null,
        "externalDescription": null,
        "created": "2024-05-31T14:41:17.420057",
        "deleted": false,
        "parts": null
    },
    "success": true,
    "message": "Create Product"
}
```

- Get Product by Id
```bash
GET
http://localhost:3000/product/1
Body:

Response:

{
  "data": {
    "id": 1,
    "internalProductId": "external",
    "internalName": "external",
    "externalProductId": 1035,
    "externalName": "external",
    "externalDescription": null,
    "created": "2024-04-05T14:07:33",
    "deleted": true,
    "parts": [
      {
        "id": 1,
        "internalPartId": "HAT5GREEN",
        "externalPartId": "1035GRN",
        "externalCountryOfOrigin": null,
        "externalPrimaryMaterial": null,
        "externalLeadTime": null,
        "productId": 1,
        "attributes": [
          {
            "name": "ftz eligible",
            "id": "2",
            "value": "120329.24324 compliant"
          },
          {
            "name": "epa certified",
            "id": "1",
            "value": "no"
          }
        ]
      }
    ]
  },
  "success": false,
  "message": "Product Found"
}
```


