package workspace.api.rest;

import workspace.domain.Workspace;
import workspace.domain.WorkspaceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("A workspace")
class WorkspaceResourceTest {
    static final String WORKSPACE_RESOURCE_IDENTIFIER = "/workspaces/{id}";

    @Autowired
    MockMvc client;

    @Autowired
    WorkspaceRepository workspaceRepository;

    @Test
    @DisplayName("is available as resource after it has been created")
    void workspacesAreAvailableAsResourcesAfterTheyHaveBeenCreated() throws Exception {
        final long workspaceId = workspaceRepository.save(new Workspace()).getId();

        client.perform(get(WORKSPACE_RESOURCE_IDENTIFIER, workspaceId))
                .andExpect(status().isOk());
    }

}
