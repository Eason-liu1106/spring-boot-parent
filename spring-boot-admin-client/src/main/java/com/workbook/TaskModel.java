package com.workbook;

public class TaskModel {
    public String taskName;
    public String taskDescripe;
    public String priority;
    public String date;
    public String createName;
    public String executor;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescripe() {
        return taskDescripe;
    }

    public void setTaskDescripe(String taskDescripe) {
        this.taskDescripe = taskDescripe;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDate() {
        return date;

    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }
}
