package com.application.projectmanager.Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "projects", schema = "public", catalog = "projectManagerDB")
public class ProjectsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "project_id")
    private Integer projectId;
    @Basic
    @Column(name = "project_name")
    private String projectName;
    @Basic
    @Column(name = "project_dead_line")
    private String projectDeadLine;
    @Basic
    @Column(name = "project_describe")
    private String projectDescribe;
    @Basic
    @Column(name = "projects_userid")
    private Integer projectsUserid;
    public Integer getProjectId() {
        return projectId;
    }
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public String getProjectDeadLine() {
        return projectDeadLine;
    }
    public void setProjectDeadLine(String projectDeadLine) {
        this.projectDeadLine = projectDeadLine;
    }
    public String getProjectDescribe() {
        return projectDescribe;
    }
    public void setProjectDescribe(String projectDescribe) {
        this.projectDescribe = projectDescribe;
    }
    public Integer getProjectsUserid() {
        return projectsUserid;
    }
    public void setProjectsUserid(Integer projectsUserid) {
        this.projectsUserid = projectsUserid;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectsEntity that = (ProjectsEntity) o;
        return Objects.equals(projectId, that.projectId) && Objects.equals(projectName, that.projectName) && Objects.equals(projectDeadLine, that.projectDeadLine) && Objects.equals(projectDescribe, that.projectDescribe) && Objects.equals(projectsUserid, that.projectsUserid);
    }
    @Override
    public int hashCode() {
        return Objects.hash(projectId, projectName, projectDeadLine, projectDescribe, projectsUserid);
    }
    @Override
    public String toString() {
        return "ProjectsEntity{" +
                "id=" + projectId +
                ", name='" + projectName + '\'' +
                ", userId=" + projectsUserid +
                ", description='" + projectDescribe + '\'' +
                '}';
    }
}
