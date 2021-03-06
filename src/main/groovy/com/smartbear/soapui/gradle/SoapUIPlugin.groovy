package com.smartbear.soapui.gradle

import com.smartbear.soapui.gradle.extensions.*
import com.smartbear.soapui.gradle.tasks.*
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * SoapUI implementation of the plugin interface
 *
 * @author Sion Williams
 */
class SoapUIPlugin implements Plugin<Project> {
    static final SOAP_TEST_TASK = 'soaptest'
    static final TOOL_TASK = 'tool'
    static final SECURITY_TEST_TASK = 'securitytest'
    static final MOCK_TASK = 'mock'
    static final LOAD_TEST_TASK = 'loadtest'
    static final EXTENSION_NAME = 'soapui'

    @Override
    void apply(Project project) {
        // Create and install the extension object
        SoapUIPluginExtension soapUIPluginExtension = project.extensions.create(EXTENSION_NAME, SoapUIPluginExtension)
        soapUIPluginExtension.extensions.create("tool", SoapUIToolConvention)
        soapUIPluginExtension.extensions.create("security", SoapUISecurityConvention)
        soapUIPluginExtension.extensions.create("load", SoapUILoadConvention)
        soapUIPluginExtension.extensions.create("test", SoapUITestConvention)
        soapUIPluginExtension.extensions.create("mock", SoapUIMockConvention)

        configSoapTest(project, soapUIPluginExtension)
        configTool(project, soapUIPluginExtension)
        configMock(project, soapUIPluginExtension)
        configLoad(project, soapUIPluginExtension)
        configSecurityTest(project, soapUIPluginExtension)
    }

    /**
     * Configures soap test task.
     *
     * @param project Project
     * @param soapUIPluginExtension SoapUIPluginExtension
     */
    private void configSoapTest(Project project, SoapUIPluginExtension soapUIPluginExtension) {
        project.task(SOAP_TEST_TASK, type: TestTask) {
            conventionMapping.projectFile = { soapUIPluginExtension.test.projectFile }
            conventionMapping.settingsFile = { soapUIPluginExtension.test.settingsFile }
            conventionMapping.projectPassword = { soapUIPluginExtension.test.projectPassword }
            conventionMapping.settingsPassword = { soapUIPluginExtension.test.settingsPassword }

            conventionMapping.testSuite = { soapUIPluginExtension.test.testSuite }
            conventionMapping.testCase = { soapUIPluginExtension.test.testCase }
            conventionMapping.username = { soapUIPluginExtension.test.username }
            conventionMapping.password = { soapUIPluginExtension.test.password }
            conventionMapping.wssPasswordType = { soapUIPluginExtension.test.wssPasswordType }
            conventionMapping.domain = { soapUIPluginExtension.test.domain }
            conventionMapping.host = { soapUIPluginExtension.test.host }
            conventionMapping.endpoint = { soapUIPluginExtension.test.endpoint }
            conventionMapping.skip = { soapUIPluginExtension.test.skip }
            conventionMapping.globalProperties = { soapUIPluginExtension.test.globalProperties }
            conventionMapping.projectProperties = { soapUIPluginExtension.test.projectProperties }
            conventionMapping.outputFolder = { soapUIPluginExtension.test.outputFolder }
            conventionMapping.soapuiProperties = { soapUIPluginExtension.test.soapuiProperties }
            conventionMapping.printReport = { soapUIPluginExtension.test.printReport }
            conventionMapping.interactive = { soapUIPluginExtension.test.interactive }
            conventionMapping.exportAll = { soapUIPluginExtension.test.exportAll }
            conventionMapping.junitReport = { soapUIPluginExtension.test.junitReport }
            conventionMapping.testFailIgnore = { soapUIPluginExtension.test.testFailIgnore }
            conventionMapping.saveAfterRun = { soapUIPluginExtension.test.saveAfterRun }
        }
    }

    /**
     * Configures tool task.
     *
     * @param project Project
     * @param soapUIPluginExtension SoapUIPluginExtension
     */
    private void configTool(Project project, SoapUIPluginExtension soapUIPluginExtension) {
        project.task(TOOL_TASK, type: ToolTask) {
            conventionMapping.projectFile = { soapUIPluginExtension.tool.projectFile }
            conventionMapping.settingsFile = { soapUIPluginExtension.tool.settingsFile }
            conventionMapping.projectPassword = { soapUIPluginExtension.tool.projectPassword }
            conventionMapping.settingsPassword = { soapUIPluginExtension.tool.settingsPassword }

            conventionMapping.iface = { soapUIPluginExtension.tool.iface }
            conventionMapping.tool = { soapUIPluginExtension.tool.tool }
            conventionMapping.outputFolder = { soapUIPluginExtension.tool.outputFolder }
            conventionMapping.soapuiProperties = { soapUIPluginExtension.tool.soapuiProperties }
        }
    }

