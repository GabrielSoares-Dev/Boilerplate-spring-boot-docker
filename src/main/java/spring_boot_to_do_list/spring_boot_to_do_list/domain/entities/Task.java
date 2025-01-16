package spring_boot_to_do_list.spring_boot_to_do_list.domain.entities;

import spring_boot_to_do_list.spring_boot_to_do_list.application.exceptions.BusinessException;
import spring_boot_to_do_list.spring_boot_to_do_list.domain.enums.TaskStatus;

public class Task {
    private String title;
    private String description;
    private TaskStatus status;

    public Task(String title, String description, TaskStatus status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

    private boolean isInvalidTitle() {
        return this.title == null || this.title.isEmpty();
    }

    private boolean isInvalidDescription() {
        return this.description == null || this.description.isEmpty();
    }

    public void create() throws BusinessException {

        if (this.isInvalidTitle()) {
            throw new BusinessException("Invalid title");
        }

        if (this.isInvalidDescription()) {
            throw new BusinessException("Invalid description");
        }

        boolean isInvalidStatus = this.status != TaskStatus.PENDING;
        if (isInvalidStatus) {
            throw new BusinessException("Invalid status");
        }

    }

}
