package com.smartbear.soapui.gradle.tasks

import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

/**
 * Specification for the tool task
 *
 * @author Sion Williams
 */
class ToolTaskSpec extends Specification {
    Project project
    static final TASK_NAME = 'tool'

    def setup() {
        project = ProjectBuilder.builder().build()
    }

    def "add tool tasks to project"() {
        expect:
        project.tasks.findByName(TASK_NAME) == null
        when:
        project.task(TASK_NAME, type: TestTask) {
            projectFile = 'sample-soapui-tool-project.xml'
            tool = 'wsi,axis1,axis2'
            iface = 'IOrderService'
        }
        then:
        Task task = project.tasks.findByName(TASK_NAME)
        task != null
        task.group == 'SoapUI'
        task.description == 'Runs soapUI functional tests'

        task.projectFile == 'sample-soapui-tool-project.xml'
        task.tool == 'wsi,axis1,axis2'
        task.iface == 'IOrderService'
    }

    def "run tool task with no project.xml defined"() {
        expect:
        project.tasks.findByName(TASK_NAME) == null
        when:
        Task task = project.task(TASK_NAME, type: TestTask) {
            tool = 'wsi,axis1,axis2'
            iface = 'IOrderService'
        }
        task.run()

        then:
        project.tasks.findByName(TASK_NAME) != null
        thrown(GradleException)
    }
}
