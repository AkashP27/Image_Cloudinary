# Image Gallery

This repository focuses on image uploading, retrieving and deletion from cloudinary using spring boot application

## Requirements

For development, you will need Sql server, postman and any IDE in your environment.

## Install

#### Step 1: Clone the repository

```bash
git clone https://github.com/AkashP27/Image_Cloudinary.git
```

#### Step 2: Create Cloudinary account

- Visit [cloudinary](https://cloudinary.com/) to get Product Environment Credentials

#### Step 3: Provide the credentials

- Open `src/main/resources/application.properties`
- Change the credentials as per your cloudinary account

#### Step 4: Create Database
- Provide the database credentials in `src/main/resources/application.properties`

#### Step 5: Build and run the app using maven

### You can test the API in postman

[Set postman environment from here](https://www.postman.com/akash-api/workspace/akash-public/environment/16112169-8686f9ff-90bd-4624-9292-e6dedb44f4bc?action=share&creator=16112169&active-environment=16112169-8686f9ff-90bd-4624-9292-e6dedb44f4bc)

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/16112169-3625b656-489d-471d-9191-77ae95d2c34a?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D16112169-3625b656-489d-471d-9191-77ae95d2c34a%26entityType%3Dcollection%26workspaceId%3D9fe04cc0-53c6-4f02-842b-8fe10274477e)