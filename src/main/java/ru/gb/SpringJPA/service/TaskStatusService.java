package ru.gb.SpringJPA.service;

import org.springframework.stereotype.Service;
import ru.gb.SpringJPA.model.TaskStatus;

@Service
public class TaskStatusService {
    public boolean checkStatus(String status) {
        for(int i = 0; i < TaskStatus.values().length; i++) {
            if (TaskStatus.values()[i].getStatusDescription().equals(status)) return true;
        }

        return false;
    }

    public TaskStatus getStatusFromString(String status) {
        for(int i = 0; i < TaskStatus.values().length; i++) {
            if (TaskStatus.values()[i].getStatusDescription().equals(status)) return TaskStatus.values()[i];
        }

        return null;
    }
}
