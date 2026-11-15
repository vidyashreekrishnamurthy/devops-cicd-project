# End-to-End DevOps CI/CD Project – Jenkins, Docker, Kubernetes, AWS, Ansible & Monitoring

## Project Overview

This project demonstrates an end-to-end DevOps workflow for building, containerizing, deploying, monitoring, and managing a Java-based Employee Management application.

The project integrates Jenkins CI/CD automation, Maven, Docker, Kubernetes, PostgreSQL, Ansible, Prometheus, and Grafana. AWS S3 and Terraform are also included for artifact storage and infrastructure provisioning.

The application supports employee record creation and retrieval using PostgreSQL.

## Architecture

```mermaid

flowchart TD
    A[GitHub Repository] --> B[Jenkins CI/CD Pipeline]
    B --> C[Maven Build]
    C --> D[ROOT.war]
    D --> E[Docker Image Build]
    E --> F[Kubernetes Deployment]
    F --> G[Java Employee Application]
    G <--> H[PostgreSQL Database]

    I[Ansible Automation] --> J[Container Health Checks]
    J --> G
    J --> H

    K[cAdvisor] --> L[Prometheus]
    L --> M[Grafana Dashboards and Alerts]

    N[Future Integration: Terraform] -.-> O[Future Integration: AWS S3]


## Technologies Used

| Technology | Purpose |
|---|---|
| Java | Application development |
| Apache Maven | Build and packaging |
| Apache Tomcat | Application server |
| Jenkins | CI/CD automation |
| Git & GitHub | Source code management |
| Docker | Containerization |
| Kubernetes | Container orchestration |
| PostgreSQL | Relational database |
| Ansible | Configuration and health checks |
| Prometheus | Metrics collection |
| Grafana | Monitoring and alerting |
| cAdvisor | Container resource monitoring |
| AWS S3 | Build artifact storage |
| Terraform | Infrastructure as Code |
| AWS IAM | Access management |

## Application Features

The Employee Management System provides:

- Add employee records.
- Retrieve employee records from PostgreSQL.
- Display employee information in a JSP web interface.
- Connect Java to PostgreSQL using JDBC.
- Use environment variables for database configuration.

### Database Configuration

The application uses:

- Database: `devopsdb`
- Table: `employees`
- Username: Configured through `DB_USER`
- Password: Configured through `DB_PASSWORD`
- Connection URL: Configured through `DB_URL`

Example Docker database hostname:

`jdbc:postgresql://devops-postgres:5432/devopsdb`

## CI/CD Pipeline

The Jenkins pipeline automates:

1. Source code checkout from GitHub.
2. Tool and environment validation.
3. Maven application build.
4. Docker image creation.
5. Importing the image into the Kubernetes environment.
6. Kubernetes configuration validation.
7. Application deployment.
8. Deployment verification.

## Docker

Docker is used to containerize the Java application and run supporting services.

The project uses containers for:

- Java application.
- PostgreSQL.
- Prometheus.
- Grafana.
- cAdvisor.

Docker networking enables the application container to communicate with PostgreSQL using the database container name.

## Kubernetes

The application is deployed to a local Kubernetes cluster.

Implemented components:

- Kubernetes Deployment.
- Two application replicas.
- Kubernetes Service.
- NodePort service configuration.
- Application deployment verification.
- Port forwarding for local access.

## Ansible Automation

Ansible is used to automate application and database health checks.

The playbook `ansible/check-containers.yml`:

- Checks application container information.
- Verifies the application container is running.
- Tests the application HTTP endpoint.
- Checks PostgreSQL container information.
- Verifies PostgreSQL health.

## Monitoring and Alerting

Prometheus, Grafana, and cAdvisor provide container monitoring.

**Prometheus**
- Collects monitoring metrics.
- Scrapes Prometheus and cAdvisor endpoints.

**Grafana**
- Visualizes monitoring metrics.
- Provides dashboards.
- Supports alert rules and email notifications.

**cAdvisor**
- Collects Docker container resource metrics.

## AWS S3 and Terraform

Terraform is used to manage AWS S3 infrastructure.

The infrastructure configuration includes:

- S3 bucket provisioning.
- Public access blocking.
- Server-side encryption.
- Resource tagging.

AWS S3 is used for build artifact storage.

## Security Practices

- Database credentials are supplied through environment variables.
- Monitoring credentials are kept in a local `.env` file.
- Sensitive environment files are excluded through `.gitignore`.
- AWS credentials are stored in Jenkins Credentials.
- S3 public access is blocked.
- S3 server-side encryption is enabled.
- Terraform state and secrets are not committed to GitHub.

## Verification Results

The following project components have been tested:

| Component | Verification |
|---|---|
| Jenkins | Pipeline build #16 successful |
| Docker | Application container running |
| Kubernetes | Two application pods running |
| PostgreSQL | Database healthy |
| JDBC | Employee records retrieved successfully |
| Employee INSERT | New employee successfully added |
| Ansible | All five playbook tasks passed |
| Prometheus | Container running |
| Grafana | Container running; notification tested |
| cAdvisor | Container healthy |

### Database Test

Example employee records:

| ID | Name | Department |
|---:|---|---|
| 1 | Vidya | DevOps |
| 2 | Rahul | Testing |
| 3 | Ananya | Cloud |

## Future Improvements

- Integrate GitHub webhooks.
- Add automated integration testing.
- Introduce vulnerability scanning.
- Deploy the application to Amazon EKS.
- Improve monitoring dashboards and alert coverage.
- Automate infrastructure deployment.

## Author

**Vidyashree K**

AWS Cloud & DevOps Engineer

GitHub: https://github.com/vidyashreekrishnamurthy

LinkedIn: https://linkedin.com/in/vidyashree-krishnamurthy-32269724b/
