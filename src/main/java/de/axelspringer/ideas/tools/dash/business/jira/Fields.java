package de.axelspringer.ideas.tools.dash.business.jira;

import lombok.Data;

@Data
public class Fields {

    /**
     * Team
     */
    private CustomField customfield_10144;

    private Priority priority;

    private IssueType issuetype;

    private IssueStatus status;

    public boolean isBug() {
        return issuetype.getName().equalsIgnoreCase("Bug");
    }

}
