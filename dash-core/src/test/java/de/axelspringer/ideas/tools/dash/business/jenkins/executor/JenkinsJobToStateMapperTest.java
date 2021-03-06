package de.axelspringer.ideas.tools.dash.business.jenkins.executor;

import de.axelspringer.ideas.tools.dash.business.jenkins.domain.Build;
import de.axelspringer.ideas.tools.dash.business.jenkins.domain.JenkinsBuildInfo;
import de.axelspringer.ideas.tools.dash.business.jenkins.domain.JenkinsJobInfo;
import de.axelspringer.ideas.tools.dash.business.jenkins.domain.Property;
import de.axelspringer.ideas.tools.dash.presentation.State;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class JenkinsJobToStateMapperTest {

    private JenkinsJobToStateMapper mapper = new JenkinsJobToStateMapper();

    @Test
    public void identifyStatusFailedTestCountRegular() throws Exception {
        assertEquals(State.YELLOW, mapper.identifyStatus(buildInfo(false), 1, jobInfo(false, true, build())));
    }

    @Test
    public void identifyStatusFailedTestCountActiveBranch() throws Exception {
        assertEquals(State.GREEN, mapper.identifyStatus(buildInfo(false), 1, jobInfo(true, false, build())));
    }

    @Test
    public void identifyStatusFailedTestCountStaleBranch() throws Exception {
        assertEquals(State.GREY, mapper.identifyStatus(buildInfo(true), 1, jobInfo(true, false, build())));
    }

    @Test
    public void identifyStatusGreenIfBuildInfoIsNull() throws Exception {
        assertEquals(State.GREEN, mapper.identifyStatus(null, 1, jobInfo(true, false, build())));
    }

    @Test
    public void identifyStatusWithNullBuildInfoRegular() {
        assertEquals(State.RED, mapper.identifyStatus(buildInfo(false), 0, jobInfo(false, true, build())));
    }

    @Test
    public void identifyStatusWithNullBuildInfoActiveBranch() {
        assertEquals(State.GREY, mapper.identifyStatus(buildInfo(false), 0, jobInfo(true, false, build())));
    }

    @Test
    public void identifyStatusWithNullBuildInfoStaleBranch() {
        assertEquals(State.YELLOW, mapper.identifyStatus(buildInfo(true), 0, jobInfo(true, false, build())));
    }

    private Build build() {
        final Build build = new Build();
        return build;
    }

    private JenkinsJobInfo jobInfo(boolean multibranch, boolean master, Build lastBuild) {
        final JenkinsJobInfo jobInfo = new JenkinsJobInfo();
        jobInfo.setName(master ? "master" : "featureABC");
        jobInfo.setProperties(Collections.singletonList(new Property(multibranch ? Property.MULTIBRANCH_CLASS : "SomeOtherClass")));
        jobInfo.setLastBuild(lastBuild);
        return jobInfo;
    }

    private JenkinsBuildInfo buildInfo(boolean twoWeeksOld) {

        final JenkinsBuildInfo jenkinsBuildInfo = new JenkinsBuildInfo();
        long twoWeeksAgo = System.currentTimeMillis() - (14 * 24 * 3600 * 1000);
        jenkinsBuildInfo.setTimestamp(twoWeeksOld ? twoWeeksAgo : System.currentTimeMillis());
        return jenkinsBuildInfo;
    }
}