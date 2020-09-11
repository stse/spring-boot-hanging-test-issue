# Hanging test issue demonstration
Demonstrates the hanging test issue when a test annotated with `@DataJpaTest` is executed before a test annotated with `@SpringBootTest`.

## Project structure
This project is a simple spring boot application using Spring Data JPA and Spring Data ReST build with maven. It also contains two IntelliJ Run/Debug configuration to execute the tests in different orders.

## Issue
If the test `workspace.domain.WorkspaceRepositoryTest` annotated with `@DataJpaTest` is executed before the test `workspace.api.rest.WorkspaceResourceTest` annotated with `@SpringBootTest` the second tests hangs while its application context is started. Use the Run/Debug configuration `JpaTestBeforeBootTest` to reproduce the behaviour. If you execute both tests the other way round with the Run/Debug configuration `BootTestBeforeJpaTest` then the issue does not occure.

## Workarounds
I figured out several workarounds to mitigate the issue which might also help to analyse the problem.

1. As I said above the issue is dependend on the execution order of the tests. Swapping the execution order solves the issue.
2. Removing the Spring Data ReST dependecy in the pom solves the issue.
3. Making the initialization of the application event publisher lazy in `workspace.domain.Workspace.WorkspaceEventManager` solves the issue.
4. Adding a mock listerner in `workspace.domain.WorkspaceRepositoryTest` solves the issue. 

After the discussion of this topic on StackOverflow the most convenient workaround or even the solution is perhaps to move the configuration for `@Asnyc` into its own configuration class. So remove the `@EnableAsnyc` from `Application` and create a new class annotated with `@Configuration` and `@EnableAsnc` in the same package. This ensures that in the JPATest `workspace.domain.WorkspaceRepositoryTest` is disabled because Spring Boot does not consider configuration clases automatically when `@DataJpaTest` is used but if the application is started then `@Asnyc` is enabled because Spring Boot finds the new configuration via the component scan.

---
Discussed in: https://stackoverflow.com/questions/63829138/spring-context-initialisation-hangs-when-executing-a-test-annotated-with-spring
