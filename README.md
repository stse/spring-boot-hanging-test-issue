# Hanging test issue demonstration
Demonstrates the hanging test issue when a test annotated with `@DataJpaTest` is executed before a test annotated with `@SpringBootTest`.

## Project structure
This project is a simple spring boot application using Spring Data JPA and Spring Data ReST build with maven. It also contains two IntelliJ Run/Debug configuration to execute the tests in different orders.

## Issue
If the test `workspace.domain.WorkspaceRepositoryTest` annotated with `@DataJpaTest` before the test `workspace.api.rest.WorkspaceResourceTest` annotated with `@SpringBootTest` the second tests hangs while it tries to boot its application context. Use the Run/Debug configuration `JpaTestBeforeBootTest` to reporduce the behaviour. If you execute both tests the other way round with the Run/Debug configuration `BootTestBeforeJpaTest` then the issue does not occure.

## Workarounds
If figured out several workarounds to mitigate the issue which might also help to analyse the problem.

1. As said above the issue is dependend on the execution order of the tests.
2. Removing the Spring Data ReST dependecy in the pom solves the issue.
3. Making the initialization of the application event publisher lazy in `workspace.domain.Workspace.WorkspaceEventManager` solves the issue.
4. Adding a mock listerner in `workspace.domain.WorkspaceRepositoryTest` solves the issue. 

---
Discussed in: https://stackoverflow.com/questions/63829138/spring-context-initialisation-hangs-when-executing-a-test-annotated-with-spring
