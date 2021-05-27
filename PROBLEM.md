 online retailer with several delivery partners in over 5 major cities. They help accept orders through their online portal or through the mobile app, and manages the inventory. The delivery partners are responsible for pickup and delivery through their logistics application.

## Proposed Solution
As part of the solution, has  three apps:
1. The app available on PlayStore / AppStore for consumers to browse restaurants, their menus and place orders
2. The app that is installed on Delivery Manager's mobile phone, which is used for receiving and accepting new orders
3.  The app used by delivery agent to pick up delivery from the warehouse, and deliver to the given addressFor the first milestone, Flippy is looking to build the backend APIs for the three apps as three different microservices, and needs your help to implement and containerize the application.

## Specification
- https://miro.com/app/board/o9J_kqqCJZ8=/
- [OpenApi Spec](./openapi.yaml)

## Milestones
- **Step 1:** Create three projects, one each for microservice "Flippy API","Flippy Delivery Partner API", "Flippy Delivery Agent API"
- **Step 2:** Setup Kubernetes Manifest files, to deploy each microservicein a different namespace. Also configure a separate database deployment for each microservice. You may choose to use NoSQL database of your choice (preferably MongoDB; also accepted Cassandra, DynamoDB, CosmosDB, Cloud Firestore)
- **Step 3:** Implement the APIs, implement JWT Authorization, and ensure that the APIs are working with OpenAPI (Refer Specifications)
- **Step 4:** Implement Messaging (Kafka), to synchronize Order data between the three microservices (As per the specification)
