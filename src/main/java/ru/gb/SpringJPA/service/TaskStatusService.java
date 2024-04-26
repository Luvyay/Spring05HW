package ru.gb.SpringJPA.service;

import org.springframework.stereotype.Service;
import ru.gb.SpringJPA.model.TaskStatus;

/**
 * Класс для работы с TaskStatus
 */
@Service
public class TaskStatusService {
    /**
     * Метод, который проверяет находится ли данный статус в TaskStatus
     * @param status
     * @return
     */
    public boolean checkStatus(String status) {
        for(int i = 0; i < TaskStatus.values().length; i++) {
            if (TaskStatus.values()[i].getStatusDescription().equals(status)) return true;
        }

        return false;
    }

    /**
     * Метод, который на основе текстового статуса возвращает конкретный TaskStatus
     * @param status
     * @return
     */
    public TaskStatus getStatusFromString(String status) {
        for(int i = 0; i < TaskStatus.values().length; i++) {
            if (TaskStatus.values()[i].getStatusDescription().equals(status)) return TaskStatus.values()[i];
        }

        return null;
    }
}
