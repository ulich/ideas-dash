package de.axelspringer.ideas.tools.dash.business.jira.issuestatemapper;

import de.axelspringer.ideas.tools.dash.business.jira.JiraConfig;
import de.axelspringer.ideas.tools.dash.business.jira.rest.Issue;
import de.axelspringer.ideas.tools.dash.presentation.State;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/**
 * Ensures that the DefaultIssueStateMapper is not used if another one is configured
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = IssueStateMapperCustomMapperTest.IssueStateMapperCustomMapperTestConfig.class)
public class IssueStateMapperCustomMapperTest {

    @Autowired
    private IssueStateMapper issueStateMapper;

    @Test
    public void testCorrectMapperInjected() {

        assertTrue(issueStateMapper instanceof CustomIssueStateMapper);
    }

    static class CustomIssueStateMapper implements IssueStateMapper {

        @Override
        public State mapToState(Issue issue) {
            return State.GREEN;
        }
    }

    @Configuration
    @Import(JiraConfig.class)
    static class IssueStateMapperCustomMapperTestConfig {

        @Bean
        public IssueStateMapper issueStateMapper() {
            return new CustomIssueStateMapper();
        }
    }
}