    /**
     * Configures mock task.
     *
     * @param project Project
     * @param soapUIPluginExtension SoapUIPluginExtension
     */
    private void configMock(Project project, SoapUIPluginExtension soapUIPluginExtension) {
        project.task(MOCK_TASK, type: MockServiceTask) {
            conventionMapping.projectFile = { soapUIPluginExtension.mock.projectFile }
            conventionMapping.settingsFile = { soapUIPluginExtension.mock.settingsFile }
            conventionMapping.projectPassword = { soapUIPluginExtension.mock.projectPassword }
            conventionMapping.settingsPassword = { soapUIPluginExtension.mock.settingsPassword }

            conventionMapping.skip = { soapUIPluginExtension.mock.skip }
            conventionMapping.globalProperties = { soapUIPluginExtension.mock.globalProperties }
            conventionMapping.projectProperties = { soapUIPluginExtension.mock.projectProperties }
            conventionMapping.saveAfterRun = { soapUIPluginExtension.mock.saveAfterRun }
            conventionMapping.soapuiProperties = { soapUIPluginExtension.mock.soapuiProperties }
        }
    }

    /**
     * Configures load task.
     *
     * @param project Project
     * @param soapUIPluginExtension SoapUIPluginExtension
     */
    private void configLoad(Project project, SoapUIPluginExtension soapUIPluginExtension) {
        project.task(LOAD_TEST_TASK, type: LoadTestTask) {
            conventionMapping.projectFile = { soapUIPluginExtension.load.projectFile }
            conventionMapping.settingsFile = { soapUIPluginExtension.load.settingsFile }
            conventionMapping.projectPassword = { soapUIPluginExtension.load.projectPassword }
            conventionMapping.settingsPassword = { soapUIPluginExtension.load.settingsPassword }
            conventionMapping.testSuite = { soapUIPluginExtension.load.testSuite }
            conventionMapping.testCase = { soapUIPluginExtension.load.testCase }
            conventionMapping.username = { soapUIPluginExtension.load.username }
            conventionMapping.password = { soapUIPluginExtension.load.password }
            conventionMapping.wssPasswordType = { soapUIPluginExtension.load.wssPasswordType }
            conventionMapping.domain = { soapUIPluginExtension.load.domain }
            conventionMapping.host = { soapUIPluginExtension.load.host }
            conventionMapping.endpoint = { soapUIPluginExtension.load.endpoint }
            conventionMapping.skip = { soapUIPluginExtension.load.skip }
            conventionMapping.outputFolder = { soapUIPluginExtension.load.outputFolder }
            conventionMapping.printReport = { soapUIPluginExtension.load.printReport }
            conventionMapping.saveAfterRun = { soapUIPluginExtension.load.saveAfterRun }
            conventionMapping.loadTest = { soapUIPluginExtension.load.loadTest }
            conventionMapping.limit = { soapUIPluginExtension.load.limit }
            conventionMapping.threadCount = { soapUIPluginExtension.load.threadCount }
        }
    }

    /**
     * Configures soap security test task.
     *
     * @param project Project
     * @param soapUIPluginExtension SoapUIPluginExtension
     */
    private void configSecurityTest(Project project, SoapUIPluginExtension soapUIPluginExtension) {
        project.task(SECURITY_TEST_TASK, type: SecurityTestTask) {
            conventionMapping.projectFile = { soapUIPluginExtension.security.projectFile }
            conventionMapping.settingsFile = { soapUIPluginExtension.security.settingsFile }
            conventionMapping.projectPassword = { soapUIPluginExtension.security.projectPassword }
            conventionMapping.settingsPassword = { soapUIPluginExtension.security.settingsPassword }
            conventionMapping.securityTest = { soapUIPluginExtension.security.securityTest }
            conventionMapping.testSuite = { soapUIPluginExtension.security.testSuite }
            conventionMapping.testCase = { soapUIPluginExtension.security.testCase }
            conventionMapping.username = { soapUIPluginExtension.security.username }
            conventionMapping.password = { soapUIPluginExtension.security.password }
            conventionMapping.wssPasswordType = { soapUIPluginExtension.security.wssPasswordType }
            conventionMapping.domain = { soapUIPluginExtension.security.domain }
            conventionMapping.host = { soapUIPluginExtension.security.host }
            conventionMapping.endpoint = { soapUIPluginExtension.security.endpoint }
            conventionMapping.skip = { soapUIPluginExtension.security.skip }
            conventionMapping.globalProperties = { soapUIPluginExtension.security.globalProperties }
            conventionMapping.projectProperties = { soapUIPluginExtension.security.projectProperties }
            conventionMapping.outputFolder = { soapUIPluginExtension.security.outputFolder }
            conventionMapping.printReport = { soapUIPluginExtension.security.printReport }
            conventionMapping.interactive = { soapUIPluginExtension.security.interactive }
            conventionMapping.exportAll = { soapUIPluginExtension.security.exportAll }
            conventionMapping.junitReport = { soapUIPluginExtension.security.junitReport }
            conventionMapping.testFailIgnore = { soapUIPluginExtension.security.testFailIgnore }
            conventionMapping.saveAfterRun = { soapUIPluginExtension.security.saveAfterRun }
        }
    }
}
