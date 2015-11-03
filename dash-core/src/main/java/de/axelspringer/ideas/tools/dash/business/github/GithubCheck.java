package de.axelspringer.ideas.tools.dash.business.github;

import de.axelspringer.ideas.tools.dash.business.check.AbstractCheck;
import de.axelspringer.ideas.tools.dash.business.customization.Group;
import de.axelspringer.ideas.tools.dash.business.customization.Team;

public class GithubCheck extends AbstractCheck {

    private final GithubConfig githubConfig;

    private String regexForMatchingRepoNames = "";

    // eg. orgs/as-ideas or user/waschnick
    private String githubFullyQualifiedName;

    public GithubCheck(String name, Group group, Team team, GithubConfig githubConfig, String githubFullyQualifiedName, String regexForMatchingRepoNames) {
        super(name, group, team);
        this.githubConfig = githubConfig;
        this.regexForMatchingRepoNames = regexForMatchingRepoNames;
        this.githubFullyQualifiedName = githubFullyQualifiedName;
    }

    public GithubConfig githubConfig() {
        return githubConfig;
    }

    public String regexForMatchingRepoNames() {
        return regexForMatchingRepoNames;
    }

    public String githubFullyQualifiedName() {
        return githubFullyQualifiedName;
    }
}
